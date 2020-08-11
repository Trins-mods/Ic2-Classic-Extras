package trinsdar.ic2c_extras.container;

import ic2.api.classic.reactor.ISteamReactor;
import ic2.core.block.base.tile.TileEntityNuclearReactorBase;
import ic2.core.block.generator.tile.TileEntityNuclearReactorElectric;
import ic2.core.block.generator.tile.TileEntityNuclearSteamReactor;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.base.FluidTankComp;
import ic2.core.inventory.slots.SlotReactor;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2BlockLang;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentInvalidReactorSlot;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentNuclearReactorEuPerTick;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentNuclearReactorHeat;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentSteamReactorString;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

public class ContainerNuclearReactorNew extends ContainerComponent<TileEntityNuclearReactorBase> {
    public int size;
    public ContainerNuclearReactorNew(InventoryPlayer player, TileEntityNuclearReactorBase tile) {
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
        this.addPlayerInventory(player, 18, 77);
        if (tile instanceof TileEntityNuclearReactorElectric){
            this.addComponent(new GuiComponentNuclearReactorEuPerTick(tile, new Box2D(108, 136, 97, 13)));
        }
        if (tile instanceof TileEntityNuclearSteamReactor){
            TileEntityNuclearSteamReactor steamReactor = (TileEntityNuclearSteamReactor) tile;
            this.addComponent(new GuiComponentSteamReactorString(steamReactor));
            this.addComponent(new FluidTankComp(new Box2D(25, 6, 16, 58), steamReactor.getWaterTank(), new Vec2i(212, 18), new Box2D(6, 25, 16, 58)));
            this.addComponent(new FluidTankComp(new Box2D(25, 190, 16, 58), steamReactor.getSteamTank(), new Vec2i(212, 18), new Box2D(190, 25, 16, 58)));
        }
        this.addComponent(new GuiComponentNuclearReactorHeat(tile, new Box2D(7, 136, 100, 13)));
    }

    @SideOnly(Side.CLIENT)
    public void onGuiLoaded(GuiIC2 gui) {
        gui.setMaxGuiXY(212, 243);
        gui.dissableInvName();
    }

    @Override
    public int guiInventorySize() {
        return 6 * this.size;
    }


    @Override
    public ResourceLocation getTexture() {
        return this.getGuiHolder() instanceof ISteamReactor ? Ic2cExtrasResourceLocations.NUCLEAR_STEAM_REACTOR : Ic2cExtrasResourceLocations.NUCLEAR_REACTOR;
    }

    @Override
    public LocaleComp getGuiName() {
        return Ic2BlockLang.nuclearReactor;
    }
}
