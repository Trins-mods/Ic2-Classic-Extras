package trinsdar.ic2c_extras.util.guicomponent;

import ic2.core.fluid.LayeredFluidTank;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.base.FluidTankComp;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiComponentTank extends FluidTankComp {
    IFluidTank tank;
    Vec2i pos;
    public GuiComponentTank(Box2D box, IFluidTank fluid, Vec2i vec, Box2D secBox) {
        super(box, fluid, vec, secBox);
        this.pos = vec;
        this.tank = fluid;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
        Box2D box = this.getPosition();
        if (this.tank instanceof LayeredFluidTank) {
            gui.displayMultiGaugge(gui.getXOffset(), gui.getYOffset(), this.pos.getX(), this.pos.getY(), box.getX(), box.getY(), gui.getTextureObject(), (LayeredFluidTank)this.tank);
        } else {
            displayGauge(gui, gui.getXOffset(), gui.getYOffset(), this.pos.getX(), this.pos.getY(), box.getX(), box.getY(), gui.getTextureObject(), this.tank.getFluid());
        }

    }

    public void displayGauge(GuiIC2 gui, int guiX, int guiY, int u, int v, int x, int y, ITextureObject texture, FluidStack liquid) {
        int w = this.getPosition().getLenght();
        int h = this.getPosition().getHeight();
        if (liquid != null) {
            TextureMap map = gui.mc.getTextureMapBlocks();
            TextureAtlasSprite liquidIcon = null;
            Fluid fluid = liquid.getFluid();
            if (fluid != null && fluid.getStill() != null) {
                liquidIcon = map.getAtlasSprite(fluid.getStill().toString());
            }

            gui.mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            int color = fluid.getColor(liquid);
            float scale = Math.min(liquid.amount, tank.getCapacity()) / (float) tank.getCapacity();
            GlStateManager.color((float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color >> 0 & 255) / 255.0F, (float)(color >> 24 & 255) / 255.0F);
            for (int col = 0; col < w / 16; col++) {
                for (int row = 0; row <= h / 16; row++) {
                    gui.drawTexturedModalRect(guiX + x + col * 16, guiY + y + row * 16 - 1, liquidIcon, 16, 16);
                }
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.bindTexture(texture.getGlTextureId());
            int mask = (int) Math.floor(h * scale);
            if (mask == 0 && liquid.amount > 0)
                mask = 1;
            gui.drawTexturedModalRect(guiX + x, guiY + y - 1, x, y - 1, w, h - mask + 1);
            gui.drawTexturedModalRect(guiX + x, guiY + y, u, v, w, h);
        }
    }
}
