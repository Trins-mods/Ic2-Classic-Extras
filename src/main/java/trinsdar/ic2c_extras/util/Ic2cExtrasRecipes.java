package trinsdar.ic2c_extras.util;

import com.mcmoddev.basemetals.data.MaterialNames;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.block.machine.high.TileEntityUraniumEnricher;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2States;
import ic2.core.util.misc.StackUtil;
import mods.railcraft.common.items.RailcraftItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ic2.api.classic.recipe.ClassicRecipes.macerator;

public class Ic2cExtrasRecipes {
    public static boolean enableHarderUranium;
    public static boolean enableCasingsRequirePlates;
    public static boolean enableCuttingToolWires;
    public static boolean enableCertainRecipesRequireSteel;
    public static boolean enableHammerRecipes;
    public static boolean enableTwoPlatesPerIngot;
    public static boolean enableAutoOredictRecipes;
    public static boolean enableLootEntries;
    public static int
    itemQuality = 0,
    dungeonWeight = 10,
    netherFortressWeight = 15,
    jungleTempleWeight = 15,
    desertTempleWeight = 15,
    strongholdWeight = 20,
    tinyPlutonioumWeight = 5;
    public static IMachineRecipeList rolling = new BasicMachineRecipeList("rolling");
    public static IMachineRecipeList extruding = new BasicMachineRecipeList("extruding");
    public static IMachineRecipeList cutting = new BasicMachineRecipeList("cutting");
    public static IMachineRecipeList oreWashingPlant = new BasicMachineRecipeList("oreWashingPlant");
    public static IMachineRecipeList thermalCentrifuge = new BasicMachineRecipeList("thermalCentrifuge");

    public static MaterialNames materialNamesBme;
    public static com.mcmoddev.modernmetals.data.MaterialNames materialNamesMme;

    static IRecipeInput casing = new RecipeInputCombined(1,
            new RecipeInputOreDict("casingRefinedIron"),
            new RecipeInputOreDict("casingSilver"), new RecipeInputOreDict("casingSteel"));

    static IRecipeInput crushedCopper = new RecipeInputCombined(1,
            new RecipeInputOreDict("crushedCopper"),
            new RecipeInputOreDict("crushedPurifiedCopper"));

    static IRecipeInput crushedTin = new RecipeInputCombined(1,
            new RecipeInputOreDict("crushedTin"),
            new RecipeInputOreDict("crushedPurifiedTin"));

    private static String getRefinedIronCasing() {
        return IC2.config.getFlag("SteelRecipes") ? "casingSteel" : "casingRefinedIron";
    }

    private static String basicCircuit = "circuitBasic";

