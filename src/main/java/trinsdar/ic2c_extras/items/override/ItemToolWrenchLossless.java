package trinsdar.ic2c_extras.items.override;

import ic2.core.item.tool.ItemToolWrench;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;

public class ItemToolWrenchLossless extends ItemToolWrench {
    @Override
    public boolean canOverrideLossChance(ItemStack stack) {
        return Ic2cExtrasConfig.removeLossyWrenchMechanic;
    }

    @Override
    public boolean hasBigCost(ItemStack stack) {
        return !Ic2cExtrasConfig.removeLossyWrenchMechanic;
    }
}
