package trinsdar.ic2c_extras;

import ic2.api.classic.crops.ClassicCrops;
import ic2.api.recipe.IBasicMachineRecipeManager;
import ic2.api.recipe.Recipes;
import ic2.core.IC2;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import trinsdar.ic2c_extras.proxy.CommonProxy;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.CreativeTabIC2CExtras;
import trinsdar.ic2c_extras.util.Registry;

@Mod(name = IC2CExtras.NAME, modid = IC2CExtras.MODID, version = IC2CExtras.VERSION, dependencies = IC2CExtras.DEPENDS)
public class IC2CExtras
{
    public static final String MODID = "ic2c_extras";
    public static final String NAME = "IC2CExtras";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod;after:gtclassic";
    public static final CreativeTabs creativeTab = new CreativeTabIC2CExtras(MODID);

    @SidedProxy(clientSide = "trinsdar.ic2c_extras.proxy.ClientProxy", serverSide = "trinsdar.ic2c_extras.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static IC2CExtras instance;

    public static Logger logger;

    public IC2CExtras(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
        if (!IC2.config.getFlag("NonRadiation")){
            Recipes.metalformerExtruding = (IBasicMachineRecipeManager)Ic2cExtrasRecipes.extruding.toIC2Exp();
            Recipes.metalformerCutting = (IBasicMachineRecipeManager) Ic2cExtrasRecipes.cutting.toIC2Exp();
            Recipes.metalformerRolling = (IBasicMachineRecipeManager)Ic2cExtrasRecipes.rolling.toIC2Exp();
            Recipes.oreWashing = (IBasicMachineRecipeManager)Ic2cExtrasRecipes.oreWashingPlant.toIC2Exp();
            Recipes.centrifuge = (IBasicMachineRecipeManager)Ic2cExtrasRecipes.thermalCentrifuge.toIC2Exp();
        }
        ClassicCrops crop = ClassicCrops.instance;
        crop.registerCrop(Registry.cropPlumbilia);
        crop.registerCropDisplayItem(Registry.cropPlumbilia, new ItemStack(Registry.leadDust));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
        MinecraftForge.EVENT_BUS.register(new Ic2cExtrasRecipes());
        MinecraftForge.EVENT_BUS.register(new Radiation());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
        IC2.getInstance().saveRecipeInfo(IC2.configFolder);
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(MODID))
        {
            ConfigManager.sync(MODID, Config.Type.INSTANCE);
        }
    }


}
