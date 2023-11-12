package trinsdar.ic2c_extras.blockentity;

import ic2.core.block.base.tiles.impls.BaseGeneratorTileEntity;
import ic2.core.inventory.container.IC2Container;
import ic2.core.inventory.handler.InventoryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.ic2c_extras.container.ContainerThermoElectricGenerator;
import trinsdar.ic2c_extras.init.ModBlocks;
import trinsdar.ic2c_extras.init.ModItems;

public class TileEntityThermoElectricGenerator extends BaseGeneratorTileEntity {

    protected boolean checkProduction = true;

    public TileEntityThermoElectricGenerator(BlockPos pos, BlockState state) {
        super(pos, state,6);
        this.tier = 1;
        this.maxStorage = 20000;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.THERMO_ELECTRIC_GENERATOR_TYPE;
    }

    protected void addSlotInfo(InventoryHandler handler) {
        /*handler.registerBlockSides(DirectionList.UP.invert());
        handler.registerBlockAccess(DirectionList.UP.invert(), AccessRule.BOTH);
        handler.registerSlotAccess(AccessRule.IMPORT, 0, 1, 2, 3, 4, 5);
        for (int i = 0; i < 6; i++) {
            handler.setSlotAccess(i, Direction.UP, AccessRule.DISABLED);
        }
        handler.registerSlotsForSide(DirectionList.DOWN.invert(), 0, 1, 2, 3, 4, 5);
        handler.registerInputFilter(new SimpleFilter(ModItems.PLUTONIUM_RTG), 0, 1, 2, 3, 4, 5);
        handler.registerNamedSlot(SlotType.INPUT, 0, 1, 2, 3, 4, 5);*/
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.production = compound.getInt("Production");
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("Production", production);
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

    @Override
    public float getEUProduction() {
        return Math.min(this.storage, this.production);
    }

    public int getProduction() {
        int count = -1;
        for (int i = 0; i < 6; i++) {
            if (inventory.get(i).getItem() == ModItems.PLUTONIUM_RTG) {
                count += 1;
            }
        }
        if (count == -1) {
            return 0;
        }
        return (int) Math.pow(2, count);
    }

    public boolean isConverting() {
        return this.storage + this.production <= this.maxStorage;
    }

    @Override
    public int getMaxEnergyOutput() {
        return 32 * tier;
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
    }

    @Override
    public void onTick() {
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
            this.updateGuiField("storage");
        }

        this.handleComparators();
    }

    int counter = 0;

    @Override
    public boolean gainEnergy() {
        if (this.isConverting() && production > 0) {
            this.storage += this.production;
            this.updateGuiField("storage");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean gainFuel() {
        return false;
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return new ContainerThermoElectricGenerator(this, player, i);
    }

    @Override
    protected void consumeFuel() {
    }

    @Override
    public boolean needsFuel() {
        return false;
    }

    @Override
    public int getMaxFuel() {
        return 0;
    }

}
