package trinsdar.ic2c_extras.container;

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
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.Loader;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;

import java.util.ArrayList;
import java.util.Arrays;

public class ContainerOreWashingPlant extends ContainerTileComponent<TileEntityOreWashingPlant>
{
    public static ArrayList<IFilter> filters = new ArrayList<>();
    public ContainerOreWashingPlant(InventoryPlayer player, TileEntityOreWashingPlant tile)
    {
        super(tile);
        filters.addAll(Arrays.asList(new BasicItemFilter(Items.WATER_BUCKET), new BasicItemFilter(Ic2Items.waterCell)));
        if (Loader.isModLoaded("gtclassic")){
            filters.add(new BasicItemFilter(getTube()));
        }
        if (Loader.isModLoaded("forestry")){
            filters.addAll(Arrays.asList(new BasicItemFilter(getCapsule(new ItemStack(Item.getByNameOrId("forestry:can"), 1, 1))), new BasicItemFilter(getCapsule(new ItemStack(Item.getByNameOrId("forestry:capsule"), 1, 1))), new BasicItemFilter(getCapsule(new ItemStack(Item.getByNameOrId("forestry:refractory"), 1, 1)))));
        }
        IFilter[] filter = new IFilter[filters.size()];
        filter = filters.toArray(filter);
        this.addSlotToContainer(new SlotCustom(tile, 0, 56, 17, tile.filter));
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 1, 56, 53));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 111, 17));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 111, 35));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 111, 53));
        this.addSlotToContainer(new SlotCustom(tile, 5, 8, 12, new ArrayFilter(filter)));
        this.addSlotToContainer(new SlotCustom(tile, 6, 8, 57, null));

        for(int i = 0; i < 4; ++i)
        {
            this.addSlotToContainer(new SlotUpgrade(tile, 7 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, Ic2GuiComp.machineChargeBox, Ic2GuiComp.machineChargePos));
        this.addComponent(new MachineProgressComp(tile, Ic2GuiComp.machineProgressBox, Ic2GuiComp.machineProgressPos));
        this.addComponent(new FluidTankComp(new Box2D(13, 32, 16, 58), tile.getWaterTank(), new Vec2i(176, 133), new Box2D(32, 13, 16, 58)));
    }
    public static ItemStack getTube() {
        FluidStack fluid = new FluidStack(FluidRegistry.WATER, 1000);
        ItemStack stack = new ItemStack(Item.getByNameOrId("gtclassic:test_tube"));
        IFluidHandlerItem handler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        handler.fill(fluid, true);
        return stack;
    }

    public static ItemStack getCapsule(ItemStack stack) {
        FluidStack fluid = new FluidStack(FluidRegistry.WATER, 1000);
        IFluidHandlerItem handler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        handler.fill(fluid, true);
        return stack;
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
