package trinsdar.ic2c_extras.init;

import ic2.core.platform.registries.IC2Items;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import trinsdar.ic2c_extras.item.ItemBasic;
import trinsdar.ic2c_extras.item.ItemIsotopicRod;
import trinsdar.ic2c_extras.item.ItemReactorRod;
import trinsdar.ic2c_extras.nuclear.MOX;
import trinsdar.ic2c_extras.nuclear.Plutonium;
import trinsdar.ic2c_extras.nuclear.Thorium232;
import trinsdar.ic2c_extras.nuclear.Uranium233;
import trinsdar.ic2c_extras.nuclear.Uranium235;
import trinsdar.ic2c_extras.nuclear.Uranium238;

public class ModItems {
    public static final Item BIOCHAFF = new ItemBasic("biochaff", "misc");
    public static final Item COIL = new ItemBasic("coil", "misc");
    //public static final Item CONTAINMENT_BOX = new ItemBasic("containment_box", "misc");
    public static final Item DENSE_LEAD_PLATE = new ItemBasic("dense_lead_plate", "misc");
    public static final Item DENSE_IRON_PLATE = new ItemBasic("dense_iron_plate", "misc");
    public static final Item EMPTY_NUCLEAR_ROD = new ItemBasic("empty_nuclear_rod", "misc");
    public static final Item HEAT_CONDUCTOR = new ItemBasic("heat_conductor", "misc");
    public static final Item IRIDIUM_SHARD = new ItemBasic("iridium_shard", "misc");
    public static final Item RAW_LEAD = new ItemBasic("raw_lead", "misc");
    public static final Item MOX_FUEL = new ItemBasic("mox_fuel", "misc");
    public static final Item ENRICHED_URANIUM_FUEL = new ItemBasic("enriched_uranium_fuel", "misc");
    public static final Item THORIUM_RTG = new ItemBasic("thorium_rtg", "misc", new Item.Properties().defaultDurability(50000));
    public static final Item PLUTONIUM_RTG = new ItemBasic("plutonium_rtg", "misc", new Item.Properties().defaultDurability(10000));



    public static final Item CRUSHED_IRON_ORE = new ItemBasic("crushed_iron_ore", "crushed");
    public static final Item CRUSHED_GOLD_ORE = new ItemBasic("crushed_gold_ore", "crushed");
    public static final Item CRUSHED_COPPER_ORE = new ItemBasic("crushed_copper_ore", "crushed");
    public static final Item CRUSHED_TIN_ORE = new ItemBasic("crushed_tin_ore", "crushed");
    public static final Item CRUSHED_SILVER_ORE = new ItemBasic("crushed_silver_ore", "crushed");
    public static final Item CRUSHED_LEAD_ORE = new ItemBasic("crushed_lead_ore", "crushed");
    public static final Item CRUSHED_ALUMINIUM_ORE = new ItemBasic("crushed_aluminium_ore", "crushed");
    public static final Item CRUSHED_URANIUM_ORE = new ItemBasic("crushed_uranium_ore", "crushed");

    public static final Item PURIFIED_IRON_ORE = new ItemBasic("purified_iron_ore", "crushed");
    public static final Item PURIFIED_GOLD_ORE = new ItemBasic("purified_gold_ore", "crushed");
    public static final Item PURIFIED_COPPER_ORE = new ItemBasic("purified_copper_ore", "crushed");
    public static final Item PURIFIED_TIN_ORE = new ItemBasic("purified_tin_ore", "crushed");
    public static final Item PURIFIED_SILVER_ORE = new ItemBasic("purified_silver_ore", "crushed");
    public static final Item PURIFIED_LEAD_ORE = new ItemBasic("purified_lead_ore", "crushed");
    public static final Item PURIFIED_ALUMINIUM_ORE = new ItemBasic("purified_aluminium_ore", "crushed");
    public static final Item PURIFIED_URANIUM_ORE = new ItemBasic("purified_uranium_ore", "crushed");
    public static final Item REFINED_URANIUM_ORE = new ItemBasic("refined_uranium_ore", "crushed");

