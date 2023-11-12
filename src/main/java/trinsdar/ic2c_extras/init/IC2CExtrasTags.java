package trinsdar.ic2c_extras.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import trinsdar.ic2c_extras.IC2CExtras;

public class IC2CExtrasTags {
    public static final TagKey<Item> RADIOACTIVE = getItemTag(new ResourceLocation(IC2CExtras.MODID, "radioactive"));
    public static final TagKey<Item> CONTAINMENT_BOX = getItemTag(new ResourceLocation(IC2CExtras.MODID, "containment_box"));
    public static final TagKey<Item> LEAD_ORE = getForgeItemTag("ores/lead");
    public static final TagKey<Item> RAW_LEAD = getForgeItemTag("raw_materials/lead");
    public static final TagKey<Item> RAW_LEAD_BLOCK = getForgeItemTag("storage_blocks/raw_lead");


    public static TagKey<Item> getItemTag(ResourceLocation loc) {
        return TagKey.create(Registry.ITEM_REGISTRY, loc);
    }

    public static TagKey<Item> getForgeItemTag(String loc) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", loc));
    }

    public static TagKey<Block> getForgeBlockTag(String loc) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", loc));
    }
}
