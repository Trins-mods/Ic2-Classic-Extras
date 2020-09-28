package trinsdar.ic2c_extras.items.urantypes;

import ic2.core.item.reactor.uranTypes.UranBaseType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.Registry;

import java.awt.Color;

import static ic2.core.item.reactor.uranTypes.IUranium.RodType.IsotopicRod;

public class Uranium235 extends UranBaseType {
    public Uranium235() {
        this.loadDefaults();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(RodType type) {
        return Ic2Icons.getTextures("ic2c_extras_nuclear_cells")[this.getTextureID(type)];
    }

    @Override
    public int getRow() {
        return 1;
    }

    @Override
    public int getMaxDurability() {
        return 5000;
    }

    @Override
    public float getEUPerPulse() {
        return 1.0f;
    }

    @Override
    public int getPulsesPerTick() {
        return 4;
    }

    @Override
    public int getPulsesForConnection() {
        return 1;
    }

    @Override
    public float getHeatModifier() {
        return 0.25f;
    }

    @Override
    public float getExplosionEffectModifier() {
        return 4.0f;
    }

    @Override
    public ItemStack getUraniumIngot() {
        return new ItemStack(Registry.uranium235Ingot);
    }

    @Override
    public ItemStack getRodType(RodType type) {
        switch (type) {
            case SingleRod:
                return new ItemStack(Registry.singleUranium235Cell).copy();
            case DualRod:
                return new ItemStack(Registry.doubleUranium235Cell).copy();
            case QuadRod:
                return new ItemStack(Registry.quadUranium235Cell).copy();
            case NearDepletedRod:
                return new ItemStack(Registry.nearDepletedUranium235Cell).copy();
            case IsotopicRod:
                return new ItemStack(Registry.isotopicUranium235Cell).copy();
            case ReEnrichedRod:
                return new ItemStack(Registry.reEnrichedUranium235Cell).copy();
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
        switch (type) {
            case SingleRod:
                return 1130;
            case DualRod:
                return 1131;
            case QuadRod:
                return 1132;
            case ReEnrichedRod:
                return 1133;
            case NearDepletedRod:
                return 1134;
            case IsotopicRod:
                return 1135;
            default:
                return 0;
        }
    }

    public short getTextureID(RodType type) {
        switch (type) {
            case SingleRod:
                return 17;
            case DualRod:
                return 11;
            case QuadRod:
                return 5;
            case ReEnrichedRod:
                return 35;
            case NearDepletedRod:
                return 23;
            case IsotopicRod:
                return 29;
            default:
                return 0;
        }
    }

    @Override
    public LocaleComp getName(RodType rodType) {
        return Ic2Lang.nullKey;
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
