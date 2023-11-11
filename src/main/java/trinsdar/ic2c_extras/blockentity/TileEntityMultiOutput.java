package trinsdar.ic2c_extras.blockentity;

import ic2.api.recipes.ingridients.inputs.IInput;
import ic2.api.recipes.ingridients.queue.SimpleStackOutput;
import ic2.api.recipes.ingridients.recipes.IRecipeOutput;
import ic2.core.block.base.tiles.impls.machine.single.BasicMachineTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public abstract class TileEntityMultiOutput extends BasicMachineTileEntity {
    public TileEntityMultiOutput(BlockPos pos, BlockState state, int slots, int energyPerTick, int maxProgress, int maxInput) {
        super(pos, state, slots, energyPerTick, maxProgress, maxInput);
    }

    public TileEntityMultiOutput(BlockPos pos, BlockState state, int slots, int upgradeSlots, int energyPerTick, int maxProgress, int maxInput) {
        super(pos, state, slots, upgradeSlots, energyPerTick, maxProgress, maxInput);
    }

    public TileEntityMultiOutput(BlockPos pos, BlockState state, int size, int upgradeSlots, int energyPerTick, int maxProgress, int maxEnergy, int maxInput) {
        super(pos, state, size, upgradeSlots, energyPerTick, maxProgress, maxEnergy, maxInput);
    }
    int outputIndex = 0;
    @Override
    public void operateOnce(int slot, IInput[] input, IRecipeOutput output, CompoundTag recipeData) {
        outputIndex = 0;
        super.operateOnce(slot, input, output, recipeData);
    }

    @Override
    protected void addOutput(int inputSlot, ItemStack output) {
        this.outputs.add(new SimpleStackOutput(output, 2 + outputIndex));
        outputIndex++;
    }
}
