package trinsdar.ic2c_extras.util;

import ic2.core.IC2;
import ic2.core.item.block.ItemBlockRare;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.blocks.BlockIc2cEGenerator;
import trinsdar.ic2c_extras.blocks.BlockMachine;
import trinsdar.ic2c_extras.blocks.BlockMetal;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityAdvancedSteamTurbine;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityCutter;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityExtruder;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityImpellerizedRoller;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityLiquescentExtruder;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityPlasmaCutter;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityRoller;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalWasher;
import trinsdar.ic2c_extras.gtintegration.MaterialGen;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockGenerator;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockMachine;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockMetal;
import trinsdar.ic2c_extras.items.ItemCasings;
import trinsdar.ic2c_extras.items.ItemCrushedOre;
import trinsdar.ic2c_extras.items.ItemMiscs;
import trinsdar.ic2c_extras.items.ItemPurifiedCrushedOre;
import trinsdar.ic2c_extras.items.ItemSmallDust;
import trinsdar.ic2c_extras.items.ItemTinyDust;
import trinsdar.ic2c_extras.items.ItemToolCrafting;

public class Registry
{
    public static final BlockMachine
    oreWashingPlant = new BlockMachine("oreWashingPlant"),
    thermalCentrifuge = new BlockMachine("thermalCentrifuge"),
    thermalWasher = new BlockMachine("thermalWasher"),
    roller = new BlockMachine("roller"),
    extruder = new BlockMachine("extruder"),
    cutter = new BlockMachine("cutter"),
    impellerizedRoller = new BlockMachine("impellerizedRoller"),
    liquescentExtruder = new BlockMachine("liquescentExtruder"),
    plasmaCutter = new BlockMachine("plasmaCutter"),
    blastFurnace = new BlockMachine("blastFurnace");
    public static final BlockIc2cEGenerator
    advancedSteamTurbine = new BlockIc2cEGenerator("advancedSteamTurbine"),
    solidFuelFirebox = new BlockIc2cEGenerator("solidFuelFirebox"),
    liquidFuelFirebox = new BlockIc2cEGenerator("liquidFuelFirebox"),
    electricHeater = new BlockIc2cEGenerator("electricHeater");
    public static final BlockMetal
    steelBlock = new BlockMetal("steelBlock", 0),
    refinedIronBlock = new BlockMetal("refinedIronBlock", 1),
    leadBlock = new BlockMetal("leadBlock", 2);

    static String copper = "copper";
    static String tin = "tin";
    static String silver = "silver";
    static String lead = "lead";
    static String iron = "iron";
    static String gold = "gold";
    static String steel = "steel";
    static String bronze = "bronze";
    static String uranium = "uranium";

    public static final ItemCasings
            copperCasing = new ItemCasings(copper, 48),
            tinCasing = new ItemCasings(tin, 49),
            silverCasing = new ItemCasings(silver, 50),
            leadCasing = new ItemCasings(lead, 51),
            ironCasing = new ItemCasings(iron, 52),
            goldCasing = new ItemCasings(gold, 53),
            refinedIronCasing = new ItemCasings("refinedIron", 54),
            steelCasing = new ItemCasings(steel, 55),
            bronzeCasing = new ItemCasings(bronze, 56);

    public static final ItemCrushedOre
            ironCrushedOre = new ItemCrushedOre(iron, 0),
            goldCrushedOre = new ItemCrushedOre(gold, 1),
            copperCrushedOre = new ItemCrushedOre(copper, 2),
            tinCrushedOre = new ItemCrushedOre(tin, 3),
            silverCrushedOre = new ItemCrushedOre(silver, 4),
            leadCrushedOre = new ItemCrushedOre(lead, 5),
            uraniumCrushedOre = new ItemCrushedOre(uranium, 6);

    public static final ItemPurifiedCrushedOre
            ironPurifiedCrushedOre = new ItemPurifiedCrushedOre(iron, 16),
            goldPurifiedCrushedOre = new ItemPurifiedCrushedOre(gold, 17),
            copperPurifiedCrushedOre = new ItemPurifiedCrushedOre(copper, 18),
            tinPurifiedCrushedOre = new ItemPurifiedCrushedOre(tin, 19),
            silverPurifiedCrushedOre = new ItemPurifiedCrushedOre(silver, 20),
            leadPurifiedCrushedOre = new ItemPurifiedCrushedOre(lead, 21),
            uraniumPurifiedCrushedOre = new ItemPurifiedCrushedOre(uranium, 22);

