package trinsdar.ic2c_extras.common;
import ic2.core.IC2;
import ic2.core.util.obj.IC2Plugin;
import trinsdar.ic2c_extras.common.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import trinsdar.ic2c_extras.Ic2cExtras;
import org.apache.logging.log4j.Level;

public class Config {
    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_RADIATION = "radiation";

    // This values below you can access elsewhere in your mod:
    public static boolean isThisAGoodTutorial = true;
    public static boolean isRadiationTurnedOn = true;
    public static String yourRealName = "Steve";

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
        isThisAGoodTutorial = cfg.getBoolean("goodTutorial", CATEGORY_GENERAL, isThisAGoodTutorial, "Set to false if you don't like this tutorial");
        yourRealName = cfg.getString("realName", CATEGORY_GENERAL, yourRealName, "Set your real name here");
    }

    private static void initRadiationConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_RADIATION, "Radiation configuration");
        isRadiationTurnedOn = cfg.getBoolean("radiationSwitch", CATEGORY_GENERAL, isRadiationTurnedOn, "Set to false if you don't want radiation on.");

    }
}
