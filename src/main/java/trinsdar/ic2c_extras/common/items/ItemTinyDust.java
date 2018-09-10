package trinsdar.ic2c_extras.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import trinsdar.ic2c_extras.Ic2cExtras;

public class ItemTinyDust extends ItemBase {
    private final String[] tinyDustTypes = {"iron", "gold", "copper", "tin", "silver", "uran", "lead", "obsidian", "bronze"};

    public ItemTinyDust(String itemName) {
        super(itemName);
        this.hasSubtypes = true;
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + itemName);
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

            for (String tinyDustTypes : tinyDustTypes)
            {
                items.add(new ItemStack(this, 1, i));
                i++;
            }
        }
    }
}
