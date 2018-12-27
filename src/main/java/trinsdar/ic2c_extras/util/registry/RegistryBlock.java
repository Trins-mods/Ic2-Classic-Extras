package trinsdar.ic2c_extras.util.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.blocks.*;
import trinsdar.ic2c_extras.blocks.tileentity.*;

public class RegistryBlock
{
    public static final BlockMachine
    oreWashingPlant = new BlockMachine("oreWashingPlant"),
    thermalCentrifuge = new BlockMachine("thermalCentrifuge"),
    metalPress = new BlockMachine("metalPress"),
    roller = new BlockMachine("roller"),
    extruder = new BlockMachine("extruder"),
    cutter = new BlockMachine("cutter"),
    impellerizedRoller = new BlockMachine("impellerizedRoller"),
    liquescentExtruder = new BlockMachine("liquescentExtruder"),
    airjetCutter = new BlockMachine("airjetCutter"),
    blastFurnace = new BlockMachine("blastFurnace");
    public static final BlockIc2cEGenerator
    advancedSteamTurbine = new BlockIc2cEGenerator("advancedSteamTurbine"),
    solidFuelFirebox = new BlockIc2cEGenerator("solidFuelFirebox"),
    liquidFuelFirebox = new BlockIc2cEGenerator("liquidFuelFirebox"),
    electricHeater = new BlockIc2cEGenerator("electricHeater");
    public static final BlockMetal
    steelBlock = new BlockMetal("steelBlock", 0),
    refinedIronBlock = new BlockMetal("refinedIronBlock", 1),
    leadBlock = new BlockMetal("leadBlock", 2);


    public static final Block[] blocks =
    {
            oreWashingPlant,
            metalPress,
            roller,
            extruder,
            cutter,
            impellerizedRoller,
            liquescentExtruder,
            thermalCentrifuge,
            blastFurnace,
            advancedSteamTurbine,
            solidFuelFirebox,
            liquidFuelFirebox,
            electricHeater,
            steelBlock,
            refinedIronBlock,
            leadBlock
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
         GameRegistry.registerTileEntity(TileEntityOreWashingPlant.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityOreWashingPlant"));
         GameRegistry.registerTileEntity(TileEntityAdvancedSteamTurbine.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityAdvancedSteamTurbine"));
         GameRegistry.registerTileEntity(TileEntityThermalCentrifuge.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityThermalCentrifuge"));
         GameRegistry.registerTileEntity(TileEntityMetalPress.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityMetalPress"));
        GameRegistry.registerTileEntity(TileEntityRoller.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityRoller"));
        GameRegistry.registerTileEntity(TileEntityExtruder.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityExtruder"));
        GameRegistry.registerTileEntity(TileEntityCutter.class, new ResourceLocation(IC2CExtras.MODID, "tileEntityCutter"));
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
