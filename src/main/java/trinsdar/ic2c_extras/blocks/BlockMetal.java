package trinsdar.ic2c_extras.blocks;

import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.List;

public class BlockMetal extends Block implements ITexturedBlock {

    int index;
    public BlockMetal(String name, int index){
        super(Material.IRON);
        this.setHardness(4.0F);
        this.setResistance(10.0F);
        this.index = index;
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(IC2CExtras.creativeTab);
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase());
        this.setUnlocalizedName(name);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) {
        return true;
    }


    @Override
    public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
        return FULL_BLOCK_AABB;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
        return Ic2Icons.getTextures("ic2c_extras_blocks")[index];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getParticleTexture(IBlockState state) {
        return this.getTextureFromState(state, EnumFacing.SOUTH);
    }

    @Override
    public List<IBlockState> getValidStates() {
        return this.blockState.getValidStates();
    }

    @Override
    public IBlockState getStateFromStack(ItemStack stack) {
        return this.getStateFromMeta(stack.getMetadata());
    }
}
