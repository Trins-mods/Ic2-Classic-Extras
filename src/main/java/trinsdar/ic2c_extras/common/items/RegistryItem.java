package trinsdar.ic2c_extras.common.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class RegistryItem
{
    public static final Item crushedOres = new ItemCrushedOre("crushedOre");
    public static final Item purifiedCrushedOres = new ItemPurifiedCrushedOre("purifiedCrushedOre");

    public static final Item[] items =
    {
            crushedOres,
            purifiedCrushedOres
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
}
