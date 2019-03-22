package trinsdar.ic2c_extras;

import ic2.api.classic.addon.IC2Plugin;
import ic2.api.classic.addon.PluginBase;
import ic2.api.classic.addon.misc.IOverrideObject;
import ic2.api.classic.addon.misc.SideGateway;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.recipe.IBasicMachineRecipeManager;
import ic2.api.recipe.Recipes;
import ic2.core.IC2;
import ic2.core.block.machine.high.TileEntityUraniumEnricher;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;
import trinsdar.ic2c_extras.proxy.CommonProxy;
import trinsdar.ic2c_extras.util.CreativeTabIC2CExtras;
import trinsdar.ic2c_extras.util.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.Icons;
import trinsdar.ic2c_extras.util.Registry;

import java.util.Map;

@IC2Plugin(name = IC2CExtras.NAME, id = IC2CExtras.MODID, version = IC2CExtras.VERSION, hasResourcePack = true)
public class IC2CExtras extends PluginBase
{
    public static final String MODID = "ic2c_extras";
    public static final String NAME = "IC2CExtras";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod";

    public static SideGateway<CommonProxy> gateway = new SideGateway<>("trinsdar.ic2c_extras.proxy.ClientProxy", "trinsdar.ic2c_extras.proxy.ServerProxy");
    public static CommonProxy proxy = gateway.get();
    public static Logger logger;

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
        if (!IC2.config.getFlag("NonRadiation")){
            Recipes.metalformerExtruding = (IBasicMachineRecipeManager)Ic2cExtrasRecipes.extruding.toIC2Exp();
            Recipes.metalformerCutting = (IBasicMachineRecipeManager) Ic2cExtrasRecipes.cutting.toIC2Exp();
            Recipes.metalformerRolling = (IBasicMachineRecipeManager)Ic2cExtrasRecipes.rolling.toIC2Exp();
            Recipes.oreWashing = (IBasicMachineRecipeManager)Ic2cExtrasRecipes.oreWashingPlant.toIC2Exp();
            Recipes.centrifuge = (IBasicMachineRecipeManager)Ic2cExtrasRecipes.thermalCentrifuge.toIC2Exp();
        }

    }

    @Override
    public void init(FMLInitializationEvent fmlInitializationEvent)
    {
        proxy.init();
        MinecraftForge.EVENT_BUS.register(new Ic2cExtrasRecipes());
        MinecraftForge.EVENT_BUS.register(new Radiation());
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
