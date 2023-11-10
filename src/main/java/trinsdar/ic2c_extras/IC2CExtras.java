package trinsdar.ic2c_extras;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trinsdar.ic2c_extras.init.ModItems;

@Mod(IC2CExtras.MODID)
public class IC2CExtras {
    public static final String MODID = "ic2c_extras";
    public static final CreativeModeTab CREATIVE_TAB = new CreativeTabIC2CExtras(MODID);


    public static Logger LOGGER = LogManager.getLogger(MODID);

    public IC2CExtras() {
        //MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerEvent);
    }

    private void registerEvent(RegisterEvent event){
        ModItems.init();
    }



}
