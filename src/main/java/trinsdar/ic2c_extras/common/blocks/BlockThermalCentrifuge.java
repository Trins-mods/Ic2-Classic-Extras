package trinsdar.ic2c_extras.common.blocks;

import ic2.api.tile.IWrenchable;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.machine.BlockLVMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IFacingBlock;
import ic2.core.platform.textures.obj.ITexturedBlock;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.IWrenchableTile;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.tileentity.TileEntityOreWashingPlant;

import java.util.Arrays;
import java.util.List;

public class BlockThermalCentrifuge extends BlockLVMachine
{

    public BlockThermalCentrifuge(String blockName)
    {
        this.setCreativeTab(Ic2cExtras.creativeTab);
        this.setRegistryName(Ic2cExtras.MODID, blockName);
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + blockName);
    }

    @Override
    public void onLoad()
    {}

    public List<Integer> getValidMetas()
    {
        return Arrays.asList(0);
    }

    @Override
    public TileEntityBlock createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityOreWashingPlant();
    }

    @Override
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
        return Ic2Icons.getTextures("ic2c_extras_blocks");
    }
}
