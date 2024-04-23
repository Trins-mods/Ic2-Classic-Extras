package trinsdar.ic2c_extras.blockentity;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IHeatTile {
    BlockEntity getOwner();

    boolean canInput(Direction direction);

    boolean canOutput(Direction direction);
}
