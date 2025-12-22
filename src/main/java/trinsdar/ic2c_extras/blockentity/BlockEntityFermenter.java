package trinsdar.ic2c_extras.blockentity;

import ic2.api.util.ILocation;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.features.IWrenchableTile;
import ic2.core.block.base.tiles.BaseInventoryTileEntity;
import ic2.core.inventory.base.ITileGui;
import ic2.core.inventory.container.IC2Container;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityFermenter extends BaseInventoryTileEntity implements IWrenchableTile, ITickListener, ITileGui, ILocation {
    public BlockEntityFermenter(BlockPos pos, BlockState state, int size) {
        super(pos, state, 3);
    }

    @Override
    public BlockEntityType<?> createType() {
        return null;
    }

    @Override
    public boolean canSetFacing(Direction direction) {
        return false;
    }

    @Override
    public boolean canRemoveBlock(Player player) {
        return false;
    }

    @Override
    public double getDropRate(Player player) {
        return 0;
    }

    @Override
    public void onTick() {

    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return null;
    }
}
