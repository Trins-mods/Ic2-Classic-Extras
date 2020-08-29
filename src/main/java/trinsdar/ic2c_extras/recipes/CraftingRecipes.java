package trinsdar.ic2c_extras.recipes;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
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
import net.minecraftforge.oredict.OreDictionary;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
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

    static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;

    static final String MACHINE_BASIC = "machineBlockBasic";
    static final String MACHINE_ADV = "machineBlockAdvanced";

    public static void init() {
        initShapedRecipes();
        initShapelessRecipes();
        initReplaceRecipes();
        initCompressRecipes();
        removeIc2Recipes();
    }

    public static void initShapedRecipes() {
        FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
        recipes.addRecipe(new ItemStack(Registry.advancedSteamTurbine, 1),
                " S ", "STS", " S ", 'S', Ic2Items.basicTurbine, 'T', Ic2Items.transformerMV);
        String basicCircuit = "circuitBasic";
        recipes.addRecipe(new ItemStack(Registry.oreWashingPlant, 1),
                "III", "BCB", "McM", 'I', IC2.getRefinedIron(), 'B', Items.BUCKET, 'C', MACHINE_BASIC, 'M', Ic2Items.carbonMesh, 'c', basicCircuit);
        recipes.addRecipe(new ItemStack(Registry.thermalWasher, 1),
                "BBB", "BOB", "BAB", 'B', Items.BUCKET, 'O', Registry.oreWashingPlant, 'A', MACHINE_ADV);
        recipes.addRecipe(new ItemStack(Registry.thermalCentrifuge, 1),
                "CMC", "IAI", "IHI", 'C', Registry.coil, 'M', Ic2Items.miningLaser, 'I', IC2.getRefinedIron(), 'A', MACHINE_ADV, 'H', Registry.heatConductor);

        recipes.addRecipe(new ItemStack(Registry.fluidCanningMachine), " C ", "EcE", "ITI", 'C', Ic2Items.electricCircuit, 'E', Ic2Items.emptyCell, 'c', Ic2Items.canner, 'T', Ic2Items.machineTank, 'I', "ingotTin");

        if (!Loader.isModLoaded("gtc_expansion") || !Ic2cExtrasConfig.compatGTCX) {
            recipes.addRecipe(new ItemStack(Registry.roller, 1),
                    "CPC", "PBP", "cPc", 'C', basicCircuit, 'B', MACHINE_BASIC, 'c', Registry.coil, 'P', Blocks.PISTON);

            recipes.addRecipe(new ItemStack(Registry.extruder, 1),
                    "iCi", "cMc", "iCi", 'C', basicCircuit, 'i', casing, 'M', MACHINE_BASIC, 'c', Registry.coil);

            recipes.addRecipe(new ItemStack(Registry.impellerizedRoller, 1),
                    "CCC", "CRC", "CBC", 'R', Registry.roller, 'B', MACHINE_ADV, 'C', Blocks.STICKY_PISTON);

            recipes.addRecipe(new ItemStack(Registry.liquescentExtruder, 1),
                    "CCC", "CEC", "CBC", 'E', Registry.extruder, 'B', MACHINE_ADV, 'C', casing);

            recipes.addRecipe(new ItemStack(Registry.metalBender), " c ", "RAE", "rcr", 'c', "circuitAdvanced", 'R', Registry.impellerizedRoller, 'A', MACHINE_ADV, 'E', Registry.liquescentExtruder, 'r', Ic2cExtrasRecipes.getRefinedIronCasing());
        }
        if (!Loader.isModLoaded("gtclassic")){
            recipes.addRecipe(new ItemStack(Registry.autocraftingTable), " B ", "CcC", " A ", 'B', Ic2Items.battery, 'C', "circuitAdvanced", 'c', "workbench", 'A', "machineBlockAdvanced");
        }
        //recipes.addRecipe(new ItemStack(Registry.cutter, 1),
        //        " C ", "TBT", "ctc", 'C', basicCircuit,'T', Ic2Items.toolBox, 'B', MACHINE_BASIC, 'c', Registry.coil, 't', Ic2Items.cutter);

        //recipes.addRecipe(new ItemStack(Registry.plasmaCutter, 1),
        //        "CCC", "CcC", "CBC", 'c', Registry.cutter,'B', MACHINE_ADV, 'C', Ic2Items.cutter);



        recipes.addRecipe(new ItemStack(Registry.treeTapper), "CTC", "HcH", "MAM", 'C', "circuitBasic", 'T', Ic2Items.electricTreeTap, 'H', new ItemStack(Blocks.HOPPER, 2), 'c', "chestWood", 'M', StackUtil.copyWithSize(Ic2Items.miningPipe, 8), 'A', MACHINE_ADV);

        recipes.addRecipe(new ItemStack(Registry.electricDisenchanter), "EeE", "ebe", "BBB", 'E', Items.EMERALD, 'e', new ItemStack(Items.ENCHANTED_BOOK, 1, OreDictionary.WILDCARD_VALUE), 'b', Ic2Items.electricEnchanter, 'B', Blocks.BOOKSHELF);

        recipes.addRecipe(new ItemStack(Registry.reinforcedEncasedCable), "RRR", "RCR", "RRR", 'R', Ic2Items.reinforcedStone, 'C', Ic2Items.tribbleInsulatedIronCable);

        recipes.addRecipe(new ItemStack(Registry.coil, 1),
                "CCC", "CIC", "CCC", 'I', IC2.getRefinedIron(), 'C', Ic2Items.copperCable);

        recipes.addRecipe(new ItemStack(Registry.heatConductor, 1),
                "RRB", "RBR", "BRR", 'R', "itemRubber", 'B', "ingotBronze");

        recipes.addRecipe(new ItemStack(Registry.blankPress), "H", "P", "P", 'H', "craftingToolForgeHammer", 'P', "plateRefinedIron");
        recipes.addRecipe(new ItemStack(Registry.rollingPress), "H", "P", 'H', "craftingToolForgeHammer", 'P', Registry.blankPress);
        recipes.addRecipe(new ItemStack(Registry.extrudingPress), "CP", 'C', "craftingToolWireCutter", 'P', Registry.blankPress);
        recipes.addRecipe(new ItemStack(Registry.cuttingPress), "P ", " C", 'P', Registry.blankPress, 'C', "craftingToolWireCutter");
        recipes.addRecipe(new ItemStack(Registry.lathingPress), " P", "C ", 'P', Registry.blankPress, 'C', "craftingToolWireCutter");
        recipes.addRecipe(new ItemStack(Registry.gearingPress), "C", "P", 'C', "craftingToolWireCutter", 'P', Registry.blankPress);

        recipes.addRecipe(new ItemStack(Registry.craftingHammer, 1),
                "III", "III", " S ", 'I', "ingotRefinedIron", 'S', "stickWood");

        recipes.addRecipe(new ItemStack(Registry.wireCutters, 1),
                "I I", " I ", "S S", 'I', "ingotRefinedIron", 'S', "stickWood");

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

        if (Ic2cExtrasConfig.requiredLeadUses){
            recipes.overrideRecipe("shaped_tile.blockreactorchamber_1490756150", Ic2Items.reactorChamber.copy(), " L ", "LML", " L ", 'L', "plateDenseLead", 'M', "machineBlockBasic");
            recipes.overrideRecipe("shaped_tile.blocknuclearreactor_1318009097", Ic2Items.nuclearReactor.copy(), "LCL", "RRR", "LGL", 'L', "plateDenseLead", 'C', "circuitAdvanced", 'R', Ic2Items.reactorChamber, 'G', Ic2Items.generator);
            if (!Loader.isModLoaded("gtc_expansion") || !Ic2cExtrasConfig.compatGTCX) {
                recipes.overrideShapelessRecipe("shapeless_item.itemreactorplating_1093967048", Ic2Items.reactorPlating, "ingotLead", Ic2Items.advancedAlloy);
            }
        } else {
            recipes.addRecipe(Ic2Items.reactorChamber.copy(), " L ", "LML", " L ", 'L', "plateDenseLead", 'M', "machineBlockBasic");
            if (!Loader.isModLoaded("gtc_expansion") || !Ic2cExtrasConfig.compatGTCX) {
                recipes.addShapelessRecipe(Ic2Items.reactorPlating, "ingotLead", Ic2Items.advancedAlloy);
            }
        }

        recipes.addRecipe(new ItemStack(Registry.containmentBox), "LLL", "LCL", "LLL", 'L', "casingLead", 'C', "chest");

        if (Loader.isModLoaded("gtclassic")) {
            recipes.addRecipe(Ic2Items.battery,
                    " C ", "TRT", "TRT", 'C', Ic2Items.copperCable, 'R', Items.REDSTONE, 'T', "casingTin");
        } else {
            recipes.addRecipe(Ic2Items.battery,
                    " C ", "TRT", "TRT", 'C', Ic2Items.insulatedCopperCable, 'R', Items.REDSTONE, 'T', "casingTin");
        }


    }

    public static void removeIc2Recipes(){
        if (Ic2cExtrasConfig.removeLossyWrenchMechanic){
            Ic2cExtrasRecipes.removeRecipe("ic2", "shaped_item.precisionwrench_-1943783685");
            Ic2cExtrasRecipes.removeRecipe("ic2", "shaped_item.precisionwrench_-1322002202");
            if (Loader.isModLoaded("gravisuit")){
                Ic2cExtrasRecipes.overrideRecipe("gravisuit", "shaped_item.gravitool_40257720", GameRegistry.makeItemStack("gravisuit:gravitool", 0, 1, null), "CHC", "AEA", "WcT", 'C', Ic2Items.carbonPlate, 'H', Ic2Items.electricHoe, 'A', Ic2Items.advancedAlloy, 'E', Ic2Items.energyCrystal, 'W', Ic2Items.electricWrench, 'c', "circuitBasic", 'T', Ic2Items.electricTreeTap);
            }
        }
    }

    public static void initReplaceRecipes() {
        if (!IC2.config.getFlag("SteelRecipes") && Ic2cExtrasConfig.cablesTakeSteel) {
            if (!Ic2cExtrasConfig.plateCablesOverrideRegularCables) {
                recipes.overrideRecipe("shaped_item.itemironcable_1314416875", StackUtil.copyWithSize(Ic2Items.ironCable, 12), "III", 'I', "ingotSteel");
            }
            recipes.overrideRecipe("shaped_item.itemironcablei_926773675", StackUtil.copyWithSize(Ic2Items.insulatedIronCable, 4), " R ", "RIR", " R ", 'R', "itemRubber", 'I', "ingotSteel");
            recipes.overrideRecipe("shaped_item.itemironcableii_268464298", StackUtil.copyWithSize(Ic2Items.doubleInsulatedIronCable, 4), "RRR", "RIR", "RRR", 'R', "itemRubber", 'I', "ingotSteel");
            recipes.overrideRecipe("shaped_item.itemplasmacable_-449044295", StackUtil.copyWithSize(Ic2Items.plasmaCable, 4), "CCC", "IPI", "CCC", 'C', Ic2Items.carbonPlate, 'I', "ingotSteel", 'P', Ic2Items.plasmaCore);
        }
        if (Ic2cExtrasConfig.emptyNuclearRod) {
            ItemStack emptyFuelRod = new ItemStack(Registry.emptyFuelRod);
            recipes.overrideShapelessRecipe("shapeless_item.reactoruraniumsimple_-1804731375", Ic2Items.reactorUraniumRodSingle, emptyFuelRod, Ic2Items.uraniumIngot);
            ClassicRecipes.canningMachine.removeCanningRecipe(Ic2Items.emptyCell, Ic2Items.uraniumIngot);
            ClassicRecipes.canningMachine.registerCannerItem(emptyFuelRod, new RecipeInputItemStack(Ic2Items.uraniumIngot), Ic2Items.reactorUraniumRodSingle);
            if (!IC2.config.getFlag("HardEnrichedUran")) {
                recipes.overrideShapelessRecipe("shapeless_item.reactoruraniumredstonesimple_-1804729360", Ic2Items.reactorRedstoneUraniumRodSingle, emptyFuelRod, Ic2Items.redstoneUraniumIngot);
                recipes.overrideShapelessRecipe("shapeless_item.reactoruraniumblazesimple_-1804728306", Ic2Items.reactorBlazeUraniumRodSingle, emptyFuelRod, Ic2Items.blazeUraniumIngot);
                recipes.overrideShapelessRecipe("shapeless_item.reactoruraniumenderpearlsimple_-1804727252", Ic2Items.reactorEnderPearlUraniumRodSingle, emptyFuelRod, Ic2Items.enderPearlUraniumIngot);
                recipes.overrideShapelessRecipe("shapeless_item.reactoruraniumnetherstarsimple_-1804726198", Ic2Items.reactorNetherStarUraniumRodSingle, emptyFuelRod, Ic2Items.netherStarUraniumIngot);
                recipes.overrideShapelessRecipe("shapeless_item.reactoruraniumcharcoalsimple_-1804725144", Ic2Items.reactorCharcoalUraniumRodSingle, emptyFuelRod, Ic2Items.charcoalUraniumIngot);
                ClassicRecipes.canningMachine.removeCanningRecipe(Ic2Items.emptyCell, Ic2Items.redstoneUraniumIngot);
                ClassicRecipes.canningMachine.removeCanningRecipe(Ic2Items.emptyCell, Ic2Items.blazeUraniumIngot);
                ClassicRecipes.canningMachine.removeCanningRecipe(Ic2Items.emptyCell, Ic2Items.enderPearlUraniumIngot);
                ClassicRecipes.canningMachine.removeCanningRecipe(Ic2Items.emptyCell, Ic2Items.netherStarUraniumIngot);
                ClassicRecipes.canningMachine.removeCanningRecipe(Ic2Items.emptyCell, Ic2Items.charcoalUraniumIngot);
                ClassicRecipes.canningMachine.registerCannerItem(emptyFuelRod, new RecipeInputItemStack(Ic2Items.redstoneUraniumIngot), Ic2Items.reactorRedstoneUraniumRodSingle);
                ClassicRecipes.canningMachine.registerCannerItem(emptyFuelRod, new RecipeInputItemStack(Ic2Items.blazeUraniumIngot), Ic2Items.reactorBlazeUraniumRodSingle);
                ClassicRecipes.canningMachine.registerCannerItem(emptyFuelRod, new RecipeInputItemStack(Ic2Items.enderPearlUraniumIngot), Ic2Items.reactorEnderPearlUraniumRodSingle);
                ClassicRecipes.canningMachine.registerCannerItem(emptyFuelRod, new RecipeInputItemStack(Ic2Items.netherStarUraniumIngot), Ic2Items.reactorNetherStarUraniumRodSingle);
                ClassicRecipes.canningMachine.registerCannerItem(emptyFuelRod, new RecipeInputItemStack(Ic2Items.charcoalUraniumIngot), Ic2Items.reactorCharcoalUraniumRodSingle);
            }
        }

    }

    public static void initShapelessRecipes() {
        FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.bronzeDust, 4), crushedCopper, crushedCopper, crushedCopper, crushedTin);

        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.constructionFoam, 4), water, "dustRedstone", "dustCoal", "dustStone", "dustStone", "dustStone");
        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.constructionFoam, 4), water, "dustRedstone", "dustCharcoal", "dustStone", "dustStone", "dustStone");

        recipes.addShapelessRecipe(new ItemStack(Registry.stoneDust, 4), Registry.stoneDustBlock);

        if (Ic2cExtrasConfig.craftingCablesWithPlates) {
            boolean e = Ic2cExtrasConfig.plateCablesMakeLessThenExtruder;
            int copperBronzeAmount = e ? 2 : 3;
            int goldHVAmount = e ? 4 : 6;
            int tinAmount = e ? 3 : 4;
            if (Ic2cExtrasConfig.plateCablesOverrideRegularCables) {
                Ic2cExtrasRecipes.removeRecipe("ic2", "shaped_item.itemcable_-895690168");
                Ic2cExtrasRecipes.removeRecipe("ic2", "shaped_item.itemgoldcable_-121137345");
                Ic2cExtrasRecipes.removeRecipe("ic2", "shaped_item.itemironcable_1314416875");
                Ic2cExtrasRecipes.removeRecipe("ic2", "shaped_item.itemironcable_-1596711841");
                Ic2cExtrasRecipes.removeRecipe("ic2", "shaped_item.itemtincable_1475909484");
                Ic2cExtrasRecipes.removeRecipe("ic2", "shaped_item.itembronzecable_1006731162");
            }
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.copperCable, copperBronzeAmount), "plateCopper", "craftingToolWireCutter");
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.goldCable, goldHVAmount), "plateGold", "craftingToolWireCutter");
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.tinCable, tinAmount), "plateTin", "craftingToolWireCutter");
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.bronzeCable, copperBronzeAmount), "plateBronze", "craftingToolWireCutter");
            if (!IC2.config.getFlag("SteelRecipes")) {
                if (Ic2cExtrasConfig.cablesTakeSteel) {
                    recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.ironCable, goldHVAmount), "plateSteel", "craftingToolWireCutter");
                } else {
                    recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.ironCable, goldHVAmount), "plateRefinedIron", "craftingToolWireCutter");
                }
            } else {
                recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.ironCable, goldHVAmount), "plateSteel", "craftingToolWireCutter");
            }
        }

        if (Ic2cExtrasConfig.craftingHammerRecipes) {

            if (Ic2cExtrasConfig.casingsRequirePlates) {
                if (!Loader.isModLoaded("gtc_expansion")) {
                    recipes.addRecipe(new ItemStack(Registry.copperPlate, 1), "H", "I", 'I', "ingotCopper", 'H', "craftingToolForgeHammer");
                    recipes.addRecipe(new ItemStack(Registry.tinPlate, 1), "H", "I", 'I', "ingotTin", 'H', "craftingToolForgeHammer");
                    recipes.addRecipe(new ItemStack(Registry.silverPlate, 1), "H", "I", 'I', "ingotSilver", 'H', "craftingToolForgeHammer");
                    recipes.addRecipe(new ItemStack(Registry.leadPlate, 1), "H", "I", 'I', "ingotLead", 'H', "craftingToolForgeHammer");
                    recipes.addRecipe(new ItemStack(Registry.ironPlate, 1), "H", "I", 'I', "ingotIron", 'H', "craftingToolForgeHammer");
                    recipes.addRecipe(new ItemStack(Registry.goldPlate, 1), "H", "I", 'I', "ingotGold", 'H', "craftingToolForgeHammer");
                    recipes.addRecipe(new ItemStack(Registry.refinedIronPlate, 1), "H", "I", 'I', "ingotRefinedIron", 'H', "craftingToolForgeHammer");
                    recipes.addRecipe(new ItemStack(Registry.steelPlate, 1), "H", "I", 'I', "ingotSteel", 'H', "craftingToolForgeHammer");
                    recipes.addRecipe(new ItemStack(Registry.bronzePlate, 1), "H", "I", 'I', "ingotBronze", 'H', "craftingToolForgeHammer");
                }
                recipes.addShapelessRecipe(new ItemStack(Registry.copperCasing, 2), "plateCopper", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.tinCasing, 2), "plateTin", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.silverCasing, 2), "plateSilver", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.leadCasing, 2), "plateLead", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.ironCasing, 2), "plateIron", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.goldCasing, 2), "plateGold", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.refinedIronCasing, 2), "plateRefinedIron", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.steelCasing, 2), "plateSteel", "craftingToolForgeHammer");
                recipes.addShapelessRecipe(new ItemStack(Registry.bronzeCasing, 2), "plateBronze", "craftingToolForgeHammer");
            } else {
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

    public static void dustUtil(String dust, ItemStack dusts, String tinyDust, ItemStack tinyDusts) {
        recipes.addRecipe(StackUtil.copyWithSize(dusts, 1),
                "TTT", "TTT", "TTT", 'T', tinyDust);
        recipes.addShapelessRecipe(StackUtil.copyWithSize(tinyDusts, 9),
                dust);

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

    public static void ingotUtil(String block, Block blocks, String ingot, ItemStack ingots) {
        recipes.addRecipe(new ItemStack(blocks, 1),
                "III", "III", "III", 'I', ingot);
        recipes.addShapelessRecipe(StackUtil.copyWithSize(ingots, 9),
                block);
    }

    public static void initCompressRecipes() {
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

        if (Loader.isModLoaded("gtclassic")) {
            TileEntityCompressor.addRecipe("ingotLead", 9, new ItemStack(Registry.leadBlock));
            TileEntityCompressor.addRecipe("ingotRefinedIron", 9, new ItemStack(Registry.refinedIronBlock));
            TileEntityCompressor.addRecipe("ingotSteel", 9, new ItemStack(Registry.steelBlock));
            GameRegistry.addSmelting(Registry.steelBlock, new ItemStack(Registry.steelIngot, 9), 0.1F);
            GameRegistry.addSmelting(Registry.refinedIronBlock, StackUtil.copyWithSize(Ic2Items.refinedIronIngot, 9), 0.1F);
            GameRegistry.addSmelting(Registry.leadBlock, new ItemStack(Registry.leadIngot, 9), 0.1F);
            TileEntityMacerator.addRecipe("blockLead", 1, new ItemStack(Registry.leadDust, 9), 0.1F);
            TileEntityMacerator.addRecipe("blockRefinedIron", 1, StackUtil.copyWithSize(Ic2Items.ironDust, 9), 0.1F);
        } else {
            CraftingRecipes.ingotUtil("blockSteel", Registry.steelBlock, "ingotSteel", new ItemStack(Registry.steelIngot));
            CraftingRecipes.ingotUtil("blockLead", Registry.leadBlock, "ingotLead", new ItemStack(Registry.leadIngot));
            CraftingRecipes.ingotUtil("blockRefinedIron", Registry.refinedIronBlock, "ingotRefinedIron", Ic2Items.refinedIronIngot);
        }

    }
}
