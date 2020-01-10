package trinsdar.ic2c_extras.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.util.recipelists.ContainerInputRecipeList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JeiMetalBenderWrapper extends BlankRecipeWrapper {
    ContainerInputRecipeList.ContainerInputRecipe entry;

    public JeiMetalBenderWrapper(ContainerInputRecipeList.ContainerInputRecipe recipe) {
        this.entry = recipe;
    }

    @Override
    public void getIngredients(IIngredients components)
    {
        List<List<ItemStack>> lists = new ArrayList();
        lists.add(entry.getInput().getInputs());
        lists.add(Arrays.asList(entry.getPress()));
        components.setInputLists(ItemStack.class, lists);
        List<List<ItemStack>> outputs = new ArrayList<List<ItemStack>>();
        int count = 0;
        for(ItemStack stack : entry.getOutputs().copy().getAllOutputs())
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

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        FontRenderer font = minecraft.fontRenderer;
        if (Ic2cExtrasConfig.debugMode) {
            font.drawString("Recipe Id: " + entry.getRecipeID(), 5, 60, Color.black.getRGB());
        }
    }
}
