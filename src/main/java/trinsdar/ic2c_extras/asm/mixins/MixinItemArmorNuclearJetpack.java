package trinsdar.ic2c_extras.asm.mixins;

import ic2.core.item.armor.base.ItemArmorElectricJetpackBase;
import ic2.core.item.armor.electric.ItemArmorNuclearJetpack;
import net.minecraft.inventory.EntityEquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import trinsdar.ic2c_extras.util.INuclearJetpackLogic;

@Mixin(ItemArmorNuclearJetpack.class)
public abstract class MixinItemArmorNuclearJetpack extends ItemArmorElectricJetpackBase implements INuclearJetpackLogic {
    public MixinItemArmorNuclearJetpack(int index, EntityEquipmentSlot equipmentSlotIn) {
        super(index, equipmentSlotIn);
    }
}
