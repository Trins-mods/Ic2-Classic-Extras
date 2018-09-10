package trinsdar.ic2c_extras.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import trinsdar.ic2c_extras.Ic2cExtras;

public class ItemCrushedOre extends ItemBase
{

    private final String[] oreTypes = {"iron", "gold", "copper", "tin", "silver", "uran"};

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
}
