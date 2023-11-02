package trinsdar.ic2c_extras.recipes;

import ic2.core.IC2;
import ic2.core.utils.helpers.StackUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.registries.ForgeRegistry;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.items.ItemNuclearRod;
import trinsdar.ic2c_extras.items.urantypes.Plutonium;
import trinsdar.ic2c_extras.items.urantypes.Thorium;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.Registry;

import java.lang.reflect.Field;
import java.util.Set;

public class Ic2cExtrasRecipes {
    public static int
    itemQuality = 0,
    dungeonWeight = 10,
    netherFortressWeight = 15,
    jungleTempleWeight = 15,
    desertTempleWeight = 15,
    strongholdWeight = 20,
    tinyPlutonioumWeight = 5;

    public static void init() {
        CraftingRecipes.init();
        MachineRecipes.init();
        ModRecipes.init();
        initHarderUraniumProcessing();
        if (Ic2cExtrasConfig.autoFluidContainerRecipes) {
            MachineRecipes.initFluidFillingndEmptyingRecipes();
        }
    }

    public static void postInit() {;
        initInputLists();
    }

    public static void initInputLists() {
    }

    public static void initHarderUraniumProcessing() {
        /*ItemStack stoneDust = new ItemStack(Registry.stoneDust);
        if (Ic2cExtrasConfig.harderUranium) {
            TileEntityUraniumEnricher.URANIUM_INGOT_REFERENCE = new ItemStack(Registry.doubleEnrichedUraniumIngot);
            CommonFilters.uranFilter = new BasicItemFilter(new ItemStack(Registry.doubleEnrichedUraniumIngot));
            macerator.removeRecipe(new RecipeInputOreDict("oreUranium"));
            macerator.addRecipe(new RecipeInputOreDict("oreUranium"), new ItemStack(Registry.uraniumCrushedOre, 2), 1.0F, "uraniumOre");
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(Ic2Items.reactorReEnrichedUraniumRod)), 1500, 36000, new ItemStack(Registry.plutoniumTinyDust, 1), new ItemStack(Registry.thoriumTinyDust, 2), getEmptyRod().copy());
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(new ItemStack(Registry.reEnrichedUranium238Cell))), 1500, 36000, new ItemStack(Registry.plutoniumTinyDust, 2), getEmptyRod().copy());
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(new ItemStack(Registry.reEnrichedThorium232Cell))), 1500, 36000, new ItemStack(Registry.uranium233TinyDust, 2), getEmptyRod().copy());
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedUranium", 1)), 900, 18000, new ItemStack(Registry.refinedUraniumOre, 1), new ItemStack(Registry.thoriumTinyDust, 1));
            if (!Loader.isModLoaded("gtclassic")){
                TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedCentrifugedUranium", 1)), 900, 18000, new ItemStack(Registry.uraniumDust), new ItemStack(Registry.uranium238, 2), new ItemStack(Registry.uranium235TinyDust));
                TileEntityThermalCentrifuge.addRecipe(new RecipeInputOreDict("dustUranium", 22), 1000, 125000, new ItemStack(Registry.uranium238, 16), new ItemStack(Registry.uranium235, 2), new ItemStack(Registry.thoriumDust, 4));
            }
            TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), 1000, new ItemStack(Registry.uraniumPurifiedCrushedOre, 1), new ItemStack(Registry.leadTinyDust, 2), stoneDust);
            CraftingRecipes.recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.uraniumDrop, 1),
                    "UUU", "TTT", "UUU", 'U', "dustUranium238", 'T', "dustTinyUranium235");
            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.moxFuel),
                    "UUU", "TTT", "UUU", 'U', "dustUranium238", 'T', "dustTinyPlutonium");
            CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.doubleEnrichedUraniumIngot), " U ", "UIU", " U ", 'U', Registry.uranium238, 'I', Ic2Items.uraniumIngot);
            CraftingRecipes.dustUtil("dustUranium235", new ItemStack(Registry.uranium235), "dustTinyUranium235", new ItemStack(Registry.uranium235TinyDust), "dustSmallUranium235", new ItemStack(Registry.uranium235SmallDust));
            CraftingRecipes.dustUtil("dustUranium238", new ItemStack(Registry.uranium238), "dustTinyUranium238", new ItemStack(Registry.uranium238TinyDust), "dustSmallUranium238", new ItemStack(Registry.uranium238SmallDust));
            CraftingRecipes.dustUtil("dustUranium233", new ItemStack(Registry.uranium233Dust), "dustTinyUranium233", new ItemStack(Registry.uranium233TinyDust));
            if (!Loader.isModLoaded("gtclassic")){
                CraftingRecipes.dustUtil("dustPlutonium", new ItemStack(Registry.plutoniumDust), "dustTinyPlutonium", new ItemStack(Registry.plutoniumTinyDust), "dustSmallPlutonium", new ItemStack(Registry.plutoniumSmallDust));
                CraftingRecipes.dustUtil("dustThorium", new ItemStack(Registry.thoriumDust), "dustTinyThorium", new ItemStack(Registry.thoriumTinyDust));
                TileEntityCompressor.addRecipe("dustPlutonium", 1, new ItemStack(Registry.plutoniumIngot));
                TileEntityCompressor.addRecipe("dustThorium", 1, new ItemStack(Registry.thoriumIngot));
            }
            TileEntityCompressor.addRecipe("dustUranium235", 1, new ItemStack(Registry.uranium235Ingot));
            TileEntityCompressor.addRecipe("dustUranium233", 1, new ItemStack(Registry.uranium233Ingot));
            TileEntityCompressor.addRecipe("dustUranium238", 1, new ItemStack(Registry.uranium238Ingot));
            TileEntityCompressor.addRecipe("crushedPurifiedUranium", 1, Ic2Items.uraniumDrop);
            IUranium[] types = ItemNuclearRod.types;
            for(IUranium uran : types) {
                if ((uran instanceof Thorium || uran instanceof Plutonium) && Loader.isModLoaded("gtclassic")){
                    continue;
                }
                rodUtil(uran.getRodType(IUranium.RodType.SingleRod), uran.getRodType(IUranium.RodType.DualRod), uran.getRodType(IUranium.RodType.QuadRod), uran.getNewIsotopicRod(), uran.getRodType(IUranium.RodType.ReEnrichedRod), uran.getRodType(IUranium.RodType.NearDepletedRod), uran.getUraniumIngot());
            }
            ItemStack emptyFuelRod = getEmptyRod();
            CraftingRecipes.recipes.overrideRecipe("shaped_item.itemCellUranEmpty_-1582032965", StackUtil.copyWithSize(Ic2Items.reactorNearDepletedUraniumRod, 4), " R ", "RIR", " R ", 'R', emptyFuelRod, 'I', Ic2Items.uraniumIngot);
            CraftingRecipes.recipes.overrideRecipe("shaped_item.itemCellRedstoneEnrichedUranEmpty_192974428", StackUtil.copyWithSize(Ic2Items.reactorNearDepletedRedstoneUraniumRod, 4), " R ", "RIR", " R ", 'R', emptyFuelRod, 'I', Ic2Items.redstoneUraniumIngot);
            CraftingRecipes.recipes.overrideRecipe("shaped_item.itemCellBlazeEnrichedUranEmpty_1080478140", StackUtil.copyWithSize(Ic2Items.reactorNearDepletedBlazeUraniumRod, 4), " R ", "RIR", " R ", 'R', emptyFuelRod, 'I', Ic2Items.blazeUraniumIngot);
            CraftingRecipes.recipes.overrideRecipe("shaped_item.itemCellEnderPearlEnrichedUranEmpty_1967981852", StackUtil.copyWithSize(Ic2Items.reactorNearDepletedEnderPearlUraniumRod, 4), " R ", "RIR", " R ", 'R', emptyFuelRod, 'I', Ic2Items.enderPearlUraniumIngot);
            CraftingRecipes.recipes.overrideRecipe("shaped_item.itemCellNetherStarEnrichedUranEmpty_-1439481732", StackUtil.copyWithSize(Ic2Items.reactorNearDepletedNetherStarUraniumRod, 4), " R ", "RIR", " R ", 'R', emptyFuelRod, 'I', Ic2Items.netherStarUraniumIngot);
            CraftingRecipes.recipes.overrideRecipe("shaped_item.itemCellCharcoalEnrichedUranEmpty_-551978020", StackUtil.copyWithSize(Ic2Items.reactorNearDepletedCharcoalUraniumRod, 4), " R ", "RIR", " R ", 'R', emptyFuelRod, 'I', Ic2Items.charcoalUraniumIngot);
        }


        CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.thermoElectricGenerator), "DDD", "DRD", "DGD", 'D', "plateDenseIron", 'R', Ic2Items.nuclearReactor, 'G', Ic2Items.thermalGenerator);
        CraftingRecipes.recipes.addRecipe(new ItemStack(Registry.plutoniumRTG), "IPI", "IPI", "IPI", 'I', "plateDenseIron", 'P', "ingotPlutonium");*/
    }

