package trinsdar.ic2c_extras.tileentity;

import ic2.core.block.base.tile.TileEntityGeneratorBase;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.container.ContainerThermoElectricGenerator;
import trinsdar.ic2c_extras.items.ItemRTG;
import trinsdar.ic2c_extras.util.Registry;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

public class TileEntityThermoElectricGenerator extends TileEntityGeneratorBase {
    public static BasicItemFilter filter = new BasicItemFilter(new ItemStack(Registry.plutoniumRTG));

    public TileEntityThermoElectricGenerator() {
        super(6);
        this.tier = 1;
        this.maxStorage = 20000;
    }

    public BasicItemFilter getFilter() {
        return filter;
    }

    @Override
    public double getOfferedEnergy() {
        return getProduction() == 0 ? Math.min(this.storage, 32) : Math.min(this.storage, getProduction());
    }

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
        production = (int) Math.pow(2, count);
        return production;
    }

    @Override
    public int getOutput() {
        return getProduction();
    }

    @Override
    public boolean isConverting() {
        return this.storage + this.getProduction() <= this.maxStorage;
    }

    @Override
    public int getMaxSendingEnergy() {
        return this.getProduction() + 1;
    }

    @Override
    public void update() {

        int oldEnergy = this.storage;
        boolean active = this.gainEnergy();
        if (this.storage > 0) {
            if (this.storage > this.maxStorage) {
                this.storage = this.maxStorage;
            }
        }

        if (!this.delayActiveUpdate()) {
            this.setActive(active);
        } else {
            if (this.ticksSinceLastActiveUpdate % this.getDelay() == 0) {
                this.setActive(this.activityMeter > 0);
                this.activityMeter = 0;
            }

            if (active) {
                ++this.activityMeter;
            } else {
                --this.activityMeter;
            }

            ++this.ticksSinceLastActiveUpdate;
        }

        if (oldEnergy != this.storage) {
            this.getNetwork().updateTileGuiField(this, "storage");
        }

        this.updateComparators();
    }

    private boolean isInventoryEmpty() {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (inventory.get(i).getItem() instanceof ItemRTG) {
                count += 1;
            }
        }
        return count == 0;
    }

    int counter = 0;

    @Override
    public boolean gainEnergy() {
        if (this.isConverting() && !isInventoryEmpty()) {
            this.storage += this.getProduction();
            counter++;
            if (counter == 20) {
                for (int i = 0; i < 6; i++) {
                    ItemStack stack = this.inventory.get(i);
                    if (!stack.isEmpty() && stack.getItem() instanceof ItemRTG) {
                        ItemRTG rtg = (ItemRTG) stack.getItem();
                        int damage = rtg.getCustomDamage(stack) + 1;
                        if (damage > rtg.getMaxCustomDamage(stack)) {
                            stack.shrink(1);
                        } else {
                            rtg.setCustomDamage(stack, damage);
                        }
                    }
                }
                counter = 0;
            }

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
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.THERMO_ELECTRIC_GENERATOR;
    }

    public static class TileEntityThermoElectricGeneratorMkII extends TileEntityThermoElectricGenerator {
        public static BasicItemFilter filter2 = new BasicItemFilter(new ItemStack(Registry.thoriumRTG));

        public TileEntityThermoElectricGeneratorMkII() {
            this.tier = 2;
            this.maxStorage = 30000;
        }

        @Override
        public BasicItemFilter getFilter() {
            return filter2;
        }

        @Override
        public LocaleComp getBlockName() {
            return Ic2cExtrasLang.THERMO_ELECTRIC_GENERATOR_MK_II;
        }

        @Override
        public int getProduction() {
            int count = 0;
            for (int i = 0; i < 6; i++) {
                if (inventory.get(i).isItemEqual(new ItemStack(Registry.thoriumRTG))) {
                    count += 1;
                }
            }
            if (count == 0) {
                return 0;
            }
            production = (int) Math.pow(2, count);
            return production;
        }
    }
}
