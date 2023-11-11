package trinsdar.ic2c_extras.nuclear;

import ic2.core.item.reactor.urantypes.UraniumBaseType;
import ic2.core.platform.registries.IC2Items;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.item.ItemStack;
import trinsdar.ic2c_extras.init.ModItems;

import java.awt.Color;


public class MOX extends UraniumBaseType {
    public static MOX INSTANCE = new MOX();


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
        return 1;
    }

    @Override
    public int getPulsesForConnection() {
        return 1;
    }

    @Override
    public float getPulseHeatModifier() {
        return 1.0f;
    }

    @Override
    public float getExplosionModifier() {
        return 2.0f;
    }

    @Override
    public ItemStack getBaseIngot() {
        return new ItemStack(ModItems.MOX_FUEL);
    }

    @Override
    public String getName() {
        return "mox";
    }

    @Override
    public boolean isEnrichedUranium() {
        return true;
    }

    @Override
    public int getFusionHeat() {
        return 5;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public ItemStack createNearDepletedRod(int count) {
        return new ItemStack(ModItems.NEAR_DEPLETED_MOX_ROD, count);
    }

    @Override
    public ItemStack createReEnrichedRod() {
        return new ItemStack(ModItems.RE_ENRICHED_MOX_ROD);
    }

    @Override
    public ItemStack createIsotopicRod() {
        ItemStack stack = new ItemStack(ModItems.ISOTOPIC_MOX_ROD);
        stack.setDamageValue(stack.getMaxDamage());
        return stack;
    }

    @Override
    public ItemStack createSingleRod() {
        return new ItemStack(ModItems.MOX_ROD);
    }

    @Override
    public ItemStack createDualRod() {
        return new ItemStack(ModItems.DUAL_MOX_ROD);
    }

    @Override
    public ItemStack createQuadRod() {
        return new ItemStack(ModItems.QUAD_MOX_ROD);
    }
}
