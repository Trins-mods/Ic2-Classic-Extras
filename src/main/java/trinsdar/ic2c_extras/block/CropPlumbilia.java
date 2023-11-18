package trinsdar.ic2c_extras.block;

import ic2.api.crops.CropProperties;
import ic2.api.crops.ICropTile;
import ic2.core.block.crops.crops.OreCrop;
import ic2.core.platform.rendering.IC2Textures;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.IC2CExtrasConfig;
import trinsdar.ic2c_extras.init.IC2CExtrasTags;
import trinsdar.ic2c_extras.init.ModBlocks;
import trinsdar.ic2c_extras.init.ModItems;

import java.util.List;

public class CropPlumbilia extends OreCrop {
    public CropPlumbilia() {
        super("plumbilia", new CropProperties(8, 2, 0, 0, 2, 0), List.of(IC2CExtrasTags.getForgeBlockTag("ores/lead"), IC2CExtrasTags.getForgeBlockTag("storage_blocks/lead")), ModItems.LEAD_DUST, Component.literal("Trinsdar"), "Purple");
    }

    public TextureAtlasSprite getTexture(int stage) {
        String stageTexture = "";
        switch (stage) {
            case 0:
            case 1:
            case 2:
                stageTexture = "growing_" + stage;
                break;
            case 3:
                stageTexture = "growing_plumbilia_" + stage;
        }

        return IC2Textures.getMappedEntriesBlock(IC2CExtras.MODID,"resources").get(stageTexture);
    }

    @Override
    public int getGrowthDuration(ICropTile cropTile) {
        return cropTile.getGrowthStage() == 3 ? 2200 : 1000;
    }

    @Override
    public ItemStack[] getDrops(ICropTile cropTile) {
        String itemId = IC2CExtrasConfig.LEAD_CROP_DROP.get();
        if (ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemId))){
            return new ItemStack[]{new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId)))};
        }
        return super.getDrops(cropTile);
    }
}
