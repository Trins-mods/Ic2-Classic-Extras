package trinsdar.ic2c_extras.recipes;

import gtclassic.GTMod;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.block.machine.high.TileEntityUraniumEnricher;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.item.recipe.AdvRecipe;
import ic2.core.item.recipe.AdvRecipeBase;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.helpers.CompareableStack;
import ic2.core.util.misc.StackUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.items.ItemNuclearRod;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.Registry;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static ic2.api.classic.recipe.ClassicRecipes.macerator;

public class Ic2cExtrasRecipes {
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
    public static Map<CompareableStack, IRecipeInput> thermalCentrifugeValidInputs = new LinkedHashMap<CompareableStack, IRecipeInput>();
    public static Map<CompareableStack, IRecipeInput> oreWashingPlantValidInputs = new LinkedHashMap<CompareableStack, IRecipeInput>();
    public static Map<CompareableStack, IRecipeInput> rollerValidInputs = new LinkedHashMap<CompareableStack, IRecipeInput>();
    public static Map<CompareableStack, IRecipeInput> extruderValidInputs = new LinkedHashMap<CompareableStack, IRecipeInput>();
    public static Map<CompareableStack, IRecipeInput> cutterValidInputs = new LinkedHashMap<CompareableStack, IRecipeInput>();

    public static String getRefinedIronCasing() {
        return IC2.config.getFlag("SteelRecipes") ? "casingSteel" : "casingRefinedIron";
    }

    public static void init() {
        CraftingRecipes.init();
        MachineRecipes.init();
        ModRecipes.init();
        initHarderUraniumProcessing();
        if (Ic2cExtrasConfig.autoFluidContainerRecipes) {
            MachineRecipes.initFluidFillingndEmptyingRecipes();
        }
        if (Loader.isModLoaded("gtclassic")) {
            GTCRecipes.init();
        }
    }

    public static void postInit() {
        MachineRecipes.postInit();
        MachineRecipes.initMetalBenderRecipes();
        initInputLists();
    }

    public static void initInputLists() {
        oreWashingPlantValidInputs.clear();
        thermalCentrifugeValidInputs.clear();
        rollerValidInputs.clear();
        extruderValidInputs.clear();
        cutterValidInputs.clear();
        for (IMachineRecipeList.RecipeEntry recipe : oreWashingPlant.getRecipeMap()) {
            IRecipeInput input = recipe.getInput();
            for (ItemStack stack : input.getInputs()) {
                oreWashingPlantValidInputs.put(new CompareableStack(stack), input);
            }
        }
        for (IMachineRecipeList.RecipeEntry recipe : thermalCentrifuge.getRecipeMap()) {
            IRecipeInput input = recipe.getInput();
            for (ItemStack stack : input.getInputs()) {
                thermalCentrifugeValidInputs.put(new CompareableStack(stack), input);
            }
        }
        for (IMachineRecipeList.RecipeEntry recipe : rolling.getRecipeMap()) {
            IRecipeInput input = recipe.getInput();
            for (ItemStack stack : input.getInputs()) {
                rollerValidInputs.put(new CompareableStack(stack), input);
            }
        }
        for (IMachineRecipeList.RecipeEntry recipe : extruding.getRecipeMap()) {
            IRecipeInput input = recipe.getInput();
            for (ItemStack stack : input.getInputs()) {
                extruderValidInputs.put(new CompareableStack(stack), input);
            }
        }
        for (IMachineRecipeList.RecipeEntry recipe : cutting.getRecipeMap()) {
            IRecipeInput input = recipe.getInput();
            for (ItemStack stack : input.getInputs()) {
                cutterValidInputs.put(new CompareableStack(stack), input);
            }
        }
    }

