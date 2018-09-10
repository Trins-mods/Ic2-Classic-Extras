package trinsdar.ic2c_extras.common.items;

import net.minecraft.item.Item;
import trinsdar.ic2c_extras.Ic2cExtras;

public class ItemBase extends Item
{
    public ItemBase(String itemName)
    {
        this.setCreativeTab(Ic2cExtras.creativeTab);
        this.setRegistryName(Ic2cExtras.MODID, itemName);
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + itemName);
    }
}
