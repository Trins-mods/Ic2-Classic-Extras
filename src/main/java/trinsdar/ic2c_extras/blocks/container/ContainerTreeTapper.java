package trinsdar.ic2c_extras.blocks.container;

import ic2.core.block.machine.med.container.ContainerCropHarvester;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityTreeTapper;
import trinsdar.ic2c_extras.util.guicomponent.SlotUpgrade2;

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
            this.addSlotToContainer(new SlotUpgrade2(tile, i + 9, 152, 8 + 18 * i));
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
}
