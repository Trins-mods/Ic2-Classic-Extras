package trinsdar.ic2c_extras.item;

import ic2.core.item.reactor.ReactorIsotopicUraniumRod;
import ic2.core.item.reactor.base.IUraniumRod;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.resources.ResourceLocation;
import trinsdar.ic2c_extras.IC2CExtras;

public class ItemIsotopicRod extends ReactorIsotopicUraniumRod {
    public ItemIsotopicRod(String itemName, IUraniumRod uranium, int componentId) {
        super(itemName, "nuclear_rods", itemName.replace("_rod", ""), uranium, componentId);
        this.id = new ResourceLocation(IC2CExtras.MODID, itemName);
        IC2Items.registerItem(this);
    }
}
