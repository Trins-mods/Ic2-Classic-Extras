package trinsdar.ic2c_extras.block;

import ic2.core.block.base.IC2Block;
import ic2.core.item.base.IC2BlockItem;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.platform.rendering.features.block.IBlockModel;
import ic2.core.utils.helpers.Tool;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.ic2c_extras.IC2CExtras;

public class BlockResource extends IC2Block implements IBlockModel {
    public BlockResource(String blockName, int harvestLevel, Properties properties) {
        super(blockName, properties);
        this.id = new ResourceLocation(IC2CExtras.MODID, blockName);
        this.setHarvestTool(Tool.PICKAXE.withLevel(harvestLevel));
        IC2Blocks.registerBlock(this);
    }

    @Override
    public BlockItem createItem() {
        return new IC2BlockItem(this, new Item.Properties().tab(IC2CExtras.CREATIVE_TAB));
    }

    @Override
    public TextureAtlasSprite getSpriteForState(BlockState blockState, Direction direction) {
        return IC2Textures.getMappedEntriesBlock(IC2CExtras.MODID,"resources").get(this.id.getPath());
    }
}
