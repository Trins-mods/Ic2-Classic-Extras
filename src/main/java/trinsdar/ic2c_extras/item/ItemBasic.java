package trinsdar.ic2c_extras.item;

import ic2.core.item.base.IC2Item;
import ic2.core.item.base.PropertiesBuilder;
import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.platform.rendering.features.item.ISimpleItemModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;
import trinsdar.ic2c_extras.IC2CExtras;

public class ItemBasic extends IC2Item implements ISimpleItemModel {
    String subfolder;
    public ItemBasic(String id, String subfolder) {
        this(id, subfolder, new PropertiesBuilder());
    }

    public ItemBasic(String id, String subfolder, PropertiesBuilder properties) {
        super(id, properties.group(IC2CExtras.CREATIVE_TAB));
        this.subfolder = subfolder;
        IC2Items.registerItem(this);
    }

    @Override
    public TextureAtlasSprite getTexture() {
        return IC2Textures.getMappedEntriesItem(IC2CExtras.MODID, subfolder).get(id.getPath());
    }

    @Override
    public ResourceLocation getRegistryName() {
        return id;
    }
}
