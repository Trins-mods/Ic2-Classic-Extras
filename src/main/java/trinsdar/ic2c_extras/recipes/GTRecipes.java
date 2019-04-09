package trinsdar.ic2c_extras.recipes;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import trinsdar.ic2c_extras.gtintegration.MaterialGen;

public class GTRecipes {
    public static void init(){
        initReplaceMaceratorRecipes();
        initFurnaceRecipes();
        RecipeCrushed.recipesCrushed();
    }

    static IMachineRecipeList macerator = ClassicRecipes.macerator;
    static String crushedOre = "crushedore";
    static String purifiedCrushedOre = "purifiedcrushedore";
    static GTMaterialGen GT;
    static GTMaterial M;
    public static void initReplaceMaceratorRecipes(){
        macerator.removeRecipe(new RecipeInputOreDict("oreBauxite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreChromite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreCryolite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreGalena"));
        macerator.removeRecipe(new RecipeInputOreDict("oreGarnierite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreLimonite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreMalachite"));
        macerator.removeRecipe(new RecipeInputOreDict("orePyrite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreSheldonite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreSphalerite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreTantalite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreTetrahedrite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreTungstate"));
        macerator.removeRecipe(new RecipeInputOreDict("orePyrolusite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreMolybdenite"));



        addMaceratorRecipe("oreBauxite", MaterialGen.getStack(GTMaterial.Bauxite, crushedOre, 4));
        addMaceratorRecipe("oreChromite", MaterialGen.getStack(GTMaterial.Chromite, crushedOre, 2));
        addMaceratorRecipe("oreCryolite", MaterialGen.getStack(GTMaterial.Cryolite, crushedOre, 2));
        addMaceratorRecipe("oreGalena", MaterialGen.getStack(GTMaterial.Galena, crushedOre, 2));
        addMaceratorRecipe("oreGarnierite", MaterialGen.getStack(GTMaterial.Garnierite, crushedOre, 2));
        addMaceratorRecipe("oreLimonite", MaterialGen.getStack(GTMaterial.Limonite, crushedOre, 2));
        addMaceratorRecipe("oreMalachite", MaterialGen.getStack(GTMaterial.Malachite, crushedOre, 2));
        addMaceratorRecipe("orePyrite", MaterialGen.getStack(GTMaterial.Pyrite, crushedOre, 5));
        addMaceratorRecipe("oreSheldonite", MaterialGen.getStack(GTMaterial.Sheldonite, crushedOre, 2));
        addMaceratorRecipe("oreSphalerite", MaterialGen.getStack(GTMaterial.Sphalerite, crushedOre, 5));
        addMaceratorRecipe("oreTantalite", MaterialGen.getStack(GTMaterial.Tantalite, crushedOre, 2));
        addMaceratorRecipe("oreTetrahedrite", MaterialGen.getStack(GTMaterial.Tetrahedrite, crushedOre, 2));
        addMaceratorRecipe("oreTungstate", MaterialGen.getStack(GTMaterial.Tungstate, crushedOre, 2));
        addMaceratorRecipe("orePyrolusite", MaterialGen.getStack(GTMaterial.Pyrolusite, crushedOre, 2));
        addMaceratorRecipe("oreMolybdenite", MaterialGen.getStack(GTMaterial.Molybdenite, crushedOre, 2));
    }
    public static void addMaceratorRecipe(String input, ItemStack output){
        TileEntityMacerator.addRecipe(input, 1, output);
    }

    public static void initFurnaceRecipes(){
        GameRegistry.addSmelting(MaterialGen.getStack(M.Galena, crushedOre, 1), GT.getNugget(M.Lead, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Garnierite, crushedOre, 1), GT.getNugget(M.Nickel, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Limonite, crushedOre, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Magnetite, crushedOre, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Malachite, crushedOre, 1), GT.getNugget(M.Copper, 1), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Pyrite, crushedOre, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Sphalerite, crushedOre, 1), GT.getNugget(M.Zinc, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Tetrahedrite, crushedOre, 1), GT.getNugget(M.Copper, 3), 0.1F);

        GameRegistry.addSmelting(MaterialGen.getStack(M.Galena, purifiedCrushedOre, 1), GT.getNugget(M.Lead, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Garnierite, purifiedCrushedOre, 1), GT.getNugget(M.Nickel, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Limonite, purifiedCrushedOre, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Magnetite, purifiedCrushedOre, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Malachite, purifiedCrushedOre, 1), GT.getNugget(M.Copper, 1), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Pyrite, purifiedCrushedOre, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Sphalerite, purifiedCrushedOre, 1), GT.getNugget(M.Zinc, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Tetrahedrite, purifiedCrushedOre, 1), GT.getNugget(M.Copper, 3), 0.1F);
    }
}
