package trinsdar.ic2c_extras.util;

import ic2.api.classic.recipe.machine.MachineOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class FluidMachineOutput extends MachineOutput {
    FluidStack fluid;
    public FluidMachineOutput(NBTTagCompound meta, FluidStack fluid, List<ItemStack> items) {
        super(meta, items);
        this.fluid = fluid;
    }

    public FluidMachineOutput(NBTTagCompound meta, FluidStack fluid, ItemStack... items) {
        super(meta, items);
        this.fluid = fluid;
    }

    public FluidStack getFluid() {
        return fluid;
    }
}
