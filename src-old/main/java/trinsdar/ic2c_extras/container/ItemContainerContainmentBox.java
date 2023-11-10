package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ContainerItemComponent;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.events.RadiationEvent;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

public class ItemContainerContainmentBox extends ContainerItemComponent<ItemInventoryContainmentBox> {

    public ItemContainerContainmentBox(ItemInventoryContainmentBox inv, int id, InventoryPlayer player) {
        super(inv, id);
        for (int x = 0; x < 4; x++){
            for (int y = 0; y < 3; y++){
                this.addSlotToContainer(new SlotCustom(inv, x + y * 4, 53 + x * 18, 19 + y * 18, new RadiationFilter()));
            }
        }
        this.addPlayerInventory(player, 0, 0);
    }

    @Override
    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.CONTAINMENT_BOX;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return getGuiHolder().canInteractWith(player);
    }
    public static class RadiationFilter implements IFilter{

        @Override
        public boolean matches(ItemStack stack) {
            for (ItemStack item : RadiationEvent.radiation) {
                if (StackUtil.isStackEqual(item, stack)) {
                    return true;
                }
            }
            return false;
        }
    }
}
