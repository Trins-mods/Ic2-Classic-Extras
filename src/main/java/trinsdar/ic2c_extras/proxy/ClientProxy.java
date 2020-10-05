package trinsdar.ic2c_extras.proxy;

import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import trinsdar.ic2c_extras.util.Icons;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onIconLoad(Ic2Icons.SpriteReloadEvent event) {
        Icons.loadSprites();
    }

    @SubscribeEvent
    public static void onRegisterTexture(TextureStitchEvent.Pre event) {
        event.getMap().registerSprite(new ResourceLocation(IC2CExtras.MODID, "fluids/biomass_still"));
        event.getMap().registerSprite(new ResourceLocation(IC2CExtras.MODID, "fluids/biomass_flow"));
        event.getMap().registerSprite(new ResourceLocation(IC2CExtras.MODID, "fluids/biogas_still"));
        event.getMap().registerSprite(new ResourceLocation(IC2CExtras.MODID, "fluids/biogas_flow"));
    }
}
