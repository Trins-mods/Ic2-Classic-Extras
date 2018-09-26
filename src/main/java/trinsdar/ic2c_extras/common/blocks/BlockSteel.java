package trinsdar.ic2c_extras.common.blocks;

import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import trinsdar.ic2c_extras.Ic2cExtras;

import java.util.Arrays;
import java.util.List;

public class BlockSteel extends Block {

    public BlockSteel(String blockName){
        super(Material.IRON);
        this.setHardness(4.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(Ic2cExtras.creativeTab);
        this.setRegistryName(Ic2cExtras.MODID, blockName);
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + blockName);
    }

    public List<Integer> getValidMetas() {
        return Arrays.asList(0);
    }
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {

        return true;
    }

    public TextureAtlasSprite getTexture(int meta)
    {
        return Ic2Icons.getTextures("ic2c_extras_blocks")[21];
    }
}
