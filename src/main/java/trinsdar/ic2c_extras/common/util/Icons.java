package trinsdar.ic2c_extras.common.util;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.Sprites;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static ic2.core.platform.textures.Ic2Icons.*;

public class Icons
{

    @SideOnly(Side.CLIENT)
    public static void loadSprites()
    {
        addSprite(new Sprites.SpriteData("ic2c_extras_blocks", "ic2c_extras:textures/sprites/blocks.png", new Sprites.SpriteInfo(16, 16)));
        addSprite(new Sprites.SpriteData("ic2c_extras_items", "ic2c_extras:textures/sprites/items.png", new Sprites.SpriteInfo(16, 16)));
        addTextureEntry(new Sprites.TextureEntry("ic2c_extras_blocks", 0, 0, 1, 12));
        addTextureEntry(new Sprites.TextureEntry("ic2c_extras_items", 0, 0, 7, 2));
        addTextureEntry(new Sprites.TextureEntry("ic2c_extras_items", 0, 2, 11, 3));
        addTextureEntry(new Sprites.TextureEntry("ic2c_extras_items", 0, 3, 9, 4));
        addTextureEntry(new Sprites.TextureEntry("ic2c_extras_items", 0, 4, 9, 5));
    }

//    @SideOnly(Side.CLIENT)
//    public static void drawTexturedRect(int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight)
//    {
//
//        float f = 1F / (float)textureWidth;
//        float f1 = 1F / (float)textureHeight;
//        BufferBuilder buffer = net.minecraft.client.renderer.Tessellator.getInstance()
//                .getBuffer();
//        buffer.begin(org.lwjgl.opengl.GL11.GL_QUADS,
//                net.minecraft.client.renderer.vertex.DefaultVertexFormats.POSITION_TEX);
//        buffer.pos((double)(x), (double)(y + height), 0)
//                .tex((double)((float)(u) * f), (double)((float)(v + height) * f1)).endVertex();
//        buffer.pos((double)(x + width), (double)(y + height), 0)
//                .tex((double)((float)(u + width) * f), (double)((float)(v + height) * f1)).endVertex();
//        buffer.pos((double)(x + width), (double)(y), 0).tex((double)((float)(u + width) * f), (double)((float)(v) * f1))
//                .endVertex();
//        buffer.pos((double)(x), (double)(y), 0).tex((double)((float)(u) * f), (double)((float)(v) * f1)).endVertex();
//        net.minecraft.client.renderer.Tessellator.getInstance().draw();
//    }
//    @SideOnly(Side.CLIENT)
//    public static void drawTexturedRect(int x, int y, int width, int height){
//        drawTexturedRect(x, y, 0, 0, width, height, width, height);
//    }
}
