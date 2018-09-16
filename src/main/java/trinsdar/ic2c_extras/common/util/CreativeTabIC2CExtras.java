package trinsdar.ic2c_extras.common.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.common.blocks.RegistryBlock;
import trinsdar.ic2c_extras.common.items.RegistryItem;

public class CreativeTabIC2CExtras extends CreativeTabs
{
    public CreativeTabIC2CExtras(String label)
    {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(RegistryBlock.blockOreWashingPlant);
    }
}
