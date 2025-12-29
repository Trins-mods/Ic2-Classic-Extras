package trinsdar.ic2c_extras.blockentity;

import ic2.api.network.buffer.IInputBuffer;
import ic2.api.network.buffer.INetworkDataBuffer;
import ic2.api.network.buffer.IOutputBuffer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.gtreimagined.tesseract.TesseractCapUtils;
import org.gtreimagined.tesseract.api.hu.IHeatHandler;

import java.util.Optional;

public class Ic2cExtrasHeatHandler implements IHeatHandler, INetworkDataBuffer {
    int heat;
    int maxHeat;
    final int maxInput, maxOutput;
    final IHeatTile owner;

    public Ic2cExtrasHeatHandler(int maxHeat, int maxInput, int maxOutput, IHeatTile owner) {
        this.maxHeat = maxHeat;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
        this.owner = owner;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        this.heat = nbt.getInt("heat");
        this.maxHeat = nbt.getInt("maxHeat");
    }

    @Override
    public CompoundTag serialize(CompoundTag nbt) {
        nbt.putInt("heat", heat);
        nbt.putInt("maxHeat", maxHeat);
        return nbt;
    }

    @Override
    public int insert(int heat, boolean simulate) {
        if (!canInput()) return 0;
        int insert = Math.min(maxInput, Math.min(getHeatCap() - this.heat, heat));
        return insertInternal(insert, simulate);
    }

    @Override
    public int extract(int heat, boolean simulate) {
        if (!canOutput()) return 0;
        int extract = Math.min(maxOutput, Math.min(this.heat, heat));
        return extractInternal(extract, simulate);
    }

    public int insertInternal(int heat, boolean simulate) {
        int insert = Math.min(getHeatCap() - this.heat, heat);
        if (!simulate) this.heat += insert;
        return insert;
    }

    public int extractInternal(int heat, boolean simulate) {
        int extract = Math.min(this.heat, heat);
        if (!simulate) this.heat -= extract;
        return extract;
    }

    @Override
    public int getHeat() {
        return heat;
    }

    @Override
    public int getHeatCap() {
        return maxHeat;
    }

    @Override
    public void update(boolean active) {
        for (Direction dir : Direction.values()) {
            if (canOutput(dir)) {
                BlockEntity tile = owner.getOwner().getLevel().getBlockEntity(owner.getOwner().getBlockPos().relative(dir));
                if (tile == null) continue;
                Optional<IHeatHandler> handle = TesseractCapUtils.INSTANCE.getHeatHandler(tile, dir.getOpposite());
                if (handle.map(h -> !h.canInput(dir.getOpposite())).orElse(true)) continue;
                handle.ifPresent(eh -> transferHeat(this, eh));
            }
        }
    }

    public static void transferHeat(IHeatHandler from, IHeatHandler to) {
        int extracted = from.extract(Integer.MAX_VALUE, true);
        if (extracted > 0) {
            int inserted = to.insert(extracted, false);
            if (inserted > 0) {
                from.extract(inserted, false);
            }
        }
    }

    @Override
    public boolean canOutput() {
        return maxOutput > 0;
    }

    @Override
    public boolean canInput() {
        return maxInput > 0;
    }

    @Override
    public boolean canInput(Direction direction) {
        return canInput() && owner.canInput(direction);
    }

    @Override
    public boolean canOutput(Direction direction) {
        return canOutput() && owner.canOutput(direction);
    }

    @Override
    public long getMaxInsert() {
        return maxInput;
    }

    @Override
    public long getMaxExtract() {
        return maxOutput;
    }

    @Override
    public void write(IOutputBuffer iOutputBuffer) {
        iOutputBuffer.writeInt(heat);
    }

    @Override
    public void read(IInputBuffer iInputBuffer) {
        heat = iInputBuffer.readInt();
    }
}
