package trinsdar.ic2c_extras.items;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.Arrays;
import java.util.List;

public class ItemPurifiedCrushedOre extends Item implements IStaticTexturedItem {
    int index;

    public ItemPurifiedCrushedOre(String variant, int index) {
        this.index = index;
        String name = variant + "PurifiedCrushedOre";
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase());
        setUnlocalizedName(name);
        setCreativeTab(IC2CExtras.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("ic2c_extras_purified_crushed_ore")[index];
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }
}
