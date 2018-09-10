package trinsdar.ic2c_extras.common.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.items.ItemCrushedOre;
import trinsdar.ic2c_extras.common.items.RegistryItem;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) { super.preInit(e); }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Ic2cExtras.MODID + ":" + id, "inventory"));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
    }
}
