package trinsdar.ic2c_extras.blocks.container;

import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.GeneratorChargeComp;
import ic2.core.inventory.slots.SlotCustom;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermoElectricGenerator;

public class ContainerThermoElectricGenerator extends ContainerTileComponent<TileEntityThermoElectricGenerator> {
    public ContainerThermoElectricGenerator(InventoryPlayer player, TileEntityThermoElectricGenerator tile) {
        super(tile);
        for (int y = 0; y < 2; ++y) {
            for (int x = 0; x < 3; ++x) {
                this.addSlotToContainer(new SlotCustom(tile, x + y * 3, 29 + x * 18, 27 + y * 18, tile.filter));
            }
        }
        this.addComponent(new GeneratorChargeComp(tile, tile.getEnergyBox(), tile.getEnergyPos()));
        this.addPlayerInventory(player);
    }

    @Override
    public ResourceLocation getTexture() {
        return this.getGuiHolder().getTexture();
    }

    @Override
    public int guiInventorySize() {
        return this.getGuiHolder().getSlotCount();
    }
}
