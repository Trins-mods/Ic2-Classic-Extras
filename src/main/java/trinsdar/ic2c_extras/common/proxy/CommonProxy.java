package trinsdar.ic2c_extras.common.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import trinsdar.ic2c_extras.common.blocks.OreWashingPlant;
import trinsdar.ic2c_extras.common.ModBlocks;


@Mod.EventBusSubscriber
public class CommonProxy {
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    event.getRegistry().register(new OreWashingPlant());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    event.getRegistry().register(new ItemBlock(ModBlocks.oreWashingPlant).setRegistryName(ModBlocks.oreWashingPlant.getRegistryName()));
    }
}


