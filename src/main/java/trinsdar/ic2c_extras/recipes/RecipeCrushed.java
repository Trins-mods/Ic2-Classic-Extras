package trinsdar.ic2c_extras.recipes;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.recipe.GTRecipeCauldron;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import net.minecraft.item.ItemStack;
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

    public static void recipesCrushed() {
        addAllRecipes(GTMaterial.Bauxite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Bauxite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Bauxite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Bauxite, 1), GT.getSmallDust(GTMaterial.Bauxite, 1));
        addAllRecipes(GTMaterial.Chromite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Chromite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Chromite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Chromite, 1), GT.getSmallDust(GTMaterial.Chromite, 1));
        addAllRecipes(GTMaterial.Cryolite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Cryolite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Cryolite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Cryolite, 1), GT.getSmallDust(GTMaterial.Cryolite, 1));
        addAllRecipes(GTMaterial.Galena, new ItemStack[]{MaterialGen.getStack(GTMaterial.Galena, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Galena, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Galena, 1), GT.getSmallDust(GTMaterial.Galena, 1));
        addAllRecipes(GTMaterial.Garnierite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Garnierite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Garnierite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Garnierite, 1), GT.getSmallDust(GTMaterial.Garnierite, 1));
        addAllRecipes(GTMaterial.Limonite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Limonite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Limonite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Limonite, 1), GT.getSmallDust(GTMaterial.Limonite, 1));
        addAllRecipes(GTMaterial.Malachite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Malachite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Malachite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Malachite, 1), GT.getSmallDust(GTMaterial.Malachite, 1));
        addAllRecipes(GTMaterial.Pyrite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Pyrite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Pyrite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Pyrite, 1), GT.getSmallDust(GTMaterial.Pyrite, 1));
        addAllRecipes(GTMaterial.Sheldonite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Sheldonite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Sheldonite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Sheldonite, 1), GT.getSmallDust(GTMaterial.Sheldonite, 1));
        addAllRecipes(GTMaterial.Sphalerite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Sphalerite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Sphalerite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Sphalerite, 1), GT.getSmallDust(GTMaterial.Sphalerite, 1));
        addAllRecipes(GTMaterial.Tantalite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Tantalite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Tantalite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Tantalite, 1), GT.getSmallDust(GTMaterial.Tantalite, 1));
        addAllRecipes(GTMaterial.Tetrahedrite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Tetrahedrite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Tetrahedrite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Tetrahedrite, 1), GT.getSmallDust(GTMaterial.Tetrahedrite, 1));
        addAllRecipes(GTMaterial.Tungstate, new ItemStack[]{MaterialGen.getStack(GTMaterial.Tungstate, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Tungstate, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Tungstate, 1), GT.getSmallDust(GTMaterial.Tungstate, 1));
        addAllRecipes(GTMaterial.Pyrolusite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Pyrolusite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Pyrolusite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Pyrolusite, 1), GT.getSmallDust(GTMaterial.Pyrolusite, 1));
        addAllRecipes(GTMaterial.Molybdenite, new ItemStack[]{MaterialGen.getStack(GTMaterial.Molybdenite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Molybdenite, 1), new ItemStack(Registry.stoneDust)}, GT.getDust(GTMaterial.Molybdenite, 1), GT.getSmallDust(GTMaterial.Molybdenite, 1));
        addFakeCauldronRecipe(new ItemStack(Registry.ironCrushedOre), new ItemStack(Registry.ironPurifiedCrushedOre), new ItemStack(Registry.ironTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.goldCrushedOre), new ItemStack(Registry.goldPurifiedCrushedOre), new ItemStack(Registry.goldTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.copperCrushedOre), new ItemStack(Registry.copperPurifiedCrushedOre), new ItemStack(Registry.copperTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.tinCrushedOre), new ItemStack(Registry.tinPurifiedCrushedOre), new ItemStack(Registry.tinTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.silverCrushedOre), new ItemStack(Registry.silverPurifiedCrushedOre), new ItemStack(Registry.silverTinyDust, 2), new ItemStack(Registry.stoneDust));
        addFakeCauldronRecipe(new ItemStack(Registry.leadCrushedOre), new ItemStack(Registry.leadPurifiedCrushedOre), new ItemStack(Registry.leadTinyDust, 2), new ItemStack(Registry.stoneDust));
    }
    
    public static void addAllRecipes(GTMaterial input, ItemStack[] oreWashingOutput, ItemStack... thermalOutput){
        addFakeCauldronRecipe(input, oreWashingOutput);
        addOreWasherRecipe(input, oreWashingOutput);
        addThermalCentrifugeRecipe(input, thermalOutput);
        addThermalCentrifugeRecipe2(input, thermalOutput);
        TileEntityMacerator.addRecipe(new RecipeInputOreDict("crushedPurified" + input.getDisplayName()), GT.getDust(input, 1));
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
        TileEntityThermalCentrifuge.addRecipe(input1, 250, outputlist);
    }
}
