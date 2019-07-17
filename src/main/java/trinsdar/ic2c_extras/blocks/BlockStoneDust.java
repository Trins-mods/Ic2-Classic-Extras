package trinsdar.ic2c_extras.blocks;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.List;

public class BlockStoneDust extends BlockFalling implements ITexturedBlock {
    public BlockStoneDust() {
        super(Material.SAND);
        this.setRegistryName("stonedustblock");
        this.setUnlocalizedName("stoneDustBlock");
        this.setSoundType(SoundType.SAND);
        this.setHardness(0.5f);
        this.setHarvestLevel("shovel", 0);
        this.setCreativeTab(IC2CExtras.creativeTab);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox(IBlockState iBlockState) {
        return FULL_BLOCK_AABB;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTextureFromState(IBlockState iBlockState, EnumFacing enumFacing) {
        return Ic2Icons.getTextures(IC2CExtras.MODID + "_blocks")[3];
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

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 40, 0));
        }
    }
}
