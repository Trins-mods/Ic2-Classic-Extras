package trinsdar.ic2c_extras;

import ic2.core.block.crops.CropRegistry;
import ic2.core.platform.events.WorldGenerator;
import ic2.core.platform.recipes.misc.AdvRecipeRegistry;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trinsdar.ic2c_extras.block.CropPlumbilia;
import trinsdar.ic2c_extras.datagen.IC2CExtrasBlockTagProvider;
import trinsdar.ic2c_extras.datagen.IC2CExtrasItemTagProvider;
import trinsdar.ic2c_extras.event.PlayerEvents;
import trinsdar.ic2c_extras.init.IC2CExtrasSounds;
import trinsdar.ic2c_extras.init.ModBlocks;
import trinsdar.ic2c_extras.init.ModItems;
import trinsdar.ic2c_extras.recipes.CraftingRecipes;
import trinsdar.ic2c_extras.recipes.MachineRecipes;

@Mod(IC2CExtras.MODID)
public class IC2CExtras {
    public static final String MODID = "ic2c_extras";
    public static final CreativeModeTab CREATIVE_TAB = new CreativeTabIC2CExtras(MODID);


    public static Logger LOGGER = LogManager.getLogger(MODID);

    public IC2CExtras() {
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherDataEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        IC2CExtrasConfig.createConfig();
    }

    private void registerEvent(RegisterEvent event){
        if (event.getRegistryKey() == ForgeRegistries.Keys.BLOCKS) {
            ModBlocks.init();
            ModItems.init();
            IC2CExtrasSounds.init();
        } else if (event.getRegistryKey() == ForgeRegistries.Keys.FEATURES) {
            IC2CExtrasWorldgen.init();
        } else if (event.getRegistryKey() == ForgeRegistries.Keys.BIOME_MODIFIERS){
            event.getForgeRegistry().register(new ResourceLocation(IC2CExtras.MODID, "modifier"), new IC2CExtrasWorldgen.IC2CExtrasModifier());
        } else if (event.getRegistryKey() == ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS){
            event.getForgeRegistry().register(new ResourceLocation(IC2CExtras.MODID, "modifier"), IC2CExtrasWorldgen.IC2CExtrasModifier.CODEC);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event){
        AdvRecipeRegistry.INSTANCE.registerListener(CraftingRecipes::loadRecipes);
        MachineRecipes.init();

    }


    private void gatherDataEvent(GatherDataEvent event){
        BlockTagsProvider provider = new IC2CExtrasBlockTagProvider(event.getGenerator(), event.getExistingFileHelper());
        event.getGenerator().addProvider(true, provider);
        event.getGenerator().addProvider(true, new IC2CExtrasItemTagProvider(event.getGenerator(), provider, event.getExistingFileHelper()));
    }

}
