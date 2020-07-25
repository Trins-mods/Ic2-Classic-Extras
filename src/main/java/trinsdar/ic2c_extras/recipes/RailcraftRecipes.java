package trinsdar.ic2c_extras.recipes;

import ic2.core.platform.registry.Ic2Items;
import mods.railcraft.api.crafting.Crafters;
import mods.railcraft.common.util.crafting.Ingredients;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import trinsdar.ic2c_extras.util.Registry;

public class RailcraftRecipes {
    private static void removeRockCrusherRecipes(ItemStack output) {
        Crafters.rockCrusher().getRecipes().removeIf(r -> output.isItemEqual(r.getOutputs().get(0).getOutput()));
    }

    private static void addRockCrusherRecipe(Ingredient input, Item output) {
        Crafters.rockCrusher().makeRecipe(input).name("ic2:crushedOre")
                .addOutput(new ItemStack(output, 2))
                .register();
    }

    public static void initRailcraftRecipes() {
        removeRockCrusherRecipes(Ic2Items.ironDust);
        removeRockCrusherRecipes(Ic2Items.goldDust);
        removeRockCrusherRecipes(Ic2Items.copperDust);
        removeRockCrusherRecipes(Ic2Items.tinDust);
        removeRockCrusherRecipes(Ic2Items.silverDust);
        removeRockCrusherRecipes(Ic2Items.uraniumDrop);
        removeRockCrusherRecipes(new ItemStack(Registry.uranium235));
        addRockCrusherRecipe(Ingredients.from(Blocks.IRON_ORE), Registry.ironCrushedOre);
        addRockCrusherRecipe(Ingredients.from(Blocks.GOLD_ORE), Registry.goldCrushedOre);
        addRockCrusherRecipe(Ingredients.from("oreCopper"), Registry.copperCrushedOre);
        addRockCrusherRecipe(Ingredients.from("oreTin"), Registry.tinCrushedOre);
        addRockCrusherRecipe(Ingredients.from("oreSilver"), Registry.silverCrushedOre);
        addRockCrusherRecipe(Ingredients.from("oreLead"), Registry.leadCrushedOre);
        addRockCrusherRecipe(Ingredients.from("oreUranium"), Registry.uraniumCrushedOre);
    }
}
