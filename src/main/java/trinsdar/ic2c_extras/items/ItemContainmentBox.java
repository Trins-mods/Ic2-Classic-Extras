package trinsdar.ic2c_extras.items;

import ic2.core.IC2;
import ic2.core.inventory.base.IHandHeldInventory;
import ic2.core.inventory.base.IHasGui;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import trinsdar.ic2c_extras.container.ItemInventoryContainmentBox;

public class ItemContainmentBox extends ItemMiscs implements IHandHeldInventory {
    public ItemContainmentBox() {
        super("containmentbox", 25);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (IC2.platform.isSimulating()) {
            IC2.platform.launchGui(playerIn, this.getInventory(playerIn, handIn, playerIn.getHeldItem(handIn)), handIn);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public IHasGui getInventory(EntityPlayer entityPlayer, EnumHand enumHand, ItemStack itemStack) {
        return new ItemInventoryContainmentBox(entityPlayer, this, itemStack);
    }

    @Override
    public int getGuiId(ItemStack stack) {
        NBTTagCompound nbt = StackUtil.getNbtData(stack);
        if (nbt.hasKey("GuiID")) {
            return nbt.getInteger("GuiID");
        }
        return -1;
    }

    @Override
    public void setGuiID(ItemStack stack, int id) {
        if (id == -1) {
            StackUtil.getOrCreateNbtData(stack).removeTag("GuiID");
            return;
        }
        StackUtil.getOrCreateNbtData(stack).setInteger("GuiID", id);
    }
}
