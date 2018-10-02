package trinsdar.ic2c_extras.common.container;

import ic2.api.classic.tile.IMachine;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.common.tileentity.TileEntityMetalPress;

public class ContainerMetalPress  {
//    public ContainerMetalPress(InventoryPlayer player, TileEntityMetalPress tile) {
//        super((IHasGui) tile);
//        this.addSlotToContainer(new SlotCustom(tile, 0, 56, 17, null));
//        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 1, 56, 53));
//        this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 116, 35));
//
//        for(int i = 0; i < 4; ++i)
//        {
//            this.addSlotToContainer(new SlotUpgrade((IMachine) tile, 7 + i, 152, 8 + i * 18));
//        }
//
//        this.addPlayerInventory(player);
//        this.addComponent(new MachineChargeComp(tile, Ic2GuiComp.machineChargeBox, Ic2GuiComp.machineChargePos));
//        this.addComponent(new MachineProgressComp((IProgressMachine) tile, Ic2GuiComp.machineProgressBox, Ic2GuiComp.machineProgressPos));
//    }
//
//
//    @Override
//    public ResourceLocation getTexture() {
//        return null;
//    }
//
//    @Override
//    public int guiInventorySize() {
//        return 0;
//    }
//
//    @Override
//    public boolean canInteractWith(EntityPlayer playerIn) {
//        return false;
//    }
}
