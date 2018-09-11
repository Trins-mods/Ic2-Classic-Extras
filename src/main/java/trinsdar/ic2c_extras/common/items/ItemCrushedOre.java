package trinsdar.ic2c_extras.common.items;

import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import trinsdar.ic2c_extras.Ic2cExtras;

import java.util.ArrayList;
import java.util.List;

public class ItemCrushedOre extends ItemBase
{

    public static final String[] oreTypes = {"iron", "gold", "copper", "tin", "silver", "uranium", "lead"};

    public ItemCrushedOre(String itemName)
    {
        super(itemName);
        this.hasSubtypes = true;
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + itemName);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return this.getUnlocalizedName() + "_" + oreTypes[itemStack.getMetadata()];
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            int i = 0;

            for (String oreType : oreTypes)
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
        for (int i = 0; i < oreTypes.length; i++)
        {
            itemList.add(new ItemStack(this, 1, i));
        }
        return itemList;
    }

    @Override
    public TextureAtlasSprite getTexture(ItemStack itemStack)
    {
        return Ic2Icons.getTextures("ic2c_blocks")[itemStack.getMetadata()];
    }
}
