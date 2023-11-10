package trinsdar.ic2c_extras.tileentity;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityGeneratorBase;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Optional;
import trinsdar.ic2c_extras.container.ContainerThermoElectricGenerator;
import trinsdar.ic2c_extras.util.Registry;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.Map;

@Optional.Interface(modid = "gtclassic", iface = "gtclassic.api.interfaces.IGTDebuggableTile", striprefs = true)
public abstract class TileEntityThermoElectricGeneratorBase extends TileEntityGeneratorBase implements IGTDebuggableTile {

    protected boolean checkProduction = true;

    public TileEntityThermoElectricGeneratorBase() {
        super(6);
        this.tier = 1;
        this.maxStorage = 20000;
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.UP.invert());
        handler.registerDefaultSlotAccess(AccessRule.Import, 0, 1, 2, 3, 4, 5);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), 0, 1, 2, 3 ,4 ,5);
        handler.registerSlotType(SlotType.Input, 0, 1, 2, 3, 4, 5);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.production = nbt.getInteger("Production");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Production", production);
        return nbt;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        super.setStackInSlot(slot, stack);
        checkProduction = true;
    }

    @Override
    public int getMaxStackSize(int slot) {
        return 1;
    }

    public abstract BasicItemFilter getFilter();

    @Override
    public int getOutput() {
        return this.production;
    }

    public abstract int getProduction();

    @Override
    public double getWrenchDropRate() {
        return 1.0D;
    }

    @Override
    public boolean isConverting() {
        return this.storage + this.production <= this.maxStorage;
    }

    @Override
    public int getMaxSendingEnergy() {
        return 32 * tier;
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
        return true;
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
    }

    @Override
    public void update() {
        int oldEnergy = this.storage;
        if (checkProduction){
            int newProduction = this.getProduction();
            if (this.production != newProduction){
                this.production = newProduction;
            }
            checkProduction = false;
        }

        boolean active = this.gainEnergy();
        if (this.storage > 0) {
            if (this.storage > this.maxStorage) {
                this.storage = this.maxStorage;
            }
        }
        this.setActive(active);

        if (oldEnergy != this.storage) {
            this.getNetwork().updateTileGuiField(this, "storage");
        }

        this.updateComparators();
    }

    int counter = 0;

    @Override
    public boolean gainEnergy() {
        if (this.isConverting() && production > 0) {
            this.storage += this.production;
            this.getNetwork().updateTileGuiField(this, "fuel");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.THERMO_ELECTRIC_GENERATOR;
    }

    @Override
    public Box2D getEnergyBox() {
        return Ic2GuiComp.generatorEnergyBox;
    }

    @Override
    public Vec2i getEnergyPos() {
        return Ic2GuiComp.machineChargePos;
    }

    @Override
    public boolean gainFuel() {
        return false;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerThermoElectricGenerator(entityPlayer.inventory, this);
    }

    @Override
    public void getData(Map<String, Boolean> map) {
        map.put("Production: " + production, true);
    }

    public static class TileEntityThermoElectricGenerator extends TileEntityThermoElectricGeneratorBase {
        public static BasicItemFilter filter = new BasicItemFilter(new ItemStack(Registry.plutoniumRTG));
        public TileEntityThermoElectricGenerator() {
            this.tier = 1;
            this.maxStorage = 20000;
        }

        @Override
        public BasicItemFilter getFilter() {
            return filter;
        }

        @Override
        public LocaleComp getBlockName() {
            return Ic2cExtrasLang.THERMO_ELECTRIC_GENERATOR;
        }

        @Override
        public int getProduction() {
            int count = -1;
            for (int i = 0; i < 6; i++) {
                if (inventory.get(i).isItemEqual(new ItemStack(Registry.plutoniumRTG))) {
                    count += 1;
                }
            }
            if (count == -1) {
                return 0;
            }
            return (int) Math.pow(2, count);
        }

        @Override
        public double getOfferedEnergy() {
            return Math.min(this.storage, 32);
        }
    }

}
