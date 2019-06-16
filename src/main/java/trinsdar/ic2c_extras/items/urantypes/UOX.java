package trinsdar.ic2c_extras.items.urantypes;

import ic2.core.item.reactor.uranTypes.UranBaseType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.util.Registry;

import java.awt.*;

import static ic2.core.item.reactor.uranTypes.IUranium.RodType.DualRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.IsotopicRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.NearDepletedRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.QuadRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.ReEnrichedRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.SingleRod;

public class UOX extends UranBaseType {
    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getMaxDurability() {
        return 8000;
    }

    @Override
    public float getEUPerPulse() {
        return 1.0f;
    }

    @Override
    public int getPulsesPerTick() {
        return 2;
    }

    @Override
    public int getPulsesForConnection() {
        return 1;
    }

    @Override
    public float getHeatModifier() {
        return 0.5f;
    }

    @Override
    public float getExplosionEffectModifier() {
        return 2.0f;
    }

    @Override
    public ItemStack getUraniumIngot() {
        return null;
    }

    @Override
    public ItemStack getRodType(RodType type) {
        switch(type) {
            case SingleRod:
                return Ic2Items.reactorEnderPearlUraniumRodSingle.copy();
            case DualRod:
                return Ic2Items.reactorEnderPearlUraniumRodDual.copy();
            case QuadRod:
                return Ic2Items.reactorEnderPearlUraniumRodQuad.copy();
            case NearDepletedRod:
                return new ItemStack(Registry.nearDepletedUOXCell).copy();
            case IsotopicRod:
                return Ic2Items.reactorEnderPearlUraniumIsotopicRod.copy();
            case ReEnrichedRod:
                return new ItemStack(Registry.reEnrichedUOXCell).copy();
            default:
                return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack getNewIsotopicRod() {
        return StackUtil.copyWithDamage(this.getRodType(IsotopicRod), this.getMaxDurability() - 1);
    }

    @Override
    public short getRodID(RodType type) {
        switch(type) {
            case SingleRod:
                return 1100;
            case DualRod:
                return 1101;
            case QuadRod:
                return 1102;
            case ReEnrichedRod:
                return 1103;
            case NearDepletedRod:
                return 1104;
            case IsotopicRod:
                return 1105;
            default:
                return 0;
        }
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
