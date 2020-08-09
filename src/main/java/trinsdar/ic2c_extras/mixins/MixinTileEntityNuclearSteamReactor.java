package trinsdar.ic2c_extras.mixins;

import ic2.core.block.base.tile.TileEntityNuclearReactorBase;
import ic2.core.block.generator.tile.TileEntityNuclearSteamReactor;
import ic2.core.fluid.FluidHandlerSteamReactor;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.util.obj.ITankListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trinsdar.ic2c_extras.container.ContainerNuclearReactorNew;

@Mixin(value = TileEntityNuclearSteamReactor.class, remap = false)
public abstract class MixinTileEntityNuclearSteamReactor extends TileEntityNuclearReactorBase implements ITankListener {

    @Shadow
    IFluidHandler fluidHandler;

    @Shadow
    private FluidTank water;

    @Shadow
    private FluidTank steam;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void onConstruction(CallbackInfo info){
        water = new IC2Tank(2000){
            @Override
            public void setCapacity(int capacity) {
                super.setCapacity(capacity);
                onContentsChanged();
            }
        };
        steam = new IC2Tank(20000){
            @Override
            public void setCapacity(int capacity) {
                super.setCapacity(capacity);
                onContentsChanged();
            }
        };
        fluidHandler = new FluidHandlerSteamReactor(water, steam);
        this.addGuiFields("heat", "maxHeat", "steam", "water");
        ((IC2Tank)water).addListener(this);
        ((IC2Tank)steam).addListener(this);
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
}
