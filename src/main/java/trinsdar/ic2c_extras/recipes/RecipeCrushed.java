package trinsdar.ic2c_extras.recipes;

import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
import gtclassic.recipe.GTRecipeCauldron;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
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

    /*
     * Yes I know you're thinking "an enum really!?" but until i decide how to
     * handle these random list I need - some of them will be enums.
     */

    static ItemStack stonedust = new ItemStack(Registry.stoneDust);
    static String purifiedCrushedOre = "purifiedcrushedore";
    public enum RecipeCauldronEnum {
        BAUXITE(GTMaterial.Bauxite, MaterialGen.getStack(GTMaterial.Bauxite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Bauxite, 1), stonedust),
        CHROMITE(GTMaterial.Chromite, MaterialGen.getStack(GTMaterial.Chromite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Chromite, 1), stonedust),
        CRYOLITE(GTMaterial.Cryolite, MaterialGen.getStack(GTMaterial.Cryolite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Cryolite, 1), stonedust),
        GALENA(GTMaterial.Galena, MaterialGen.getStack(GTMaterial.Galena, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Galena, 1), stonedust),
        GARNIERITE(GTMaterial.Garnierite, MaterialGen.getStack(GTMaterial.Garnierite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Garnierite, 1), stonedust),
        LIMONITE(GTMaterial.Limonite, MaterialGen.getStack(GTMaterial.Limonite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Limonite, 1), stonedust),
        MALACHITE(GTMaterial.Malachite, MaterialGen.getStack(GTMaterial.Malachite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Malachite, 1), stonedust),
        PYRITE(GTMaterial.Pyrite, MaterialGen.getStack(GTMaterial.Pyrite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Pyrite, 1), stonedust),
        SHELDONITE(GTMaterial.Sheldonite, MaterialGen.getStack(GTMaterial.Sheldonite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Sheldonite, 1), stonedust),
        SPHALERITE(GTMaterial.Sphalerite, MaterialGen.getStack(GTMaterial.Sphalerite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Sphalerite, 1), stonedust),
        TANTALITE(GTMaterial.Tantalite, MaterialGen.getStack(GTMaterial.Tantalite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Tantalite, 1), stonedust),
        TETRAHEDRITE(GTMaterial.Tetrahedrite, MaterialGen.getStack(GTMaterial.Tetrahedrite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Tetrahedrite, 1), stonedust),
        TUNGSTATE(GTMaterial.Tungstate, MaterialGen.getStack(GTMaterial.Tungstate, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Tungstate, 1), stonedust),
        PYROLUSITE(GTMaterial.Pyrolusite, MaterialGen.getStack(GTMaterial.Pyrolusite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Pyrolusite, 1), stonedust),
        MOLYBDENITE(GTMaterial.Molybdenite, MaterialGen.getStack(GTMaterial.Molybdenite, purifiedCrushedOre, 1), GT.getSmallDust(GTMaterial.Molybdenite, 1), stonedust);

        GTMaterial input;
        ItemStack[] outputs;

        /**
         * Creates recipes for cauldron washing and JEI
         *
         * @param input   material from GT to be dust input
         * @param outputs an array of materials for small dust output
         */
        RecipeCauldronEnum(GTMaterial input, ItemStack... outputs) {
            this.input = input;
            this.outputs = outputs;
        }

        public GTMaterial getInput() {
            return this.input;
        }

        public ItemStack[] getOutputs() {
            return this.outputs;
        }
    }

    public enum RecipeThermalCentrifugeEnum {
        BAUXITE(GTMaterial.Bauxite, GT.getDust(GTMaterial.Bauxite, 1), GT.getSmallDust(GTMaterial.Bauxite, 1)),
        CHROMITE(GTMaterial.Chromite, GT.getDust(GTMaterial.Chromite, 1), GT.getSmallDust(GTMaterial.Chromite, 1)),
        CRYOLITE(GTMaterial.Cryolite, GT.getDust(GTMaterial.Cryolite, 1), GT.getSmallDust(GTMaterial.Cryolite, 1)),
        GALENA(GTMaterial.Galena, GT.getDust(GTMaterial.Galena, 1), GT.getSmallDust(GTMaterial.Galena, 1)),
        GARNIERITE(GTMaterial.Garnierite, GT.getDust(GTMaterial.Garnierite, 1), GT.getSmallDust(GTMaterial.Garnierite, 1)),
        LIMONITE(GTMaterial.Limonite, GT.getDust(GTMaterial.Limonite, 1), GT.getSmallDust(GTMaterial.Limonite, 1)),
        MALACHITE(GTMaterial.Malachite, GT.getDust(GTMaterial.Malachite, 1), GT.getSmallDust(GTMaterial.Malachite, 1)),
        PYRITE(GTMaterial.Pyrite, GT.getDust(GTMaterial.Pyrite, 1), GT.getSmallDust(GTMaterial.Pyrite, 1)),
        SHELDONITE(GTMaterial.Sheldonite, GT.getDust(GTMaterial.Sheldonite, 1), GT.getSmallDust(GTMaterial.Sheldonite, 1)),
        SPHALERITE(GTMaterial.Sphalerite, GT.getDust(GTMaterial.Sphalerite, 1), GT.getSmallDust(GTMaterial.Sphalerite, 1)),
        TANTALITE(GTMaterial.Tantalite, GT.getDust(GTMaterial.Tantalite, 1), GT.getSmallDust(GTMaterial.Tantalite, 1)),
        TETRAHEDRITE(GTMaterial.Tetrahedrite, GT.getDust(GTMaterial.Tetrahedrite, 1), GT.getSmallDust(GTMaterial.Tetrahedrite, 1)),
        TUNGSTATE(GTMaterial.Tungstate, GT.getDust(GTMaterial.Tungstate, 1), GT.getSmallDust(GTMaterial.Tungstate, 1)),
        PYROLUSITE(GTMaterial.Pyrolusite, GT.getDust(GTMaterial.Pyrolusite, 1), GT.getSmallDust(GTMaterial.Pyrolusite, 1)),
        MOLYBDENITE(GTMaterial.Molybdenite, GT.getDust(GTMaterial.Molybdenite, 1), GT.getSmallDust(GTMaterial.Molybdenite, 1));

        GTMaterial input;
        ItemStack[] outputs;

        /**
         * Creates recipes for cauldron washing and JEI
         *
         * @param input   material from GT to be dust input
         * @param outputs an array of materials for small dust output
         */
        RecipeThermalCentrifugeEnum(GTMaterial input, ItemStack... outputs) {
            this.input = input;
            this.outputs = outputs;
        }

        public GTMaterial getInput() {
            return this.input;
        }

        public ItemStack[] getOutputs() {
            return this.outputs;
        }
    }

    public static void recipesCrushed() {
        for (RecipeCrushed.RecipeCauldronEnum recipes : RecipeCrushed.RecipeCauldronEnum.values()) {
            addFakeCauldronRecipe(recipes.getInput(), recipes.getOutputs());
            addOreWasherRecipe(recipes.getInput(), recipes.getOutputs());
        }
        for (RecipeCrushed.RecipeThermalCentrifugeEnum recipes : RecipeCrushed.RecipeThermalCentrifugeEnum.values()) {
            addThermalCentrifugeRecipe(recipes.getInput(), recipes.getOutputs());
            addThermalCentrifugeRecipe2(recipes.getInput(), recipes.getOutputs());
        }
        addFakeCauldronRecipe(new ItemStack(Registry.ironCrushedOre), new ItemStack(Registry.ironPurifiedCrushedOre), new ItemStack(Registry.ironTinyDust, 2), stonedust);
        addFakeCauldronRecipe(new ItemStack(Registry.goldCrushedOre), new ItemStack(Registry.goldPurifiedCrushedOre), new ItemStack(Registry.goldTinyDust, 2), stonedust);
        addFakeCauldronRecipe(new ItemStack(Registry.copperCrushedOre), new ItemStack(Registry.copperPurifiedCrushedOre), new ItemStack(Registry.copperTinyDust, 2), stonedust);
        addFakeCauldronRecipe(new ItemStack(Registry.tinCrushedOre), new ItemStack(Registry.tinPurifiedCrushedOre), new ItemStack(Registry.tinTinyDust, 2), stonedust);
        addFakeCauldronRecipe(new ItemStack(Registry.silverCrushedOre), new ItemStack(Registry.silverPurifiedCrushedOre), new ItemStack(Registry.silverTinyDust, 2), stonedust);
        addFakeCauldronRecipe(new ItemStack(Registry.leadCrushedOre), new ItemStack(Registry.leadPurifiedCrushedOre), new ItemStack(Registry.leadTinyDust, 2), stonedust);
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
        outputlist.add(stonedust);
        TileEntityThermalCentrifuge.addRecipe(input1, 250, outputlist);
    }
}
