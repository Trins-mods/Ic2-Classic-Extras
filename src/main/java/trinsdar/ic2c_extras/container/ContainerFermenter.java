package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.components.base.FluidTankComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidUtil;
import trinsdar.ic2c_extras.tileentity.TileEntityFermenter;
import trinsdar.ic2c_extras.util.guicomponent.GuiComponentFermenterProgress;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

public class ContainerFermenter extends ContainerTileComponent<TileEntityFermenter> {

    public ContainerFermenter(InventoryPlayer player, TileEntityFermenter tile) {
        super(tile);
        this.addSlotToContainer(new SlotOutput(player.player, tile, 0, 86, 83));
        this.addSlotToContainer(new SlotCustom(tile,1, 14, 46, new FluidItemFilter()));
        this.addSlotToContainer(new SlotOutput(player.player, tile,2, 14, 64));
        this.addSlotToContainer(new SlotCustom(tile,3, 148, 43, new FluidItemFilter()));
        this.addSlotToContainer(new SlotOutput(player.player, tile,4, 148, 61));
        this.addSlotToContainer(new SlotUpgrade(tile, 5, 143, 83));
        this.addSlotToContainer(new SlotUpgrade(tile, 6, 125, 83));
        this.addPlayerInventory(player, 0, 18);
        this.addComponent(new FluidTankComp(new Box2D(38,49,48, 30), tile.getInputTank(), new Vec2i(176,68), new Box2D(38, 49, 48, 30)));
        this.addComponent(new FluidTankComp(new Box2D(21,127,16, 58), tile.getOutputTank(), new Vec2i(176,14), new Box2D(127, 21, 16, 58)));
        this.addComponent(new GuiComponentFermenterProgress(tile, new Box2D(41, 40, 42,5), new Vec2i(176, 9), false));
        this.addComponent(new GuiComponentFermenterProgress(tile, new Box2D(37, 87, 42,9), new Vec2i(176, 0), true));
    }

    @Override
    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.FERMENTER;
    }

    @Override
    public void onGuiLoaded(GuiIC2 gui) {
        gui.dissableInvName();
        gui.disableName();
        gui.setMaxGuiY(184);
    }

    @Override
    public int guiInventorySize() {
        return this.getGuiHolder().slotCount;
    }

    public static class FluidItemFilter implements IFilter {
        @Override
        public boolean matches(ItemStack stack) {
            return !stack.isEmpty() && FluidUtil.getFluidHandler(stack) != null;
        }
    }
}
