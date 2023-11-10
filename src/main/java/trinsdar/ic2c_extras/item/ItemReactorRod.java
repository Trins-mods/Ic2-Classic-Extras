package trinsdar.ic2c_extras.item;

import ic2.core.item.reactor.ReactorUraniumRod;
import ic2.core.item.reactor.base.IUraniumRod;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.resources.ResourceLocation;
import trinsdar.ic2c_extras.IC2CExtras;

public class ItemReactorRod extends ReactorUraniumRod {
    public ItemReactorRod(String itemName, IUraniumRod uranium, int rodCount, int componentId) {
        super(itemName, "nuclear_rods", itemName.replace("_rod", ""), uranium, rodCount, componentId);
        this.id = new ResourceLocation(IC2CExtras.MODID, itemName);
        IC2Items.registerItem(this);
    }
}
