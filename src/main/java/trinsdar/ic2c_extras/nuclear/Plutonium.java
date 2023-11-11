package trinsdar.ic2c_extras.nuclear;

import ic2.core.item.reactor.urantypes.UraniumBaseType;
import net.minecraft.world.item.ItemStack;
import trinsdar.ic2c_extras.init.ModItems;

import java.awt.*;

public class Plutonium extends UraniumBaseType {
    public static Plutonium INSTANCE = new Plutonium();

    @Override
    public int getRodDurability() {
        return 10000;
    }

    @Override
    public float getPulseEU() {
        return 1.0f;
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
        return 4.0f;
    }

    @Override
    public float getExplosionModifier() {
        return 6.0f;
    }

    @Override
    public ItemStack getBaseIngot() {
        return new ItemStack(ModItems.PLUTONIUM_INGOT);
    }

    @Override
    public String getName() {
        return "plutonium";
    }

    @Override
    public boolean isEnrichedUranium() {
        return true;
    }

    @Override
    public int getFusionHeat() {
        return 25;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public ItemStack createNearDepletedRod(int count) {
        return new ItemStack(ModItems.NEAR_DEPLETED_PLUTONIUM_ROD, count);
    }

    @Override
    public ItemStack createReEnrichedRod() {
        return new ItemStack(ModItems.RE_ENRICHED_PLUTONIUM_ROD);
    }

    @Override
    public ItemStack createIsotopicRod() {
        ItemStack stack = new ItemStack(ModItems.ISOTOPIC_PLUTONIUM_ROD);
        stack.setDamageValue(stack.getMaxDamage());
        return stack;
    }

    @Override
    public ItemStack createSingleRod() {
        return new ItemStack(ModItems.PLUTONIUM_ROD);
    }

    @Override
    public ItemStack createDualRod() {
        return new ItemStack(ModItems.DUAL_PLUTONIUM_ROD);
    }

    @Override
    public ItemStack createQuadRod() {
        return new ItemStack(ModItems.QUAD_PLUTONIUM_ROD);
    }
}
