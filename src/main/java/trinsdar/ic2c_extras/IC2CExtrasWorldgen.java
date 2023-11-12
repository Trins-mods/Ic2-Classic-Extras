package trinsdar.ic2c_extras;

import com.mojang.serialization.Codec;
import ic2.core.platform.events.WorldGenerator;
import ic2.core.platform.registries.IC2Blocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import trinsdar.ic2c_extras.init.ModBlocks;

import java.util.List;

import static ic2.core.platform.events.WorldGenerator.target;

public class IC2CExtrasWorldgen {
    static ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>> LEAD_FEATURE;
    static Holder<PlacedFeature> ORE_LEAD;
    public static void init(){
        LEAD_FEATURE = WorldGenerator.createOreFeature(new ResourceLocation(IC2CExtras.MODID, "lead_ore"), 5, 0.0F, target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE), target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_LEAD_ORE));
        ORE_LEAD = WorldGenerator.feature(new ResourceLocation(IC2CExtras.MODID, "lead_ore"), LEAD_FEATURE, List.of(CountPlacement.of(10), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(22)), BiomeFilter.biome()));
    }

    public static class IC2CExtrasModifier implements BiomeModifier {
        public static final Codec<IC2CExtrasModifier> CODEC = Codec.unit(IC2CExtrasModifier::new);
        @Override
        public void modify(Holder<Biome> arg, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
            if (phase == Phase.ADD){
                if (IC2CExtrasConfig.LEAD_ORE_GEN.get()){
                    builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ORE_LEAD);
                }
            }
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {
            return CODEC;
        }
    }
}
