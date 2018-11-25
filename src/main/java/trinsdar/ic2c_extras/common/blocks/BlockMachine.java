package trinsdar.ic2c_extras.common.blocks;

import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.tileentity.TileEntityMetalPress;
import trinsdar.ic2c_extras.common.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.common.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.common.util.RegistryBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockMachine extends BlockMultiID {
    public BlockMachine(String name){
        super(Material.IRON);
        this.setHardness(4.0F);
        this.setResistance(20.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(Ic2cExtras.creativeTab);
        this.setRegistryName(name);
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + name);
    }

    @Override
    public List<Integer> getValidMetas() {
        return Arrays.asList(0);
    }

    @Override
    public TileEntityBlock createNewTileEntity(World worldIn, int meta){
        if (this == RegistryBlock.metalPress){
            return new TileEntityMetalPress();
        }else if (this == RegistryBlock.oreWashingPlant){
            return new TileEntityOreWashingPlant();
        }else if (this == RegistryBlock.thermalCentrifuge){
            return new TileEntityThermalCentrifuge();
        }else{
            return null;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
        return Ic2Icons.getTextures("ic2c_extras_blocks");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTextureFromState(IBlockState state, EnumFacing side) {
        if (this == RegistryBlock.metalPress){
            if (side == EnumFacing.SOUTH) {
                return Ic2Icons.getTextures("ic2c_extras_blocks")[13];
            }else {
                return Ic2Icons.getTextures("ic2c_extras_blocks")[12];
            }
        }else if (this == RegistryBlock.oreWashingPlant){
            if (side == EnumFacing.SOUTH) {
                return Ic2Icons.getTextures("ic2c_extras_blocks")[18];
            }else {
                return Ic2Icons.getTextures("ic2c_extras_blocks")[22];
            }
        }else if (this == RegistryBlock.thermalCentrifuge){
            if (side == EnumFacing.SOUTH) {
                return Ic2Icons.getTextures("ic2c_extras_blocks")[17];
            }else {
                return Ic2Icons.getTextures("ic2c_extras_blocks")[16];
            }
        }else{
            return Ic2Icons.getTextures("ic2c_extras_blocks")[255];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getParticleTexture(IBlockState state) {
        return Ic2Icons.getTextures("ic2c_extras_blocks")[16];
    }

    @Override
    public List<IBlockState> getValidStateList()
    {
        IBlockState def = getDefaultState();
        List<IBlockState> states = new ArrayList<IBlockState>();
        for(EnumFacing side : EnumFacing.VALUES)
        {
            states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active, false));
            states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active, true));
        }
        return states;
    }

    @Override
    public List<IBlockState> getValidStates()
    {
        return getBlockState().getValidStates();
    }
}
