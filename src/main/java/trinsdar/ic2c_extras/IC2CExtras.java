package trinsdar.ic2c_extras;

import ic2.core.IC2;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trinsdar.ic2c_extras.events.Bear989Event;
import trinsdar.ic2c_extras.events.RadiationEvent;
import trinsdar.ic2c_extras.proxy.CommonProxy;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.CreativeTabIC2CExtras;
import trinsdar.ic2c_extras.util.Registry;
import trinsdar.ic2c_extras.util.V2Encoder;
import trinsdar.ic2c_extras.util.recipelists.FermenterRecipeManager;

@Mod(IC2CExtras.MODID)
public class IC2CExtras {
    public static final String MODID = "ic2c_extras";
    public static final String NAME = "IC2CExtras";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDS = "required-after:ic2;required-after:ic2-classic-spmod;after:gtclassic@[1.1.6,);after:gravisuit";
    public static final CreativeModeTab creativeTab = new CreativeTabIC2CExtras(MODID);

    public static CommonProxy proxy;


    public static Logger LOGGER = LogManager.getLogger(MODID);

    public IC2CExtras() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        if (!IC2.config.getFlag("NonRadiation")) {
            Recipes.metalformerExtruding = (IBasicMachineRecipeManager) Ic2cExtrasRecipes.extruding.toIC2Exp();
            Recipes.metalformerCutting = (IBasicMachineRecipeManager) Ic2cExtrasRecipes.cutting.toIC2Exp();
            Recipes.metalformerRolling = (IBasicMachineRecipeManager) Ic2cExtrasRecipes.rolling.toIC2Exp();
            Recipes.oreWashing = (IBasicMachineRecipeManager) Ic2cExtrasRecipes.oreWashingPlant.toIC2Exp();
            Recipes.centrifuge = (IBasicMachineRecipeManager) Ic2cExtrasRecipes.thermalCentrifuge.toIC2Exp();
            Recipes.fermenter = new FermenterRecipeManager("fermenter");
        }
        ClassicCrops crop = ClassicCrops.instance;
        crop.registerCrop(Registry.cropPlumbilia);
        crop.registerCropDisplayItem(Registry.cropPlumbilia, new ItemStack(Registry.leadDust));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        RadiationEvent.initRadiation();
        MinecraftForge.EVENT_BUS.register(new Ic2cExtrasRecipes());
        MinecraftForge.EVENT_BUS.register(new Bear989Event());
        if (Ic2cExtrasConfig.itemRadiation) {
            MinecraftForge.EVENT_BUS.register(new RadiationEvent());
        }

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @SubscribeEvent
    public void onRegisterItem(RegisterEvent event){
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCKS)){
            Registry.register();

        }
    }


}
