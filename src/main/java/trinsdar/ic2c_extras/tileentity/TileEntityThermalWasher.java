package trinsdar.ic2c_extras.tileentity;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.INullableRecipeInput;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IStackOutput;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityAdvancedMachine;
import ic2.core.block.base.util.output.SimpleStackOutput;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2GuiLang;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.helpers.CompareableStack;
import ic2.core.util.misc.FluidHelper;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
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
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import trinsdar.ic2c_extras.container.ContainerThermalWasher;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.GuiMachine.OreWashingPlantGui;
import trinsdar.ic2c_extras.util.StackHelper;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Optional.Interface(iface = "gtclassic.api.interfaces.IGTDebuggableTile", modid = "gtclassic", striprefs = true)
public class TileEntityThermalWasher extends TileEntityAdvancedMachine implements ITankListener, IFluidHandler, IClickable, IGTDebuggableTile {
    @NetworkField(index = 13)
    public IC2Tank waterTank = new IC2Tank(FluidRegistry.getFluidStack(FluidRegistry.WATER.getName(), 0), 20000);
    public int water = 0;
    public int maxWater = 20000;

    public static final int slotFuel = 0;
    public static final int slotInput = 1;
    public static final int slotOutput = 2;
    public static final int slotOutput2 = 3;
    public static final int slotOutput3 = 4;
    public static final int slotInputTank = 5;
    public static final int slotOutputTank = 6;

    public IFilter filter = new MachineFilter(this);

