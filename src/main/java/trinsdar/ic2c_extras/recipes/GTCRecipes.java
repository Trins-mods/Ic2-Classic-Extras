package trinsdar.ic2c_extras.recipes;

import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTItems;
import gtclassic.common.tile.GTTileCentrifuge;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.Registry;

public class GTCRecipes {
    public static void init() {
        CraftingRecipes.recipes.addRecipe(new ItemStack(GTItems.heatStorageHelium1), " T ", "THT", " T ", 'T', "casingTin", 'H', GTMaterialGen.getTube(GTMaterial.Helium, 1));
        CraftingRecipes.recipes.addRecipe(new ItemStack(GTItems.heatStorageHelium3), "TTT", "HHH", "TTT", 'T', "casingTin", 'H', GTMaterialGen.get(GTItems.heatStorageHelium1));
        CraftingRecipes.recipes.addRecipe(new ItemStack(GTItems.heatStorageHelium6), "THT", "TCT", "THT", 'T', "casingTin", 'H', GTMaterialGen.get(GTItems.heatStorageHelium3), 'C', Ic2Items.denseCopperPlate);
        TileEntityMacerator.addRecipe("crushedUranium", 1, GTMaterialGen.getDust(GTMaterial.Uranium, 1));
        TileEntityMacerator.addRecipe("crushedPurifiedUranium", 1, GTMaterialGen.getDust(GTMaterial.Uranium, 1));
        TileEntityMacerator.addRecipe("crushedCentrifugedUranium", 1, GTMaterialGen.getDust(GTMaterial.Uranium, 1));
        GTTileCentrifuge.addRecipe("crushedCentrifugedUranium", 1, 0, GTTileCentrifuge.totalEu(6000), GTMaterialGen.getDust(GTMaterial.Uranium, 1), new ItemStack(Registry.uranium238, 2), new ItemStack(Registry.uranium235TinyDust));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(new ItemStack(GTItems.reEnrichedRodThorium))), 1500, 36000, new ItemStack(Registry.uranium233TinyDust, 2));
        GTTileCentrifuge.addRecipe("dustUranium", 22, 0, GTTileCentrifuge.totalEu(250000), GTMaterialGen.get(Registry.uranium238, 16), GTMaterialGen.get(Registry.uranium235, 2), GTMaterialGen.getDust(GTMaterial.Thorium, 4));
    }

    public static void initUranOverride(){
        CraftingRecipes.dustUtil("dustPlutonium", GTMaterialGen.getDust(GTMaterial.Plutonium, 1), "dustTinyPlutonium", new ItemStack(Registry.plutoniumTinyDust), "dustSmallPlutonium", new ItemStack(Registry.plutoniumSmallDust));
        CraftingRecipes.dustUtil("dustThorium", GTMaterialGen.getDust(GTMaterial.Thorium, 1), "dustTinyThorium", new ItemStack(Registry.thoriumTinyDust));
        Ic2cExtrasRecipes.overrideRecipe("gtclassic", "shaped_item.gtclassic.near_depleted_rod_thorium_-1778115925", GTMaterialGen.get(GTItems.nearDepletedRodThorium, 4), " R ", "RIR", " R ", 'R', Ic2cExtrasRecipes.getEmptyRod(), 'I', "ingotThorium");
        Ic2cExtrasRecipes.overrideRecipe("gtclassic", "shaped_item.gtclassic.near_depleted_rod_plutonium_71443403", GTMaterialGen.get(GTItems.nearDepletedRodPlutonium, 4), " R ", "RIR", " R ", 'R', Ic2cExtrasRecipes.getEmptyRod(), 'I', "ingotPlutonium");
    }
}
