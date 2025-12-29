package trinsdar.ic2c_extras.blockentity;

import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.network.buffer.NetworkInfo;
import ic2.api.util.DirectionList;
import ic2.core.block.base.cache.CapabilityCache;
import ic2.core.block.base.cache.ICache;
import ic2.core.block.base.features.ITickListener;
import ic2.core.block.base.tiles.BaseElectricTileEntity;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.ITileGui;
import ic2.core.inventory.container.IC2Container;
import ic2.core.inventory.filter.SpecialFilters;
import ic2.core.inventory.filter.special.ElectricItemFilter;
import ic2.core.inventory.filter.special.MachineFilter;
import ic2.core.inventory.handler.AccessRule;
import ic2.core.inventory.handler.InventoryHandler;
import ic2.core.inventory.handler.SlotType;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.gtreimagined.tesseract.api.forge.TesseractCaps;
import org.gtreimagined.tesseract.api.hu.IHeatHandler;
import trinsdar.ic2c_extras.container.ContainerElectricHeatGenerator;
import trinsdar.ic2c_extras.container.ContainerOreWashingPlant;
import trinsdar.ic2c_extras.init.ModBlocks;

public class BlockEntityElectricHeatGenerator extends BaseElectricTileEntity implements IHeatTile, ITickListener, ITileGui {
    @NetworkInfo
    final Ic2cExtrasHeatHandler heatHandler;
    ICache<IHeatHandler> heatCache;
    int coilCount = 0;
    public BlockEntityElectricHeatGenerator(BlockPos pos, BlockState state) {
        super(pos, state, 11, 32, 10000);
        heatHandler = new Ic2cExtrasHeatHandler(0, 0, 32, this){
            @Override
            public int getHeatCap() {
                return 10 * coilCount;
            }
        };
        heatCache = new CapabilityCache<>(this, DirectionList.ALL, TesseractCaps.HEAT_CAPABILITY);
        this.addCaches(heatCache);
        this.setFuelSlot(0);
        this.addCapability(TesseractCaps.HEAT_CAPABILITY, heatHandler);
        this.addGuiFields("heatHandler");
    }

    @Override
    protected void addSlotInfo(InventoryHandler handler) {
        handler.registerBlockSides(DirectionList.ALL);
        handler.registerBlockAccess(DirectionList.ALL, AccessRule.BOTH);
        handler.registerSlotAccess(AccessRule.BOTH, 0);
        handler.registerSlotsForSide(DirectionList.UP.invert(), 0);
        handler.registerInputFilter(SpecialFilters.createChargeFilter(), 0);
        handler.registerOutputFilter(ElectricItemFilter.NOT_DISCHARGE_FILTER, 0);
        handler.registerNamedSlot(SlotType.BATTERY, 0);
    }

    @Override
    public boolean supportsNotify() {
        return false;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.ELECTRIC_HEAT_GENERATOR_TYPE;
    }

    @Override
    public BlockEntity getOwner() {
        return this;
    }

    @Override
    public Ic2cExtrasHeatHandler getHeatHandler() {
        return heatHandler;
    }

    @Override
    public boolean canInput(Direction direction) {
        return false;
    }

    @Override
    public boolean canOutput(Direction direction) {
        return direction == this.getFacing();
    }

    @Override
    public void onTick() {
        handleChargeSlot(this.maxEnergy);
        if (getStoredEU() > 0 && heatHandler.getHeat() < heatHandler.getHeatCap()){
            int min = Math.min(Math.min(10, getStoredEU()), heatHandler.getHeatCap() - heatHandler.getHeat());
            this.useEnergy(min);
            heatHandler.insertInternal(min, false);
            if (!this.isActive()) setActive(true);
            this.updateGuiField("heatHandler");
        } else {
            if (this.isActive()) setActive(false);
        }
        this.heatHandler.update(this.isActive());

    }

    @Override
    public boolean canAcceptEnergy(IEnergyEmitter emitter, Direction side) {
        if (side == this.getFacing()) return false;
        return super.canAcceptEnergy(emitter, side);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        super.setStackInSlot(slot, stack);
        int coilCount = 0;
        for (int i = 1; i < 11; i++) {
            ItemStack stack1 = getStackInSlot(i);
            if (!stack1.isEmpty()) coilCount++;
        }
        this.coilCount = coilCount;
    }

    @Override
    public int getMaxStackSize(int slot) {
        if (slot > 0 && slot < 11) return 1;
        return super.getMaxStackSize(slot);
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand interactionHand, Direction direction, int i) {
        return new ContainerElectricHeatGenerator(this, player, i);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        this.heatHandler.serialize(compound);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.heatHandler.deserialize(compound);
    }
}
