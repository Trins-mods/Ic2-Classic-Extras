package trinsdar.ic2c_extras.util;

import ic2.core.platform.textures.Sprites;
import ic2.core.platform.textures.Sprites.SpriteData;
import ic2.core.platform.textures.Sprites.SpriteInfo;
import ic2.core.platform.textures.Sprites.TextureEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static ic2.core.platform.textures.Ic2Icons.*;

public class Icons
{

    @SideOnly(Side.CLIENT)
    public static void loadSprites()
    {
        addSprite(new SpriteData("ic2c_extras_blocks", "ic2c_extras:textures/sprites/blocks.png", new SpriteInfo(2, 2)));
        addSprite(new SpriteData("thermalcentrifuge", "ic2c_extras:textures/sprites/thermalcentrifuge.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("orewashingplant", "ic2c_extras:textures/sprites/orewashingplant.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("roller", "ic2c_extras:textures/sprites/roller.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("extruder", "ic2c_extras:textures/sprites/extruder.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("cutter", "ic2c_extras:textures/sprites/cutter.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("impellerizedroller", "ic2c_extras:textures/sprites/impellerizedroller.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("liquescentextruder", "ic2c_extras:textures/sprites/liquescentextruder.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("plasmacutter", "ic2c_extras:textures/sprites/plasmacutter.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("advancedsteamturbine", "ic2c_extras:textures/sprites/advancedsteamturbine.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("blastfurnace", "ic2c_extras:textures/sprites/blastfurnace.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("solidfuelfirebox", "ic2c_extras:textures/sprites/solidfuelfirebox.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("liquidfuelfirebox", "ic2c_extras:textures/sprites/liquidfuelfirebox.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("electricheater", "ic2c_extras:textures/sprites/electricheater.png", new SpriteInfo(1, 12)));
        addSprite(new SpriteData("ic2c_extras_items", "ic2c_extras:textures/sprites/items.png", new SpriteInfo(16, 5)));
        addTextureEntry(new TextureEntry("ic2c_extras_blocks", 0, 0, 2, 2));
        addTextureEntry(new TextureEntry("thermalcentrifuge", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("orewashingplant", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("roller", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("extruder", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("cutter", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("impellerizedroller", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("liquescentextruder", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("plasmacutter", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("advancedsteamturbine", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("blastfurnace", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("solidfuelfirebox", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("liquidfuelfirebox", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("electricheater", 0, 0, 1, 12));
        addTextureEntry(new TextureEntry("ic2c_extras_items", 0, 0, 16, 5));
    }
}
