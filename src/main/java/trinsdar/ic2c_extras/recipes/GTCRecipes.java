package trinsdar.ic2c_extras.recipes;

import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileCentrifuge;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.util.Registry;

public class GTCRecipes {
    public static void init(){
        CraftingRecipes.recipes.addShapelessRecipe(GTMaterialGen.getDust(GTMaterial.Thorium, 1), "dustThorium232");
        CraftingRecipes.recipes.addShapelessRecipe(new ItemStack(Registry.thorium232Dust, 1), "dustThorium");
        if (Ic2cExtrasRecipes.enableEmptyRods){
            GTTileCentrifuge.removeRecipe("item.itemCellEmpty_1");
            GTTileCentrifuge.addRecipe(Ic2Items.reactorNearDepletedUraniumRod, 0, GTTileCentrifuge.totalEu(2500), new ItemStack(Registry.emptyFuelRod), GTMaterialGen.getDust(GTMaterial.Thorium, 1));
            //CraftingRecipes.recipes.overrideShapelessRecipe("shapeless_item.gtclassic.singlethorium_rod_-1442102634", new ItemStack(GTItems.rodThorium1), Registry.emptyFuelRod, GTMaterialGen.getIngot(GTMaterial.Thorium, 1));
            //CraftingRecipes.recipes.overrideShapelessRecipe("shapeless_item.gtclassic.singleplutonium_rod_-710027402", new ItemStack(GTItems.rodPlutonium1), Registry.emptyFuelRod, GTMaterialGen.getIngot(GTMaterial.Plutonium, 1));
        }
    }
}
