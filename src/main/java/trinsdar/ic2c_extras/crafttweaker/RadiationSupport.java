package trinsdar.ic2c_extras.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import trinsdar.ic2c_extras.events.RadiationEvent;

@ZenClass("mods.ic2.Radiation")
@ZenRegister
public class RadiationSupport {

    @ZenMethod
    public static void addEntry(IItemStack stack){
        for (ItemStack entry : RadiationEvent.radiation){
            if (entry.isItemEqual(CraftTweakerMC.getItemStack(stack))){
                CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > "
                        + "Entry Already exists!");
                return;
            }
        }
        RadiationEvent.radiation.add(CraftTweakerMC.getItemStack(stack));
    }
}
