package trinsdar.ic2c_extras.util.guicomponent;

import ic2.core.block.generator.tile.TileEntityNuclearReactorElectric;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

public class GuiComponentNuclearReactorEuPerTick extends GuiComponent {
    TileEntityNuclearReactorElectric reactor;
    public GuiComponentNuclearReactorEuPerTick(TileEntityNuclearReactorElectric tile) {
        super(new Box2D(108, 136, 97, 13));
        this.reactor = tile;
    }

    @Override
    public List<ActionRequest> getNeededRequests() {
        return Collections.singletonList(ActionRequest.FrontgroundDraw);
    }

    @Override
    public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
        gui.drawString(Ic2cExtrasLang.REACTOR_EU.getLocalizedFormatted(reactor.getReactorEUEnergyOutput()), this.getPosition().getX(), this.getPosition().getY(), Color.cyan.hashCode());
    }
}
