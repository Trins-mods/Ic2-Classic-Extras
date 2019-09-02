package trinsdar.ic2c_extras.util.crafttweaker;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import trinsdar.ic2c_extras.tileentity.TileEntityMetalBender;

import java.util.Locale;

@ZenClass("mods.ic2.MetalBender")
@ZenRegister
public class MetalBenderSupport {
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, IItemStack press) {
        CraftTweakerPlugin.apply(new MetalBenderRecipeAction(CraftTweakerPlugin.of(input), CraftTweakerMC.getItemStack(press), CraftTweakerMC.getItemStack(output)));
    }

    private static final class MetalBenderRecipeAction implements IAction {

        private final IRecipeInput input;
        private final ItemStack press;
        private final ItemStack output;

        MetalBenderRecipeAction(IRecipeInput input, ItemStack press, ItemStack output) {
            this.input = input;
            this.press = press;
            this.output = output;
        }

        @Override
        public void apply() {
            TileEntityMetalBender.addRecipe(this.input, this.press, this.output);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Recipe[%s, %s -> %s] to %s", this.input, this.press, this.output, TileEntityMetalBender.metalBender);
        }
    }
}
