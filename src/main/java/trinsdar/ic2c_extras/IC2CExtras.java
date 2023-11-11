package trinsdar.ic2c_extras;

import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trinsdar.ic2c_extras.datagen.Ic2cExtrasBlockTagProvider;
import trinsdar.ic2c_extras.datagen.Ic2cExtrasItemTagProvider;
import trinsdar.ic2c_extras.event.PlayerEvents;
import trinsdar.ic2c_extras.init.ModItems;

@Mod(IC2CExtras.MODID)
public class IC2CExtras {
    public static final String MODID = "ic2c_extras";
    public static final CreativeModeTab CREATIVE_TAB = new CreativeTabIC2CExtras(MODID);


    public static Logger LOGGER = LogManager.getLogger(MODID);

    public IC2CExtras() {
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherDataEvent);
    }

    private void registerEvent(RegisterEvent event){
        ModItems.init();
    }


    private void gatherDataEvent(GatherDataEvent event){
        BlockTagsProvider provider = new Ic2cExtrasBlockTagProvider(event.getGenerator(), event.getExistingFileHelper());
        event.getGenerator().addProvider(true, provider);
        event.getGenerator().addProvider(true, new Ic2cExtrasItemTagProvider(event.getGenerator(), provider, event.getExistingFileHelper()));
    }

}
