package trinsdar.ic2c_extras.nuclear;

import ic2.core.item.reactor.urantypes.UraniumBaseType;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.world.item.ItemStack;
import trinsdar.ic2c_extras.init.ModItems;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

public class Uranium238 extends UraniumBaseType {
    public static Uranium238 INSTANCE = new Uranium238();

    @Override
    public List<int[]> getPulseArea() {
        return Collections.emptyList();
    }

    @Override
    public int getRodDurability() {
        return 8000;
    }

    @Override
    public float getPulseEU() {
        return 0.7f;
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
        return 0.5f;
    }

    @Override
    public float getExplosionModifier() {
        return 2.0f;
    }

    @Override
    public ItemStack getBaseIngot() {
        return new ItemStack(IC2Items.INGOT_URANIUM);
    }

    @Override
    public String getName() {
        return "uranium_238";
    }

    @Override
    public boolean isEnrichedUranium() {
        return false;
    }

    @Override
    public int getFusionHeat() {
        return 3;
    }

    @Override
    public int getColor() {
        return 0xffffff;
    }

    @Override
    public ItemStack createNearDepletedRod(int count) {
        return new ItemStack(ModItems.NEAR_DEPLETED_URANIUM_238_ROD, count);
    }

    @Override
    public ItemStack createReEnrichedRod() {
        return new ItemStack(ModItems.RE_ENRICHED_URANIUM_238_ROD);
    }

    @Override
    public ItemStack createIsotopicRod() {
        ItemStack stack = new ItemStack(ModItems.ISOTOPIC_URANIUM_238_ROD);
        stack.setDamageValue(stack.getMaxDamage());
        return stack;
    }

    @Override
    public ItemStack createSingleRod() {
        return new ItemStack(ModItems.URANIUM_238_ROD);
    }

    @Override
    public ItemStack createDualRod() {
        return new ItemStack(ModItems.DUAL_URANIUM_238_ROD);
    }

    @Override
    public ItemStack createQuadRod() {
        return new ItemStack(ModItems.QUAD_URANIUM_238_ROD);
    }
}
