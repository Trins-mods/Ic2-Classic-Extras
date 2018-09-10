package trinsdar.ic2c_extras.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemCrushedOre extends ItemBase
{
    public ItemCrushedOre(String itemName)
    {
        super(itemName);
        this.hasSubtypes = true;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        super.getSubItems(tab, items);
    }
}
