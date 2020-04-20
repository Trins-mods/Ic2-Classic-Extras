package trinsdar.ic2c_extras.items;

import ic2.core.item.base.ItemGrandualInt;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.Arrays;
import java.util.List;

public class ItemRTG extends ItemGrandualInt {
    int maxDamage;
    int id;

    public ItemRTG(String name, int maxDamage, int id) {
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase());
        setUnlocalizedName(name);
        this.maxDamage = maxDamage;
        this.id = id;
        this.setCreativeTab(IC2CExtras.creativeTab);
    }

    @Override
    public int getMaxCustomDamage(ItemStack stack) {
        return maxDamage;
    }

    @Override
    public int getTextureEntry(int i) {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("ic2c_extras_rtg")[id];
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }
}
