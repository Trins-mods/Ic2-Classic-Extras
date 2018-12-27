package trinsdar.ic2c_extras.util.references;

import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleJEIInfoComp;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleTileInfoComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import trinsdar.ic2c_extras.IC2CExtras;

public class Ic2cExtrasLang {
    public static LocaleComp heat = new LocaleTileInfoComp("tileInfo.heatAmount.name");
    public static LocaleComp jeiHeat = new LocaleJEIInfoComp("jeiInfo.heat.name");
    public static LocaleComp jeiWater = new LocaleJEIInfoComp("jeiInfo.water.name");

    public static LocaleComp thermalCentrifuge = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".thermalCentrifuge");
    public static LocaleComp oreWashingPlant = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".oreWashingPlant");
    public static LocaleComp roller = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".roller");
    public static LocaleComp extruder = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".extruder");
    public static LocaleComp cutter = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".cutter");
    public static LocaleComp impellerizedRoller = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".impellerizedRoller");
    public static LocaleComp liquescentExtruder = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".liquescentExtruder");
    public static LocaleComp plasmaCutter = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".plasmaCutter");
    public static LocaleComp advancedSteamTurbine = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".advancedSteamTurbine");
}
