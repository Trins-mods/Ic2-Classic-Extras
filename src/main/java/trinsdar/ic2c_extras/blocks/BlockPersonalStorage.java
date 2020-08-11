package trinsdar.ic2c_extras.blocks;

import ic2.core.IC2;
import ic2.core.block.personal.BlockPersonal;
import ic2.core.block.personal.tile.TileEntityPersonalEnergyStorage;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockPersonalStorage extends BlockPersonal {
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
                                ItemStack stack) {
        if (!IC2.platform.isRendering() && stack.getMetadata() == 5) {
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityPersonalEnergyStorage && nbt.hasKey("energy")) {
                ((TileEntityPersonalEnergyStorage) tile).storage.setStored(nbt.getInteger("energy"));
            }
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te, EntityPlayer player, int fortune) {
        int meta = this.getMetaFromState(state);
        if (meta == 5){
            List<ItemStack> items = new ArrayList();
            if (te instanceof TileEntityPersonalEnergyStorage) {
                TileEntityPersonalEnergyStorage block = (TileEntityPersonalEnergyStorage) te;
                ItemStack result = block.storage.getDrop();
                NBTTagCompound nbt = StackUtil.getOrCreateNbtData(result);
                if (block.storage.getStored() > 0){
                    nbt.setInteger("energy", (int)(.8f * block.storage.getStored()));
                }
                items.add(result);
            }
            return items;
        }
        return super.getWrenchDrops(world, pos, state, te, player, fortune);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {

    }
}
