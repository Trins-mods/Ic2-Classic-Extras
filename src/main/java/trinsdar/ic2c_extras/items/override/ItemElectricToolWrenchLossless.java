package trinsdar.ic2c_extras.items.override;

import ic2.core.item.tool.electric.ItemElectricToolWrench;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;

public class ItemElectricToolWrenchLossless extends ItemElectricToolWrench {
    @Override
    public boolean canOverrideLossChance(ItemStack stack) {
        return Ic2cExtrasConfig.removeLossyWrenchMechanic || super.canOverrideLossChance(stack);
    }

    @Override
    public boolean hasBigCost(ItemStack stack) {
        return !Ic2cExtrasConfig.removeLossyWrenchMechanic;
    }
}
