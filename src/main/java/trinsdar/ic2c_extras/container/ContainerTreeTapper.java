package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.slots.SlotBase;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.tileentity.TileEntityTreeTapper;

import javax.annotation.Nullable;

public class ContainerTreeTapper extends ContainerTileComponent<TileEntityTreeTapper> {
    public ContainerTreeTapper(InventoryPlayer player, TileEntityTreeTapper tile) {
        super(tile);
        int i;
        for ( i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.addSlotToContainer(new SlotOutput(player.player, tile,  i + j * 3, 63 + 18 * i, 14 + j * 18));
            }
        }
        for(i = 0; i < 4; ++i) {
            this.addSlotToContainer(new SlotUpgrade3(tile, i + 9, 152, 8 + 18 * i));
        }
        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, Ic2GuiComp.cropHarvesterChargeBox, Ic2GuiComp.machineChargePos));
    }

    @Override
    public ResourceLocation getTexture() {
        return this.getGuiHolder().getTexture();
    }

    @Override
    public int guiInventorySize() {
        return this.getGuiHolder().slotCount;
    }

    public static class SlotUpgrade3 extends SlotBase {
        TileEntityTreeTapper treeTapper;

        public SlotUpgrade3(TileEntityTreeTapper tile, int index, int xPosition, int yPosition) {
            super(tile, index, xPosition, yPosition);
            this.treeTapper = tile;
        }

        @Override
        public boolean isItemValid(@Nullable ItemStack stack) {
            return StackUtil.isStackEqual(stack, Ic2Items.overClockerUpgrade) || StackUtil.isStackEqual(stack, Ic2Items.padUpgradeBasicFieldUpgrade) || StackUtil.isStackEqual(stack, Ic2Items.padUpgradeFieldUpgrade) || StackUtil.isStackEqual(stack, Ic2Items.padUpgradeAdvFieldUpgrade);
        }

        @Override
        public void onSlotChanged() {
            this.treeTapper.setOverclockerUpgrade();
            super.onSlotChanged();
        }
    }
}
