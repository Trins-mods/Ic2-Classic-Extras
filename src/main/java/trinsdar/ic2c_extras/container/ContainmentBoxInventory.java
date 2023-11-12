package trinsdar.ic2c_extras.container;

import ic2.core.inventory.base.IHasHeldGui;
import ic2.core.inventory.container.IC2Container;
import ic2.core.inventory.inv.PortableInventory;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ContainmentBoxInventory extends PortableInventory {
    public ContainmentBoxInventory(Player owner, IHasHeldGui held, ItemStack stack, Slot slot) {
        super(owner, held, stack, slot);
    }

    @Override
    public int getSlotCount() {
        return 12;
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int windowId) {
        return new ContainmentBoxContainer(this, player, this.getID(), windowId);
    }
}
