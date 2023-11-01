package trinsdar.ic2c_extras.tileentity;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.recipe.RecipeModifierHelpers;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.Nullable;
import trinsdar.ic2c_extras.container.ContainerFluidCanningMachine;
import trinsdar.ic2c_extras.tileentity.base.TileEntityFluidCannerBase;
import trinsdar.ic2c_extras.util.FluidMachineOutput;
import trinsdar.ic2c_extras.util.GuiMachine;
import trinsdar.ic2c_extras.util.recipelists.FluidCanningRecipeList;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Optional.Interface(iface = "gtclassic.api.interfaces.IGTDebuggableTile", modid = "gtclassic", striprefs = true)
public class TileEntityFluidCanningMachine extends TileEntityFluidCannerBase implements ITankListener, IClickable, IGTDebuggableTile, IFluidHandler {
    public static FluidCanningRecipeList fluidCanning = new FluidCanningRecipeList("fluidCanning");
    public MachineFilter filter = new MachineFilter(this);

    public TileEntityFluidCanningMachine() {
        super(3, 4, 1, 400, 32);
        slotInput = 0;
        slotOutput = 2;
        this.setFuelSlot(1);
        this.inputTank.addListener(this);
        this.outputTank.addListener(this);
        this.inputTank.setCanFill(true);
        this.outputTank.setCanFill(false);
        this.inputTank.setCanDrain(false);
        this.outputTank.setCanDrain(true);
        this.addGuiFields("inputTank", "outputTank");
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, 1);
        handler.registerDefaultSlotAccess(AccessRule.Import, 0);
        handler.registerDefaultSlotAccess(AccessRule.Export, 2);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), 2);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), 0);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), 1);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, 1);
        handler.registerSlotType(SlotType.Fuel, 1);
        handler.registerSlotType(SlotType.Input, 0);
        handler.registerSlotType(SlotType.Output, 2);
    }

    @Override
    public void onTankChanged(IFluidTank tank) {
        this.getNetwork().updateTileGuiField(this, "inputTank");
        this.getNetwork().updateTileGuiField(this, "outputTank");
        this.shouldCheckRecipe = true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.inputTank.readFromNBT(nbt.getCompoundTag("inputTank"));
        this.outputTank.readFromNBT(nbt.getCompoundTag("outputTank"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.inputTank.writeToNBT(this.getTag(nbt, "inputTank"));
        this.outputTank.writeToNBT(this.getTag(nbt, "outputTank"));
        return nbt;
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{slotInput};
    }

    @Override
    public IFilter[] getInputFilters(int[] slots) {
        return new IFilter[]{filter};
    }

    @Override
    public boolean isRecipeSlot(int slot) {
        return true;
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{slotOutput};
    }

    @Override
    public FluidCanningRecipeList getRecipeList() {
        return fluidCanning;
    }

    @Override
    public Set<IMachineUpgradeItem.UpgradeType> getSupportedTypes() {
        return new LinkedHashSet(Arrays.asList(IMachineUpgradeItem.UpgradeType.values()));
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerFluidCanningMachine(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return GuiMachine.FluidCanningGui.class;
    }


    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.FLUID_CANNING_MACHINE;
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
    public double getWrenchDropRate() {
        return 1.0D;
    }

    public ResourceLocation getGuiTexture() {
        return Ic2cExtrasResourceLocations.FLUID_CANNING_MACHINE;
    }

    public static void addFillingRecipe(IRecipeInput input, FluidStack inputFluid, ItemStack output) {
        addFillingRecipe(input, inputFluid, output, 50);
    }

    public static void addEmptyingRecipe(IRecipeInput input, ItemStack output, FluidStack outputFluid) {
        addEmptyingRecipe(input, output, outputFluid, 50);
    }

    public static void addFillingRecipe(IRecipeInput input, FluidStack inputFluid, ItemStack output, int totalEu) {
        addFillingRecipe(input, inputFluid, output, totalEu(totalEu));
    }

    public static void addEmptyingRecipe(IRecipeInput input, ItemStack output, FluidStack outputFluid, int totalEu) {
        addEmptyingRecipe(input, output, outputFluid, totalEu(totalEu));
    }

    public static IRecipeModifier[] totalEu(int amount) {
        return new IRecipeModifier[]{RecipeModifierHelpers.ModifierType.RECIPE_LENGTH.create((amount) - 400)};
    }

    public static void addFillingRecipe(IRecipeInput input, FluidStack inputFluid, ItemStack output, IRecipeModifier[] modifiers) {
        NBTTagCompound mods = new NBTTagCompound();
        for (IRecipeModifier modifier : modifiers) {
            modifier.apply(mods);
        }
        fluidCanning.addFillingRecipe(input, inputFluid, new MachineOutput(mods, output), "filling_" + output.getTranslationKey() + "_" + inputFluid.getUnlocalizedName());
    }

    public static void addEmptyingRecipe(IRecipeInput input, ItemStack output, FluidStack outputFluid, IRecipeModifier[] modifiers) {
        NBTTagCompound mods = new NBTTagCompound();
        for (IRecipeModifier modifier : modifiers) {
            modifier.apply(mods);
        }
        fluidCanning.addEmptyingRecipe(input, new FluidMachineOutput(mods, outputFluid, output), "emptying_" + output.getTranslationKey() + "_" + outputFluid.getUnlocalizedName());
    }

    public static void addEnrichingRecipe(IRecipeInput input, FluidStack inputFluid, ItemStack output, FluidStack outputFluid, IRecipeModifier[] modifiers) {
        NBTTagCompound mods = new NBTTagCompound();
        for (IRecipeModifier modifier : modifiers) {
            modifier.apply(mods);
        }
        fluidCanning.addEnrichingRecipe(input, inputFluid, new FluidMachineOutput(mods, outputFluid, output), "enriching_" + output.getTranslationKey());
    }

    public static void addEnrichingRecipe(IRecipeInput input, FluidStack inputFluid, FluidStack outputFluid, IRecipeModifier[] modifiers) {
        NBTTagCompound mods = new NBTTagCompound();
        for (IRecipeModifier modifier : modifiers) {
            modifier.apply(mods);
        }
        fluidCanning.addEnrichingRecipe(input, inputFluid, outputFluid, mods, "enriching_" + outputFluid.getFluid().getUnlocalizedName());
    }

    @Override
    public boolean hasRightClick() {
        return true;
    }

    @Override
    public boolean onRightClick(EntityPlayer player, EnumHand hand, EnumFacing enumFacing, Side side) {
        ItemStack playerStack = player.getHeldItem(hand);
        if (!playerStack.isEmpty()) {
            FluidActionResult result = FluidUtil.tryEmptyContainer(playerStack, this.inputTank, this.inputTank.getCapacity() - this.inputTank.getFluidAmount(), player, true);
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
            FluidActionResult result2 = FluidUtil.tryFillContainer(playerStack, this.outputTank, this.outputTank.getCapacity(), player, true);
            if (result2.isSuccess()) {
                playerStack.shrink(1);
                ItemStack resultStack = result2.getResult();
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
        FluidStack fluid = this.inputTank.getFluid();
        map.put("Input Tank: " + (fluid != null ? fluid.amount + "mb of " + fluid.getLocalizedName() : "Empty"), false);
        fluid = this.outputTank.getFluid();
        map.put("Output Tank: " + (fluid != null ? fluid.amount + "mb of " + fluid.getLocalizedName() : "Empty"), false);
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
}
