package trinsdar.ic2c_extras;

import ic2.api.classic.addon.IC2Plugin;
import ic2.api.classic.addon.PluginBase;
import ic2.api.classic.addon.misc.IOverrideObject;
import ic2.core.IC2;
import ic2.core.item.block.ItemBlockMetal;
import ic2.core.platform.lang.storage.Ic2ItemLang;
import ic2.core.util.misc.ModulLoader.BlockOverride;
import ic2.core.util.misc.ModulLoader.ItemOverride;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import trinsdar.ic2c_extras.blocks.BlockEnergyStorage;
import trinsdar.ic2c_extras.blocks.BlockPersonalStorage;
import trinsdar.ic2c_extras.blocks.BlockUraniumOre;
import trinsdar.ic2c_extras.items.override.ItemBlockEnergyStorage;
import trinsdar.ic2c_extras.items.override.ItemBlockPersonalStorage;
import trinsdar.ic2c_extras.items.override.ItemElectricToolWrenchLossless;
import trinsdar.ic2c_extras.items.override.ItemMisc2;
import trinsdar.ic2c_extras.items.override.ItemNanoArmorPlated;
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
        map.put("blockMetal", new BlockOverride(new BlockUraniumOre().setCreativeTab(IC2.tabIC2), ItemBlockMetal.class));
        map.put("blockElectric", new BlockOverride(new BlockEnergyStorage().setCreativeTab(IC2.tabIC2), ItemBlockEnergyStorage.class));
        map.put("blockPersonal", new BlockOverride(new BlockPersonalStorage().setCreativeTab(IC2.tabIC2), ItemBlockPersonalStorage.class));
        map.put("itemMisc", new ItemOverride(new ItemMisc2().setCreativeTab(IC2.tabIC2)));
        map.put("itemToolWrench", new ItemOverride(new ItemToolWrenchLossless().setCreativeTab(IC2.tabIC2)));
        map.put("itemArmorNanoBoots", new ItemOverride((new ItemNanoArmorPlated(7, EntityEquipmentSlot.FEET)).setUnlocalizedName(Ic2ItemLang.nanoBoots)));
        map.put("itemArmorNanoLegs", new ItemOverride((new ItemNanoArmorPlated(6, EntityEquipmentSlot.LEGS)).setUnlocalizedName(Ic2ItemLang.nanoLeggings)));
        map.put("itemArmorNanoChestplate", new ItemOverride((new ItemNanoArmorPlated(5, EntityEquipmentSlot.CHEST)).setUnlocalizedName(Ic2ItemLang.nanoChestplate)));
        map.put("itemArmorNanoHelmet", new ItemOverride((new ItemNanoArmorPlated(4, EntityEquipmentSlot.HEAD)).setUnlocalizedName(Ic2ItemLang.nanoHelmet)));
        if (!Loader.isModLoaded("gtc_expansion")){
            map.put("itemToolWrenchElectric", new ItemOverride(new ItemElectricToolWrenchLossless().setCreativeTab(IC2.tabIC2)));
        }
    }
}
