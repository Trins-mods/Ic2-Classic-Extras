package trinsdar.ic2c_extras;

import ic2.core.platform.registries.IC2Items;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import trinsdar.ic2c_extras.init.ModItems;

public class CreativeTabIC2CExtras extends CreativeModeTab {
    public CreativeTabIC2CExtras(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModItems.CRUSHED_IRON_ORE);
    }
}
