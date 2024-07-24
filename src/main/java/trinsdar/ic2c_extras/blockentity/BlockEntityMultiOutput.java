package trinsdar.ic2c_extras.blockentity;

import ic2.api.recipes.ingridients.queue.MultiStackOutput;
import ic2.core.block.base.tiles.impls.machine.single.BasicMachineTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockEntityMultiOutput extends BasicMachineTileEntity {
    public BlockEntityMultiOutput(BlockPos pos, BlockState state, int slots, int energyPerTick, int maxProgress, int maxInput) {
        super(pos, state, slots, energyPerTick, maxProgress, maxInput);
    }

    public BlockEntityMultiOutput(BlockPos pos, BlockState state, int slots, int upgradeSlots, int energyPerTick, int maxProgress, int maxInput) {
        super(pos, state, slots, upgradeSlots, energyPerTick, maxProgress, maxInput);
    }

    public BlockEntityMultiOutput(BlockPos pos, BlockState state, int size, int upgradeSlots, int energyPerTick, int maxProgress, int maxEnergy, int maxInput) {
        super(pos, state, size, upgradeSlots, energyPerTick, maxProgress, maxEnergy, maxInput);
    }

    protected void addOutput(int inputSlot, ItemStack output) {
        this.outputs.add(new MultiStackOutput(output, this.getOutputSlots()));
    }

    public abstract int[] getOutputSlots();
}
