package trinsdar.ic2c_extras.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabIC2CExtras extends CreativeTabs
{
    public CreativeTabIC2CExtras(String label)
    {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(RegistryBlock.oreWashingPlant);
    }
}
