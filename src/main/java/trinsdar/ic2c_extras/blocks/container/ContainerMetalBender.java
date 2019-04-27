package trinsdar.ic2c_extras.blocks.container;

import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.gui.components.base.MachineSpeedComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.platform.lang.storage.Ic2GuiLang;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityMetalBender;
import trinsdar.ic2c_extras.util.guicomponent.SlotUpgrade2;

public class ContainerMetalBender extends ContainerTileComponent<TileEntityMetalBender> {
    public ContainerMetalBender(InventoryPlayer player, TileEntityMetalBender tile)
    {
        super(tile);
        this.addSlotToContainer(new SlotCustom(tile, 0, 47, 17, null));
        this.addSlotToContainer(new SlotCustom(tile, 1, 65, 17, null));
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 2, 56, 53));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 116, 35));


        for(int i = 0; i < 2; ++i)
        {
            this.addSlotToContainer(new SlotUpgrade2(tile, 4 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, Ic2GuiComp.machineChargeBox, Ic2GuiComp.machineChargePos));
        this.addComponent(new MachineProgressComp(tile, Ic2GuiComp.machineProgressBox, Ic2GuiComp.machineProgressPos));
        this.addComponent(new MachineSpeedComp(tile, Ic2GuiLang.machineSpeed));
    }

    @Override
    public ResourceLocation getTexture()
    {
        return this.getGuiHolder().getGuiTexture();
    }

    @Override
    public int guiInventorySize()
    {
        return this.getGuiHolder().slotCount;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.getGuiHolder().canInteractWith(entityPlayer);
    }
}
