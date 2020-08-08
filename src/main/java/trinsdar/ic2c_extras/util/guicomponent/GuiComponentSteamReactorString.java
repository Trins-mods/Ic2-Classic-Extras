package trinsdar.ic2c_extras.util.guicomponent;

import ic2.core.block.base.tile.TileEntityNuclearReactorBase;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

public class GuiComponentSteamReactorString extends GuiComponent {
    TileEntityNuclearReactorBase reactor;
    public GuiComponentSteamReactorString(TileEntityNuclearReactorBase tile) {
        super(new Box2D(108, 136, 97, 13));
        this.reactor = tile;
    }

    @Override
    public List<ActionRequest> getNeededRequests() {
        return Collections.singletonList(ActionRequest.FrontgroundDraw);
    }

    @Override
    public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
        gui.drawString(Ic2cExtrasLang.REACTOR_STEAM.getLocalizedFormatted(reactor.getReactorEUEnergyOutput()), this.getPosition().getX() + 3, this.getPosition().getY() + 3, Color.cyan.hashCode());
    }
}
