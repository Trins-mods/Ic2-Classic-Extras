package trinsdar.ic2c_extras.block;

import ic2.core.block.resource.OreBlock;
import ic2.core.item.base.IC2BlockItem;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.utils.helpers.Tool;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.init.ModItems;

import javax.annotation.Nullable;

public class BlockOre extends BlockResource {
    private final IntProvider dropRate = UniformInt.of(1, 3);
    public BlockOre(String blockName, float hardness, float resistance, int harvestLevel, boolean deepslate) {
        super(blockName, harvestLevel, Properties.of(Material.STONE).color(deepslate ? MaterialColor.DEEPSLATE : MaterialColor.STONE).strength(hardness, resistance).requiresCorrectToolForDrops());
    }

    public ItemStack createDrop(BlockState state, ItemStack tool, RandomSource rand, @Nullable BlockEntity tile) {
        return EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SILK_TOUCH, tool) > 0 ? new ItemStack(this) : new ItemStack(ModItems.RAW_LEAD, this.generateLoot(rand, EnchantmentHelper.getTagEnchantmentLevel(Enchantments.BLOCK_FORTUNE, tool)));
    }

    private int generateLoot(RandomSource source, int lootLevel) {
        return this.dropRate.sample(source) * (Math.max(0, source.nextInt(lootLevel + 2) - 1) + 1);
    }

}
