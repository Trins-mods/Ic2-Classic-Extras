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

            registry.handleRecipes(MachineRecipe.class, new MachineRecipeWrapper.MachineManager(), "roller");
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.roller), new String[]{"roller"});
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.metalPress), new String[]{"roller"});
            registry.addRecipes(Ic2cExtrasRecipes.rolling.getRecipeMap(), "roller");

            registry.handleRecipes(MachineRecipe.class, new MachineRecipeWrapper.MachineManager(), "extruder");
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.extruder), new String[]{"extruder"});
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.metalPress), new String[]{"extruder"});
            registry.addRecipes(Ic2cExtrasRecipes.extruding.getRecipeMap(), "extruder");

            registry.handleRecipes(MachineRecipe.class, new MachineRecipeWrapper.MachineManager(), "cutter");
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.cutter), new String[]{"cutter"});
            registry.addRecipeCatalyst(new ItemStack(RegistryBlock.metalPress), new String[]{"cutter"});
            registry.addRecipes(Ic2cExtrasRecipes.cutting.getRecipeMap(), "cutter");

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
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new JeiThermalCentrifugeCategory(helper));
        registry.addRecipeCategories(new JeiOreWashingCategory(helper));
        registry.addRecipeCategories(new IRecipeCategory[]{
                new MachineRecipeCategory(helper, "roller", new ItemStack(RegistryBlock.roller), new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guiroller.png")),
                new MachineRecipeCategory(helper, "extruder", new ItemStack(RegistryBlock.extruder), new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guiroller.png")),
                new MachineRecipeCategory(helper, "cutter", new ItemStack(RegistryBlock.cutter), new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guiroller.png"))});
    }
}
