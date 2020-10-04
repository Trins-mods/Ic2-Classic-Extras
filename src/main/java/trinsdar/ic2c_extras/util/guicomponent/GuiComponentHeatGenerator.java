package trinsdar.ic2c_extras.util.guicomponent;

import ic2.api.energy.tile.IHeatSource;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

public class GuiComponentHeatGenerator extends GuiComponent {
    IHeatSource heatSource;
    public GuiComponentHeatGenerator(Box2D box, IHeatSource heatSource) {
        super(box);
        this.heatSource = heatSource;
    }

    @Override
    public List<ActionRequest> getNeededRequests() {
        return Collections.singletonList(ActionRequest.FrontgroundDraw);
    }

    @Override
    public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
        gui.drawString(heatSource.drawHeat(null, heatSource.getConnectionBandwidth(null), true) + " hU / " + heatSource.getConnectionBandwidth(null) + " hU Max", this.getPosition().getX() + 3, this.getPosition().getY() + 3, Color.cyan.hashCode());
    }
}
