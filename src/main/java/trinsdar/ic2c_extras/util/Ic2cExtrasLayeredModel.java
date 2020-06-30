package trinsdar.ic2c_extras.util;

import ic2.core.Direction;
import ic2.core.platform.textures.Ic2Models;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.IFacingBlock;
import ic2.core.platform.textures.obj.ILayeredBlockModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.model.PerspectiveMapWrapper;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

public class Ic2cExtrasLayeredModel extends BaseModel {

    List<BakedQuad>[] quads;
    ILayeredBlockModel block;
    IBlockState meta;

    public Ic2cExtrasLayeredModel(ILayeredBlockModel model, IBlockState state) {
        super(Ic2Models.getBlockTransforms());
        this.block = model;
        this.meta = state;
    }

    public void init() {
        int layers = this.block.getLayers(this.meta);
        this.quads = this.createList(7);
        this.setParticalTexture(this.block.getParticleTexture(this.meta));
        EnumFacing blockFacing = EnumFacing.NORTH;
        ModelRotation rotation = ModelRotation.X0_Y0;
        if (this.block instanceof IFacingBlock) {
            IFacingBlock facing = (IFacingBlock)this.block;
            if (facing.hasRotation(this.meta)) {
                blockFacing = facing.getRotation(this.meta);
                Tuple<Integer, Integer> result = Direction.getModelRotation(blockFacing);
                rotation = ModelRotation.getModelRotation(result.getFirst(), result.getSecond());
            }
        }
        for (int i = 0; i < layers; ++i) {
            AxisAlignedBB box = this.block.getRenderBox(this.meta, i);
            EnumFacing[] facings;
            int facingLength;
            int j;
            EnumFacing side;
            ModelRotation sideRotation;
            BlockPartFace face;
            TextureAtlasSprite sprite;
            facings = EnumFacing.VALUES;
            facingLength = facings.length;
            for (j = 0; j < facingLength; ++j) {
                side = facings[j];
                sideRotation = this.getRotation(blockFacing, side, rotation);
                face = this.createBlockFace(side, i);
                sprite = this.block.getLayerTexture(this.meta, side, i);
                if (sprite != null) {
                    this.quads[side.getIndex()].add(this.getBakery().makeBakedQuad(this.getMinBox(side, box), this.getMaxBox(side, box), face, sprite, side, sideRotation, null, false, true));
                }
            }
        }
    }

    protected ModelRotation getRotation(EnumFacing facing, EnumFacing side, ModelRotation defaultRotation) {
        if (facing.getAxis().isHorizontal() && side.getAxis().isVertical()) {
            return defaultRotation;
        } else {
            return facing.getAxis().isVertical() && side.getAxis() == EnumFacing.Axis.X ? defaultRotation : ModelRotation.X0_Y0;
        }
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return this.quads[side == null ? 6 : side.getIndex()];
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return Pair.of(this, PerspectiveMapWrapper.handlePerspective(this, this.getCamera(), cameraTransformType).getRight());
    }

    protected BlockPartFace createBlockFace(EnumFacing side, int layer) {
        return new BlockPartFace(null, -1, "", new BlockFaceUV(new float[] { 0.0F, 0.0F, 16.0F,
                16.0F }, 0));
    }
}
