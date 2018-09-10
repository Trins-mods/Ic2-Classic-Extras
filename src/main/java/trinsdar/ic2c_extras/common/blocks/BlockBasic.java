package trinsdar.ic2c_extras.common.blocks;

import trinsdar.ic2c_extras.Ic2cExtras;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block{
    public BlockBasic(){
        super(Material.ROCK);
        setUnlocalizedName(Ic2cExtras.MODID + ".blockbasic");
        setRegistryName("blockbasic");
    }
}
