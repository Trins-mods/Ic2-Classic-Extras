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
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityMetalPresser;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.util.guicomponent.SlotUpgrade2;

import java.util.Arrays;

public class ContainerMetalPresser extends ContainerTileComponent<TileEntityMetalPresser> {
    public ContainerMetalPresser(InventoryPlayer player, TileEntityMetalPresser tile)
    {
        super(tile);
        this.addSlotToContainer(new SlotCustom(tile, 0, 56, 17, null));
        this.addSlotToContainer(new SlotCustom(tile, 1, 8, 57, null));
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 2, 56, 53));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 111, 17));


        for(int i = 0; i < 2; ++i)
        {
            this.addSlotToContainer(new SlotUpgrade2(tile, 3 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, Ic2GuiComp.machineChargeBox, Ic2GuiComp.machineChargePos));
        this.addComponent(new MachineProgressComp(tile, Ic2GuiComp.machineProgressBox, Ic2GuiComp.machineProgressPos));
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