    public static void rodUtil(ItemStack single, ItemStack dual, ItemStack quad, ItemStack isotope, ItemStack reEnriched, ItemStack nearDepleted, ItemStack ingredient) {
        /*ItemStack emptyRod = getEmptyRod();
        IRecipeInput copper = Loader.isModLoaded("gtc_expansion") ? new RecipeInputOreDict("plateCopper") : new RecipeInputItemStack(Ic2Items.denseCopperPlate);
        IRecipeInput coal = Loader.isModLoaded("gtclassic") ? new RecipeInputCombined(1, new RecipeInputOreDict("dustCoal"), new RecipeInputOreDict("dustCharcoal"), new RecipeInputOreDict("dustCarbon")) : new RecipeInputCombined(1, new RecipeInputOreDict("dustCoal"), new RecipeInputOreDict("dustCharcoal"));
        CraftingRecipes.recipes.addShapelessRecipe(single, coal, reEnriched);
        CraftingRecipes.recipes.addRecipe(StackUtil.copyWithSize(nearDepleted, 4), " R ", "RIR", " R ", 'R', emptyRod, 'I', ingredient);
        CraftingRecipes.recipes.addRecipe(dual, "RPR", 'R', single, 'P', copper);
        CraftingRecipes.recipes.addRecipe(quad, " R ", "PPP", " R ", 'R', dual, 'P', copper);
        CraftingRecipes.recipes.addRecipe(quad, "RPR", "PPP", "RPR", 'R', single, 'P', copper);
        CraftingRecipes.recipes.addShapelessRecipe(isotope, nearDepleted, coal);
        if (!IC2.config.getFlag("HardEnrichedUran")) {
            CraftingRecipes.recipes.addShapelessRecipe(single, emptyRod, ingredient);
            ClassicRecipes.canningMachine.registerCannerItem(emptyRod, new RecipeInputItemStack(ingredient), single);
        }*/
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        /*LootFunction[] funcs = new LootFunction[]{new SetMetadata(new LootCondition[0], new RandomValueRange(0, 3))};
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
        }*/
    }
}
