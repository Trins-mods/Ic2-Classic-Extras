package trinsdar.ic2c_extras.recipes;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import trinsdar.ic2c_extras.util.Registry;

public class CraftingRecipes {
    static IRecipeInput casing = new RecipeInputCombined(1,
            new RecipeInputOreDict("casingRefinedIron"),
            new RecipeInputOreDict("casingBronze"), new RecipeInputOreDict("casingSteel"));

    static IRecipeInput crushedCopper = new RecipeInputCombined(1,
            new RecipeInputOreDict("crushedCopper"),
            new RecipeInputOreDict("crushedPurifiedCopper"));

    static IRecipeInput crushedTin = new RecipeInputCombined(1,
            new RecipeInputOreDict("crushedTin"),
            new RecipeInputOreDict("crushedPurifiedTin"));

    private static String basicCircuit = "circuitBasic";

    static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
    public static void init(){
        initShapedRecipes();
        initShapelessRecipes();
        initReplaceRecipes();
        initCompressRecipes();
    }

    public static void initShapedRecipes(){
        FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
        recipes.addRecipe(new ItemStack(Registry.advancedSteamTurbine, 1),
                " S ", "STS", " S ", 'S', Ic2Items.basicTurbine,'T', Ic2Items.transformerMV);
        recipes.addRecipe(new ItemStack(Registry.oreWashingPlant, 1),
                "III", "BCB", "McM", 'I', IC2.getRefinedIron(),'B', Items.BUCKET, 'C', Ic2Items.machine, 'M', Ic2Items.carbonMesh, 'c', basicCircuit);
        recipes.addRecipe(new ItemStack(Registry.thermalWasher, 1),
                "BBB", "BOB", "BAB", 'B', Items.BUCKET, 'O', Registry.oreWashingPlant, 'A', Ic2Items.advMachine);
        recipes.addRecipe(new ItemStack(Registry.thermalCentrifuge, 1),
                "CMC", "IAI", "IHI", 'C', Registry.coil,'M', Ic2Items.miningLaser, 'I', IC2.getRefinedIron(), 'A', Ic2Items.advMachine, 'H', Registry.heatConductor);

        recipes.addRecipe(new ItemStack(Registry.fluidCanningMachine), " C ", "EcE", "ITI", 'C', Ic2Items.electricCircuit, 'E', Ic2Items.emptyCell, 'c', Ic2Items.canner,  'T', Ic2Items.machineTank, 'I', "ingotTin");

        recipes.addRecipe(new ItemStack(Registry.roller, 1),
                "CPC", "PBP", "cPc", 'C', basicCircuit, 'B', Ic2Items.machine, 'c', Registry.coil, 'P', Blocks.PISTON);

        recipes.addRecipe(new ItemStack(Registry.extruder, 1),
                "iCi", "cMc", "iCi", 'C', basicCircuit, 'i', casing, 'M', Ic2Items.machine, 'c', Registry.coil);

        //recipes.addRecipe(new ItemStack(Registry.cutter, 1),
        //        " C ", "TBT", "ctc", 'C', basicCircuit,'T', Ic2Items.toolBox, 'B', Ic2Items.machine, 'c', Registry.coil, 't', Ic2Items.cutter);

        recipes.addRecipe(new ItemStack(Registry.impellerizedRoller, 1),
                "CCC", "CRC", "CBC", 'R', Registry.roller,'B', Ic2Items.advMachine, 'C', Blocks.STICKY_PISTON);

        recipes.addRecipe(new ItemStack(Registry.liquescentExtruder, 1),
                "CCC", "CEC", "CBC", 'E', Registry.extruder,'B', Ic2Items.advMachine, 'C', casing);

        //recipes.addRecipe(new ItemStack(Registry.plasmaCutter, 1),
        //        "CCC", "CcC", "CBC", 'c', Registry.cutter,'B', Ic2Items.advMachine, 'C', Ic2Items.cutter);

        recipes.addRecipe(new ItemStack(Registry.metalBender), " c ", "RAE", "rcr", 'c', "circuitAdvanced", 'R', Registry.impellerizedRoller, 'A', Ic2Items.advMachine, 'E', Registry.liquescentExtruder, 'r', Ic2cExtrasRecipes.getRefinedIronCasing());

        recipes.addRecipe(new ItemStack(Registry.treeTapper), "CTC", "HcH", "MAM", 'C', "circuitBasic", 'T', Ic2Items.electricTreeTap, 'H', new ItemStack(Blocks.HOPPER, 2), 'c', "chestWood", 'M', StackUtil.copyWithSize(Ic2Items.miningPipe, 8), 'A', Ic2Items.advMachine);

        recipes.addRecipe(new ItemStack(Registry.coil, 1),
                "CCC", "CIC", "CCC", 'I', IC2.getRefinedIron(),'C', Ic2Items.copperCable);

        recipes.addRecipe(new ItemStack(Registry.heatConductor, 1),
                "RRB", "RBR", "BRR", 'R', "itemRubber",'B', "ingotBronze");

        recipes.addRecipe(new ItemStack(Registry.blankPress), "H", "P", "P", 'H', "craftingToolForgeHammer", 'P', "plateRefinedIron");
        recipes.addRecipe(new ItemStack(Registry.rollingPress), "H", "P", 'H', "craftingToolForgeHammer", 'P', Registry.blankPress);
        recipes.addRecipe(new ItemStack(Registry.extrudingPress), "CP", 'C', "craftingToolWireCutter", 'P', Registry.blankPress);
        recipes.addRecipe(new ItemStack(Registry.cuttingPress), "P ", " C", 'P', Registry.blankPress, 'C', "craftingToolWireCutter");
        recipes.addRecipe(new ItemStack(Registry.lathingPress), " P", "C ", 'P', Registry.blankPress, 'C', "craftingToolWireCutter");
        recipes.addRecipe(new ItemStack(Registry.gearingPress), "C", "P", 'C', "craftingToolWireCutter", 'P', Registry.blankPress);

        recipes.addRecipe(new ItemStack(Registry.craftingHammer, 1),
                "III", "III", " S ", 'I', IC2.getRefinedIron(),'S', "stickWood");

        recipes.addRecipe(new ItemStack(Registry.wireCutters, 1),
                "I I", " I ", "S S", 'I', IC2.getRefinedIron(),'S', "stickWood");

        recipes.addRecipe(Ic2Items.fuelCan,
                " TT", "T T", "TTT", 'T', "casingTin");

        recipes.addRecipe(new ItemStack(Registry.stoneDustBlock), "SS", "SS", 'S', "dustStone");

        recipes.addRecipe(Ic2Items.reactorCoolantCellSimple, " T ", "TWT", " T ", 'T', "casingTin", 'W', water);
        recipes.addRecipe(Ic2Items.reactorCoolantCellTriple, "TTT", "CCC", "TTT", 'T', "casingTin", 'C', Ic2Items.reactorCoolantCellSimple);
        recipes.addRecipe(Ic2Items.reactorCoolantCellTriple, "TTT", "WWW", "TTT", 'T', new RecipeInputOreDict("casingTin", 2), 'W', StackUtil.copyWithSize(Ic2Items.waterCell, 2));
        recipes.addRecipe(Ic2Items.reactorCoolantCellSix, "TCT", "TDT", "TCT", 'T', "casingTin", 'C', Ic2Items.reactorCoolantCellTriple, 'D', Ic2Items.denseCopperPlate);

        recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.emptyCell, 16),
                " T ", "T T", " T ", 'T', "casingTin");

        recipes.addRecipe(new ItemStack(Registry.energiumDust, 9), "RRR", "RDR", "RRR", 'R', new RecipeInputOreDict("dustRedstone", 2), 'D', "dustDiamond");

        if (Loader.isModLoaded("gtclassic")){
            recipes.addRecipe(Ic2Items.battery,
                    " C ", "TRT", "TRT", 'C', Ic2Items.copperCable,'R', Items.REDSTONE, 'T', "casingTin");
        }else {
            recipes.addRecipe(Ic2Items.battery,
                    " C ", "TRT", "TRT", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'T', "casingTin");
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CCC", "RcR", "CCC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', Ic2cExtrasRecipes.getRefinedIronCasing());
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CRC", "CcC", "CRC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', Ic2cExtrasRecipes.getRefinedIronCasing());
        }


    }

    public static void initReplaceRecipes(){
        if (!IC2.config.getFlag("SteelRecipes") && Ic2cExtrasRecipes.enableCertainRecipesRequireSteel){
            recipes.overrideRecipe("shaped_HV Cable", StackUtil.copyWithSize(Ic2Items.ironCable, 12), "III", 'I', "ingotSteel");
            recipes.overrideRecipe("shaped_Insulated HV Cable", StackUtil.copyWithSize(Ic2Items.insulatedIronCable, 4), " R ", "RIR", " R ", 'R', "itemRubber", 'I', "ingotSteel");
            recipes.overrideRecipe("shaped_2xIns. HV Cable", StackUtil.copyWithSize(Ic2Items.doubleInsulatedIronCable, 4), "RRR", "RIR", "RRR", 'R', "itemRubber", 'I', "ingotSteel");
            recipes.overrideRecipe("shaped_Plasma Cable", StackUtil.copyWithSize(Ic2Items.plasmaCable, 4), "CCC", "IPI", "CCC", 'C', Ic2Items.carbonPlate, 'I', "ingotSteel", 'P', Ic2Items.plasmaCore);
            recipes.overrideRecipe("shaped_Advanced Machine Block", Ic2Items.advMachine, "ICI", "AMA", "ICI", 'I', "ingotSteel", 'C', Ic2Items.carbonPlate, 'A', Ic2Items.advancedAlloy, 'M', Ic2Items.machine);
            recipes.overrideRecipe("shaped_Advanced Machine Block_1", Ic2Items.advMachine, "IAI", "CMC", "IAI", 'I', "ingotSteel", 'C', Ic2Items.carbonPlate, 'A', Ic2Items.advancedAlloy, 'M', Ic2Items.machine);
            recipes.overrideRecipe("shaped_Tesla Coil", Ic2Items.teslaCoil, "RRR", "RMR", "ICI", 'R', "dustRedstone", 'M', Ic2Items.transformerMV, 'I', "ingotSteel", 'C', basicCircuit);
        }

    }

    public static void initShapelessRecipes(){
        FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.bronzeDust, 4), crushedCopper, crushedCopper, crushedCopper, crushedTin);

        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.constructionFoam, 4), water, "dustRedstone", "dustCoal", "dustStone", "dustStone", "dustStone");
        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.constructionFoam, 4), water, "dustRedstone", "dustCharcoal", "dustStone", "dustStone", "dustStone");

        recipes.addShapelessRecipe(new ItemStack(Registry.stoneDust, 4), Registry.stoneDustBlock);

        if (Ic2cExtrasRecipes.enableCuttingToolWires){
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.copperCable, 3), "plateCopper", Registry.wireCutters);
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.goldCable, 6), "plateGold", Registry.wireCutters);
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.tinCable, 4), "plateTin", Registry.wireCutters);
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.bronzeCable, 3), "plateBronze", Registry.wireCutters);
            if (!IC2.config.getFlag("SteelRecipes")){
                if (Ic2cExtrasRecipes.enableCertainRecipesRequireSteel){
                    recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.ironCable, 6), "plateSteel", Registry.wireCutters);
                }else{
                    recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.ironCable, 6), "plateRefinedIron", Registry.wireCutters);
                }

            }else{
                recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.ironCable, 6), "plateSteel", Registry.wireCutters);
            }
        }

        if (Ic2cExtrasRecipes.enableHammerRecipes){
            if (Ic2cExtrasRecipes.enableCasingsRequirePlates){
                recipes.addShapelessRecipe(new ItemStack(Registry.copperCasing, 2), "plateCopper", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.tinCasing, 2), "plateTin", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.silverCasing, 2), "plateSilver", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.leadCasing, 2), "plateLead", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.ironCasing, 2), "plateIron", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.goldCasing, 2), "plateGold", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.refinedIronCasing, 2), "plateRefinedIron", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.steelCasing, 2), "plateSteel", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.bronzeCasing, 2), "plateBronze", "craftingToolForgeHammer");
            }else{
                recipes.addShapelessRecipe(new ItemStack(Registry.copperCasing, 2), "ingotCopper", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.tinCasing, 2), "ingotTin", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.silverCasing, 2), "ingotSilver", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.leadCasing, 2), "ingotLead", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.ironCasing, 2), "ingotIron", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.goldCasing, 2), "ingotGold", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.refinedIronCasing, 2), "ingotRefinedIron", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.steelCasing, 2), "ingotSteel", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.bronzeCasing, 2), "ingotBronze", "craftingToolForgeHammer");
            }
        }
    }

    public static void dustUtil(String dust, ItemStack dusts, String tinyDust, ItemStack tinyDusts, String smallDust, ItemStack smallDusts) {
        recipes.addRecipe(StackUtil.copyWithSize(dusts, 1),
                "TTT", "TTT", "TTT", 'T', tinyDust);
        recipes.addShapelessRecipe(StackUtil.copyWithSize(tinyDusts, 9),
                dust);
        recipes.addRecipe(StackUtil.copyWithSize(dusts, 1),
                "SS", "SS", 'S', smallDust);
        recipes.addShapelessRecipe(StackUtil.copyWithSize(smallDusts, 4),
                dust);

    }

    public static void ingotUtil(String block, Block blocks, String ingot, ItemStack ingots){
        recipes.addRecipe(new ItemStack(blocks, 1),
                "III", "III", "III", 'I', ingot);
        recipes.addShapelessRecipe(StackUtil.copyWithSize(ingots, 9),
                block);
    }

    public static void initCompressRecipes(){
        CraftingRecipes.dustUtil("dustIron", Ic2Items.ironDust, "dustTinyIron", new ItemStack(Registry.ironTinyDust), "dustSmallIron", new ItemStack(Registry.ironSmallDust));
        CraftingRecipes.dustUtil("dustGold", Ic2Items.goldDust, "dustTinyGold", new ItemStack(Registry.goldTinyDust), "dustSmallGold", new ItemStack(Registry.goldSmallDust));
        CraftingRecipes.dustUtil("dustCopper", Ic2Items.copperDust, "dustTinyCopper", new ItemStack(Registry.copperTinyDust), "dustSmallCopper", new ItemStack(Registry.copperSmallDust));
        CraftingRecipes.dustUtil("dustTin", Ic2Items.tinDust, "dustTinyTin", new ItemStack(Registry.tinTinyDust), "dustSmallTin", new ItemStack(Registry.tinSmallDust));
        CraftingRecipes.dustUtil("dustSilver", Ic2Items.silverDust, "dustTinySilver", new ItemStack(Registry.silverTinyDust), "dustSmallSilver", new ItemStack(Registry.silverSmallDust));
        CraftingRecipes.dustUtil("dustLead", new ItemStack(Registry.leadDust), "dustTinyLead", new ItemStack(Registry.leadTinyDust), "dustSmallLead", new ItemStack(Registry.leadSmallDust));
        CraftingRecipes.dustUtil("dustObsidian", Ic2Items.obsidianDust, "dustTinyObsidian", new ItemStack(Registry.obsidianTinyDust), "dustSmallObsidian", new ItemStack(Registry.obsidianSmallDust));
        CraftingRecipes.dustUtil("dustBronze", Ic2Items.bronzeDust, "dustTinyBronze", new ItemStack(Registry.bronzeTinyDust), "dustSmallBronze", new ItemStack(Registry.bronzeSmallDust));
        recipes.addRecipe(Ic2Items.iridiumOre,
                "III", "III", "III", 'I', Registry.iridiumShard);
        recipes.addShapelessRecipe(new ItemStack(Registry.iridiumShard, 9),
                Ic2Items.iridiumOre);

        if (Loader.isModLoaded("gtclassic")){
            TileEntityCompressor.addRecipe("ingotLead", 9, new ItemStack(Registry.leadBlock));
            TileEntityCompressor.addRecipe("ingotRefinedIron", 9, new ItemStack(Registry.refinedIronBlock));
            TileEntityCompressor.addRecipe("ingotSteel", 9, new ItemStack(Registry.steelBlock));
            GameRegistry.addSmelting(Registry.steelBlock, new ItemStack(Registry.steelIngot, 9), 0.1F);
            GameRegistry.addSmelting(Registry.refinedIronBlock, StackUtil.copyWithSize(Ic2Items.refinedIronIngot, 9), 0.1F);
            GameRegistry.addSmelting(Registry.leadBlock, new ItemStack(Registry.leadIngot, 9), 0.1F);
            TileEntityMacerator.addRecipe("blockLead", 1, new ItemStack(Registry.leadDust, 9), 0.1F);
            TileEntityMacerator.addRecipe("blockRefinedIron", 1, StackUtil.copyWithSize(Ic2Items.ironDust, 9), 0.1F);
        }else if (!Loader.isModLoaded("gtclassic")){
            CraftingRecipes.ingotUtil("blockSteel", Registry.steelBlock, "ingotSteel", new ItemStack(Registry.steelIngot));
            CraftingRecipes.ingotUtil("blockLead", Registry.leadBlock, "ingotLead", new ItemStack(Registry.leadIngot));
            CraftingRecipes.ingotUtil("blockRefinedIron", Registry.refinedIronBlock, "ingotRefinedIron", Ic2Items.refinedIronIngot);
        }

    }
}
