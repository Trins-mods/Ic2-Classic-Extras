package trinsdar.ic2c_extras.blocks;

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
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityCutter;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityExtruder;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityImpellerizedRoller;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityLiquescentExtruder;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityPlasmaCutter;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityRoller;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.registry.RegistryBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockMachine extends BlockMultiID {
    public BlockMachine(String name){
        super(Material.IRON);
        this.setHardness(4.0F);
        this.setResistance(20.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(IC2CExtras.creativeTab);
        this.setRegistryName(name);
        this.setUnlocalizedName(IC2CExtras.MODID + "." + name);
    }

    @Override
    public List<Integer> getValidMetas() {
        return Arrays.asList(0);
    }

    @Override
    public TileEntityBlock createNewTileEntity(World worldIn, int meta){
        if (this == RegistryBlock.oreWashingPlant){
            return new TileEntityOreWashingPlant();
        }else if (this == RegistryBlock.thermalCentrifuge){
            return new TileEntityThermalCentrifuge();
        }else if (this == RegistryBlock.roller){
            return new TileEntityRoller();
        }else if (this == RegistryBlock.extruder){
            return new TileEntityExtruder();
        }else if (this == RegistryBlock.cutter){
            return new TileEntityCutter();
        }else if (this == RegistryBlock.impellerizedRoller){
            return new TileEntityImpellerizedRoller();
        }else if (this == RegistryBlock.liquescentExtruder){
            return new TileEntityLiquescentExtruder();
        }else if (this == RegistryBlock.plasmaCutter){
            return new TileEntityPlasmaCutter();
        }else{
            return new TileEntityBlock();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
        if (this == RegistryBlock.oreWashingPlant){
            return Ic2Icons.getTextures("orewashingplant");
        }else if (this == RegistryBlock.thermalCentrifuge){
            return Ic2Icons.getTextures("thermalcentrifuge");
        }else if (this == RegistryBlock.roller){
            return Ic2Icons.getTextures("roller");
        } else if (this == RegistryBlock.extruder){
            return Ic2Icons.getTextures("extruder");
        }else if (this == RegistryBlock.cutter){
            return Ic2Icons.getTextures("cutter");
        }else if (this == RegistryBlock.impellerizedRoller){
            return Ic2Icons.getTextures("impellerizedroller");
        }else if (this == RegistryBlock.liquescentExtruder){
            return Ic2Icons.getTextures("liquescentextruder");
        }else if (this == RegistryBlock.plasmaCutter){
            return Ic2Icons.getTextures("plasmacutter");
        }else if (this == RegistryBlock.blastFurnace){
            return Ic2Icons.getTextures("blastfurnace");
        }else{
            return Ic2Icons.getTextures("roller");
        }
    }

    @Override
    public int getMaxSheetSize(int meta)
    {
        return 1;
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
