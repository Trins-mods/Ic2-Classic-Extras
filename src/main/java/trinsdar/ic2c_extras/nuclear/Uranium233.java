package trinsdar.ic2c_extras.nuclear;

import ic2.core.item.reactor.urantypes.UraniumBaseType;
import net.minecraft.world.item.ItemStack;
import trinsdar.ic2c_extras.init.ModItems;

import java.awt.*;

public class Uranium233 extends UraniumBaseType {
    public static Uranium233 INSTANCE = new Uranium233();
    public Uranium233(){
        this.addArray(-1, -1);
        this.addArray(0, -1);
        this.addArray(1, -1);
        this.addArray(-1, 0);
        this.addArray(1, 0);
        this.addArray(-1, 1);
        this.addArray(0, 1);
        this.addArray(1, 1);
    }

    @Override
    public int getRodDurability() {
        return 12000;
    }

    @Override
    public float getPulseEU() {
        return 0.5f;
    }

    @Override
    public int getUraniumPulses() {
        return 2;
    }

    @Override
    public int getPulsesForConnection() {
        return 1;
    }

    @Override
    public float getPulseHeatModifier() {
        return 0.8f;
    }

    @Override
    public float getExplosionModifier() {
        return 2.0f;
    }

    @Override
    public ItemStack getBaseIngot() {
        return new ItemStack(ModItems.URANIUM_233_INGOT);
    }

    @Override
    public String getName() {
        return "uranium_233";
    }

    @Override
    public boolean isEnrichedUranium() {
        return true;
    }

    @Override
    public int getFusionHeat() {
        return 20;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public ItemStack createNearDepletedRod(int count) {
        return new ItemStack(ModItems.NEAR_DEPLETED_URANIUM_233_ROD, count);
    }

    @Override
    public ItemStack createReEnrichedRod() {
        return new ItemStack(ModItems.RE_ENRICHED_URANIUM_233_ROD);
    }

    @Override
    public ItemStack createIsotopicRod() {
        ItemStack stack = new ItemStack(ModItems.ISOTOPIC_URANIUM_233_ROD);
        stack.setDamageValue(stack.getMaxDamage());
        return stack;
    }

    @Override
    public ItemStack createSingleRod() {
        return new ItemStack(ModItems.URANIUM_233_ROD);
    }

    @Override
    public ItemStack createDualRod() {
        return new ItemStack(ModItems.DUAL_URANIUM_233_ROD);
    }

    @Override
    public ItemStack createQuadRod() {
        return new ItemStack(ModItems.QUAD_URANIUM_233_ROD);
    }
}
