package trinsdar.ic2c_extras.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.fluid.FluidItemStackHandler;
import trinsdar.ic2c_extras.util.Registry;
import trinsdar.ic2c_extras.util.fluidcell.CustomModelLoader;
import trinsdar.ic2c_extras.util.fluidcell.ModelFluidCell;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemUniversalFluidCell extends Item {
    private final int size = Fluid.BUCKET_VOLUME;
    private final ItemStack empty = new ItemStack(this);
    public ModelResourceLocation[] model = new ModelResourceLocation[2];
    public ItemUniversalFluidCell(){
        this.setMaxStackSize(16);
        this.setRegistryName("universal_fluid_cell");
        this.setUnlocalizedName("universalFluidCell");
        this.setCreativeTab(IC2CExtras.creativeTab);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(getFluidName(stack));
        if (isFluidGas(stack)) {
            tooltip.add(TextFormatting.GREEN + I18n.format("Gaseous"));
        }
        if (isFluidPlaceable(stack)) {
            tooltip.add(TextFormatting.YELLOW + I18n.format("Can be placed in world"));
        }
    }

    // helper for fluid containing itemstacks
    public static String getFluidName(ItemStack stack) {
        FluidStack fluid = FluidUtil.getFluidContained(stack);
        if (fluid != null) {
            return fluid.amount + "mB of " + fluid.getLocalizedName();
        }
        return "Empty";
    }

    // helper for fluid containing itemstacks
    public static Boolean isFluidPlaceable(ItemStack stack) {
        FluidStack fluid = FluidUtil.getFluidContained(stack);
        return fluid != null && fluid.getFluid().canBePlacedInWorld();
    }

    // helper for fluid containing itemstacks
    public static Boolean isFluidGas(ItemStack stack) {
        FluidStack fluid = FluidUtil.getFluidContained(stack);
        return fluid != null && fluid.getFluid().isGaseous();
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new FluidItemStackHandler(stack, stack, size);
    }

    @Override
    public void getSubItems(@Nullable final CreativeTabs tab, final NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            subItems.add(empty);
            for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
                subItems.add(getFilledCell(fluid.getName(), 1));
            }
        }
    }

    public ItemStack getFilledCell(String name, int count) {
        FluidStack fluid = FluidRegistry.getFluidStack(name, 1000);
        ItemStack stack = empty.copy();
        IFluidHandlerItem handler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        handler.fill(fluid, true);
        stack.setCount(count);
        return stack;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, @Nonnull EntityPlayer player,
                                                    @Nonnull EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        FluidStack fluidStack = FluidUtil.getFluidContained(itemstack);
        if (fluidStack == null) {
            return tryPickUpFluid(world, player, hand, itemstack, fluidStack);
        }
        return tryPlaceFluid(world, player, hand, itemstack, fluidStack);
    }

    public ActionResult<ItemStack> tryPickUpFluid(@Nonnull World world, @Nonnull EntityPlayer player,
                                                  @Nonnull EnumHand hand, ItemStack itemstack, FluidStack fluidStack) {
        RayTraceResult mop = this.rayTrace(world, player, true);
        ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(player, world, itemstack, mop);
        if (ret != null)
            return ret;
        if (mop == null) {
            return ActionResult.newResult(EnumActionResult.PASS, itemstack);
        }
        BlockPos clickPos = mop.getBlockPos();
        if (world.isBlockModifiable(player, clickPos)) {
            FluidActionResult result = FluidUtil.tryPickUpFluid(itemstack, player, world, clickPos, mop.sideHit);
            if (result.isSuccess()) {
                ItemHandlerHelper.giveItemToPlayer(player, result.getResult());
                itemstack.shrink(1);
                return ActionResult.newResult(EnumActionResult.SUCCESS, itemstack);
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, itemstack);
    }

    public ActionResult<ItemStack> tryPlaceFluid(@Nonnull World world, @Nonnull EntityPlayer player,
                                                 @Nonnull EnumHand hand, ItemStack itemstack, FluidStack fluidStack) {
        RayTraceResult mop = this.rayTrace(world, player, false);
        ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(player, world, itemstack, mop);
        if (ret != null)
            return ret;
        if (mop == null || mop.typeOfHit != RayTraceResult.Type.BLOCK) {
            return ActionResult.newResult(EnumActionResult.PASS, itemstack);
        }
        BlockPos clickPos = mop.getBlockPos();
        if (world.isBlockModifiable(player, clickPos)) {
            BlockPos targetPos = clickPos.offset(mop.sideHit);
            if (player.canPlayerEdit(targetPos, mop.sideHit, itemstack)) {
                FluidActionResult result = FluidUtil.tryPlaceFluid(player, world, targetPos, itemstack, fluidStack);
                if (result.isSuccess() && !player.capabilities.isCreativeMode) {
                    player.addStat(StatList.getObjectUseStats(this));
                    itemstack.shrink(1);
                    ItemStack emptyStack = new ItemStack(Registry.universalFluidCell);
                    if (itemstack.isEmpty()) {
                        return ActionResult.newResult(EnumActionResult.SUCCESS, emptyStack);
                    } else {
                        ItemHandlerHelper.giveItemToPlayer(player, emptyStack);
                        return ActionResult.newResult(EnumActionResult.SUCCESS, itemstack);
                    }
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, itemstack);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        CustomModelLoader.register("universal_fluid_cell", new ModelFluidCell());
    }
}
