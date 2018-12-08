package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.audio.PositionSpec;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.INullableRecipeInput;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IRecipeMachine;
import ic2.api.classic.tile.MachineType;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.energy.EnergyNet;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.audio.AudioSource;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.comparator.ComparatorManager;
import ic2.core.block.base.util.comparator.comparators.ComparatorProgress;
import ic2.core.block.base.util.info.EnergyUsageInfo;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.block.base.util.info.misc.IEnergyUser;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.*;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.helpers.FilteredList;
import ic2.core.util.misc.FluidHelper;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IOutputMachine;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.container.ContainerOreWashingPlant;

import java.util.*;

public class TileEntityOreWashingPlant extends TileEntityBasicElectricMachine implements ITankListener
{

    @NetworkField(index = 13)
    public IC2Tank waterTank = new IC2Tank(FluidRegistry.getFluidStack(FluidRegistry.WATER.getName(), 0), 10000);

    public static IMachineRecipeList oreWashingPlant = new BasicMachineRecipeList("oreWashingPlant");

    public static final int slotInput = 0;
    public static final int slotFuel = 1;
    public static final int slotOutput = 2;
    public static final int slotOutput2 = 3;
    public static final int slotOutput3 = 4;
    public static final int slotInputTank = 5;
    public static final int slotOutputTank = 6;

    public TileEntityOreWashingPlant()
    {
        super(7, 8, 400, 32);
        this.setCustomName("tileOreWashingPlant");
        this.waterTank.addListener(this);
        this.waterTank.setCanFill(true);
        this.addGuiFields("recipeOperation", "recipeEnergy", "progress", "waterTank");
    }


