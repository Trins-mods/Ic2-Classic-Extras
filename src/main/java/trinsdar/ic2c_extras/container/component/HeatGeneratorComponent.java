package trinsdar.ic2c_extras.container.component;

import com.mojang.blaze3d.vertex.PoseStack;
import ic2.core.inventory.gui.components.GuiWidget;
import ic2.core.utils.math.geometry.Box2i;
import net.minecraft.network.chat.Component;
import tesseract.api.heat.IHeatHandler;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class HeatGeneratorComponent extends GuiWidget {
    IHeatHandler heatHandler;
    public HeatGeneratorComponent(Box2i box, IHeatHandler heatHandler) {
        super(box);
        this.heatHandler = heatHandler;
    }

    @Override
    protected void addRequests(Set<ActionRequest> set) {
        set.add(ActionRequest.DRAW_FOREGROUND);
    }

    @Override
    public void drawForeground(PoseStack matrix, int mouseX, int mouseY) {
        gui.drawString(matrix, Component.literal(heatHandler.getHeat() + " hU / " + heatHandler.getHeatCap() + " hU Max"), this.getBox().getX() + 3, this.getBox().getY() + 3, Color.cyan.hashCode());
    }
}
