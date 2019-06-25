package trinsdar.ic2c_extras.recipes;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.block.machine.high.TileEntityUraniumEnricher;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2States;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.Registry;

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
    public static boolean enableUraniumOreDropReplacement;
    public static boolean enableAutoFluidContainerRecipes;
    public static boolean enableEmptyRods;
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

    public static String getRefinedIronCasing() {
        return IC2.config.getFlag("SteelRecipes") ? "casingSteel" : "casingRefinedIron";
    }

    public static void init(){
        CraftingRecipes.init();
        MachineRecipes.init();
        initHarderUraniumProcessing();
    }




    public static void initHarderUraniumProcessing(){
        ItemStack stoneDust = new ItemStack(Registry.stoneDust);
        if (enableHarderUranium){
            TileEntityUraniumEnricher.URANIUM_INGOT_REFERENCE = new ItemStack(Registry.doubleEnrichedUraniumIngot);
            CommonFilters.uranFilter = new BasicItemFilter(new ItemStack(Registry.doubleEnrichedUraniumIngot));
            macerator.removeRecipe(new RecipeInputOreDict("oreUranium"));
            macerator.addRecipe(new RecipeInputOreDict("oreUranium"), new ItemStack(Registry.uraniumCrushedOre,2), 1.0F, "uraniumOre");
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(Ic2Items.reactorReEnrichedUraniumRod)), 500, new ItemStack(Registry.plutoniumTinyDust, 2), new ItemStack(Registry.uranium238SmallDust, 2));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(new ItemStack(Registry.reEnrichedPlutoniumCell))), 500, new ItemStack(Registry.thorium232TinyDust, 1), new ItemStack(Registry.plutoniumTinyDust, 1));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(new ItemStack(Registry.reEnrichedMOXCell))), 500, new ItemStack(Registry.thorium232TinyDust, 1), new ItemStack(Registry.uranium238TinyDust, 2));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(new ItemStack(Registry.reEnrichedThorium232Cell))), 500, new ItemStack(Registry.thorium230TinyDust, 1), new ItemStack(Registry.thorium232TinyDust, 1));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedUranium", 1)), 400, new ItemStack(Registry.uranium238, 6), new ItemStack(Registry.uranium235TinyDust, 1));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), 450, new ItemStack(Registry.uranium238, 4), new ItemStack(Registry.uranium235TinyDust, 1), stoneDust);
            TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), 2000, new ItemStack(Registry.uraniumPurifiedCrushedOre, 1), new ItemStack(Registry.uranium235TinyDust, 1), stoneDust);
            CraftingRecipes.recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.uraniumDrop, 1),
                    "UUU", "TTT", "UUU", 'U', "dustUranium238",'T', "dustTinyUranium235");
            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.moxFuel),
                    "UUU", "TTT", "UUU", 'U', "dustUranium238",'T', "dustTinyPlutonium");
            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.doubleEnrichedUraniumIngot), " U ", "UIU", " U ", 'U', Registry.uranium238, 'I', Ic2Items.uraniumIngot);
            CraftingRecipes.dustUtil("dustUranium235", new ItemStack(Registry.uranium235), "dustTinyUranium235", new ItemStack(Registry.uranium235TinyDust), "dustSmallUranium235", new ItemStack(Registry.uranium235SmallDust));
            CraftingRecipes.dustUtil("dustUranium238", new ItemStack(Registry.uranium238), "dustTinyUranium238", new ItemStack(Registry.uranium238TinyDust), "dustSmallUranium238", new ItemStack(Registry.uranium238SmallDust));
            CraftingRecipes.dustUtil("dustPlutonium", new ItemStack(Registry.plutoniumDust), "dustTinyPlutonium", new ItemStack(Registry.plutoniumTinyDust), "dustSmallPlutonium", new ItemStack(Registry.plutoniumSmallDust));
            CraftingRecipes.dustUtil("dustThorium232", new ItemStack(Registry.thorium232Dust), "dustTinyThorium232", new ItemStack(Registry.thorium232TinyDust));
            CraftingRecipes.dustUtil("dustThorium230", new ItemStack(Registry.thorium230Dust), "dustTinyThorium230", new ItemStack(Registry.thorium230TinyDust));
            TileEntityCompressor.addRecipe("dustPlutonium", 1, new ItemStack(Registry.plutoniumIngot));
            TileEntityCompressor.addRecipe("dustThorium232", 1, new ItemStack(Registry.thorium232Ingot));
            TileEntityCompressor.addRecipe("dustThorium230", 1, new ItemStack(Registry.thorium230Ingot));
            rodUtil(new ItemStack(Registry.singleUOXCell), new ItemStack(Registry.doubleUOXCell), new ItemStack(Registry.quadUOXCell), new ItemStack(Registry.isotopicUOXCell), new ItemStack(Registry.reEnrichedUOXCell), new ItemStack(Registry.nearDepletedUOXCell), new ItemStack(Registry.oxidizedUraniumIngot));
            rodUtil(new ItemStack(Registry.singlePlutoniumCell), new ItemStack(Registry.doublePlutoniumCell), new ItemStack(Registry.quadPlutoniumCell), new ItemStack(Registry.isotopicPlutoniumCell), new ItemStack(Registry.reEnrichedPlutoniumCell), new ItemStack(Registry.nearDepletedPlutoniumCell), new ItemStack(Registry.plutoniumIngot));
            rodUtil(new ItemStack(Registry.singleMOXCell), new ItemStack(Registry.doubleMOXCell), new ItemStack(Registry.quadMOXCell), new ItemStack(Registry.isotopicMOXCell), new ItemStack(Registry.reEnrichedMOXCell), new ItemStack(Registry.nearDepletedMOXCell), new ItemStack(Registry.moxFuel));
            rodUtil(new ItemStack(Registry.singleThorium232Cell), new ItemStack(Registry.doubleThorium232Cell), new ItemStack(Registry.quadThorium232Cell), new ItemStack(Registry.isotopicThorium232Cell), new ItemStack(Registry.reEnrichedThorium232Cell), new ItemStack(Registry.nearDepletedThorium232Cell), new ItemStack(Registry.thorium232Ingot));
            rodUtil(new ItemStack(Registry.singleThorium230Cell), new ItemStack(Registry.doubleThorium230Cell), new ItemStack(Registry.quadThorium230Cell), new ItemStack(Registry.isotopicThorium230Cell), new ItemStack(Registry.reEnrichedThorium230Cell), new ItemStack(Registry.nearDepletedThorium230Cell), new ItemStack(Registry.thorium230Ingot));
        }


    }

    public static ItemStack getEmptyRod(){
        return enableEmptyRods ? new ItemStack(Registry.emptyFuelRod) : Ic2Items.emptyCell;
    }

    public static void rodUtil(ItemStack single, ItemStack dual, ItemStack quad, ItemStack isotope, ItemStack reEnriched, ItemStack nearDepleted, ItemStack ingredient){
        ItemStack emptyRod = getEmptyRod();
        IRecipeInput coal = new RecipeInputCombined(1, new RecipeInputOreDict("dustCoal"), new RecipeInputOreDict("dustCharcoal"));
        CraftingRecipes.recipes.addShapelessRecipe(single, coal, reEnriched);
        CraftingRecipes.recipes.addRecipe(StackUtil.copyWithSize(nearDepleted, 8), "RRR", "RIR", "RRR", 'R', emptyRod, 'I', ingredient);
        CraftingRecipes.recipes.addRecipe(dual, "RPR", 'R', single, 'P', Ic2Items.denseCopperPlate);
        CraftingRecipes.recipes.addRecipe(quad, " R ", "PPP", " R ", 'R', dual, 'P', Ic2Items.denseCopperPlate);
        CraftingRecipes.recipes.addRecipe(quad, "RPR", "PPP", "RPR", 'R', single, 'P', Ic2Items.denseCopperPlate);
        CraftingRecipes.recipes.addShapelessRecipe(isotope, nearDepleted, coal);
        if (!IC2.config.getFlag("HardEnrichedUran")){
            CraftingRecipes.recipes.addShapelessRecipe(single, emptyRod, ingredient);
            ClassicRecipes.canningMachine.registerCannerItem(emptyRod, new RecipeInputItemStack(ingredient), single);
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

    public static void setConfig(boolean uranium, boolean casings, boolean wires, boolean steel, boolean hammer, boolean oredict, boolean loot, boolean twoPlates, boolean uraniumDrop, boolean fluidContainer, boolean emptyRods){
        enableHarderUranium = uranium;
        enableCasingsRequirePlates = casings;
        enableCuttingToolWires = wires;
        enableCertainRecipesRequireSteel = steel;
        enableHammerRecipes = hammer;
        enableTwoPlatesPerIngot = twoPlates;
        enableAutoOredictRecipes = oredict;
        enableLootEntries = loot;
        enableUraniumOreDropReplacement = uraniumDrop;
        enableAutoFluidContainerRecipes = fluidContainer;
        enableEmptyRods = emptyRods;
    }
}
