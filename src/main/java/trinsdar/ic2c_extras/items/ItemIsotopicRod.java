package trinsdar.ic2c_extras.items;

import ic2.core.item.reactor.base.ItemDepletedUraniumRodBase;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.Arrays;
import java.util.List;

public class ItemIsotopicRod extends ItemDepletedUraniumRodBase {
    ItemNuclearRod.NuclearRodVariants variant;

    public ItemIsotopicRod(ItemNuclearRod.NuclearRodVariants variant) {
        this.variant = variant;
        String name = "isotopic" + variant.getPrefix();
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase() + "cell");
        this.setCreativeTab(IC2CExtras.creativeTab);
        setUnlocalizedName(new LangComponentHolder.LocaleItemComp("item." + name + "Cell"));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta) {
        return getUranium(new ItemStack(this)).getTexture(IUranium.RodType.IsotopicRod);
    }

    @Override
    public LocaleComp getLangComponent(ItemStack stack) {
        return this.name;
    }

    @Override
    public IUranium getUranium(ItemStack itemStack) {
        return ItemNuclearRod.getUran(variant);
    }

    @Override
    public ItemStack[] getSubParts() {
        return new ItemStack[0];
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(this.getUranium(new ItemStack(this)).getNewIsotopicRod());
        }
    }

    @Override
    public boolean hasSubParts() {
        return false;
    }

    @Override
    public ItemStack getReactorPart() {
        return getUranium(new ItemStack(this)).getNewIsotopicRod();
    }

    @Override
    public int getMaxCustomDamage(ItemStack stack) {
        return this.getUranium(stack).getMaxDurability();
    }

    @Override
    public int getTextureEntry(int i) {
        return 0;
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }
}
