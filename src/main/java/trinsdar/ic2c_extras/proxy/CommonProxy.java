package trinsdar.ic2c_extras.proxy;

import ic2.core.IC2;
import ic2.core.platform.registry.ItemAPI;
import ic2.core.util.misc.StackUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import trinsdar.ic2c_extras.Config;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.recipes.MachineRecipes;
import trinsdar.ic2c_extras.recipes.RailcraftRecipes;
import trinsdar.ic2c_extras.util.Ic2cExtrasOredict;
import trinsdar.ic2c_extras.util.Registry;

import java.io.File;


public class CommonProxy
{
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event)
    {
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "ic2/ic2c_extras.cfg"));
        Config.readConfig();
        config.save();
        Registry.init();
        initItemApi();
        Registry.registerTiles();
        if (!IC2.config.getFlag("NonRadiation")){
            Ic2cExtrasOredict.init();
        }
    }

    public void initItemApi(){
        ItemAPI.instance.putState("te", "ore_washing_plant", Registry.oreWashingPlant.getDefaultState(), new ItemStack(Registry.oreWashingPlant));
        ItemAPI.instance.putState("te", "centrifuge", Registry.thermalCentrifuge.getDefaultState(), new ItemStack(Registry.thermalCentrifuge));
        ItemAPI.instance.putStack("crushed", "copper", new ItemStack(Registry.copperCrushedOre));
        ItemAPI.instance.putStack("crushed", "tin", new ItemStack(Registry.tinCrushedOre));
        ItemAPI.instance.putStack("crushed", "silver", new ItemStack(Registry.silverCrushedOre));
        ItemAPI.instance.putStack("crushed", "lead", new ItemStack(Registry.leadCrushedOre));
        ItemAPI.instance.putStack("crushed", "iron", new ItemStack(Registry.ironCrushedOre));
        ItemAPI.instance.putStack("crushed", "gold", new ItemStack(Registry.goldCrushedOre));
        ItemAPI.instance.putStack("crushed", "uranium", new ItemStack(Registry.uraniumCrushedOre));
        ItemAPI.instance.putStack("purified", "copper", new ItemStack(Registry.copperPurifiedCrushedOre));
        ItemAPI.instance.putStack("purified", "tin", new ItemStack(Registry.tinPurifiedCrushedOre));
        ItemAPI.instance.putStack("purified", "silver", new ItemStack(Registry.silverPurifiedCrushedOre));
        ItemAPI.instance.putStack("purified", "lead", new ItemStack(Registry.leadPurifiedCrushedOre));
        ItemAPI.instance.putStack("purified", "iron", new ItemStack(Registry.ironPurifiedCrushedOre));
        ItemAPI.instance.putStack("purified", "gold", new ItemStack(Registry.goldPurifiedCrushedOre));
        ItemAPI.instance.putStack("purified", "uranium", new ItemStack(Registry.uraniumPurifiedCrushedOre));
        ItemAPI.instance.putStack("casing", "bronze", new ItemStack(Registry.bronzeCasing));
        ItemAPI.instance.putStack("casing", "copper", new ItemStack(Registry.copperCasing));
        ItemAPI.instance.putStack("casing", "tin", new ItemStack(Registry.tinCasing));
        ItemAPI.instance.putStack("casing", "steel", new ItemStack(Registry.steelCasing));
        ItemAPI.instance.putStack("casing", "lead", new ItemStack(Registry.leadCasing));
        ItemAPI.instance.putStack("casing", "iron", new ItemStack(Registry.ironCasing));
        ItemAPI.instance.putStack("casing", "gold", new ItemStack(Registry.goldCasing));
        ItemAPI.instance.putStack("dust", "small_bronze", new ItemStack(Registry.bronzeTinyDust));
        ItemAPI.instance.putStack("dust", "small_copper", new ItemStack(Registry.copperTinyDust));
        ItemAPI.instance.putStack("dust", "small_gold", new ItemStack(Registry.goldTinyDust));
        ItemAPI.instance.putStack("dust", "small_iron", new ItemStack(Registry.ironTinyDust));
        ItemAPI.instance.putStack("dust", "small_lead", new ItemStack(Registry.leadTinyDust));
        ItemAPI.instance.putStack("dust", "small_obsidian", new ItemStack(Registry.obsidianTinyDust));
        ItemAPI.instance.putStack("dust", "small_silver", new ItemStack(Registry.silverTinyDust));
        ItemAPI.instance.putStack("dust", "small_tin", new ItemStack(Registry.tinTinyDust));
        ItemAPI.instance.putStack("dust", "stone", new ItemStack(Registry.stoneDust));
        ItemAPI.instance.putStack("dust", "lead", new ItemStack(Registry.leadDust));
        ItemAPI.instance.putStack("dust", "diamond", new ItemStack(Registry.diamondDust));
        ItemAPI.instance.putStack("ingot", "steel", new ItemStack(Registry.steelIngot));
        ItemAPI.instance.putStack("ingot", "lead", new ItemStack(Registry.leadIngot));
        ItemAPI.instance.putStack("nuclear", "uranium_235", new ItemStack(Registry.uranium235));
        ItemAPI.instance.putStack("nuclear", "uranium_238", new ItemStack(Registry.uranium238));
        ItemAPI.instance.putStack("nuclear", "plutonium", new ItemStack(Registry.plutoniumDust));
        ItemAPI.instance.putStack("nuclear", "small_uranium_235", new ItemStack(Registry.uranium235TinyDust));
        ItemAPI.instance.putStack("nuclear", "small_uranium_238", new ItemStack(Registry.uranium238TinyDust));
        ItemAPI.instance.putStack("nuclear", "small_plutonium", new ItemStack(Registry.plutoniumTinyDust));
        ItemAPI.instance.putStack("nuclear", "mox", new ItemStack(Registry.moxFuel));
        ItemAPI.instance.putStack("nuclear", "depleted_mox", new ItemStack(Registry.nearDepletedMOXCell));
        ItemAPI.instance.putStack("nuclear", "depleted_dual_mox", StackUtil.copyWithSize(new ItemStack(Registry.nearDepletedMOXCell), 2));
        ItemAPI.instance.putStack("nuclear", "depleted_quad_mox", StackUtil.copyWithSize(new ItemStack(Registry.nearDepletedMOXCell), 4));
        ItemAPI.instance.putStack("crafting", "fuel_rod", new ItemStack(Registry.emptyFuelRod));
        ItemAPI.instance.putStack("misc_resource", "slag", new ItemStack(Registry.slag));
        ItemAPI.instance.putStack("misc_resource", "iridium_shard", new ItemStack(Registry.iridiumShard));
        ItemAPI.instance.putStack("crafting", "coil", new ItemStack(Registry.coil));
        ItemAPI.instance.putStack("crafting", "heat_conductor", new ItemStack(Registry.heatConductor));
    }

    public void init()
    {
        if (!IC2.config.getFlag("NonRadiation")){
            Ic2cExtrasRecipes.init();
        }
    }

    public void postInit()
    {
        if (!IC2.config.getFlag("NonRadiation")){
            if (Loader.isModLoaded("railcraft")){
                RailcraftRecipes.initRailcraftRecipes();
            }
            Ic2cExtrasRecipes.postInit();
        }
    }
}


