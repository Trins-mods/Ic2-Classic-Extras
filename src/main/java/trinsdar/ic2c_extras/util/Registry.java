package trinsdar.ic2c_extras.util;

import ic2.api.crops.CropCard;
import ic2.core.IC2;
import ic2.core.item.block.ItemBlockRare;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.blocks.BlockIc2cEGenerator;
import trinsdar.ic2c_extras.blocks.BlockMachine;
import trinsdar.ic2c_extras.blocks.BlockMetal;
import trinsdar.ic2c_extras.blocks.BlockStoneDust;
import trinsdar.ic2c_extras.blocks.CropPlumbilia;
import trinsdar.ic2c_extras.items.ItemCasings;
import trinsdar.ic2c_extras.items.ItemCrushedOre;
import trinsdar.ic2c_extras.items.ItemDepeletedNuclearRods;
import trinsdar.ic2c_extras.items.ItemIsotopicRod;
import trinsdar.ic2c_extras.items.ItemMiscs;
import trinsdar.ic2c_extras.items.ItemNuclearRod;
import trinsdar.ic2c_extras.items.ItemNuclearRod.NuclearRodTypes;
import trinsdar.ic2c_extras.items.ItemNuclearRod.NuclearRodVariants;
import trinsdar.ic2c_extras.items.ItemPlates;
import trinsdar.ic2c_extras.items.ItemPurifiedCrushedOre;
import trinsdar.ic2c_extras.items.ItemRTG;
import trinsdar.ic2c_extras.items.ItemSmallDust;
import trinsdar.ic2c_extras.items.ItemTinyDust;
import trinsdar.ic2c_extras.items.ItemToolCrafting;
import trinsdar.ic2c_extras.items.ItemToolCutter;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockGenerator;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockMachine;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockMetal;
import trinsdar.ic2c_extras.tileentity.TileEntityAdvancedSteamTurbine;
import trinsdar.ic2c_extras.tileentity.TileEntityCutter;
import trinsdar.ic2c_extras.tileentity.TileEntityElectricDisenchanter;
import trinsdar.ic2c_extras.tileentity.TileEntityExtruder;
import trinsdar.ic2c_extras.tileentity.TileEntityFluidCanningMachine;
import trinsdar.ic2c_extras.tileentity.TileEntityImpellerizedRoller;
import trinsdar.ic2c_extras.tileentity.TileEntityLiquescentExtruder;
import trinsdar.ic2c_extras.tileentity.TileEntityMetalBender;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityPlasmaCutter;
import trinsdar.ic2c_extras.tileentity.TileEntityRoller;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalWasher;
import trinsdar.ic2c_extras.tileentity.TileEntityThermoElectricGenerator;
import trinsdar.ic2c_extras.tileentity.TileEntityTreeTapper;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.util.ArrayList;
import java.util.List;

public class Registry {
    static final List<Block> toRegisterBlock = new ArrayList();
    static final List<Item> toRegisterItem = new ArrayList<>();

