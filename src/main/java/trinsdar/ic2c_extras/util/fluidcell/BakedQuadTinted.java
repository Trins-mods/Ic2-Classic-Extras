package trinsdar.ic2c_extras.util.fluidcell;

import net.minecraft.client.renderer.block.model.BakedQuad;

public class BakedQuadTinted extends BakedQuad {
    private int[] vertexData;

    public BakedQuadTinted(BakedQuad quad, int rgb) {
        super(quad.getVertexData(), quad.getTintIndex(), quad.getFace(), quad.getSprite(), quad.shouldApplyDiffuseLighting(), quad.getFormat());
        this.vertexData = quad.getVertexData();

        for(int i = 0; i < 4; ++i) {
            this.vertexData[this.format.getColorOffset() / 4 + this.format.getIntegerSize() * i] = ModelUtils.rgbToABGR(rgb);
        }

    }

    public int[] getVertexData() {
        return this.vertexData;
    }
}
