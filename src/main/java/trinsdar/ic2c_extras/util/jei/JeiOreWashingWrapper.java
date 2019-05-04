package trinsdar.ic2c_extras.util.jei;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.core.platform.registry.Ic2Formatters;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JeiOreWashingWrapper extends BlankRecipeWrapper {
    IMachineRecipeList.RecipeEntry entry;

    public JeiOreWashingWrapper(IMachineRecipeList.RecipeEntry recipe) {
        this.entry = recipe;
    }

    @Override
    public void getIngredients(IIngredients components)
    {
        components.setInputLists(ItemStack.class, Arrays.asList(this.entry.getInput().getInputs()));
        components.setInputs(FluidStack.class, Arrays.asList(new FluidStack(FluidRegistry.WATER, TileEntityOreWashingPlant.getRequiredWater(entry.getOutput()))));
        List<List<ItemStack>> outputs = new ArrayList<List<ItemStack>>();
        int count = 0;
        for(ItemStack stack : entry.getOutput().copy().getAllOutputs())
        {
            outputs.add(Arrays.asList(stack));
            count++;
            if(count >= 3)
            {
                break;
            }
        }
        components.setOutputLists(ItemStack.class, outputs);
    }
}
