package trinsdar.ic2c_extras.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import trinsdar.ic2c_extras.Config;
import trinsdar.ic2c_extras.util.Ic2cExtrasOredict;
import trinsdar.ic2c_extras.util.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.RegistryBlock;
import trinsdar.ic2c_extras.util.RegistryItem;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityMetalPress;


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
        TileEntityMetalPress.init();
        Ic2cExtrasOredict.init();
        Ic2cExtrasRecipes.init();
    }

    public void postInit()
    {

    }
}


