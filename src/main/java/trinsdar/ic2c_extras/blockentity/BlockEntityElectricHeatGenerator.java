package trinsdar.ic2c_extras.blockentity;

import ic2.api.util.DirectionList;
import ic2.core.block.base.cache.CapabilityCache;
import ic2.core.block.base.cache.ICache;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.tiles.BaseElectricTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandler;
import tesseract.api.forge.TesseractCaps;
import tesseract.api.heat.IHeatHandler;

public class BlockEntityElectricHeatGenerator extends BaseElectricTileEntity implements IHeatTile, ITickListener {
    final Ic2cExtrasHeatHandler heatHandler;
    ICache<IHeatHandler> heatCache;
    public BlockEntityElectricHeatGenerator(BlockPos pos, BlockState state) {
        super(pos, state, 1, 32, 10000);
        heatHandler = new Ic2cExtrasHeatHandler(128, 0, 32, this);
        heatCache = new CapabilityCache<>(this, DirectionList.ALL, TesseractCaps.HEAT_CAPABILITY);
        this.addCaches(heatCache);
        this.addCapability(TesseractCaps.HEAT_CAPABILITY, heatHandler);
    }

    @Override
    public boolean supportsNotify() {
        return false;
    }

    @Override
    public BlockEntityType<?> createType() {
        return null;
    }

    @Override
    public BlockEntity getOwner() {
        return this;
    }

    @Override
    public boolean canInput(Direction direction) {
        return false;
    }

    @Override
    public boolean canOutput(Direction direction) {
        return direction == this.getFacing();
    }

    @Override
    public void onTick() {
        if (getStoredEU() > 0 && heatHandler.getHeat() < heatHandler.getHeatCap()){
            int min = Math.min(Math.min(10, getStoredEU()), heatHandler.getHeatCap() - heatHandler.getHeat());
            this.useEnergy(min);
            heatHandler.insertInternal(min, false);
            if (!this.isActive()) setActive(true);
        } else {
            if (this.isActive()) setActive(false);
        }
        this.heatHandler.update(this.isActive());
    }
}
