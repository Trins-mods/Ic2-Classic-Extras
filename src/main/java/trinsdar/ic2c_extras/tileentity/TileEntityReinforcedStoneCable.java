package trinsdar.ic2c_extras.tileentity;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.entity.explosion.ExplosionIC2;
import ic2.core.platform.registry.Ic2States;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityReinforcedStoneCable extends TileEntityMachine implements IEnergyConductor {
    protected boolean addedToEnergyNet;
    public TileEntityReinforcedStoneCable() {
        super(0);
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
        if (!this.addedToEnergyNet && this.isSimulating()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            this.addedToEnergyNet = true;
        }
    }

    @Override
    public void onUnloaded() {
        if (this.addedToEnergyNet && this.isSimulating()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.addedToEnergyNet = false;
        }
        super.onUnloaded();
    }

    @Override
    public void setFacing(EnumFacing face) {
        if (this.addedToEnergyNet) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        }

        this.addedToEnergyNet = false;
        super.setFacing(face);
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
        this.addedToEnergyNet = true;
    }

    @Override
    public void setFacingWithoutNotify(EnumFacing face) {
        if (this.addedToEnergyNet) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        }

        this.addedToEnergyNet = false;
        super.setFacingWithoutNotify(face);
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
        this.addedToEnergyNet = true;
    }

    @Override
    public double getConductionLoss() {
        return 0.8;
    }

    @Override
    public double getInsulationEnergyAbsorption() {
        return 99999999;
    }

    @Override
    public double getInsulationBreakdownEnergy() {
        return 99999999;
    }

    @Override
    public double getConductorBreakdownEnergy() {
        return 2048;
    }

    @Override
    public void removeInsulation() {

    }

    @Override
    public void removeConductor() {
        this.world.setBlockToAir(this.getPos());
        ExplosionIC2 explosion = new ExplosionIC2(this.world, null, new Vec3d(pos), 2.5F, 0.75F, 0.75F, null);
        explosion.doExplosion();
        this.world.setBlockState(this.getPos(), Ic2States.reinforcedStoneCrackt);
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return enumFacing == this.getFacing() || enumFacing == this.getFacing().getOpposite();
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing enumFacing) {
        return enumFacing == this.getFacing() || enumFacing == this.getFacing().getOpposite();
    }
}
