package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ItemContainer;
import ic2.core.inventory.filter.IFilter;
import ic2.core.inventory.slot.FilterSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.init.IC2CExtrasTags;

public class ContainmentBoxContainer extends ItemContainer<ContainmentBoxInventory> {
    public static ResourceLocation GUI_LOCATION = new ResourceLocation(IC2CExtras.MODID, "textures/gui/containment_box.png");
    private static final IFilter FILTER = i -> i.is(IC2CExtrasTags.CONTAINMENT_BOX);
    public ContainmentBoxContainer(ContainmentBoxInventory key, Player player, int id, int windowID) {
        super(key, player, id, windowID);
        int columns = 4;
        int slotX = 53;

        for(int i = 0; i < key.getSlotCount(); ++i) {
            int x = i % columns;
            int y = i / columns;
            this.addSlot(new FilterSlot(key, i, slotX + 18 * x, 19 + 18 * y, FILTER));
        }
        this.addPlayerInventory(player.getInventory());
    }

    @Override
    public ResourceLocation getTexture() {
        return GUI_LOCATION;
    }
}
