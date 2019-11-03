package trinsdar.ic2c_extras.blocks;

import ic2.core.block.resources.BlockMetal;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;

import java.util.ArrayList;
import java.util.List;

public class BlockUraniumOre extends BlockMetal {
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int meta = this.getMetaFromState(state);
        if (meta == 2 && Ic2cExtrasConfig.uramiumOreDrop){
            List<ItemStack> list = new ArrayList();
            list.add(Ic2Items.uraniumOre.copy());
            return list;
        }
        return super.getDrops(world, pos, state, fortune);
    }
}
