package trinsdar.ic2c_extras.item;

import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorPlannerComponent;
import ic2.api.reactor.ISteamReactor;
import ic2.api.reactor.planner.ISimulatedReactor;
import ic2.api.reactor.planner.SimulatedStack;
import ic2.core.item.base.PropertiesBuilder;
import ic2.core.item.reactor.ReactorUraniumRod;
import ic2.core.item.reactor.base.IUraniumRod;
import ic2.core.item.reactor.planner.SimulatedUranium;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.nuclear.MOX;

public class ItemReactorRod extends ReactorUraniumRod {
    public ItemReactorRod(String itemName, IUraniumRod uranium, int rodCount, int componentId) {
        super(itemName, "nuclear_rods", itemName.replace("_rod", ""), new PropertiesBuilder().group(IC2CExtras.CREATIVE_TAB), uranium, rodCount, componentId);
        this.id = new ResourceLocation(IC2CExtras.MODID, itemName);
        IC2Items.registerItem(this);
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack source, int myX, int myY, int sourceX, int sourceY, boolean heatRun, boolean damageTick) {
        if (getUranium() == MOX.INSTANCE && !(reactor instanceof ISteamReactor)){
            if (!heatRun) {
                reactor.addOutput(this.getUranium().getPulseEU() * getEuModifier(reactor.getHeat(), reactor.getMaxHeat()));
            }
            return true;
        }
        return super.acceptUraniumPulse(stack, reactor, source, myX, myY, sourceX, sourceY, heatRun, damageTick);
    }

    @Override
    public SimulatedStack createSimulationComponent(ItemStack self) {
        if (getUranium() == MOX.INSTANCE){
            return new SimulatedUranium(this.getComponentID(self), this.getMaxDamage(self), this.getUranium(), this.getRodCount()){
                @Override
                public boolean acceptUraniumPulse(ISimulatedReactor reactor, int x, int y, SimulatedStack source, int sourceX, int sourceY, boolean heatRun, boolean damageTick) {
                    if (!(reactor instanceof ISteamReactor)){
                        if (reactor.isSimulatingPulses()) {
                            reactor.addFuelPulse();
                        }
                        if (!heatRun) {
                            reactor.addOutput(getUranium().getPulseEU() * getEuModifier(reactor.getHeat(), reactor.getMaxHeat()));
                        }
                        return true;
                    }
                    return super.acceptUraniumPulse(reactor, x, y, source, sourceX, sourceY, heatRun, damageTick);
                }
            };
        }
        return super.createSimulationComponent(self);
    }

    public float getEuModifier(int heat, int maxHeat){
        float percent = Math.min(1f, (float)heat / maxHeat);
        return 4.0F * percent + 1.0F;
    }
}
