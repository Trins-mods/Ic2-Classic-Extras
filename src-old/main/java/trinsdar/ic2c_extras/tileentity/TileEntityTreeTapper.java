package trinsdar.ic2c_extras.tileentity;

import gtclassic.api.interfaces.IGTDebuggableTile;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.tile.IMachine;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.resources.BlockRubberWood;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2States;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import trinsdar.ic2c_extras.container.ContainerTreeTapper;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Optional.Interface(iface = "gtclassic.api.interfaces.IGTDebuggableTile", modid = "gtclassic", striprefs = true)
public class TileEntityTreeTapper extends TileEntityElecMachine implements ITickable, IHasGui, IMachine, IGTDebuggableTile {
    public int nextDelay = 10;
    public int delay = 0;
    public int radius = 1;

    public TileEntityTreeTapper() {
        super(13, 128);
        this.maxEnergy = 50000;
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Export, 0, 1, 2, 3, 4, 5, 6, 7, 8);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), 0, 1, 2, 3, 4, 5, 6, 7, 8);
        handler.registerSlotType(SlotType.Output, 0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerTreeTapper(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer entityPlayer) {
        return GuiComponentContainer.class;
    }

    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.TREE_TAPPER;
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.TREE_TAPPER;
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
        } else if (isInventoryFull()) {
            this.delay += 20;
        } else {
            this.delay += this.nextDelay;
            this.setActive(true);
            for (BlockPos additionalPos : getTargetBlocks(this.getPos())) {
                ItemStack stack = StackUtil.copyWithSize(Ic2Items.stickyResin, 1 + worldIn.rand.nextInt(3));
                boolean server = IC2.platform.isSimulating();
                if (server && attemptExtract(worldIn, additionalPos, true) && !isInventoryFull() && addItemsToInventory(stack, true)) {
                    attemptExtract(worldIn, additionalPos, false);
                    addItemsToInventory(stack, false);
                    useEnergy(20);
                } else if (isInventoryFull()) {
                    break;
                }
            }
            this.setActive(false);
            useEnergy(100);
        }
    }

    public boolean isInventoryFull() {
        int fullSlotCount = 0;
        for (int i = 0; i < 9; i++) {
            if (this.inventory.get(i).getCount() == 64) {
                fullSlotCount += 1;
            }
        }
        if (fullSlotCount == 9) {
            return true;
        }
        return false;
    }

    public static boolean attemptExtract(World world, BlockPos pos, boolean simulate) {
        IBlockState state = world.getBlockState(pos);
        if (state.getValue(BlockRubberWood.resin)) {
            boolean server = IC2.platform.isSimulating();
            if (state.getValue(BlockRubberWood.collectable)) {
                if (server) {
                    if (!simulate) {
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
        for (int y = 0; y < 8; y++) {
            for (int x = -radius; x < radius + 1; x++) {
                for (int z = -radius; z < radius + 1; z++) {
                    BlockPos newPos = pos.add(x, y, z);
                    IBlockState state = this.getWorld().getBlockState(newPos);
                    if (state.getBlock() == Ic2States.rubberWood.getBlock()) {
                        targetBlocks.add(newPos);
                    }
                }
            }
        }
        return targetBlocks;
    }

    public boolean addItemsToInventory(ItemStack stack, boolean simulate) {
        for (int i = 0; i < 9; i++) {
            if (this.inventory.get(i).isEmpty() || this.getStackInSlot(i).getCount() + stack.getCount() <= 67) {
                if (!simulate) {
                    int count = this.getStackInSlot(i).getCount() + stack.getCount();
                    this.setStackInSlot(i, StackUtil.copyWithSize(Ic2Items.stickyResin, count));
                }
                return true;
            }
        }
        return false;
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

        for (int i = 0; i < 4; ++i) {
            ItemStack stack = inventory.get(i + inventory.size() - 4);
            if (StackUtil.isStackEqual(stack, Ic2Items.overClockerUpgrade)) {
                overclocker += 1;
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

    @Optional.Method(modid = "gtclassic")
    @Override
    public void getData(Map<String, Boolean> map) {
        map.put("Radius: " + radius + " Blocks", true);
        map.put("Tick Delay between runs: " + nextDelay + " Ticks", true);
        map.put("Ticks till next run: " + delay, true);
    }
}