    public static void initHarderUraniumProcessing() {
        ItemStack stoneDust = new ItemStack(Registry.stoneDust);
        if (Ic2cExtrasConfig.harderUranium) {
            TileEntityUraniumEnricher.URANIUM_INGOT_REFERENCE = new ItemStack(Registry.doubleEnrichedUraniumIngot);
            CommonFilters.uranFilter = new BasicItemFilter(new ItemStack(Registry.doubleEnrichedUraniumIngot));
            macerator.removeRecipe(new RecipeInputOreDict("oreUranium"));
            macerator.addRecipe(new RecipeInputOreDict("oreUranium"), new ItemStack(Registry.uraniumCrushedOre, 2), 1.0F, "uraniumOre");
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(Ic2Items.reactorReEnrichedUraniumRod)), 1500, 36000, new ItemStack(Registry.plutoniumTinyDust, 2), new ItemStack(Registry.thorium232TinyDust, 2), new ItemStack(Registry.uranium238SmallDust, 2));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(new ItemStack(Registry.reEnrichedThorium232Cell))), 1500, 36000, new ItemStack(Registry.thorium230TinyDust, 1), new ItemStack(Registry.thorium232TinyDust, 1));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedUranium", 1)), 900, 18000, new ItemStack(Registry.refinedUraniumOre, 1), new ItemStack(Registry.thorium232TinyDust, 1));
            if (!Loader.isModLoaded("gtclassic")){
                TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedCentrifugedUranium", 1)), 900, 18000, new ItemStack(Registry.uraniumDust), new ItemStack(Registry.uranium238, 2), new ItemStack(Registry.uranium235TinyDust));
            }
            TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), 1000, new ItemStack(Registry.uraniumPurifiedCrushedOre, 1), new ItemStack(Registry.leadTinyDust, 2), stoneDust);
            CraftingRecipes.recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.uraniumDrop, 1),
                    "UUU", "TTT", "UUU", 'U', "dustUranium238", 'T', "dustTinyUranium235");
            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.moxFuel),
                    "UUU", "TTT", "UUU", 'U', "dustUranium238", 'T', "dustTinyPlutonium");
            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.doubleEnrichedUraniumIngot), " U ", "UIU", " U ", 'U', Registry.uranium238, 'I', Ic2Items.uraniumIngot);
            CraftingRecipes.dustUtil("dustUranium235", new ItemStack(Registry.uranium235), "dustTinyUranium235", new ItemStack(Registry.uranium235TinyDust), "dustSmallUranium235", new ItemStack(Registry.uranium235SmallDust));
            CraftingRecipes.dustUtil("dustUranium238", new ItemStack(Registry.uranium238), "dustTinyUranium238", new ItemStack(Registry.uranium238TinyDust), "dustSmallUranium238", new ItemStack(Registry.uranium238SmallDust));
            CraftingRecipes.dustUtil("dustPlutonium", new ItemStack(Registry.plutoniumDust), "dustTinyPlutonium", new ItemStack(Registry.plutoniumTinyDust), "dustSmallPlutonium", new ItemStack(Registry.plutoniumSmallDust));
            CraftingRecipes.dustUtil("dustThorium232", new ItemStack(Registry.thorium232Dust), "dustTinyThorium232", new ItemStack(Registry.thorium232TinyDust));
            CraftingRecipes.dustUtil("dustThorium230", new ItemStack(Registry.thorium230Dust), "dustTinyThorium230", new ItemStack(Registry.thorium230TinyDust));
            TileEntityCompressor.addRecipe("dustPlutonium", 1, new ItemStack(Registry.plutoniumIngot));
            TileEntityCompressor.addRecipe("dustThorium232", 1, new ItemStack(Registry.thorium232Ingot));
            TileEntityCompressor.addRecipe("dustThorium230", 1, new ItemStack(Registry.thorium230Ingot));
            TileEntityCompressor.addRecipe("dustUranium235", 1, new ItemStack(Registry.uranium235Ingot));
            TileEntityCompressor.addRecipe("crushedPurifiedUranium", 1, Ic2Items.uraniumDrop);
            IUranium[] types = ItemNuclearRod.types;
            for(IUranium uran : types)
                rodUtil(uran.getRodType(IUranium.RodType.SingleRod), uran.getRodType(IUranium.RodType.DualRod), uran.getRodType(IUranium.RodType.QuadRod), uran.getNewIsotopicRod(), uran.getRodType(IUranium.RodType.ReEnrichedRod), uran.getRodType(IUranium.RodType.NearDepletedRod), uran.getUraniumIngot());
            }


            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.thermoElectricGenerator), "DDD", "DRD", "DGD", 'D', "plateDenseIron", 'R', Ic2Items.nuclearReactor, 'G', Ic2Items.thermalGenerator);
            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.thermoElectricGeneratorMKII), "CIC", "CTC", "CAC", 'C', Ic2cExtrasRecipes.getRefinedIronCasing(), 'I', Ic2Items.iridiumPlate, 'T', Registry.thermoElectricGenerator, 'A', Ic2Items.advMachine);
            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.plutoniumRTG), "IPI", "IPI", "IPI", 'I', "plateDenseIron", 'P', "ingotPlutonium");
            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.thoriumRTG), "ItI", "ITI", "ItI", 'I', "plateDenseIron", 't', "ingotThorium230", 'T', "ingotThorium232");
    }

    public static ItemStack getEmptyRod() {
        return Ic2cExtrasConfig.emptyNuclearRod ? new ItemStack(Registry.emptyFuelRod) : Ic2Items.emptyCell;
    }

    public static void rodUtil(ItemStack single, ItemStack dual, ItemStack quad, ItemStack isotope, ItemStack reEnriched, ItemStack nearDepleted, ItemStack ingredient) {
        ItemStack emptyRod = getEmptyRod();
        IRecipeInput copper = Loader.isModLoaded("gtc_expansion") ? new RecipeInputOreDict("plateCopper") : new RecipeInputItemStack(Ic2Items.denseCopperPlate);
        IRecipeInput coal = Loader.isModLoaded("gtclassic") ? new RecipeInputCombined(1, new RecipeInputOreDict("dustCoal"), new RecipeInputOreDict("dustCharcoal"), new RecipeInputOreDict("dustCarbon")) : new RecipeInputCombined(1, new RecipeInputOreDict("dustCoal"), new RecipeInputOreDict("dustCharcoal"));
        CraftingRecipes.recipes.addShapelessRecipe(single, coal, reEnriched);
        CraftingRecipes.recipes.addRecipe(StackUtil.copyWithSize(nearDepleted, 8), "RRR", "RIR", "RRR", 'R', emptyRod, 'I', ingredient);
        CraftingRecipes.recipes.addRecipe(dual, "RPR", 'R', single, 'P', copper);
        CraftingRecipes.recipes.addRecipe(quad, " R ", "PPP", " R ", 'R', dual, 'P', copper);
        CraftingRecipes.recipes.addRecipe(quad, "RPR", "PPP", "RPR", 'R', single, 'P', copper);
        CraftingRecipes.recipes.addShapelessRecipe(isotope, nearDepleted, coal);
        if (!IC2.config.getFlag("HardEnrichedUran")) {
            CraftingRecipes.recipes.addShapelessRecipe(single, emptyRod, ingredient);
            ClassicRecipes.canningMachine.registerCannerItem(emptyRod, new RecipeInputItemStack(ingredient), single);
        }
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        LootFunction[] funcs = new LootFunction[]{new SetMetadata(new LootCondition[0], new RandomValueRange(0, 3))};
        String entryNameIridium = "ic2c_extras:iridiumshard";
        String entryNamePlutonium = "ic2c_extras:tinyplutonium";
        Item shard = Registry.iridiumShard;
        Item plutonium = Registry.plutoniumTinyDust;
        if (Ic2cExtrasConfig.lootEntries) {
            if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, dungeonWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            } else if (event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE)) {
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, netherFortressWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            } else if (event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR) || event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CROSSING)) {
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, strongholdWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            } else if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE)) {
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, jungleTempleWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            } else if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID)) {
                event.getTable().getPool("main").addEntry(new LootEntryItem(shard, desertTempleWeight, itemQuality, funcs, new LootCondition[0], entryNameIridium));
                event.getTable().getPool("main").addEntry(new LootEntryItem(plutonium, tinyPlutonioumWeight, itemQuality, funcs, new LootCondition[0], entryNamePlutonium));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void removeRecipe(String modid, String recipeId) {
        ((ForgeRegistry<?>) ForgeRegistries.RECIPES).remove(new ResourceLocation(modid, recipeId));
        Field duplicatesf = null;
        try {
            duplicatesf = AdvRecipeBase.class.getDeclaredField("duplicates");
        } catch (NoSuchFieldException e) {
            IC2CExtras.logger.info("Trying to access Advanced recipes has failed : (");
        } catch (SecurityException e) {
            IC2CExtras.logger.info("AdvRecipeBase security deployed");
        }
        if (duplicatesf != null) {
            duplicatesf.setAccessible(true);
        }
        try {
            if (duplicatesf != null) {
                ((Set<ResourceLocation>) duplicatesf.get(duplicatesf)).remove(new ResourceLocation(modid, recipeId));
            }
        } catch (IllegalArgumentException e) {
            IC2CExtras.logger.info("Accessed AdvRecipeBase class but field getter failed");
        } catch (IllegalAccessException e) {
            IC2CExtras.logger.info("Accessed AdvRecipeBase class but access denied");
        }
    }

    public static void overrideRecipe(String modid, String recipeId, ItemStack output, Object... input) {
        Loader loader = Loader.instance();
        ModContainer old = loader.activeModContainer();
        loader.setActiveModContainer(loader.getIndexedModList().get(modid));
        ((ForgeRegistry<?>) ForgeRegistries.RECIPES).remove(new ResourceLocation(modid, recipeId));
        Field duplicatesf = null;
        try {
            duplicatesf = AdvRecipeBase.class.getDeclaredField("duplicates");
        } catch (NoSuchFieldException e) {
            GTMod.logger.info("Trying to access Advanced recipes has failed : (");
        } catch (SecurityException e) {
            GTMod.logger.info("AdvRecipeBase security deployed");
        }
        if (duplicatesf != null) {
            duplicatesf.setAccessible(true);
        }
        try {
            if (duplicatesf != null) {
                ((Set<ResourceLocation>) duplicatesf.get(duplicatesf)).remove(new ResourceLocation(modid, recipeId));
            }
        } catch (IllegalArgumentException e) {
            GTMod.logger.info("Accessed AdvRecipeBase class but field getter failed");
        } catch (IllegalAccessException e) {
            GTMod.logger.info("Accessed AdvRecipeBase class but access denied");
        }
        AdvRecipe.overrideAndGet(recipeId, output, input);
        loader.setActiveModContainer(old);
    }
}
