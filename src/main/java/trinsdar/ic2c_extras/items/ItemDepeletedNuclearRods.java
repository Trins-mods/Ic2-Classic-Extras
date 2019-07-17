package trinsdar.ic2c_extras.items;

import ic2.core.item.base.ItemIC2;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;

import java.util.Arrays;
import java.util.List;

public class ItemDepeletedNuclearRods extends ItemIC2 {
    int index;
    public ItemDepeletedNuclearRods(String name, LocaleComp comp, int id) {
        this.index = id;
        this.setRegistryName(IC2CExtras.MODID, name);
        setCreativeTab(IC2CExtras.creativeTab);
        setUnlocalizedName(comp);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta)
    {
        return Ic2Icons.getTextures("ic2c_extras_nuclear_cells")[index];
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
