package trinsdar.ic2c_extras.common.blocks;

import trinsdar.ic2c_extras.Ic2cExtras;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class OreWashingPlant extends Block{
    public OreWashingPlant(){
        super(Material.ROCK);
        setUnlocalizedName(Ic2cExtras.MODID + ".ore_wasing_plant");
        setRegistryName("ore_washing_plant");
    }
}
