package trinsdar.ic2c_extras.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import trinsdar.ic2c_extras.IC2CExtras;

public class Ic2cExtrasTags {
    public static final TagKey<Item> RADIOACTIVE = getItemTag(new ResourceLocation(IC2CExtras.MODID, "radioactive"));


    public static TagKey<Item> getItemTag(ResourceLocation loc) {
        return TagKey.create(Registry.ITEM_REGISTRY, loc);
    }

    public static TagKey<Item> getForgeItemTag(String loc) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", loc));
    }
}
