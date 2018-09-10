package trinsdar.ic2c_extras.common;

import net.minecraftforge.fml.common.registry.GameRegistry;
import trinsdar.ic2c_extras.common.blocks.BlockBasic;

import static trinsdar.ic2c_extras.Ic2cExtras.MODID;

public class ModBlocks {
    @GameRegistry.ObjectHolder(MODID + ":blockbasic")
    public static BlockBasic blockBasic;
}

