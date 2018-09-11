package trinsdar.ic2c_extras.common.items;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class RegistryItem
{
    public static final Item crushedOres = new ItemCrushedOre("crushedOre");
    public static final Item purifiedCrushedOres = new ItemPurifiedCrushedOre("purifiedCrushedOre");
    public static final Item tinyDustTypes = new ItemTinyDust("tinyDust");
    public static final Item nuclearFuelTypes = new ItemNuclearTypes("nuclearFuel");
    public static final Item itemCasings = new ItemCasings("itemCasings");

    public static final Item[] items =
    {
            crushedOres,
            purifiedCrushedOres,
            tinyDustTypes,
            nuclearFuelTypes,
            itemCasings
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
