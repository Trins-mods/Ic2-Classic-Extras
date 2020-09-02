package trinsdar.ic2c_extras.util.guicomponent;

import ic2.core.block.base.tile.TileEntityNuclearReactorBase;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.item.reactor.base.ItemHeatVentBase;
import ic2.core.util.math.Box2D;
import net.minecraft.item.ItemStack;
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
        double steamPerTick = 0;
        for (ItemStack stack : reactor.inventory){
            if (stack.getItem() instanceof ItemHeatVentBase){
                ItemHeatVentBase base = (ItemHeatVentBase) stack.getItem();
                ItemHeatVentBase.VentProperty prop = base.getProperty(stack);
                if (prop != null && prop.getType() == 1) {
                    int customDamage = base.getCustomDamage(stack);
                    double effizens = customDamage < 100 ? 0.0D : (double)customDamage / ((double)base.getMaxCustomDamage(stack) - 100.0D) * 100.0D;
                    double steamProduction = (double)prop.getSelf() * effizens;
                    steamProduction /= 100.0D;
                    steamProduction /= 40.0D;
                    steamProduction *= 160.0D;
                    steamPerTick += steamProduction;
                }
            }
        }
        gui.drawString(Ic2cExtrasLang.REACTOR_STEAM.getLocalizedFormatted((int)steamPerTick), this.getPosition().getX() + 3, this.getPosition().getY() + 3, Color.cyan.hashCode());
    }
}
