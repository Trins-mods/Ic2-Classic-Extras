package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.MachineType;
import ic2.core.block.base.tile.TileEntityAdvancedMachine;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2GuiLang;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.util.GuiMachine.ExtruderGui;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

public class TileEntityLiquescentExtruder extends TileEntityAdvancedMachine {
    public TileEntityLiquescentExtruder() {
        super(4, 30, 4000);
    }

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return Ic2cExtrasRecipes.extruding.getRecipeInAndOutput(input, false);
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{2, 3};
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player)
    {
        return ExtruderGui.class;
    }

    @Override
    public Slot[] getInventorySlots(InventoryPlayer player) {
        return new Slot[]{new SlotDischarge(this, 2147483647, 0, 56, 53), new SlotCustom(this, 1, 56, 17, new MachineFilter(this)), new SlotOutput(player.player, this, 2, 113, 35), new SlotOutput(player.player, this, 3, 131, 35)};
    }

    @Override
    public ResourceLocation getTexture() {
        return Ic2cExtrasResourceLocations.liquescentExtruder;
    }

    @Override
    public LocaleComp getSpeedName() {
        return Ic2GuiLang.machineSpeed;
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.liquescentExtruder;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return Ic2cExtrasRecipes.extruding;
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
