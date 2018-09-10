package trinsdar.ic2c_extras.common.blocks;

import trinsdar.ic2c_extras.Ic2cExtras;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends BlockBase
{
    public BlockBasic(String name)
    {
        super(Material.ROCK, name);
        setUnlocalizedName(Ic2cExtras.MODID + ".blockbasic");
        setRegistryName("blockbasic");
    }
}
