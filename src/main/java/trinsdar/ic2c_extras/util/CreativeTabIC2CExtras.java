package trinsdar.ic2c_extras.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTabIC2CExtras extends CreativeModeTab {
    public CreativeTabIC2CExtras(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Registry.ironCrushedOre);
    }
}
