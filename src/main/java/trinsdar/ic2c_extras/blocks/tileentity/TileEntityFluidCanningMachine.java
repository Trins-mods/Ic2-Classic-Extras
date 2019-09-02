package trinsdar.ic2c_extras.blocks.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.RecipeModifierHelpers;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IStackOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.block.base.util.output.IStackRegistry;
import ic2.core.block.base.util.output.MultiSlotOutput;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.AdvRecipeBase;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.ITankListener;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import trinsdar.ic2c_extras.blocks.container.ContainerFluidCanningMachine;
import trinsdar.ic2c_extras.blocks.container.ContainerMetalBender;
import trinsdar.ic2c_extras.blocks.tileentity.base.TileEntityFluidCannerBase;
import trinsdar.ic2c_extras.util.GuiMachine;
import trinsdar.ic2c_extras.util.recipelists.FluidCanningRecipeList;
import trinsdar.ic2c_extras.util.recipelists.FluidCanningRecipeList.FluidCanningRecipe;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

public class TileEntityFluidCanningMachine extends TileEntityFluidCannerBase implements ITankListener, IClickable {
    public static FluidCanningRecipeList fluidCanning = new FluidCanningRecipeList("fluidCanning");

    public TileEntityFluidCanningMachine() {
        super(3, 4, 1, 400, 32);
        slotInput = 0;
        slotOutput = 2;
        this.inputTank.addListener(this);
        this.outputTank.addListener(this);
        this.inputTank.setCanFill(true);
        this.outputTank.setCanFill(true);
        this.inputTank.setCanDrain(true);
        this.outputTank.setCanDrain(true);
        this.addGuiFields("inputTank", "outputTank");
    }

    @Override
    protected void addSlots(InventoryHandler handler)
    {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, 1);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutput);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInput);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), 1);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, 1);
        handler.registerSlotType(SlotType.Fuel, 1);
        handler.registerSlotType(SlotType.Input, slotInput);
        handler.registerSlotType(SlotType.Output, slotOutput);
    }

    @Override
    public void onTankChanged(IFluidTank tank)
    {
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
        return new IFilter[]{new MachineFilter(this)};
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
    public ContainerIC2 getGuiContainer(EntityPlayer player)
    {
        return new ContainerFluidCanningMachine(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player)
    {
        return GuiMachine.FluidCanningGui.class;
    }


    @Override
    public LocaleComp getBlockName()
    {
        return Ic2cExtrasLang.fluidCanningMachine;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    public EnumFacing left(){
        if (this.getFacing() == EnumFacing.NORTH){
            return EnumFacing.EAST;
        }
        if (this.getFacing() == EnumFacing.WEST){
            return EnumFacing.NORTH;
        }
        if (this.getFacing() == EnumFacing.SOUTH){
            return EnumFacing.WEST;
        }
        if (this.getFacing() == EnumFacing.EAST){
            return EnumFacing.SOUTH;
        }
        return this.getFacing();
    }

    public EnumFacing right(){
        if (this.getFacing() == EnumFacing.NORTH){
            return EnumFacing.WEST;
        }
        if (this.getFacing() == EnumFacing.WEST){
            return EnumFacing.SOUTH;
        }
        if (this.getFacing() == EnumFacing.SOUTH){
            return EnumFacing.EAST;
        }
        if (this.getFacing() == EnumFacing.EAST){
            return EnumFacing.NORTH;
        }
        return this.getFacing();
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
            if (facing == EnumFacing.UP || facing == left()){
                return (T)this.inputTank;
            }else if (facing == EnumFacing.DOWN || facing == right()){
                return (T)this.outputTank;
            }else {
                return super.getCapability(capability, facing);
            }
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public double getWrenchDropRate() {
        return 1.0D;
    }

    public ResourceLocation getGuiTexture()
    {
        return Ic2cExtrasResourceLocations.fluidCanningMachine;
    }

    public static void addFillingRecipe(IRecipeInput input, FluidStack inputFluid,  ItemStack output)
    {
        NBTTagCompound mods = new NBTTagCompound();
        RecipeModifierHelpers.IRecipeModifier[] modifiers = totalEu(50);
        for (RecipeModifierHelpers.IRecipeModifier modifier : modifiers) {
            modifier.apply(mods);
        }
        addFillingRecipe(input, inputFluid,  new MachineOutput(mods, output));
    }

    public static void addEmptyingRecipe(IRecipeInput input, ItemStack output, FluidStack outputFluid)
    {
        NBTTagCompound mods = new NBTTagCompound();
        RecipeModifierHelpers.IRecipeModifier[] modifiers = totalEu(50);
        for (RecipeModifierHelpers.IRecipeModifier modifier : modifiers) {
            modifier.apply(mods);
        }
        addEmptyingRecipe(input,  new MachineOutput(mods, output), outputFluid);
    }

    public static void addEnrichingRecipe(IRecipeInput input, FluidStack inputFluid, ItemStack output, FluidStack outputFluid)
    {
        addEnrichingRecipe(input, inputFluid, new MachineOutput(null, output), outputFluid);
    }

    public static RecipeModifierHelpers.IRecipeModifier[] totalEu(int amount) {
        return new RecipeModifierHelpers.IRecipeModifier[] { RecipeModifierHelpers.ModifierType.RECIPE_LENGTH.create((amount) - 400) };
    }

    public static void addFillingRecipe(IRecipeInput input, FluidStack inputFluid, MachineOutput output)
    {
        fluidCanning.addFillingRecipe(input, inputFluid,  output, AdvRecipeBase.getRecipeID(Arrays.asList(input), Arrays.asList(output), "filling_" + output.getAllOutputs().get(0).getUnlocalizedName()));
    }

    public static void addEmptyingRecipe(IRecipeInput input, MachineOutput output,  FluidStack outputFluid)
    {
        fluidCanning.addEmptyingRecipe(input, output, outputFluid, AdvRecipeBase.getRecipeID(Arrays.asList(input), Arrays.asList(output), "emptying_" + output.getAllOutputs().get(0).getUnlocalizedName()));
    }

    public static void addEnrichingRecipe(IRecipeInput input, FluidStack inputFluid, MachineOutput output, FluidStack outputFluid)
    {
        fluidCanning.addEnrichingRecipe(input, inputFluid, output, outputFluid, AdvRecipeBase.getRecipeID(Arrays.asList(input), Arrays.asList(output), "enriching_" + output.getAllOutputs().get(0).getUnlocalizedName()));
    }

    public static void addEnrichingRecipe(IRecipeInput input, FluidStack inputFluid, FluidStack outputFluid)
    {
        fluidCanning.addEnrichingRecipe(input, inputFluid, outputFluid, AdvRecipeBase.getRecipeID(Arrays.asList(input), Arrays.asList(outputFluid), "enriching_" + outputFluid.getFluid().getUnlocalizedName()));
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
            if (result.isSuccess()){
                playerStack.shrink(1);
                ItemStack resultStack = result.getResult();
                if (!resultStack.isEmpty()) {
                    if (!player.inventory.addItemStackToInventory(resultStack)) {
                        player.dropItem(resultStack, false);
                    }
                }
                return true;
            }
            FluidActionResult result2 = FluidUtil.tryFillContainer(playerStack, this.outputTank, 1000, player, true);
            if (result.isSuccess()){
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
}
