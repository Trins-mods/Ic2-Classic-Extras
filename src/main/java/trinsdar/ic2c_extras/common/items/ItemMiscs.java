package trinsdar.ic2c_extras.common.items;

import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

//Added for compatibility with other mods and to make making lead crops easier.
public class ItemMiscs extends ItemBase {

    public static final String[] itemMiscs = {"lead_ingot", "lead_dust", "stone_dust", "slag"};

    public ItemMiscs(String itemName){
        super(itemName);
        this.hasSubtypes = true;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName() + "_" + itemMiscs[itemStack.getMetadata()];
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            int i = 0;

            for (String itemMisc : itemMiscs)
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
        for (int i = 0; i < itemMiscs.length; i++)
        {
            itemList.add(new ItemStack(this, 1, i));
        }
        return itemList;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(ItemStack itemStack)
    {
        return Ic2Icons.getTextures("ic2c_extras_items")[64 + itemStack.getMetadata()];
    }
}
