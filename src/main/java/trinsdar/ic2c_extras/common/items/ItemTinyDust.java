package trinsdar.ic2c_extras.common.items;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtras;

import java.util.ArrayList;
import java.util.List;

public class ItemTinyDust extends ItemBase
{
    public static final String[] tinyDustTypes = {"iron", "gold", "copper", "tin", "silver", "uranium235", "lead", "obsidian", "bronze", "uranium238", "plutonium"};

    public ItemTinyDust(String itemName) {
        super(itemName);
        this.hasSubtypes = true;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName() + "_" + tinyDustTypes[itemStack.getMetadata()];
    }
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            int i = 0;

            for (String tinyDustType : tinyDustTypes)
            {
                items.add(new ItemStack(this, 1, i));
                i++;
            }
        }
    }

    @Override
    public List<ItemStack> getValidItemVariants()
    {
        List<ItemStack> itemList = new ArrayList<>();
        for (int i = 0; i < tinyDustTypes.length; i++)
        {
            itemList.add(new ItemStack(this, 1, i));
        }
        return itemList;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(ItemStack itemStack)
    {
        return Ic2Icons.getTextures("ic2c_extras_items")[32 + itemStack.getMetadata()];
    }
}
