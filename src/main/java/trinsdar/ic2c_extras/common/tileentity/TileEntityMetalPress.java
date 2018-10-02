package trinsdar.ic2c_extras.common.tileentity;

import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.IRecipeMachine;
import ic2.api.classic.tile.MachineType;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.recipe.IRecipeInput;
import ic2.core.audio.AudioSource;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.base.util.info.EnergyUsageInfo;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.block.base.util.info.misc.IEnergyUser;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.items.RegistryItem;

import java.util.ArrayList;
import java.util.List;

public class TileEntityMetalPress  extends TileEntityBasicElectricMachine {

    public TileEntityMetalPress(){super(3, 10, 400, 32);}

    public MachineType getType() {
        return MachineType.macerator;
    }

    public LocaleComp getBlockName()
    {
        return new LangComponentHolder.LocaleBlockComp("tile.metalPress");
    }

    public static IMachineRecipeList metalPress = new BasicMachineRecipeList("metalPress");

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return metalPress.getRecipeInAndOutput(input, false);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guimetalpress.png");
    }

    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player)
    {
        return GuiComponentContainer.class;
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
            return metalPress.getRecipeInAndOutput(par1, true) != null ? super.isValidInput(par1) : false;
        }
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public String getName() {
        return "MetalPress";
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return metalPress;
    }

    @Override
    public void update() {

    }

    public static void init(){
        addRecipe((String)"ingotCopper", 1, new ItemStack(RegistryItem.itemCasings, 2, 0));
        addRecipe((String)"ingotTin", 1, new ItemStack(RegistryItem.itemCasings, 2, 1));
        addRecipe((String)"ingotSilver", 1, new ItemStack(RegistryItem.itemCasings, 2, 2));
        addRecipe((String)"ingotLead", 1, new ItemStack(RegistryItem.itemCasings, 2, 3));
        addRecipe((String)"ingotIron", 1, new ItemStack(RegistryItem.itemCasings, 2, 4));
        addRecipe((String)"ingotGold", 1, new ItemStack(RegistryItem.itemCasings, 2, 5));
        addRecipe((String)"ingotRefinedIron", 1, new ItemStack(RegistryItem.itemCasings, 2, 6));
        addRecipe((String)"ingotSteel", 1, new ItemStack(RegistryItem.itemCasings, 2, 7));
        addRecipe((String)"ingotBronze", 1, new ItemStack(RegistryItem.itemCasings, 2, 8));
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
        metalPress.addRecipe(input, output, exp, makeString(output));
    }

    private static String makeString(ItemStack stack) {
        return stack.getDisplayName();
    }


}
