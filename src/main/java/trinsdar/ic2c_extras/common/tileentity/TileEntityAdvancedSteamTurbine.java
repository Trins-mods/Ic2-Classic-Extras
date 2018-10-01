package trinsdar.ic2c_extras.common.tileentity;

import ic2.api.classic.energy.tile.IEnergySourceInfo;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.IInfoTile;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.classic.tile.machine.ISpeedMachine;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.comparator.ComparatorManager;
import ic2.core.block.base.util.comparator.comparators.ComparatorEUStorage;
import ic2.core.block.base.util.comparator.comparators.ComparatorSpeed;
import ic2.core.block.base.util.info.EmitterInfo;
import ic2.core.block.base.util.info.EnergyInfo;
import ic2.core.block.base.util.info.SourceTierInfo;
import ic2.core.block.base.util.info.SpeedInfo;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import ic2.core.block.generator.tile.TileEntityBasicSteamTurbine;
import ic2.core.fluid.IC2Tank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileEntityAdvancedSteamTurbine extends TileEntityBasicSteamTurbine{

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

    public double getOfferedEnergy() {
        return (double)Math.min(160, this.energy);
    }

    public int getSourceTier() {
        return 3;
    }

    public int getMaxSendingEnergy() {
        return 160;
    }

    @Override
    public String getName() {
        return "AdvancedSteamTurbine";
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }
}
