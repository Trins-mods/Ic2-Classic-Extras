package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.recipe.INullableRecipeInput;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.helpers.FilteredList;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.container.ContainerThermalCentrifuge;

import java.util.Iterator;
import java.util.List;

public class TileEntityThermalCentrifuge extends TileEntityBasicElectricMachine
{
    public static final int maxHeat = 1000;
    public int heat;
    public TileEntityThermalCentrifuge() {
        super( 5, 48, 400, 128);
        this.setCustomName("tileThermalCentrifuge");
        this.addGuiFields("heat");
    }

    public float getHeat() {
        return (float)this.heat;
    }

    public float getMaxHeat() { return (float)this.maxHeat; }

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return thermalCentrifuge.getRecipeInAndOutput(input, false);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return new ResourceLocation("ic2c_extras", "textures/guiSprites/GUIThermalCentrifuge.png");
    }

    public static IMachineRecipeList thermalCentrifuge = new BasicMachineRecipeList("thermalCentrifuge");

    public static final int slotInput = 0;
    public static final int slotFuel = 1;
    public static final int slotOutput = 2;
    public static final int slotOutput2 = 3;
    public static final int slotOutput3 = 4;

    @Override
    protected void addSlots(InventoryHandler handler)
    {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput, slotOutput2, slotOutput3);
        handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), 0, 2, 4);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.getOppositeList(), 1, 3);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
        handler.registerSlotType(SlotType.Fuel, slotFuel);
        handler.registerSlotType(SlotType.Input, slotInput);
        handler.registerSlotType(SlotType.Output, slotOutput, slotOutput2, slotOutput3);
    }

    @Override
    public ResourceLocation getStartSoundFile()
    {
        return Ic2Sounds.maceratorOp;
    }

    @Override
    public ResourceLocation getInterruptSoundFile()
    {
        return Ic2Sounds.interruptingSound;
    }


    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerThermalCentrifuge(player.inventory, this);
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return thermalCentrifuge;
    }

    @Override
    public MachineType getType() {
        return MachineType.macerator;
    }

    @Override
    public void update() {
        this.handleRedstone();
        this.updateNeighbors();
        boolean noRoom = this.addToInventory();
        IMachineRecipeList.RecipeEntry entry = this.getRecipe();
        boolean canWork = this.canWork() || entry != null && this.energy > 0;
        boolean operate = canWork && !noRoom && entry != null;
        if (canWork || entry != null) {
            this.handleChargeSlot(this.recipeEnergy * this.recipeOperation);
        }

        if ((canWork || entry != null) && this.energy > 0) {
            if (this.heat < 1000) {
                ++this.heat;
                this.getNetwork().updateTileGuiField(this, "heat");
            }

            this.useEnergy(1);
        } else if (this.heat > 0) {
            this.heat -= Math.min(this.heat, 4);
            this.getNetwork().updateTileGuiField(this, "heat");
        }

        if (operate && this.energy >= this.energyConsume) {
            if (!this.getActive()) {
                this.getNetwork().initiateTileEntityEvent(this, 0, false);
            }

            this.setActive(true);
            this.progress += this.progressPerTick;
            this.useEnergy(this.recipeEnergy);
            if (this.progress >= (float)this.recipeOperation) {
                this.operate(entry);
                this.progress = 0.0F;
                this.notifyNeighbors();
            }

            this.getNetwork().updateTileGuiField(this, "progress");
        } else {
            if (this.getActive()) {
                if (this.progress != 0.0F) {
                    this.getNetwork().initiateTileEntityEvent(this, 1, false);
                } else {
                    this.getNetwork().initiateTileEntityEvent(this, 2, false);
                }
            }

            if (entry == null && this.progress != 0.0F) {
                this.progress = 0.0F;
                this.getNetwork().updateTileGuiField(this, "progress");
            }

            this.setActive(false);
        }

        for(int i = 0; i < 2; ++i) {
            ItemStack item = (ItemStack)this.inventory.get(i + this.inventory.size() - 2);
            if (!item.isEmpty() && item.getItem() instanceof IMachineUpgradeItem) {
                ((IMachineUpgradeItem)item.getItem()).onTick(item, this);
            }
        }

        this.updateComparators();
    }

    public IMachineRecipeList.RecipeEntry getRecipe()
    {
        if (this.inventory.get(slotInput).isEmpty() && !this.canWorkWithoutItems())
        {
            return null;
        }
        else
        {
            if (this.lastRecipe != null)
            {
                IRecipeInput recipe = this.lastRecipe.getInput();
                if (recipe instanceof INullableRecipeInput)
                {
                    if (!recipe.matches(this.inventory.get(slotInput)))
                    {
                        this.lastRecipe = null;
                    }
                }
                else if (!this.inventory.get(slotInput).isEmpty() && recipe.matches(this.inventory.get(0)))
                {
                    if (recipe.getAmount() > this.inventory.get(slotInput).getCount())
                    {
                        return null;
                    }
                }
                else
                {
                    this.lastRecipe = null;
                }
            }

            if (this.lastRecipe == null)
            {
                IMachineRecipeList.RecipeEntry out = this.getOutputFor(this.inventory.get(slotInput).copy());
                if (out == null)
                {
                    return null;
                }

                this.lastRecipe = out;
                this.handleModifiers(out);
            }

            if (this.lastRecipe == null)
            {
                return null;
            }
            else if (this.inventory.get(slotOutput).getCount() >= this.inventory.get(slotOutput).getMaxStackSize())
            {
                return null;
            }
            else if (this.inventory.get(slotOutput2).getCount() >= this.inventory.get(slotOutput2).getMaxStackSize())
            {
                return null;
            }
            else if (this.inventory.get(slotOutput3).getCount() >= this.inventory.get(slotOutput3).getMaxStackSize())
            {
                return null;
            }
            else if (this.inventory.get(slotOutput).isEmpty())
            {
                return this.lastRecipe;
            }
            else
            {
                Iterator var4 = this.lastRecipe.getOutput().getAllOutputs().iterator();

                ItemStack output;
                do
                {
                    if (!var4.hasNext())
                    {
                        return null;
                    }

                    output = (ItemStack) var4.next();
                }
                while (!StackUtil.isStackEqual(this.inventory.get(slotOutput), output, false, true));

                return this.lastRecipe;
            }
        }
    }

    @Override
    public void operateOnce(IRecipeInput input, MachineOutput output, List<ItemStack> list)
    {
        super.operateOnce(input, output, list);
    }

    @Override
    public void operate(IMachineRecipeList.RecipeEntry entry) {

        IRecipeInput input = entry.getInput();
        MachineOutput output = entry.getOutput().copy();

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemStack = this.inventory.get(i + this.inventory.size() - 4);
            if (itemStack.getItem() instanceof IMachineUpgradeItem)
            {
                IMachineUpgradeItem item = (IMachineUpgradeItem) itemStack.getItem();
                item.onProcessEndPre(itemStack, this, output);
            }
        }

        List<ItemStack> list = new FilteredList();
        this.operateOnce(input, output, list);

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemStack = this.inventory.get(i + this.inventory.size() - 4);
            if (itemStack.getItem() instanceof IMachineUpgradeItem)
            {
                IMachineUpgradeItem item = (IMachineUpgradeItem) itemStack.getItem();
                item.onProcessEndPost(itemStack, this, input, output, list);
            }
        }

        if (list.size() > 0)
        {
            for (int i = 0; i < 3 && i < list.size(); i++) {
                // Dangerous thing here. Might dupe items if there is random rolls
                ItemStack toAdd = list.get(i);
                if (toAdd.isEmpty()) {
                    continue;
                }
                if (getStackInSlot(slotOutput + i).isEmpty()) {
                    setStackInSlot(slotOutput + i, toAdd);
                } else {
                    getStackInSlot(slotOutput + i).grow(toAdd.getCount());
                }
            }
        }
    }


    public static void init() { //recipes in recipes class now
    }

    public static void addRecipe(IRecipeInput input, MachineOutput output, String id)
    {
        thermalCentrifuge.addRecipe(input, output, id);
    }

    private static String makeString(ItemStack stack)
    {
        return stack.getDisplayName();
    }
}
