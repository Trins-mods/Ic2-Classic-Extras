package trinsdar.ic2c_extras.jei;


import mezz.jei.api.IModPlugin;
import net.minecraft.resources.ResourceLocation;
import trinsdar.ic2c_extras.IC2CExtras;

import javax.annotation.Nonnull;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin {
    public String oreWashingId = "oreWashing";
    public String thermalCentrifugeId = "thermalCentrifuge";
    public String rollerId = "roller";
    public String extruderId = "extruder";
    public String cutterId = "cutter";
    public String metalBenderId = "metalBender";
    public String fluidCanningId = "fluidCanning";

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(IC2CExtras.MODID, "jei_plugin");
    }
}
