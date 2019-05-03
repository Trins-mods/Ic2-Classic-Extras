package trinsdar.ic2c_extras.blocks.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import trinsdar.ic2c_extras.blocks.container.ContainerFluidCanningMachine;
import trinsdar.ic2c_extras.blocks.container.ContainerMetalBender;
import trinsdar.ic2c_extras.blocks.tileentity.base.TileEntityFluidCannerBase;
import trinsdar.ic2c_extras.util.GuiMachine;
import trinsdar.ic2c_extras.util.recipelists.FluidCanningRecipeList;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TileEntityFluidCanningMachine extends TileEntityFluidCannerBase {
    public static FluidCanningRecipeList fluidCanning = new FluidCanningRecipeList("fluidCanning");

    public TileEntityFluidCanningMachine() {
        super(3, 4, 1, 400, 32);
        slotInput = 0;
        slotOutput = 2;
    }

    @Override
    protected void addSlots(InventoryHandler handler)
    {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, 1);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInput);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput);
        handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), slotOutput);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.getOppositeList(), slotInput);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), 1);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, 1);
        handler.registerSlotType(SlotType.Fuel, 1);
        handler.registerSlotType(SlotType.Input, slotInput);
        handler.registerSlotType(SlotType.Output, slotOutput);
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{slotInput};
    }

    @Override
    public IFilter[] getInputFilters(int[] slots) {
        return new IFilter[0];
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
        return Ic2cExtrasLang.metalBender;
    }

    @Override
    public double getWrenchDropRate() {
        return 1.0D;
    }

    public ResourceLocation getGuiTexture()
    {
        return Ic2cExtrasResourceLocations.fluidCanningMachine;
    }

    public static void addRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount,  ItemStack output)
    {
        addRecipe(input, inputFluid, inputFluidAmount,  new MachineOutput(null, output));
    }

    public static void addRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, Fluid outputFluid, int outputFluidAmount,  ItemStack output)
    {
        addRecipe(input, inputFluid, inputFluidAmount, outputFluid, outputFluidAmount,  new MachineOutput(null, output));
    }

    public static void addRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, MachineOutput output)
    {
        fluidCanning.addRecipe(input, inputFluid, inputFluidAmount,  output, output.getAllOutputs().get(0).getDisplayName());
    }

    public static void addRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, Fluid outputFluid, int outputFluidAmount, MachineOutput output)
    {
        fluidCanning.addRecipe(input, inputFluid, inputFluidAmount,  output, outputFluid, outputFluidAmount, output.getAllOutputs().get(0).getDisplayName());
    }

    public static void addRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, Fluid outputFluid, int outputFluidAmount)
    {
        fluidCanning.addRecipe(input, inputFluid, inputFluidAmount,  outputFluid, outputFluidAmount, outputFluid.getName());
    }
}