    public static final BlockMachine oreWashingPlant = registerBlock(new BlockMachine("oreWashingPlant", Ic2cExtrasLang.ORE_WASHING_PLANT));
    public static final BlockMachine thermalCentrifuge = registerBlock(new BlockMachine("thermalCentrifuge", Ic2cExtrasLang.THERMAL_CENTRIFUGE));
    public static final BlockMachine thermalWasher = registerBlock(new BlockMachine("thermalWasher", Ic2cExtrasLang.THERMAL_WASHER));
    public static final BlockMachine roller = registerBlock(new BlockMachine("roller", Ic2cExtrasLang.ROLLER));
    public static final BlockMachine extruder = registerBlock(new BlockMachine("extruder", Ic2cExtrasLang.EXTRUDER));
    public static final BlockMachine cutter = registerBlock(new BlockMachine("cutter", Ic2cExtrasLang.CUTTER));
    public static final BlockMachine impellerizedRoller = registerBlock(new BlockMachine("impellerizedRoller", Ic2cExtrasLang.IMPELLERIZED_ROLLER));
    public static final BlockMachine liquescentExtruder = registerBlock(new BlockMachine("liquescentExtruder", Ic2cExtrasLang.LIQUESCENT_EXTRUDER));
    public static final BlockMachine plasmaCutter = registerBlock(new BlockMachine("plasmaCutter", Ic2cExtrasLang.PLASMA_CUTTER));
    public static final BlockMachine metalBender = registerBlock(new BlockMachine("metalBender", Ic2cExtrasLang.METAL_BENDER));
    public static final BlockMachine fluidCanningMachine = registerBlock(new BlockMachine("fluidCanningMachine", Ic2cExtrasLang.FLUID_CANNING_MACHINE));
    public static final BlockMachine treeTapper = registerBlock(new BlockMachine("treeTapper", Ic2cExtrasLang.TREE_TAPPER));
    public static final BlockMachine electricDisenchanter = registerBlock(new BlockMachine("electricdisenchanter", Ic2cExtrasLang.ELECTRIC_DISENCHANTER));
    public static final BlockIc2cEGenerator advancedSteamTurbine = registerBlock(new BlockIc2cEGenerator("advancedSteamTurbine", Ic2cExtrasLang.ADVANCED_STEAM_TURBINE));
    public static final BlockIc2cEGenerator thermoElectricGenerator = registerBlock(new BlockIc2cEGenerator("thermoElectricGenerator", Ic2cExtrasLang.THERMO_ELECTRIC_GENERATOR));
    public static final BlockIc2cEGenerator thermoElectricGeneratorMKII = registerBlock(new BlockIc2cEGenerator("thermoElectricGeneratorMkII", Ic2cExtrasLang.THERMO_ELECTRIC_GENERATOR_MK_II));
    public static final BlockMetal steelBlock = registerBlock(new BlockMetal("steelBlock", 0));
    public static final BlockMetal refinedIronBlock = registerBlock(new BlockMetal("refinedIronBlock", 1));
    public static final BlockMetal leadBlock = registerBlock(new BlockMetal("leadBlock", 2));
    public static final BlockStoneDust stoneDustBlock = registerBlock(new BlockStoneDust());

    public static CropCard cropPlumbilia = new CropPlumbilia();

    static String copper = "copper";
    static String tin = "tin";
    static String silver = "silver";
    static String lead = "lead";
    static String iron = "iron";
    static String gold = "gold";
    static String steel = "steel";
    static String bronze = "bronze";
    static String uranium = "uranium";

    public static final ItemCasings copperCasing = createItem(new ItemCasings(copper, 0));
    public static final ItemCasings tinCasing = createItem(new ItemCasings(tin, 1));
    public static final ItemCasings silverCasing = createItem(new ItemCasings(silver, 2));
    public static final ItemCasings leadCasing = createItem(new ItemCasings(lead, 3));
    public static final ItemCasings ironCasing = createItem(new ItemCasings(iron, 4));
    public static final ItemCasings goldCasing = createItem(new ItemCasings(gold, 5));
    public static final ItemCasings refinedIronCasing = createItem(new ItemCasings("refinedIron", 6));
    public static final ItemCasings steelCasing = createItem(new ItemCasings(steel, 7));
    public static final ItemCasings bronzeCasing = createItem(new ItemCasings(bronze, 8));

    public static final ItemPlates copperPlate = createItem(new ItemPlates(copper, 0));
    public static final ItemPlates tinPlate = createItem(new ItemPlates(tin, 1));
    public static final ItemPlates silverPlate = createItem(new ItemPlates(silver, 2));
    public static final ItemPlates leadPlate = createItem(new ItemPlates(lead, 3));
    public static final ItemPlates ironPlate = createItem(new ItemPlates(iron, 4));
    public static final ItemPlates goldPlate = createItem(new ItemPlates(gold, 5));
    public static final ItemPlates refinedIronPlate = createItem(new ItemPlates("refinedIron", 6));
    public static final ItemPlates steelPlate = createItem(new ItemPlates(steel, 7));
    public static final ItemPlates bronzePlate = createItem(new ItemPlates(bronze, 8));

