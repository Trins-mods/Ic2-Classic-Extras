package trinsdar.ic2c_extras;
import ic2.core.IC2;
import ic2.core.platform.config.ConfigEntry;
import ic2.core.platform.config.IC2Config;
import ic2.core.platform.config.components.IConfigNotify;
import trinsdar.ic2c_extras.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

public class Config implements IConfigNotify {
    private static Config config = new Config();

    public Config(){}

    public static void init(){
        IC2Config.ConfigType bool = IC2Config.ConfigType.Boolean;
        IC2Config.ConfigType intg = IC2Config.ConfigType.Integer;
        IC2Config.ConfigType flo = IC2Config.ConfigType.Float;
        IC2Config.ConfigType txt = IC2Config.ConfigType.String;
        if (IC2.config.isLoaded()){
            IC2.config.addCustomConfig(new ConfigEntry(bool, "Ic2cExtras", "enableItemRadiation", "Enable certain items giving radiation", "ItemRadiation", true).setGameRestart().setServerSync());
            IC2.config.addConfigNotify(config);
        }else {
            throw new RuntimeException("The Ic2Classic config is not loaded");
        }
    }

    @Override
    public void onConfigReloaded(IC2Config config) {
        Radiation.setConfig(config.getFlag("ItemRadiation"));
    }
}
