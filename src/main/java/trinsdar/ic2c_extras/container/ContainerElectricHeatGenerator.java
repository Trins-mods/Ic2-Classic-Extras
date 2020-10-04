package trinsdar.ic2c_extras.container;

import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.util.math.Box2D;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.tileentity.TileEntityElectricHeatGenerator;
import trinsdar.ic2c_extras.util.Registry;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentHeatGenerator;

public class ContainerElectricHeatGenerator extends ContainerTileComponent<TileEntityElectricHeatGenerator> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(IC2CExtras.MODID, "textures/gui/electricheatgenerator.png");
    public ContainerElectricHeatGenerator(InventoryPlayer player, TileEntityElectricHeatGenerator tile) {
        super(tile);
        IFilter filter = new BasicItemFilter(Registry.coil);
        for (int x = 0; x < 5; x++){
            this.addSlotToContainer(new SlotCustom(tile, x,44 + (18 * x), 27, filter));
            this.addSlotToContainer(new SlotCustom(tile, x + 5,44 + (18 * x), 45, filter));
        }
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 10, 8, 62));
        this.addPlayerInventory(player);
        this.addComponent(new GuiComponentHeatGenerator(new Box2D(34, 66, 108, 13), tile));
        this.addComponent(new MachineChargeComp(tile, new Box2D(9, 43, 14, 15), Ic2GuiComp.machineChargePos));
    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    @Override
    public void onGuiLoaded(GuiIC2 gui) {
        gui.dissableInvName();
    }

    @Override
    public int guiInventorySize() {
        return this.getGuiHolder().getSlotCount();
    }
}
