package trinsdar.ic2c_extras.container;

import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.tileentity.TileEntityElectricDisenchanter;

public class ContainerElectricDisenchanter extends ContainerTileComponent<TileEntityElectricDisenchanter> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(IC2CExtras.MODID, "textures/guisprites/guielectricdisenchanter.png");
    public ContainerElectricDisenchanter(InventoryPlayer player, TileEntityElectricDisenchanter tile) {
        super(tile);
        this.addSlotToContainer(new SlotCustom(tile,0, 48, 36, new BasicItemFilter(new ItemStack(Items.BOOK))));
        this.addSlotToContainer(new SlotCustom(tile, 1,66, 36, new EnchantedFilter()));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 126, 36));
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 3, 9, 49));
        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, Ic2GuiComp.electricEnchanterChargeBox, Ic2GuiComp.machineChargePos));
        this.addComponent(new MachineProgressComp(tile, Ic2GuiComp.electricEnchanterProgressBox, Ic2GuiComp.machineProgressPos));
    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURE;
    }

    @Override
    public int guiInventorySize() {
        return this.getGuiHolder().slotCount;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.getGuiHolder().canInteractWith(entityPlayer);
    }

    public static class EnchantedFilter implements IFilter {

        @Override
        public boolean matches(ItemStack stack) {
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
            return nbt.hasKey("ench");
        }
    }
}
