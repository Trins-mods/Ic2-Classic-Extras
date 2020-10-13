package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.energy.tile.IHeatSource;
import ic2.api.recipe.IFermenterRecipeManager.FermentationProperty;
import ic2.api.recipe.Recipes;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.obj.IOutputMachine;
import ic2.core.util.obj.ITankListener;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.jetbrains.annotations.Nullable;
import trinsdar.ic2c_extras.container.ContainerFermenter;
import trinsdar.ic2c_extras.util.StackHelper;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TileEntityFermenter extends TileEntityMachine implements IOutputMachine, ITickable, IFluidHandler, IHasGui, ITankListener {
    int heat;
    @NetworkField(index = 3)
    IC2Tank inputTank = new IC2Tank(10000){
        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return super.canFillFluidType(fluid) && fluid.getFluid().getName().equals("biomass");
        }
    };
    @NetworkField(index = 4)
    IC2Tank outputTank = new IC2Tank(2000);
    @NetworkField(index = 5)
    int fertProgress = 0;
    @NetworkField(index = 6)
    int bioProgress = 0;
    @NetworkField(index = 7)
    int maxBioProgress = 4000;
    @NetworkField(index = 8)
    int maxFertProgres = 500;

    int storedHeat = 0;
    Map.Entry<String, FermentationProperty> lastRecipe = null;
    boolean shouldCheckRecipe = true;

    boolean checkHeatSource = true;
    private static int outputSlot = 0;
    private static int inputTankInSlot = 1;
    private static int inputTankOutSlot = 2;
    private static int outputTankInSlot = 3;
    private static int outputTankOutSlot = 4;
    public TileEntityFermenter() {
        super(7);
        this.addNetworkFields("inputTank", "outputTank", "fertProgress", "bioProgress", "maxBioProgress", "maxFertProgress");
        this.addGuiFields("inputTank", "outputTank", "fertProgress", "bioProgress", "maxBioProgress", "maxFertProgress");
        this.inputTank.addListener(this);
        this.outputTank.addListener(this);
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        IFilter filter = new ContainerFermenter.FluidItemFilter();
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Import, inputTankInSlot, outputTankInSlot);
        handler.registerDefaultSlotAccess(AccessRule.Export, outputSlot, inputTankOutSlot, outputTankOutSlot);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), outputSlot, inputTankOutSlot, outputTankOutSlot);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), inputTankInSlot, outputTankInSlot);
        handler.registerInputFilter(filter, inputTankInSlot, outputTankInSlot);
        handler.registerSlotType(SlotType.Input, inputTankInSlot, outputTankInSlot);
        handler.registerSlotType(SlotType.Output, outputSlot, inputTankOutSlot, outputTankOutSlot);
    }

    public IC2Tank getInputTank() {
        return inputTank;
    }

    public IC2Tank getOutputTank() {
        return outputTank;
    }

    public int getBioProgress() {
        return bioProgress;
    }

    public int getFertProgress() {
        return fertProgress;
    }

    public int getMaxBioProgress() {
        return maxBioProgress;
    }

    public int getMaxFertProgres() {
        return maxFertProgres;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public IHasInventory getOutputInventory() {
        return new RangedInventoryWrapper(this, outputSlot, outputTankOutSlot, inputTankOutSlot);
    }

    @Override
    public IHasInventory getInputInventory() {
        return null;
    }

    @Override
    public double getEnergy() {
        return 0;
    }

    @Override
    public boolean useEnergy(double v, boolean b) {
        return false;
    }

    @Override
    public void setRedstoneSensitive(boolean b) {

    }

    @Override
    public boolean isRedstoneSensitive() {
        return false;
    }

    @Override
    public boolean isProcessing() {
        return this.getActive();
    }

    @Override
    public boolean isValidInput(ItemStack itemStack) {
        return false;
    }

    @Override
    public Set<IMachineUpgradeItem.UpgradeType> getSupportedTypes() {
        return Collections.singleton(IMachineUpgradeItem.UpgradeType.ImportExport);
    }

    @Override
    public World getMachineWorld() {
        return this.getWorld();
    }

    @Override
    public BlockPos getMachinePos() {
        return this.getPos();
    }

    @Override
    public void update() {
        if (this.fertProgress >= maxFertProgres){
            if (this.getStackInSlot(outputSlot).isEmpty()){
                this.setStackInSlot(outputSlot, Ic2Items.fertilizer.copy());
                this.fertProgress = 0;
                this.getNetwork().updateTileGuiField(this, "fertProgress");
            } else if (this.getStackInSlot(outputSlot).getCount() < 64){
                this.getStackInSlot(outputSlot).grow(1);
                this.fertProgress = 0;
                this.getNetwork().updateTileGuiField(this, "fertProgress");
            }
            doUpgradeStuff();
            shouldCheckRecipe = true;
        }
        if (shouldCheckRecipe){
            lastRecipe = getRecipe();
            shouldCheckRecipe = false;
        }
        if (this.storedHeat < 0){
            storedHeat = 0;
        }
        StackHelper.doFluidContainerThings(this, this.inputTank, inputTankInSlot, inputTankOutSlot);
        StackHelper.doFluidContainerThings(this, this.outputTank, outputTankInSlot, outputTankOutSlot);
        TileEntity offset = getWorld().getTileEntity(getPos().offset(this.getFacing()));
        if (lastRecipe != null && offset instanceof IHeatSource && this.getStackInSlot(outputSlot).getCount() < 64){
            IHeatSource heatSource = (IHeatSource) offset;
            int simHeatReceived = heatSource.drawHeat(this.getFacing().getOpposite(), 100, false);
            if (simHeatReceived > 0 || this.storedHeat > 0){
                this.storedHeat += simHeatReceived;
                if (this.bioProgress < maxBioProgress){
                    int room = maxBioProgress - bioProgress;
                    int progress = Math.min(room, storedHeat);
                    this.bioProgress += progress;
                    storedHeat -= progress;
                    this.getNetwork().updateTileGuiField(this, "bioProgress");
                } else {
                    this.bioProgress = 0;
                    this.inputTank.drain(lastRecipe.getValue().inputAmount, true);
                    this.outputTank.fill(FluidRegistry.getFluidStack(lastRecipe.getValue().output, lastRecipe.getValue().outputAmount), true);
                    shouldCheckRecipe = true;
                    if (lastRecipe.getKey().equals("biomass")){
                        fertProgress += 20;
                        this.getNetwork().updateTileGuiField(this, "fertProgress");
                    }
                    this.getNetwork().updateTileGuiField(this, "bioProgress");
                    doUpgradeStuff();
                }
                if (!this.isActive){
                    this.setActive(true);
                }
            } else {
                if (this.isActive){
                    this.setActive(false);
                }
                doUpgradeStuff();
            }
        } else {
            if (this.isActive){
                this.setActive(false);
            }
            doUpgradeStuff();
        }
    }

    private Map.Entry<String, FermentationProperty> getRecipe(){
        FluidStack input = this.inputTank.getFluid();
        if (input == null){
            return null;
        }
        String fluidName = input.getFluid().getName();
        FermentationProperty property = Recipes.fermenter.getFermentationInformation(input.getFluid());
        if (property == null){
            return null;
        }
        if (lastRecipe != null){
            if (!lastRecipe.getKey().equals(fluidName) || input.amount < property.inputAmount){
                lastRecipe = null;
                maxBioProgress = 4000;
                bioProgress = 0;
                this.getNetwork().updateTileGuiField(this,"maxBioProgress");
                this.getNetwork().updateTileGuiField(this,"bioProgress");
            }
        }
        if (lastRecipe == null){
            if (Recipes.fermenter.acceptsFluid(input.getFluid()) && input.amount >= property.inputAmount){
                lastRecipe = new AbstractMap.SimpleEntry<String, FermentationProperty>(fluidName, Recipes.fermenter.getRecipeMap().get(fluidName));
                maxBioProgress = lastRecipe.getValue().heat;
                this.getNetwork().updateTileGuiField(this,"maxBioProgress");
            }
        }
        if (lastRecipe == null){
            return null;
        }
        maxBioProgress = lastRecipe.getValue().heat;
        this.getNetwork().updateTileGuiField(this,"maxBioProgress");
        FluidStack output = this.outputTank.getFluid();
        if (output == null){
            return lastRecipe;
        }
        if (output.isFluidEqual(Recipes.fermenter.getOutput(input.getFluid())) && outputTank.getFluidAmount() + property.outputAmount <= outputTank.getCapacity()){
            return lastRecipe;
        }
        return null;
    }

    private void doUpgradeStuff(){
        for(int i = 0; i < 2; ++i) {
            ItemStack item = this.inventory.get(i + this.inventory.size() - 2);
            if (item.getItem() instanceof IMachineUpgradeItem) {
                ((IMachineUpgradeItem)item.getItem()).onTick(item, this);
            }
        }
    }

    @Override
    public void onBlockUpdate(Block block) {
        super.onBlockUpdate(block);
        this.checkHeatSource = true;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        super.setStackInSlot(slot, stack);
        shouldCheckRecipe = true;
    }

    @Override
    public boolean canRemoveBlock(EntityPlayer player) {
        return true;
    }

    @Override
    public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
        return this.getFacing() != facing;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.inputTank.writeToNBT(this.getTag(nbt, "inputTank"));
        this.outputTank.writeToNBT(this.getTag(nbt, "outputTank"));
        nbt.setInteger("bioProgress", bioProgress);
        nbt.setInteger("fertProgress", fertProgress);
        nbt.setInteger("storedHeat", storedHeat);
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.inputTank.readFromNBT(nbt.getCompoundTag("inputTank"));
        this.outputTank.readFromNBT(nbt.getCompoundTag("outputTank"));
        bioProgress = nbt.getInteger("bioProgress");
        fertProgress = nbt.getInteger("fertProgress");
        this.storedHeat = nbt.getInteger("storedHeat");
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
        shouldCheckRecipe = true;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        List<IFluidTankProperties> list = new ArrayList<>();
        list.addAll(Arrays.asList(this.inputTank.getTankProperties()));
        list.addAll(Arrays.asList(this.outputTank.getTankProperties()));
        return list.toArray(new IFluidTankProperties[0]);
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return this.inputTank.fill(resource, doFill);
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return this.outputTank.drain(resource, doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return this.outputTank.drain(maxDrain, doDrain);
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerFermenter(entityPlayer.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer entityPlayer) {
        return GuiComponentContainer.class;
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return !this.isInvalid();
    }

    @Override
    public boolean hasGui(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void onTankChanged(IFluidTank iFluidTank) {
        this.getNetwork().updateTileGuiField(this, "inputTank");
        this.getNetwork().updateTileGuiField(this, "outputTank");
        this.shouldCheckRecipe = true;
    }

    public static void init(){
        Recipes.fermenter.addRecipe("biomass", 20, 4000, "biogas", 400);
    }
}
