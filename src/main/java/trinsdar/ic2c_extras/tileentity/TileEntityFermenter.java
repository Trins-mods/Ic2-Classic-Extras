package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.energy.tile.IHeatSource;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.jetbrains.annotations.Nullable;
import trinsdar.ic2c_extras.util.Ic2cExtrasTank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TileEntityFermenter extends TileEntityMachine implements IOutputMachine, ITickable, IFluidHandler {
    int heat;
    @NetworkField(index = 3)
    IC2Tank inputTank = new IC2Tank(10000){
        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return super.canFillFluidType(fluid) && fluid.getFluid().getName().equals("biomass");
        }
    };
    @NetworkField(index = 4)
    IC2Tank outputTank = new IC2Tank(2000);
    @NetworkField(index = 5)
    int fertProgress = 0;
    @NetworkField(index = 6)
    int bioProgrees = 0;
    int maxBioProgress = 4000;

    boolean checkHeatSource = true;
    private static int outputSlot = 0;
    public TileEntityFermenter() {
        super(7);
    }

    @Override
    public IHasInventory getOutputInventory() {
        return null;
    }

    @Override
    public IHasInventory getInputInventory() {
        return null;
    }

    @Override
    public double getEnergy() {
        return 0;
    }

    @Override
    public boolean useEnergy(double v, boolean b) {
        return false;
    }

    @Override
    public void setRedstoneSensitive(boolean b) {

    }

    @Override
    public boolean isRedstoneSensitive() {
        return false;
    }

    @Override
    public boolean isProcessing() {
        return this.getActive();
    }

    @Override
    public boolean isValidInput(ItemStack itemStack) {
        return false;
    }

    @Override
    public Set<IMachineUpgradeItem.UpgradeType> getSupportedTypes() {
        return Collections.singleton(IMachineUpgradeItem.UpgradeType.ImportExport);
    }

    @Override
    public World getMachineWorld() {
        return this.getWorld();
    }

    @Override
    public BlockPos getMachinePos() {
        return this.getPos();
    }

    @Override
    public void update() {
        if (this.fertProgress >= 500){
            if (this.getStackInSlot(outputSlot).isEmpty()){
                this.setStackInSlot(outputSlot, Ic2Items.fertilizer.copy());
                this.fertProgress = 0;
            } else if (this.getStackInSlot(outputSlot).getCount() < 64){
                this.getStackInSlot(outputSlot).grow(1);
                this.fertProgress = 0;
            }
        }
        TileEntity offset = getWorld().getTileEntity(getPos().offset(this.getFacing()));
        if (offset instanceof IHeatSource && this.inputTank.getFluidAmount() >= 20 && this.outputTank.getFluidAmount() + 400 <= this.outputTank.getCapacity() && this.getStackInSlot(outputSlot).getCount() < 64){
            IHeatSource heatSource = (IHeatSource) offset;
            int simHeatReceived = heatSource.drawHeat(this.getFacing().getOpposite(), 100, false);
            if (simHeatReceived > 0){
                if (this.bioProgrees < 4000){
                    this.bioProgrees += simHeatReceived;
                } else {
                    this.bioProgrees = 0;
                    this.inputTank.drain(20, true);
                    this.outputTank.fill(FluidRegistry.getFluidStack("biogas", 400), true);
                    fertProgress += 20;
                }
                if (!this.isActive){
                    this.setActive(true);
                }
            } else {
                if (this.isActive){
                    this.setActive(false);
                }
            }
        } else {
            if (this.isActive){
                this.setActive(false);
            }
        }
    }

    @Override
    public void onBlockUpdate(Block block) {
        super.onBlockUpdate(block);
        this.checkHeatSource = true;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        List<IFluidTankProperties> list = new ArrayList<>();
        list.addAll(Arrays.asList(this.inputTank.getTankProperties()));
        list.addAll(Arrays.asList(this.outputTank.getTankProperties()));
        return list.toArray(new IFluidTankProperties[0]);
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return this.inputTank.fill(resource, doFill);
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return this.outputTank.drain(resource, doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return this.outputTank.drain(maxDrain, doDrain);
    }
}