    public static final ItemTinyDust
            ironTinyDust = new ItemTinyDust(iron, 32),
            goldTinyDust = new ItemTinyDust(gold, 33),
            copperTinyDust = new ItemTinyDust(copper, 34),
            tinTinyDust = new ItemTinyDust(tin, 35),
            silverTinyDust = new ItemTinyDust(silver, 36),
            leadTinyDust = new ItemTinyDust(lead, 37),
            uranium235TinyDust = new ItemTinyDust(uranium + "235", 38),
            obsidianTinyDust = new ItemTinyDust("obsidian", 39),
            bronzeTinyDust = new ItemTinyDust(bronze, 40),
            uranium238TinyDust = new ItemTinyDust(uranium + "238", 41),
            plutoniumTinyDust = new ItemTinyDust("plutonium", 42);

    public static final ItemSmallDust
            ironSmallDust = new ItemSmallDust(iron, 80),
            goldSmallDust = new ItemSmallDust(gold, 81),
            copperSmallDust = new ItemSmallDust(copper, 82),
            tinSmallDust = new ItemSmallDust(tin, 83),
            silverSmallDust = new ItemSmallDust(silver, 84),
            leadSmallDust = new ItemSmallDust(lead, 85),
            uranium235SmallDust = new ItemSmallDust(uranium + "235", 86),
            obsidianSmallDust = new ItemSmallDust("obsidian", 87),
            bronzeSmallDust = new ItemSmallDust(bronze, 88),
            uranium238SmallDust = new ItemSmallDust(uranium + "238", 89),
            plutoniumSmallDust = new ItemSmallDust("plutonium", 90);

    public static final ItemMiscs
            leadIngot = new ItemMiscs("leadIngot", 64),
            leadDust = new ItemMiscs("leadDust", 65),
            stoneDust = new ItemMiscs("stoneDust", 66),
            slag = new ItemMiscs("slag", 67),
            uranium235 = new ItemMiscs("uranium235", 68),
            uranium238 = new ItemMiscs("uranium238", 69),
            plutonium = new ItemMiscs("plutonium", 70),
            coil = new ItemMiscs("coil", 71),
            heatConductor = new ItemMiscs("heatConductor", 72),
            steelIngot = new ItemMiscs("steelIngot", 73),
            plutoniumEnrichedUraniumIngot = new ItemMiscs("plutoniumEnrichedUraniumIngot", 74),
            doubleEnrichedUraniumIngot = new ItemMiscs("doubleEnrichedUraniumIngot", 75),
            iridiumShard = new ItemMiscs("iridiumShard", 76),
            refinedIronPlate = new ItemMiscs("refinedIronPlate", 79),
            bronzePlate = new ItemMiscs("bronzePlate", 57),
            steelPlate = new ItemMiscs("steelPlate", 58),
            diamondDust = new ItemMiscs("diamondDust", 43),
            energiumDust = new ItemMiscs("energiumDust", 44);


    public static final ItemToolCrafting
            craftingHammer = new ItemToolCrafting(80, "craftingHammer", 77, true),
            wireCutters = new ItemToolCrafting(60, "wireCutters", 78, true);


