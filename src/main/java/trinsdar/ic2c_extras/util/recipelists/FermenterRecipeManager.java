package trinsdar.ic2c_extras.util.recipelists;

import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IFermenterRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.recipes.managers.RecipeManager;
import ic2.core.util.helpers.CompareableStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FermenterRecipeManager implements IFermenterRecipeManager {
    public static final FermentationProperty INVALID_RECIPE = new FermentationProperty(0, 0, "", 0);

    protected Map<String, FermentationProperty> recipes = new LinkedHashMap<String, FermentationProperty>();
    protected Map<String, Tuple<String, FermentationProperty>> recipeMap = new LinkedHashMap<String, Tuple<String, FermentationProperty>>();
    protected Map<CompareableStack, IRecipeInput> validInputs = new LinkedHashMap<CompareableStack, IRecipeInput>();

    String category;

    public FermenterRecipeManager(String category) {
        this.category = category;
    }

    @Override
    public void addRecipe(String input, int inputAmount, int heat, String output, int outputAmount) {
        String id = getRecipeID(recipeMap.keySet(), input, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (!FluidRegistry.isFluidRegistered(input)) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (!FluidRegistry.isFluidRegistered(output)) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.logger.info("Recipe[" + input + "] as input " + category);
            return;
        }
        if (recipes.containsKey(input)){
            IC2CExtras.logger.info(input + " already registered as a recipe for machine " + category);
            return;
        }
        FermentationProperty recipe = new FermentationProperty(inputAmount, heat, output, outputAmount);
        recipes.put(input, recipe);
        recipeMap.put(id, new Tuple<>(input, recipe));
    }

    public static String getRecipeID(Set<String> ids, String base, int index) {
        String newString = base;
        if (index > 0) {
            newString = newString + "_" + index;
        }
        if (ids.contains(newString)) {
            return getRecipeID(ids, base, index + 1);
        }
        return newString;
    }

    @Override
    public FermentationProperty getFermentationInformation(Fluid fluid) {
        return recipes.getOrDefault(fluid.getName(), null);
    }

    @Override
    public FluidStack getOutput(Fluid fluid) {
        return recipes.containsKey(fluid.getName()) ? recipes.get(fluid.getName()).getOutput() : null;
    }

    @Override
    public Map<String, FermentationProperty> getRecipeMap() {
        return recipes;
    }

    public Map<String, Tuple<String, FermentationProperty>> getRecipeMap2() {
        return recipeMap;
    }

    @Override
    public boolean acceptsFluid(Fluid fluid) {
        return recipes.containsKey(fluid.getName());
    }

    @Override
    public Set<Fluid> getAcceptedFluids() {
        Set<Fluid> fluids = new LinkedHashSet<>();
        for (String key : recipes.keySet()){
            if (FluidRegistry.isFluidRegistered(key)){
                fluids.add(FluidRegistry.getFluid(key));
            }
        }
        return fluids;
    }
}
