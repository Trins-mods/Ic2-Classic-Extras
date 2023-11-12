package trinsdar.ic2c_extras.item;

import ic2.core.IC2;
import ic2.core.inventory.base.IHasHeldSlotInventory;
import ic2.core.inventory.base.IPortableInventory;
import ic2.core.item.base.PropertiesBuilder;
import ic2.core.item.base.features.IScrollableInventory;
import ic2.core.platform.player.KeyHelper;
import ic2.core.utils.tooltips.ILangHelper;
import ic2.core.utils.tooltips.ToolTipHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import trinsdar.ic2c_extras.container.ContainmentBoxInventory;

public class ItemContainmentBox extends ItemBasic implements IHasHeldSlotInventory, ILangHelper{
    public ItemContainmentBox() {
        super("containment_box", "misc", new PropertiesBuilder().maxStackSize(1));

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addToolTip(ItemStack stack, Player player, TooltipFlag type, ToolTipHelper helper) {
        helper.addKeybindingTooltip(this.buildKeyDescription(KeyHelper.RIGHT_CLICK, "tooltip.ic2.open_item_inventory", new Object[0]));
    }

    @Override
    public IPortableInventory getInventory(Player player, ItemStack itemStack, Slot slot) {
        return new ContainmentBoxInventory(player, this, itemStack, slot);
    }

    @Override
    public IPortableInventory getInventory(Player player, InteractionHand interactionHand, ItemStack itemStack) {
        return new ContainmentBoxInventory(player, this, itemStack, null);
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (IC2.PLATFORM.isSimulating()) {
            IC2.PLATFORM.launchGui(playerIn, handIn, Direction.NORTH, this.getInventory(playerIn, handIn, stack));
        }

        return InteractionResultHolder.success(stack);
    }
}
