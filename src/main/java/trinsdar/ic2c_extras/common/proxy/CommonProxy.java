package trinsdar.ic2c_extras.common.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import trinsdar.ic2c_extras.common.util.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.common.util.RegistryBlock;
import trinsdar.ic2c_extras.common.util.RegistryItem;
import trinsdar.ic2c_extras.common.tileentity.TileEntityMetalPress;
import trinsdar.ic2c_extras.common.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.common.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.common.util.Ic2cExtrasItemApi;


public class CommonProxy
{
    public static Ic2cExtrasItemApi itemApi;
    public static Configuration config;

    public void preInit()
    {
        MinecraftForge.EVENT_BUS.register(RegistryBlock.class);
        RegistryBlock.registerTiles();
        MinecraftForge.EVENT_BUS.register(RegistryItem.class);
    }

    public void init()
    {
        TileEntityOreWashingPlant.init();
        TileEntityThermalCentrifuge.init();
        TileEntityMetalPress.init();
        Ic2cExtrasRecipes.init();
        //itemApi.init();
    }

    public void postInit()
    {

    }
}


