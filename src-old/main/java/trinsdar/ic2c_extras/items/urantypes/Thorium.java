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

import java.awt.*;

public class Thorium extends UranBaseType {
    public Thorium() {
        this.loadDefaults();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(RodType type) {
        return Ic2Icons.getTextures("ic2c_extras_nuclear_cells")[this.getTextureID(type)];
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getMaxDurability() {
        return 32000;
    }

    @Override
    public float getEUPerPulse() {
        return 0.6f;
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
        return 0.25f;
    }

    @Override
    public float getExplosionEffectModifier() {
        return 0.5f;
    }

    @Override
    public ItemStack getUraniumIngot() {
        return new ItemStack(Registry.thoriumIngot);
    }

    @Override
    public ItemStack getRodType(RodType type) {
        switch (type) {
            case SingleRod:
                return new ItemStack(Registry.singleThorium232Cell).copy();
            case DualRod:
                return new ItemStack(Registry.doubleThorium232Cell).copy();
            case QuadRod:
                return new ItemStack(Registry.quadThorium232Cell).copy();
            case NearDepletedRod:
                return new ItemStack(Registry.nearDepletedThorium232Cell).copy();
            case IsotopicRod:
                return new ItemStack(Registry.isotopicThorium232Cell).copy();
            case ReEnrichedRod:
                return new ItemStack(Registry.reEnrichedThorium232Cell).copy();
            default:
                return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack getNewIsotopicRod() {
        return StackUtil.copyWithDamage(this.getRodType(RodType.IsotopicRod), this.getMaxDurability() - 1);
    }

    @Override
    public short getRodID(RodType type) {
        switch (type) {
            case SingleRod:
                return 1118;
            case DualRod:
                return 1119;
            case QuadRod:
                return 1120;
            case ReEnrichedRod:
                return 1121;
            case NearDepletedRod:
                return 1122;
            case IsotopicRod:
                return 1123;
            default:
                return 0;
        }
    }

    public short getTextureID(RodType type) {
        switch (type) {
            case SingleRod:
                return 15;
            case DualRod:
                return 9;
            case QuadRod:
                return 3;
            case ReEnrichedRod:
                return 33;
            case NearDepletedRod:
                return 21;
            case IsotopicRod:
                return 27;
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
