package trinsdar.ic2c_extras.fluid;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

public class FluidItemStackHandler extends FluidHandlerItemStack {

    protected final ItemStack emptyContainer;

    public FluidItemStackHandler(ItemStack container, ItemStack emptyContainer, int capacity) {
        super(container, capacity);
        this.emptyContainer = emptyContainer;
    }

    @Override
    protected void setContainerToEmpty() {
        this.emptyContainer.setTagCompound(null);
        container = emptyContainer;
    }
}
