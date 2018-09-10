package trinsdar.ic2c_extras.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import trinsdar.ic2c_extras.Ic2cExtras;

public class BlockBase extends Block
{
    public BlockBase(Material materialIn, String blockName)
    {
        super(materialIn);
        this.setRegistryName(Ic2cExtras.MODID, blockName);
        this.setUnlocalizedName(Ic2cExtras.MODID + "." + blockName);
    }
}
