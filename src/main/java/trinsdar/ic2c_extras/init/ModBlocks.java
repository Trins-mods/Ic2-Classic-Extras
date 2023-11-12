package trinsdar.ic2c_extras.init;

import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.platform.registries.IC2Tiles;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import trinsdar.ic2c_extras.block.BlockMachine;
import trinsdar.ic2c_extras.block.BlockOre;
import trinsdar.ic2c_extras.block.BlockResource;
import trinsdar.ic2c_extras.blockentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blockentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.blockentity.TileEntityThermoElectricGenerator;

public class ModBlocks {

    public static final Block LEAD_ORE = new BlockOre("lead_ore", 4.0f, 5.0f, 2, false);
    public static final Block DEEPSLATE_LEAD_ORE = new BlockOre("deepslate_lead_ore", 4.0f, 5.0f, 2, true);
    public static final Block LEAD_BLOCK = new BlockResource("lead_block", 2, BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5.0f, 6.0f).requiresCorrectToolForDrops());
    public static final Block RAW_LEAD_BLOCK = new BlockResource("raw_lead_block", 1, BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(4.0f, 5.0f).requiresCorrectToolForDrops());

    public static final BlockEntityType<TileEntityThermalCentrifuge> THERMAL_CENTRIFUGE_TYPE = IC2Tiles.createTile("thermal_centrifuge", TileEntityThermalCentrifuge::new);
    public static final BlockEntityType<TileEntityOreWashingPlant> ORE_WASHING_PLANT_TYPE = IC2Tiles.createTile("ore_washing_plant", TileEntityOreWashingPlant::new);
    public static final BlockEntityType<TileEntityThermoElectricGenerator> THERMO_ELECTRIC_GENERATOR_TYPE = IC2Tiles.createTile("thermo_electric_generator", TileEntityThermoElectricGenerator::new);

    public static final Block THERMAL_CENTRIFUGE = new BlockMachine("thermal_centrifuge", IBlockDropProvider.SELF_OR_ADV_MACHINE, THERMAL_CENTRIFUGE_TYPE);
    public static final Block ORE_WASHING_PLANT = new BlockMachine("ore_washing_plant", IBlockDropProvider.SELF_OR_MACHINE, ORE_WASHING_PLANT_TYPE);
    public static final Block THERMO_ELECTRIC_GENERATOR = new BlockMachine("thermo_electric_generator", IBlockDropProvider.SELF_OR_MACHINE, THERMO_ELECTRIC_GENERATOR_TYPE);
    public static void init(){
    }
}
