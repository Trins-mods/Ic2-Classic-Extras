package trinsdar.ic2c_extras.blockentity;

import ic2.api.network.buffer.NetworkInfo;
import ic2.api.tiles.readers.IFuelStorage;
import ic2.api.util.DirectionList;
import ic2.api.util.ILocation;
import ic2.core.block.base.cache.CapabilityCache;
import ic2.core.block.base.cache.ICache;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.features.IWrenchableTile;
import ic2.core.block.base.misc.comparator.ComparatorNames;
import ic2.core.block.base.misc.comparator.types.base.FuelComparator;
import ic2.core.block.base.tiles.BaseInventoryTileEntity;
import ic2.core.inventory.base.ITileGui;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.api.forge.TesseractCaps;
import tesseract.api.heat.IHeatHandler;

public abstract class BlockEntityBaseFuelBox extends BaseInventoryTileEntity implements IWrenchableTile, ITickListener, ITileGui, IFuelStorage, IHeatTile, ILocation {
    @NetworkInfo
    public int fuel = 0;
    @NetworkInfo
    final Ic2cExtrasHeatHandler heatHandler;
    @NetworkInfo
    public int production;
    ICache<IHeatHandler> heatCache;
    public BlockEntityBaseFuelBox(BlockPos pos, BlockState state, int size) {
        super(pos, state, size);
        heatHandler = new Ic2cExtrasHeatHandler(0, 0, 32, this);
        heatCache = new CapabilityCache<>(this, DirectionList.ALL, TesseractCaps.HEAT_CAPABILITY);
        this.addCaches(heatCache);
        this.addCapability(TesseractCaps.HEAT_CAPABILITY, heatHandler);
        this.addGuiFields("heatHandler", "fuel");
        this.addComparator(new FuelComparator("fuel", ComparatorNames.FUEL, this));
    }

    @Override
    public boolean canSetFacing(Direction direction) {
        return direction.getAxis().isHorizontal() && direction != this.getFacing();
    }

    @Override
    public boolean canRemoveBlock(Player player) {
        return true;
    }

    @Override
    public double getDropRate(Player player) {
        return 1;
    }

    public boolean needsFuel() {
        return this.fuel <= 0 && this.heatHandler.getHeat() + this.production <= this.heatHandler.getHeatCap();
    }

    public boolean needsHeat() {
        return this.fuel > 0 && this.heatHandler.getHeat() + this.production <= this.heatHandler.getHeatCap();
    }

    public abstract boolean gainFuel();

    public boolean gainEnergy() {
        if (this.needsHeat()) {
            this.heatHandler.insertInternal(Math.min(production, heatHandler.getHeatCap() - heatHandler.getHeat()), false);
            this.consumeFuel();
            return true;
        } else {
            return false;
        }
    }

    protected void consumeFuel() {
        --this.fuel;
    }

    @Override
    public void onTick() {
        if (this.needsFuel() && this.gainFuel()) {
            this.updateGuiField("fuel");
        }

        boolean active = this.gainEnergy();
        if (active) {
            this.updateGuiField("fuel");
            this.updateGuiField("heatHandler");
        }
        this.setActive(active);
        this.handleComparators();
    }

    @Override
    public int getFuel() {
        return fuel;
    }

    @Override
    public BlockEntity getOwner() {
        return this;
    }

    @Override
    public IHeatHandler getHeatHandler() {
        return heatHandler;
    }

    @Override
    public boolean canInput(Direction direction) {
        return false;
    }

    @Override
    public boolean canOutput(Direction direction) {
        return direction == this.getFacing();
    }
}
