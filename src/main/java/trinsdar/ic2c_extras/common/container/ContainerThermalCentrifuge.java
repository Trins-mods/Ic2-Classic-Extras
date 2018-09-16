package trinsdar.ic2c_extras.common.container;

import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.common.tileentity.TileEntityBase;

public class ContainerThermalCentrifuge extends ContainerTileComponent<TileEntityBase> {
    public ContainerThermalCentrifuge(InventoryPlayer player, TileEntityBase tile) {
        super(tile);

        this.addSlotToContainer(new SlotCustom(tile, 0, 8, 17, null));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 113, 13));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 113, 36));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 113, 59));

        for(int i = 0; i < 4; ++i) {
            this.addSlotToContainer(new SlotUpgrade(tile, 7 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, Ic2GuiComp.machineChargeBox, Ic2GuiComp.machineChargePos));
        this.addComponent(new MachineProgressComp(tile, Ic2GuiComp.machineProgressBox, Ic2GuiComp.machineProgressPos));

    }

    @Override
    public ResourceLocation getTexture()
    {
        return this.getGuiHolder().getGuiTexture();
    }

    @Override
    public int guiInventorySize()
    {
        return 9;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return true;
    }
}