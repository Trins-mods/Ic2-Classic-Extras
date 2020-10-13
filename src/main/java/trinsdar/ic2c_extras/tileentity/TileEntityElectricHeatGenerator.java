package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.network.adv.NetworkField;
import ic2.api.energy.tile.IHeatSource;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.math.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.container.ContainerElectricHeatGenerator;
import trinsdar.ic2c_extras.util.Registry;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

public class TileEntityElectricHeatGenerator extends TileEntityElecMachine implements IHeatSource, ITickable, IHasGui {
    @NetworkField(index = 7)
    public int heat;
    @NetworkField(index = 8)
    public int maxHeat;
    private int coilCount = 0;
    public TileEntityElectricHeatGenerator() {
        super(11, 128);
        this.maxHeat = 0;
        this.maxEnergy = 10000;
        this.setFuelSlot(10);
        this.addNetworkFields("heat", "maxHeat");
        this.addGuiFields("heat", "maxHeat");
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.ELECTRIC_HEAT_GENERATOR;
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, 10);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), 10);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, 10);
        handler.registerSlotType(SlotType.Fuel, 10);
    }

    @Override
    public int maxrequestHeatTick(EnumFacing enumFacing) {
        return enumFacing == null || enumFacing == this.getFacing() ? this.maxHeat : 0;
    }

    @Override
    public int requestHeat(EnumFacing enumFacing, int i) {
        return drawHeat(enumFacing, i, false);
    }

    @Override
    public int drawHeat(EnumFacing side, int request, boolean simulate) {
        if ((side == null && simulate) || (side != null && side == this.getFacing())){
            if (request <= this.maxrequestHeatTick(side)){
                int drawn = Math.min(this.heat, request);
                if (!simulate) {
                    heat -= drawn;
                }
                return drawn;
            }
        }
        return 0;
    }

    @Override
    public boolean supportsNotify() {
        return false;
    }

    @Override
    public void update() {
        this.handleChargeSlot(this.maxEnergy);
        int oldMaxHeat = this.maxHeat;
        this.maxHeat = coilCount * 10;
        if (oldMaxHeat != this.maxHeat){
            this.getNetwork().updateTileGuiField(this, "maxHeat");
        }
        if (this.energy > 0 && this.maxHeat > 0 && this.heat < this.maxHeat){
            int room = this.maxHeat - this.heat;
            int made = Math.min(room, this.energy);
            heat+= made;
            this.getNetwork().updateTileGuiField(this, "heat");
            energy -= made;
            if (!this.isActive){
                this.setActive(true);
            }
        } else {
            if (this.isActive){
                this.setActive(false);
            }
        }
    }

    @Override
    public int getMaxStackSize(int slot) {
        if (slot < 10) return 1;
        return super.getMaxStackSize(slot);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        super.setStackInSlot(slot, stack);
        this.coilCount = 0;
        for (int i = 0; i < 10; i++){
          if (this.getStackInSlot(i).getItem() == Registry.coil){
              coilCount++;
          }
        }
    }

    @Override
    public boolean canRemoveBlock(EntityPlayer player) {
        return true;
    }

    @Override
    public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
        return this.getFacing() != facing;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.heat = nbt.getInteger("heat");
        this.maxHeat = nbt.getInteger("maxHeat");
        this.coilCount = nbt.getInteger("coilCount");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("heat", this.heat);
        nbt.setInteger("maxHeat", this.maxHeat);
        nbt.setInteger("coilCount", this.coilCount);
        return nbt;
    }

    @Override
    public void setFacing(EnumFacing face) {
        super.setFacing(face);
        updateConnections();
    }

    public void updateConnections() {
        for (EnumFacing facing : EnumFacing.VALUES) {
            BlockPos sidedPos = pos.offset(facing);
            if (world.isBlockLoaded(sidedPos)) {
                world.neighborChanged(sidedPos, Blocks.AIR, pos);
            }
        }
        if (world.isBlockLoaded(pos)) {
            world.neighborChanged(pos, Blocks.AIR, pos);
        }
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerElectricHeatGenerator(entityPlayer.inventory,this);
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
}
