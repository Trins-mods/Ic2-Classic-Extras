package trinsdar.ic2c_extras.nuclear;

import ic2.core.item.reactor.base.IUraniumRod;
import ic2.core.item.reactor.urantypes.UraniumBaseType;
import net.minecraft.world.item.ItemStack;
import trinsdar.ic2c_extras.init.ModItems;

import java.util.List;

public class Thorium232 extends UraniumBaseType {
    public static Thorium232 INSTANCE = new Thorium232();
    @Override
    public int getRodDurability() {
        return 32000;
    }

    @Override
    public float getPulseEU() {
        return 0.6f;
    }

    @Override
    public int getUraniumPulses() {
        return 1;
    }

    @Override
    public int getPulsesForConnection() {
        return 1;
    }

    @Override
    public float getPulseHeatModifier() {
        return 0.25f;
    }

    @Override
    public float getExplosionModifier() {
        return 0.5f;
    }

    @Override
    public boolean isEnrichedUranium() {
        return false;
    }

    @Override
    public int getFusionHeat() {
        return 1;
    }

    @Override
    public ItemStack getBaseIngot() {
        return new ItemStack(ModItems.THORIUM_INGOT);
    }

    @Override
    public String getName() {
        return "thorium";
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public ItemStack createNearDepletedRod(int count) {
        return new ItemStack(ModItems.NEAR_DEPLETED_THORIUM_ROD, count);
    }

    @Override
    public ItemStack createReEnrichedRod() {
        return new ItemStack(ModItems.RE_ENRICHED_THORIUM_ROD);
    }

    @Override
    public ItemStack createIsotopicRod() {
        ItemStack stack = new ItemStack(ModItems.ISOTOPIC_THORIUM_ROD);
        stack.setDamageValue(stack.getMaxDamage());
        return stack;
    }

    @Override
    public ItemStack createSingleRod() {
        return new ItemStack(ModItems.THORIUM_ROD);
    }

    @Override
    public ItemStack createDualRod() {
        return new ItemStack(ModItems.DUAL_THORIUM_ROD);
    }

    @Override
    public ItemStack createQuadRod() {
        return new ItemStack(ModItems.QUAD_THORIUM_ROD);
    }
}
