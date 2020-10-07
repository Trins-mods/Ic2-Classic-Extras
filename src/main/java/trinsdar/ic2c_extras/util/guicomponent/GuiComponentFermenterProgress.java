package trinsdar.ic2c_extras.util.guicomponent;

import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.player.PlayerHandler;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.tileentity.TileEntityFermenter;

import java.util.Arrays;
import java.util.List;

public class GuiComponentFermenterProgress extends GuiComponent {
    TileEntityFermenter block;
    Vec2i texPos;
    boolean fertilizer;

    public GuiComponentFermenterProgress(TileEntityFermenter tile, Box2D box, Vec2i pos, boolean fertilizer) {
        super(box);
        this.block = tile;
        this.texPos = pos;
        this.fertilizer = fertilizer;
    }

    public List<ActionRequest> getNeededRequests() {
        return Arrays.asList(ActionRequest.BackgroundDraw, ActionRequest.ToolTip);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawBackground(GuiIC2 gui, int mouseX, int mouseY, float particalTicks) {
        float progress = this.fertilizer ? this.block.getFertProgress() : this.block.getBioProgress();
        int maxProgress = this.fertilizer ? this.block.getMaxFertProgres() : this.block.getMaxBioProgress();
        if (progress > 0) {
            int x = gui.getXOffset();
            int y = gui.getYOffset();
            float per = progress / maxProgress;
            if (per > 1.0F) {
                per = 1.0F;
            }

            Box2D box = this.getPosition();
            int maxX = box.getLenght();
            int lvl = (int) (per * (float) maxX);
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
            float progress = this.fertilizer ? this.block.getFertProgress() : this.block.getBioProgress();
            int maxProgress = this.fertilizer ? this.block.getMaxFertProgres() : this.block.getMaxBioProgress();
            tooltips.add(Ic2InfoLang.compMachineProgress
                    .getLocalizedFormatted(progress, maxProgress));
        }

    }
}
