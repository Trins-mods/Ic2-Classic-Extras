package trinsdar.ic2c_extras.common.tileentity;

import ic2.core.block.generator.tile.TileEntityBasicSteamTurbine;
import ic2.core.fluid.IC2Tank;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class TileEntityAdvancedSteamTurbine extends TileEntityBasicSteamTurbine{

    public TileEntityAdvancedSteamTurbine(){
        super();
        this.setCustomName("tileAdvancedSteamTurbine");
    }
    public FluidTank tank = new IC2Tank(4000);

    public int getOutput() {
        return Math.min(160, this.energy);
    }

    @Override
    public void update() {
        this.setActive(this.speed > 0.0F);
        if (this.energy >= 4000) {
            this.addChange(-0.005F);
        } else {
            float value = 64.0F * this.speed;
            if (value > 64.0F) {
                value = 64.0F;
            }

            FluidStack drained = this.drain(64.0F);
            if (drained == null) {
                this.addChange(-0.002F);
            } else {
                this.addChange(this.limit((float)drained.amount - value, -0.005F, 0.005F));
            }

            float producing = 128.0F * this.speed;
            if (drained != null) {
                producing += (float)drained.amount / 32.0F;
            }

            if (producing >= 1.0F) {
                this.addEnergy((int)producing);
            }

            this.updateComparators();
        }
    }

    private float limit(float base, float min, float max) {
        return MathHelper.clamp(base, min, max);
    }

    @Override
    public double getOfferedEnergy() {
        return (double)Math.min(160, this.energy);
    }

    @Override
    public int getSourceTier() {
        return 3;
    }

    @Override
    public int getMaxSendingEnergy() {
        return 160;
    }
    
}
