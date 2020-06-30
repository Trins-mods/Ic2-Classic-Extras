package trinsdar.ic2c_extras.blocks;

import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import ic2.core.platform.textures.obj.ILayeredBlockModel;
import ic2.core.util.helpers.BlockStateContainerIC2;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.tileentity.TileEntityCutter;
import trinsdar.ic2c_extras.tileentity.TileEntityElectricDisenchanter;
import trinsdar.ic2c_extras.tileentity.TileEntityExtruder;
import trinsdar.ic2c_extras.tileentity.TileEntityFluidCanningMachine;
import trinsdar.ic2c_extras.tileentity.TileEntityImpellerizedRoller;
import trinsdar.ic2c_extras.tileentity.TileEntityLiquescentExtruder;
import trinsdar.ic2c_extras.tileentity.TileEntityMetalBender;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityPlasmaCutter;
import trinsdar.ic2c_extras.tileentity.TileEntityRoller;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalWasher;
import trinsdar.ic2c_extras.tileentity.TileEntityTreeTapper;
import trinsdar.ic2c_extras.util.Ic2cExtrasLayeredModel;
import trinsdar.ic2c_extras.util.Icons;
import trinsdar.ic2c_extras.util.Registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockMachine extends BlockMultiID implements ILayeredBlockModel, ICustomModeledBlock {
    public BlockMachine(String name, LocaleComp comp) {
        super(Material.IRON);
        this.setHardness(4.0F);
        this.setResistance(20.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(IC2CExtras.creativeTab);
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase());
        this.setUnlocalizedName(comp);
    }

    @Override
    public List<Integer> getValidMetas() {
        return Collections.singletonList(0);
    }

    @Override
    public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
        if (this == Registry.oreWashingPlant) {
            return new TileEntityOreWashingPlant();
        } else if (this == Registry.thermalCentrifuge) {
            return new TileEntityThermalCentrifuge();
        } else if (this == Registry.thermalWasher) {
            return new TileEntityThermalWasher();
        } else if (this == Registry.roller) {
            return new TileEntityRoller();
        } else if (this == Registry.extruder) {
            return new TileEntityExtruder();
        } else if (this == Registry.cutter) {
            return new TileEntityCutter();
        } else if (this == Registry.impellerizedRoller) {
            return new TileEntityImpellerizedRoller();
        } else if (this == Registry.liquescentExtruder) {
            return new TileEntityLiquescentExtruder();
        } else if (this == Registry.plasmaCutter) {
            return new TileEntityPlasmaCutter();
        } else if (this == Registry.metalBender) {
            return new TileEntityMetalBender();
        } else if (this == Registry.fluidCanningMachine) {
            return new TileEntityFluidCanningMachine();
        } else if (this == Registry.treeTapper) {
            return new TileEntityTreeTapper();
        } else if (this == Registry.electricDisenchanter){
            return new TileEntityElectricDisenchanter();
        } else {
            return null;
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<>();
        if (this == Registry.thermalCentrifuge || this == Registry.thermalWasher || this == Registry.impellerizedRoller || this == Registry.liquescentExtruder || this == Registry.plasmaCutter || this == Registry.metalBender) {
            drops.add(Ic2Items.advMachine);
        } else if (this == Registry.fluidCanningMachine) {
            drops.add(Ic2Items.canner);
        } else if (this == Registry.electricDisenchanter){
            drops.add(Ic2Items.electricEnchanter);
        } else {
            drops.add(Ic2Items.machine);
        }
        return drops;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite[] getIconSheet(int meta) {
        return Icons.getTextureData(this);
    }

    @Override
    public int getMaxSheetSize(int meta) {
        return 1;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainerIC2(this, allFacings, active);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    @Override
    public IBlockState getDefaultBlockState() {
        IBlockState state = this.getDefaultState().withProperty(active, false);
        if (this.hasFacing()) {
            state = state.withProperty(allFacings, EnumFacing.NORTH);
        }

        return state;
    }

    @Override
    public List<IBlockState> getValidStateList() {
        IBlockState def = getDefaultState();
        List<IBlockState> states = new ArrayList<IBlockState>();
        for (EnumFacing side : EnumFacing.VALUES) {
            states.add(def.withProperty(allFacings, side).withProperty(active, false));
            states.add(def.withProperty(allFacings, side).withProperty(active, true));
        }
        return states;
    }

    @Override
    public List<IBlockState> getValidStates() {
        return getBlockState().getValidStates();
    }

    @Override
    public boolean isLayered(IBlockState iBlockState) {
        return this == Registry.electricDisenchanter;
    }

    @Override
    public int getLayers(IBlockState iBlockState) {
        return this == Registry.electricDisenchanter ? 2 : 1;
    }

    @Override
    public AxisAlignedBB getRenderBox(IBlockState iBlockState, int i) {
        return FULL_BLOCK_AABB;
    }

    @Override
    public TextureAtlasSprite getLayerTexture(IBlockState state, EnumFacing enumFacing, int i) {
        if (this == Registry.electricDisenchanter && enumFacing.getAxis() != EnumFacing.Axis.Y && i == 1){
            String active = state.getValue(BlockMultiID.active) ? "_active" : "";
            return Ic2Icons.getTextures("electric_disenchanter_side" + active + "_overlay")[0];
        }
        return this.getTextureFromState(state, enumFacing);
    }

    @Override
    public List<IBlockState> getValidModelStates() {
        return this.getValidStateList();
    }

    @Override
    public BaseModel getModelFromState(IBlockState iBlockState) {
        return new Ic2cExtrasLayeredModel(this, iBlockState);
    }
}
