package trinsdar.ic2c_extras.util;

import ic2.core.fluid.IC2Tank;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class Ic2cExtrasTank extends IC2Tank {
    public Ic2cExtrasTank(int capacity) {
        super(capacity);
    }

    public Ic2cExtrasTank(@Nullable FluidStack fluidStack, int capacity) {
        super(fluidStack, capacity);
    }

    public Ic2cExtrasTank(Fluid fluid, int amount, int capacity) {
        super(fluid, amount, capacity);
    }

    @Override
    public void setCapacity(int capacity) {
        super.setCapacity(capacity);
        onContentsChanged();
    }
}
