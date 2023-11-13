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
import ic2.core.fluid.IC2Tank;
import ic2.core.fluid.InsertionTank;
import ic2.core.inventory.container.IC2Container;
import ic2.core.inventory.filter.SpecialFilters;
import ic2.core.inventory.filter.special.ElectricItemFilter;
import ic2.core.inventory.filter.special.MachineFilter;
import ic2.core.inventory.handler.AccessRule;
import ic2.core.inventory.handler.InventoryHandler;
import ic2.core.inventory.handler.SlotType;
import ic2.core.utils.helpers.FluidHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.ic2c_extras.container.ContainerOreWashingPlant;
import trinsdar.ic2c_extras.init.IC2CExtrasSounds;
import trinsdar.ic2c_extras.init.ModBlocks;
import trinsdar.ic2c_extras.recipes.MachineRecipes;

import java.util.function.Consumer;

public class TileEntityOreWashingPlant extends TileEntityMultiOutput implements Consumer<IC2Tank>, IClickable, IFluidMachine {
    @NetworkInfo
    private InsertionTank waterTank = new InsertionTank(16000, fluid -> {
        return fluid.getFluid() == Fluids.WATER;
    });

    ICache<IFluidHandler> fluidCache;
    public TileEntityOreWashingPlant(BlockPos pos, BlockState state) {
        super(pos, state,7, 8, 400, 32);
        fluidCache = new CapabilityCache<>(this, DirectionList.ALL, ForgeCapabilities.FLUID_HANDLER);
        this.waterTank.addListener(this);
        this.addGuiFields("waterTank");
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.ORE_WASHING_PLANT_TYPE;
    }

    public IC2Tank getWaterTank() {
        return this.waterTank;
    }


    @Override
    protected void addSlotInfo(InventoryHandler handler) {
        handler.registerBlockSides(DirectionList.ALL);
        handler.registerBlockAccess(DirectionList.ALL, AccessRule.BOTH);
        handler.registerSlotAccess(AccessRule.BOTH, 0);
        handler.registerSlotAccess(AccessRule.IMPORT, 1);
        handler.registerSlotAccess(AccessRule.EXPORT, 2, 3, 4);
        handler.registerSlotsForSide(DirectionList.DOWN.invert(), 1);
        handler.registerSlotsForSide(DirectionList.UP.invert(), 0, 2, 3, 4);
        handler.registerInputFilter(SpecialFilters.createChargeFilter(), 0);
        handler.registerOutputFilter(ElectricItemFilter.NOT_DISCHARGE_FILTER, 0);
        handler.registerInputFilter(new MachineFilter(this), 1);
        handler.registerInputFilter(SpecialFilters.ALWAYS_FALSE, 2, 3, 4);
        handler.registerNamedSlot(SlotType.BATTERY, 0);
        handler.registerNamedSlot(SlotType.INPUT, 1);
        handler.registerNamedSlot(SlotType.OUTPUT, 2, 3, 4);
    }


    @Override
    protected ResourceLocation getWorkingSound() {
        return IC2CExtrasSounds.ORE_WASHING_PLANT_OP;
    }

    /*@Override
    public void update() {
        if (!this.inventory.get(slotInputTank).isEmpty()) {
            StackHelper.doFluidContainerThings(this, this.waterTank, slotInputTank, slotOutputTank);
        }
        super.update();
    }*/

    @Override
    public boolean canProcess() {
        return super.canProcess() && waterTank.getFluidAmount() >= 1000;
    }

    @Override
    protected RecipeResult isRecipeStillValid(int slot, IMachineRecipeList.RecipeEntry entry) {
        if (waterTank.getFluidAmount() >= getRequiredWater(entry.getOutput())){
            return RecipeResult.SUCCESS;
        }
        return RecipeResult.PASS;
    }

    @Override
    public void operateOnce(int slot, IInput[] input, IRecipeOutput output, CompoundTag recipeData) {
        super.operateOnce(slot, input, output, recipeData);
        this.waterTank.drainInternally(getRequiredWater(output), IFluidHandler.FluidAction.EXECUTE);
    }

    /*@Override
    public void operateOnce(IRecipeInput input, MachineOutput output, List<IStackOutput> list) {
        List<ItemStack> result = output.getRecipeOutput(getWorld().rand, getTileData());
        for (int i = 0; i < result.size(); i++) {
            list.add(new SimpleStackOutput(result.get(i), slotOutput + (i % 3)));
        }
        consumeInput(input, output.getMetadata());
        this.waterTank.drain(getRequiredWater(output), true);
    }*/

    @Override
    public IC2Container createContainer(Player player, InteractionHand hand, Direction side, int windowID) {
        return new ContainerOreWashingPlant(this, player, windowID);
    }

    @Override
    public void accept(IC2Tank tank) {
        this.updateGuiField("waterTank");
        this.addToTick();
        this.rebuildCache |= !this.isOperating();
    }

    @Override
    public ResourceLocation getTexture() {
        return ContainerOreWashingPlant.GUI_TEXTURE;
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
    public BlockEntityType<?> getType() {
        return ModBlocks.ORE_WASHING_PLANT_TYPE;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return MachineRecipes.ORE_WASHING_PLANT;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return super.getCapability(cap, side);
    }

    public static int getRequiredWater(IRecipeOutput output) {
        if (output == null) {
            return 1000;
        }
        return output.getMetadata().getInt("amount");
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