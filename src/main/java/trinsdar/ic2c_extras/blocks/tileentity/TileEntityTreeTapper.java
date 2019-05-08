package trinsdar.ic2c_extras.blocks.tileentity;

import ic2.api.classic.audio.PositionSpec;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.tile.IMachine;
import ic2.api.crops.CropCard;
import ic2.api.crops.ICropTile;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.resources.BlockRubberWood;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.inventory.transport.wrapper.InventoryWrapper;
import ic2.core.network.NetworkManager;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.registry.Ic2States;
import ic2.core.util.helpers.AabbUtil;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import trinsdar.ic2c_extras.blocks.container.ContainerTreeTapper;
import trinsdar.ic2c_extras.items.itemblocks.ItemBlockMachine;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TileEntityTreeTapper extends TileEntityElecMachine implements ITickable, IHasGui, IMachine {
    public int nextDelay = 10;
    public int delay = 0;
    public int radius = 1;
    public TileEntityTreeTapper() {
        super(13, 128);
        this.maxEnergy = 50000;
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerTreeTapper(player.inventory,this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer entityPlayer) {
        return GuiComponentContainer.class;
    }

    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.treeTapper;
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.treeTapper;
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {

    }

    @Override
    public double getWrenchDropRate() {
        return 1.0D;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public boolean hasGui(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void update() {
        World worldIn = this.getWorld();
        this.updateNeighbors();
        if (this.delay > 0) {
            --this.delay;
        } else if (!this.hasEnergy(100)) {
            this.delay += 20;
        } else if (isInventoryFull()){
            this.delay += 20;
        }else {
            this.delay += this.nextDelay;
            for (BlockPos additionalPos : getTargetBlocks(this.getPos())) {
                IBlockState state = worldIn.getBlockState(additionalPos);
                ItemStack stack = StackUtil.copyWithSize(Ic2Items.stickyResin, 1 + worldIn.rand.nextInt(3));
                if (state.getBlock() == Ic2States.rubberWood.getBlock()) {
                    boolean server = IC2.platform.isSimulating();
                    if (server && attemptExtract(worldIn, additionalPos, true) && !isInventoryFull() && canAddItems(stack)) {
                        attemptExtract(worldIn, additionalPos, false);
                        addItemsToInventory(stack);
                        useEnergy(20);
                    } else if (isInventoryFull()){
                        break;
                    }
                }
            }
            useEnergy(100);
        }
    }

    public boolean isInventoryFull(){
        int fullSlotCount = 0;
        for (int i = 0; i < 9; i++){
            if (this.inventory.get(i).getCount() == 64){
                fullSlotCount += 1;
            }
        }
        if (fullSlotCount == 9){
            return true;
        }
        return false;
    }

    public boolean canAddItems(ItemStack stack){
        for (int i = 0; i < 9; i++){
            if (this.inventory.get(i).isEmpty() || this.getStackInSlot(0).getCount() + stack.getCount() <= 64){
                return true;
            }
        }
        return false;
    }

    public static boolean attemptExtract(World world, BlockPos pos, boolean simulate) {
        IBlockState state = world.getBlockState(pos);
        if (state.getValue(BlockRubberWood.resin)) {
            boolean server = IC2.platform.isSimulating();
            if (state.getValue(BlockRubberWood.collectable)) {
                if (server) {
                    if (!simulate){
                        world.setBlockState(pos, state.withProperty(BlockRubberWood.collectable, false));
                        world.scheduleUpdate(pos, state.getBlock(), 100);
                        (IC2.network.get(server)).announceBlockUpdate(world, pos);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public Set<BlockPos> getTargetBlocks(BlockPos pos) {
        Set<BlockPos> targetBlocks = new LinkedHashSet<BlockPos>();
        for (int y = 0; y < 8; y++){
            for (int x = -radius; x < radius + 1; x++) {
                for (int z = -radius; z < radius + 1; z++) {
                    BlockPos newPos = pos.add(x, y, z);
                    targetBlocks.add(newPos);
                }
            }
        }
        return targetBlocks;
    }

    public void addItemsToInventory(ItemStack stack) {
        for (int i = 0; i < 9; i++){
            if (canAddItems(stack)){
                int count = this.getStackInSlot(i).getCount() + stack.getCount();
                this.setStackInSlot(i, StackUtil.copyWithSize(Ic2Items.stickyResin, count));
                break;
            }
        }
    }

    public void onLoaded() {
        super.onLoaded();
        if (this.isSimulating()) {
            this.setOverclockerUpgrade();
        }

    }

    public void setOverclockerUpgrade() {
        int overclocker = 0;
        int newRange = 1;

        for(int i = 0; i < 4; ++i) {
            ItemStack stack = inventory.get(i + inventory.size() - 4);
            if (StackUtil.isStackEqual(stack, Ic2Items.overClockerUpgrade)) {
                ++overclocker;
            } else if (StackUtil.isStackEqual(stack, Ic2Items.padUpgradeBasicFieldUpgrade)) {
                ++newRange;
            } else if (StackUtil.isStackEqual(stack, Ic2Items.padUpgradeFieldUpgrade)) {
                newRange += 2;
            } else if (StackUtil.isStackEqual(stack, Ic2Items.padUpgradeAdvFieldUpgrade)) {
                newRange += 3;
            }
        }

        if (newRange > 5) {
            newRange = 5;
        }

        this.radius = newRange;
        if (overclocker < 1) {
            this.nextDelay = 20;
        } else if (overclocker < 2) {
            this.nextDelay = 10;
        } else if (overclocker < 3) {
            this.nextDelay = 5;
        } else if (overclocker < 4) {
            this.nextDelay = 2;
        } else if (overclocker >= 4) {
            this.nextDelay = 0;
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.delay = nbt.getInteger("Delay");
        this.radius = nbt.getInteger("Radius");
        this.nextDelay = nbt.getInteger("NextDelay");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Delay", this.delay);
        nbt.setInteger("Radius", this.radius);
        nbt.setInteger("NextDelay", this.nextDelay);
        return nbt;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public boolean useEnergy(double amount, boolean simulate) {
        if (energy < amount) {
            return false;
        }
        if (!simulate) {
            useEnergy((int) amount);
        }
        return true;
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
        return false;
    }

    @Override
    public boolean isValidInput(ItemStack itemStack) {
        return false;
    }

    @Override
    public Set<IMachineUpgradeItem.UpgradeType> getSupportedTypes() {
        return new LinkedHashSet(Arrays.asList(IMachineUpgradeItem.UpgradeType.values()));
    }

    @Override
    public World getMachineWorld() {
        return getWorld();
    }

    @Override
    public BlockPos getMachinePos() {
        return getPos();
    }
}
