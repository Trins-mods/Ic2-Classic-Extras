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

public class Uranium233 extends UranBaseType {
    public Uranium233(){
        this.addArray(-1, -1);
        this.addArray(0, -1);
        this.addArray(1, -1);
        this.addArray(-1, 0);
        this.addArray(1, 0);
        this.addArray(-1, 1);
        this.addArray(0, 1);
        this.addArray(1, 1);
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
        return 12000;
    }

    @Override
    public float getEUPerPulse() {
        return 0.5f;
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
        return 0.8f;
    }

    @Override
    public float getExplosionEffectModifier() {
        return 2.0f;
    }

    @Override
    public ItemStack getUraniumIngot() {
        return new ItemStack(Registry.uranium233Ingot);
    }

    @Override
    public ItemStack getRodType(RodType type) {
        switch (type) {
            case SingleRod:
                return new ItemStack(Registry.singleUranium233Cell).copy();
            case DualRod:
                return new ItemStack(Registry.doubleUranium233Cell).copy();
            case QuadRod:
                return new ItemStack(Registry.quadUranium233Cell).copy();
            case NearDepletedRod:
                return new ItemStack(Registry.nearDepletedUranium233Cell).copy();
            case IsotopicRod:
                return new ItemStack(Registry.isotopicUranium233Cell).copy();
            case ReEnrichedRod:
                return new ItemStack(Registry.reEnrichedUranium233Cell).copy();
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
                return 1124;
            case DualRod:
                return 1125;
            case QuadRod:
                return 1126;
            case ReEnrichedRod:
                return 1127;
            case NearDepletedRod:
                return 1128;
            case IsotopicRod:
                return 1129;
            default:
                return 0;
        }
    }

    public short getTextureID(RodType type) {
        switch (type) {
            case SingleRod:
                return 16;
            case DualRod:
                return 10;
            case QuadRod:
                return 4;
            case ReEnrichedRod:
                return 34;
            case NearDepletedRod:
                return 22;
            case IsotopicRod:
                return 28;
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
