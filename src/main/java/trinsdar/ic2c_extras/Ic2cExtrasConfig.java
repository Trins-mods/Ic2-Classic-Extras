package trinsdar.ic2c_extras;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = IC2CExtras.MODID, name = "ic2/ic2c_extras")
public class Ic2cExtrasConfig {
    @Comment({
            "Disables everything in the mod except for radiation. If true the only other config option that does anything is the Radiation config.",
            " Note: This will disable everything but the items and blocks and tiles themselves and hide them from jei."
    })
    @Config.RequiresMcRestart
    public static boolean disableNonRadiation = false;
    @Config.RequiresMcRestart
    @Comment("Enables certain items giving radiation")
    public static boolean itemRadiation = true;
    @Config.RequiresMcRestart
    @Comment("Enables harder uranium processing")
    public static boolean harderUranium = true;
    @Config.RequiresMcRestart
    @Comment("Enables casings requiring plates to craft.")
    public static boolean casingsRequirePlates = true;
    @Config.RequiresMcRestart
    @Comment("Enables additional recipe of crafting cables with wire cutter and plates")
    public static boolean craftingCablesWithPlates = false;
    @Config.RequiresMcRestart
    @Comment("Enables overriding the ic2c cable cafting recipe with the plate cable crafting recipe when true. requires craftingCablesWithPlates to be true.")
    public static boolean plateCablesOverrideRegularCables = false;
    @Config.RequiresMcRestart
    @Comment("Enables plate cable crafting recips making the same amount as one ingot does in the ic2c cable recipes instead of the amount one ingot does in the extruder.")
    public static boolean plateCablesMakeLessThenExtruder = false;
    @Config.RequiresMcRestart
    @Comment("Enables crafting plates and casings with the hammer")
    public static boolean craftingHammerRecipes = true;
    @Config.RequiresMcRestart
    @Comment("Enables manual crafting of plates taking 2 ingots")
    public static boolean twoIngotPlates = false;
    @Config.RequiresMcRestart
    @Comment("Enables the requirement of steel for hv cables && plasma cables. Does nothing if enableSteelRecipes is true.")
    public static boolean cablesTakeSteel = false;
    @Config.RequiresMcRestart
    @Comment("Enables adding recipes for plates and casings to roller and crafting table.")
    public static boolean autoOredictRecipes = true;
    @Config.RequiresMcRestart
    @Comment("Enables the ability to wash crushed ore with a cauldron when my other mod gtc expansion is loaded.")
    public static boolean cauldronWashing = true;
    @Config.RequiresMcRestart
    @Comment("Enables adding tiny plutonium and iridium shards to vanilla loot tables")
    public static boolean lootEntries = true;
    @Config.RequiresMcRestart
    @Comment("Enables replacing the drop of uranium ore with the block instead of uranium ore item.")
    public static boolean uramiumOreDrop = true;
    @Config.RequiresMcRestart
    @Comment("Enables auto recipes for draining and filling fluid container items in the fluid canning machine.")
    public static boolean autoFluidContainerRecipes = true;
    @Config.RequiresMcRestart
    @Comment("Enables nucear cells taking my empty nuclear fuel cell.")
    public static boolean emptyNuclearRod = true;
    @Config.RequiresMcRestart
    @Comment("Enables things like showing the recipe ids of my machine recipes in jei.")
    public static boolean debugMode = false;
    @Config.RequiresMcRestart
    @Comment("Determines whether dense plates take 9 plates(true) or 8 ingots(false).")
    public static boolean densePlatesTakePlates = false;
    @Config.RequiresMcRestart
    @Comment("Whether or not gtcx compat is enabled.")
    public static boolean compatGTCX = true;
}
