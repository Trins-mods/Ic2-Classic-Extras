package trinsdar.ic2c_extras.util.recipelists;

import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.recipes.managers.RecipeManager;
import ic2.core.util.helpers.CompareableStack;
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

public class ContainerInputRecipeList {
    public static final ContainerInputRecipe INVALID_RECIPE = new ContainerInputRecipe(null, ItemStack.EMPTY,
            new MachineOutput(null, new ArrayList<ItemStack>()), "Invalid");

    protected List<ContainerInputRecipe> recipes = new ArrayList<ContainerInputRecipe>();
    protected Map<String, ContainerInputRecipe> recipeMap = new LinkedHashMap<String, ContainerInputRecipe>();
    protected Map<CompareableStack, IRecipeInput> validInputs = new LinkedHashMap<CompareableStack, IRecipeInput>();
    protected Map<String, ItemStack> validContainers = new LinkedHashMap<>();
    String category;

    public ContainerInputRecipeList(String category) {
        this.category = category;
    }

    public void addRecipe(IRecipeInput input, ItemStack press, MachineOutput output, String id) {
        id = getRecipeID(recipeMap.keySet(), id, 0);
        if (recipeMap.containsKey(id) || !RecipeManager.register(category, id)) {
            return;
        }
        if (input == null) {
            IC2CExtras.LOGGER.info("Recipe[" + id + "] has a invalid input for machine " + category);
            return;
        }
        if (press == null || press == ItemStack.EMPTY) {
            IC2CExtras.LOGGER.info("Recipe[" + id + "] has a invalid press input for machine " + category);
            return;
        }
        if (isListInvalid(output.getAllOutputs())) {
            IC2CExtras.LOGGER.info("Recipe[" + id + "] has a invalid output for machine " + category);
            IC2CExtras.LOGGER.info("Recipe[" + input + "," + press + "] as input " + category);
            return;
        }
        ContainerInputRecipe recipe = new ContainerInputRecipe(input, press, output, id);
        if (!validContainers.containsKey(press.getUnlocalizedName())) {
            validContainers.put(press.getUnlocalizedName(), press);
        }
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
        return input.matches(stack);
    }

    public boolean isValidRecipeContainer(ItemStack stack) {
        return validContainers.containsKey(stack.getUnlocalizedName());
    }

    public ContainerInputRecipe getRecipe(Predicate<ContainerInputRecipe> checker) {
        for (ContainerInputRecipe recipe : recipes) {
            if (checker.test(recipe)) {
                return recipe;
            }
        }
        return INVALID_RECIPE;
    }

    public ContainerInputRecipe getFromID(String id) {
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

    public static class ContainerInputRecipe {
        IRecipeInput input;
        ItemStack press;
        MachineOutput outputs;
        String id;

        public ContainerInputRecipe(IRecipeInput input, ItemStack press, MachineOutput outputs, String id) {
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

        public ItemStack getPress() {
            return press;
        }

        public boolean matches(ItemStack stack, ItemStack container) {
            return input == null ? stack.isEmpty() : (input.matches(stack) && input.getAmount() <= stack.getCount() && press.isItemEqualIgnoreDurability(container));
        }

        public MachineOutput getOutputs() {
            return outputs;
        }

    }

    public Collection<ContainerInputRecipe> getRecipeList() {
        return new ArrayList<ContainerInputRecipe>(recipes);
    }
}
