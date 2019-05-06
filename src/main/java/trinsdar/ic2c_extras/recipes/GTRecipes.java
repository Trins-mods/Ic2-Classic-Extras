package trinsdar.ic2c_extras.recipes;

import gtclassic.GTItems;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.tile.GTTileShredder;
import gtclassic.tile.GTTileSmelter;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import trinsdar.ic2c_extras.gtintegration.MaterialGen;
import trinsdar.ic2c_extras.util.Registry;

public class GTRecipes {
    public static void init(){
        initReplaceMaceratorRecipes();
        initFurnaceRecipes();
        initOtherMachineRecipes();
        RecipeCrushed.recipesCrushed();
    }

    static IMachineRecipeList macerator = ClassicRecipes.macerator;
    static String crushedOre = "crushedore";
    static String purifiedCrushedOre = "purifiedcrushedore";
    static GTMaterialGen GT;
    static GTMaterial M;
    public static void initReplaceMaceratorRecipes(){
        macerator.removeRecipe(new RecipeInputOreDict("oreBauxite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreBismuthtine"));
        macerator.removeRecipe(new RecipeInputOreDict("oreCassiterite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreChromite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreGalena"));
        macerator.removeRecipe(new RecipeInputOreDict("oreGarnierite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreLimonite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreMagnetite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreMalachite"));
        macerator.removeRecipe(new RecipeInputOreDict("orePyrite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreSheldonite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreSphalerite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreTantalite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreTetrahedrite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreTungstate"));
        macerator.removeRecipe(new RecipeInputOreDict("orePyrolusite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreMolybdenite"));
        macerator.removeRecipe(new RecipeInputOreDict("oreIridium"));



        addMaceratorRecipe("oreBauxite", MaterialGen.getStack(GTMaterial.Bauxite, crushedOre, 4));
        addMaceratorRecipe("oreBismuthtine", MaterialGen.getStack(GTMaterial.Bismuthtine, crushedOre, 2));
        addMaceratorRecipe("oreCassiterite", MaterialGen.getStack(GTMaterial.Cassiterite, crushedOre, 2));
        addMaceratorRecipe("oreChromite", MaterialGen.getStack(GTMaterial.Chromite, crushedOre, 2));
        addMaceratorRecipe("oreGalena", MaterialGen.getStack(GTMaterial.Galena, crushedOre, 2));
        addMaceratorRecipe("oreGarnierite", MaterialGen.getStack(GTMaterial.Garnierite, crushedOre, 2));
        addMaceratorRecipe("oreLimonite", MaterialGen.getStack(GTMaterial.Limonite, crushedOre, 2));
        addMaceratorRecipe("oreMagnetite", MaterialGen.getStack(GTMaterial.Magnetite, crushedOre, 2));
        addMaceratorRecipe("oreMalachite", MaterialGen.getStack(GTMaterial.Malachite, crushedOre, 2));
        addMaceratorRecipe("orePyrite", MaterialGen.getStack(GTMaterial.Pyrite, crushedOre, 2));
        addMaceratorRecipe("oreSheldonite", MaterialGen.getStack(GTMaterial.Sheldonite, crushedOre, 2));
        addMaceratorRecipe("oreSphalerite", MaterialGen.getStack(GTMaterial.Sphalerite, crushedOre, 2));
        addMaceratorRecipe("oreTantalite", MaterialGen.getStack(GTMaterial.Tantalite, crushedOre, 2));
        addMaceratorRecipe("oreTetrahedrite", MaterialGen.getStack(GTMaterial.Tetrahedrite, crushedOre, 2));
        addMaceratorRecipe("oreTungstate", MaterialGen.getStack(GTMaterial.Tungstate, crushedOre, 2));
        addMaceratorRecipe("orePyrolusite", MaterialGen.getStack(GTMaterial.Pyrolusite, crushedOre, 2));
        addMaceratorRecipe("oreMolybdenite", MaterialGen.getStack(GTMaterial.Molybdenite, crushedOre, 2));
        addMaceratorRecipe("oreIridium", MaterialGen.getStack(GTMaterial.Iridium, crushedOre, 2));

    }
    public static void addMaceratorRecipe(String input, ItemStack output){
        TileEntityMacerator.addRecipe(input, 1, output);
    }