    public static final ItemCrushedOre ironCrushedOre = createItem(new ItemCrushedOre(iron, 0));
    public static final ItemCrushedOre goldCrushedOre = createItem(new ItemCrushedOre(gold, 1));
    public static final ItemCrushedOre copperCrushedOre = createItem(new ItemCrushedOre(copper, 2));
    public static final ItemCrushedOre tinCrushedOre = createItem(new ItemCrushedOre(tin, 3));
    public static final ItemCrushedOre silverCrushedOre = createItem(new ItemCrushedOre(silver, 4));
    public static final ItemCrushedOre leadCrushedOre = createItem(new ItemCrushedOre(lead, 5));
    public static final ItemCrushedOre uraniumCrushedOre = createItem(new ItemCrushedOre(uranium, 6));

    public static final ItemPurifiedCrushedOre ironPurifiedCrushedOre = createItem(new ItemPurifiedCrushedOre(iron, 0));
    public static final ItemPurifiedCrushedOre goldPurifiedCrushedOre = createItem(new ItemPurifiedCrushedOre(gold, 1));
    public static final ItemPurifiedCrushedOre copperPurifiedCrushedOre = createItem(new ItemPurifiedCrushedOre(copper, 2));
    public static final ItemPurifiedCrushedOre tinPurifiedCrushedOre = createItem(new ItemPurifiedCrushedOre(tin, 3));
    public static final ItemPurifiedCrushedOre silverPurifiedCrushedOre = createItem(new ItemPurifiedCrushedOre(silver, 4));
    public static final ItemPurifiedCrushedOre leadPurifiedCrushedOre = createItem(new ItemPurifiedCrushedOre(lead, 5));
    public static final ItemPurifiedCrushedOre uraniumPurifiedCrushedOre = createItem(new ItemPurifiedCrushedOre(uranium, 6));

    public static final ItemTinyDust ironTinyDust = createItem(new ItemTinyDust(iron, 0));
    public static final ItemTinyDust goldTinyDust = createItem(new ItemTinyDust(gold, 1));
    public static final ItemTinyDust copperTinyDust = createItem(new ItemTinyDust(copper, 2));
    public static final ItemTinyDust tinTinyDust = createItem(new ItemTinyDust(tin, 3));
    public static final ItemTinyDust silverTinyDust = createItem(new ItemTinyDust(silver, 4));
    public static final ItemTinyDust leadTinyDust = createItem(new ItemTinyDust(lead, 5));
    public static final ItemTinyDust uranium235TinyDust = createItem(new ItemTinyDust(uranium + "235", 6));
    public static final ItemTinyDust obsidianTinyDust = createItem(new ItemTinyDust("obsidian", 7));
    public static final ItemTinyDust bronzeTinyDust = createItem(new ItemTinyDust(bronze, 8));
    public static final ItemTinyDust uranium238TinyDust = createItem(new ItemTinyDust(uranium + "238", 9));
    public static final ItemTinyDust plutoniumTinyDust = createItem(new ItemTinyDust("plutonium", 10));
    public static final ItemTinyDust thorium232TinyDust = createItem(new ItemTinyDust("thorium232", 11));
    public static final ItemTinyDust thorium230TinyDust = createItem(new ItemTinyDust("thorium230", 12));

    public static final ItemSmallDust ironSmallDust = createItem(new ItemSmallDust(iron, 0));
    public static final ItemSmallDust goldSmallDust = createItem(new ItemSmallDust(gold, 1));
    public static final ItemSmallDust copperSmallDust = createItem(new ItemSmallDust(copper, 2));
    public static final ItemSmallDust tinSmallDust = createItem(new ItemSmallDust(tin, 3));
    public static final ItemSmallDust silverSmallDust = createItem(new ItemSmallDust(silver, 4));
    public static final ItemSmallDust leadSmallDust = createItem(new ItemSmallDust(lead, 5));
    public static final ItemSmallDust uranium235SmallDust = createItem(new ItemSmallDust(uranium + "235", 6));
    public static final ItemSmallDust obsidianSmallDust = createItem(new ItemSmallDust("obsidian", 7));
    public static final ItemSmallDust bronzeSmallDust = createItem(new ItemSmallDust(bronze, 8));
    public static final ItemSmallDust uranium238SmallDust = createItem(new ItemSmallDust(uranium + "238", 9));
    public static final ItemSmallDust plutoniumSmallDust = createItem(new ItemSmallDust("plutonium", 10));

