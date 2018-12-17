package trinsdar.ic2c_extras.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import trinsdar.ic2c_extras.Config;
import trinsdar.ic2c_extras.util.Ic2cExtrasOredict;
import trinsdar.ic2c_extras.util.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.RegistryBlock;
import trinsdar.ic2c_extras.util.RegistryItem;
import trinsdar.ic2c_extras.tileentity.TileEntityMetalPress;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;


public class CommonProxy
{
    public static Configuration config;

    public void preInit()
    {
        Config.init();
        MinecraftForge.EVENT_BUS.register(RegistryBlock.class);
        RegistryBlock.registerTiles();
        MinecraftForge.EVENT_BUS.register(RegistryItem.class);
    }

    public void init()
    {
        Config.initConfigOverride();
        TileEntityOreWashingPlant.init();
        TileEntityThermalCentrifuge.init();
        TileEntityMetalPress.init();
        Ic2cExtrasOredict.init();
        Ic2cExtrasRecipes.init();
    }

    public void postInit()
    {

    }
}


