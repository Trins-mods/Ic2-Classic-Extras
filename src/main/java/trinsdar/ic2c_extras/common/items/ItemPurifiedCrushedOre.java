package trinsdar.ic2c_extras.common.items;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemPurifiedCrushedOre extends Item implements IStaticTexturedItem {
    int index;
    ItemMaterials variant;
    public ItemPurifiedCrushedOre(ItemMaterials variant, int index) {
        this.index = index;
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase() + "_purified_crushed_ore");
        setUnlocalizedName(Ic2cExtras.MODID + "." + variant.toString().toLowerCase() + "PurifiedCrushedOre");
        setCreativeTab(Ic2cExtras.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta)
    {
        return Ic2Icons.getTextures("ic2c_extras_items")[index];
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }
}
