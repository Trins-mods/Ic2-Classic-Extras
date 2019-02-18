package trinsdar.ic2c_extras.blocks.tileentity;

import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;

public class TileEntityTreeTapper extends TileEntityElecMachine implements ITickable, IHasGui {
    public TileEntityTreeTapper() {
        super(13, 128);
    }

    @Override
    public boolean supportsNotify() {
        return false;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public boolean hasGui(EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public void update() {

    }
}