    public static final ItemMiscs leadIngot = createItem(new ItemMiscs("leadIngot", 0));
    public static final ItemMiscs leadDust = createItem(new ItemMiscs("leadDust", 1));
    public static final ItemMiscs stoneDust = createItem(new ItemMiscs("stoneDust", 2));
    public static final ItemMiscs slag = createItem(new ItemMiscs("slag", 3));
    public static final ItemMiscs uranium235 = createItem(new ItemMiscs("uranium235", 4));
    public static final ItemMiscs uranium238 = createItem(new ItemMiscs("uranium238", 5));
    public static final ItemMiscs plutoniumDust = createItem(new ItemMiscs("plutoniumDust", 6));
    public static final ItemMiscs coil = createItem(new ItemMiscs("coil", 7));
    public static final ItemMiscs heatConductor = createItem(new ItemMiscs("heatConductor", 8));
    public static final ItemMiscs steelIngot = createItem(new ItemMiscs("steelIngot", 9));
    public static final ItemMiscs oxidizedUraniumIngot = createItem(new ItemMiscs("oxidizedUraniumIngot", 10));
    public static final ItemMiscs doubleEnrichedUraniumIngot = createItem(new ItemMiscs("doubleEnrichedUraniumIngot", 11));
    public static final ItemMiscs iridiumShard = createItem(new ItemMiscs("iridiumShard", 12));
    public static final ItemMiscs diamondDust = createItem(new ItemMiscs("diamondDust", 13));
    public static final ItemMiscs energiumDust = createItem(new ItemMiscs("energiumDust", 14));
    public static final ItemMiscs emptyFuelRod = createItem(new ItemMiscs("emptyFuelRod", 15));
    public static final ItemMiscs thorium232Dust = createItem(new ItemMiscs("thorium232Dust", 16));
    public static final ItemMiscs thorium232Ingot = createItem(new ItemMiscs("thorium232Ingot", 17));
    public static final ItemMiscs thorium230Dust = createItem(new ItemMiscs("thorium230Dust", 18));
    public static final ItemMiscs thorium230Ingot = createItem(new ItemMiscs("thorium230Ingot", 19));
    public static final ItemMiscs plutoniumIngot = createItem(new ItemMiscs("plutoniumIngot", 20));
    public static final ItemMiscs moxFuel = createItem(new ItemMiscs("moxFuel", 21));
    public static final ItemMiscs denseIronPlate = createItem(new ItemMiscs("denseIronPlate", 9, "plates"));
    public static final ItemMiscs blankPress = createItem(new ItemMiscs("blankPress", 0, "presses"));
    public static final ItemMiscs rollingPress = createItem(new ItemMiscs("rollingPress", 1, "presses"));
    public static final ItemMiscs extrudingPress = createItem(new ItemMiscs("extrudingPress", 2, "presses"));
    public static final ItemMiscs cuttingPress = createItem(new ItemMiscs("cuttingPress", 3, "presses"));
    public static final ItemMiscs lathingPress = createItem(new ItemMiscs("lathingPress", 4, "presses"));
    public static final ItemMiscs gearingPress = createItem(new ItemMiscs("gearingPress", 5, "presses"));
    public static final ItemMiscs nickelCrushedOre = createItem(new ItemMiscs("nickelCrushedOre", 0, "nickel"));
    public static final ItemMiscs aluminumCrushedOre = createItem(new ItemMiscs("aluminumCrushedOre", 0, "aluminum"));
    public static final ItemMiscs platinumCrushedOre = createItem(new ItemMiscs("platinumCrushedOre", 0, "platinum"));
    public static final ItemMiscs nickelPurifiedCrushedOre = createItem(new ItemMiscs("nickelPurifiedCrushedOre", 1, "nickel"));
    public static final ItemMiscs aluminumPurifiedCrushedOre = createItem(new ItemMiscs("aluminumPurifiedCrushedOre", 1, "aluminum"));
    public static final ItemMiscs platinumPurifiedCrushedOre = createItem(new ItemMiscs("platinumPurifiedCrushedOre", 1, "platinum"));
    public static final ItemMiscs nickelTinyDust = createItem(new ItemMiscs("nickelTinyDust", 2, "nickel"));
    public static final ItemMiscs aluminumTinyDust = createItem(new ItemMiscs("aluminumTinyDust", 2, "aluminum"));
    public static final ItemMiscs platinumTinyDust = createItem(new ItemMiscs("platinumTinyDust", 2, "platinum"));

