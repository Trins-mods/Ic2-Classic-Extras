package trinsdar.ic2c_extras.blocks;

import ic2.core.block.base.BlockMultiID;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import ic2.core.platform.textures.obj.ILayeredBlockModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.Ic2cExtrasLayeredModel;
import trinsdar.ic2c_extras.util.Registry;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.util.List;

public class BlockDisenchanter extends BlockMachine implements ILayeredBlockModel, ICustomModeledBlock {
    public BlockDisenchanter() {
        super("electricdisenchanter", Ic2cExtrasLang.ELECTRIC_DISENCHANTER);
    }

    @Override
    public boolean isLayered(IBlockState iBlockState) {
        return this == Registry.electricDisenchanter;
    }

    @Override
    public int getLayers(IBlockState iBlockState) {
        return this == Registry.electricDisenchanter ? 2 : 1;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBox(IBlockState iBlockState, int i) {
        return FULL_BLOCK_AABB;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getLayerTexture(IBlockState state, EnumFacing enumFacing, int i) {
        if (this == Registry.electricDisenchanter && enumFacing.getAxis() != EnumFacing.Axis.Y && i == 1){
            String active = state.getValue(BlockMultiID.active) ? "_active" : "";
            return Ic2Icons.getTextures("electric_disenchanter_side" + active + "_overlay")[0];
        }
        return this.getTextureFromState(state, enumFacing);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public List<IBlockState> getValidModelStates() {
        return this.getValidStateList();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BaseModel getModelFromState(IBlockState iBlockState) {
        return new Ic2cExtrasLayeredModel(this, iBlockState);
    }
}
