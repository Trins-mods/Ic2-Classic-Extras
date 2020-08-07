package trinsdar.ic2c_extras.container;

import ic2.core.block.generator.tile.TileEntityNuclearReactorElectric;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotReactor;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2BlockLang;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentInvalidReactorSlot;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentNuclearReactorEuPerTick;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentNuclearReactorHeat;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

public class ContainerNuclearReactorElectricNew extends ContainerComponent<TileEntityNuclearReactorElectric> {
    public int size;
    public ContainerNuclearReactorElectricNew(InventoryPlayer player, TileEntityNuclearReactorElectric tile) {
        super(tile);
        this.size = tile.getReactorSize();
        int x = 0;
        int y = 0;

        for(int i = 0; i < 54; ++i) {
            if (x < this.size) {
                this.addSlotToContainer(new SlotReactor(tile, i, 26 + 18 * x, 25 + 18 * y));
            } else {
                this.addComponent(new GuiComponentInvalidReactorSlot(tile, 26 + 18 * x, 25 + 18 * y));
            }
            ++x;
            if (x >= 9) {
                ++y;
                x = 0;
            }
        }
        this.addPlayerInventory(player, 19, 77);
        this.addComponent(new GuiComponentNuclearReactorEuPerTick(tile));
        this.addComponent(new GuiComponentNuclearReactorHeat(tile));
    }

    @SideOnly(Side.CLIENT)
    public void onGuiLoaded(GuiIC2 gui) {
        gui.setMaxGuiY(243);
        gui.dissableInvName();
    }

    @Override
    public int guiInventorySize() {
        return 6 * this.size;
    }


    @Override
    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.NUCLEAR_REACTOR;
    }

    @Override
    public LocaleComp getGuiName() {
        return Ic2BlockLang.nuclearReactor;
    }
}
