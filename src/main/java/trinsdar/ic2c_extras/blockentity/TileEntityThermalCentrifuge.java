package trinsdar.ic2c_extras.blockentity;

import ic2.api.network.buffer.NetworkInfo;
import ic2.api.recipes.ingridients.recipes.IRecipeOutput;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.api.util.DirectionList;
import ic2.core.block.base.tiles.impls.machine.single.BasicMachineTileEntity;
import ic2.core.inventory.container.IC2Container;
import ic2.core.inventory.filter.SpecialFilters;
import ic2.core.inventory.filter.special.ElectricItemFilter;
import ic2.core.inventory.filter.special.MachineFilter;
import ic2.core.inventory.handler.AccessRule;
import ic2.core.inventory.handler.InventoryHandler;
import ic2.core.inventory.handler.SlotType;
import ic2.core.platform.registries.IC2Sounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import trinsdar.ic2c_extras.container.ContainerThermalCentrifuge;
import trinsdar.ic2c_extras.init.ModBlocks;
import trinsdar.ic2c_extras.recipes.MachineRecipes;


public class TileEntityThermalCentrifuge extends TileEntityMultiOutput {
    @NetworkInfo
    public int maxHeat = 500;
    @NetworkInfo
    public int heat;

    private static final int defaultEu = 48;

    public static final String neededHeat = "minHeat";

    public TileEntityThermalCentrifuge(BlockPos pos, BlockState state) {
        super(pos, state,5, 2, 48, 200, 128);
        this.addGuiFields("heat", "maxHeat");
    }

    public float getHeat() {
        return (float) this.heat;
    }

    public float getMaxHeat() {
        if (lastRecipe == null){
            return 500;
        }
        return (float) getRequiredHeat(lastRecipe.getOutput());
    }

    /*@Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return thermalCentrifuge.getRecipeInAndOutput(input, false);
    }*/

    @Override
    public ResourceLocation getTexture() {
        return ContainerThermalCentrifuge.GUI_TEXTURE;
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

    /*@Override
    public boolean isValidInput(ItemStack par1) {
        return super.isValidInput(par1) && isRecipeInputValid(par1);
    }

    public boolean isRecipeInputValid(ItemStack stack) {
        IRecipeInput input = Ic2cExtrasRecipes.thermalCentrifugeValidInputs.get(new CompareableStack(stack));
        if (input == null) {
            return false;
        }
        return input.matches(stack);
    }*/

    @Override
    public ResourceLocation getWorkingSound() {
        return IC2Sounds.EXTRACTOR_PROCESSING;
    }

    @Override
    public IC2Container createContainer(Player player, InteractionHand hand, Direction side, int windowID) {
        return new ContainerThermalCentrifuge(this, player, windowID);
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return MachineRecipes.THERMAL_CENTRIFUGE;
    }

    @Override
    public BlockEntityType<?> createType() {
        return ModBlocks.THERMAL_CENTRIFUGE_TYPE;
    }

    /*@Override
    public double getWrenchDropRate() {
        return 1.0D;
    }*/

    /*@Override
    protected RecipeResult isRecipeStillValid(int slot, IMachineRecipeList.RecipeEntry entry) {
        if (heat < getRequiredHeat(entry.getOutput())){
            return RecipeResult.PASS;
        }
        return super.isRecipeStillValid(slot, entry);
    }*/



    @Override
    public boolean canProcess() {
        return heat >= maxHeat && super.canProcess();
    }

    @Override
    public void onTick() {
        super.onTick();
        if (((lastRecipe != null && !this.inventory.get(1).isEmpty()) || isRedstonePowered()) && this.energy > 0) {
            int newMaxHeat = (int) getMaxHeat();
            if (newMaxHeat != maxHeat) {
                maxHeat = newMaxHeat;
                updateGuiField("maxHeat");
            }
            boolean maxHeatCheck = false;
            if (this.heat < maxHeat) {
                ++this.heat;
                maxHeatCheck = true;
                this.updateGuiField("heat");
            }
            if (this.heat == maxHeat && maxHeatCheck) {
                rebuildCache = true;
                maxHeatCheck = false;
            }
            if (this.heat > maxHeat) {
                this.heat = maxHeat;
                this.rebuildCache = true;
                this.updateGuiField("heat");
            }

            this.useEnergy(1);
        } else if (this.heat > 0) {
            int newMaxHeat = 500;
            if (newMaxHeat != maxHeat) {
                maxHeat = newMaxHeat;
                this.updateGuiField("maxHeat");
            }
            this.heat -= Math.min(this.heat, 4);
            this.updateGuiField("heat");
        }
    }

    @Override
    protected boolean canStopTicking(boolean charging) {
        return false;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.heat = compound.getInt("heat");
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("heat", this.heat);
    }

    public static int getRequiredHeat(IRecipeOutput output) {
        if (output == null) {
            return 1;
        }
        return output.getMetadata().getInt("heat");
    }
}
