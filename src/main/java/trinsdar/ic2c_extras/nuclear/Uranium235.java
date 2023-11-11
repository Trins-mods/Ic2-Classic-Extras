package trinsdar.ic2c_extras.nuclear;

import ic2.core.item.reactor.urantypes.UraniumBaseType;
import net.minecraft.world.item.ItemStack;
import trinsdar.ic2c_extras.init.ModItems;

public class Uranium235 extends UraniumBaseType {

    public static Uranium235 INSTANCE = new Uranium235();

    @Override
    public int getRodDurability() {
        return 5000;
    }

    @Override
    public float getPulseEU() {
        return 1.0f;
    }

    @Override
    public int getUraniumPulses() {
        return 4;
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
        return 4.0f;
    }

    @Override
    public ItemStack getBaseIngot() {
        return new ItemStack(ModItems.URANIUM_235_INGOT);
    }

    @Override
    public String getName() {
        return "uranium_235";
    }

    @Override
    public boolean isEnrichedUranium() {
        return true;
    }

    @Override
    public int getFusionHeat() {
        return 15;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public ItemStack createNearDepletedRod(int count) {
        return new ItemStack(ModItems.NEAR_DEPLETED_URANIUM_235_ROD, count);
    }

    @Override
    public ItemStack createReEnrichedRod() {
        return new ItemStack(ModItems.RE_ENRICHED_URANIUM_235_ROD);
    }

    @Override
    public ItemStack createIsotopicRod() {
        ItemStack stack = new ItemStack(ModItems.ISOTOPIC_URANIUM_235_ROD);
        stack.setDamageValue(stack.getMaxDamage());
        return stack;
    }

    @Override
    public ItemStack createSingleRod() {
        return new ItemStack(ModItems.URANIUM_235_ROD);
    }

    @Override
    public ItemStack createDualRod() {
        return new ItemStack(ModItems.DUAL_URANIUM_235_ROD);
    }

    @Override
    public ItemStack createQuadRod() {
        return new ItemStack(ModItems.QUAD_URANIUM_235_ROD);
    }
}
