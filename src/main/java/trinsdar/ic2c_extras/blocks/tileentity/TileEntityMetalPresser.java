package trinsdar.ic2c_extras.blocks.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.MachineType;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.IFilter;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.blocks.container.ContainerMetalPresser;
import trinsdar.ic2c_extras.blocks.container.ContainerOreWashingPlant;
import trinsdar.ic2c_extras.recipes.ContainerInputRecipeList;
import trinsdar.ic2c_extras.util.GuiMachine;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TileEntityMetalPresser extends TileEntityContainerInputBase {

    public static final ContainerInputRecipeList metalPresser = new ContainerInputRecipeList("metalPresser");

    public TileEntityMetalPresser() {
        super(4, 2, 5, 400, 32);
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
        return metalPresser;
    }

    @Override
    public Set<IMachineUpgradeItem.UpgradeType> getSupportedTypes() {
        return new LinkedHashSet(Arrays.asList(IMachineUpgradeItem.UpgradeType.values()));
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player)
    {
        return new ContainerMetalPresser(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player)
    {
        return GuiMachine.MetalPresserGui.class;
    }


    @Override
    public LocaleComp getBlockName()
    {
        return Ic2cExtrasLang.oreWashingPlant;
    }

    public ResourceLocation getGuiTexture()
    {
        return Ic2cExtrasResourceLocations.oreWashingPlant;
    }
}
