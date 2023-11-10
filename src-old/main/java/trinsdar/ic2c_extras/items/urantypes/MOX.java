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


public class MOX extends UranBaseType {
    public MOX(){
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
        return 5000;
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
        return 1.0f;
    }

    @Override
    public float getExplosionEffectModifier() {
        return 2.0f;
    }

    @Override
    public ItemStack getUraniumIngot() {
        return new ItemStack(Registry.moxFuel);
    }

    @Override
    public ItemStack getRodType(RodType type) {
        switch (type) {
            case SingleRod:
                return new ItemStack(Registry.singleMOXCell).copy();
            case DualRod:
                return new ItemStack(Registry.doubleMOXCell).copy();
            case QuadRod:
                return new ItemStack(Registry.quadMOXCell).copy();
            case NearDepletedRod:
                return new ItemStack(Registry.nearDepletedMOXCell).copy();
            case IsotopicRod:
                return new ItemStack(Registry.isotopicMOXCell).copy();
            case ReEnrichedRod:
                return new ItemStack(Registry.reEnrichedMOXCell).copy();
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
                return 1112;
            case DualRod:
                return 1113;
            case QuadRod:
                return 1114;
            case ReEnrichedRod:
                return 1115;
            case NearDepletedRod:
                return 1116;
            case IsotopicRod:
                return 1117;
            default:
                return 0;
        }
    }

    public short getTextureID(RodType type) {
        switch (type) {
            case SingleRod:
                return 14;
            case DualRod:
                return 8;
            case QuadRod:
                return 2;
            case ReEnrichedRod:
                return 32;
            case NearDepletedRod:
                return 20;
            case IsotopicRod:
                return 26;
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
