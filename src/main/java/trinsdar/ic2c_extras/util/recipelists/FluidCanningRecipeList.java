package trinsdar.ic2c_extras.util.recipelists;

import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.ICannerEnrichRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.MachineRecipe;
import ic2.api.recipe.MachineRecipeResult;
import ic2.api.recipe.RecipeOutput;
import ic2.core.block.machine.recipes.managers.RecipeManager;
import ic2.core.util.helpers.CompareableStack;
import ic2.core.util.helpers.ItemWithMeta;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class FluidCanningRecipeList implements ICannerEnrichRecipeManager {
    public static final FluidCanningRecipe INVALID_RECIPE = new FluidCanningRecipe(null, null,
            new MachineOutput(null, new ArrayList<ItemStack>()), true, null, "Invalid");

    protected List<FluidCanningRecipe> recipes = new ArrayList<FluidCanningRecipe>();
    protected Map<String, FluidCanningRecipe> recipeMap = new LinkedHashMap<String, FluidCanningRecipe>();
    protected Map<CompareableStack, IRecipeInput> validInputs = new LinkedHashMap<CompareableStack, IRecipeInput>();
    String category;

    public FluidCanningRecipeList(String category) {
        this.category = category;
    }

    public void addEnrichingRecipe(IRecipeInput input, FluidStack inputFluid, MachineOutput output, FluidStack outputFluid, String id) {
        id = getRecipeID(recipeMap.keySet(), id, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (input == null || inputFluid == null) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (isListInvalid(output.getAllOutputs()) || outputFluid == null) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.logger.info("Recipe[" + input + "," + inputFluid + "] as input " + category);
            return;
        }
        FluidCanningRecipe recipe = new FluidCanningRecipe(input, inputFluid, output, true, outputFluid, id);
        recipes.add(recipe);
        recipeMap.put(id, recipe);
        for (ItemStack stack : input.getInputs()) {
            validInputs.put(new CompareableStack(stack), input);
        }
    }

    public void addEmptyingRecipe(IRecipeInput input, MachineOutput output, FluidStack outputFluid, String id) {
        id = getRecipeID(recipeMap.keySet(), id, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (input == null) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (isListInvalid(output.getAllOutputs()) || outputFluid == null) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.logger.info("Recipe[" + input + "] as input " + category);
            return;
        }
        FluidCanningRecipe recipe = new FluidCanningRecipe(input, output, outputFluid, id);
        recipes.add(recipe);
        recipeMap.put(id, recipe);
        for (ItemStack stack : input.getInputs()) {
            validInputs.put(new CompareableStack(stack), input);
        }
    }

    public void addFillingRecipe(IRecipeInput input, FluidStack inputFluid, MachineOutput output, String id) {
        id = getRecipeID(recipeMap.keySet(), id, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (input == null || inputFluid == null) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (isListInvalid(output.getAllOutputs())) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.logger.info("Recipe[" + input + "," + inputFluid + "] as input " + category);
            return;
        }
        FluidCanningRecipe recipe = new FluidCanningRecipe(input, inputFluid, output, id);
        recipes.add(recipe);
        recipeMap.put(id, recipe);
        for (ItemStack stack : input.getInputs()) {
            validInputs.put(new CompareableStack(stack), input);
        }
    }

    public void addEnrichingRecipe(IRecipeInput input, FluidStack inputFluid, FluidStack outputFluid, String id) {
        id = getRecipeID(recipeMap.keySet(), id, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (input == null || inputFluid == null) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (outputFluid == null) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.logger.info("Recipe[" + input + "," + inputFluid + "] as input " + category);
            return;
        }
        FluidCanningRecipe recipe = new FluidCanningRecipe(input, inputFluid, new MachineOutput(null, new ArrayList<ItemStack>()), false, outputFluid, id);
        recipes.add(recipe);
        recipeMap.put(id, recipe);
        for (ItemStack stack : input.getInputs()) {
            validInputs.put(new CompareableStack(stack), input);
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
        IRecipeInput input = validInputs.get(new CompareableStack(stack));
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

    /* ICannerEnrichRecipeManager methods */

    @Override
    public void addRecipe(FluidStack fluidStack, IRecipeInput iRecipeInput, FluidStack fluidStack1) {
        this.addEnrichingRecipe(iRecipeInput, fluidStack, fluidStack1, fluidStack1.getUnlocalizedName());
    }

    @Override
    public RecipeOutput getOutputFor(FluidStack fluidStack, ItemStack itemStack, boolean b, boolean b1) {
        return null;
    }

    @Override
    public boolean addRecipe(Input input, FluidStack fluidStack, NBTTagCompound nbtTagCompound, boolean b) {
        return false;
    }

    @Override
    public MachineRecipeResult<Input, FluidStack, RawInput> apply(RawInput rawInput, boolean b) {
        return null;
    }

    @Override
    public Iterable<? extends MachineRecipe<Input, FluidStack>> getRecipes() {
        return null;
    }

    @Override
    public boolean isIterable() {
        return false;
    }

    public static class FluidCanningRecipe {
        IRecipeInput input;
        FluidStack inputFluid;
        MachineOutput outputs;
        FluidStack outputFluid;
        String id;
        boolean itemOutput;
        boolean fluidInput;
        boolean fluidOutput;

        public FluidCanningRecipe(IRecipeInput input, FluidStack inputFluid, MachineOutput outputs, boolean itemOutput, FluidStack outputFluid, String id) {
            this.input = input;
            this.inputFluid = inputFluid;
            this.outputs = outputs;
            this.outputFluid = outputFluid;
            this.id = id;
            this.itemOutput = itemOutput;
            this.fluidInput = true;
            this.fluidOutput = true;
        }

        public FluidCanningRecipe(IRecipeInput input, MachineOutput outputs, FluidStack outputFluid, String id) {
            this.input = input;
            this.outputs = outputs;
            this.outputFluid = outputFluid;
            this.id = id;
            this.fluidInput = false;
            this.itemOutput = true;
            this.fluidOutput = true;
        }

        public FluidCanningRecipe(IRecipeInput input, FluidStack inputFluid, MachineOutput outputs, String id) {
            this.input = input;
            this.inputFluid = inputFluid;
            this.outputs = outputs;
            this.id = id;
            this.itemOutput = true;
            this.fluidInput = true;
            this.fluidOutput = false;
        }

        public String getRecipeID() {
            return id;
        }

        public IRecipeInput getInput() {
            return input;
        }

        public FluidStack getInputFluid() {
            return inputFluid;
        }

        public FluidStack getOutputFluid() {
            return outputFluid;
        }

        public boolean matches(ItemStack stack) {
            return input == null ? stack.isEmpty() : (input.matches(stack) && input.getAmount() <= stack.getCount());
        }

        public boolean hasFluidInput() {
            return fluidInput;
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
