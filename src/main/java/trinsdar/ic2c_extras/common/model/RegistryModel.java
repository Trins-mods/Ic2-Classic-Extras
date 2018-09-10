package trinsdar.ic2c_extras.common.model;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.items.ItemCrushedOre;
import trinsdar.ic2c_extras.common.items.ItemTinyDust;
import trinsdar.ic2c_extras.common.items.RegistryItem;

@Mod.EventBusSubscriber
public class RegistryModel
{
    @SubscribeEvent
    public static void initModels(ModelRegistryEvent event)
    {
        registerBlockModels();
        registerItemModels();
    }

    public static void registerBlockModels()
    {

    }

    public static void registerItemModels()
    {
        int i = 0;

        for (String oreType : ItemCrushedOre.oreTypes)
        {
            System.out.println(i);
            registerItemModelWithMeta(RegistryItem.crushedOres, i, new ResourceLocation(Ic2cExtras.MODID, "crushed_ore/crushed_ore_" + oreType));
            registerItemModelWithMeta(RegistryItem.purifiedCrushedOres, i, new ResourceLocation(Ic2cExtras.MODID, "purified_crushed_ore/purified_crushed_ore_" + oreType));
            i++;
        }

        i = 0;

        for (String dustType : ItemTinyDust.tinyDustTypes)
        {
            registerItemModelWithMeta(RegistryItem.tinyDustTypes, i, new ResourceLocation(Ic2cExtras.MODID, "tiny_dust/tiny_dust_" + dustType));
            i++;
        }
    }

    public static void registerBlockItemModel(Block block)
    {
        registerItemModel(Item.getItemFromBlock(block), new ModelResourceLocation(new ResourceLocation(block.getRegistryName().toString()), "inventory"));
    }

    public static void registerBlockItemModelWithMetaAndVariant(Block block, int meta, ModelResourceLocation modelLocation)
    {
        registerItemModelWithMeta(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(block.getRegistryName().toString()), "inventory"));
    }

    public static void registerItemModel(Item item)
    {
        registerItemModel(item, new ModelResourceLocation(new ResourceLocation(item.getRegistryName().toString()), "inventory"));
    }

    public static void registerItemModel(Item item, ResourceLocation location)
    {
        registerItemModelWithMetaAndVariant(item, 0, new ModelResourceLocation(location, "inventory"));
    }

    public static void registerItemModelWithMeta(Item item, int meta, ResourceLocation location)
    {
        registerItemModelWithMetaAndVariant(item, meta, new ModelResourceLocation(location, "inventory"));
    }

    public static void registerItemModelWithMetaAndVariant(Item item, int meta, ModelResourceLocation modelLocation)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, modelLocation);
    }
}
