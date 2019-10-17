package trinsdar.ic2c_extras;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;
import trinsdar.ic2c_extras.proxy.CommonProxy;

public class Config {
    private static final String CATEGORY_GENERAL = "general";
    public static boolean disableNonRadiation = false;
    public static boolean itemRadiation = true;
    public static boolean harderUranium = true;
    public static boolean casingsRequirePlates = true;
    public static boolean craftingCablesWithPlates = false;
    public static boolean craftingHammerRecipes = true;
    public static boolean twoIngotPlates = false;
    public static boolean cablesTakeSteel = false;
    public static boolean autoOredictRecipes = true;
    public static boolean cauldronWashing = true;
    public static boolean lootEntries = true;
    public static boolean uramiumOreDrop = true;
    public static boolean autoFluidContainerRecipes = true;
    public static boolean emptyNuclearRod = true;
    public static boolean debugMode = false;
    public static boolean densePlatesTakePlates = false;


    public Config(){}

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            IC2CExtras.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    public static void initGeneralConfig(Configuration cfg){
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General");
        disableNonRadiation = cfg.getBoolean("disableNonRadiation", CATEGORY_GENERAL, disableNonRadiation, "Disables everything in the mod except for radiation. If true the only other config option that does anything is the Radiation config. Note: This will disable everything but the items and blocks and tiles themselves and hide them from jei.");
        itemRadiation = cfg.getBoolean("itemRadiation", CATEGORY_GENERAL, itemRadiation, "Enables certain items giving radiation");
        harderUranium = cfg.getBoolean("harderUranium", CATEGORY_GENERAL, harderUranium, "Enables harder uranium processing");
        casingsRequirePlates = cfg.getBoolean("casingsRequirePlates", CATEGORY_GENERAL, casingsRequirePlates, "Enables casings requiring plates to craft.");
        craftingCablesWithPlates = cfg.getBoolean("craftingCablesWithPlates", CATEGORY_GENERAL, craftingCablesWithPlates, "Enables additional recipe of crafting cables with wire cutter and plates");
        craftingHammerRecipes = cfg.getBoolean("craftingHammerRecipes", CATEGORY_GENERAL, craftingHammerRecipes, "Enables crafting plates and casings with the hammer");
        twoIngotPlates = cfg.getBoolean("twoIngotPlates", CATEGORY_GENERAL, twoIngotPlates, "Enables manual crafting of plates taking 2 ingots");
        cablesTakeSteel = cfg.getBoolean("cablesTakeSteel", CATEGORY_GENERAL, cablesTakeSteel, "Enables the requirement of steel for hv cables, plasma cables. Does nothing if enableSteelRecipes is true.");
        autoOredictRecipes = cfg.getBoolean("autoOredictRecipes", CATEGORY_GENERAL, autoOredictRecipes, "Enables the ability to wash crushed ore with a cauldron when my other mod gtc expansion is loaded.");
        cauldronWashing = cfg.getBoolean("cauldronWashing", CATEGORY_GENERAL, cauldronWashing, "Enables adding tiny plutonium and iridium shards to vanilla loot tables");
        lootEntries = cfg.getBoolean("lootEntries", CATEGORY_GENERAL, lootEntries, "Enables adding recipes for plates and casings to roller and crafting table.");
        uramiumOreDrop = cfg.getBoolean("uramiumOreDrop", CATEGORY_GENERAL, uramiumOreDrop, "Enables replacing the drop of uranium ore with the block instead of uranium ore item.");
        autoFluidContainerRecipes = cfg.getBoolean("autoFluidContainerRecipes", CATEGORY_GENERAL, autoFluidContainerRecipes, "Enables auto recipes for draining and filling fluid container items in the fluid canning machine.");
        emptyNuclearRod = cfg.getBoolean("emptyNuclearRod", CATEGORY_GENERAL, emptyNuclearRod, "Enables nucear cells taking my empty nuclear fuel cell.");
        debugMode = cfg.getBoolean("debugMode", CATEGORY_GENERAL, debugMode, "Enables things like showing the recipe ids of my machine recipes in jei.");
        densePlatesTakePlates = cfg.getBoolean("densePlatesTakePlates", CATEGORY_GENERAL, densePlatesTakePlates, "Determines whether dense plates take 9 plates(true) or 8 ingots(false).");
    }
}
