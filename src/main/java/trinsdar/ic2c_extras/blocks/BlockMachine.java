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
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityCutter;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityExtruder;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityFluidCanningMachine;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityImpellerizedRoller;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityLiquescentExtruder;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityMetalBender;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityPlasmaCutter;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityRoller;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalWasher;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityTreeTapper;
import trinsdar.ic2c_extras.util.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockMachine extends BlockMultiID {
    public BlockMachine(String name, LocaleComp comp){
        super(Material.IRON);
        this.setHardness(4.0F);
        this.setResistance(20.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(IC2CExtras.creativeTab);
        this.setRegistryName(name.toLowerCase());
        this.setUnlocalizedName(comp);
    }

    @Override
    public List<Integer> getValidMetas() {
        return Arrays.asList(0);
    }

    @Override
    public TileEntityBlock createNewTileEntity(World worldIn, int meta){
        if (this == Registry.oreWashingPlant){
            return new TileEntityOreWashingPlant();
        }else if (this == Registry.thermalCentrifuge){
            return new TileEntityThermalCentrifuge();
        }else if (this == Registry.thermalWasher){
            return new TileEntityThermalWasher();
        }else if (this == Registry.roller){
            return new TileEntityRoller();
        }else if (this == Registry.extruder){
            return new TileEntityExtruder();
        }else if (this == Registry.cutter){
            return new TileEntityCutter();
        }else if (this == Registry.impellerizedRoller){
            return new TileEntityImpellerizedRoller();
        }else if (this == Registry.liquescentExtruder){
            return new TileEntityLiquescentExtruder();
        }else if (this == Registry.plasmaCutter){
            return new TileEntityPlasmaCutter();
        }else if (this == Registry.metalBender){
            return new TileEntityMetalBender();
        }else if (this == Registry.fluidCanningMachine){
            return new TileEntityFluidCanningMachine();
        }else if (this == Registry.treeTapper){
            return new TileEntityTreeTapper();
        }else {
            return new TileEntityBlock();
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<>();
        if (this == Registry.thermalCentrifuge || this == Registry.thermalWasher || this == Registry.impellerizedRoller || this == Registry.liquescentExtruder || this == Registry.plasmaCutter || this == Registry.metalBender){
            drops.add(Ic2Items.advMachine);
            return drops;
        }else if (this == Registry.fluidCanningMachine){
            drops.add(Ic2Items.canner);
            return drops;
        }else{
            drops.add(Ic2Items.machine);
            return drops;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
        if (this == Registry.oreWashingPlant){
            return Ic2Icons.getTextures("orewashingplant");
        }else if (this == Registry.thermalCentrifuge){
            return Ic2Icons.getTextures("thermalcentrifuge");
        }else if (this == Registry.thermalWasher){
            return Ic2Icons.getTextures("thermalwasher");
        }else if (this == Registry.roller){
            return Ic2Icons.getTextures("roller");
        }else if (this == Registry.extruder){
            return Ic2Icons.getTextures("extruder");
        }else if (this == Registry.cutter){
            return Ic2Icons.getTextures("cutter");
        }else if (this == Registry.impellerizedRoller){
            return Ic2Icons.getTextures("impellerizedroller");
        }else if (this == Registry.liquescentExtruder){
            return Ic2Icons.getTextures("liquescentextruder");
        }else if (this == Registry.plasmaCutter){
            return Ic2Icons.getTextures("plasmacutter");
        }else if (this == Registry.metalBender){
            return Ic2Icons.getTextures("metalbender");
        }else if (this == Registry.fluidCanningMachine){
            return Ic2Icons.getTextures("fluidcanningmachine");
        } else if (this == Registry.treeTapper){
            return Ic2Icons.getTextures("treetapper");
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
