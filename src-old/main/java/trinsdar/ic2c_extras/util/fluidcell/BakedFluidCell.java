package trinsdar.ic2c_extras.util.fluidcell;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import javax.vecmath.Matrix4f;
import java.util.List;

public class BakedFluidCell implements IBakedModel {
    private static ItemOverrideFluidCell OVERRIDE = new ItemOverrideFluidCell();
    private final List<BakedQuad> quads;
    private final TextureAtlasSprite particle;
    public final ModelFluidCell parent;
    public final VertexFormat format;

    public BakedFluidCell(List<BakedQuad> quads, ModelFluidCell parent, TextureAtlasSprite particle,
                          VertexFormat format) {
        this.quads = quads;
        this.parent = parent;
        this.particle = particle;
        this.format = format;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return quads;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return particle;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return OVERRIDE;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(
            ItemCameraTransforms.TransformType cameraTransformType) {
        return Pair.of(this, ModelUtils.getItemTransform(cameraTransformType));
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }
}
