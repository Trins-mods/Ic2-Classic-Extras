package trinsdar.ic2c_extras.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import trinsdar.ic2c_extras.events.RadiationEvent;

import java.util.Locale;

@ZenClass("mods.ic2.Radiation")
@ZenRegister
public class RadiationSupport {

    @ZenMethod
    public static void addEntry(IItemStack stack){
        CraftTweakerPlugin.apply(new RadiationAction(CraftTweakerMC.getItemStack(stack)));
    }

    private static final class RadiationAction implements IAction{
        final ItemStack stack;

        private RadiationAction(ItemStack stack){
            this.stack = stack;
        }

        @Override
        public void apply() {
            for (ItemStack entry : RadiationEvent.radiation){
                if (entry.isItemEqual(stack)){
                    CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > "
                            + "Entry Already exists!");
                    return;
                }
            }
            RadiationEvent.radiation.add(stack);
        }

        @Override
        public String describe() {
            return String.format(Locale.ENGLISH, "Add Item[%s] to %s", this.stack, RadiationEvent.radiation);
        }
    }
}
