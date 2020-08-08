package trinsdar.ic2c_extras.mixins;

import ic2.core.block.base.tile.TileEntityNuclearReactorBase;
import ic2.core.block.generator.tile.TileEntityNuclearSteamReactor;
import ic2.core.fluid.FluidHandlerSteamReactor;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.util.obj.ITankListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import trinsdar.ic2c_extras.container.ContainerNuclearReactorNew;

@Mixin(TileEntityNuclearSteamReactor.class)
public abstract class MixinTileEntityNuclearSteamReactor extends TileEntityNuclearReactorBase implements ITankListener {
    private IC2Tank water = new IC2Tank(2000){
        @Override
        public void setCapacity(int capacity) {
            super.setCapacity(capacity);
            onContentsChanged();
        }
    };
    private IC2Tank steam = new IC2Tank(20000){
        @Override
        public void setCapacity(int capacity) {
            super.setCapacity(capacity);
            onContentsChanged();
        }
    };
    @Shadow
    IFluidHandler fluidHandler;

    public MixinTileEntityNuclearSteamReactor(){
        fluidHandler = new FluidHandlerSteamReactor(water, steam);
        this.addGuiFields("heat", "maxHeat", "steam", "water");
        water.addListener(this);
        steam.addListener(this);
    }

    public FluidTank getWaterTank() {
        return this.water;
    }

    public FluidTank getSteamTank() {
        return this.steam;
    }

    @Override
    public void onTankChanged(IFluidTank iFluidTank) {
        this.getNetwork().updateTileGuiField(this, "water");
        this.getNetwork().updateTileGuiField(this, "steam");
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerNuclearReactorNew(player.inventory, this);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.water.writeToNBT(this.getTag(nbt, "WaterTank"));
        this.steam.writeToNBT(this.getTag(nbt, "SteamTank"));
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.water.readFromNBT(nbt.getCompoundTag("WaterTank"));
        this.steam.readFromNBT(nbt.getCompoundTag("SteamTank"));
    }

    public void updateReactorSize() {
        super.updateReactorSize();
        int expand = this.size - 3;
        this.water.setCapacity(2000 + expand * 1000);
        this.steam.setCapacity(20000 + expand * 5000);
    }
}
