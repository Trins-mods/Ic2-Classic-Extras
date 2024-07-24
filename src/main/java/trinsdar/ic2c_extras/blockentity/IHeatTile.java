package trinsdar.ic2c_extras.blockentity;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import tesseract.api.heat.IHeatHandler;

public interface IHeatTile {
    BlockEntity getOwner();

    IHeatHandler getHeatHandler();

    boolean canInput(Direction direction);

    boolean canOutput(Direction direction);
}