    public static void initOtherMachineRecipes(){
        GTTileShredder.addRecipe("oreBauxite", 1, MaterialGen.getStack(M.Bauxite, crushedOre, 4), GT.getDust(M.Alumina, 1));

        GTTileShredder.addRecipe("oreChromite", 1, MaterialGen.getStack(M.Chromite, crushedOre, 2), GT.getSmallDust(M.Chrome, 1), GT.getSmallDust(M.Iron, 1));

        GTTileShredder.addRecipe("oreGalena", 1, MaterialGen.getStack(M.Galena, crushedOre, 2), GT.getSmallDust(M.Silver, 2));

        GTTileShredder.addRecipe("oreGarnierite", 1, MaterialGen.getStack(M.Garnierite, crushedOre, 3), GT.getSmallDust(M.Platinum, 1),
                GT.getSmallDust(M.Copper, 1));

        GTTileShredder.addRecipe("oreLimonite", 1, MaterialGen.getStack(M.Limonite, crushedOre, 2), GT.getSmallDust(M.Iron, 2));

        GTTileShredder.addRecipe("oreMalachite", 1, MaterialGen.getStack(M.Malachite, crushedOre, 2), GT.getDust(M.Calcite, 1), GT.getSmallDust(M.Copper, 2));

        GTTileShredder.addRecipe("orePyrite", 1, MaterialGen.getStack(M.Pyrite, crushedOre, 2),  GT.getSmallDust(M.Iron, 2));

        GTTileShredder.addRecipe("oreSheldonite", 1, MaterialGen.getStack(M.Sheldonite, crushedOre, 2), GT.getDust(M.Nickel, 1), GT.getSmallDust(M.Platinum, 1));

        GTTileShredder.addRecipe("oreSphalerite", 1, MaterialGen.getStack(M.Sphalerite, crushedOre, 2), GT.getDust(M.Zinc, 1), GT.getSmallDust(M.GarnetYellow, 1));

        GTTileShredder.addRecipe("oreTantalite", 1, MaterialGen.getStack(M.Tantalite, crushedOre, 2), GT.getSmallDust(M.Niobium, 2), GT.getSmallDust(M.Tantalum, 1));

        GTTileShredder.addRecipe("oreTetrahedrite", 1, MaterialGen.getStack(M.Tetrahedrite, crushedOre, 2), GT.getSmallDust(M.Copper, 2), GT.getSmallDust(M.Zinc, 1));

        GTTileShredder.addRecipe("oreTungstate", 1, MaterialGen.getStack(M.Tungstate, crushedOre, 2), GT.getSmallDust(M.Iron, 3), GT.getSmallDust(M.Manganese, 3));

        GTTileShredder.addRecipe("orePyrolusite", 1, MaterialGen.getStack(M.Pyrolusite, crushedOre, 2), GT.getSmallDust(M.Manganese, 2));

        GTTileShredder.addRecipe("oreMolybdenite", 1, MaterialGen.getStack(M.Molybdenite, crushedOre, 2), GT.getSmallDust(M.Molybdenum, 2));

        GTTileShredder.addRecipe("oreIridium", 1, MaterialGen.getStack(M.Iridium, crushedOre, 2), GT.getSmallDust(M.Platinum, 2));

        //and some vanilla ic2 ores

        GTTileShredder.addRecipe("oreCopper", 1, new ItemStack(Registry.copperCrushedOre, 2), GT.getSmallDust(M.Gold, 1),
                GT.getSmallDust(M.Nickel, 1));

        GTTileShredder.addRecipe("oreUranium", 1, new ItemStack(Registry.uraniumCrushedOre, 2), new ItemStack(Registry.uranium238SmallDust, 2), new ItemStack(Registry.uranium235SmallDust));

        GTTileShredder.addRecipe("oreIron", 1, new ItemStack(Registry.ironCrushedOre, 2), GT.getSmallDust(M.Iron, 1),
                GT.getSmallDust(M.Nickel, 1));

        GTTileShredder.addRecipe("oreGold", 1, new ItemStack(Registry.goldCrushedOre, 2), GT.getSmallDust(M.Copper, 1),
                GT.getSmallDust(M.Nickel, 1));

        GTTileShredder.addRecipe("oreTin", 1, new ItemStack(Registry.tinCrushedOre, 2), GT.getSmallDust(M.Iron, 1),
                GT.getSmallDust(M.Zinc, 1));

        GTTileShredder.addRecipe("oreSilver", 1, new ItemStack(Registry.silverCrushedOre, 2), GT.getSmallDust(M.Lead, 2));

        GTTileSmelter.addRecipe("dustTinyIron", 9, new ItemStack(GTItems.moldIngot), new ItemStack(Items.IRON_INGOT));
        GTTileSmelter.addRecipe("dustTinyGold", 9, new ItemStack(GTItems.moldIngot), new ItemStack(Items.GOLD_INGOT));
        GTTileSmelter.addRecipe("dustTinyCopper", 9, new ItemStack(GTItems.moldIngot),Ic2Items.copperIngot);
        GTTileSmelter.addRecipe("dustTinyTin", 9, new ItemStack(GTItems.moldIngot), Ic2Items.tinIngot);
        GTTileSmelter.addRecipe("dustTinySilver", 9, new ItemStack(GTItems.moldIngot), Ic2Items.silverIngot);
        GTTileSmelter.addRecipe("dustTinyLead", 9, new ItemStack(GTItems.moldIngot), GT.getIngot(M.Lead, 1));
        GTTileSmelter.addRecipe("dustTinyBronze", 9, new ItemStack(GTItems.moldIngot), Ic2Items.bronzeIngot);
        //Ic2cExtrasRecipes.extruding.addRecipe(new RecipeInputOreDict("plateTantalum", 1), new ItemStack(GTItems.foilTantalum, 2), "Tantalum Foil");
    }

    public static void initFurnaceRecipes(){
        GameRegistry.addSmelting(MaterialGen.getStack(M.Bismuthtine, crushedOre, 1), GT.getNugget(M.Bismuth, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Cassiterite, crushedOre, 1), GT.getNugget(M.Tin, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Galena, crushedOre, 1), GT.getNugget(M.Lead, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Garnierite, crushedOre, 1), GT.getNugget(M.Nickel, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Limonite, crushedOre, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Magnetite, crushedOre, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Malachite, crushedOre, 1), GT.getNugget(M.Copper, 1), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Pyrite, crushedOre, 1), GT.get(Items.IRON_NUGGET, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Sphalerite, crushedOre, 1), GT.getNugget(M.Zinc, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Tetrahedrite, crushedOre, 1), GT.getNugget(M.Copper, 3), 0.1F);

        GameRegistry.addSmelting(MaterialGen.getStack(M.Bismuthtine, purifiedCrushedOre, 1), GT.getNugget(M.Bismuth, 3), 0.1F);
        GameRegistry.addSmelting(MaterialGen.getStack(M.Cassiterite, purifiedCrushedOre, 1), GT.getNugget(M.Tin, 3), 0.1F);
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
