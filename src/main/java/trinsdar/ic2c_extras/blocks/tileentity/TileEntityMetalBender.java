package trinsdar.ic2c_extras.blocks.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.IFilter;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.blocks.container.ContainerMetalBender;
import trinsdar.ic2c_extras.recipes.ContainerInputRecipeList;
import trinsdar.ic2c_extras.util.GuiMachine;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TileEntityMetalBender extends TileEntityContainerInputBase {

    public static final ContainerInputRecipeList metalBender = new ContainerInputRecipeList("metalBender");

    public TileEntityMetalBender() {
        super(4, 2, 15, 4000, 128);
        slotInput = 0;
        slotInputContainer = 1;
        slotOutput = 3;
    }


    @Override
    public int[] getInputSlots() {
        return new int[]{slotInput, slotInputContainer};
    }

    @Override
    public IFilter[] getInputFilters(int[] slots) {
        return new IFilter[0];
    }

    @Override
    public boolean isRecipeSlot(int slot) {
        return true;
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{slotOutput};
    }

    @Override
    public ContainerInputRecipeList getRecipeList() {
        return metalBender;
    }

    @Override
    public Set<IMachineUpgradeItem.UpgradeType> getSupportedTypes() {
        return new LinkedHashSet(Arrays.asList(IMachineUpgradeItem.UpgradeType.values()));
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player)
    {
        return new ContainerMetalBender(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player)
    {
        return GuiMachine.MetalBenderGui.class;
    }


    @Override
    public LocaleComp getBlockName()
    {
        return Ic2cExtrasLang.metalBender;
    }

    public ResourceLocation getGuiTexture()
    {
        return Ic2cExtrasResourceLocations.metalBender;
    }

    public static void addRecipe(IRecipeInput input, ItemStack press, ItemStack output)
    {
        addRecipe(input, press, new MachineOutput(null, output));
    }

    public static void addRecipe(IRecipeInput input, ItemStack press, MachineOutput output)
    {
        metalBender.addRecipe(input, press, output, output.getAllOutputs().get(0).getDisplayName());
    }
}
