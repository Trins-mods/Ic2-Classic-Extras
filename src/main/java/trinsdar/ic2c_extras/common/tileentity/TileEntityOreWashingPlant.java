package trinsdar.ic2c_extras.common.tileentity;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.inventory.gui.custom.MachineGui;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2BlockLang;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Resources;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.Sys;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Not at all finished just random values to test stuff
public class TileEntityOreWashingPlant extends TileEntityBasicElectricMachine
{
    public TileEntityOreWashingPlant() {
        super(3, 2, 400, 32);
    }

    public IMachineRecipeList getRecipeList() {
        return ClassicRecipes.macerator;
    }

    public MachineType getType() {
        return MachineType.macerator;
    }

    public ResourceLocation getGuiTexture() {
        return Ic2Resources.macerator;
    }

    public LocaleComp getBlockName() {
        return Ic2BlockLang.macerator;
    }

    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return MachineGui.MaceratorGui.class;
    }

    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return ClassicRecipes.macerator.getRecipeInAndOutput(input, false);
    }

    public ResourceLocation getStartSoundFile() {
        return Ic2Sounds.maceratorOp;
    }

    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

    public double getWrenchDropRate() {
        return 0.8500000238418579D;
    }

    public boolean isValidInput(ItemStack par1) {
        if (par1 == null) {
            return false;
        } else {
            return ClassicRecipes.macerator.getRecipeInAndOutput(par1, true) != null ? super.isValidInput(par1) : false;
        }
    }

    public static void postInit() {
        Set<String> oreBlacklist = new HashSet();
        oreBlacklist.addAll(Arrays.asList("oreCoal", "oreIron", "oreGold", "oreSilver", "oreCopper", "oreTin", "oreRedstone", "oreUranium"));
        Set<String> ingotBlackList = new HashSet();
        ingotBlackList.addAll(Arrays.asList("ingotIron", "ingotGold", "ingotSilver", "ingotCopper", "ingotTin", "ingotBronze"));
        String[] var2 = OreDictionary.getOreNames();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String id = var2[var4];
            String dust;
            NonNullList list;
            if (id.startsWith("ore")) {
                if (!oreBlacklist.contains(id)) {
                    dust = "dust" + id.substring(3);
                    if (OreDictionary.doesOreNameExist(dust)) {
                        list = OreDictionary.getOres(dust, false);
                        if (!list.isEmpty()) {
                            addRecipe((String)id, 1, StackUtil.copyWithSize((ItemStack)list.get(0), 2));
                        }
                    }
                }
            } else if (id.startsWith("ingot") && !ingotBlackList.contains(id)) {
                dust = "dust" + id.substring(3);
                if (OreDictionary.doesOreNameExist(dust)) {
                    list = OreDictionary.getOres(dust, false);
                    if (!list.isEmpty()) {
                        addRecipe((String)id, 1, ((ItemStack)list.get(0)).copy());
                    }
                }
            }
        }

    }

    public static void init() {
        addRecipe((String)"oreIron", 1, StackUtil.copyWithSize(Ic2Items.ironDust, 2), 0.7F);
        addRecipe((String)"oreGold", 1, StackUtil.copyWithSize(Ic2Items.goldDust, 2), 1.0F);
        addRecipe((String)"oreSilver", 1, StackUtil.copyWithSize(Ic2Items.silverDust, 2), 0.8F);
        addRecipe((String)"oreCopper", 1, StackUtil.copyWithSize(Ic2Items.copperDust, 2), 0.3F);
        addRecipe((String)"oreTin", 1, StackUtil.copyWithSize(Ic2Items.tinDust, 2), 0.4F);
        addRecipe((String)"oreUranium", 1, StackUtil.copyWithSize(Ic2Items.uraniumDrop, 2), 1.0F);
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input)), output);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input, stacksize)), output);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputOreDict(input, stacksize)), output);
    }

    public static void addRecipe(ItemStack input, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input)), output, exp);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input, stacksize)), output, exp);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputOreDict(input, stacksize)), output, exp);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output) {
        addRecipe(input, output, 0.0F);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output, float exp) {
        ClassicRecipes.macerator.addRecipe(input, output, exp, makeString(output));
    }

    private static String makeString(ItemStack stack) {
        return stack.getDisplayName();
    }

    @Override
    public void update()
    {

    }

    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public boolean hasCustomName()
    {
        return false;
    }
}
