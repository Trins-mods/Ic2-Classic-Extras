package trinsdar.ic2c_extras.blocks;

import ic2.core.IC2;
import ic2.core.block.resources.BlockMetal;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;

import java.util.ArrayList;
import java.util.List;

public class BlockUraniumOre extends BlockMetal {
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int meta = this.getMetaFromState(state);
        if (meta == 2 && Ic2cExtrasRecipes.enableUraniumOreDropReplacement){
            List<ItemStack> list = new ArrayList();
            list.add(Ic2Items.uraniumOre);
            return list;
        }
        return super.getDrops(world, pos, state, fortune);
    }
}
