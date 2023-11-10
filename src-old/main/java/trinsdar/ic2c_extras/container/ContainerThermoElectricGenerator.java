package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.GeneratorChargeComp;
import ic2.core.inventory.slots.SlotCustom;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.tileentity.TileEntityThermoElectricGeneratorBase;

public class ContainerThermoElectricGenerator extends ContainerTileComponent<TileEntityThermoElectricGeneratorBase> {
    public ContainerThermoElectricGenerator(InventoryPlayer player, TileEntityThermoElectricGeneratorBase tile) {
        super(tile);
        for (int y = 0; y < 2; ++y) {
            for (int x = 0; x < 3; ++x) {
                this.addSlotToContainer(new SlotCustom(tile, x + y * 3, 29 + x * 18, 27 + y * 18, tile.getFilter()));
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
