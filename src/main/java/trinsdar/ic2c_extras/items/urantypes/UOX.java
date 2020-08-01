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

import java.awt.*;

import static ic2.core.item.reactor.uranTypes.IUranium.RodType.DualRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.IsotopicRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.NearDepletedRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.QuadRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.ReEnrichedRod;
import static ic2.core.item.reactor.uranTypes.IUranium.RodType.SingleRod;

public class UOX extends UranBaseType {
    public UOX() {
        this.loadDefaults();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(RodType type) {
        return Ic2Icons.getTextures("ic2c_extras_nuclear_cells")[this.getRodID(type) - 1100];
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
        return new ItemStack(Registry.oxidizedUraniumIngot);
    }

    @Override
    public ItemStack getRodType(RodType type) {
        switch (type) {
            case SingleRod:
                return new ItemStack(Registry.singleUOXCell).copy();
            case DualRod:
                return new ItemStack(Registry.doubleUOXCell).copy();
            case QuadRod:
                return new ItemStack(Registry.quadUOXCell).copy();
            case NearDepletedRod:
                return new ItemStack(Registry.nearDepletedUOXCell).copy();
            case IsotopicRod:
                return new ItemStack(Registry.isotopicUOXCell).copy();
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
        switch (type) {
            case SingleRod:
                return 1112;
            case DualRod:
                return 1106;
            case QuadRod:
                return 1100;
            case ReEnrichedRod:
                return 1130;
            case NearDepletedRod:
                return 1118;
            case IsotopicRod:
                return 1124;
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
