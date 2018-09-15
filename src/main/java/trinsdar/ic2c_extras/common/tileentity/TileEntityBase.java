package trinsdar.ic2c_extras.common.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.IRecipeMachine;
import ic2.api.classic.tile.MachineType;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.info.misc.IEnergyUser;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidTank;
import trinsdar.ic2c_extras.Ic2cExtras;

import java.util.Set;

public class TileEntityBase extends TileEntityElecMachine implements ITickable, IProgressMachine, IRecipeMachine, IOutputMachine, IHasGui, INetworkTileEntityEventListener, IEnergyUser
{

    public IFluidTank tank;

    public TileEntityBase(int slots, int energyConsumption, int maxProgress, int maxinput)
    {
        super(slots, maxinput);
    }

    @Override
    public double getEnergy()
    {
        return 0;
    }

    @Override
    public boolean useEnergy(double v, boolean b)
    {
        return false;
    }

    @Override
    public void setRedstoneSensitive(boolean b)
    {

    }

    @Override
    public boolean isRedstoneSensitive()
    {
        return false;
    }

    @Override
    public boolean isProcessing()
    {
        return false;
    }

    @Override
    public boolean isValidInput(ItemStack itemStack)
    {
        return false;
    }

    @Override
    public Set<IMachineUpgradeItem.UpgradeType> getSupportedTypes()
    {
        return null;
    }

    @Override
    public World getMachineWorld()
    {
        return this.getWorld();
    }

    @Override
    public BlockPos getMachinePos()
    {
        return this.getPos();
    }

    @Override
    public IMachineRecipeList getRecipeList()
    {
        return null;
    }

    @Override
    public MachineType getType()
    {
        return null;
    }

    @Override
    public float getProgress()
    {
        return 0;
    }

    @Override
    public float getMaxProgress()
    {
        return 0;
    }

    @Override
    public void onNetworkEvent(int i)
    {

    }

    @Override
    public boolean supportsNotify()
    {
        return false;
    }

    @Override
    public int getEnergyUsage()
    {
        return 0;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer entityPlayer)
    {
        return null;
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer entityPlayer)
    {
        return null;
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer)
    {

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return false;
    }

    @Override
    public boolean hasGui(EntityPlayer entityPlayer)
    {
        return false;
    }

    @Override
    public IHasInventory getOutputInventory()
    {
        return null;
    }

    @Override
    public IHasInventory getInputInventory()
    {
        return null;
    }

    @Override
    public void update()
    {

    }

    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }

    public ResourceLocation getGuiTexture()
    {
        return new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guiorewashingplant.png");
    }
}
