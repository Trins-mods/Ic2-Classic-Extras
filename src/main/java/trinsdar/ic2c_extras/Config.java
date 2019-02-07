package trinsdar.ic2c_extras;
import ic2.core.IC2;
import ic2.core.platform.config.ConfigEntry;
import ic2.core.platform.config.IC2Config;
import ic2.core.platform.config.components.IConfigNotify;
import trinsdar.ic2c_extras.util.Ic2cExtrasRecipes;

public class Config implements IConfigNotify {
    private static Config config = new Config();

    public Config(){}

    public static void init(){
        IC2Config.ConfigType bool = IC2Config.ConfigType.Boolean;
        IC2Config.ConfigType intg = IC2Config.ConfigType.Integer;
        IC2Config.ConfigType flo = IC2Config.ConfigType.Float;
        IC2Config.ConfigType txt = IC2Config.ConfigType.String;
        if (IC2.config.isLoaded()){
            IC2.config.addCustomConfig(new ConfigEntry(bool, "IC2CExtras", "disableNonRadiation", "Disables everything in the mod except for radiation. If true the only other config option that does anything is the Radiation config. Note: This will disable everything but the items and blocks and tiles themselves and hide them from jei.", "NonRadiation", false).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "IC2CExtras", "enableItemRadiation", "Enables certain items giving radiation", "ItemRadiation", true).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "IC2CExtras", "enableHarderUranium", "Enables harder uranium processing. Note: this is different then enableHarderEnrichedUran in that it deals with processing the ore and such", "HarderUranium", true).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "IC2CExtras", "enableCasingsRequirePlates", "Enables casings requiring plates to craft. Note the only plate that i add is refined iron plates so if you enable this you will need another mod that adds plates.", "CasingsNeedPlates", false).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "IC2CExtras", "enableCrafingCablesWithPlates", "Enables additional recipe of crafting cables with wire cutter and plates", "CablesWithPlates", false).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "IC2CExtras", "enableCrafingHammerRecipes", "Enables crafting plates and casings with the hammer", "HammerRecipes", false).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "IC2CExtras", "enableCertainRecipesNeedSteel", "Enables the requirement of steel for hv cables, plasma cables, advanced machine blocks, and tesla coils. Does nothing if enableSteelRecipes is true.", "CertainRecipesNeedSteel", false).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "IC2CExtras", "enableAutoOredictRecipes", "Enables adding recipes for crushed ore, purified crushed ore, casings, ect. to ore washing plant, thermal centrifuge, roller, and crafting table.", "AutoOredictRecipes", true).setGameRestart().setServerSync());
            IC2.config.addCustomConfig(new ConfigEntry(bool, "IC2CExtras", "enableLootEntries", "Enables adding tiny plutonium and iridium shards to vanilla loot tables", "LootEntries", true).setGameRestart().setServerSync());
            IC2.config.addConfigNotify(config);
        }else {
            throw new RuntimeException("The Ic2Classic config is not loaded");
        }
    }


    @Override
    public void onConfigReloaded(IC2Config config) {
        Radiation.setConfig(config.getFlag("ItemRadiation"));
        Ic2cExtrasRecipes.setConfig(config.getFlag("HarderUranium"), config.getFlag("CasingsNeedPlates"), config.getFlag("CablesWithPlates"), config.getFlag("CertainRecipesNeedSteel"), config.getFlag("HammerRecipes"), config.getFlag("AutoOredictRecipes"), config.getFlag("LootEntries"));
    }
}
