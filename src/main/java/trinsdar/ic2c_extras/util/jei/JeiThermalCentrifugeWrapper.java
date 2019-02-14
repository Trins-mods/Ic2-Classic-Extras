package trinsdar.ic2c_extras.util.jei;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.registry.Ic2Formatters;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JeiThermalCentrifugeWrapper extends BlankRecipeWrapper {
    RecipeEntry entry;

    public JeiThermalCentrifugeWrapper(RecipeEntry recipe) {
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
            if(count >= 3)
            {
                break;
            }
        }
        components.setOutputLists(ItemStack.class, outputs);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        FontRenderer font = minecraft.fontRenderer;
        font.drawString(Ic2cExtrasLang.jeiHeat.getLocalizedFormatted(Ic2Formatters.bigFormat.format((long) TileEntityThermalCentrifuge.getRequiredHeat(entry.getOutput()))), 44, 12, Color.gray.getRGB());
    }
}
