package trinsdar.ic2c_extras.util;

import ic2.api.crops.CropCard;
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
import trinsdar.ic2c_extras.blocks.CropPlumbilia;
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
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermoElectricGenerator;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityTreeTapper;
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
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockGenerator;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockMachine;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockMetal;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.RodLang;

public class Registry
{
    public static final BlockMachine
    oreWashingPlant = new BlockMachine("oreWashingPlant", Ic2cExtrasLang.oreWashingPlant),
    thermalCentrifuge = new BlockMachine("thermalCentrifuge", Ic2cExtrasLang.thermalCentrifuge),
    thermalWasher = new BlockMachine("thermalWasher", Ic2cExtrasLang.thermalWasher),
    roller = new BlockMachine("roller", Ic2cExtrasLang.roller),
    extruder = new BlockMachine("extruder", Ic2cExtrasLang.extruder),
    cutter = new BlockMachine("cutter", Ic2cExtrasLang.cutter),
    impellerizedRoller = new BlockMachine("impellerizedRoller", Ic2cExtrasLang.impellerizedRoller),
    liquescentExtruder = new BlockMachine("liquescentExtruder", Ic2cExtrasLang.liquescentExtruder),
    plasmaCutter = new BlockMachine("plasmaCutter", Ic2cExtrasLang.plasmaCutter),
    metalBender = new BlockMachine("metalBender", Ic2cExtrasLang.metalBender),
    fluidCanningMachine = new BlockMachine("fluidCanningMachine", Ic2cExtrasLang.fluidCanningMachine),
    treeTapper = new BlockMachine("treeTapper", Ic2cExtrasLang.treeTapper);
    public static final BlockIc2cEGenerator
    advancedSteamTurbine = new BlockIc2cEGenerator("advancedSteamTurbine", Ic2cExtrasLang.advancedSteamTurbine),
    thermoElectricGenerator = new BlockIc2cEGenerator("thermoElectricGenerator", Ic2cExtrasLang.thermoElectricGenerator),
    thermoElectricGeneratorMKII = new BlockIc2cEGenerator("thermoElectricGeneratorMkII", Ic2cExtrasLang.thermoElectricGeneratorMkII);
    public static final BlockMetal
    steelBlock = new BlockMetal("steelBlock", 0),
    refinedIronBlock = new BlockMetal("refinedIronBlock",  1),
    leadBlock = new BlockMetal("leadBlock",  2);
    public static final BlockStoneDust stoneDustBlock = new BlockStoneDust();

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
    plutoniumTinyDust = new ItemTinyDust("plutonium", 10),
    thorium232TinyDust = new ItemTinyDust("thorium232", 11),
    thorium230TinyDust = new ItemTinyDust("thorium230", 12);

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
    plutoniumDust = new ItemMiscs("plutoniumDust", 6),
    coil = new ItemMiscs("coil", 7),
    heatConductor = new ItemMiscs("heatConductor", 8),
    steelIngot = new ItemMiscs("steelIngot", 9),
    oxidizedUraniumIngot = new ItemMiscs("oxidizedUraniumIngot", 10),
    doubleEnrichedUraniumIngot = new ItemMiscs("doubleEnrichedUraniumIngot", 11),
    iridiumShard = new ItemMiscs("iridiumShard", 12),
    diamondDust = new ItemMiscs("diamondDust", 13),
    energiumDust = new ItemMiscs("energiumDust", 14),
    emptyFuelRod = new ItemMiscs("emptyFuelRod", 15),
    thorium232Dust = new ItemMiscs("thorium232Dust", 16),
    thorium232Ingot = new ItemMiscs("thorium232Ingot", 17),
    thorium230Dust = new ItemMiscs("thorium230Dust", 18),
    thorium230Ingot = new ItemMiscs("thorium230Ingot", 19),
    plutoniumIngot = new ItemMiscs("plutoniumIngot", 20),
    moxFuel = new ItemMiscs("moxFuel", 21),
    denseIronPlate = new ItemMiscs("denseIronPlate", 9, "plates"),
    blankPress = new ItemMiscs("blankPress", 0, "presses"),
    rollingPress = new ItemMiscs("rollingPress", 1, "presses"),
    extrudingPress = new ItemMiscs("extrudingPress", 2, "presses"),
    cuttingPress = new ItemMiscs("cuttingPress", 3, "presses"),
    lathingPress = new ItemMiscs("lathingPress", 4, "presses"),
    gearingPress = new ItemMiscs("gearingPress", 5, "presses");

