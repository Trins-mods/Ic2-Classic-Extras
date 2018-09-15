package trinsdar.ic2c_extras;

import ic2.api.classic.addon.IC2Plugin;
import ic2.api.classic.addon.IModul;
import ic2.api.classic.addon.misc.IOverrideObject;
import ic2.api.classic.addon.misc.SideGateway;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.misc.ModulLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import trinsdar.ic2c_extras.common.proxy.CommonProxy;
import trinsdar.ic2c_extras.common.util.CreativeTabIC2CExtras;
import trinsdar.ic2c_extras.common.util.Icons;

import java.util.Map;

@IC2Plugin(name = Ic2cExtras.NAME, id = Ic2cExtras.MODID, version = Ic2cExtras.VERSION, hasResourcePack = true)
public class Ic2cExtras implements IModul
{
    public static final String MODID = "ic2c_extras";
    public static final String NAME = "Ic2cExtras";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod";

    public static SideGateway<CommonProxy> gateway = new SideGateway<>("trinsdar.ic2c_extras.common.proxy.ClientProxy", "trinsdar.ic2c_extras.common.proxy.ServerProxy");
    public static CommonProxy proxy = gateway.get();

    @Override
    public boolean canLoad(Side side)
    {
        return Loader.isModLoaded("ic2-classic-spmod");
    }

    @Override
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent, Map<String, IOverrideObject> map)
    {
        proxy.preInit();
    }

    @Override
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent)
    {

    }

    @Override
    public void init(FMLInitializationEvent fmlInitializationEvent)
    {
        proxy.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent)
    {
        proxy.postInit();
    }

    @Override
    public void gameLoaded(FMLLoadCompleteEvent fmlLoadCompleteEvent)
    {

    }

    @Override
    public void onTextureLoad()
    {
        Icons.loadSprites();
    }

    @Override
    public void onServerStarting(MinecraftServer minecraftServer)
    {

    }

    @Override
    public void onServerStopped()
    {

    }

    public static final CreativeTabs creativeTab = new CreativeTabIC2CExtras(MODID);
}
