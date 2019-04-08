package trinsdar.ic2c_extras;

import ic2.api.classic.addon.IC2Plugin;
import ic2.api.classic.addon.PluginBase;
import ic2.api.classic.addon.misc.IOverrideObject;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import java.util.Map;

@IC2Plugin(id = "ic2c_extrasconfig", name = "Ic2cExtrasConfigLoader", version = IC2CExtras.VERSION)
public class IC2CExtrasConfigLoader extends PluginBase {
    @Override
    public boolean canLoad(Side side) {
        return true;
    }

    @Override
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent, Map<String, IOverrideObject> map)
    {
        Config.init();
    }
}
