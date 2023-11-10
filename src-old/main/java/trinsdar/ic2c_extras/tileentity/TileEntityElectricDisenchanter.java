package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import trinsdar.ic2c_extras.container.ContainerElectricDisenchanter;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

public class TileEntityElectricDisenchanter extends TileEntityElecMachine implements IHasGui, ITickable, IProgressMachine {
    private static final int SLOT_BOOK = 0;
    private static final int SLOT_TOOL = 1;
    private static final int SLOT_OUTPUT = 2;
    private static final int SLOT_FUEL = 3;
    int progress = 0;
    int maxProgress = 100;
    boolean canProgress = false;
    public TileEntityElectricDisenchanter() {
        super(4, 512);
        this.setMaxEnergy(10000000);
        this.setFuelSlot(SLOT_FUEL);
        this.addGuiFields("progress");
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, SLOT_FUEL);
        handler.registerDefaultSlotAccess(AccessRule.Import, SLOT_BOOK, SLOT_TOOL);
        handler.registerDefaultSlotAccess(AccessRule.Export, SLOT_OUTPUT);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), SLOT_OUTPUT);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), SLOT_BOOK, SLOT_TOOL);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), 1);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, SLOT_FUEL);
        handler.registerSlotType(SlotType.Fuel, SLOT_FUEL);
        handler.registerSlotType(SlotType.Input, SLOT_BOOK, SLOT_TOOL);
        handler.registerSlotType(SlotType.Output, SLOT_OUTPUT);
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.ELECTRIC_DISENCHANTER;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        super.setStackInSlot(slot, stack);
        canProgress = false;
        if (!this.getStackInSlot(SLOT_BOOK).isEmpty() && !this.getStackInSlot(SLOT_TOOL).isEmpty() && this.getStackInSlot(SLOT_OUTPUT).isEmpty()){
            ItemStack tool = this.getStackInSlot(SLOT_TOOL);
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(tool);
            if (nbt.hasKey("ench")){
                NBTTagList enchantList = nbt.getTagList("ench", 10);
                if (!enchantList.hasNoTags()){
                    canProgress = true;
                }
            }
        }
    }

    @Override
    public boolean supportsNotify() {
        return false;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerElectricDisenchanter(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer entityPlayer) {
        return GuiComponentContainer.class;
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return !this.isInvalid();
    }

    @Override
    public boolean hasGui(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public float getProgress() {
        return progress;
    }

    @Override
    public float getMaxProgress() {
        return maxProgress;
    }

    @Override
    public void update() {
        this.handleChargeSlot(maxEnergy);
        if (canProgress && this.getStoredEU() >= 500){
            setActive(true);
            progress++;
            useEnergy(500);
            if (progress >= maxProgress) {
                disEnchant();
                progress = 0;
                notifyNeighbors();
            }
            getNetwork().updateTileGuiField(this, "progress");
        } else {
            if (progress != 0) {
                progress = 0;
                getNetwork().updateTileGuiField(this, "progress");
            }
            setActive(false);
        }
    }

    public void disEnchant(){
        ItemStack tool = this.getStackInSlot(SLOT_TOOL);
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(tool);
        NBTTagList enchantList = nbt.getTagList("ench", 10);
        int index = world.rand.nextInt(enchantList.tagCount());
        NBTTagCompound enchantment = enchantList.getCompoundTagAt(index);
        ItemStack output = new ItemStack(Items.ENCHANTED_BOOK);
        NBTTagCompound outputNBT = StackUtil.getOrCreateNbtData(output);
        NBTTagList outputEnchantList = new NBTTagList();
        outputEnchantList.appendTag(enchantment);
        outputNBT.setTag("StoredEnchantments", outputEnchantList);
        enchantList.removeTag(index);
        if (enchantList.hasNoTags()){
            nbt.removeTag("ench");
            if (nbt.hasKey("RepairCost")){
                nbt.setInteger("RepairCost", 0);
            }
        }
        this.getStackInSlot(SLOT_BOOK).shrink(1);
        this.setStackInSlot(SLOT_OUTPUT, output);
    }
}
