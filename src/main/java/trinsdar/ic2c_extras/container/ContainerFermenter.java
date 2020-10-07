package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidUtil;
import trinsdar.ic2c_extras.tileentity.TileEntityFermenter;

public class ContainerFermenter extends ContainerTileComponent<TileEntityFermenter> {
    public ContainerFermenter(InventoryPlayer player, TileEntityFermenter tile) {
        super(tile);
        this.addSlotToContainer(new SlotOutput(player.player, tile, 0, 86, 83));
        this.addSlotToContainer(new SlotCustom(tile,1, 14, 48, new FluidItemFilter()));
        this.addSlotToContainer(new SlotOutput(player.player, tile,2, 14, 66));
        this.addSlotToContainer(new SlotCustom(tile,3, 148, 43, new FluidItemFilter()));
        this.addSlotToContainer(new SlotOutput(player.player, tile,4, 148, 61));
        this.addSlotToContainer(new SlotUpgrade(tile,5, 143, 83));
        this.addSlotToContainer(new SlotUpgrade(tile,6, 125, 83));
    }

    @Override
    public ResourceLocation getTexture() {
        return null;
    }

    @Override
    public int guiInventorySize() {
        return 0;
    }

    public static class FluidItemFilter implements IFilter {
        @Override
        public boolean matches(ItemStack stack) {
            return !stack.isEmpty() && FluidUtil.getFluidHandler(stack) != null;
        }
    }
}
