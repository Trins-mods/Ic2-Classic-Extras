package trinsdar.ic2c_extras.util.guicomponent;

import ic2.core.inventory.filters.IFilter;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.tileentity.base.TileEntityContainerInputBase;

public class MachineFilter2 implements IFilter {
    TileEntityContainerInputBase machine;

    public MachineFilter2(TileEntityContainerInputBase machine) {
        this.machine = machine;
    }

    public boolean matches(ItemStack stack) {
        return !stack.isEmpty() && this.machine.isValidContainer(stack);
    }
}
