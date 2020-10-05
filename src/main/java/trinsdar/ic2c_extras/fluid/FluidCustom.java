package trinsdar.ic2c_extras.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import trinsdar.ic2c_extras.IC2CExtras;

import java.awt.Color;

public class FluidCustom extends Fluid {
    public FluidCustom(String fluidName, int r, int g, int b) {
        super(fluidName, new ResourceLocation(IC2CExtras.MODID, "fluids/" + fluidName + "_still"), new ResourceLocation(IC2CExtras.MODID, "fluids/" + fluidName + "_flow"), new Color(r, g, b));
    }
}
