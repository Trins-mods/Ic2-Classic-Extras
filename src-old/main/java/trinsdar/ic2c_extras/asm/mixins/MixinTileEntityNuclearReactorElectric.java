package trinsdar.ic2c_extras.asm.mixins;

import ic2.core.block.base.tile.TileEntityNuclearReactorBase;
import ic2.core.block.generator.tile.TileEntityNuclearReactorElectric;
import ic2.core.inventory.container.ContainerIC2;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trinsdar.ic2c_extras.container.ContainerNuclearReactorNew;

@Mixin(value = TileEntityNuclearReactorElectric.class, remap = false)
public abstract class MixinTileEntityNuclearReactorElectric extends TileEntityNuclearReactorBase {
    @Inject(method = "<init>", at = @At("RETURN"))
    public void onConstruction(CallbackInfo info){
        this.addGuiFields("output", "heat", "maxHeat");
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerNuclearReactorNew(player.inventory, this);
    }

    @Override
    public void setHeat(int newHeat) {
        super.setHeat(newHeat);
        this.getNetwork().updateTileGuiField(this, "heat");
    }

    @Override
    public void setMaxHeat(int newMaxHeat) {
        super.setMaxHeat(newMaxHeat);
        this.getNetwork().updateTileGuiField(this, "maxHeat");
    }

    @Override
    public float addOutput(float energy) {
        float output = super.addOutput(energy);
        this.getNetwork().updateTileGuiField(this, "output");
        return output;
    }

    @Override
    public int addHeat(int amount) {
        int heat = super.addHeat(amount);
        this.getNetwork().updateTileGuiField(this, "heat");
        return heat;
    }
}
