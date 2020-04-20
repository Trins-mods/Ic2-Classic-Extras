package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.item.recipe.AdvRecipeBase;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.util.GuiMachine.RollerGui;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.util.Arrays;

import static trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes.rolling;

public class TileEntityRoller extends TileEntityBasicElectricMachine {
    public TileEntityRoller() {
        super(3, 5, 400, 32);
    }

    @Override
    public MachineType getType() {
        return null;
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.roller;
    }

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return rolling.getRecipeInAndOutput(input, false);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return Ic2cExtrasResourceLocations.roller;
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return RollerGui.class;
    }

    @Override
    public ResourceLocation getStartSoundFile() {
        return Ic2Sounds.compressorOp;
    }

    @Override
    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

    @Override
    public double getWrenchDropRate() {
        return 1.0D;
    }

    @Override
    public boolean isValidInput(ItemStack par1) {
        if (par1 == null) {
            return false;
        } else {
            return rolling.getRecipeInAndOutput(par1, true) != null && super.isValidInput(par1);
        }
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return rolling;
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        addRecipe((new RecipeInputItemStack(input)), output);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output) {
        addRecipe((new RecipeInputItemStack(input, stacksize)), output);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output) {
        addRecipe((new RecipeInputOreDict(input, stacksize)), output);
    }

    public static void addRecipe(ItemStack input, ItemStack output, float exp) {
        addRecipe((new RecipeInputItemStack(input)), output, exp);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output, float exp) {
        addRecipe((new RecipeInputItemStack(input, stacksize)), output, exp);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output, float exp) {
        addRecipe((new RecipeInputOreDict(input, stacksize)), output, exp);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output) {
        addRecipe(input, output, 0.0F);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output, float exp) {
        rolling.addRecipe(input, output, exp, output.getUnlocalizedName());
    }
}
