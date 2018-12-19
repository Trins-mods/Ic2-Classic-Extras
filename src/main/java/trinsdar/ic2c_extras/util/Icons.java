package trinsdar.ic2c_extras.util;

import ic2.core.platform.textures.Sprites;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static ic2.core.platform.textures.Ic2Icons.*;

public class Icons
{

    @SideOnly(Side.CLIENT)
    public static void loadSprites()
    {
        addSprite(new Sprites.SpriteData("ic2c_extras_blocks", "ic2c_extras:textures/sprites/blocks.png", new Sprites.SpriteInfo(2, 2)));
        addSprite(new Sprites.SpriteData("thermalcentrifuge", "ic2c_extras:textures/sprites/thermalcentrifuge.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("orewashingplant", "ic2c_extras:textures/sprites/orewashingplant.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("metalpress", "ic2c_extras:textures/sprites/metalpress.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("advancedsteamturbine", "ic2c_extras:textures/sprites/advancedsteamturbine.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("blastfurnace", "ic2c_extras:textures/sprites/blastfurnace.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("solidfuelfirebox", "ic2c_extras:textures/sprites/solidfuelfirebox.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("liquidfuelfirebox", "ic2c_extras:textures/sprites/liquidfuelfirebox.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("electricheater", "ic2c_extras:textures/sprites/electricheater.png", new Sprites.SpriteInfo(1, 12)));
        addSprite(new Sprites.SpriteData("ic2c_extras_items", "ic2c_extras:textures/sprites/items.png", new Sprites.SpriteInfo(16, 5)));
        addTextureEntry(new Sprites.TextureEntry("ic2c_extras_blocks", 0, 0, 2, 2));
        addTextureEntry(new Sprites.TextureEntry("thermalcentrifuge", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("orewashingplant", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("metalpress", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("advancedsteamturbine", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("blastfurnace", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("solidfuelfirebox", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("liquidfuelfirebox", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("electricheater", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("ic2c_extras_items", 0, 0, 16, 5));
    }
}
