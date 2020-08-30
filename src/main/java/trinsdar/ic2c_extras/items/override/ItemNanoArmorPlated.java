package trinsdar.ic2c_extras.items.override;

import ic2.core.item.armor.electric.ItemArmorNanoSuit;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.ToolTipType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import trinsdar.ic2c_extras.util.IReactorPlated;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.util.List;
import java.util.Map;

public class ItemNanoArmorPlated extends ItemArmorNanoSuit implements IReactorPlated {
    public ItemNanoArmorPlated(int index, EntityEquipmentSlot equipmentSlotIn) {
        super(index, equipmentSlotIn);
    }

    @Override
    public boolean hasReactorPlate(ItemStack stack) {
        return StackUtil.getNbtData(stack).getBoolean("ReactorPlating");
    }

    @Override
    public void onSortedItemToolTip(ItemStack stack, EntityPlayer player, boolean debugTooltip, List<String> tooltip, Map<ToolTipType, List<String>> sortedTooltip) {
        super.onSortedItemToolTip(stack, player, debugTooltip, tooltip, sortedTooltip);
        List<String> s = sortedTooltip.get(ToolTipType.Shift);
        NBTTagCompound nbt = StackUtil.getNbtData(stack);
        if (nbt.hasKey("ReactorPlating")) {
            s.add(Ic2cExtrasLang.REACTOR_PLATED.getLocalized());
        }
    }
}
