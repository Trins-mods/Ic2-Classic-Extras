package trinsdar.ic2c_extras.util;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import trinsdar.ic2c_extras.items.*;
import trinsdar.ic2c_extras.items.ItemMiscs.ItemMiscsTypes;

public class RegistryItem
{
    public static final ItemCasings
    copperCasing = new ItemCasings(ItemMaterials.COPPER, 48),
    tinCasing = new ItemCasings(ItemMaterials.TIN, 49),
    silverCasing = new ItemCasings(ItemMaterials.SILVER, 50),
    leadCasing = new ItemCasings(ItemMaterials.LEAD, 51),
    ironCasing = new ItemCasings(ItemMaterials.IRON, 52),
    goldCasing = new ItemCasings(ItemMaterials.GOLD, 53),
    refinedIronCasing = new ItemCasings(ItemMaterials.REFINED_IRON, 54),
    steelCasing = new ItemCasings(ItemMaterials.STEEL, 55),
    bronzeCasing = new ItemCasings(ItemMaterials.BRONZE, 56);

    public static final ItemCrushedOre
    ironCrushedOre = new ItemCrushedOre(ItemMaterials.IRON, 0),
    goldCrushedOre = new ItemCrushedOre(ItemMaterials.GOLD, 1),
    copperCrushedOre = new ItemCrushedOre(ItemMaterials.COPPER, 2),
    tinCrushedOre = new ItemCrushedOre(ItemMaterials.TIN, 3),
    silverCrushedOre = new ItemCrushedOre(ItemMaterials.SILVER, 4),
    leadCrushedOre = new ItemCrushedOre(ItemMaterials.LEAD, 5),
    uraniumCrushedOre = new ItemCrushedOre(ItemMaterials.URANIUM, 6);

    public static final ItemPurifiedCrushedOre
    ironPurifiedCrushedOre = new ItemPurifiedCrushedOre(ItemMaterials.IRON, 16),
    goldPurifiedCrushedOre = new ItemPurifiedCrushedOre(ItemMaterials.GOLD, 17),
    copperPurifiedCrushedOre = new ItemPurifiedCrushedOre(ItemMaterials.COPPER, 18),
    tinPurifiedCrushedOre = new ItemPurifiedCrushedOre(ItemMaterials.TIN, 19),
    silverPurifiedCrushedOre = new ItemPurifiedCrushedOre(ItemMaterials.SILVER, 20),
    leadPurifiedCrushedOre = new ItemPurifiedCrushedOre(ItemMaterials.LEAD, 21),
    uraniumPurifiedCrushedOre = new ItemPurifiedCrushedOre(ItemMaterials.URANIUM, 22);

    public static final ItemTinyDust
    ironTinyDust = new ItemTinyDust(ItemMaterials.IRON, 32),
    goldTinyDust = new ItemTinyDust(ItemMaterials.GOLD, 33),
    copperTinyDust = new ItemTinyDust(ItemMaterials.COPPER, 34),
    tinTinyDust = new ItemTinyDust(ItemMaterials.TIN, 35),
    silverTinyDust = new ItemTinyDust(ItemMaterials.SILVER, 36),
    leadTinyDust = new ItemTinyDust(ItemMaterials.LEAD, 37),
    uranium235TinyDust = new ItemTinyDust(ItemMaterials.URANIUM235, 38),
    obsidianTinyDust = new ItemTinyDust(ItemMaterials.OBSIDIAN, 39),
    bronzeTinyDust = new ItemTinyDust(ItemMaterials.BRONZE, 40),
    uranium238TinyDust = new ItemTinyDust(ItemMaterials.URANIUM238, 41),
    plutoniumTinyDust = new ItemTinyDust(ItemMaterials.PLUTONIUM, 42);

    public static final ItemMiscs
    leadIngot = new ItemMiscs(ItemMiscsTypes.LEAD_INGOT),
    leadDust = new ItemMiscs(ItemMiscsTypes.LEAD_DUSTS),
    stoneDust = new ItemMiscs(ItemMiscsTypes.STONE_DUSTS),
    slag = new ItemMiscs(ItemMiscsTypes.SLAG),
    uranium235 = new ItemMiscs(ItemMiscsTypes.URANIUM235),
    uranium238 = new ItemMiscs(ItemMiscsTypes.URANIUM238),
    plutonium = new ItemMiscs(ItemMiscsTypes.PLUTONIUM),
    coil = new ItemMiscs(ItemMiscsTypes.COIL),
    heatConductor = new ItemMiscs(ItemMiscsTypes.HEAT_CONDUCTOR),
    steelIngot = new ItemMiscs(ItemMiscsTypes.STEEL_INGOT),
    plutoniumEnrichedUraniumIngot = new ItemMiscs(ItemMiscsTypes.PLUTONIUM_ENRICHED_URANIUM_INGOT),
    plutoniumEnrichedUranium = new ItemMiscs(ItemMiscsTypes.PLUTONIUM_ENRICHED_URANIUM),
    iridiumShard = new ItemMiscs(ItemMiscsTypes.IRIDIUM_SHARD);

    public static final ItemToolCrafting
    craftingHammer = new ItemToolCrafting(50, "craftingHammer", 77, true),
    wireCutters = new ItemToolCrafting(50, "wireCutters", 78, true);
    

    public static final Item[] items =
    {
        copperCasing,
        tinCasing,
        silverCasing,
        leadCasing,
        ironCasing,
        goldCasing,
        refinedIronCasing,
        steelCasing,
        bronzeCasing,

        ironCrushedOre,
        goldCrushedOre,
        copperCrushedOre,
        tinCrushedOre,
        silverCrushedOre,
        leadCrushedOre,
        uraniumCrushedOre,

        ironPurifiedCrushedOre,
        goldPurifiedCrushedOre,
        copperPurifiedCrushedOre,
        tinPurifiedCrushedOre,
        silverPurifiedCrushedOre,
        leadPurifiedCrushedOre,
        uraniumPurifiedCrushedOre,

        ironTinyDust,
        goldTinyDust,
        copperTinyDust,
        tinTinyDust,
        silverTinyDust,
        leadTinyDust,
        uranium235TinyDust,
        obsidianTinyDust,
        bronzeTinyDust,
        uranium238TinyDust,
        plutoniumTinyDust,

        leadIngot,
        leadDust,
        stoneDust,
        slag,
        uranium235,
        uranium238,
        plutonium,
        coil,
        heatConductor,
        steelIngot,
        plutoniumEnrichedUraniumIngot,
        plutoniumEnrichedUranium,
        iridiumShard,
        craftingHammer,
        wireCutters
    };

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry registry = event.getRegistry();

        for (Item item : items)
        {
            registry.register(item);
        }
    }

    public static void registerModels() {
    }

    @SubscribeEvent
    public static void registerItems(ModelRegistryEvent event) {
        RegistryItem.registerModels();
    }
}
