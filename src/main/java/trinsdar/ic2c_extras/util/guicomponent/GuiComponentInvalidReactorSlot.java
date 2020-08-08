package trinsdar.ic2c_extras.util.guicomponent;

import ic2.core.block.base.tile.TileEntityNuclearReactorBase;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.util.math.Box2D;

import java.util.Collections;
import java.util.List;

public class GuiComponentInvalidReactorSlot extends GuiComponent {
    TileEntityNuclearReactorBase reactor;
    public GuiComponentInvalidReactorSlot(TileEntityNuclearReactorBase tile, int x, int y) {
        super(new Box2D(x, y, 16, 16));
        this.reactor = tile;
    }

    @Override
    public List<ActionRequest> getNeededRequests() {
        return Collections.singletonList(ActionRequest.BackgroundDraw);
    }

    @Override
    public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
        int x = gui.getXOffset();
        int y = gui.getYOffset();
        Box2D box = this.getPosition();
        gui.drawTexturedModalRect(x + box.getX(), y + box.getY(), 213, 1, box.getLenght(),
                box.getHeight());
    }
}