    public static void init(){
        IC2.getInstance().createBlock(oreWashingPlant, ItemBlockMachine.class);
        IC2.getInstance().createBlock(thermalCentrifuge, ItemBlockMachine.class);
        IC2.getInstance().createBlock(thermalWasher, ItemBlockMachine.class);
        IC2.getInstance().createBlock(roller, ItemBlockMachine.class);
        IC2.getInstance().createBlock(extruder, ItemBlockMachine.class);
        IC2.getInstance().createBlock(cutter, ItemBlockMachine.class);
        IC2.getInstance().createBlock(impellerizedRoller, ItemBlockMachine.class);
        IC2.getInstance().createBlock(liquescentExtruder, ItemBlockMachine.class);
        IC2.getInstance().createBlock(plasmaCutter, ItemBlockMachine.class);
        IC2.getInstance().createBlock(blastFurnace, ItemBlockRare.class);
        IC2.getInstance().createBlock(advancedSteamTurbine, ItemBlockGenerator.class);
        IC2.getInstance().createBlock(solidFuelFirebox, ItemBlockRare.class);
        IC2.getInstance().createBlock(liquidFuelFirebox, ItemBlockRare.class);
        IC2.getInstance().createBlock(electricHeater, ItemBlockRare.class);
        IC2.getInstance().createBlock(steelBlock, ItemBlockMetal.class);
        IC2.getInstance().createBlock(refinedIronBlock, ItemBlockMetal.class);
        IC2.getInstance().createBlock(leadBlock, ItemBlockMetal.class);

        IC2.getInstance().createItem(copperCasing);
        IC2.getInstance().createItem(tinCasing);
        IC2.getInstance().createItem(silverCasing);
        IC2.getInstance().createItem(leadCasing);
        IC2.getInstance().createItem(ironCasing);
        IC2.getInstance().createItem(goldCasing);
        IC2.getInstance().createItem(refinedIronCasing);
        IC2.getInstance().createItem(steelCasing);
        IC2.getInstance().createItem(bronzeCasing);
        IC2.getInstance().createItem(ironCrushedOre);
        IC2.getInstance().createItem(goldCrushedOre);
        IC2.getInstance().createItem(copperCrushedOre);
        IC2.getInstance().createItem(tinCrushedOre);
        IC2.getInstance().createItem(silverCrushedOre);
        IC2.getInstance().createItem(leadCrushedOre);
        IC2.getInstance().createItem(uraniumCrushedOre);
        IC2.getInstance().createItem(ironPurifiedCrushedOre);
        IC2.getInstance().createItem(goldPurifiedCrushedOre);
        IC2.getInstance().createItem(copperPurifiedCrushedOre);
        IC2.getInstance().createItem(tinPurifiedCrushedOre);
        IC2.getInstance().createItem(silverPurifiedCrushedOre);
        IC2.getInstance().createItem(leadPurifiedCrushedOre);
        IC2.getInstance().createItem(uraniumPurifiedCrushedOre);
        IC2.getInstance().createItem(ironTinyDust);
        IC2.getInstance().createItem(goldTinyDust);
        IC2.getInstance().createItem(copperTinyDust);
        IC2.getInstance().createItem(tinTinyDust);
        IC2.getInstance().createItem(silverTinyDust);
        IC2.getInstance().createItem(leadTinyDust);
        IC2.getInstance().createItem(uranium235TinyDust);
        IC2.getInstance().createItem(obsidianTinyDust);
        IC2.getInstance().createItem(bronzeTinyDust);
        IC2.getInstance().createItem(uranium238TinyDust);
        IC2.getInstance().createItem(plutoniumTinyDust);
        IC2.getInstance().createItem(ironSmallDust);
        IC2.getInstance().createItem(goldSmallDust);
        IC2.getInstance().createItem(copperSmallDust);
        IC2.getInstance().createItem(tinSmallDust);
        IC2.getInstance().createItem(silverSmallDust);
        IC2.getInstance().createItem(leadSmallDust);
        IC2.getInstance().createItem(uranium235SmallDust);
        IC2.getInstance().createItem(obsidianSmallDust);
        IC2.getInstance().createItem(bronzeSmallDust);
        IC2.getInstance().createItem(uranium238SmallDust);
        IC2.getInstance().createItem(plutoniumSmallDust);
        IC2.getInstance().createItem(leadIngot);
        IC2.getInstance().createItem(leadDust);
        IC2.getInstance().createItem(stoneDust);
        IC2.getInstance().createItem(slag);
        IC2.getInstance().createItem(uranium235);
        IC2.getInstance().createItem(uranium238);
        IC2.getInstance().createItem(plutonium);
        IC2.getInstance().createItem(coil);
        IC2.getInstance().createItem(heatConductor);
        IC2.getInstance().createItem(steelIngot);
        IC2.getInstance().createItem(plutoniumEnrichedUraniumIngot);
        IC2.getInstance().createItem(doubleEnrichedUraniumIngot);
        IC2.getInstance().createItem(iridiumShard);
        IC2.getInstance().createItem(refinedIronPlate);
        IC2.getInstance().createItem(bronzePlate);
        IC2.getInstance().createItem(steelPlate);
        IC2.getInstance().createItem(diamondDust);
        IC2.getInstance().createItem(energiumDust);
        IC2.getInstance().createItem(craftingHammer);
        IC2.getInstance().createItem(wireCutters);
    }

    public static void initMaterials(){
        for (Item item : MaterialGen.itemMap.values()) {
            IC2.getInstance().createItem(item);
        }
    }

    public static void registerTiles()
    {
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
    }
}
