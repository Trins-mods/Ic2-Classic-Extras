package trinsdar.ic2c_extras.common;
import ic2.core.IC2;
import ic2.api.classic.addon.IC2Plugin;
import ic2.core.platform.config.ConfigEntry;
import ic2.core.platform.config.IC2Config;
import trinsdar.ic2c_extras.common.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import trinsdar.ic2c_extras.Ic2cExtras;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_RADIATION = "radiation";

    // This values below you can access elsewhere in your mod:
    public static boolean isRadiationTurnedOn = true;

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
            initRadiationConfig(cfg);
        } catch (Exception e1) {
            IC2.log.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
    }

    private static void initRadiationConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_RADIATION, "Radiation configuration");
        isRadiationTurnedOn = cfg.getBoolean("radiationSwitch", CATEGORY_GENERAL, isRadiationTurnedOn, "Set to false if you don't want radiation on.");

    }
    List<ConfigEntry> defaults = new ArrayList();

    private void init(IC2Config config){
        IC2Config.ConfigType bool = IC2Config.ConfigType.Boolean;
        IC2Config.ConfigType intg = IC2Config.ConfigType.Integer;
        IC2Config.ConfigType flo = IC2Config.ConfigType.Float;
        IC2Config.ConfigType txt = IC2Config.ConfigType.String;
        this.add((new ConfigEntry(bool, "radiation", "enableItemRadiation", "Enable certain items giving radiation", "ItemRadiation", true)).setGameRestart().setServerSync());
    }
    private void add(ConfigEntry par1) {
        this.defaults.add(par1);
    }
}
