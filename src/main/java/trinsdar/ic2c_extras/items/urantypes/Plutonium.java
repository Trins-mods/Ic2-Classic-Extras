package trinsdar.ic2c_extras.items.urantypes;

import ic2.core.item.reactor.uranTypes.UranBaseType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.Registry;

import java.awt.*;

public class Plutonium extends UranBaseType {
    public Plutonium(){
        this.loadDefaults();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(RodType type) {
        return Ic2Icons.getTextures("ic2c_extras_nuclear_cells")[this.getRodID(type) - 1100];
    }
    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getMaxDurability() {
        return 10000;
    }

    @Override
    public float getEUPerPulse() {
        return 1.0f;
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
        return 4.0f;
    }

    @Override
    public float getExplosionEffectModifier() {
        return 6.0f;
    }

    @Override
    public ItemStack getUraniumIngot() {
        return null;
    }

    @Override
    public ItemStack getRodType(RodType type) {
        switch(type) {
            case SingleRod:
                return new ItemStack(Registry.singlePlutoniumCell).copy();
            case DualRod:
                return new ItemStack(Registry.doublePlutoniumCell).copy();
            case QuadRod:
                return new ItemStack(Registry.quadPlutoniumCell).copy();
            case NearDepletedRod:
                return new ItemStack(Registry.nearDepletedPlutoniumCell).copy();
            case IsotopicRod:
                return new ItemStack(Registry.isotopicPlutoniumCell).copy();
            case ReEnrichedRod:
                return new ItemStack(Registry.reEnrichedPlutoniumCell).copy();
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
        switch(type) {
            case SingleRod:
                return 1101;
            case DualRod:
                return 1106;
            case QuadRod:
                return 1111;
            case ReEnrichedRod:
                return 1126;
            case NearDepletedRod:
                return 1116;
            case IsotopicRod:
                return 1121;
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
