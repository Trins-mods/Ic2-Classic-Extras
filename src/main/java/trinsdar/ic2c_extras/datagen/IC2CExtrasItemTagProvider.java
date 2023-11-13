package trinsdar.ic2c_extras.datagen;

import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.registries.IC2Tags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.init.IC2CExtrasTags;
import trinsdar.ic2c_extras.init.ModItems;

public class IC2CExtrasItemTagProvider extends ItemTagsProvider {
    public IC2CExtrasItemTagProvider(DataGenerator arg, BlockTagsProvider arg2, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, arg2, IC2CExtras.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.copy(IC2CExtrasTags.getForgeBlockTag("ores"), IC2CExtrasTags.getForgeItemTag("ores"));
        this.copy(IC2CExtrasTags.getForgeBlockTag("storage_blocks"), IC2CExtrasTags.getForgeItemTag("storage_blocks"));
        this.copy(IC2CExtrasTags.getForgeBlockTag("ores/lead"), IC2CExtrasTags.getForgeItemTag("ores/lead"));
        this.copy(IC2CExtrasTags.getForgeBlockTag("storage_blocks/lead"), IC2CExtrasTags.getForgeItemTag("storage_blocks/lead"));
        this.copy(IC2CExtrasTags.getForgeBlockTag("storage_blocks/raw_lead"), IC2CExtrasTags.getForgeItemTag("storage_blocks/raw_lead"));
        this.tag(IC2CExtrasTags.getForgeItemTag("ingots")).add(ModItems.LEAD_INGOT, ModItems.PLUTONIUM_INGOT, ModItems.THORIUM_INGOT, ModItems.URANIUM_233_INGOT, ModItems.URANIUM_235_INGOT);
        this.tag(IC2CExtrasTags.getForgeItemTag("dusts")).add(ModItems.STONE_DUST, ModItems.LEAD_DUST, ModItems.PLUTONIUM_DUST, ModItems.THORIUM_DUST, ModItems.URANIUM_DUST, ModItems.URANIUM_233_DUST, ModItems.URANIUM_235_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("ingots/lead")).add(ModItems.LEAD_INGOT);
        this.tag(IC2CExtrasTags.getForgeItemTag("ingots/thorium")).add(ModItems.THORIUM_INGOT);
        this.tag(IC2CExtrasTags.getForgeItemTag("ingots/plutonium")).add(ModItems.PLUTONIUM_INGOT);
        this.tag(IC2CExtrasTags.getForgeItemTag("ingots/uranium235")).add(ModItems.URANIUM_235_INGOT);
        this.tag(IC2CExtrasTags.getForgeItemTag("ingots/uranium233")).add(ModItems.URANIUM_233_INGOT);
        this.tag(IC2CExtrasTags.getForgeItemTag("dusts/stone")).add(ModItems.STONE_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("dusts/lead")).add(ModItems.LEAD_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("dusts/thorium")).add(ModItems.THORIUM_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("dusts/plutonium")).add(ModItems.PLUTONIUM_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("dusts/uranium")).add(ModItems.URANIUM_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("dusts/uranium235")).add(ModItems.URANIUM_235_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("dusts/uranium233")).add(ModItems.URANIUM_233_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts")).add(ModItems.TINY_IRON_DUST, ModItems.TINY_GOLD_DUST,
                ModItems.TINY_COPPER_DUST, ModItems.TINY_TIN_DUST, ModItems.TINY_ALUMINIUM_DUST, ModItems.TINY_SILVER_DUST,
                ModItems.TINY_LEAD_DUST, ModItems.TINY_THORIUM_DUST, ModItems.TINY_PLUTONIUM_DUST, ModItems.TINY_URANIUM_233_DUST,
                ModItems.TINY_URANIUM_235_DUST, ModItems.TINY_URANIUM_238_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/iron")).add(ModItems.TINY_IRON_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/gold")).add(ModItems.TINY_GOLD_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/copper")).add(ModItems.TINY_COPPER_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/tin")).add(ModItems.TINY_TIN_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/aluminium")).add(ModItems.TINY_ALUMINIUM_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/silver")).add(ModItems.TINY_SILVER_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/lead")).add(ModItems.TINY_LEAD_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/thorium")).add(ModItems.TINY_THORIUM_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/plutonium")).add(ModItems.TINY_PLUTONIUM_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/uranium")).add(ModItems.TINY_URANIUM_238_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/uranium235")).add(ModItems.TINY_URANIUM_235_DUST);
        this.tag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/uranium233")).add(ModItems.TINY_URANIUM_233_DUST);

        this.tag(IC2CExtrasTags.getForgeItemTag("crushed_ores")).add(ModItems.CRUSHED_IRON_ORE, ModItems.CRUSHED_GOLD_ORE,
                ModItems.CRUSHED_COPPER_ORE, ModItems.CRUSHED_TIN_ORE, ModItems.CRUSHED_ALUMINIUM_ORE, ModItems.CRUSHED_SILVER_ORE,
                ModItems.CRUSHED_LEAD_ORE, ModItems.CRUSHED_URANIUM_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("crushed_ores/iron")).add(ModItems.CRUSHED_IRON_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("crushed_ores/gold")).add(ModItems.CRUSHED_GOLD_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("crushed_ores/copper")).add(ModItems.CRUSHED_COPPER_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("crushed_ores/tin")).add(ModItems.CRUSHED_TIN_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("crushed_ores/aluminium")).add(ModItems.CRUSHED_ALUMINIUM_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("crushed_ores/silver")).add(ModItems.CRUSHED_SILVER_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("crushed_ores/lead")).add(ModItems.CRUSHED_LEAD_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("crushed_ores/uranium")).add(ModItems.CRUSHED_URANIUM_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("purified_ores")).add(ModItems.PURIFIED_IRON_ORE, ModItems.PURIFIED_GOLD_ORE,
                ModItems.PURIFIED_COPPER_ORE, ModItems.PURIFIED_TIN_ORE, ModItems.PURIFIED_ALUMINIUM_ORE, ModItems.PURIFIED_SILVER_ORE,
                ModItems.PURIFIED_LEAD_ORE, ModItems.PURIFIED_URANIUM_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("purified_ores/iron")).add(ModItems.PURIFIED_IRON_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("purified_ores/gold")).add(ModItems.PURIFIED_GOLD_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("purified_ores/copper")).add(ModItems.PURIFIED_COPPER_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("purified_ores/tin")).add(ModItems.PURIFIED_TIN_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("purified_ores/aluminium")).add(ModItems.PURIFIED_ALUMINIUM_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("purified_ores/silver")).add(ModItems.PURIFIED_SILVER_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("purified_ores/lead")).add(ModItems.PURIFIED_LEAD_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("purified_ores/uranium")).add(ModItems.PURIFIED_URANIUM_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("refined_ores")).add(ModItems.REFINED_URANIUM_ORE);
        this.tag(IC2CExtrasTags.getForgeItemTag("refined_ores/uranium")).add(ModItems.REFINED_URANIUM_ORE);
        this.tag(IC2CExtrasTags.RAW_LEAD).add(ModItems.RAW_LEAD);
        this.tag(IC2CExtrasTags.RADIOACTIVE)
                .addTag(IC2CExtrasTags.getForgeItemTag("ingots/uranium235"))
                .addTag(IC2CExtrasTags.getForgeItemTag("ingots/uranium233"))
                .addTag(IC2CExtrasTags.getForgeItemTag("ingots/plutonium"))
                .addTag(IC2CExtrasTags.getForgeItemTag("dusts/uranium235"))
                .addTag(IC2CExtrasTags.getForgeItemTag("dusts/uranium233"))
                .addTag(IC2CExtrasTags.getForgeItemTag("dusts/plutonium"))
                .addTag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/uranium235"))
                .addTag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/uranium233"))
                .addTag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/plutonium"))
                .add(ModItems.MOX_FUEL, ModItems.ENRICHED_URANIUM_FUEL, ModItems.MOX_INGOT, ModItems.ENRICHED_URANIUM_INGOT, ModItems.PLUTONIUM_RTG, IC2Items.INGOT_URANIUM_ENRICHED_ENDERPEARL,
                        IC2Items.INGOT_URANIUM_ENRICHED_NETHERSTAR, IC2Items.INGOT_URANIUM_ENRICHED_BLAZE, IC2Items.INGOT_URANIUM_ENRICHED_CHARCOAL, IC2Items.INGOT_URANIUM_ENRICHED_REDSTONE)
                .add(IC2Items.URANIUM_ROD_SINGLE, IC2Items.URANIUM_ROD_DUAL, IC2Items.URANIUM_ROD_QUAD,
                        IC2Items.URANIUM_ROD_ISOTOPIC, IC2Items.URANIUM_ROD_NEAR_DEPLETED, IC2Items.URANIUM_ROD_RE_ENRICHED)
                .add(IC2Items.URANIUM_ROD_BLAZE_SINGLE, IC2Items.URANIUM_ROD_BLAZE_DUAL, IC2Items.URANIUM_ROD_BLAZE_QUAD,
                        IC2Items.URANIUM_ROD_ISOTOPIC_BLAZE, IC2Items.URANIUM_ROD_NEAR_DEPLETED_BLAZE, IC2Items.URANIUM_ROD_RE_ENRICHED_BLAZE)
                .add(IC2Items.URANIUM_ROD_CHARCOAL_SINGLE, IC2Items.URANIUM_ROD_CHARCOAL_DUAL, IC2Items.URANIUM_ROD_CHARCOAL_QUAD,
                        IC2Items.URANIUM_ROD_ISOTOPIC_CHARCOAL, IC2Items.URANIUM_ROD_NEAR_DEPLETED_CHARCOAL, IC2Items.URANIUM_ROD_RE_ENRICHED_CHARCOAL)
                .add(IC2Items.URANIUM_ROD_ENDER_PEARL_SINGLE, IC2Items.URANIUM_ROD_ENDER_PEARL_DUAL, IC2Items.URANIUM_ROD_ENDER_PEARL_QUAD,
                        IC2Items.URANIUM_ROD_ISOTOPIC_ENDER_PEARL, IC2Items.URANIUM_ROD_NEAR_DEPLETED_ENDER_PEARL, IC2Items.URANIUM_ROD_RE_ENRICHED_ENDER_PEARL)
                .add(IC2Items.URANIUM_ROD_REDSTONE_SINGLE, IC2Items.URANIUM_ROD_REDSTONE_DUAL, IC2Items.URANIUM_ROD_REDSTONE_QUAD,
                        IC2Items.URANIUM_ROD_ISOTOPIC_REDSTONE, IC2Items.URANIUM_ROD_NEAR_DEPLETED_REDSTONE, IC2Items.URANIUM_ROD_RE_ENRICHED_REDSTONE)
                .add(IC2Items.URANIUM_ROD_NETHER_STAR_SINGLE, IC2Items.URANIUM_ROD_NETHER_STAR_DUAL, IC2Items.URANIUM_ROD_NETHER_STAR_QUAD,
                        IC2Items.URANIUM_ROD_ISOTOPIC_NETHER_STAR, IC2Items.URANIUM_ROD_NEAR_DEPLETED_NETHER_STAR, IC2Items.URANIUM_ROD_RE_ENRICHED_NETHER_STAR)
                .add(ModItems.MOX_ROD, ModItems.DUAL_MOX_ROD, ModItems.QUAD_MOX_ROD, ModItems.ISOTOPIC_MOX_ROD, ModItems.RE_ENRICHED_MOX_ROD, ModItems.NEAR_DEPLETED_MOX_ROD)
                .add(ModItems.PLUTONIUM_ROD, ModItems.DUAL_PLUTONIUM_ROD, ModItems.QUAD_PLUTONIUM_ROD, ModItems.ISOTOPIC_PLUTONIUM_ROD, ModItems.RE_ENRICHED_PLUTONIUM_ROD, ModItems.NEAR_DEPLETED_PLUTONIUM_ROD)
                .add(ModItems.URANIUM_233_ROD, ModItems.DUAL_URANIUM_233_ROD, ModItems.QUAD_URANIUM_233_ROD, ModItems.ISOTOPIC_URANIUM_233_ROD, ModItems.RE_ENRICHED_URANIUM_233_ROD, ModItems.NEAR_DEPLETED_URANIUM_233_ROD)
                .add(ModItems.URANIUM_235_ROD, ModItems.DUAL_URANIUM_235_ROD, ModItems.QUAD_URANIUM_235_ROD, ModItems.ISOTOPIC_URANIUM_235_ROD, ModItems.RE_ENRICHED_URANIUM_235_ROD, ModItems.NEAR_DEPLETED_URANIUM_235_ROD);
        this.tag(IC2CExtrasTags.CONTAINMENT_BOX).addTag(IC2CExtrasTags.RADIOACTIVE)
                .add(ModItems.THORIUM_ROD, ModItems.DUAL_THORIUM_ROD, ModItems.QUAD_THORIUM_ROD, ModItems.ISOTOPIC_THORIUM_ROD, ModItems.RE_ENRICHED_THORIUM_ROD, ModItems.NEAR_DEPLETED_THORIUM_ROD)
                .add(ModItems.URANIUM_238_ROD, ModItems.DUAL_URANIUM_238_ROD, ModItems.QUAD_URANIUM_238_ROD, ModItems.ISOTOPIC_URANIUM_238_ROD, ModItems.RE_ENRICHED_URANIUM_238_ROD, ModItems.NEAR_DEPLETED_URANIUM_238_ROD)
                .addOptionalTag(IC2Tags.INGOT_URANIUM.location()).addTag(IC2CExtrasTags.getForgeItemTag("dusts/uranium")).addTag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/uranium"))
                .addTag(IC2CExtrasTags.getForgeItemTag("ingots/thorium")).addTag(IC2CExtrasTags.getForgeItemTag("dusts/thorium")).addTag(IC2CExtrasTags.getForgeItemTag("tiny_dusts/thorium"));
    }
}
