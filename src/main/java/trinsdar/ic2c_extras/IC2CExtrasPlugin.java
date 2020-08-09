package trinsdar.ic2c_extras;

import ic2.api.classic.addon.IC2Plugin;
import ic2.api.classic.addon.PluginBase;
import ic2.api.classic.addon.misc.IOverrideObject;
import ic2.core.IC2;
import ic2.core.item.block.ItemBlockMetal;
import ic2.core.util.misc.ModulLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import trinsdar.ic2c_extras.blocks.BlockUraniumOre;
import trinsdar.ic2c_extras.items.override.ItemMisc2;
import trinsdar.ic2c_extras.items.override.ItemToolWrenchLossless;

import java.util.Map;

@IC2Plugin(id = "ic2c_extras_plugin", name = "Ic2cExtrasPlugin", version = IC2CExtras.VERSION)
public class IC2CExtrasPlugin extends PluginBase {
    @Override
    public boolean canLoad(Side side) {
        return true;
    }

    @Override
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent, Map<String, IOverrideObject> map) {
        map.put("blockMetal", new ModulLoader.BlockOverride(new BlockUraniumOre().setCreativeTab(IC2.tabIC2), ItemBlockMetal.class));
        map.put("itemMisc", new ModulLoader.ItemOverride(new ItemMisc2().setCreativeTab(IC2.tabIC2)));
        map.put("itemToolWrench", new ModulLoader.ItemOverride(new ItemToolWrenchLossless().setCreativeTab(IC2.tabIC2)));
    }
}
