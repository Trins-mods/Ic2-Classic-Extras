package trinsdar.ic2c_extras.common.blocks;

import ic2.api.crops.CropProperties;
import ic2.api.crops.ICropTile;
import ic2.core.block.crop.crops.CropCardBase;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CropGalena extends CropCardBase {

    public CropGalena(CropProperties properties) {
        super(new CropProperties(8, 2, 0, 0, 2, 0));
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int status) {
        return status == 4 ? this.getSprite("bc")[85] : this.getSprite("bc")[31 + status];
    }


    public String getId() {
        return "Galena";
    }


    public int getMaxSize() {
        return 4;
    }

    public ItemStack getGain(ICropTile crop) {
        return Ic2Items.silverDust.copy();
    }

    public double dropGainChance() {
        return super.dropGainChance() / 2.0D;
    }

    public String[] getAttributes() {
        return new String[]{"Lead", "Leaves", "Metal"};
    }

    public int getGrowthDuration(ICropTile cropTile) {
        return cropTile.getCurrentSize() == 3 ? 2200 : 1000;
    }

    public boolean canGrow(ICropTile cropTile) {
        return cropTile.getCurrentSize() < 3 || cropTile.getCurrentSize() == 3 && (cropTile.isBlockBelow("oreLead") || cropTile.isBlockBelow("blockLead"));
    }

    public int getOptimalHarvestSize(ICropTile cropTile) {
        return 4;
    }

    public boolean canBeHarvested(ICropTile cropTile) {
        return cropTile.getCurrentSize() == 4;
    }

    public int getSizeAfterHarvest(ICropTile cropTile) {
        return 2;
    }

}
