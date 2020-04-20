package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.guicomponent.MachineHeatComp;

public class ContainerThermalCentrifuge extends ContainerTileComponent<TileEntityThermalCentrifuge> {

    public static Box2D machineChargeBox = new Box2D(11, 36, 14, 14);
    public static Vec2i machineChargePos = new Vec2i(176, 0);
    public static Box2D machineProgressBox = new Box2D(48, 35, 45, 17);
    public static Vec2i machineProgressPos = new Vec2i(176, 14);
    public static Box2D machineHeatBox = new Box2D(56, 52, 24, 17);
    public static Vec2i machineHeatPos = new Vec2i(176, 31);

    public ContainerThermalCentrifuge(InventoryPlayer player, TileEntityThermalCentrifuge tile) {
        super(tile);

        this.addSlotToContainer(new SlotCustom(tile, 0, 11, 17, tile.filter));
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 1, 11, 53));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 111, 17));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 111, 35));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 111, 53));

        for (int i = 0; i < 4; ++i) {
            this.addSlotToContainer(new SlotUpgrade(tile, 5 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, ContainerThermalCentrifuge.machineChargeBox, ContainerThermalCentrifuge.machineChargePos));
        this.addComponent(new MachineProgressComp(tile, ContainerThermalCentrifuge.machineProgressBox, ContainerThermalCentrifuge.machineProgressPos));
        this.addComponent(new MachineHeatComp(tile, ContainerThermalCentrifuge.machineHeatBox, ContainerThermalCentrifuge.machineHeatPos));
    }

    @Override
    public ResourceLocation getTexture() {
        return this.getGuiHolder().getGuiTexture();
    }

    @Override
    public int guiInventorySize() {
        return this.getGuiHolder().slotCount;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.getGuiHolder().canInteractWith(entityPlayer);
    }
}
