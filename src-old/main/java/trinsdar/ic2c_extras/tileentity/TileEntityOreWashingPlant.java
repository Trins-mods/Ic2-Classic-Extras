package trinsdar.ic2c_extras.tileentity;

import gtclassic.api.fluid.GTFluidHandler;
import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IStackOutput;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.base.util.output.SimpleStackOutput;
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
import ic2.core.util.helpers.CompareableStack;
import ic2.core.util.misc.FluidHelper;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import trinsdar.ic2c_extras.container.ContainerOreWashingPlant;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.GuiMachine.OreWashingPlantGui;
import trinsdar.ic2c_extras.util.StackHelper;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.List;
import java.util.Map;

import static trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes.oreWashingPlant;

@Optional.Interface(iface = "gtclassic.api.interfaces.IGTDebuggableTile", modid = "gtclassic", striprefs = true)
public class TileEntityOreWashingPlant extends TileEntityBasicElectricMachine implements ITankListener, IClickable, IGTDebuggableTile {
    @NetworkField(index = 13)
    private IC2Tank waterTank = new IC2Tank(16000) {
        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return super.canFillFluidType(fluid) && fluid.getFluid() == FluidRegistry.WATER;
        }
    };
    public static final String waterAmount = "amount";
    public int water = 0;
    public int maxWater = 10000;
    public static final int slotInput = 0;
    public static final int slotFuel = 1;
    public static final int slotOutput = 2;
    public static final int slotOutput2 = 3;
    public static final int slotOutput3 = 4;
    public static final int slotInputTank = 5;
    public static final int slotOutputTank = 6;

    public TileEntityOreWashingPlant() {
        super(7, 8, 400, 32);
        this.waterTank.addListener(this);
        this.waterTank.setCanFill(true);
        this.addGuiFields("waterTank");
    }

    public IC2Tank getWaterTank() {
        return this.waterTank;
    }


    @Override
    protected void addSlots(InventoryHandler handler) {
        this.filter = new MachineFilter(this);
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput, slotOutput2, slotOutput3, slotOutputTank);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutput, slotOutput2, slotOutput3);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInput);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
        handler.registerInputFilter(this.filter, slotInput);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
        handler.registerSlotType(SlotType.Fuel, slotFuel);
        handler.registerSlotType(SlotType.Input, slotInput);
        handler.registerSlotType(SlotType.Output, slotOutput, slotOutput2, slotOutput3, slotOutputTank);
    }

    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return oreWashingPlant.getRecipeInAndOutput(input, false);
    }

    @Override
    public boolean isValidInput(ItemStack par1) {
        return super.isValidInput(par1) && isRecipeInputValid(par1);
    }

    public boolean isRecipeInputValid(ItemStack stack) {
        IRecipeInput input = Ic2cExtrasRecipes.oreWashingPlantValidInputs.get(new CompareableStack(stack));
        if (input == null) {
            return false;
        }
        return input.matches(stack);
    }

    @Override
    public ResourceLocation getStartSoundFile() {
        return Ic2cExtrasResourceLocations.oreWashingPlantOp;
    }

    @Override
    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

    @Override
    public void update() {
        if (!this.inventory.get(slotInputTank).isEmpty()) {
            StackHelper.doFluidContainerThings(this, this.waterTank, slotInputTank, slotOutputTank);
        }
        super.update();
    }

    @Override
    public boolean canWork() {
        return super.canWork() && waterTank.getFluidAmount() >= 1000;
    }

    @Override
    protected EnumActionResult isRecipeStillValid(IMachineRecipeList.RecipeEntry entry) {
        if (waterTank.getFluidAmount() >= getRequiredWater(entry.getOutput())) {
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
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
        this.waterTank.drain(getRequiredWater(output), true);
    }


    public FluidStack getFluid() {
        return this.waterTank.getFluid();
    }

    public int getPixel() {
        return (int) ((double) this.waterTank.getFluidAmount() / (double) this.waterTank.getCapacity() * 58.0D);
    }

    @Override
    public void onTankChanged(IFluidTank tank) {
        this.getNetwork().updateTileGuiField(this, "waterTank");
        if (lastRecipe != null && activeRecipe == null) {
            this.checkRecipe = true;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.waterTank.readFromNBT(nbt.getCompoundTag("Tank"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.waterTank.writeToNBT(this.getTag(nbt, "Tank"));
        return nbt;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerOreWashingPlant(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return OreWashingPlantGui.class;
    }


    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.ORE_WASHING_PLANT;
    }

    public ResourceLocation getGuiTexture() {
        return Ic2cExtrasResourceLocations.ORE_WASHING_PLANT;
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
    public IMachineRecipeList getRecipeList() {
        return oreWashingPlant;
    }

    @Override
    public IHasInventory getOutputInventory() {
        return new RangedInventoryWrapper(this, slotOutput, slotOutput2, slotOutput3, slotOutputTank);
    }

    @Override
    public IHasInventory getInputInventory() {
        return new RangedInventoryWrapper(this, slotInput).setFilters(filter);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.waterTank);
        }
        return super.getCapability(capability, facing);
    }

    public static int getRequiredWater(MachineOutput output) {
        if (output == null || output.getMetadata() == null) {
            return 1000;
        }
        return output.getMetadata().getInteger(waterAmount);
    }

    protected static NBTTagCompound createNeededWater(int amount) {
        if (amount <= 0) {
            return null;
        }
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger(waterAmount, amount);
        return nbt;
    }

    public static void addRecipe(IRecipeInput input, int water, ItemStack... output) {
        addRecipe(input, new MachineOutput(createNeededWater(water), output));
    }

    public static void addRecipe(IRecipeInput input, MachineOutput output) {
        oreWashingPlant.addRecipe(input, output, output.getAllOutputs().get(0).getUnlocalizedName());
    }

    @Override
    public boolean hasRightClick() {
        return true;
    }

    @Override
    public boolean onRightClick(EntityPlayer player, EnumHand hand, EnumFacing enumFacing, Side side) {
        ItemStack playerStack = player.getHeldItem(hand);
        if (!playerStack.isEmpty()) {
            FluidActionResult result = FluidUtil.tryEmptyContainer(playerStack, waterTank, this.waterTank.getCapacity() - this.waterTank.getFluidAmount(), player, true);
            if (result.isSuccess()) {
                playerStack.shrink(1);
                ItemStack resultStack = result.getResult();
                if (!resultStack.isEmpty()) {
                    if (!player.inventory.addItemStackToInventory(resultStack)) {
                        player.dropItem(resultStack, false);
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasLeftClick() {
        return false;
    }

    @Override
    public void onLeftClick(EntityPlayer entityPlayer, Side side) {

    }

    @Optional.Method(modid = "gtclassic")
    @Override
    public void getData(Map<String, Boolean> map) {
        FluidStack fluid = this.waterTank.getFluid();
        int amount = fluid != null ? fluid.amount : 0;
        map.put("Contains " + amount + " mb of Water", false);
    }
}
