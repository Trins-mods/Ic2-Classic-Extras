package trinsdar.ic2c_extras.container;

import ic2.core.inventory.base.IC2ItemInventory;
import ic2.core.inventory.base.IHandHeldInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemInventoryContainmentBox extends IC2ItemInventory {

    public ItemInventoryContainmentBox(EntityPlayer player, IHandHeldInventory inv, ItemStack item) {
        super(player, inv, item);
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ItemContainerContainmentBox(this, this.getID(), player.inventory);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return GuiComponentContainer.class;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return !player.isDead;
    }

    @Override
    public boolean hasGui(EntityPlayer player) {
        return true;
    }

    @Override
    public int getInventorySize() {
        return 12;
    }
}
