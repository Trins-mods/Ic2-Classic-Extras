package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Sounds;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.util.GuiMachine;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

public class TileEntityBlockCuttingMachine extends TileEntityBasicElectricMachine {
    public static IMachineRecipeList blockCutting = new BasicMachineRecipeList("blockCutting");
    public static final String hardness = "hardness";

    public TileEntityBlockCuttingMachine() {
        super(4, 5, 400, 32);
    }

    @Override
    public MachineType getType() {
        return null;
    }

    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.BLOCK_CUTTING_MACHINE;
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return GuiMachine.BlockCuttingGui.class;
    }

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return blockCutting.getRecipeInAndOutput(input, false);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return Ic2cExtrasResourceLocations.BLOCK_CUTTING_MACHINE;
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
            return blockCutting.getRecipeInAndOutput(par1, true) != null && super.isValidInput(par1);
        }
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return blockCutting;
    }

    public static int getBladeHardness(MachineOutput output) {
        if (output == null || output.getMetadata() == null) {
            return 0;
        }
        return output.getMetadata().getInteger(hardness);
    }

    protected static NBTTagCompound createBladeHardness(int amount) {
        if (amount < 0) {
            return null;
        }
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger(hardness, amount);
        return nbt;
    }

    public static void addRecipe(ItemStack input, int hardness, ItemStack output) {
        addRecipe((new RecipeInputItemStack(input)), hardness, output);
    }

    public static void addRecipe(ItemStack input, int stacksize, int hardness, ItemStack output) {
        addRecipe((new RecipeInputItemStack(input, stacksize)), hardness, output);
    }

    public static void addRecipe(String input, int stacksize, int hardness, ItemStack output) {
        addRecipe((new RecipeInputOreDict(input, stacksize)), hardness, output);
    }

    public static void addRecipe(String input, int hardness, ItemStack output) {
        addRecipe((new RecipeInputOreDict(input, 1)), hardness, output);
    }

    public static void addRecipe(IRecipeInput input, int hardness, ItemStack output) {
        blockCutting.addRecipe(input, new MachineOutput(createBladeHardness(hardness), output), output.getDisplayName());
    }
}
