package trinsdar.ic2c_extras.common.items;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.obj.IC2Plugin;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import trinsdar.ic2c_extras.Ic2cExtras;

import java.util.ArrayList;
import java.util.List;

@IC2Plugin
public class ItemCasings extends ItemBase{
    public static final String[] itemCasings = {"copper", "tin", "silver", "lead", "iron", "gold", "refined_iron", "steel", "bronze",};

    public ItemCasings(String itemName) {
        super(itemName);
        this.hasSubtypes = true;
    }
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName() + "_" + itemCasings[itemStack.getMetadata()];
    }
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            int i = 0;

            for (String itemCasings : itemCasings)
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
        for (int i = 0; i < itemCasings.length; i++)
        {
            itemList.add(new ItemStack(this, 1, i));
        }
        return itemList;
    }

    @Override
    public TextureAtlasSprite getTexture(ItemStack itemStack)
    {
        return Ic2Icons.getTextures("ic2c_extras_items")[48 + itemStack.getMetadata()];
    }
}
