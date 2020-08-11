package trinsdar.ic2c_extras.asm.mixins;

import ic2.core.inventory.base.IC2ItemInventory;
import ic2.core.inventory.base.IHandHeldInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.item.inv.inventories.NuclearJetpackInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import trinsdar.ic2c_extras.container.ContainerNuclearJetpackNew;

@Mixin(NuclearJetpackInventory.class)
public abstract class MixinNuclearJetpackInventory extends IC2ItemInventory {
    public MixinNuclearJetpackInventory(EntityPlayer player, IHandHeldInventory inv, ItemStack item) {
        super(player, inv, item);
    }

    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        //noinspection ConstantConditions
        return new ContainerNuclearJetpackNew((NuclearJetpackInventory) ((Object)this), this.getID(), player.inventory);
    }
}
