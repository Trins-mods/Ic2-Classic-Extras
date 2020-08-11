package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ContainerItemComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.slots.SlotReactor;
import ic2.core.item.inv.inventories.NuclearJetpackInventory;
import ic2.core.util.math.Box2D;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentNuclearReactorEuPerTick;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentNuclearReactorHeat;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

public class ContainerNuclearJetpackNew extends ContainerItemComponent<NuclearJetpackInventory> {
    public ContainerNuclearJetpackNew(NuclearJetpackInventory inv, int id, InventoryPlayer player) {
        super(inv, id);
        for(int y = 0; y < 5; ++y) {
            for(int x = 0; x < 5; ++x) {
                this.addSlotToContainer(new SlotReactor(inv.holder, x + y * 5, 62 + x * 18, 18 + y * 18));
            }
        }
        this.addPlayerInventory(player, 18, 70);
        this.addComponent(new GuiComponentNuclearReactorEuPerTick(inv.holder, new Box2D(108, 129, 97, 13)));
        this.addComponent(new GuiComponentNuclearReactorHeat(inv.holder, new Box2D(7, 129, 100, 13)));
    }

    @Override
    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.NUCLEAR_JETPACK;
    }

    @Override
    public void onGuiLoaded(GuiIC2 gui) {
        gui.dissableInvName();
        gui.setMaxGuiXY(212, 236);
    }
}
