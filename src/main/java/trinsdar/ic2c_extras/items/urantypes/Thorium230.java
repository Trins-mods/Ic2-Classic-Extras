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

public class Thorium230 extends UranBaseType {
    public Thorium230(){
        this.addArray(new int[]{-1, -1});
        this.addArray(new int[]{0, -1});
        this.addArray(new int[]{1, -1});
        this.addArray(new int[]{-1, 0});
        this.addArray(new int[]{1, 0});
        this.addArray(new int[]{-1, 1});
        this.addArray(new int[]{0, 1});
        this.addArray(new int[]{1, 1});
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
        return 20000;
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
        return 0.7f;
    }

    @Override
    public float getExplosionEffectModifier() {
        return 2.0f;
    }

    @Override
    public ItemStack getUraniumIngot() {
        return new ItemStack(Registry.thorium230Ingot);
    }

    @Override
    public ItemStack getRodType(RodType type) {
        switch (type) {
            case SingleRod:
                return new ItemStack(Registry.singleThorium230Cell).copy();
            case DualRod:
                return new ItemStack(Registry.doubleThorium230Cell).copy();
            case QuadRod:
                return new ItemStack(Registry.quadThorium230Cell).copy();
            case NearDepletedRod:
                return new ItemStack(Registry.nearDepletedThorium230Cell).copy();
            case IsotopicRod:
                return new ItemStack(Registry.isotopicThorium230Cell).copy();
            case ReEnrichedRod:
                return new ItemStack(Registry.reEnrichedThorium230Cell).copy();
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
                return 1116;
            case DualRod:
                return 1110;
            case QuadRod:
                return 1104;
            case ReEnrichedRod:
                return 1134;
            case NearDepletedRod:
                return 1122;
            case IsotopicRod:
                return 1128;
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