    public TileEntityThermalWasher() {
        super(7, 16, 4000);
        this.waterTank.addListener(this);
        this.waterTank.setCanFill(true);
        this.addGuiFields("waterTank");
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput, slotOutput2, slotOutput3, slotOutputTank);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutput, slotOutput2, slotOutput3);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInput);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
        handler.registerInputFilter(filter, slotInput);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
        handler.registerSlotType(SlotType.Fuel, slotFuel);
        handler.registerSlotType(SlotType.Input, slotInput, slotInputTank);
        handler.registerSlotType(SlotType.Output, slotOutput, slotOutput2, slotOutput3, slotOutputTank);
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
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return Ic2cExtrasRecipes.oreWashingPlant.getRecipeInAndOutput(input, false);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return OreWashingPlantGui.class;
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{slotOutput, slotOutput2, slotOutput3};
    }

    @Override
    public Slot[] getInventorySlots(InventoryPlayer inventoryPlayer) {
        return new Slot[0];
    }

    @Override
    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.THERMAL_WASHER;
    }

    @Override
    public LocaleComp getSpeedName() {
        return Ic2GuiLang.machineHeat;
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.THERMAL_WASHER;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return Ic2cExtrasRecipes.oreWashingPlant;
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
    public ResourceLocation getProcessSoundFile() {
        return Ic2cExtrasResourceLocations.oreWashingPlantOp;
    }

    @Override
    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerThermalWasher(player.inventory, this);
    }

    @Override
    public void update() {
        if (!this.inventory.get(slotInputTank).isEmpty()) {
            StackHelper.doFluidContainerThings(this, this.waterTank, slotInputTank, slotOutputTank);
        }
        super.update();
    }

    @Override
    public IMachineRecipeList.RecipeEntry getRecipe(int slot) {
        if (this.notCheckRecipe.contains(slot)) {
            return (IMachineRecipeList.RecipeEntry) this.activeRecipes.get(slot);
        } else {
            this.notCheckRecipe.add(slot);
            if (((ItemStack) this.inventory.get(slot)).isEmpty() && !this.canWorkWithoutItems()) {
                this.lastRecipes.remove(slot);
                this.activeRecipes.remove(slot);
                return null;
            } else {
                IMachineRecipeList.RecipeEntry lastRecipe = (IMachineRecipeList.RecipeEntry) this.lastRecipes.get(slot);
                if (lastRecipe != null) {
                    IRecipeInput recipe = lastRecipe.getInput();
                    if (recipe instanceof INullableRecipeInput) {
                        if (!recipe.matches((ItemStack) this.inventory.get(slot))) {
                            this.lastRecipes.remove(slot);
                            this.activeRecipes.remove(slot);
                            lastRecipe = null;
                        }
                    } else if (!((ItemStack) this.inventory.get(slot)).isEmpty() && recipe.matches((ItemStack) this.inventory.get(slot))) {
                        if (recipe.getAmount() > ((ItemStack) this.inventory.get(slot)).getCount()) {
                            this.activeRecipes.remove(slot);
                            return null;
                        }

                        EnumActionResult result = this.isRecipeStillValid(lastRecipe);
                        if (result == EnumActionResult.FAIL) {
                            this.lastRecipes.remove(slot);
                            this.activeRecipes.remove(slot);
                            lastRecipe = null;
                        } else if (result == EnumActionResult.PASS) {
                            this.activeRecipes.remove(slot);
                            return null;
                        }
                    } else {
                        this.lastRecipes.remove(slot);
                        this.activeRecipes.remove(slot);
                        lastRecipe = null;
                    }
                }

                if (lastRecipe == null) {
                    IMachineRecipeList.RecipeEntry out = this.getOutputFor(((ItemStack) this.inventory.get(slot)).copy());
                    if (out == null || isRecipeStillValid(out) == EnumActionResult.PASS) {
                        this.activeRecipes.remove(slot);
                        return null;
                    }

                    lastRecipe = out;
                    this.lastRecipes.put(slot, out);
                    this.activeRecipes.put(slot, out);
                    this.handleModifiers(out);
                }

                EnumActionResult result = this.canFillRecipeIntoOutputs(lastRecipe.getOutput());
                if (result == EnumActionResult.SUCCESS) {
                    this.activeRecipes.put(slot, lastRecipe);
                    return lastRecipe;
                } else if (result == EnumActionResult.PASS) {
                    this.activeRecipes.remove(slot);
                    return null;
                } else if (this.hasEmptyOutput(slot)) {
                    this.activeRecipes.put(slot, lastRecipe);
                    return lastRecipe;
                } else {
                    Iterator var12 = lastRecipe.getOutput().getAllOutputs().iterator();

                    while (var12.hasNext()) {
                        ItemStack output = (ItemStack) var12.next();
                        int[] var6 = this.getOutputSlots();
                        int var7 = var6.length;

                        for (int var8 = 0; var8 < var7; ++var8) {
                            int outputSlot = var6[var8];
                            if (StackUtil.isStackEqual((ItemStack) this.inventory.get(outputSlot), output, false, true) && ((ItemStack) this.inventory.get(outputSlot)).getCount() + output.getCount() <= ((ItemStack) this.inventory.get(outputSlot)).getMaxStackSize()) {
                                this.activeRecipes.put(slot, lastRecipe);
                                return lastRecipe;
                            }
                        }
                    }

                    this.activeRecipes.remove(slot);
                    return null;
                }
            }
        }
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
    public void operateOnce(int slot, IRecipeInput input, MachineOutput output, List<IStackOutput> list) {
        List<ItemStack> result = output.getRecipeOutput(getWorld().rand, getTileData());
        for (int i = 0; i < result.size(); i++) {
            list.add(new SimpleStackOutput(result.get(i), slotOutput + (i % 3)));
        }
        consumeInput(input, slotInput, output.getMetadata());
        this.waterTank.drain(getRequiredWater(output), true);
    }

    @Override
    public IHasInventory getOutputInventory() {
        return new RangedInventoryWrapper(this, slotOutput, slotOutput2, slotOutput3, slotOutputTank);
    }

    @Override
    public IHasInventory getInputInventory() {
        return new RangedInventoryWrapper(this, slotInput).setFilters(filter);
    }

    public FluidStack getFluid() {
        return this.waterTank.getFluid();
    }

    @Override
    public void onTankChanged(IFluidTank tank) {
        this.getNetwork().updateTileGuiField(this, "waterTank");
        this.notCheckRecipe.clear();
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
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T) this.waterTank : super.getCapability(capability, facing);
    }

    @Override
    protected EnumActionResult isRecipeStillValid(IMachineRecipeList.RecipeEntry entry) {
        if (waterTank.getFluidAmount() >= getRequiredWater(entry.getOutput())) {
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    public static int getRequiredWater(MachineOutput output) {
        if (output == null || output.getMetadata() == null) {
            return 0;
        }
        return output.getMetadata().getInteger(TileEntityOreWashingPlant.waterAmount);
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
