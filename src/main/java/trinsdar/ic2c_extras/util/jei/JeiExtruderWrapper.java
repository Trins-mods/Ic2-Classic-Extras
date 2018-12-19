package trinsdar.ic2c_extras.util.jei;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JeiExtruderWrapper extends BlankRecipeWrapper {
    IMachineRecipeList.RecipeEntry entry;

    public JeiExtruderWrapper(IMachineRecipeList.RecipeEntry recipe) {
        this.entry = recipe;
    }

    @Override
    public void getIngredients(IIngredients components)
    {
        components.setInputLists(ItemStack.class, Arrays.asList(this.entry.getInput().getInputs()));
        List<List<ItemStack>> outputs = new ArrayList<List<ItemStack>>();
        int count = 0;
        for(ItemStack stack : entry.getOutput().copy().getAllOutputs())
        {
            outputs.add(Arrays.asList(stack));
            count++;
            if(count >= 1)
            {
                break;
            }
        }
        components.setOutputLists(ItemStack.class, outputs);
    }
}
