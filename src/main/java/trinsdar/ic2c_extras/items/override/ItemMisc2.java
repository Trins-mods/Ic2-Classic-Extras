package trinsdar.ic2c_extras.items.override;

import ic2.core.item.misc.ItemMisc;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

public class ItemMisc2 extends ItemMisc {
    @Override
    public void onLoad() {
        super.onLoad();
        this.unlocalizedNames.put(180, Ic2cExtrasLang.URANIUM_FUEL);
    }

    @Override
    public TextureAtlasSprite getTexture(int meta) {
        if (meta == 259){
            return Ic2Icons.getTextures("ic2c_extras_plates")[11];
        }
        return super.getTexture(meta);
    }
}
