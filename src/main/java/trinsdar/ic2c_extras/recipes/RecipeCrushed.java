package trinsdar.ic2c_extras.recipes;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.recipe.GTRecipeCauldron;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.gtintegration.MaterialGen;
import trinsdar.ic2c_extras.util.Registry;

import java.util.ArrayList;
import java.util.List;

public class RecipeCrushed {

    static GTMaterialGen GT;
    static GTMaterial M;

    static String purifiedCrushedOre = "purifiedcrushedore";
    static String tinyDust = "tinydust";

    public static void recipesCrushed() {
        addAllRecipes(GTMaterial.Bauxite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Bauxite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Bauxite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Bauxite, 1), MaterialGen.getStack(GTMaterial.Alumina, tinyDust, 2));
        addAllRecipes(GTMaterial.Bismuthtine, new ItemStack[]{MaterialGen.getStack(GTMaterial.Bismuthtine, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Bismuthtine, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Bismuthtine, 1), MaterialGen.getStack(GTMaterial.Bismuth, tinyDust, 2));
        addAllRecipes(GTMaterial.Cassiterite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Cassiterite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Cassiterite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Cassiterite, 1), new ItemStack(Registry.tinTinyDust, 2));
        addAllRecipes(GTMaterial.Chromite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Chromite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Chromite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Chromite, 1), new ItemStack(Registry.ironTinyDust, 2));
        addAllRecipes(GTMaterial.Galena, new ItemStack[]{MaterialGen.getStack(GTMaterial.Galena, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Galena, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Galena, 1), new ItemStack(Registry.silverTinyDust, 1));
        addAllRecipes(GTMaterial.Garnierite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Garnierite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Garnierite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Garnierite, 1), MaterialGen.getStack(GTMaterial.Platinum, tinyDust, 2));
        addAllRecipes(GTMaterial.Limonite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Limonite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Limonite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Limonite, 1), MaterialGen.getStack(GTMaterial.Nickel, tinyDust, 2));
        addAllRecipes(GTMaterial.Magnetite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Magnetite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Magnetite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Magnetite, 1), new ItemStack(Registry.ironTinyDust, 2));
        addAllRecipes(GTMaterial.Malachite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Malachite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Malachite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Malachite, 1), new ItemStack(Registry.copperTinyDust, 2));
        addAllRecipes(GTMaterial.Pyrite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Pyrite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Pyrite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Pyrite, 1), new ItemStack(Registry.ironTinyDust, 2));
        addAllRecipes(GTMaterial.Sheldonite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Sheldonite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Sheldonite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Sheldonite, 1), MaterialGen.getStack(GTMaterial.Nickel, tinyDust, 1));
        addAllRecipes(GTMaterial.Sphalerite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Sphalerite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Sphalerite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Sphalerite, 1), MaterialGen.getStack(GTMaterial.Zinc, tinyDust, 2));
        addAllRecipes(GTMaterial.Tantalite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Tantalite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Tantalite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Tantalite, 1), MaterialGen.getStack(GTMaterial.Tantalum, tinyDust, 2));
        addAllRecipes(GTMaterial.Tetrahedrite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Tetrahedrite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Tetrahedrite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Tetrahedrite, 1), new ItemStack(Registry.copperTinyDust, 2));
        addAllRecipes(GTMaterial.Tungstate, new ItemStack[]{MaterialGen.getStack(GTMaterial.Tungstate, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Tungstate, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Tungstate, 1), MaterialGen.getStack(GTMaterial.Manganese, tinyDust, 2));
        addAllRecipes(GTMaterial.Pyrolusite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Pyrolusite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Pyrolusite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Pyrolusite, 1), MaterialGen.getStack(GTMaterial.Manganese, tinyDust, 2));
        addAllRecipes(GTMaterial.Molybdenite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Molybdenite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Molybdenite, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Molybdenite, 1), MaterialGen.getStack(GTMaterial.Molybdenum, tinyDust, 2));
        addAllRecipes(GTMaterial.Iridium, new ItemStack[]{MaterialGen.getStack(GTMaterial.Iridium, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Iridium, tinyDust, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Iridium, 1), MaterialGen.getStack(GTMaterial.Osmium, tinyDust, 2));
        tinyDustUtil(GTMaterial.Alumina);
        tinyDustUtil(GTMaterial.Platinum);
        tinyDustUtil(GTMaterial.Nickel);
        tinyDustUtil(GTMaterial.Zinc);
        tinyDustUtil(GTMaterial.Tantalum);
        tinyDustUtil(GTMaterial.Manganese);
        tinyDustUtil(GTMaterial.Molybdenum);
        tinyDustUtil(GTMaterial.Osmium);
        tinyDustUtil(GTMaterial.Bismuth);
        addFakeCauldronRecipe(new ItemStack(Registry.ironCrushedOre), new ItemStack(Registry.ironPurifiedCrushedOre), new ItemStack(Registry.ironTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.goldCrushedOre), new ItemStack(Registry.goldPurifiedCrushedOre), new ItemStack(Registry.goldTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.copperCrushedOre), new ItemStack(Registry.copperPurifiedCrushedOre), new ItemStack(Registry.copperTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.tinCrushedOre), new ItemStack(Registry.tinPurifiedCrushedOre), new ItemStack(Registry.tinTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.silverCrushedOre), new ItemStack(Registry.silverPurifiedCrushedOre), new ItemStack(Registry.silverTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.leadCrushedOre), new ItemStack(Registry.leadPurifiedCrushedOre), new ItemStack(Registry.leadTinyDust, 2), new ItemStack(Registry.stoneDust));
    }
    
    public static void addAllRecipes(GTMaterial input, ItemStack[] oreWashingOutput, ItemStack... thermalOutput){
        OreDictionary.registerOre("crushedPurified" + input.getDisplayName(), MaterialGen.getStack(input, "purifiedcrushedore", 1));
        OreDictionary.registerOre("crushed" + input.getDisplayName(), MaterialGen.getStack(input, "crushedore", 1));
        tinyDustUtil(input);
        addFakeCauldronRecipe(input, oreWashingOutput);
        addOreWasherRecipe(input, oreWashingOutput);
        addThermalCentrifugeRecipe(input, thermalOutput);
        addThermalCentrifugeRecipe2(input, thermalOutput);
        TileEntityMacerator.addRecipe(new RecipeInputOreDict("crushedPurified" + input.getDisplayName()), GT.getDust(input, 1));
        TileEntityMacerator.addRecipe(new RecipeInputOreDict("crushed" + input.getDisplayName()), GT.getDust(input, 1));
    }

    public static void tinyDustUtil(GTMaterial material){
        String tinyDust = "dustTiny" + material.getDisplayName();
        OreDictionary.registerOre(tinyDust, MaterialGen.getStack(material, "tinydust", 1));
        TileEntityCompressor.addRecipe(tinyDust, 9, GT.getDust(material, 1));
        ClassicRecipes.advCrafting.addRecipe(GT.getDust(material, 1), "ttt", "ttt", "ttt", 't', tinyDust);
        ClassicRecipes.advCrafting.addShapelessRecipe(MaterialGen.getStack(material, "tinydust", 9), "dust" + material.getDisplayName());
    }

    public static void addFakeCauldronRecipe(GTMaterial input, ItemStack... outputs) {
        List<IRecipeInput> inputlist = new ArrayList<>();
        List<ItemStack> outputlist = new ArrayList<>();
        inputlist.add((IRecipeInput) (new RecipeInputItemStack(MaterialGen.getStack(input, "crushedore", 1))));
        for (ItemStack stack : outputs) {
            outputlist.add(stack);
        }
        GTRecipeCauldron.addFakeCauldronRecipe(inputlist, new MachineOutput(null, outputlist));
    }

    public static void addFakeCauldronRecipe(ItemStack input, ItemStack... outputs) {
        List<IRecipeInput> inputlist = new ArrayList<>();
        List<ItemStack> outputlist = new ArrayList<>();
        inputlist.add((IRecipeInput) (new RecipeInputItemStack(input)));
        for (ItemStack stack : outputs) {
            outputlist.add(stack);
        }
        GTRecipeCauldron.addFakeCauldronRecipe(inputlist, new MachineOutput(null, outputlist));
    }

    public static void addOreWasherRecipe(GTMaterial input, ItemStack... outputs) {
        IRecipeInput input1 = new RecipeInputOreDict("crushed" + input.getDisplayName());
        TileEntityOreWashingPlant.addRecipe(input1, 1000, outputs);
    }

    public static void addThermalCentrifugeRecipe(GTMaterial input, ItemStack... outputs) {
        IRecipeInput input1 = new RecipeInputOreDict("crushedPurified" + input.getDisplayName());
        TileEntityThermalCentrifuge.addRecipe(input1, 250, outputs);
    }

    public static void addThermalCentrifugeRecipe2(GTMaterial input, ItemStack... outputs) {
        IRecipeInput input1 = new RecipeInputOreDict("crushed" + input.getDisplayName());
        List<ItemStack> outputlist = new ArrayList<>();
        for (ItemStack stack : outputs) {
            outputlist.add(stack);
        }
        outputlist.add(new ItemStack(Registry.stoneDust));
        TileEntityThermalCentrifuge.addRecipe(input1, 300, outputlist);
    }
}
