package trinsdar.ic2c_extras.common.blocks;

import ic2.api.tile.IWrenchable;
import ic2.core.IC2;
import ic2.core.inventory.base.IHasGui;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IFacingBlock;
import ic2.core.platform.textures.obj.ITexturedBlock;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.IWrenchableTile;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockThermalCentrifuge extends BlockContainerBase implements IWrenchable, ITexturedBlock, IFacingBlock {

    public static final IProperty<EnumFacing> facingProperty = PropertyEnum.create("facing", EnumFacing.class);
    public static final IProperty<Boolean> active = PropertyBool.create("active");

    public BlockThermalCentrifuge(Material materialIn, String blockName) {
        super(materialIn, blockName);
        this.setDefaultState(this.blockState.getBaseState().withProperty(facingProperty, EnumFacing.NORTH).withProperty(active, false));
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof IClickable)
        {
            IClickable click = (IClickable) te;
            if (click.hasRightClick() && click.onRightClick(playerIn, hand, facing, FMLCommonHandler.instance().getEffectiveSide()))
            {
                return true;
            }
        }

        if (playerIn.isSneaking())
        {
            return false;
        }
        else
        {
            return te instanceof IHasGui && (IC2.platform.isRendering() || IC2.platform.launchGui(playerIn, (IHasGui) te, hand));
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(facingProperty, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(facingProperty).getIndex() + (state.getValue(active) ? 6 : 0);
    }


    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(facingProperty, EnumFacing.getFront(meta - (meta >= 6 ? 6 : 0))).withProperty(active, meta >= 6 ? true : false);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{facingProperty, active});
    }

    @Override
    public boolean hasRotation(IBlockState iBlockState)
    {
        return iBlockState.getValue(facingProperty) != null;
    }

    @Override
    public EnumFacing getRotation(IBlockState iBlockState) {
        return iBlockState.getValue(facingProperty);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState)
    {
        return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing)
    {
        int index = 0;
        switch (enumFacing)
        {
            case UP:
                index = 1;
                break;
            case DOWN:
                index = 0;
                break;
            case NORTH:
                index = 3;
                break;
            case SOUTH:
                index = 5;
                break;
            case EAST:
                index = 2;
                break;
            case WEST:
                index = 4;
                break;
        }
        return Ic2Icons.getTextures("ic2c_extras_blocks")[index + (iBlockState.getValue(active) ? 6 : 0)];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getParticleTexture(IBlockState iBlockState)
    {
        return Ic2Icons.getTextures("ic2c_extras_blocks")[(iBlockState.getValue(active) ? 6 : 0)];
    }

    @Override
    public List<IBlockState> getValidStates()
    {
        return this.blockState.getValidStates();
    }

    @Override
    public IBlockState getStateFromStack(ItemStack itemStack) { return null; }

    @Override
    public EnumFacing getFacing(World world, BlockPos blockPos) {
        IBlockState state = world.getBlockState(blockPos);
        return state.getValue(facingProperty);
    }

    @Override
    public boolean setFacing(World world, BlockPos blockPos, EnumFacing enumFacing, EntityPlayer entityPlayer) {
        return false;
    }

    @Override
    public boolean wrenchCanRemove(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
        TileEntity tile = world.getTileEntity(blockPos);
        return tile != null && tile instanceof IWrenchableTile ? ((IWrenchableTile) tile).canRemoveBlock(entityPlayer) : false;
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
        return null;
    }


}