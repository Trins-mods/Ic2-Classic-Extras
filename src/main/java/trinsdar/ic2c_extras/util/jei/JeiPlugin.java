package trinsdar.ic2c_extras.util.jei;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Resources;
import ic2.jeiIntigration.SubModul;
import ic2.jeiIntigration.core.machine.basic.MachineRecipe;
import ic2.jeiIntigration.core.machine.basic.MachineRecipeCategory;
import ic2.jeiIntigration.core.machine.basic.MachineRecipeWrapper;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.GuiMachine;
import trinsdar.ic2c_extras.util.GuiMachine.*;
import trinsdar.ic2c_extras.util.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.RegistryBlock;
import trinsdar.ic2c_extras.util.RegistryItem;

import javax.annotation.Nonnull;
import java.util.Arrays;

@JEIPlugin
public class JeiPlugin implements IModPlugin {
    public String oreWashingId = "oreWashing";
    public String thermalCentrifugeId = "thermalCentrifuge";
    public String rollerId = "roller";
    public String extruderId = "extruder";
    public String cutterId = "cutter";

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime arg0) {
        // empty method for construction
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        if (SubModul.load){
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.thermalCentrifuge), thermalCentrifugeId);
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.oreWashingPlant), oreWashingId);
            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiThermalCentrifugeWrapper(var1);
                }
            }, "thermalCentrifuge");
            registry.addRecipeClickArea(ThermalCentrifugeGui.class, 78, 32, 20, 23, thermalCentrifugeId);
            registry.addRecipes(TileEntityThermalCentrifuge.thermalCentrifuge.getRecipeMap(), thermalCentrifugeId);
            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiOreWashingWrapper(var1);
                }
            }, oreWashingId);
            registry.addRecipeClickArea(OreWashingPlantGui.class, 78, 32, 20, 23, oreWashingId);
            registry.addRecipes(TileEntityOreWashingPlant.oreWashingPlant.getRecipeMap(), oreWashingId);

            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiRollerWrapper(var1);
                }
            }, "roller");
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.roller), rollerId);
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.metalPress), rollerId);
            registry.addRecipeClickArea(RollerGui.class, 78, 32, 20, 23, rollerId);
            registry.addRecipes(Ic2cExtrasRecipes.rolling.getRecipeMap(), rollerId);

            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiExtruderWrapper(var1);
                }
            }, extruderId);
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.extruder), extruderId);
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.metalPress), extruderId);
            registry.addRecipeClickArea(ExtruderGui.class, 78, 32, 20, 23, extruderId);
            registry.addRecipes(Ic2cExtrasRecipes.extruding.getRecipeMap(), extruderId);

            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiCutterWrapper(var1);
                }
            }, cutterId);
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.cutter), cutterId);
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.metalPress), cutterId);
            registry.addRecipeClickArea(CutterGui.class, 78, 32, 20, 23, cutterId);
            registry.addRecipes(Ic2cExtrasRecipes.cutting.getRecipeMap(), cutterId);

            IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
            if (!Ic2cExtrasRecipes.enableHarderUranium){
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.uranium235));
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.uranium238));
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.uranium235TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.uranium238TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.uraniumCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.uraniumPurifiedCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.plutonium));
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.plutoniumTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.plutoniumEnrichedUranium));
                blacklist.addIngredientToBlacklist(new ItemStack(RegistryItem.plutoniumEnrichedUraniumIngot));
            }
        }
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new JeiThermalCentrifugeCategory(helper));
        registry.addRecipeCategories(new JeiOreWashingCategory(helper));
        registry.addRecipeCategories(new JeiRollerCategory(helper));
        registry.addRecipeCategories(new JeiExtruderCategory(helper));
        registry.addRecipeCategories(new JeiCutterCategory(helper));
    }
}
