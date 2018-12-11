package trinsdar.ic2c_extras.util.jei;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;

import javax.annotation.Nonnull;
@JEIPlugin
public class JeiPlugin implements IModPlugin {
    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime arg0) {
        // empty method for construction
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {

        registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
            @Override
            public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
            {
                return new JeiThermalCentrifugeWrapper(var1);
            }
        }, "thermalCentrifuge");
        registry.addRecipes(TileEntityThermalCentrifuge.thermalCentrifuge.getRecipeMap(), "thermalCentrifuge");
        registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
            @Override
            public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
            {
                return new JeiOreWashingWrapper(var1);
            }
        }, "oreWashing");
        registry.addRecipes(TileEntityOreWashingPlant.oreWashingPlant.getRecipeMap(), "oreWashing");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        registry.addRecipeCategories(new JeiThermalCentrifugeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new JeiOreWashingCategory(registry.getJeiHelpers().getGuiHelper()));
    }
}