    public static final Item TINY_IRON_DUST = new ItemBasic("tiny_iron_dust", "dusts");
    public static final Item TINY_GOLD_DUST = new ItemBasic("tiny_gold_dust", "dusts");
    public static final Item TINY_COPPER_DUST = new ItemBasic("tiny_copper_dust", "dusts");
    public static final Item TINY_TIN_DUST = new ItemBasic("tiny_tin_dust", "dusts");
    public static final Item TINY_ALUMINIUM_DUST = new ItemBasic("tiny_aluminium_dust", "dusts");
    public static final Item TINY_SILVER_DUST = new ItemBasic("tiny_silver_dust", "dusts");
    public static final Item TINY_LEAD_DUST = new ItemBasic("tiny_lead_dust", "dusts");
    public static final Item TINY_URANIUM_233_DUST = new ItemBasic("tiny_uranium_233_dust", "dusts");
    public static final Item TINY_URANIUM_235_DUST = new ItemBasic("tiny_uranium_235_dust", "dusts");
    public static final Item TINY_URANIUM_238_DUST = new ItemBasic("tiny_uranium_238_dust", "dusts");
    public static final Item TINY_PLUTONIUM_DUST = new ItemBasic("tiny_plutonium_dust", "dusts");
    public static final Item TINY_THORIUM_DUST = new ItemBasic("tiny_thorium_232_dust", "dusts");
    public static final Item STONE_DUST = new ItemBasic("stone_dust", "dusts");
    public static final Item LEAD_DUST = new ItemBasic("lead_dust", "dusts");
    public static final Item URANIUM_DUST = new ItemBasic("uranium_dust", "dusts");
    public static final Item URANIUM_233_DUST = new ItemBasic("uranium_233_dust", "dusts");
    public static final Item URANIUM_235_DUST = new ItemBasic("uranium_235_dust", "dusts");
    public static final Item URANIUM_238_DUST = new ItemBasic("uranium_238_dust", "dusts");
    public static final Item PLUTONIUM_DUST = new ItemBasic("plutonium_dust", "dusts");
    public static final Item THORIUM_DUST = new ItemBasic("thorium_232_dust", "dusts");

    public static final Item DOUBLE_ENRICHED_URANIUM = new ItemBasic("double_enriched_uranium_ingot", "ingots");
    public static final Item LEAD_INGOT = new ItemBasic("lead_ingot", "ingots");
    public static final Item PLUTONIUM_INGOT = new ItemBasic("plutonium_ingot", "ingots");
    public static final Item THORIUM_INGOT = new ItemBasic("thorium_232_ingot", "ingots");
    public static final Item URANIUM_233_INGOT = new ItemBasic("uranium_233_ingot", "ingots");
    public static final Item URANIUM_235_INGOT = new ItemBasic("uranium_235_ingot", "ingots");
    public static final Item URANIUM_238_INGOT = new ItemBasic("uranium_238_ingot", "ingots");


    public static final Item NEAR_DEPLETED_MOX_ROD = new ItemBasic("near_depleted_mox_rod", "nuclear_rods");
    public static final Item NEAR_DEPLETED_PLUTONIUM_ROD = new ItemBasic("near_depleted_plutonium_rod", "nuclear_rods");
    public static final Item NEAR_DEPLETED_THORIUM_ROD = new ItemBasic("near_depleted_thorium_232_rod", "nuclear_rods");
    public static final Item NEAR_DEPLETED_URANIUM_233_ROD = new ItemBasic("near_depleted_uranium_233_rod", "nuclear_rods");
    public static final Item NEAR_DEPLETED_URANIUM_235_ROD = new ItemBasic("near_depleted_uranium_235_rod", "nuclear_rods");
    public static final Item NEAR_DEPLETED_URANIUM_238_ROD = new ItemBasic("near_depleted_uranium_238_rod", "nuclear_rods");

    public static final Item RE_ENRICHED_MOX_ROD = new ItemBasic("re_enriched_mox_rod", "nuclear_rods");
    public static final Item RE_ENRICHED_PLUTONIUM_ROD = new ItemBasic("re_enriched_plutonium_rod", "nuclear_rods");
    public static final Item RE_ENRICHED_THORIUM_ROD = new ItemBasic("re_enriched_thorium_232_rod", "nuclear_rods");
    public static final Item RE_ENRICHED_URANIUM_233_ROD = new ItemBasic("re_enriched_uranium_233_rod", "nuclear_rods");
    public static final Item RE_ENRICHED_URANIUM_235_ROD = new ItemBasic("re_enriched_uranium_235_rod", "nuclear_rods");
    public static final Item RE_ENRICHED_URANIUM_238_ROD = new ItemBasic("re_enriched_uranium_238_rod", "nuclear_rods");

