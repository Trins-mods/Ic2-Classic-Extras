package trinsdar.ic2c_extras.blockentity;

import ic2.api.network.buffer.NetworkInfo;
import ic2.api.recipes.ingridients.inputs.IInput;
import ic2.api.recipes.ingridients.recipes.IRecipeOutput;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.api.tiles.IFluidMachine;
import ic2.api.util.DirectionList;
import ic2.core.block.base.cache.CapabilityCache;
import ic2.core.block.base.cache.ICache;
import ic2.core.block.base.features.IClickable;
import ic2.core.block.base.tiles.impls.machine.single.BaseAdvMachineTileEntity;
import ic2.core.block.machines.containers.mv.AdvMachineContainer;
import ic2.core.block.machines.tiles.mv.CentrifugalExtractorTileEntity;
import ic2.core.block.machines.tiles.mv.InductionFurnaceTileEntity;
import ic2.core.fluid.InsertionTank;
import ic2.core.inventory.gui.components.simple.SpeedComponent;
import ic2.core.inventory.gui.components.simple.TankComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.inventory.slot.XPSlot;
import ic2.core.utils.helpers.FluidHelper;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.init.IC2CExtrasSounds;
import trinsdar.ic2c_extras.init.ModBlocks;
import trinsdar.ic2c_extras.recipes.MachineRecipes;

import static trinsdar.ic2c_extras.blockentity.TileEntityOreWashingPlant.getRequiredWater;

public class TileEntityThermalWasher extends BaseAdvMachineTileEntity implements IClickable, IFluidMachine {

    public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IC2CExtras.MODID, "textures/gui/thermal_washer.png");
    @NetworkInfo
    private InsertionTank waterTank = new InsertionTank(16000, fluid -> {
        return fluid.getFluid() == Fluids.WATER;
    });

    ICache<IFluidHandler> fluidCache;
    public TileEntityThermalWasher(BlockPos pos, BlockState state) {
        super(pos, state, 5, 16, 4000);
        fluidCache = new CapabilityCache<>(this, DirectionList.ALL, ForgeCapabilities.FLUID_HANDLER);
        this.addCaches(fluidCache);
        this.addCapability(ForgeCapabilities.FLUID_HANDLER, waterTank);
        this.waterTank.addListener(tank -> {
            this.updateGuiField("waterTank");
            this.addToTick();
            this.rebuildCache |= !this.isOperating();
        });
        this.addGuiFields("waterTank");
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{2,3,4};
    }

    @Override
    public Slot[] addSlots(Player player) {
        return new Slot[]{
                FilterSlot.createDischargeSlot(this, this.tier, 0, 56, 53),
                new FilterSlot(this, 1, 56, 17, (T) -> {
                    return getRecipeList().getRecipe(T, false) != null;
                }),
                new XPSlot(this, 2, 111, 17),
                new XPSlot(this, 3, 111, 35),
                new XPSlot(this, 4, 111, 53)
        };
    }

    @Override
    public void addComponents(AdvMachineContainer container) {
        container.getComponents().remove(0);
        container.addComponent(new SpeedComponent(this, getSpeedName(), new Vec2i(80, 53)));
        container.addComponent(new TankComponent(new Box2i(32, 13, 16, 58), new Vec2i(176, 133), waterTank).setTankName("Water Tank"));
    }

    @Override
    public Component getSpeedName() {
        return InductionFurnaceTileEntity.HEAT;
    }

    @Override
    public ResourceLocation getTexture() {
        return GUI_TEXTURE;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.THERMAL_WASHER_TYPE;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return MachineRecipes.ORE_WASHING_PLANT;
    }

    @Override
    protected ResourceLocation getWorkingSound() {
        return IC2CExtrasSounds.ORE_WASHING_PLANT_OP;
    }

    @Override
    protected RecipeResult isRecipeStillValid(int slot, IMachineRecipeList.RecipeEntry entry) {
        if (waterTank.getFluidAmount() >= getRequiredWater(entry.getOutput())){
            return RecipeResult.IGNORE;
        }
        return RecipeResult.PASS;
    }

    @Override
    public void operateOnce(int slot, IInput[] input, IRecipeOutput output, CompoundTag recipeData) {
        super.operateOnce(slot, input, output, recipeData);
        this.waterTank.drainInternally(getRequiredWater(output), IFluidHandler.FluidAction.EXECUTE);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.waterTank.readFromNBT(compound.getCompound("Tank"));
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("Tank", this.waterTank.writeToNBT(new CompoundTag()));
    }

    @Override
    public IFluidHandler getConnectedTank(Direction direction) {
        return fluidCache.getHandler(direction);
    }

    @Override
    public boolean onRightClick(Player player, InteractionHand interactionHand, Direction direction, BlockHitResult blockHitResult) {
        ItemStack stack = player.getItemInHand(interactionHand);
        return !stack.isEmpty() && FluidHelper.drainContainers(stack, player, this.waterTank);
    }
}
