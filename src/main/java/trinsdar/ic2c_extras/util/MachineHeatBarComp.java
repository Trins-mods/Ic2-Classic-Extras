package trinsdar.ic2c_extras.util;

import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.player.PlayerHandler;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;

import java.util.Arrays;
import java.util.List;

public class MachineHeatBarComp extends GuiComponent {
    TileEntityThermalCentrifuge block;
    Vec2i texPos;

    public MachineHeatBarComp(TileEntityThermalCentrifuge tile, Box2D box, Vec2i pos) {
        super(box);
        this.block = tile;
        this.texPos = pos;
    }

    public List<ActionRequest> getNeededRequests() {
        return Arrays.asList(ActionRequest.BackgroundDraw, ActionRequest.ToolTip);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
        float heat = this.block.getHeat();
        if (heat > 0) {
            int x = gui.getXOffset();
            int y = gui.getYOffset();
            float per = heat / this.block.getMaxHeat();
            if (per > 1.0F) {
                per = 1.0F;
            }

            Box2D box = this.getPosition();
            int maxX = box.getLenght();
            int lvl = (int) (per * (float)maxX);
            if (lvl <= 0) {
                return;
            }

            gui.drawTexturedModalRect(x + box.getX(), y + box.getY(), this.texPos.getX(), this.texPos.getY(), lvl,
                    box.getHeight());
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onToolTipCollecting(GuiIC2 gui, int mouseX, int mouseY, List<String> tooltips) {
        if (this.isMouseOver(mouseX, mouseY) && PlayerHandler.getClientPlayerHandler().hasEUReader()) {
            tooltips.add(Ic2cExtrasLang.heat
                    .getLocalizedFormatted(new Object[] { this.block.getHeat(), this.block.getMaxHeat() }));
        }

    }
}
