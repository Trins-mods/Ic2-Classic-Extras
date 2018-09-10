package trinsdar.ic2c_extras;

import net.minecraft.creativetab.CreativeTabs;
import trinsdar.ic2c_extras.common.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import trinsdar.ic2c_extras.common.util.CreativeTabIC2CExtras;

@Mod(modid = Ic2cExtras.MODID, name = Ic2cExtras.NAME, version = Ic2cExtras.VERSION, dependencies = Ic2cExtras.DEPENDS )
public class Ic2cExtras
{
    public static final String MODID = "ic2c_extras";
    public static final String NAME = "Ic2cExtras";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod";

    @SidedProxy(clientSide = "trinsdar.ic2c_extras.common.proxy.ClientProxy", serverSide = "trinsdar.ic2c_extras.common.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Ic2cExtras instance;

    public static Logger logger;

    public static final CreativeTabs creativeTab = new CreativeTabIC2CExtras(MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        proxy.init(event);
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
