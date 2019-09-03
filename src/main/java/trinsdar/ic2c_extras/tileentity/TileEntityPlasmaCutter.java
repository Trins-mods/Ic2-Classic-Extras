package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityAdvancedMachine;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2GuiLang;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.helpers.CompareableStack;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.util.GuiMachine.CutterGui;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

public class TileEntityPlasmaCutter extends TileEntityAdvancedMachine {
    public TileEntityPlasmaCutter() {
        super(4, 30, 4000);
    }

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return Ic2cExtrasRecipes.cutting.getRecipeInAndOutput(input, false);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return CutterGui.class;
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{2, 3};
    }

    @Override
    public Slot[] getInventorySlots(InventoryPlayer player) {
        return new Slot[]{new SlotDischarge(this, 2147483647, 0, 56, 53), new SlotCustom(this, 1, 56, 17, new MachineFilter(this)), new SlotOutput(player.player, this, 2, 113, 35), new SlotOutput(player.player, this, 3, 131, 35)};
    }

    @Override
    public boolean isValidInput(ItemStack par1) {
        return super.isValidInput(par1) && isRecipeInputValid(par1);
    }

    public boolean isRecipeInputValid(ItemStack stack) {
        IRecipeInput input = Ic2cExtrasRecipes.cutterValidInputs.get(new CompareableStack(stack));
        if (input == null) {
            return false;
        }
        return input.matches(stack);
    }

    @Override
    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.plasmaCutter;
    }

    @Override
    public LocaleComp getSpeedName() {
        return Ic2GuiLang.machineSpeed;
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.plasmaCutter;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return Ic2cExtrasRecipes.cutting;
    }

    @Override
    public MachineType getType() {
        return null;
    }

    @Override
    public double getWrenchDropRate() {
        return 1.0D;
    }

    @Override
    public ResourceLocation getProcessSoundFile() {
        return Ic2Sounds.compressorOp;
    }

    @Override
    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }
}
