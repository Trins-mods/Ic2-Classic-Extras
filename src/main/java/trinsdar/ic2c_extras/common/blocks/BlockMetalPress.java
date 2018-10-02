package trinsdar.ic2c_extras.common.blocks;

import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.machine.BlockLVMachine;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.tileentity.TileEntityMetalPress;

import java.util.Arrays;
import java.util.List;

public class BlockMetalPress extends BlockLVMachine {

    public BlockMetalPress(String blockName)
    {
        this.setCreativeTab(Ic2cExtras.creativeTab);
        this.setRegistryName(Ic2cExtras.MODID, blockName);
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + blockName);
    }

    @Override
    public void onLoad()
    {}

    public List<Integer> getValidMetas() {
        return Arrays.asList(0);
    }

//    @Override
//    public TileEntityBlock createNewTileEntity(World worldIn, int meta)
//    {
//        return new TileEntityMetalPress();
//    }

    @Override
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
        return Ic2Icons.getTextures("ic2c_extras_blocks");
    }
}
