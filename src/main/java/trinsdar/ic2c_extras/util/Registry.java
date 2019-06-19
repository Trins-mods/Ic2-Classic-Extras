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
import trinsdar.ic2c_extras.blocks.BlockStoneDust;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityAdvancedSteamTurbine;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityCutter;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityExtruder;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityFluidCanningMachine;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityImpellerizedRoller;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityLiquescentExtruder;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityMetalBender;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityPlasmaCutter;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityRoller;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalWasher;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityTreeTapper;
import trinsdar.ic2c_extras.items.ItemCasings;
import trinsdar.ic2c_extras.items.ItemCrushedOre;
import trinsdar.ic2c_extras.items.ItemMiscs;
import trinsdar.ic2c_extras.items.ItemNuclearRod;
import trinsdar.ic2c_extras.items.ItemNuclearRod.NuclearRodTypes;
import trinsdar.ic2c_extras.items.ItemNuclearRod.NuclearRodVariants;
import trinsdar.ic2c_extras.items.ItemPlates;
import trinsdar.ic2c_extras.items.ItemPurifiedCrushedOre;
import trinsdar.ic2c_extras.items.ItemSmallDust;
import trinsdar.ic2c_extras.items.ItemTinyDust;
import trinsdar.ic2c_extras.items.ItemToolCrafting;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockGenerator;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockMachine;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockMetal;

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
    blastFurnace = new BlockMachine("blastFurnace"),
    metalBender = new BlockMachine("metalBender"),
    fluidCanningMachine = new BlockMachine("fluidCanningMachine"),
    treeTapper = new BlockMachine("treeTapper");
    public static final BlockIc2cEGenerator
    advancedSteamTurbine = new BlockIc2cEGenerator("advancedSteamTurbine"),
    solidFuelFirebox = new BlockIc2cEGenerator("solidFuelFirebox"),
    liquidFuelFirebox = new BlockIc2cEGenerator("liquidFuelFirebox"),
    electricHeater = new BlockIc2cEGenerator("electricHeater");
    public static final BlockMetal
    steelBlock = new BlockMetal("steelBlock", 0),
    refinedIronBlock = new BlockMetal("refinedIronBlock", 1),
    leadBlock = new BlockMetal("leadBlock", 2);
    public static final BlockStoneDust stoneDustBlock = new BlockStoneDust();

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
    copperCasing = new ItemCasings(copper, 0),
    tinCasing = new ItemCasings(tin, 1),
    silverCasing = new ItemCasings(silver, 2),
    leadCasing = new ItemCasings(lead, 3),
    ironCasing = new ItemCasings(iron, 4),
    goldCasing = new ItemCasings(gold, 5),
    refinedIronCasing = new ItemCasings("refinedIron", 6),
    steelCasing = new ItemCasings(steel, 7),
    bronzeCasing = new ItemCasings(bronze, 8);

    public static final ItemPlates
    copperPlate = new ItemPlates(copper, 0),
    tinPlate = new ItemPlates(tin, 1),
    silverPlate = new ItemPlates(silver, 2),
    leadPlate = new ItemPlates(lead, 3),
    ironPlate = new ItemPlates(iron, 4),
    goldPlate = new ItemPlates(gold, 5),
    refinedIronPlate = new ItemPlates("refinedIron", 6),
    steelPlate = new ItemPlates(steel, 7),
    bronzePlate = new ItemPlates(bronze, 8);

    public static final ItemCrushedOre
    ironCrushedOre = new ItemCrushedOre(iron, 0),
    goldCrushedOre = new ItemCrushedOre(gold, 1),
    copperCrushedOre = new ItemCrushedOre(copper, 2),
    tinCrushedOre = new ItemCrushedOre(tin, 3),
    silverCrushedOre = new ItemCrushedOre(silver, 4),
    leadCrushedOre = new ItemCrushedOre(lead, 5),
    uraniumCrushedOre = new ItemCrushedOre(uranium, 6);

    public static final ItemPurifiedCrushedOre
    ironPurifiedCrushedOre = new ItemPurifiedCrushedOre(iron, 0),
    goldPurifiedCrushedOre = new ItemPurifiedCrushedOre(gold, 1),
    copperPurifiedCrushedOre = new ItemPurifiedCrushedOre(copper, 2),
    tinPurifiedCrushedOre = new ItemPurifiedCrushedOre(tin, 3),
    silverPurifiedCrushedOre = new ItemPurifiedCrushedOre(silver, 4),
    leadPurifiedCrushedOre = new ItemPurifiedCrushedOre(lead, 5),
    uraniumPurifiedCrushedOre = new ItemPurifiedCrushedOre(uranium, 6);

    public static final ItemTinyDust
    ironTinyDust = new ItemTinyDust(iron, 0),
    goldTinyDust = new ItemTinyDust(gold, 1),
    copperTinyDust = new ItemTinyDust(copper, 2),
    tinTinyDust = new ItemTinyDust(tin, 3),
    silverTinyDust = new ItemTinyDust(silver, 4),
    leadTinyDust = new ItemTinyDust(lead, 5),
    uranium235TinyDust = new ItemTinyDust(uranium + "235", 6),
    obsidianTinyDust = new ItemTinyDust("obsidian", 7),
    bronzeTinyDust = new ItemTinyDust(bronze, 8),
    uranium238TinyDust = new ItemTinyDust(uranium + "238", 9),
    plutoniumTinyDust = new ItemTinyDust("plutonium", 10);

    public static final ItemSmallDust
    ironSmallDust = new ItemSmallDust(iron, 0),
    goldSmallDust = new ItemSmallDust(gold, 1),
    copperSmallDust = new ItemSmallDust(copper, 2),
    tinSmallDust = new ItemSmallDust(tin, 3),
    silverSmallDust = new ItemSmallDust(silver, 4),
    leadSmallDust = new ItemSmallDust(lead, 5),
    uranium235SmallDust = new ItemSmallDust(uranium + "235", 6),
    obsidianSmallDust = new ItemSmallDust("obsidian", 7),
    bronzeSmallDust = new ItemSmallDust(bronze, 8),
    uranium238SmallDust = new ItemSmallDust(uranium + "238", 9),
    plutoniumSmallDust = new ItemSmallDust("plutonium", 10);

    public static final ItemMiscs
    leadIngot = new ItemMiscs("leadIngot", 0),
    leadDust = new ItemMiscs("leadDust", 1),
    stoneDust = new ItemMiscs("stoneDust", 2),
    slag = new ItemMiscs("slag", 3),
    uranium235 = new ItemMiscs("uranium235", 4),
    uranium238 = new ItemMiscs("uranium238", 5),
    plutonium = new ItemMiscs("plutonium", 6),
    coil = new ItemMiscs("coil", 7),
    heatConductor = new ItemMiscs("heatConductor", 8),
    steelIngot = new ItemMiscs("steelIngot", 9),
    oxidizedUraniumIngot = new ItemMiscs("oxidizedUraniumIngot", 10),
    doubleEnrichedUraniumIngot = new ItemMiscs("doubleEnrichedUraniumIngot", 11),
    iridiumShard = new ItemMiscs("iridiumShard", 12),
    diamondDust = new ItemMiscs("diamondDust", 16),
    energiumDust = new ItemMiscs("energiumDust", 17),
    emptyFuelRod = new ItemMiscs("emptyFuelRod", 18),
    nearDepletedUOXCell = new ItemMiscs("nearDepletedUOXCell", 15, "nuclear_cells"),
    nearDepletedPlutoniumCell = new ItemMiscs("nearDepletedPlutoniumCell", 16, "nuclear_cells"),
    nearDepletedMOXCell = new ItemMiscs("nearDepletedMOXCell", 17, "nuclear_cells"),
    nearDepletedThoriumCell = new ItemMiscs("nearDepletedThroiumCell", 18, "nuclear_cells"),
    nearDepletedThoriumOxideCell= new ItemMiscs("nearDepletedThoriumOxideCell", 19, "nuclear_cells"),
    reEnrichedUOXCell = new ItemMiscs("reEnrichedUOXCell", 25, "nuclear_cells"),
    reEnrichedPlutoniumCell = new ItemMiscs("reEnrichedPlutoniumCell", 26, "nuclear_cells"),
    reEnrichedMOXCell = new ItemMiscs("reEnrichedMOXCell", 27, "nuclear_cells"),
    reEnrichedThoriumCell = new ItemMiscs("reEnrichedThroiumCell", 28, "nuclear_cells"),
    reEnrichedThoriumOxideCell= new ItemMiscs("reEnrichedThoriumOxideCell", 29, "nuclear_cells"),
    blankPress = new ItemMiscs("blankPress", 0, "presses"),
    rollingPress = new ItemMiscs("rollingPress", 1, "presses"),
    extrudingPress = new ItemMiscs("extrudingPress", 2, "presses"),
    cuttingPress = new ItemMiscs("cuttingPress", 3, "presses"),
    lathingPress = new ItemMiscs("lathingPress", 4, "presses"),
    gearingPress = new ItemMiscs("gearingPress", 5, "presses");

    public static final ItemNuclearRod
    singleUOXCell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.UOX, 10),
    doubleUOXCell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.UOX, 5),
    quadUOXCell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.UOX, 0),
    isotopicUOXCell = new ItemNuclearRod(NuclearRodTypes.ISOTOPE, NuclearRodVariants.UOX, 20),
    singlePlutoniumCell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.PLUTONIUM, 11),
    doublePlutoniumCell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.PLUTONIUM, 6),
    quadPlutoniumCell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.PLUTONIUM, 1),
    isotopicPlutoniumCell = new ItemNuclearRod(NuclearRodTypes.ISOTOPE, NuclearRodVariants.PLUTONIUM, 21),
    singleMOXCell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.MOX, 12),
    doubleMOXCell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.MOX, 7),
    quadMOXCell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.MOX, 2),
    isotopicMOXCell = new ItemNuclearRod(NuclearRodTypes.ISOTOPE, NuclearRodVariants.MOX, 22),
    singleThoriumCell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.THORIUM, 13),
    doubleThoriumCell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.THORIUM, 8),
    quadThoriumCell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.THORIUM, 3),
    isotopicThoriumCell = new ItemNuclearRod(NuclearRodTypes.ISOTOPE, NuclearRodVariants.THORIUM, 23),
    singleThoriumOxideCell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.THORIUMOXIDE, 14),
    doubleThoriumOxideCell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.THORIUMOXIDE, 9),
    quadThoriumOxideCell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.THORIUMOXIDE, 4),
    isotopicThoriumOxideCell = new ItemNuclearRod(NuclearRodTypes.ISOTOPE, NuclearRodVariants.THORIUMOXIDE, 24);


    public static final ItemToolCrafting
    craftingHammer = new ItemToolCrafting(80, "craftingHammer", 0, true),
    wireCutters = new ItemToolCrafting(60, "wireCutters", 1, true);


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
        IC2.getInstance().createBlock(metalBender, ItemBlockMachine.class);
        IC2.getInstance().createBlock(fluidCanningMachine, ItemBlockMachine.class);
        IC2.getInstance().createBlock(treeTapper, ItemBlockMachine.class);
        IC2.getInstance().createBlock(blastFurnace, ItemBlockRare.class);
        IC2.getInstance().createBlock(advancedSteamTurbine, ItemBlockGenerator.class);
        IC2.getInstance().createBlock(solidFuelFirebox, ItemBlockRare.class);
        IC2.getInstance().createBlock(liquidFuelFirebox, ItemBlockRare.class);
        IC2.getInstance().createBlock(electricHeater, ItemBlockRare.class);
        IC2.getInstance().createBlock(steelBlock, ItemBlockMetal.class);
        IC2.getInstance().createBlock(refinedIronBlock, ItemBlockMetal.class);
        IC2.getInstance().createBlock(leadBlock, ItemBlockMetal.class);
        IC2.getInstance().createBlock(stoneDustBlock, ItemBlockMetal.class);

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
        IC2.getInstance().createItem(oxidizedUraniumIngot);
        IC2.getInstance().createItem(doubleEnrichedUraniumIngot);
        IC2.getInstance().createItem(iridiumShard);
        IC2.getInstance().createItem(copperPlate);
        IC2.getInstance().createItem(tinPlate);
        IC2.getInstance().createItem(silverPlate);
        IC2.getInstance().createItem(leadPlate);
        IC2.getInstance().createItem(ironPlate);
        IC2.getInstance().createItem(goldPlate);
        IC2.getInstance().createItem(refinedIronPlate);
        IC2.getInstance().createItem(steelPlate);
        IC2.getInstance().createItem(bronzePlate);
        IC2.getInstance().createItem(diamondDust);
        IC2.getInstance().createItem(energiumDust);
        IC2.getInstance().createItem(emptyFuelRod);
        IC2.getInstance().createItem(blankPress);
        IC2.getInstance().createItem(rollingPress);
        IC2.getInstance().createItem(extrudingPress);
        IC2.getInstance().createItem(cuttingPress);
        IC2.getInstance().createItem(lathingPress);
        IC2.getInstance().createItem(gearingPress);
        IC2.getInstance().createItem(craftingHammer);
        IC2.getInstance().createItem(wireCutters);

        IC2.getInstance().createItem(singleUOXCell);
        IC2.getInstance().createItem(doubleUOXCell);
        IC2.getInstance().createItem(quadUOXCell);
        IC2.getInstance().createItem(nearDepletedUOXCell);
        IC2.getInstance().createItem(isotopicUOXCell);
        IC2.getInstance().createItem(reEnrichedUOXCell);
        IC2.getInstance().createItem(singlePlutoniumCell);
        IC2.getInstance().createItem(doublePlutoniumCell);
        IC2.getInstance().createItem(quadPlutoniumCell);
        IC2.getInstance().createItem(nearDepletedPlutoniumCell);
        IC2.getInstance().createItem(isotopicPlutoniumCell);
        IC2.getInstance().createItem(reEnrichedPlutoniumCell);
        IC2.getInstance().createItem(singleMOXCell);
        IC2.getInstance().createItem(doubleMOXCell);
        IC2.getInstance().createItem(quadMOXCell);
        IC2.getInstance().createItem(nearDepletedMOXCell);
        IC2.getInstance().createItem(isotopicMOXCell);
        IC2.getInstance().createItem(reEnrichedMOXCell);
        IC2.getInstance().createItem(singleThoriumCell);
        IC2.getInstance().createItem(doubleThoriumCell);
        IC2.getInstance().createItem(quadThoriumCell);
        IC2.getInstance().createItem(nearDepletedThoriumCell);
        IC2.getInstance().createItem(isotopicThoriumCell);
        IC2.getInstance().createItem(reEnrichedThoriumCell);
        IC2.getInstance().createItem(singleThoriumOxideCell);
        IC2.getInstance().createItem(doubleThoriumOxideCell);
        IC2.getInstance().createItem(quadThoriumOxideCell);
        IC2.getInstance().createItem(nearDepletedThoriumOxideCell);
        IC2.getInstance().createItem(isotopicThoriumOxideCell);
        IC2.getInstance().createItem(reEnrichedThoriumOxideCell);
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
        GameRegistry.registerTileEntity(TileEntityMetalBender.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityMetalBender"));
        GameRegistry.registerTileEntity(TileEntityFluidCanningMachine.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityFluidCanningMachine"));
        GameRegistry.registerTileEntity(TileEntityTreeTapper.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityTreeTapper"));
    }
}
