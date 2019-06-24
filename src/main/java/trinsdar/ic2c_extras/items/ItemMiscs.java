package trinsdar.ic2c_extras.items;

import ic2.core.item.base.ItemIC2;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Config;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;

import java.util.Arrays;
import java.util.List;

public class ItemMiscs extends ItemIC2 {
    int index;
    String sprite;
    public ItemMiscs(String  name, int id) {
        this.index = id;
        this.sprite = "misc_items";
        setUnlocalizedName(name);
        setCreativeTab(IC2CExtras.creativeTab);
    }

    public ItemMiscs(String  name, int id, String sprite) {
        this.index = id;
        this.sprite = sprite;
        setUnlocalizedName(name);
        setCreativeTab(IC2CExtras.creativeTab);
    }
    @Override
    public LocaleComp getLangComponent(ItemStack stack) {
        if (Ic2cExtrasRecipes.enableEmptyRods && sprite.equals("nuclear_cells")){
            return new LangComponentHolder.LocaleItemComp(this.getUnlocalizedName().replace("Cell", "Rod"));
        }
        return new LangComponentHolder.LocaleItemComp(this.getUnlocalizedName());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta)
    {
        return Ic2Icons.getTextures("ic2c_extras_" + sprite)[index];
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
