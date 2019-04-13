package trinsdar.ic2c_extras.recipes;

import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.recipes.managers.RecipeManager;
import ic2.core.util.helpers.ItemWithMeta;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class MetalPresserRecipeList {
    public static final MetalPresserRecipe INVALID_RECIPE = new MetalPresserRecipe(null, ItemStack.EMPTY,
            new MachineOutput(null, new ArrayList<ItemStack>()), "Invalid");

    protected List<MetalPresserRecipe> recipes = new ArrayList<MetalPresserRecipe>();
    protected Map<String, MetalPresserRecipe> recipeMap = new LinkedHashMap<String, MetalPresserRecipe>();
    protected Map<ItemWithMeta, IRecipeInput> validInputs = new LinkedHashMap<ItemWithMeta, IRecipeInput>();
    String category;

    public MetalPresserRecipeList(String category) {
        this.category = category;
    }

    public void addRecipe(IRecipeInput input, ItemStack press, MachineOutput output, String id) {
        id = getRecipeID(recipeMap.keySet(), id, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (input == null){
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (press == null || press == ItemStack.EMPTY){
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid press input for machine " + category);
            return;
        }
        if (isListInvalid(output.getAllOutputs())) {
            IC2CExtras.logger.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.logger.info("Recipe[" + input +"," + press + "] as input " + category);
            return;
        }
        MetalPresserRecipe recipe = new MetalPresserRecipe(input, press, output, id);
        recipes.add(recipe);
        recipeMap.put(id, recipe);
        ItemStack stack = input.getInputs().get(0);
        validInputs.put(new ItemWithMeta(stack), input);
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

    public MetalPresserRecipe getRecipe(Predicate<MetalPresserRecipe> checker) {
        for (MetalPresserRecipe recipe : recipes) {
            if (checker.test(recipe)) {
                return recipe;
            }
        }
        return INVALID_RECIPE;
    }

    public MetalPresserRecipe getFromID(String id) {
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

    public static class MetalPresserRecipe {
        IRecipeInput input;
        ItemStack press;
        MachineOutput outputs;
        String id;

        public MetalPresserRecipe(IRecipeInput input, ItemStack press, MachineOutput outputs, String id) {
            this.input = input;
            this.press = press;
            this.outputs = outputs;
            this.id = id;
        }

        public String getRecipeID() {
            return id;
        }

        public IRecipeInput getInput() {
            return input;
        }

        public ItemStack getPress(){
            return press;
        }

        public boolean matches(ItemStack stack) {
            return input == null ? stack.isEmpty() : (input.matches(stack) && input.getAmount() <= stack.getCount());
        }

        public MachineOutput getOutputs() {
            return outputs;
        }

    }

    public Collection<MetalPresserRecipe> getRecipeList() {
        return new ArrayList<MetalPresserRecipe>(recipes);
    }
}
