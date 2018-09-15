package trinsdar.ic2c_extras;

import ic2.api.classic.addon.misc.IOverrideObject;
import ic2.core.IC2;
import ic2.core.platform.textures.Ic2Icons;
import ic2.api.classic.addon.misc.SideGateway;
import ic2.core.util.misc.ModulLoader;
import ic2.api.classic.addon.IC2Plugin;
import ic2.api.classic.addon.IModul;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;
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
import trinsdar.ic2c_extras.common.util.Icons;

import java.util.Map;

@IC2Plugin(name = Ic2cExtras.NAME, id = Ic2cExtras.MODID, version = Ic2cExtras.VERSION)
public class Ic2cExtras implements IModul
{
    public static final String MODID = "ic2c_extras";
    public static final String NAME = "Ic2cExtras";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod";

    public static SideGateway<CommonProxy> gateway = new SideGateway<>("trinsdar.ic2c_extras.common.proxy.ClientProxy", "trinsdar.ic2c_extras.common.proxy.ServerProxy");
    public static CommonProxy proxy = gateway.get();
    private Ic2Icons ic2Icons;


    public String getID()
    {
        return MODID;
    }


    public String getName()
    {
        return NAME;
    }


    public String getConfigTag()
    {
        return "IC2 Classic Extras";
    }

    @Override
    public boolean canLoad(Side side)
    {
        return Loader.isModLoaded("ic2-classic-spmod");
    }

    @Override
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent, Map<String, IOverrideObject> map) {
        proxy.preInit();

    }


    public boolean hasResourcePack()
    {
        return true;
    }

    @Override
    public void preInit(FMLPreInitializationEvent preinit)
    {
        proxy.preInit();
    }

    @Override
    public void init(FMLInitializationEvent fmlInitializationEvent) {
        proxy.init();

    }

    @Override
    public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent) {
        proxy.postInit();
    }

    @Override
    public void gameLoaded(FMLLoadCompleteEvent fmlLoadCompleteEvent) {

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onTextureLoad() {

    }


    public void init()
    {
        proxy.init();
    }


    public void postInit()
    {
        proxy.postInit();
    }


    @SideOnly(Side.CLIENT)
    public void onTextureLoad(Ic2Icons ic2Icons)
    {
        Icons.loadSprites(ic2Icons);
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
