package trinsdar.ic2c_extras.items;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.Arrays;
import java.util.List;

public class ItemMiscs extends Item implements IStaticTexturedItem {
    public enum ItemMiscsTypes{
        LEAD_INGOT(64),
        LEAD_DUSTS(65),
        STONE_DUSTS(66),
        SLAG(67),
        URANIUM235(68),
        URANIUM238(69),
        PLUTONIUM(70),
        COIL(71),
        HEAT_CONDUCTOR(72),
        STEEL_INGOT(73),
        PLUTONIUM_ENRICHED_URANIUM_INGOT(74),
        PLUTONIUM_ENRICHED_URANIUM(75),
        IRIDIUM_SHARD(76);

        private int id;

        ItemMiscsTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    int index;
    ItemMiscsTypes variant;
    public ItemMiscs(ItemMiscsTypes variant) {
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase());
        setUnlocalizedName(IC2CExtras.MODID + "." + variant.toString().toLowerCase());
        setCreativeTab(IC2CExtras.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta)
    {
        return Ic2Icons.getTextures("ic2c_extras_items")[variant.getID()];
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }
}
