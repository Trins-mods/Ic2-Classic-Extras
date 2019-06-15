package trinsdar.ic2c_extras.items.urantypes;

import ic2.core.item.reactor.uranTypes.UranBaseType;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class Plutonium extends UranBaseType {
    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getMaxDurability() {
        return 0;
    }

    @Override
    public float getEUPerPulse() {
        return 0;
    }

    @Override
    public int getPulsesPerTick() {
        return 0;
    }

    @Override
    public int getPulsesForConnection() {
        return 0;
    }

    @Override
    public float getHeatModifier() {
        return 0;
    }

    @Override
    public float getExplosionEffectModifier() {
        return 0;
    }

    @Override
    public ItemStack getUraniumIngot() {
        return null;
    }

    @Override
    public ItemStack getRodType(RodType rodType) {
        return null;
    }

    @Override
    public ItemStack getNewIsotopicRod() {
        return null;
    }

    @Override
    public short getRodID(RodType rodType) {
        return 0;
    }

    @Override
    public LocaleComp getName(RodType rodType) {
        return null;
    }

    @Override
    public boolean isReEnrichedUran() {
        return false;
    }

    @Override
    public Color getReEnrichedColor() {
        return null;
    }

    @Override
    public ItemStack getIngridient() {
        return null;
    }

    @Override
    public int getIngrientPoints() {
        return 0;
    }

    @Override
    public int getIngridientCost() {
        return 0;
    }
}
