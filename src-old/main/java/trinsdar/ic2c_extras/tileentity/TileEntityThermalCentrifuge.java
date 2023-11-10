package trinsdar.ic2c_extras.tileentity;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.RecipeModifierHelpers;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IStackOutput;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.base.util.output.SimpleStackOutput;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.helpers.CompareableStack;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Optional;
import trinsdar.ic2c_extras.container.ContainerThermalCentrifuge;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.GuiMachine.ThermalCentrifugeGui;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes.thermalCentrifuge;

@Optional.Interface(iface = "gtclassic.api.interfaces.IGTDebuggableTile", modid = "gtclassic", striprefs = true)
public class TileEntityThermalCentrifuge extends TileEntityBasicElectricMachine implements IGTDebuggableTile {
    @NetworkField(index = 13)
    public int maxHeat = 500;
    @NetworkField(index = 14)
    public int heat;

    private static final int defaultEu = 48;

    public static final String neededHeat = "minHeat";

    public TileEntityThermalCentrifuge() {
        super(5, defaultEu, 200, 128);
        this.addGuiFields("heat", "maxHeat");
    }

    public float getHeat() {
        return (float) this.heat;
    }

    public float getMaxHeat() {
        if (lastRecipe == null){
            return 500;
        }
        return (float) getRequiredHeat(lastRecipe.getOutput());
    }

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return thermalCentrifuge.getRecipeInAndOutput(input, false);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return Ic2cExtrasResourceLocations.THERMAL_CENTRIFUGE;
    }

    public static final int slotInput = 0;
    public static final int slotFuel = 1;
    public static final int slotOutput = 2;
    public static final int slotOutput2 = 3;
    public static final int slotOutput3 = 4;

    @Override
    protected void addSlots(InventoryHandler handler) {
        this.filter = new MachineFilter(this);
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput, slotOutput2, slotOutput3);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutput, slotOutput2, slotOutput3);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInput);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
        handler.registerInputFilter(this.filter, slotInput);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
        handler.registerSlotType(SlotType.Fuel, slotFuel);
        handler.registerSlotType(SlotType.Input, slotInput);
        handler.registerSlotType(SlotType.Output, slotOutput, slotOutput2, slotOutput3);
    }

    @Override
    public boolean isValidInput(ItemStack par1) {
        return super.isValidInput(par1) && isRecipeInputValid(par1);
    }

    public boolean isRecipeInputValid(ItemStack stack) {
        IRecipeInput input = Ic2cExtrasRecipes.thermalCentrifugeValidInputs.get(new CompareableStack(stack));
        if (input == null) {
            return false;
        }
        return input.matches(stack);
    }

    @Override
    public IHasInventory getInputInventory() {
        return new RangedInventoryWrapper(this, slotInput).setFilters(filter);
    }

    @Override
    public ResourceLocation getStartSoundFile() {
        return Ic2Sounds.extractorOp;
    }

    @Override
    public ResourceLocation getInterruptSoundFile() {
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
        return null;
    }

    @Override
    public double getWrenchDropRate() {
        return 1.0D;
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return ThermalCentrifugeGui.class;
    }


    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.THERMAL_CENTRIFUGE;
    }

    @Override
    protected EnumActionResult isRecipeStillValid(IMachineRecipeList.RecipeEntry entry) {
        if (heat >= getRequiredHeat(entry.getOutput())) {
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public boolean canWork() {
        return heat >= maxHeat && super.canWork();
    }

    @Override
    protected EnumActionResult canFillRecipeIntoOutputs(MachineOutput output) {
        List<ItemStack> result = output.getAllOutputs();
        for (int i = 0; i < result.size() && i < 3; i++) {
            ItemStack stack = getStackInSlot(slotOutput + i);
            ItemStack extra = result.get(i);
            if ((!stack.isEmpty() && !StackUtil.isStackEqual(stack, extra, false, true))
                    || stack.getCount() + extra.getCount() > extra.getMaxStackSize()) {
                return EnumActionResult.PASS;
            }
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public void operateOnce(IRecipeInput input, MachineOutput output, List<IStackOutput> list) {
        List<ItemStack> result = output.getRecipeOutput(getWorld().rand, getTileData());
        for (int i = 0; i < result.size(); i++) {
            list.add(new SimpleStackOutput(result.get(i), slotOutput + (i % 3)));
        }
        consumeInput(input, output.getMetadata());
    }

    @Override
    public void update() {
        super.update();
        if (((lastRecipe != null && !this.inventory.get(slotInput).isEmpty()) || super.canWork()) && this.energy > 0) {
            int newMaxHeat = (int) getMaxHeat();
            if (newMaxHeat != maxHeat) {
                maxHeat = newMaxHeat;
                getNetwork().updateTileGuiField(this, "maxHeat");
            }
            boolean maxHeatCheck = false;
            if (this.heat < maxHeat) {
                ++this.heat;
                maxHeatCheck = true;
                this.getNetwork().updateTileGuiField(this, "heat");
            }
            if (this.heat == maxHeat && maxHeatCheck) {
                checkRecipe = true;
                maxHeatCheck = false;
            }
            if (this.heat > maxHeat) {
                this.heat = maxHeat;
                this.checkRecipe = true;
                this.getNetwork().updateTileGuiField(this, "heat");
            }

            this.useEnergy(1);
        } else if (this.heat > 0) {
            int newMaxHeat = 500;
            if (newMaxHeat != maxHeat) {
                maxHeat = newMaxHeat;
                getNetwork().updateTileGuiField(this, "maxHeat");
            }
            this.heat -= Math.min(this.heat, 4);
            this.getNetwork().updateTileGuiField(this, "heat");
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.heat = nbt.getInteger("Heat");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Heat", this.heat);
        return nbt;
    }

    public static int getRequiredHeat(MachineOutput output) {
        if (output == null || output.getMetadata() == null) {
            return 1;
        }
        return output.getMetadata().getInteger(neededHeat);
    }

    public static void addRecipe(IRecipeInput input, int heat, ItemStack... output) {
        addRecipe(input, heat, 12000, output);
    }

    public static void addRecipe(IRecipeInput input, int hear, int totalEu, ItemStack... output) {
        addRecipe(input, hear, totalEu(totalEu), output);
    }

    public static void addRecipe(IRecipeInput input, int heat, RecipeModifierHelpers.IRecipeModifier[] modifiers, ItemStack... output) {
        List<ItemStack> outputlist = new ArrayList<>();
        NBTTagCompound nbt = new NBTTagCompound();
        for (RecipeModifierHelpers.IRecipeModifier modifier : modifiers) {
            modifier.apply(nbt);
        }
        nbt.setInteger(neededHeat, heat);
        if (heat <= 0) {
            nbt = null;
        }
        for (ItemStack stack : output) {
            outputlist.add(stack);
        }
        addRecipe(input, new MachineOutput(nbt, outputlist));
    }

    public static RecipeModifierHelpers.IRecipeModifier[] totalEu(int amount) {
        return new RecipeModifierHelpers.IRecipeModifier[]{RecipeModifierHelpers.ModifierType.RECIPE_LENGTH.create((amount / defaultEu) - 200)};
    }

    public static void addRecipe(IRecipeInput input, MachineOutput output) {
        thermalCentrifuge.addRecipe(input, output, output.getAllOutputs().get(0).getUnlocalizedName());
    }

    @Optional.Method(modid = "gtclassic")
    @Override
    public void getData(Map<String, Boolean> map) {
        map.put("Heat: " + heat, true);
        map.put("Max Heat: " + maxHeat, true);
    }
}
