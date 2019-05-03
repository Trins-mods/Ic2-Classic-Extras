package trinsdar.ic2c_extras.blocks.container;

import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.components.base.FluidTankComp;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityFluidCanningMachine;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.base.TileEntityFluidCannerBase;
import trinsdar.ic2c_extras.util.guicomponent.SlotUpgrade2;

import java.util.Arrays;

public class ContainerFluidCanningMachine extends ContainerTileComponent<TileEntityFluidCanningMachine> {
    public static Vec2i tankOverlay = new Vec2i(176, 133);
    public static Box2D machineChargeBox = new Box2D(44, 37, 14, 14);
    public static Vec2i machineChargePos = new Vec2i(176, 0);
    public static Box2D machineProgressBox = new Box2D(64, 35, 24, 17);
    public static Vec2i machineProgressPos = new Vec2i(176, 14);
    public ContainerFluidCanningMachine(InventoryPlayer player, TileEntityFluidCanningMachine tile)
    {
        super(tile);
        this.addSlotToContainer(new SlotCustom(tile, 0, 44, 18, null));
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 1, 44, 54));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 94, 36));
        for(int i = 0; i < 4; ++i)
        {
            this.addSlotToContainer(new SlotUpgrade2(tile, 3 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, machineChargeBox, machineChargePos));
        this.addComponent(new MachineProgressComp(tile, machineProgressBox, machineProgressPos));
        this.addComponent(new FluidTankComp(new Box2D(13, 21, 16, 58), tile.inputTank, tankOverlay, new Box2D(21, 13, 16, 58)));
        this.addComponent(new FluidTankComp(new Box2D(13, 117, 16, 58), tile.outputTank, tankOverlay, new Box2D(117, 13, 16, 58)));
    }

    @Override
    public ResourceLocation getTexture()
    {
        return this.getGuiHolder().getGuiTexture();
    }

    @Override
    public int guiInventorySize()
    {
        return this.getGuiHolder().slotCount;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.getGuiHolder().canInteractWith(entityPlayer);
    }
}
