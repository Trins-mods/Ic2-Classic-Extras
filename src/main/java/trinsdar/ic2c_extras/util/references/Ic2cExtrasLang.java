package trinsdar.ic2c_extras.util.references;

import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import trinsdar.ic2c_extras.IC2CExtras;

public class Ic2cExtrasLang {
    public static LocaleComp heat = new LangComponentHolder.LocaleTileInfoComp("tileInfo.heatAmount.name");

    public static LocaleComp thermalCentrifuge = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".thermalCentrifuge");
    public static LocaleComp oreWashingPlant = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".oreWashingPlant");
    public static LocaleComp metalPress = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".metalPress");
    public static LocaleComp roller = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".roller");
    public static LocaleComp extruder = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".extruder");
    public static LocaleComp cutter = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".cutter");
    public static LocaleComp advancedSteamTurbine = new LangComponentHolder.LocaleBlockComp("tile." + IC2CExtras.MODID + ".advancedSteamTurbine");
}
