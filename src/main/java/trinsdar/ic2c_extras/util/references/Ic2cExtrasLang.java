package trinsdar.ic2c_extras.util.references;

import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleJEIInfoComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleTileInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;

public class Ic2cExtrasLang {
    public static LocaleComp heat = new LocaleTileInfoComp("tileInfo.heatAmount.name");
    public static LocaleComp jeiHeat = new LocaleJEIInfoComp("jeiInfo.heat.name");

    public static LocaleComp thermalCentrifuge = new LangComponentHolder.LocaleBlockComp("tile.thermalCentrifuge");
    public static LocaleComp oreWashingPlant = new LangComponentHolder.LocaleBlockComp("tile.oreWashingPlant");
    public static LocaleComp thermalWasher = new LangComponentHolder.LocaleBlockComp("tile.thermalWasher");
    public static LocaleComp metalBender = new LangComponentHolder.LocaleBlockComp("tile.metalBender");
    public static LocaleComp fluidCanningMachine = new LangComponentHolder.LocaleBlockComp("tile.fluidCanningMachine");
    public static LocaleComp treeTapper = new LangComponentHolder.LocaleBlockComp("tile.treeTapper");
    public static LocaleComp roller = new LangComponentHolder.LocaleBlockComp("tile.roller");
    public static LocaleComp extruder = new LangComponentHolder.LocaleBlockComp("tile.extruder");
    public static LocaleComp cutter = new LangComponentHolder.LocaleBlockComp("tile." + "cutter");
    public static LocaleComp blockCuttingMachine = new LangComponentHolder.LocaleBlockComp("tile.blockCuttingMachine");
    public static LocaleComp impellerizedRoller = new LangComponentHolder.LocaleBlockComp("tile.impellerizedRoller");
    public static LocaleComp liquescentExtruder = new LangComponentHolder.LocaleBlockComp("tile.liquescentExtruder");
    public static LocaleComp plasmaCutter = new LangComponentHolder.LocaleBlockComp("tile.plasmaCutter");
    public static LocaleComp advancedSteamTurbine = new LangComponentHolder.LocaleBlockComp("tile.advancedSteamTurbine");
    public static LocaleComp thermoElectricGenerator = new LangComponentHolder.LocaleBlockComp("tile.thermoElectricGenerator");
    public static LocaleComp thermoElectricGeneratorMkII = new LangComponentHolder.LocaleBlockComp("tile.thermoElectricGeneratorMkII");
}
