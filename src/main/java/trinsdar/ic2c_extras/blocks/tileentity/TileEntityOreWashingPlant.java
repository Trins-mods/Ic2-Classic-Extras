package trinsdar.ic2c_extras.blocks.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.fluid.IC2Tank;
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
import ic2.core.util.helpers.FilteredList;
import ic2.core.util.misc.FluidHelper;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import trinsdar.ic2c_extras.blocks.container.ContainerOreWashingPlant;
import trinsdar.ic2c_extras.util.GuiMachine.OreWashingPlantGui;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import javax.annotation.Nullable;
import java.util.List;

import static trinsdar.ic2c_extras.util.Ic2cExtrasRecipes.oreWashingPlant;

public class TileEntityOreWashingPlant extends TileEntityBasicElectricMachine implements ITankListener, IFluidHandler
{

    @NetworkField(index = 13)
    public IC2Tank waterTank = new IC2Tank(FluidRegistry.getFluidStack(FluidRegistry.WATER.getName(), 0), 10000);

    public int water = 0;
    public int maxWater = 10000;



    public static final int slotInput = 0;
    public static final int slotFuel = 1;
    public static final int slotOutput = 2;
    public static final int slotOutput2 = 3;
    public static final int slotOutput3 = 4;
    public static final int slotInputTank = 5;
    public static final int slotOutputTank = 6;

    public TileEntityOreWashingPlant()
    {
        super(7, 16, 400, 32);
        this.waterTank.addListener(this);
        this.waterTank.setCanFill(true);
        this.addGuiFields("waterTank");
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
        return Ic2cExtrasResourceLocations.oreWashingPlantOp;
    }

    public ResourceLocation getInterruptSoundFile()
    {
        return Ic2Sounds.interruptingSound;
    }

    @Override
    public void update()
    {
        if (!this.inventory.get(slotInputTank).isEmpty())
        {
            this.handleTank();
        }
        super.update();
    }

    @Override
    public boolean canWork()
    {
        if(super.canWork())
        {
            return waterTank.getFluidAmount() >= 1000;
        }
        return false;
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
            this.waterTank.drain(1000, true);
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
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
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
        return OreWashingPlantGui.class;
    }


    @Override
    public LocaleComp getBlockName()
    {
        return Ic2cExtrasLang.oreWashingPlant;
    }

    public ResourceLocation getGuiTexture()
    {
        return Ic2cExtrasResourceLocations.oreWashingPlant;
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
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[]{new FluidTankProperties(new FluidStack(FluidRegistry.WATER, this.water), 10000)};
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        if (resource != null && resource.getFluid() == FluidRegistry.WATER) {
            int toAdd = Math.min(resource.amount, maxWater - this.water);
            if (doFill) {
                this.water += toAdd;
                this.getNetwork().updateTileGuiField(this, "water");
            }

            return toAdd;
        } else {
            return 0;
        }
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return resource != null && resource.getFluid() == FluidRegistry.WATER ? this.drain(resource.amount, doDrain) : null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        int amount = Math.min(maxDrain, this.water);
        if (amount <= 0) {
            return null;
        } else {
            if (doDrain) {
                this.water -= amount;
                this.getNetwork().updateTileGuiField(this, "water");
            }

            return new FluidStack(FluidRegistry.WATER, amount);
        }
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

    public static void addRecipe(IRecipeInput input, MachineOutput output)
    {
        oreWashingPlant.addRecipe(input, output, input.getInputs().get(0).getDisplayName());
    }

}
