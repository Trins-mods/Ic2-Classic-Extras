package trinsdar.ic2c_extras.blocks;

import ic2.core.IC2;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.config.IC2Config;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.helpers.BlockStateContainerIC2;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
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
import trinsdar.ic2c_extras.tileentity.TileEntityAutocraftingTable;
import trinsdar.ic2c_extras.tileentity.TileEntityCutter;
import trinsdar.ic2c_extras.tileentity.TileEntityElectricDisenchanter;
import trinsdar.ic2c_extras.tileentity.TileEntityElectricHeatGenerator;
import trinsdar.ic2c_extras.tileentity.TileEntityExtruder;
import trinsdar.ic2c_extras.tileentity.TileEntityFermenter;
import trinsdar.ic2c_extras.tileentity.TileEntityFluidCanningMachine;
import trinsdar.ic2c_extras.tileentity.TileEntityImpellerizedRoller;
import trinsdar.ic2c_extras.tileentity.TileEntityLiquescentExtruder;
import trinsdar.ic2c_extras.tileentity.TileEntityMetalBender;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityPlasmaCutter;
import trinsdar.ic2c_extras.tileentity.TileEntityReinforcedStoneCable;
import trinsdar.ic2c_extras.tileentity.TileEntityRoller;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalWasher;
import trinsdar.ic2c_extras.tileentity.TileEntityTreeTapper;
import trinsdar.ic2c_extras.util.Icons;
import trinsdar.ic2c_extras.util.Registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockMachine extends BlockMultiID{
    public BlockMachine(String name, LocaleComp comp) {
        super(Material.IRON);
        this.setHardness(4.0F);
        this.setResistance(20.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(IC2CExtras.creativeTab);
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase());
        this.setTranslationKey(comp);
        if (name.equals("reinforcedencasedcable")){
            this.setHardness(80.0F);
            this.setResistance(150.0F);
            this.setHarvestLevel("pickaxe", 2);
        }
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
        } else if (this == Registry.autocraftingTable){
            return new TileEntityAutocraftingTable();
        } else if (this == Registry.reinforcedEncasedCable){
            return new TileEntityReinforcedStoneCable();
        } else if (this == Registry.electricHeatGenerator){
            return new TileEntityElectricHeatGenerator();
        } else if (this == Registry.fermenter){
            return new TileEntityFermenter();
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
        } else if (this == Registry.reinforcedEncasedCable){
            drops.add(new ItemStack(this));
        } else if (this == Registry.electricHeatGenerator){
            drops.add(IC2.config.getFlag("SteelRecipes") ? new ItemStack(Registry.steelIngot, 3) : StackUtil.copyWithSize(Ic2Items.refinedIronIngot, 3));
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
}
