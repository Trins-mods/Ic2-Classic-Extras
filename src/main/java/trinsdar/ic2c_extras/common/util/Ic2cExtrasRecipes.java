package trinsdar.ic2c_extras.common.util;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static ic2.api.classic.recipe.ClassicRecipes.macerator;

public class Ic2cExtrasRecipes {
    public static void init(){
        initShapedRecipes();
        initReplaceRecipes();
        initReplaceMaceratorRecipes();
    }

    static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
    public static void initShapedRecipes(){
        recipes.addRecipe(new ItemStack(RegistryBlock.advancedSteamTurbine, 1),
                new Object[]{" S ", "STS", " S ", 'S', Ic2Items.basicTurbine,'T', Ic2Items.transformerMV});

    }
    public static void initReplaceRecipes(){
    }
    public static void initReplaceMaceratorRecipes(){
        macerator.removeRecipe(new RecipeInputOreDict("oreIron"));
        macerator.addRecipe(new RecipeInputOreDict("oreIron"), new ItemStack(RegistryItem.crushedOres,2, 0), 0.7F, "ironOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreGold"));
        macerator.addRecipe(new RecipeInputOreDict("oreGold"), new ItemStack(RegistryItem.crushedOres,2, 1), 1.0F, "goldOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreCopper"));
        macerator.addRecipe(new RecipeInputOreDict("oreCopper"), new ItemStack(RegistryItem.crushedOres,2, 2), 0.3F, "copperOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreTin"));
        macerator.addRecipe(new RecipeInputOreDict("oreTin"), new ItemStack(RegistryItem.crushedOres,2, 3), 0.4F, "tinOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreSilver"));
        macerator.addRecipe(new RecipeInputOreDict("oreSilver"), new ItemStack(RegistryItem.crushedOres,2, 4), 0.8F, "silverOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreUranium"));
        macerator.addRecipe(new RecipeInputOreDict("oreUranium"), new ItemStack(RegistryItem.crushedOres,2, 5), 1.0F, "uraniumOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreLead"));
        macerator.addRecipe(new RecipeInputOreDict("oreLead"), new ItemStack(RegistryItem.crushedOres,2, 6), 0.8F, "leadOre");
    }
}
