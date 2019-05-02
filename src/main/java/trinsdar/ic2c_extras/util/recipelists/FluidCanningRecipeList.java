package trinsdar.ic2c_extras.util.recipelists;

import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.recipes.managers.RecipeManager;
import ic2.core.util.helpers.ItemWithMeta;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class FluidCanningRecipeList {
    public static final FluidCanningRecipe INVALID_RECIPE = new FluidCanningRecipe(null, null, 0,
            new MachineOutput(null, new ArrayList<ItemStack>()), null, 0, "Invalid");

    protected List<FluidCanningRecipe> recipes = new ArrayList<FluidCanningRecipe>();
    protected Map<String, FluidCanningRecipe> recipeMap = new LinkedHashMap<String, FluidCanningRecipe>();
    protected Map<ItemWithMeta, IRecipeInput> validInputs = new LinkedHashMap<ItemWithMeta, IRecipeInput>();
    String category;

    public FluidCanningRecipeList(String category) {
        this.category = category;
    }

    public void addRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, MachineOutput output, Fluid outputFluid, int outputFluidAmount, String id) {
        id = getRecipeID(recipeMap.keySet(), id, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (input == null || inputFluid == null){
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (isListInvalid(output.getAllOutputs()) || outputFluid == null) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.logger.info("Recipe[" + input + "," + inputFluid + "] as input " + category);
            return;
        }
        FluidCanningRecipe recipe = new FluidCanningRecipe(input, inputFluid, inputFluidAmount, output, outputFluid, outputFluidAmount, id);
        recipes.add(recipe);
        recipeMap.put(id, recipe);
        for (ItemStack stack : input.getInputs()){
            validInputs.put(new ItemWithMeta(stack), input);
        }
    }

    public void addRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, MachineOutput output, String id) {
        id = getRecipeID(recipeMap.keySet(), id, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (input == null || inputFluid == null){
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (isListInvalid(output.getAllOutputs())) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.logger.info("Recipe[" + input + "," + inputFluid + "] as input " + category);
            return;
        }
        FluidCanningRecipe recipe = new FluidCanningRecipe(input, inputFluid, inputFluidAmount, output, id);
        recipes.add(recipe);
        recipeMap.put(id, recipe);
        for (ItemStack stack : input.getInputs()){
            validInputs.put(new ItemWithMeta(stack), input);
        }
    }

    public void addRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, Fluid outputFluid, int outputFluidAmount, String id) {
        id = getRecipeID(recipeMap.keySet(), id, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (input == null || inputFluid == null){
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (outputFluid == null) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.logger.info("Recipe[" + input + "," + inputFluid + "] as input " + category);
            return;
        }
        FluidCanningRecipe recipe = new FluidCanningRecipe(input, inputFluid, inputFluidAmount, outputFluid, outputFluidAmount, id);
        recipes.add(recipe);
        recipeMap.put(id, recipe);
        for (ItemStack stack : input.getInputs()){
            validInputs.put(new ItemWithMeta(stack), input);
        }
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

    public boolean isValidRecipeInput(ItemStack stack) {
        IRecipeInput input = validInputs.get(new ItemWithMeta(stack));
        if (input == null) {
            return false;
        }
        if (input.matches(stack)) {
            return true;
        }
        return false;
    }

    public FluidCanningRecipe getRecipe(Predicate<FluidCanningRecipe> checker) {
        for (FluidCanningRecipe recipe : recipes) {
            if (checker.test(recipe)) {
                return recipe;
            }
        }
        return INVALID_RECIPE;
    }

    public FluidCanningRecipe getFromID(String id) {
        return recipeMap.get(id);
    }

    private boolean isListInvalid(List<ItemStack> list) {
        if (list.isEmpty()) {
            return true;
        }
        for (ItemStack stack : list) {
            if (stack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static class FluidCanningRecipe {
        IRecipeInput input;
        Fluid inputFluid;
        int inputFluidAmount;
        MachineOutput outputs;
        Fluid outputFluid;
        int outputFluidAmount;
        String id;
        boolean itemOutput;
        boolean fluidOutput;

        public FluidCanningRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, MachineOutput outputs, Fluid outputFluid, int outputFluidAmount, String id) {
            this.input = input;
            this.inputFluid = inputFluid;
            this.inputFluidAmount = inputFluidAmount;
            this.outputs = outputs;
            this.outputFluid = outputFluid;
            this.outputFluidAmount = outputFluidAmount;
            this.id = id;
            this.itemOutput = true;
            this.fluidOutput = true;
        }

        public FluidCanningRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, MachineOutput outputs,  String id) {
            this.input = input;
            this.inputFluid = inputFluid;
            this.inputFluidAmount = inputFluidAmount;
            this.outputs = outputs;
            this.id = id;
            this.itemOutput = true;
            this.fluidOutput = false;
        }

        public FluidCanningRecipe(IRecipeInput input, Fluid inputFluid, int inputFluidAmount, Fluid outputFluid, int outputFluidAmount, String id) {
            this.input = input;
            this.inputFluid = inputFluid;
            this.inputFluidAmount = inputFluidAmount;
            this.outputFluid = outputFluid;
            this.outputFluidAmount = outputFluidAmount;
            this.id = id;
            this.itemOutput = false;
            this.fluidOutput = true;
        }

        public String getRecipeID() {
            return id;
        }

        public IRecipeInput getInput() {
            return input;
        }

        public Fluid getInputFluid() {
            return inputFluid;
        }

        public int getInputFluidAmount() {
            return inputFluidAmount;
        }

        public Fluid getOutputFluid() {
            return outputFluid;
        }

        public int getOutputFluidAmount() {
            return outputFluidAmount;
        }

        public boolean matches(ItemStack stack, Fluid fluid) {
            return input == null ? stack.isEmpty() : (input.matches(stack) && input.getAmount() <= stack.getCount() && inputFluid.equals(fluid));
        }

        public boolean hasFluidOutput() {
            return fluidOutput;
        }

        public boolean hasItemOutput() {
            return itemOutput;
        }

        public MachineOutput getOutputs() {
            return outputs;
        }

    }

    public Collection<FluidCanningRecipe> getRecipeList() {
        return new ArrayList<FluidCanningRecipe>(recipes);
    }
}
