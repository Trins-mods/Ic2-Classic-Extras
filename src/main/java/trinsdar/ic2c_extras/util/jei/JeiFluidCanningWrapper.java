package trinsdar.ic2c_extras.util.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.util.recipelists.FluidCanningRecipeList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JeiFluidCanningWrapper extends BlankRecipeWrapper {
    FluidCanningRecipeList.FluidCanningRecipe entry;

    public JeiFluidCanningWrapper(FluidCanningRecipeList.FluidCanningRecipe recipe) {
        this.entry = recipe;
    }

    @Override
    public void getIngredients(IIngredients components)
    {
        components.setInputLists(ItemStack.class, Arrays.asList(this.entry.getInput().getInputs()));
        if (entry.hasFluidInput()){
            components.setInputs(FluidStack.class, Arrays.asList(this.entry.getInputFluid()));
        }
        List<List<ItemStack>> outputs = new ArrayList<List<ItemStack>>();
        if (entry.hasItemOutput()){
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
        if (entry.hasFluidOutput()){
            components.setOutput(FluidStack.class, entry.getOutputFluid());
        }

    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        FontRenderer font = minecraft.fontRenderer;
        if (Ic2cExtrasConfig.debugMode) {
            font.drawString("Recipe Id: " + entry.getRecipeID(), 5, 62, Color.black.getRGB());
        }
    }
}