    public static final ItemDepeletedNuclearRods nearDepletedUOXCell = createItem(new ItemDepeletedNuclearRods("nearDepletedUOXCell", 15));
    public static final ItemDepeletedNuclearRods nearDepletedPlutoniumCell = createItem(new ItemDepeletedNuclearRods("nearDepletedPlutoniumCell", 16));
    public static final ItemDepeletedNuclearRods nearDepletedMOXCell = createItem(new ItemDepeletedNuclearRods("nearDepletedMOXCell", 17));
    public static final ItemDepeletedNuclearRods nearDepletedThorium232Cell = createItem(new ItemDepeletedNuclearRods("nearDepletedThorium232Cell", 18));
    public static final ItemDepeletedNuclearRods nearDepletedThorium230Cell = createItem(new ItemDepeletedNuclearRods("nearDepletedThorium230Cell", 19));
    public static final ItemDepeletedNuclearRods reEnrichedUOXCell = createItem(new ItemDepeletedNuclearRods("reEnrichedUOXCell", 25));
    public static final ItemDepeletedNuclearRods reEnrichedPlutoniumCell = createItem(new ItemDepeletedNuclearRods("reEnrichedPlutoniumCell", 26));
    public static final ItemDepeletedNuclearRods reEnrichedMOXCell = createItem(new ItemDepeletedNuclearRods("reEnrichedMOXCell", 27));
    public static final ItemDepeletedNuclearRods reEnrichedThorium232Cell = createItem(new ItemDepeletedNuclearRods("reEnrichedThorium232Cell", 28));
    public static final ItemDepeletedNuclearRods reEnrichedThorium230Cell = createItem(new ItemDepeletedNuclearRods("reEnrichedThorium230Cell", 29));

    public static final ItemRTG plutoniumRTG = createItem(new ItemRTG("plutoniumRTG", 129600, 1));
    public static final ItemRTG thoriumRTG = createItem(new ItemRTG("thoriumRTG", 172800, 0));

