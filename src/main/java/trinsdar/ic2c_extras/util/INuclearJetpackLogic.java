package trinsdar.ic2c_extras.util;

import ic2.core.item.inv.logics.NuclearJetpackLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface INuclearJetpackLogic {
    NuclearJetpackLogic getLogic(EntityPlayer player, ItemStack stack);
}
