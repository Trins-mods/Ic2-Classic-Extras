package trinsdar.ic2c_extras.util.references;

import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleJEIInfoComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleTileInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;

public class Ic2cExtrasLang {
    public static final LocaleComp HEAT = new LocaleTileInfoComp("tileInfo.heatAmount.name");
    public static final LocaleComp REACTOR_HEAT = new LocaleTileInfoComp("tileInfo.reactorHeatAmount.name");
    public static final LocaleComp REACTOR_EU = new LocaleTileInfoComp("tileInfo.reactorEUPerTick.name");
    public static final LocaleComp REACTOR_STEAM = new LocaleTileInfoComp("tileInfo.reactorSteamPerTick.name");
    public static final LocaleComp REACTOR_THERMOMETER = new LocaleTileInfoComp("tileInfo.reactorNeedThermometer.name");
    public static final LocaleComp JEI_HEAT = new LocaleJEIInfoComp("jeiInfo.heat.name");

    public static final LocaleComp THERMAL_CENTRIFUGE = new LocaleBlockComp("tile.thermalCentrifuge");
    public static final LocaleComp ORE_WASHING_PLANT = new LocaleBlockComp("tile.oreWashingPlant");
    public static final LocaleComp THERMAL_WASHER = new LocaleBlockComp("tile.thermalWasher");
    public static final LocaleComp METAL_BENDER = new LocaleBlockComp("tile.metalBender");
    public static final LocaleComp FLUID_CANNING_MACHINE = new LocaleBlockComp("tile.fluidCanningMachine");
    public static final LocaleComp TREE_TAPPER = new LocaleBlockComp("tile.treeTapper");
    public static final LocaleComp ELECTRIC_DISENCHANTER = new LocaleBlockComp("tile.electricDisenchanter");
    public static final LocaleComp ROLLER = new LocaleBlockComp("tile.roller");
    public static final LocaleComp EXTRUDER = new LocaleBlockComp("tile.extruder");
    public static final LocaleComp CUTTER = new LocaleBlockComp("tile.cutter");
    public static final LocaleComp BLOCK_CUTTING_MACHINE = new LocaleBlockComp("tile.blockCuttingMachine");
    public static final LocaleComp IMPELLERIZED_ROLLER = new LocaleBlockComp("tile.impellerizedRoller");
    public static final LocaleComp LIQUESCENT_EXTRUDER = new LocaleBlockComp("tile.liquescentExtruder");
    public static final LocaleComp PLASMA_CUTTER = new LocaleBlockComp("tile.plasmaCutter");
    public static final LocaleComp AUTOCRAFTING_TABLE = new LocaleBlockComp("tile.autocraftingTable");
    public static final LocaleComp REINFORCED_ENCASED_CABLE = new LocaleBlockComp("tile.reinforcedEncasedCable");
    public static final LocaleComp ADVANCED_STEAM_TURBINE = new LocaleBlockComp("tile.advancedSteamTurbine");
    public static final LocaleComp THERMO_ELECTRIC_GENERATOR = new LocaleBlockComp("tile.thermoElectricGenerator");
    public static final LocaleComp THERMO_ELECTRIC_GENERATOR_MK_II = new LocaleBlockComp("tile.thermoElectricGeneratorMkII");
    public static final LocaleComp THERMO_ELECTRIC_GENERATOR_TOOLTIP = new LocaleTileInfoComp("tileInfo.thermoElectricGenerator.name");
    public static final LocaleComp THERMO_ELECTRIC_GENERATOR_MK_II_TOOLTIP = new LocaleTileInfoComp("tileInfo.thermoElectricGeneratorMkII.name");

    public static final LocaleComp STEEL_BLOCK = new LocaleBlockComp("tile.steelBlock");
    public static final LocaleComp REFINED_IRON_BLOCK = new LocaleBlockComp("tile.refinedIronBlock");
    public static final LocaleComp LEAD_BLOCK = new LocaleBlockComp("tile.leadBlock");
    public static final LocaleComp STONE_DUST_BLOCK = new LocaleBlockComp("tile.stoneDustBlock");

    public static final LocaleComp URANIUM_FUEL = new LangComponentHolder.LocaleItemComp("item.itemFuelUran");

    public static final LocaleComp REACTOR_PLATED = new LangComponentHolder.LocaleItemInfoComp("itemInfo.reactorPlated.name");
}
