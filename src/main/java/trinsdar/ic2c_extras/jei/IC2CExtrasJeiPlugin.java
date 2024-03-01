package trinsdar.ic2c_extras.jei;

import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.block.generators.tiles.LiquidFuelGenTileEntity;
import ic2.core.block.machines.tiles.ev.ColossalCompressor;
import ic2.core.block.machines.tiles.ev.ColossalExtractor;
import ic2.core.block.machines.tiles.ev.ColossalFurnace;
import ic2.core.block.machines.tiles.ev.ColossalMacerator;
import ic2.core.block.machines.tiles.ev.ColossalRecycler;
import ic2.core.block.machines.tiles.hv.UraniumEnchricherTileEntity;
import ic2.core.block.machines.tiles.lv.CannerTileEntity;
import ic2.core.block.machines.tiles.lv.ElectrolyzerTileEntity;
import ic2.core.block.machines.tiles.lv.RareEarthExtractorTileEntity;
import ic2.core.block.machines.tiles.mv.RareEarthCentrifugeTileEntity;
import ic2.core.block.machines.tiles.mv.RefineryTileEntity;
import ic2.core.block.machines.tiles.mv.VacuumCannerTileEntity;
import ic2.core.block.machines.tiles.nv.StoneCannerTileEntity;
import ic2.core.inventory.gui.IC2Screen;
import ic2.core.utils.collection.CollectionUtils;
import ic2.jeiplugin.JEIModule;
import ic2.jeiplugin.core.IC2GuiHandler;
import ic2.jeiplugin.core.recipes.categories.ElectrolyzerCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.container.ContainerOreWashingPlant;
import trinsdar.ic2c_extras.container.ContainerThermalCentrifuge;
import trinsdar.ic2c_extras.init.ModBlocks;
import trinsdar.ic2c_extras.recipes.MachineRecipes;

import java.util.Map;

@JeiPlugin
public class IC2CExtrasJeiPlugin implements IModPlugin {

    public static final RecipeType<IMachineRecipeList.RecipeEntry> ORE_WASHING_PLANT = RecipeType.create(IC2CExtras.MODID, "ore_washing_plant", IMachineRecipeList.RecipeEntry.class);
    public static final RecipeType<IMachineRecipeList.RecipeEntry> THERMAL_CENTRIFUGE = RecipeType.create(IC2CExtras.MODID, "thermal_centrifuge", IMachineRecipeList.RecipeEntry.class);
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(IC2CExtras.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        if (!JEIModule.ALLOWS_LOADING) return;
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ORE_WASHING_PLANT), ORE_WASHING_PLANT);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.THERMAL_WASHER), ORE_WASHING_PLANT);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.THERMAL_CENTRIFUGE), THERMAL_CENTRIFUGE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (!JEIModule.ALLOWS_LOADING) return;
        registration.addRecipeCategories(new ThermalCentrifugeCategory(registration.getJeiHelpers().getGuiHelper(), THERMAL_CENTRIFUGE, ContainerThermalCentrifuge.GUI_TEXTURE, ModBlocks.THERMAL_CENTRIFUGE));
        registration.addRecipeCategories(new OreWashingPlantCategory(registration.getJeiHelpers().getGuiHelper(), ORE_WASHING_PLANT, ContainerOreWashingPlant.GUI_TEXTURE, ModBlocks.ORE_WASHING_PLANT));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (!JEIModule.ALLOWS_LOADING) return;
        registration.addRecipes(ORE_WASHING_PLANT, MachineRecipes.ORE_WASHING_PLANT.getAllEntries());
        registration.addRecipes(THERMAL_CENTRIFUGE, MachineRecipes.THERMAL_CENTRIFUGE.getAllEntries());
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        if (!JEIModule.ALLOWS_LOADING) return;
        Map<IMachineRecipeList, RecipeType<?>> locations = CollectionUtils.createMap();
        locations.put(MachineRecipes.ORE_WASHING_PLANT, ORE_WASHING_PLANT);
        locations.put(MachineRecipes.THERMAL_CENTRIFUGE, THERMAL_CENTRIFUGE);
        Map<Class<?>, RecipeType<?>> classBased = CollectionUtils.createMap();
        registration.addGuiContainerHandler(IC2Screen.class, new IC2GuiHandler(locations, classBased));
    }
}
