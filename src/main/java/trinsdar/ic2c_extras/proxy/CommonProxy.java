package trinsdar.ic2c_extras.proxy;

import ic2.core.IC2;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import trinsdar.ic2c_extras.Config;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.recipes.MachineRecipes;
import trinsdar.ic2c_extras.recipes.RailcraftRecipes;
import trinsdar.ic2c_extras.util.Ic2cExtrasOredict;
import trinsdar.ic2c_extras.util.Registry;

import java.io.File;


public class CommonProxy
{
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event)
    {
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "ic2/ic2c_extras.cfg"));
        Config.readConfig();
        config.save();
        Registry.init();
        Registry.registerTiles();
        if (!IC2.config.getFlag("NonRadiation")){
            Ic2cExtrasOredict.init();
        }
    }

    public void init()
    {
        if (!IC2.config.getFlag("NonRadiation")){
            Ic2cExtrasRecipes.init();
        }
    }

    public void postInit()
    {
        if (!IC2.config.getFlag("NonRadiation")){
            if (Loader.isModLoaded("railcraft")){
                RailcraftRecipes.initRailcraftRecipes();
            }
            Ic2cExtrasRecipes.postInit();
        }
    }
}


