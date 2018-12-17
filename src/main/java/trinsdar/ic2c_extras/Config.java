package trinsdar.ic2c_extras;
import ic2.core.IC2;
import ic2.core.platform.config.ConfigEntry;
import ic2.core.platform.config.IC2Config;
import ic2.core.platform.config.components.IConfigNotify;
import trinsdar.ic2c_extras.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;
import trinsdar.ic2c_extras.util.Ic2cExtrasRecipes;

import java.util.ArrayList;
import java.util.List;

public class Config implements IConfigNotify {
    private static Config config = new Config();
    private static boolean higherRatio;

    public Config(){}

    public static void init(){
        IC2Config.ConfigType bool = IC2Config.ConfigType.Boolean;
        IC2Config.ConfigType intg = IC2Config.ConfigType.Integer;
        IC2Config.ConfigType flo = IC2Config.ConfigType.Float;
        IC2Config.ConfigType txt = IC2Config.ConfigType.String;
        if (IC2.config.isLoaded()){
            IC2.config.addCustomConfig(new ConfigEntry(bool, "Ic2cExtras", "enableItemRadiation", "Enable certain items giving radiation", "ItemRadiation", true).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "Ic2cExtras", "enableHarderUranium", "Enable harder uranium processing. Note: this is different then enableHarderEnrichedUran in that it deals with processing the ore and such", "HarderUranium", true).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "Ic2cExtras", "enableHigherUraniumRatio", "Enables the setting of the uranium ore ratio to my specified value. Note: If you want to set your own value then you need to disable this.", "HigherUraniumRatio", true).setGameRestart().setServerSync());
            IC2.config.addConfigNotify(config);
        }else {
            throw new RuntimeException("The Ic2Classic config is not loaded");
        }
    }

    public static void initConfigOverride(){
        if (higherRatio){
            IC2.config.setValue("oreDensityFactor", 1.2F);
        }
    }

    @Override
    public void onConfigReloaded(IC2Config config) {
        Radiation.setConfig(config.getFlag("ItemRadiation"));
        Ic2cExtrasRecipes.setConfig(config.getFlag("HarderUranium"));
        this.setConfig(config.getFlag("HigherUraniumRatio"));
    }

    public void setConfig(boolean enabled){
        higherRatio = enabled;
    }
}
