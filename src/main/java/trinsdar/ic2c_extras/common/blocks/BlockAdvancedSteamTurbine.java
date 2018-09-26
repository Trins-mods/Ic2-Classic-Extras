package trinsdar.ic2c_extras.common.blocks;

import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.generator.BlockGenerator;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.tileentity.TileEntityAdvancedSteamTurbine;

import java.util.Arrays;
import java.util.List;

public class BlockAdvancedSteamTurbine extends BlockGenerator {
    public BlockAdvancedSteamTurbine(String blockName)
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

    @Override
    public TileEntityBlock createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityAdvancedSteamTurbine();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
        return Ic2Icons.getTextures("ic2c_extras_blocks");
    }
}
