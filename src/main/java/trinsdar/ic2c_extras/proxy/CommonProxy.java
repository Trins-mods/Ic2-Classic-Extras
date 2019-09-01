package trinsdar.ic2c_extras.proxy;

import ic2.core.IC2;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.recipes.MachineRecipes;
import trinsdar.ic2c_extras.recipes.RailcraftRecipes;
import trinsdar.ic2c_extras.util.Ic2cExtrasOredict;
import trinsdar.ic2c_extras.util.Registry;


public class CommonProxy
{
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event)
    {
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