    public static final Item URANIUM_238_ROD = new ItemReactorRod("uranium_238_rod", Uranium238.INSTANCE, 1, 1100);
    public static final Item PLUTONIUM_ROD = new ItemReactorRod("plutonium_rod", Plutonium.INSTANCE, 1, 1106);
    public static final Item MOX_ROD = new ItemReactorRod("mox_rod", MOX.INSTANCE, 1, 1112);
    public static final Item THORIUM_ROD = new ItemReactorRod("thorium_232_rod", Thorium232.INSTANCE, 1, 1118);
    public static final Item URANIUM_233_ROD = new ItemReactorRod("uranium_233_rod", Uranium233.INSTANCE, 1, 1124);
    public static final Item URANIUM_235_ROD = new ItemReactorRod("uranium_235_rod", Uranium235.INSTANCE, 1, 1130);
    public static final Item DUAL_URANIUM_238_ROD = new ItemReactorRod("dual_uranium_238_rod", Uranium238.INSTANCE, 2, 1101);
    public static final Item DUAL_PLUTONIUM_ROD = new ItemReactorRod("dual_plutonium_rod", Plutonium.INSTANCE, 2, 1107);
    public static final Item DUAL_MOX_ROD = new ItemReactorRod("dual_mox_rod", MOX.INSTANCE, 2, 1113);
    public static final Item DUAL_THORIUM_ROD = new ItemReactorRod("dual_thorium_232_rod", Thorium232.INSTANCE, 2, 1119);
    public static final Item DUAL_URANIUM_233_ROD = new ItemReactorRod("dual_uranium_233_rod", Uranium233.INSTANCE, 2, 1125);
    public static final Item DUAL_URANIUM_235_ROD = new ItemReactorRod("dual_uranium_235_rod", Uranium235.INSTANCE, 2, 1131);
    public static final Item QUAD_URANIUM_238_ROD = new ItemReactorRod("quad_uranium_238_rod", Uranium238.INSTANCE, 4, 1102);
    public static final Item QUAD_PLUTONIUM_ROD = new ItemReactorRod("quad_plutonium_rod", Plutonium.INSTANCE, 4, 1108);
    public static final Item QUAD_MOX_ROD = new ItemReactorRod("quad_mox_rod", MOX.INSTANCE, 4, 1114);
    public static final Item QUAD_THORIUM_ROD = new ItemReactorRod("quad_thorium_232_rod", Thorium232.INSTANCE, 4, 1120);
    public static final Item QUAD_URANIUM_233_ROD = new ItemReactorRod("quad_uranium_233_rod", Uranium233.INSTANCE, 4, 1126);
    public static final Item QUAD_URANIUM_235_ROD = new ItemReactorRod("quad_uranium_235_rod", Uranium235.INSTANCE, 4, 1132);
    public static final Item ISOTOPIC_URANIUM_238_ROD = new ItemIsotopicRod("isotopic_uranium_238_rod", Uranium238.INSTANCE, 1105);
    public static final Item ISOTOPIC_PLUTONIUM_ROD = new ItemIsotopicRod("isotopic_plutonium_rod", Plutonium.INSTANCE, 1111);
    public static final Item ISOTOPIC_MOX_ROD = new ItemIsotopicRod("isotopic_mox_rod", MOX.INSTANCE, 1117);
    public static final Item ISOTOPIC_THORIUM_ROD = new ItemIsotopicRod("isotopic_thorium_232_rod", Thorium232.INSTANCE, 1123);
    public static final Item ISOTOPIC_URANIUM_233_ROD = new ItemIsotopicRod("isotopic_uranium_233_rod", Uranium233.INSTANCE, 1129);
    public static final Item ISOTOPIC_URANIUM_235_ROD = new ItemIsotopicRod("isotopic_uranium_235_rod", Uranium235.INSTANCE, 1135);


    public static void init(){
    }
}
