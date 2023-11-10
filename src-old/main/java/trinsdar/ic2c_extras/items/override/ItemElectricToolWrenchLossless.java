package trinsdar.ic2c_extras.items.override;

import ic2.core.IC2;
import ic2.core.item.tool.electric.ItemElectricToolWrench;
import ic2.core.util.obj.ToolTipType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;

import java.util.List;
import java.util.Map;

public class ItemElectricToolWrenchLossless extends ItemElectricToolWrench {
    @Override
    public boolean canOverrideLossChance(ItemStack stack) {
        return Ic2cExtrasConfig.removeLossyWrenchMechanic || super.canOverrideLossChance(stack);
    }

    @Override
    public boolean hasBigCost(ItemStack stack) {
        return !Ic2cExtrasConfig.removeLossyWrenchMechanic;
    }

    @Override
    public void onSortedItemToolTip(ItemStack stack, EntityPlayer player, boolean debugTooltip, List<String> tooltip, Map<ToolTipType, List<String>> sortedTooltip) {
        super.onSortedItemToolTip(stack, player, debugTooltip, tooltip, sortedTooltip);
        if (Ic2cExtrasConfig.removeLossyWrenchMechanic){
            tooltip.remove(1);
            // removing from same index twice since on the first remove index 2 becomes index 1
            tooltip.remove(1);
            sortedTooltip.get(ToolTipType.Ctrl).clear();
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (IC2.platform.isSimulating() && IC2.keyboard.isModeSwitchKeyDown(playerIn) && Ic2cExtrasConfig.removeLossyWrenchMechanic) {
            return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
