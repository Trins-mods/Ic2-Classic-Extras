package trinsdar.ic2c_extras.util.jei;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.core.IC2;
import ic2.jeiIntigration.SubModul;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.RegistryBlock;
import trinsdar.ic2c_extras.util.RegistryItem;

import javax.annotation.Nonnull;
import java.util.Arrays;

@JEIPlugin
public class JeiPlugin implements IModPlugin {
    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime arg0) {
        // empty method for construction
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        if (SubModul.load){
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.thermalCentrifuge), new String[] { "thermalCentrifuge" });
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.oreWashingPlant), new String[] { "oreWashing" });
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

            IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
            if (!IC2.config.getFlag("HarderUranium")){
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
        registry.addRecipeCategories(new JeiThermalCentrifugeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new JeiOreWashingCategory(registry.getJeiHelpers().getGuiHelper()));
    }
}
