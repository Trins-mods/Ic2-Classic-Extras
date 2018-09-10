package trinsdar.ic2c_extras.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import trinsdar.ic2c_extras.Ic2cExtras;

import javax.annotation.Nullable;

public class BlockContainerBase extends BlockContainer
{
    public BlockContainerBase(Material materialIn, String blockName)
    {
        super(materialIn);
        this.setCreativeTab(Ic2cExtras.creativeTab);
        this.setRegistryName(Ic2cExtras.MODID, blockName);
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + blockName);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return null;
    }
}
