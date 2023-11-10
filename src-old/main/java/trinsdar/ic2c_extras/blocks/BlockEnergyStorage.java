package trinsdar.ic2c_extras.blocks;

import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.block.wiring.BlockElectric;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IItemContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockEnergyStorage extends BlockElectric {

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
                                ItemStack stack) {
        if (!IC2.platform.isRendering() && stack.getMetadata() < 3 || stack.getMetadata() == 5) {
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityElectricBlock && nbt.hasKey("energy")) {
                ((TileEntityElectricBlock) tile).setStored(nbt.getInteger("energy"));
            }
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te, EntityPlayer player, int fortune) {
        int meta = this.getMetaFromState(state);
        if (meta < 3 || meta == 5){
            List<ItemStack> items = new ArrayList();
            ItemStack result = new ItemStack(this, 1, meta);
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(result);
            if (te instanceof IWorldNameable) {
                IWorldNameable name = (IWorldNameable)te;
                if (name.hasCustomName()) {
                    result.setStackDisplayName(name.getName());
                }
            }

            items.add(result);
            if (te instanceof IItemContainer) {
                items.addAll(((IItemContainer)te).getDrops());
            }
            if (te instanceof TileEntityElectricBlock){
                TileEntityElectricBlock block = (TileEntityElectricBlock)te;
                if (block.getStored() > 0){
                    nbt.setInteger("energy", (int)(.8f * block.getStored()));
                }
            }
            return items;
        }
        return super.getWrenchDrops(world, pos, state, te, player, fortune);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {

    }
}
