package trinsdar.ic2c_extras.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.container.ContainerMetalBender;
import trinsdar.ic2c_extras.tileentity.base.TileEntityContainerInputBase;
import trinsdar.ic2c_extras.util.guicomponent.MachineFilter2;
import trinsdar.ic2c_extras.util.recipelists.ContainerInputRecipeList;
import trinsdar.ic2c_extras.util.GuiMachine;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasResourceLocations;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class TileEntityMetalBender extends TileEntityContainerInputBase {

    public static final ContainerInputRecipeList metalBender = new ContainerInputRecipeList("metalBender");
    public MachineFilter filter = new MachineFilter(this);
    public MachineFilter2 pressFilter = new MachineFilter2(this);

    public TileEntityMetalBender() {
        super(4, 2, 15, 4000, 128);
        slotInput = 0;
        slotInputContainer = 1;
        slotOutput = 3;
    }

    @Override
    protected void addSlots(InventoryHandler handler) {
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, 2);
        handler.registerDefaultSlotAccess(AccessRule.Import, 0, 1);
        handler.registerDefaultSlotAccess(AccessRule.Export, 3);
        handler.registerDefaultSlotsForSide(RotationList.UP.invert(), 3);
        handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), 0, 1);
        handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), 2);
        handler.registerInputFilter(filter, 0);
        handler.registerInputFilter(pressFilter, 1);
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, 2);
        handler.registerSlotType(SlotType.Fuel, 2);
        handler.registerSlotType(SlotType.Input, 0, 1);
        handler.registerSlotType(SlotType.Output, 3);
    }


    @Override
    public int[] getInputSlots() {
        return new int[]{slotInput};
    }

    @Override
    public IFilter[] getInputFilters(int[] slots) {
        return new IFilter[]{filter};
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
    public ContainerIC2 getGuiContainer(EntityPlayer player) {
        return new ContainerMetalBender(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return GuiMachine.MetalBenderGui.class;
    }


    @Override
    public LocaleComp getBlockName() {
        return Ic2cExtrasLang.metalBender;
    }

    @Override
    public double getWrenchDropRate() {
        return 1.0D;
    }

    public ResourceLocation getGuiTexture() {
        return Ic2cExtrasResourceLocations.metalBender;
    }

    public static void addRecipe(IRecipeInput input, ItemStack press, ItemStack output) {
        addRecipe(input, press, new MachineOutput(null, output));
    }

    public static void addRecipe(IRecipeInput input, ItemStack press, MachineOutput output) {
        metalBender.addRecipe(input, press, output, output.getAllOutputs().get(0).getUnlocalizedName());
    }
}
