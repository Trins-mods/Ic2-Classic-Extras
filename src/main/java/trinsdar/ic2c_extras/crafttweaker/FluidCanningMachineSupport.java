package trinsdar.ic2c_extras.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import trinsdar.ic2c_extras.tileentity.TileEntityFluidCanningMachine;

import java.util.Locale;

@ZenClass("mods.ic2.FluidCanningMachine")
@ZenRegister
public class FluidCanningMachineSupport {
    @ZenMethod
    public static void addFillingRecipe(IItemStack output, IIngredient input, ILiquidStack inputFluid, @Optional(valueLong = 50L) int totalEu) {
        CraftTweakerPlugin.apply(new FluidCannerFillingRecipeAction(CraftTweakerPlugin.of(input), CraftTweakerMC.getLiquidStack(inputFluid), CraftTweakerMC.getItemStack(output), totalEu));
    }

    @ZenMethod
    public static void addEmptyingRecipe(IItemStack output, ILiquidStack outputFluid, IIngredient input, @Optional(valueLong = 50L) int totalEu) {
        CraftTweakerPlugin.apply(new FluidCannerEmptyingRecipeAction(CraftTweakerPlugin.of(input), CraftTweakerMC.getItemStack(output), CraftTweakerMC.getLiquidStack(outputFluid), totalEu));
    }

    @ZenMethod
    public static void addEnrichingRecipe(ILiquidStack outputFluid, IIngredient input, ILiquidStack inputFluid) {
        CraftTweakerPlugin.apply(new FluidCannerEnrichingRecipeAction(CraftTweakerPlugin.of(input), CraftTweakerMC.getLiquidStack(inputFluid), CraftTweakerMC.getLiquidStack(outputFluid)));
    }

    @ZenMethod
    public static void addEnrichingRecipe(IItemStack output, ILiquidStack outputFluid, IIngredient input, ILiquidStack inputFluid) {
        CraftTweakerPlugin.apply(new FluidCannerEnrichingRecipeAction2(CraftTweakerPlugin.of(input), CraftTweakerMC.getLiquidStack(inputFluid), CraftTweakerMC.getItemStack(output), CraftTweakerMC.getLiquidStack(outputFluid)));
    }

    private static final class FluidCannerFillingRecipeAction implements IAction {

        private final IRecipeInput input;
        private final FluidStack inputFluid;
        private final ItemStack output;
        private final int totalEu;

        FluidCannerFillingRecipeAction(IRecipeInput input, FluidStack inputFluid, ItemStack output, int totalEu) {
            this.input = input;
            this.inputFluid = inputFluid;
            this.output = output;
            this.totalEu = totalEu;
        }

        @Override
        public void apply() {
            TileEntityFluidCanningMachine.addFillingRecipe(this.input, this.inputFluid, this.output, this.totalEu);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s, %s -> %s] to %s", this.input, this.inputFluid, this.output, TileEntityFluidCanningMachine.fluidCanning);
        }
    }

    private static final class FluidCannerEmptyingRecipeAction implements IAction {

        private final IRecipeInput input;
        private final FluidStack outputFluid;
        private final ItemStack output;
        private final int totalEu;

        FluidCannerEmptyingRecipeAction(IRecipeInput input, ItemStack output, FluidStack outputFluid, int totalEu) {
            this.input = input;
            this.outputFluid = outputFluid;
            this.output = output;
            this.totalEu = totalEu;
        }

        @Override
        public void apply() {
            TileEntityFluidCanningMachine.addEmptyingRecipe(this.input, this.output, this.outputFluid, this.totalEu);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s -> %s, %s] to %s", this.input, this.output, this.outputFluid, TileEntityFluidCanningMachine.fluidCanning);
        }
    }

    private static final class FluidCannerEnrichingRecipeAction implements IAction {

        private final IRecipeInput input;
        private final FluidStack inputFluid;
        private final FluidStack outputFluid;

        FluidCannerEnrichingRecipeAction(IRecipeInput input, FluidStack inputFluid, FluidStack outputFluid) {
            this.input = input;
            this.outputFluid = outputFluid;
            this.inputFluid = inputFluid;
        }

        @Override
        public void apply() {
            TileEntityFluidCanningMachine.addEnrichingRecipe(this.input, this.inputFluid, this.outputFluid);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s, %s -> %s] to %s", this.input, this.inputFluid, this.outputFluid, TileEntityFluidCanningMachine.fluidCanning);
        }
    }

    private static final class FluidCannerEnrichingRecipeAction2 implements IAction {

        private final IRecipeInput input;
        private final FluidStack inputFluid;
        private final ItemStack output;
        private final FluidStack outputFluid;

        FluidCannerEnrichingRecipeAction2(IRecipeInput input, FluidStack inputFluid, ItemStack output, FluidStack outputFluid) {
            this.input = input;
            this.inputFluid = inputFluid;
            this.outputFluid = outputFluid;
            this.output = output;
        }

        @Override
        public void apply() {
            TileEntityFluidCanningMachine.addEnrichingRecipe(this.input, this.inputFluid, this.output, this.outputFluid);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s, %s -> %s, %s] to %s", this.input, this.inputFluid, this.output, this.outputFluid, TileEntityFluidCanningMachine.fluidCanning);
        }
    }
}