    public static void init(){
        initShapedRecipes();
        initShapelessRecipes();
        initReplaceRecipes();
        initCompressRecipes();
        initFurnaceRecipes();
        initReplaceMaceratorRecipes();
        initMachineRecipes();
        initHarderUraniumProcessing();
        postInit();
        initRailcraftRecipes();
    }
    static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
    public static void initShapedRecipes(){
        FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
        recipes.addRecipe(new ItemStack(Registry.advancedSteamTurbine, 1),
                " S ", "STS", " S ", 'S', Ic2Items.basicTurbine,'T', Ic2Items.transformerMV);
        recipes.addRecipe(new ItemStack(Registry.oreWashingPlant, 1),
                "III", "BCB", "McM", 'I', IC2.getRefinedIron(),'B', Items.BUCKET, 'C', Ic2Items.machine, 'M', Ic2Items.carbonMesh, 'c', basicCircuit);
        recipes.addRecipe(new ItemStack(Registry.thermalWasher, 1),
                "BBB", "BAB", "BOB", 'B', Items.BUCKET, 'A', Ic2Items.advMachine, 'O', Registry.oreWashingPlant);
        recipes.addRecipe(new ItemStack(Registry.thermalCentrifuge, 1),
                "CMC", "IAI", "IHI", 'C', Registry.coil,'M', Ic2Items.miningLaser, 'I', IC2.getRefinedIron(), 'A', Ic2Items.advMachine, 'H', Registry.heatConductor);

        recipes.addRecipe(new ItemStack(Registry.roller, 1),
                " C ", "TBT", "ctc", 'C', basicCircuit,'T', Ic2Items.toolBox, 'B', Ic2Items.machine, 'c', Registry.coil, 't', Blocks.PISTON);

        recipes.addRecipe(new ItemStack(Registry.extruder, 1),
                " C ", "TBT", "cwc", 'C', basicCircuit,'T', Ic2Items.toolBox, 'B', Ic2Items.machine, 'c', Registry.coil, 'w', Ic2Items.ironFence);

        //recipes.addRecipe(new ItemStack(Registry.cutter, 1),
        //        " C ", "TBT", "ctc", 'C', basicCircuit,'T', Ic2Items.toolBox, 'B', Ic2Items.machine, 'c', Registry.coil, 't', Ic2Items.cutter);

        recipes.addRecipe(new ItemStack(Registry.impellerizedRoller, 1),
                "CCC", "CRC", "CBC", 'R', Registry.roller,'B', Ic2Items.advMachine, 'C', Blocks.STICKY_PISTON);

        recipes.addRecipe(new ItemStack(Registry.liquescentExtruder, 1),
                "CCC", "CEC", "CBC", 'E', Registry.extruder,'B', Ic2Items.advMachine, 'C', Ic2Items.ironFence);

        //recipes.addRecipe(new ItemStack(Registry.plasmaCutter, 1),
        //        "CCC", "CcC", "CBC", 'c', Registry.cutter,'B', Ic2Items.advMachine, 'C', Ic2Items.cutter);

        recipes.addRecipe(new ItemStack(Registry.coil, 1),
                "CCC", "CIC", "CCC", 'I', IC2.getRefinedIron(),'C', Ic2Items.copperCable);

        recipes.addRecipe(new ItemStack(Registry.heatConductor, 1),
                "RRB", "RBR", "BRR", 'R', "itemRubber",'B', "ingotBronze");

        recipes.addRecipe(new ItemStack(Registry.craftingHammer, 1),
                "III", "III", " S ", 'I', IC2.getRefinedIron(),'S', "stickWood");

        recipes.addRecipe(new ItemStack(Registry.wireCutters, 1),
                "I I", " I ", "S S", 'I', IC2.getRefinedIron(),'S', "stickWood");

        recipes.addRecipe(Ic2Items.battery,
                " C ", "TRT", "TRT", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'T', "casingTin");

        recipes.addRecipe(Ic2Items.fuelCan,
                " TT", "T T", "TTT", 'T', "casingTin");

        recipes.addRecipe(Ic2Items.reactorCoolantCellSimple, " T ", "TWT", " T ", 'T', "casingTin", 'W', water);
        recipes.addRecipe(Ic2Items.reactorCoolantCellTriple, "TTT", "CCC", "TTT", 'T', "casingTin", 'C', Ic2Items.reactorCoolantCellSimple);
        recipes.addRecipe(Ic2Items.reactorCoolantCellTriple, "TTT", "WWW", "TTT", 'T', new RecipeInputOreDict("casingTin", 2), 'W', StackUtil.copyWithSize(Ic2Items.waterCell, 2));
        recipes.addRecipe(Ic2Items.reactorCoolantCellSix, "TCT", "TDT", "TCT", 'T', "casingTin", 'C', Ic2Items.reactorCoolantCellTriple, 'D', Ic2Items.denseCopperPlate);

        recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.emptyCell, 16),
                " T ", "T T", " T ", 'T', "casingTin");

        recipes.addRecipe(new ItemStack(Registry.energiumDust, 9), "RRR", "RDR", "RRR", 'R', new RecipeInputOreDict("dustRedstone", 2), 'D', "dustDiamond");

        if (Loader.isModLoaded("gtclassic")){
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CCC", "RcR", "CCC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', casing);
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CRC", "CcC", "CRC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', casing);
            recipes.addRecipe(new ItemStack(Registry.energiumDust, 9), "RRR", "RDR", "RRR", 'R', new ItemStack(Items.REDSTONE, 2), 'D', "dustRuby");
        }else{
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CCC", "RcR", "CCC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', getRefinedIronCasing());
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CRC", "CcC", "CRC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', getRefinedIronCasing());
        }


    }

    public static void initReplaceRecipes(){
        if (!IC2.config.getFlag("SteelRecipes") && enableCertainRecipesRequireSteel){
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


        if (enableCuttingToolWires){
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.copperCable, 3), "plateCopper", Registry.wireCutters);
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.goldCable, 6), "plateGold", Registry.wireCutters);
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.tinCable, 4), "plateTin", Registry.wireCutters);
            recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.bronzeCable, 3), "plateBronze", Registry.wireCutters);
            if (!IC2.config.getFlag("SteelRecipes")){
                if (enableCertainRecipesRequireSteel){
                    recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.ironCable, 6), "plateSteel", Registry.wireCutters);
                }else{
                    recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.ironCable, 6), "plateRefinedIron", Registry.wireCutters);
                }

            }else{
                recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.ironCable, 6), "plateSteel", Registry.wireCutters);
            }
        }

        if (enableHammerRecipes){
            if (enableCasingsRequirePlates){
                recipes.addShapelessRecipe(new ItemStack(Registry.copperCasing, 2), "plateCopper", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.tinCasing, 2), "plateTin", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.silverCasing, 2), "plateSilver", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.leadCasing, 2), "plateLead", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.ironCasing, 2), "plateIron", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.goldCasing, 2), "plateGold", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.refinedIronCasing, 2), "plateRefinedIron", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.steelCasing, 2), "plateSteel", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.bronzeCasing, 2), "plateBronze", Registry.craftingHammer);
            }else{
                recipes.addShapelessRecipe(new ItemStack(Registry.copperCasing, 2), "ingotCopper", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.tinCasing, 2), "ingotTin", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.silverCasing, 2), "ingotSilver", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.leadCasing, 2), "ingotLead", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.ironCasing, 2), "ingotIron", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.goldCasing, 2), "ingotGold", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.refinedIronCasing, 2), "ingotRefinedIron", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.steelCasing, 2), "ingotSteel", Registry.craftingHammer);
                recipes.addShapelessRecipe(new ItemStack(Registry.bronzeCasing, 2), "ingotBronze", Registry.craftingHammer);
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
        Ic2cExtrasRecipes.dustUtil("dustIron", Ic2Items.ironDust, "dustTinyIron", new ItemStack(Registry.ironTinyDust), "dustSmallIron", new ItemStack(Registry.ironSmallDust));
        Ic2cExtrasRecipes.dustUtil("dustGold", Ic2Items.goldDust, "dustTinyGold", new ItemStack(Registry.goldTinyDust), "dustSmallGold", new ItemStack(Registry.goldSmallDust));
        Ic2cExtrasRecipes.dustUtil("dustCopper", Ic2Items.copperDust, "dustTinyCopper", new ItemStack(Registry.copperTinyDust), "dustSmallCopper", new ItemStack(Registry.copperSmallDust));
        Ic2cExtrasRecipes.dustUtil("dustTin", Ic2Items.tinDust, "dustTinyTin", new ItemStack(Registry.tinTinyDust), "dustSmallTin", new ItemStack(Registry.tinSmallDust));
        Ic2cExtrasRecipes.dustUtil("dustSilver", Ic2Items.silverDust, "dustTinySilver", new ItemStack(Registry.silverTinyDust), "dustSmallSilver", new ItemStack(Registry.silverSmallDust));
        Ic2cExtrasRecipes.dustUtil("dustLead", new ItemStack(Registry.leadDust), "dustTinyLead", new ItemStack(Registry.leadTinyDust), "dustSmallLead", new ItemStack(Registry.leadSmallDust));
        Ic2cExtrasRecipes.dustUtil("dustObsidian", Ic2Items.obsidianDust, "dustTinyObsidian", new ItemStack(Registry.obsidianTinyDust), "dustSmallObsidian", new ItemStack(Registry.obsidianSmallDust));
        Ic2cExtrasRecipes.dustUtil("dustBronze", Ic2Items.bronzeDust, "dustTinyBronze", new ItemStack(Registry.bronzeTinyDust), "dustSmallBronze", new ItemStack(Registry.bronzeSmallDust));
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
            Ic2cExtrasRecipes.ingotUtil("blockSteel", Registry.steelBlock, "ingotSteel", new ItemStack(Registry.steelIngot));
            Ic2cExtrasRecipes.ingotUtil("blockLead", Registry.leadBlock, "ingotLead", new ItemStack(Registry.leadIngot));
            Ic2cExtrasRecipes.ingotUtil("blockRefinedIron", Registry.refinedIronBlock, "ingotRefinedIron", Ic2Items.refinedIronIngot);
        }

    }

    public static void initFurnaceRecipes(){
        GameRegistry.addSmelting(Registry.ironCrushedOre, new ItemStack(Items.IRON_INGOT), 0.7F);
        GameRegistry.addSmelting(Registry.goldCrushedOre, new ItemStack(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(Registry.copperCrushedOre, StackUtil.copyWithSize(Ic2Items.copperIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.tinCrushedOre, StackUtil.copyWithSize(Ic2Items.tinIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.silverCrushedOre, StackUtil.copyWithSize(Ic2Items.silverIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.leadCrushedOre, new ItemStack(Registry.leadIngot), 0.5F);
        GameRegistry.addSmelting(Registry.ironPurifiedCrushedOre, new ItemStack(Items.IRON_INGOT), 0.7F);
        GameRegistry.addSmelting(Registry.goldPurifiedCrushedOre, new ItemStack(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(Registry.copperPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.copperIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.tinPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.tinIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.silverPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.silverIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.leadPurifiedCrushedOre, new ItemStack(Registry.leadIngot), 0.5F);
        GameRegistry.addSmelting(Registry.leadDust, new ItemStack(Registry.leadIngot), 0.5F);
    }

    public static void initReplaceMaceratorRecipes(){
        macerator.removeRecipe(new RecipeInputOreDict("oreIron"));
        macerator.addRecipe(new RecipeInputOreDict("oreIron"), new ItemStack(Registry.ironCrushedOre,2), 0.7F, "ironOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreGold"));
        macerator.addRecipe(new RecipeInputOreDict("oreGold"), new ItemStack(Registry.goldCrushedOre,2), 1.0F, "goldOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreCopper"));
        macerator.addRecipe(new RecipeInputOreDict("oreCopper"), new ItemStack(Registry.copperCrushedOre,2), 0.3F, "copperOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreTin"));
        macerator.addRecipe(new RecipeInputOreDict("oreTin"), new ItemStack(Registry.tinCrushedOre,2), 0.4F, "tinOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreSilver"));
        macerator.addRecipe(new RecipeInputOreDict("oreSilver"), new ItemStack(Registry.silverCrushedOre,2), 0.8F, "silverOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreLead"));
        macerator.addRecipe(new RecipeInputOreDict("oreLead"), new ItemStack(Registry.leadCrushedOre,2), 0.8F, "leadOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorIron"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorIron", 3), new ItemStack(Registry.ironCrushedOre,2), 0.7F, "ironPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorGold"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorGold", 3), new ItemStack(Registry.goldCrushedOre,2), 1.0F, "goldPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorCopper"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorCopper", 3), new ItemStack(Registry.copperCrushedOre,2), 0.3F, "copperPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorTin"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorTin", 3), new ItemStack(Registry.tinCrushedOre,2), 0.4F, "tinPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorSilver"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorSilver", 3), new ItemStack(Registry.silverCrushedOre,2), 0.8F, "silverPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorLead"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorLead", 3), new ItemStack(Registry.leadCrushedOre,2), 0.8F, "leadPoorOre");
    }

    public static void postInit() {
        Set<String> crushedBlacklist = new HashSet();
        Set<String> crushedPurifiedBlackList = new HashSet();
        Set<String> plateBlacklist = new HashSet();
        Set<String> ingotWhitelist = new HashSet();
        Set<String> gemBlacklist = new HashSet();
        Set<String> ingotBmeMmeBlacklist = new HashSet();
        crushedBlacklist.addAll(Arrays.asList("crushedIron", "crushedGold", "crushedSilver", "crushedLead", "crushedCopper", "crushedTin", "crushedUranium"));
        crushedPurifiedBlackList.addAll(Arrays.asList("crushedPurifiedIron", "crushedPurifiedGold", "crushedPurifiedSilver", "crushedPurifiedLead", "crushedPurifiedCopper", "crushedPurifiedTin", "crushedPurifiedUranium"));
        plateBlacklist.addAll(Arrays.asList("plateIron", "plateGold", "plateSilver", "plateLead", "plateCopper", "plateTin", "plateRefinedIron", "plateSteel", "plateBronze"));
        ingotWhitelist.addAll(Arrays.asList("ingotIron", "ingotGold", "ingotSilver", "ingotLead", "ingotCopper", "ingotTin", "ingotRefinedIron", "ingotSteel", "ingotBronze"));
        gemBlacklist.addAll(Arrays.asList("ingotDiamond", "ingotEmerald", "ingotQuartz", "ingotIridium"));
        if (Loader.isModLoaded("basemetals")){
            plateBlacklist.addAll(Arrays.asList("plateAdamantine", "plateAntimony", "plateBismuth", "plateColdiron", "plateNickel", "platePlatinum", "plateStarsteel", "plateZinc"));
            ingotBmeMmeBlacklist.addAll(Arrays.asList("ingotAdamantine", "ingotAntimony", "ingotBismuth", "ingotColdiron", "ingotNickel", "ingotPlatinum", "ingotStarsteel", "ingotZinc"));
        }
        if (Loader.isModLoaded("modernmetals")){
            plateBlacklist.addAll(Arrays.asList("plateAluminum", "plateAluminium", "plateAluminumbrass", "plateAluminiumbrass", "plateBeryllium", "plateBoron", "plateCadmium", "plateChrome", "plateChromium", "plateGalvanizedsteel", "plateIridium", "plateMagnesium", "plateManganese", "plateNichrome", "plateOsmium", "platePlutonium", "plateRutile", "plateStainlesssteel", "plateTantalum", "plateTitanium", "plateThorium", "plateTungsten", "plateUranium", "plateZirconium"));
            ingotBmeMmeBlacklist.addAll(Arrays.asList("ingotAluminum", "ingotAluminium", "ingotAluminumbrass", "ingotAluminiumbrass", "ingotBeryllium", "ingotBoron", "ingotCadmium", "ingotChrome", "ingotChromium", "ingotGalvanizedsteel", "ingotIridium", "ingotMagnesium", "ingotManganese", "ingotNichrome", "ingotOsmium", "ingotPlutonium", "ingotRutile", "ingotStainlesssteel", "ingotTantalum", "ingotTitanium", "ingotThorium", "ingotTungsten", "ingotUranium", "ingotZirconium"));
        }
        String[] var2 = OreDictionary.getOreNames();
        int var3 = var2.length;

        if (enableAutoOredictRecipes){
            for(int var4 = 0; var4 < var3; ++var4) {
                String id = var2[var4];
                String casing;
                String plate;
                NonNullList listCasings;
                NonNullList listPlates;
                if (id.startsWith("plate")) {
                    if (!plateBlacklist.contains(id)){
                        casing = "casing" + id.substring(5);
                        if (OreDictionary.doesOreNameExist(casing)) {
                            listCasings = OreDictionary.getOres(casing, false);
                            if (!listCasings.isEmpty()) {
                                rolling.addRecipe(new RecipeInputOreDict(id, 1), StackUtil.copyWithSize((ItemStack)listCasings.get(0), 2), casing + "Rolling");
                            }
                        }
                    }

                }
                if (id.startsWith("ingot")){
                    if (ingotWhitelist.contains(id) && !gemBlacklist.contains(id)){
                        plate = "plate" + id.substring(5);
                        if (enableCasingsRequirePlates){
                            if (OreDictionary.doesOreNameExist(plate)) {
                                listPlates = OreDictionary.getOres(plate, false);
                                if (!listPlates.isEmpty()) {
                                    rolling.addRecipe(new RecipeInputOreDict(id, 1), (ItemStack)listPlates.get(0), plate + "Rolling");
                                    if (enableHammerRecipes){
                                        if (enableTwoPlatesPerIngot){
                                            recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", "I", 'H', "craftingToolForgeHammer", 'I', id  );
                                        }else {
                                            recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", 'H', "craftingToolForgeHammer", 'I', id );
                                        }
                                    }
                                }
                            }
                        }
                    } else if (!ingotWhitelist.contains(id) && !gemBlacklist.contains(id) && !ingotBmeMmeBlacklist.contains(id)){
                        plate = "plate" + id.substring(5);
                        if (OreDictionary.doesOreNameExist(plate)) {
                            listPlates = OreDictionary.getOres(plate, false);
                            if (!listPlates.isEmpty()) {
                                rolling.addRecipe(new RecipeInputOreDict(id, 1), (ItemStack)listPlates.get(0), plate + "Rolling");
                                if (enableHammerRecipes){
                                    if (enableTwoPlatesPerIngot){
                                        recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", "I", 'H', "craftingToolForgeHammer", 'I', id );
                                    }else {
                                        recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", 'H', "craftingToolForgeHammer", 'I', id );
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void initMachineRecipes(){
        int lowHeat = 250;
        int mediumHeat = 300;
        ItemStack stoneDust = new ItemStack(Registry.stoneDust);
        //ore washing plant
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedIron", 1)), 1000, new ItemStack(Registry.ironPurifiedCrushedOre, 1), new ItemStack(Registry.ironTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedGold", 1)), 1000, new ItemStack(Registry.goldPurifiedCrushedOre, 1), new ItemStack(Registry.goldTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), 1000, new ItemStack(Registry.copperPurifiedCrushedOre, 1), new ItemStack(Registry.copperTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedTin", 1)), 1000, new ItemStack(Registry.tinPurifiedCrushedOre, 1), new ItemStack(Registry.tinTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), 1000, new ItemStack(Registry.silverPurifiedCrushedOre, 1), new ItemStack(Registry.silverTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedLead", 1)), 1000, new ItemStack(Registry.leadPurifiedCrushedOre, 1), new ItemStack(Registry.leadTinyDust, 3), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputItemStack(new ItemStack(Blocks.GRAVEL, 1))), 100, stoneDust);

        //thermal centrifuge recipes
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedIron", 1)), lowHeat, Ic2Items.ironDust, new ItemStack(Registry.goldTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedGold", 1)), lowHeat, Ic2Items.goldDust, new ItemStack(Registry.silverTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedCopper", 1)), lowHeat, Ic2Items.copperDust, new ItemStack(Registry.tinTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedTin", 1)), lowHeat, Ic2Items.tinDust, new ItemStack(Registry.ironTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedSilver", 1)), lowHeat, Ic2Items.silverDust, new ItemStack(Registry.leadTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedLead", 1)), lowHeat, new ItemStack(Registry.leadDust, 1), new ItemStack(Registry.copperTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedIron", 1)), mediumHeat, Ic2Items.ironDust, new ItemStack(Registry.goldTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedGold", 1)), mediumHeat, Ic2Items.goldDust, new ItemStack(Registry.silverTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), mediumHeat, Ic2Items.copperDust, new ItemStack(Registry.tinTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedTin", 1)), mediumHeat, Ic2Items.tinDust, new ItemStack(Registry.ironTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), mediumHeat, Ic2Items.silverDust, stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedLead", 1)), mediumHeat, new ItemStack(Registry.leadDust, 1), stoneDust);

        if (enableCasingsRequirePlates){
            rolling.addRecipe((new RecipeInputOreDict("plateCopper", 1)),  new ItemStack(Registry.copperCasing, 2), 0.7f, "copperPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateTin", 1)),  new ItemStack(Registry.tinCasing, 2), 0.7f, "tinPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateSilver", 1)),  new ItemStack(Registry.silverCasing, 2), 0.7f, "silverPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateLead", 1)),  new ItemStack(Registry.leadCasing, 2), 0.7f, "leadPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateIron", 1)),  new ItemStack(Registry.ironCasing, 2), 0.7f, "ironPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateGold", 1)),  new ItemStack(Registry.goldCasing, 2), 0.7f, "goldPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateRefinedIron", 1)),  new ItemStack(Registry.refinedIronCasing, 2), 0.7f, "refinedIronPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateSteel", 1)),  new ItemStack(Registry.steelCasing, 2), 0.7f, "steelPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateBronze", 1)),  new ItemStack(Registry.bronzeCasing, 2), 0.7f, "bronzePlateItemCasingRolling");
        }else {
            rolling.addRecipe((new RecipeInputOreDict("ingotCopper", 1)),  new ItemStack(Registry.copperCasing, 2), 0.7f, "copperItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotTin", 1)),  new ItemStack(Registry.tinCasing, 2), 0.7f, "tinItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotSilver", 1)),  new ItemStack(Registry.silverCasing, 2), 0.7f, "silverItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotLead", 1)),  new ItemStack(Registry.leadCasing, 2), 0.7f, "leadItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotIron", 1)),  new ItemStack(Registry.ironCasing, 2), 0.7f, "ironItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotGold", 1)),  new ItemStack(Registry.goldCasing, 2), 0.7f, "goldItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotRefinedIron", 1)),  new ItemStack(Registry.refinedIronCasing, 2), 0.7f, "refinedIronItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotSteel", 1)),  new ItemStack(Registry.steelCasing, 2), 0.7f, "steelItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotBronze", 1)),  new ItemStack(Registry.bronzeCasing, 2), 0.7f, "bronzeItemCasingRolling");
        }

        rolling.addRecipe((new RecipeInputOreDict(getRefinedIronCasing(), 1)),  StackUtil.copyWithSize(Ic2Items.miningPipe, 1), 0.7f, "miningPipeRolling");

        extruding.addRecipe(new RecipeInputOreDict("ingotCopper", 1),  StackUtil.copyWithSize(Ic2Items.copperCable, 3), 0.7f, "copperCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotTin", 1),  StackUtil.copyWithSize(Ic2Items.tinCable, 4), 0.7f, "tinCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotBronze", 1),  StackUtil.copyWithSize(Ic2Items.bronzeCable, 3), 0.7f, "bronzeCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotGold", 1),  StackUtil.copyWithSize(Ic2Items.goldCable, 6), 0.7f, "goldCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("casingTin", 1),  StackUtil.copyWithSize(Ic2Items.tinCan, 1), 0.7f, "tinCanExtruding");
        extruding.addRecipe(new RecipeInputOreDict(getRefinedIronCasing(), 2),  StackUtil.copyWithSize(Ic2Items.ironFence, 3), 0.7f, "ironFenceExtruding");

        macerator.addRecipe(new RecipeInputOreDict("gemDiamond"), new ItemStack(Registry.diamondDust), 0.5F, "Diamond Dust");
        macerator.addRecipe(new RecipeInputItemStack(Ic2Items.energyCrystal), new ItemStack(Registry.energiumDust, 6), "Energium Dust");

        TileEntityCompressor.addRecipe(new ItemStack(Registry.energiumDust), 6, Ic2Items.energyCrystal);

        if (!IC2.config.getFlag("SteelRecipes")){
            if(enableCertainRecipesRequireSteel){
                extruding.addRecipe(new RecipeInputOreDict("ingotSteel", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f, "HVCableExtruding");
            }else {
                extruding.addRecipe(new RecipeInputOreDict("ingotRefinedIron", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f, "HVCableExtruding");
            }
        }else {
            extruding.addRecipe(new RecipeInputOreDict("ingotSteel", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f, "HVCableExtruding");
        }

        //cutting.addRecipe(new RecipeInputOreDict(IC2.getRefinedIron(), 3), Ic2Items.turbineBlade, "turbineBladeCutting");
    }

    public static void initRailcraftRecipes(){
        if (Loader.isModLoaded("railcraft")){
            //Crafters.rockCrusher().getRecipes().removeIf(-> true);
            macerator.addRecipe(new RecipeInputOreDict("itemSlag"), RailcraftItems.DUST.getStack(1, 4), "Ground Blast Furnace Slag");
        }
    }
    public static void initHarderUraniumProcessing(){
        ItemStack stoneDust = new ItemStack(Registry.stoneDust);
        if (enableHarderUranium){
            TileEntityUraniumEnricher.URANIUM_INGOT_REFERENCE = new ItemStack(Registry.doubleEnrichedUraniumIngot);
            CommonFilters.uranFilter = new BasicItemFilter(new ItemStack(Registry.doubleEnrichedUraniumIngot));
            macerator.removeRecipe(new RecipeInputOreDict("oreUranium"));
            macerator.addRecipe(new RecipeInputOreDict("oreUranium"), new ItemStack(Registry.uraniumCrushedOre,2), 1.0F, "uraniumOre");
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(Ic2Items.reactorReEnrichedUraniumRod)), 500, new ItemStack(Registry.plutoniumTinyDust, 2), new ItemStack(Registry.uranium238SmallDust, 2));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedUranium", 1)), 400, new ItemStack(Registry.uranium238, 6), new ItemStack(Registry.uranium235TinyDust, 1));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), 450, new ItemStack(Registry.uranium238, 4), new ItemStack(Registry.uranium235TinyDust, 1), stoneDust);
            TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), 2000, new ItemStack(Registry.uraniumPurifiedCrushedOre, 1), new ItemStack(Registry.uranium235TinyDust, 1), stoneDust);
            recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.uraniumDrop, 1),
                    "UUU", "TTT", "UUU", 'U', "dustUranium238",'T', "dustTinyUranium235");
            recipes.addRecipe(new ItemStack(Registry.doubleEnrichedUraniumIngot), " U ", "UIU", " U ", 'U', Registry.uranium238, 'I', Ic2Items.uraniumIngot);
            Ic2cExtrasRecipes.dustUtil("dustUranium235", new ItemStack(Registry.uranium235), "dustTinyUranium235", new ItemStack(Registry.uranium235TinyDust), "dustSmallUranium235", new ItemStack(Registry.uranium235SmallDust));
            Ic2cExtrasRecipes.dustUtil("dustUranium238", new ItemStack(Registry.uranium238), "dustTinyUranium238", new ItemStack(Registry.uranium238TinyDust), "dustSmallUranium238", new ItemStack(Registry.uranium238SmallDust));
            Ic2cExtrasRecipes.dustUtil("dustPlutonium", new ItemStack(Registry.plutonium), "dustTinyPlutonium", new ItemStack(Registry.plutoniumTinyDust), "dustSmallPlutonium", new ItemStack(Registry.plutoniumSmallDust));
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onHarvestDropsEvent(BlockEvent.HarvestDropsEvent event) {
        IBlockState block = event.getState();
        if (block == Ic2States.uraniumOre){
            if (enableHarderUranium){
                event.getDrops().clear();
                event.getDrops().add(Ic2Items.uraniumOre);
            }
        }
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        LootFunction[] funcs = new LootFunction[] { new SetMetadata(new LootCondition[0], new RandomValueRange(0, 3)) };
        String entryNameIridium = "ic2c_extras:iridiumshard";
        String entryNamePlutonium = "ic2c_extras:tinyplutonium";
        Item shard = Registry.iridiumShard;
        Item plutonium = Registry.plutoniumTinyDust;
        if (enableLootEntries){
            if(event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)){
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, dungeonWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            }else if(event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE)){
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, netherFortressWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            }else if(event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR) || event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CROSSING)){
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, strongholdWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            }else if(event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE)){
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, jungleTempleWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            }else if(event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID)){
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, desertTempleWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            }
        }


    }

    public static void setConfig(boolean uranium, boolean casings, boolean wires, boolean steel, boolean hammer, boolean oredict, boolean loot, boolean twoPlates){
        enableHarderUranium = uranium;
        enableCasingsRequirePlates = casings;
        enableCuttingToolWires = wires;
        enableCertainRecipesRequireSteel = steel;
        enableHammerRecipes = hammer;
        enableTwoPlatesPerIngot = twoPlates;
        enableAutoOredictRecipes = oredict;
        enableLootEntries = loot;
    }
}
