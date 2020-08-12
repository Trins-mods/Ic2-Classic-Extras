package trinsdar.ic2c_extras.asm.mixins;

import ic2.core.item.armor.electric.ItemArmorQuantumNuclearJetplate;
import ic2.core.item.armor.electric.ItemArmorQuantumSuit;
import ic2.core.item.inv.logics.NuclearJetpackLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import trinsdar.ic2c_extras.util.INuclearJetpackLogic;

@Mixin(ItemArmorQuantumNuclearJetplate.class)
public abstract class MixinItemArmorQuantumNuclearJetPlate extends ItemArmorQuantumSuit implements INuclearJetpackLogic {
    @Shadow
    public ItemArmorQuantumNuclearJetplate.NuclearQuantumJetpack jetpack;
    public MixinItemArmorQuantumNuclearJetPlate(int index, EntityEquipmentSlot equipmentSlotIn) {
        super(index, equipmentSlotIn);
    }

    @Override
    public NuclearJetpackLogic getLogic(EntityPlayer player, ItemStack stack) {
        return jetpack.getLogic(player, stack);
    }
}
