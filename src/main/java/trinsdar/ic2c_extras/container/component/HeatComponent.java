package trinsdar.ic2c_extras.container.component;

import com.mojang.blaze3d.vertex.PoseStack;
import ic2.core.inventory.gui.components.GuiWidget;
import ic2.core.utils.helpers.Formatters;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import trinsdar.ic2c_extras.blockentity.BlockEntityThermalCentrifuge;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

public class HeatComponent extends GuiWidget {
    BlockEntityThermalCentrifuge block;
    Vec2i texPos;

    public HeatComponent(BlockEntityThermalCentrifuge tile, Box2i box, Vec2i pos) {
        super(box);
        this.block = tile;
        this.texPos = pos;
    }

    @Override
    protected void addRequests(Set<ActionRequest> set) {
        set.addAll(Arrays.asList(ActionRequest.DRAW_BACKGROUND, ActionRequest.TOOLTIP));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void drawBackground(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
        float heat = this.block.getHeat();
        if (heat > 0) {
            int x = gui.getGuiLeft();
            int y = gui.getGuiTop();
            float per = heat / this.block.maxHeat;
            if (per > 1.0F) {
                per = 1.0F;
            }

            Box2i box = this.getBox();
            int maxX = box.getWidth();
            int lvl = (int) (per * (float) maxX);
            if (lvl <= 0) {
                return;
            }

            gui.drawTextureRegion(matrix, x + box.getX(), y + box.getY(), this.texPos.getX(), this.texPos.getY(), lvl, box.getHeight());
        }

    }

    @OnlyIn(Dist.CLIENT)
    public void addTooltips(PoseStack matrix, int mouseX, int mouseY, Consumer<Component> tooltips) {
        if (this.isMouseOver(mouseX, mouseY) && this.hasEUReader()) {
            tooltips.accept(this.translate("gui.ic2c_extras.heat", Formatters.EU_FORMAT.format((double)this.block.heat), Formatters.EU_FORMAT.format((double)this.block.maxHeat)));
        }

    }
}
