package trinsdar.ic2c_extras.tileentity;

import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.machine.low.logic.crafter.CraftingRecipe;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.container.ContainerAutocraftingTable;
import trinsdar.ic2c_extras.util.StackHelper;

import java.util.ArrayList;
import java.util.List;

public class TileEntityAutocraftingTable extends TileEntityElecMachine implements ITickable, IHasGui {

    public static final ResourceLocation GUI_LOCATION = new ResourceLocation(IC2CExtras.MODID, "textures/gui/autocraftingtable.png");
    protected static final int[] slotInputs = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    protected static final int[] slotContainer = {10, 11, 12, 13, 14, 15, 16, 17, 18};
    protected static final int[] slotOutputs = {19};
    public List<ItemStack> currentRecipe = new ArrayList<>();
    public CraftingRecipe craftingThingy = new CraftingRecipe();

    public TileEntityAutocraftingTable() {
        super(29, 32);
        this.maxEnergy = 10000;
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Import, slotInputs);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotOutputs);
        handler.registerDefaultSlotAccess(AccessRule.Export, slotContainer);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), slotInputs);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), slotOutputs);
        handler.registerDefaultSlotsForSide(RotationList.DOWN, slotContainer);
        handler.registerInputFilter(CommonFilters.Anything, slotInputs);
        handler.registerSlotType(SlotType.Input, slotInputs);
        handler.registerSlotType(SlotType.Output, slotOutputs);
        handler.registerSlotType(SlotType.Output, slotContainer);
    }

    @Override
    public List<ItemStack> getDrops() {
        List<ItemStack> list = new ArrayList(this.inventory.size());

        for(int i = 0; i < this.inventory.size(); ++i) {
            ItemStack stack = this.inventory.get(i);
            if (!stack.isEmpty() && !within(i, 18, 27)) {
                list.add(stack);
            }
        }

        return list;
    }

    public boolean within(int value, int low, int high) {
        return value >= low && value <= high;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList list = nbt.getTagList("currentRecipe", 10);
        this.currentRecipe.clear();
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound data = list.getCompoundTagAt(i);
            this.currentRecipe.add(new ItemStack(data));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.currentRecipe.size(); ++i) {
            NBTTagCompound data = new NBTTagCompound();
            this.currentRecipe.get(i).writeToNBT(data);
            list.appendTag(data);
        }
        nbt.setTag("currentRecipe", list);
        return nbt;
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return !this.isInvalid();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return GuiComponentContainer.class;
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerAutocraftingTable(player.inventory, this);
    }

    @Override
    public boolean hasGui(EntityPlayer player) {
        return true;
    }

    @Override
    public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
        return facing != getFacing() && facing.getAxis().isHorizontal();
    }

    @Override
    public void onGuiClosed(EntityPlayer arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update() {
        if (world.getTotalWorldTime() % 20 == 0) {
            tryImportItems(this.getFacing().getOpposite());
            ItemStack mStack = this.getStackInSlot(27);
            if (!this.currentRecipe.isEmpty() && !mStack.isEmpty()
                    && StackHelper.canMerge(mStack, this.inventory.get(28)) && this.energy >= 50) {
                // see if the tile inventory has what the currentRecipe needs
                StackHelper.tryCondenseInventory(this, 0, 9);
                int recipeMatches = 0;
                for (int i = 0; i < 9; ++i) {
                    if (StackHelper.containsWithSizeFuzzy(this.currentRecipe, this.getStackInSlot(i)) != -1) {
                        recipeMatches++;
                    }
                }
                // enough matches found
                if (recipeMatches >= this.currentRecipe.size()) {
                    int tooMatch = recipeMatches;
                    for (int i = 0; i < 9 && tooMatch > 0; ++i) {
                        int currentIndex = StackHelper.containsWithSizeFuzzy(this.currentRecipe, this.getStackInSlot(i));
                        if (currentIndex != -1) {
                            ItemStack matchedSlot = this.getStackInSlot(i);
                            tryToDamageItems(matchedSlot);
                            // checking if the damage didnt void the item
                            if (!matchedSlot.isEmpty()) {
                                if (tryToShiftContainerItems(matchedSlot)){
                                    matchedSlot.shrink(this.currentRecipe.get(currentIndex).getCount());
                                }
                                tooMatch--;
                            }
                        }
                    }
                    // if all matching stacks have been subtracted
                    if (tooMatch == 0) {
                        int oldCount = this.getStackInSlot(28).getCount();
                        this.setStackInSlot(28, StackUtil.copyWithSize(mStack.copy(), oldCount
                                + mStack.getCount()));
                        this.useEnergy(50);
                    }
                }
                tryExportItems(this.getFacing(), 28);
            }
        }
    }

    public void tryToDamageItems(ItemStack stack) {
        if (stack.isItemStackDamageable()) {
            if (stack.attemptDamageItem(1, this.world.rand, null)){
                stack.shrink(1);
            }
        }
    }

    public boolean tryToShiftContainerItems(ItemStack container) {
        if (container.getItem().hasContainerItem(container)) {
            ItemStack cItem = container.getItem().getContainerItem(container);
            for (int i = 9; i < 18; ++i) {
                if (StackHelper.canMerge(cItem, this.getStackInSlot(i))) {
                    int oldCount = this.getStackInSlot(i).getCount();
                    this.setStackInSlot(i, StackUtil.copyWithSize(cItem, oldCount + cItem.getCount()));
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @SuppressWarnings("static-access")
    public void tryImportItems(EnumFacing side) {
        BlockPos importPos = this.getPos().offset(side);
        if (!this.getWorld().isBlockLoaded(importPos)) {
            return;
        }
        IItemTransporter slave = TransporterManager.manager.getTransporter(this.getWorld().getTileEntity(importPos), true);
        IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
        if (slave != null && controller != null) {
            int limit = controller.getSizeInventory(side);
            for (int i = 0; i < limit; ++i) {
                ItemStack stack = slave.removeItem(CommonFilters.Anything, side.getOpposite(), 1, false);
                if (stack.isEmpty()) {
                    break;
                }
                ItemStack added = controller.addItem(stack, side, true);
                if (added.getCount() <= 0) {
                    break;
                }
                slave.removeItem(new BetterBasicItemFilter(added), side.getOpposite(), 1, true);
            }
        }
    }

    @SuppressWarnings("static-access")
    public void tryExportItems(EnumFacing side, int... slots) {
        BlockPos exportPos = this.getPos().offset(side);
        if (!this.getWorld().isBlockLoaded(exportPos)) {
            return;
        }
        IItemTransporter slave = TransporterManager.manager.getTransporter(this.getWorld().getTileEntity(exportPos), false);
        if (slave != null) {
            for (int i : slots) {
                int added = slave.addItem(this.getStackInSlot(i).copy(), side.getOpposite(), true).getCount();
                if (added > 0) {
                    this.getStackInSlot(i).shrink(added);
                    break;
                }
            }
        }
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public boolean canRemoveBlock(EntityPlayer player) {
        return true;
    }

    public ResourceLocation getGuiTexture() {
        return GUI_LOCATION;
    }

    public static class BetterBasicItemFilter implements IFilter {

        ItemStack item;

        public BetterBasicItemFilter(Item item) {
            this(new ItemStack(item));
        }

        public BetterBasicItemFilter(Block block) {
            this(new ItemStack(block));
        }

        public BetterBasicItemFilter(ItemStack par1) {
            this.item = par1;
        }

        public boolean matches(ItemStack stack) {
            return StackUtil.isStackEqual(this.item.copy(), stack, false, false);
        }
    }
}
