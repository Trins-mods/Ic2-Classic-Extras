package trinsdar.ic2c_extras;

import carbonconfiglib.CarbonConfig;
import carbonconfiglib.config.Config;
import carbonconfiglib.config.ConfigEntry;
import carbonconfiglib.config.ConfigHandler;
import carbonconfiglib.config.ConfigSection;

public class IC2CExtrasConfig {
    public static ConfigEntry.BoolValue DISABLE_NON_RADIATION;
    public static ConfigEntry.BoolValue ITEM_RADIATION;
    public static ConfigEntry.BoolValue LOOT_ENTRIES;
    public static ConfigEntry.BoolValue EMPTY_NUCLEAR_ROD;
    public static ConfigEntry.BoolValue EXTRA_NUCLEAR;
    public static ConfigEntry.BoolValue REACTOR_CHAMBER_REQUIRES_LEAD;
    public static ConfigEntry.BoolValue LEAD_ORE_GEN;
    public static ConfigEntry.IntValue BIOMASS_PER_FERTILIZER;
    public static ConfigEntry.IntValue BIOMASS_PROCESSES_PER_FERTILIZER;
    public static ConfigEntry.StringValue LEAD_CROP_DROP;

    public static void createConfig(){
        Config config = new Config("ic2c/ic2c_extras");
        ConfigSection recipes = config.add("recipes");
        DISABLE_NON_RADIATION = recipes.addBool("disable_non_radiation", false, "Disables everything in the mod except for radiation. If true the only other config option that does anything is the Radiation config.",
                " Note: This will disable everything but the items and blocks and tiles themselves and hide them from jei.");
        EMPTY_NUCLEAR_ROD = recipes.addBool("empty_nuclear_rod", true, "Enables nuclear rods taking the empty nuclear fuel rod.");
        EXTRA_NUCLEAR = recipes.addBool("extra_nuclear", true, "Enables all the nuclear rods and the required changes to uranium processing.");
        BIOMASS_PER_FERTILIZER = recipes.addInt("biomass_per_fertilizer", 500, "Amount of total biomass that needs to be consumed to make 1 fertilizer in the fermenter").setMin(1);
        BIOMASS_PROCESSES_PER_FERTILIZER = recipes.addInt("biomass_processes_per_fertilizer", 25, "Factor that biomassPerFertilizer divides by to determine how much it progresses towards the full amount").setMin(1);
        REACTOR_CHAMBER_REQUIRES_LEAD = recipes.addBool("reactor_chamber_requires_lead", false, "If true the reactor chamber will require dense lead plates in place of dense copper plates.",
                "If false then dense lead plates are an alternative instead.");
        ConfigSection misc = config.add("misc");
        ITEM_RADIATION = misc.addBool("item_radiation", true, "Enables items with the #ic2c_extras:radioactive tag giving radiation.");
        LOOT_ENTRIES = misc.addBool("loot_entries", true, "Enable tiny plutonium and iridium shards being in various loot tables.");
        LEAD_ORE_GEN = misc.addBool("lead_ore_gen", true, "Enable lead ore gen, will generate around same height as silver.");
        LEAD_CROP_DROP = misc.addString("lead_crop_drop", "ic2c_extras:lead_dust", "Format is modid:item_id, if the item provided here is not valid it will default back to ic2c extras lead dust.");
        ConfigHandler handler = CarbonConfig.CONFIGS.createConfig(config);
        handler.register();
    }
}
