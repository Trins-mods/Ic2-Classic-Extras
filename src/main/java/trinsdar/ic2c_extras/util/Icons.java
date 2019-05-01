package trinsdar.ic2c_extras.util;

import ic2.core.platform.textures.Sprites;
import ic2.core.platform.textures.Sprites.SpriteData;
import ic2.core.platform.textures.Sprites.SpriteInfo;
import ic2.core.platform.textures.Sprites.TextureEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;

import static ic2.core.platform.textures.Ic2Icons.*;

public class Icons
{

    @SideOnly(Side.CLIENT)
    public static void loadSprites()
    {
        makeSprites("ic2c_extras_blocks", "blocks", 2, 2);
        makeSprites("thermalcentrifuge", "thermalcentrifuge", 1, 12);
        makeSprites("thermalwasher", "thermalwasher", 1, 12);
        makeSprites("orewashingplant", "orewashingplant", 1, 12);
        makeSprites("roller", "roller", 1, 12);
        makeSprites("extruder", "extruder", 1, 12);
        makeSprites("cutter", "cutter", 1, 12);
        makeSprites("metalbender", "metalbender", 1, 12);
        makeSprites("impellerizedroller", "impellerizedroller", 1, 12);
        makeSprites("liquescentextruder", "liquescentextruder", 1, 12);
        makeSprites("plasmacutter", "plasmacutter", 1, 12);
        makeSprites("advancedsteamturbine", "advancedsteamturbine", 1, 12);
        makeSprites("blastfurnace", "blastfurnace", 1, 12);
        makeSprites("solidfuelfirebox", "solidfuelfirebox", 1, 12);
        makeSprites("liquidfuelfirebox", "liquidfuelfirebox", 1, 12);
        makeSprites("electricheater", "electricheater", 1, 12);
        makeSprites("ic2c_extras_misc_items", "misc_items", 16, 2);
        makeSprites("ic2c_extras_crushed_ore", "crushed_ore", 7, 1);
        makeSprites("ic2c_extras_purified_crushed_ore", "purified_crushed_ore", 7, 1);
        makeSprites("ic2c_extras_item_casings", "item_casings", 16, 1);
        makeSprites("ic2c_extras_materials", "materials", 5, 1);
        makeSprites("ic2c_extras_presses", "presses", 16, 2);
        makeSprites("ic2c_extras_small_dust", "small_dust", 16, 1);
        makeSprites("ic2c_extras_tiny_dust", "tiny_dust", 16, 1);
        makeSprites("ic2c_extras_tools", "tools", 6, 1);
        addCustomTexture("metalbender", 0, 9, new ResourceLocation(IC2CExtras.MODID,"animations/metalbender_front"));
    }

    public static void makeSprites(String id, String fileName, int maxX, int maxY){
        addSprite(new SpriteData(id, "ic2c_extras:textures/sprites/" + fileName + ".png", new SpriteInfo(maxX, maxY)));
        addTextureEntry(new TextureEntry(id, 0, 0, maxX, maxY));
    }
}