    public static final ItemDepeletedNuclearRods
    nearDepletedUOXCell = new ItemDepeletedNuclearRods("nearDepletedUOXCell", RodLang.nearDepletedUOXCell, 15),
    nearDepletedPlutoniumCell = new ItemDepeletedNuclearRods("nearDepletedPlutoniumCell", RodLang.nearDepletedPlutoniumCell,  16),
    nearDepletedMOXCell = new ItemDepeletedNuclearRods("nearDepletedMOXCell", RodLang.nearDepletedMOXCell, 17),
    nearDepletedThorium232Cell = new ItemDepeletedNuclearRods("nearDepletedThorium232Cell", RodLang.nearDepletedThorium232Cell, 18),
    nearDepletedThorium230Cell= new ItemDepeletedNuclearRods("nearDepletedThorium230Cell", RodLang.nearDepletedThorium230Cell, 19),
    reEnrichedUOXCell = new ItemDepeletedNuclearRods("reEnrichedUOXCell", RodLang.reEnrichedUOXCell, 25),
    reEnrichedPlutoniumCell = new ItemDepeletedNuclearRods("reEnrichedPlutoniumCell", RodLang.reEnrichedPlutoniumCell, 26),
    reEnrichedMOXCell = new ItemDepeletedNuclearRods("reEnrichedMOXCell", RodLang.reEnrichedMOXCell, 27),
    reEnrichedThorium232Cell = new ItemDepeletedNuclearRods("reEnrichedThorium232Cell", RodLang.reEnrichedThorium232Cell, 28),
    reEnrichedThorium230Cell= new ItemDepeletedNuclearRods("reEnrichedThorium230Cell", RodLang.reEnrichedThorium230Cell, 29);
    
    public static final ItemRTG plutoniumRTG = new ItemRTG("plutoniumRTG", 129600, 1);
    public static final ItemRTG thoriumRTG = new ItemRTG("thoriumRTG", 172800, 0);

    public static final ItemNuclearRod
    singleUOXCell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.UOX),
    doubleUOXCell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.UOX),
    quadUOXCell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.UOX),
    singlePlutoniumCell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.PLUTONIUM),
    doublePlutoniumCell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.PLUTONIUM),
    quadPlutoniumCell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.PLUTONIUM),
    singleMOXCell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.MOX),
    doubleMOXCell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.MOX),
    quadMOXCell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.MOX),
    singleThorium232Cell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.THORIUM232),
    doubleThorium232Cell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.THORIUM232),
    quadThorium232Cell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.THORIUM232),
    singleThorium230Cell = new ItemNuclearRod(NuclearRodTypes.SINGLE, NuclearRodVariants.THORIUM230),
    doubleThorium230Cell = new ItemNuclearRod(NuclearRodTypes.DOUBLE, NuclearRodVariants.THORIUM230),
    quadThorium230Cell = new ItemNuclearRod(NuclearRodTypes.QUAD, NuclearRodVariants.THORIUM230);

    public static final ItemIsotopicRod
    isotopicUOXCell = new ItemIsotopicRod(NuclearRodVariants.UOX),
    isotopicPlutoniumCell = new ItemIsotopicRod(NuclearRodVariants.PLUTONIUM),
    isotopicMOXCell = new ItemIsotopicRod(NuclearRodVariants.MOX),
    isotopicThorium232Cell = new ItemIsotopicRod(NuclearRodVariants.THORIUM232),
    isotopicThorium230Cell = new ItemIsotopicRod(NuclearRodVariants.THORIUM230);

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
        IC2.getInstance().createBlock(advancedSteamTurbine, ItemBlockGenerator.class);
        IC2.getInstance().createBlock(thermoElectricGenerator, ItemBlockGenerator.class);
        IC2.getInstance().createBlock(thermoElectricGeneratorMKII, ItemBlockGenerator.class);
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
        IC2.getInstance().createItem(thorium232TinyDust);
        IC2.getInstance().createItem(thorium230TinyDust);
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
        IC2.getInstance().createItem(plutoniumDust);
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
        IC2.getInstance().createItem(thorium232Dust);
        IC2.getInstance().createItem(thorium232Ingot);
        IC2.getInstance().createItem(thorium230Dust);
        IC2.getInstance().createItem(thorium230Ingot);
        IC2.getInstance().createItem(plutoniumIngot);
        IC2.getInstance().createItem(moxFuel);
        IC2.getInstance().createItem(denseIronPlate);
        IC2.getInstance().createItem(blankPress);
        IC2.getInstance().createItem(rollingPress);
        IC2.getInstance().createItem(extrudingPress);
        IC2.getInstance().createItem(cuttingPress);
        IC2.getInstance().createItem(lathingPress);
        IC2.getInstance().createItem(gearingPress);
        IC2.getInstance().createItem(craftingHammer);
        IC2.getInstance().createItem(wireCutters);
        IC2.getInstance().createItem(plutoniumRTG);
        IC2.getInstance().createItem(thoriumRTG);

        ItemNuclearRod.init();

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
        IC2.getInstance().createItem(singleThorium232Cell);
        IC2.getInstance().createItem(doubleThorium232Cell);
        IC2.getInstance().createItem(quadThorium232Cell);
        IC2.getInstance().createItem(nearDepletedThorium232Cell);
        IC2.getInstance().createItem(isotopicThorium232Cell);
        IC2.getInstance().createItem(reEnrichedThorium232Cell);
        IC2.getInstance().createItem(singleThorium230Cell);
        IC2.getInstance().createItem(doubleThorium230Cell);
        IC2.getInstance().createItem(quadThorium230Cell);
        IC2.getInstance().createItem(nearDepletedThorium230Cell);
        IC2.getInstance().createItem(isotopicThorium230Cell);
        IC2.getInstance().createItem(reEnrichedThorium230Cell);
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
        GameRegistry.registerTileEntity(TileEntityThermoElectricGenerator.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityThermoElectricGenerator"));
        GameRegistry.registerTileEntity(TileEntityThermoElectricGenerator.TileEntityThermoElectricGeneratorMkII.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityThermoElectricGeneratorMkII"));
    }
}