    @Override
    protected void addSlots(InventoryHandler handler)
    {
        this.filter = new MachineFilter(this);
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput, slotInputTank);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput, slotOutput2, slotOutput3, slotOutputTank);
        handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), 0, 2, 4);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.getOppositeList(), 1, 3);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
        handler.registerSlotType(SlotType.Fuel, slotFuel);
        handler.registerSlotType(SlotType.Input, slotInput, slotInputTank);
        handler.registerSlotType(SlotType.Output, slotOutput, slotOutput2, slotOutput3, slotOutputTank);
    }

    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input)
    {
        return oreWashingPlant.getRecipeInAndOutput(input, false);
    }

    public boolean canWorkWithoutItems()
    {
        return false;
    }

    public ResourceLocation getStartSoundFile()
    {
        return Ic2Sounds.maceratorOp;
    }

    public ResourceLocation getInterruptSoundFile()
    {
        return Ic2Sounds.interruptingSound;
    }

    @Override
    public void update()
    {
        this.handleChargeSlot(500);
        this.updateNeighbors();
        if (!this.inventory.get(slotInputTank).isEmpty())
        {
            this.handleTank();
        }

        boolean noRoom = this.addToInventory();
        IMachineRecipeList.RecipeEntry entry = this.getRecipe();
        boolean canWork = this.canWork() && !noRoom;
        boolean operate = canWork && entry != null && this.waterTank.getFluidAmount() >= 1000;
        if (operate)
        {
            this.handleChargeSlot(this.maxEnergy);
        }

        if (operate && this.energy >= this.energyConsume)
        {
            if (!this.getActive())
            {
                this.getNetwork().initiateTileEntityEvent(this, 0, false);
            }

            this.setActive(true);
            this.progress += this.progressPerTick;
            this.useEnergy(this.recipeEnergy);
            if (this.progress >= (float) this.recipeOperation)
            {
                this.operate(entry);
                this.progress = 0.0F;
                this.notifyNeighbors();
            }

            this.getNetwork().updateTileGuiField(this, "progress");
        }
        else
        {
            if (this.getActive())
            {
                if (this.progress != 0.0F)
                {
                    this.getNetwork().initiateTileEntityEvent(this, 1, false);
                }
                else
                {
                    this.getNetwork().initiateTileEntityEvent(this, 2, false);
                }
            }

            if (entry == null && this.progress != 0.0F)
            {
                this.progress = 0.0F;
                this.getNetwork().updateTileGuiField(this, "progress");
            }

            this.setActive(false);
        }

        for (int i = 0; i < 4; ++i)
        {
            ItemStack item = this.inventory.get(i + this.inventory.size() - 4);
            if (item.getItem() instanceof IMachineUpgradeItem)
            {
                ((IMachineUpgradeItem) item.getItem()).onTick(item, this);
            }
        }

        this.updateComparators();
    }

    public void handleTank()
    {
        IFluidHandlerItem containerFluidHandler = FluidUtil.getFluidHandler(this.inventory.get(slotInputTank));

        if (FluidHelper.hasFluid(containerFluidHandler, FluidRegistry.getFluidStack(FluidRegistry.WATER.getName(), 1), false))
        {
            if (this.waterTank.getFluidAmount() + FluidUtil.getFluidContained(this.inventory.get(slotInputTank)).amount <= this.waterTank.getCapacity())
            {
                RangedInventoryWrapper output = new RangedInventoryWrapper(this, slotOutputTank);
                if (FluidHelper.drainContainers(this.waterTank, this, slotInputTank, output))
                {
                    this.getNetwork().updateTileGuiField(this, "tank");
                    this.setStackInSlot(slotOutputTank, output.getStackInSlot(0));
                }
            }
        }
    }

    @Override
    public void operate(IMachineRecipeList.RecipeEntry entry)
    {
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
            for (int i = 0; i < 4 && i < list.size(); i++) {
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
            this.waterTank.drain(1000, true);
        }

    }

    @Override
    public void operateOnce(IRecipeInput input, MachineOutput output, List<ItemStack> list)
    {
        super.operateOnce(input, output, list);
    }

    private IMachineRecipeList.RecipeEntry getRecipe()
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


    public FluidStack getFluid()
    {
        return this.waterTank.getFluid();
    }

    public int getPixel()
    {
        return (int) ((double) this.waterTank.getFluidAmount() / (double) this.waterTank.getCapacity() * 58.0D);
    }

    @Override
    public void onTankChanged(IFluidTank tank)
    {
        this.getNetwork().updateTileGuiField(this, "waterTank");
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.waterTank.readFromNBT(nbt.getCompoundTag("Tank"));
        this.progress = nbt.getFloat("progress");
        this.results.clear();
        NBTTagList list = nbt.getTagList("Results", 10);

        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound data = list.getCompoundTagAt(i);
            this.results.add(new ItemStack(data));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setFloat("progress", this.progress);
        NBTTagList list = new NBTTagList();
        Iterator var3 = this.results.iterator();

        while (var3.hasNext())
        {
            ItemStack item = (ItemStack) var3.next();
            NBTTagCompound data = new NBTTagCompound();
            item.writeToNBT(data);
            list.appendTag(data);
        }

        nbt.setTag("Results", list);
        this.waterTank.writeToNBT(this.getTag(nbt, "Tank"));
        return nbt;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player)
    {
        return new ContainerOreWashingPlant(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player)
    {
        return GuiComponentContainer.class;
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer)
    {

    }

    @Override
    public boolean hasGui(EntityPlayer player)
    {
        return true;
    }

    @Override
    public LocaleComp getBlockName()
    {
        return new LangComponentHolder.LocaleBlockComp("tile.oreWashingPlant");
    }

    public ResourceLocation getGuiTexture()
    {
        return new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guiorewashingplant.png");
    }

    @Override
    public MachineType getType()
    {
        return MachineType.macerator;
    }

    @Override
    public IMachineRecipeList getRecipeList()
    {
        return oreWashingPlant;
    }

    @Override
    public IHasInventory getOutputInventory()
    {
        return new RangedInventoryWrapper(this, slotOutput, slotOutput2, slotOutput3, slotOutputTank);
    }

    @Override
    public IHasInventory getInputInventory()
    {
        return new RangedInventoryWrapper(this, slotInput, slotInputTank);
    }

    @Override
    public String getName()
    {
        return "OreWashingPlant";
    }

    @Override
    public boolean hasCustomName()
    {
        return true;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T)this.waterTank : super.getCapability(capability, facing);
    }



    public static void init() { //recipes in recipes class now
    }

    public static void addRecipe(IRecipeInput input, MachineOutput output, String id)
    {
        oreWashingPlant.addRecipe(input, output, id);
    }

    private static String makeString(ItemStack stack)
    {
        return stack.getDisplayName();
    }
}
