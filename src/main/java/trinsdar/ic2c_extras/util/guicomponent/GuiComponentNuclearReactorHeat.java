package trinsdar.ic2c_extras.util.guicomponent;

import ic2.api.reactor.IReactor;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.player.PlayerHandler;
import ic2.core.util.math.Box2D;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.util.Arrays;
import java.util.List;

public class GuiComponentNuclearReactorHeat extends GuiComponent {
    IReactor reactor;
    public GuiComponentNuclearReactorHeat(IReactor tile, Box2D box) {
        super(box);
        this.reactor = tile;
    }

    @Override
    public List<ActionRequest> getNeededRequests() {
        return Arrays.asList(ActionRequest.BackgroundDraw, ActionRequest.ToolTip);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
        float heat = this.reactor.getHeat();
        if (heat > 0) {
            int x = gui.getXOffset();
            int y = gui.getYOffset();
            float per = heat / this.reactor.getMaxHeat();
            if (per > 1.0F) {
                per = 1.0F;
            }

            Box2D box = this.getPosition();
            int maxX = box.getLenght();
            int lvl = (int) (per * (float) maxX);
            if (lvl <= 0) {
                return;
            }

            gui.drawTexturedModalRect(x + box.getX(), y + box.getY(), 0, 243, lvl,
                    box.getHeight());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
        if (this.isMouseOver(mouseX, mouseY)) {
            if (PlayerHandler.getClientPlayerHandler().hasThermometer()){
                float percent = ((float)this.reactor.getHeat() / (float)this.reactor.getMaxHeat()) * 100f;
                float heat = (Math.round(percent * 100) / 100.0f);
                tooltips.add(Ic2cExtrasLang.REACTOR_HEAT
                        .getLocalizedFormatted(heat + " %"));
            } else {
                tooltips.add(Ic2cExtrasLang.REACTOR_THERMOMETER.getLocalized());
            }

        }

    }
}
