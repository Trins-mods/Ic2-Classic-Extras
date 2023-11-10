package trinsdar.ic2c_extras.items.urantypes;

import ic2.core.item.reactor.uranTypes.UranBaseType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.Registry;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import static ic2.core.item.reactor.uranTypes.IUranium.RodType.IsotopicRod;

public class Uranium238 extends UranBaseType {
    public Uranium238() {
        this.loadDefaults();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(RodType type) {
        return Ic2Icons.getTextures("ic2c_extras_nuclear_cells")[this.getTextureID(type)];
    }

    @Override
    public List<int[]> getPulseArea(int pulse) {
        return Collections.emptyList();
    }

    @Override
    public int getRow() {
        return 1;
    }

    @Override
    public int getMaxDurability() {
        return 8000;
    }

    @Override
    public float getEUPerPulse() {
        return 0.7f;
    }

    @Override
    public int getPulsesPerTick() {
        return 1;
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
        return new ItemStack(Registry.uranium238Ingot);
    }

    @Override
    public ItemStack getRodType(RodType type) {
        switch (type) {
            case SingleRod:
                return new ItemStack(Registry.singleUranium238Cell).copy();
            case DualRod:
                return new ItemStack(Registry.doubleUranium238Cell).copy();
            case QuadRod:
                return new ItemStack(Registry.quadUranium238Cell).copy();
            case NearDepletedRod:
                return new ItemStack(Registry.nearDepletedUranium238Cell).copy();
            case IsotopicRod:
                return new ItemStack(Registry.isotopicUranium238Cell).copy();
            case ReEnrichedRod:
                return new ItemStack(Registry.reEnrichedUranium238Cell).copy();
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

    public short getTextureID(RodType type) {
        switch (type) {
            case SingleRod:
                return 12;
            case DualRod:
                return 6;
            case ReEnrichedRod:
                return 30;
            case NearDepletedRod:
                return 18;
            case IsotopicRod:
                return 24;
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
        return true;
    }

    @Override
    public Color getReEnrichedColor() {
        return new Color(16777215);
    }

    @Override
    public ItemStack getIngridient() {
        return Ic2Items.airCell;
    }

    @Override
    public int getIngrientPoints() {
        return 25;
    }

    @Override
    public int getIngridientCost() {
        return 25;
    }
}
