package trinsdar.ic2c_extras.util;

import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import trinsdar.ic2c_extras.Ic2cExtras;

public class Ic2cExtrasLang {
    public static LocaleComp heat = new LangComponentHolder.LocaleTileInfoComp("tileInfo.heatAmount.name");

    public static LocaleComp thermalCentrifuge = new LangComponentHolder.LocaleBlockComp("tile." + Ic2cExtras.MODID + ".thermalCentrifuge");

    public Ic2cExtrasLang(){
    }
}
