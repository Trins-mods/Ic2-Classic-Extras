package trinsdar.ic2c_extras.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.tileentity.TileEntityBase;

public class RegistryBlock
{
    public static final Block blockOreWashingPlant = new BlockOreWashingPlant(Material.IRON, "blockOreWashingPlant");

    public static final Block blockThermalCentrifuge = new BlockThermalCentrifuge(Material.IRON, "blockThermalCentrifuge");

    public static final Block blockCasing = new BlockCasing(Material.IRON, "blockCasing");

    public static final Block[] blocks =
    {
            blockOreWashingPlant,
            blockThermalCentrifuge
    };

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry registry = event.getRegistry();

        for (Block block : blocks)
        {
            registry.register(block);
        }

    }

    public static void registerTiles()
    {
         GameRegistry.registerTileEntity(TileEntityBase.class, new ResourceLocation(Ic2cExtras.MODID, "tileEntityOreWashingPlant"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry registry = event.getRegistry();

        for (Block block : blocks)
        {
            registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));
        }
    }
}