    public static final ItemNuclearRod singleUOXCell = createItem(new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.UOX));
    public static final ItemNuclearRod doubleUOXCell = createItem(new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.UOX));
    public static final ItemNuclearRod quadUOXCell = createItem(new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.UOX));
    public static final ItemNuclearRod singlePlutoniumCell = createItem(new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.PLUTONIUM));
    public static final ItemNuclearRod doublePlutoniumCell = createItem(new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.PLUTONIUM));
    public static final ItemNuclearRod quadPlutoniumCell = createItem(new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.PLUTONIUM));
    public static final ItemNuclearRod singleMOXCell = createItem(new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.MOX));
    public static final ItemNuclearRod doubleMOXCell = createItem(new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.MOX));
    public static final ItemNuclearRod quadMOXCell = createItem(new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.MOX));
    public static final ItemNuclearRod singleThorium232Cell = createItem(new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.THORIUM232));
    public static final ItemNuclearRod doubleThorium232Cell = createItem(new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.THORIUM232));
    public static final ItemNuclearRod quadThorium232Cell = createItem(new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.THORIUM232));
    public static final ItemNuclearRod singleThorium230Cell = createItem(new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.THORIUM230));
    public static final ItemNuclearRod doubleThorium230Cell = createItem(new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.THORIUM230));
    public static final ItemNuclearRod quadThorium230Cell = createItem(new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.THORIUM230));

    public static final ItemIsotopicRod isotopicUOXCell = createItem(new ItemIsotopicRod(NuclearRodVariants.UOX));
    public static final ItemIsotopicRod isotopicPlutoniumCell = createItem(new ItemIsotopicRod(NuclearRodVariants.PLUTONIUM));
    public static final ItemIsotopicRod isotopicMOXCell = createItem(new ItemIsotopicRod(NuclearRodVariants.MOX));
    public static final ItemIsotopicRod isotopicThorium232Cell = createItem(new ItemIsotopicRod(NuclearRodVariants.THORIUM232));
    public static final ItemIsotopicRod isotopicThorium230Cell = createItem(new ItemIsotopicRod(NuclearRodVariants.THORIUM230));


    public static final ItemToolCrafting craftingHammer = createItem(new ItemToolCrafting(256, "craftingHammer", 0, true));
    public static final ItemToolCutter wireCutters = createItem(new ItemToolCutter(256, "wireCutters", 1, true));


    public static <T extends Item> T createItem(T item) {
        toRegisterItem.add(item);
        return item;
    }

    public static void register() {
        for (Block block : toRegisterBlock) {
            createBlock(block);
        }
        ItemNuclearRod.init();
        for (Item item : toRegisterItem) {
            IC2.getInstance().createItem(item);
        }
    }

    static <T extends Block> T registerBlock(T block) {
        toRegisterBlock.add(block);
        return block;
    }

    public static void createBlock(Block block) {
        IC2.getInstance().createBlock(block, getItemBlock(block));
    }

    static Class<? extends ItemBlockRare> getItemBlock(Block block) {
        if (block instanceof BlockMachine) {
            return ItemBlockMachine.class;
        } else {
            return block instanceof BlockIc2cEGenerator ? ItemBlockGenerator.class : ItemBlockMetal.class;
        }
    }

    public static void registerTiles() {
        GameRegistry.registerTileEntity(TileEntityOreWashingPlant.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityOreWashingPlant"));
        GameRegistry.registerTileEntity(TileEntityAdvancedSteamTurbine.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityAdvancedSteamTurbine"));
        GameRegistry.registerTileEntity(TileEntityThermalCentrifuge.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityThermalCentrifuge"));
        GameRegistry.registerTileEntity(TileEntityThermalWasher.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityThermalWasher"));
        GameRegistry.registerTileEntity(TileEntityRoller.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityRoller"));
        GameRegistry.registerTileEntity(TileEntityExtruder.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityExtruder"));
        GameRegistry.registerTileEntity(TileEntityCutter.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityCutter"));
        GameRegistry.registerTileEntity(TileEntityImpellerizedRoller.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityImpellerizedRoller"));
        GameRegistry.registerTileEntity(TileEntityLiquescentExtruder.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityLiquescentExtruder"));
        GameRegistry.registerTileEntity(TileEntityPlasmaCutter.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityPlasmaCutter"));
        GameRegistry.registerTileEntity(TileEntityMetalBender.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityMetalBender"));
        GameRegistry.registerTileEntity(TileEntityFluidCanningMachine.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityFluidCanningMachine"));
        GameRegistry.registerTileEntity(TileEntityTreeTapper.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityTreeTapper"));
        GameRegistry.registerTileEntity(TileEntityElectricDisenchanter.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityElectricDisenchanter"));
        GameRegistry.registerTileEntity(TileEntityThermoElectricGenerator.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityThermoElectricGenerator"));
        GameRegistry.registerTileEntity(TileEntityThermoElectricGenerator.TileEntityThermoElectricGeneratorMkII.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityThermoElectricGeneratorMkII"));
    }
}
