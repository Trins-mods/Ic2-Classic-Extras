package trinsdar.ic2c_extras.common.items;

import ic2.core.platform.textures.obj.ITexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.Ic2cExtras;

import java.util.List;

public class ItemBase extends Item implements ITexturedItem
{
    public ItemBase(String itemName)
    {
        this.setCreativeTab(Ic2cExtras.creativeTab);
        this.setRegistryName(Ic2cExtras.MODID, itemName);
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + itemName);
    }

    @Override
    public List<ItemStack> getValidItemVariants()
    {
        return null;
    }

    @Override
    public TextureAtlasSprite getTexture(ItemStack itemStack)
    {
        return null;
    }
}
