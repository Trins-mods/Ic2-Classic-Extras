package trinsdar.ic2c_extras.blocks;

import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.tileentity.TileEntityAdvancedSteamTurbine;
import trinsdar.ic2c_extras.tileentity.TileEntityThermoElectricGenerator;
import trinsdar.ic2c_extras.util.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockIc2cEGenerator extends BlockMultiID {
    public BlockIc2cEGenerator(String name, LocaleComp comp)
    {
        super(Material.IRON);
        this.setHardness(4.0F);
        this.setResistance(20.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(IC2CExtras.creativeTab);
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase());
        this.setUnlocalizedName(comp);
    }

    public List<Integer> getValidMetas() {
        return Arrays.asList(0);
    }

    @Override
    public TileEntityBlock createNewTileEntity(World worldIn, int meta)
    {
        if (this == Registry.advancedSteamTurbine) {
            return new TileEntityAdvancedSteamTurbine();
        }else if (this == Registry.thermoElectricGenerator){
            return new TileEntityThermoElectricGenerator();
        }else if (this == Registry.thermoElectricGeneratorMKII){
            return new TileEntityThermoElectricGenerator.TileEntityThermoElectricGeneratorMkII();
        } else{
            return new TileEntityBlock();
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
        if (this == Registry.advancedSteamTurbine){
            return Ic2Icons.getTextures("advancedsteamturbine");
        }else if (this == Registry.thermoElectricGenerator){
            return Ic2Icons.getTextures("thermoelectricgenerator");
        }else if (this == Registry.thermoElectricGeneratorMKII){
            return Ic2Icons.getTextures("thermoelectricgeneratormkii");
        }else {
            return Ic2Icons.getTextures("advancedsteamturbine");
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<>();
        if (this == Registry.advancedSteamTurbine){
            drops.add(Ic2Items.basicTurbine);
            return drops;
        }else{
            drops.add(Ic2Items.machine);
            return drops;
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
