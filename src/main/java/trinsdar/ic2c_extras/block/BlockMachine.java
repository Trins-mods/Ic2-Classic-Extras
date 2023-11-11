package trinsdar.ic2c_extras.block;

import ic2.core.block.base.drops.IBlockDropProvider;
import ic2.core.block.base.tiles.BaseTileEntity;
import ic2.core.block.machines.BaseMachineBlock;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.rendering.IC2Textures;
import ic2.core.platform.rendering.features.ITextureProvider;
import ic2.core.platform.rendering.features.providers.ToggleProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.block.entity.BlockEntityType;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.Map;

public class BlockMachine extends BaseMachineBlock {
    public BlockMachine(String blockName, IBlockDropProvider drop, BlockEntityType<? extends BaseTileEntity> type) {
        super(blockName, drop, new ToggleProvider(IC2CExtras.MODID, blockName), type);
        IC2Blocks.registerBlock(this);
    }
}
