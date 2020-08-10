package trinsdar.ic2c_extras.asm.mixins;

import ic2.api.classic.network.adv.NetworkField;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trinsdar.ic2c_extras.container.ContainerNuclearReactorNew;
import trinsdar.ic2c_extras.util.Ic2cExtrasTank;

@Mixin(value = TileEntityNuclearSteamReactor.class, remap = false)
public abstract class MixinTileEntityNuclearSteamReactor extends TileEntityNuclearReactorBase implements ITankListener {

    @Shadow
    IFluidHandler fluidHandler;

    @NetworkField(index = 5)
    private IC2Tank water2 = new Ic2cExtrasTank(2000);

    @NetworkField(index = 6)
    private IC2Tank steam2 = new Ic2cExtrasTank(20000);
    

    @Inject(method = "<init>", at = @At("RETURN"))
    public void onConstruction(CallbackInfo info){
        fluidHandler = new FluidHandlerSteamReactor(water2, steam2);
        this.addGuiFields("heat", "maxHeat", "steam2", "water2");
        this.addNetworkFields("steam2", "water2");
        water2.addListener(this);
        steam2.addListener(this);
    }

    public FluidTank getWaterTank() {
        return this.water2;
    }

    public FluidTank getSteamTank() {
        return this.steam2;
    }

    @Override
    public void onTankChanged(IFluidTank iFluidTank) {
        this.getNetwork().updateTileGuiField(this, "water2");
        this.getNetwork().updateTileGuiField(this, "steam2");
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerNuclearReactorNew(player.inventory, this);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.water2.writeToNBT(this.getTag(nbt, "WaterTank"));
        this.steam2.writeToNBT(this.getTag(nbt, "SteamTank"));
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.water2.readFromNBT(nbt.getCompoundTag("WaterTank"));
        this.steam2.readFromNBT(nbt.getCompoundTag("SteamTank"));
    }

    public void updateReactorSize() {
        super.updateReactorSize();
        int expand = this.size - 3;
        this.water2.setCapacity(2000 + expand * 1000);
        this.steam2.setCapacity(20000 + expand * 5000);
    }
}
