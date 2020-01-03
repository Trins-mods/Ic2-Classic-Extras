package trinsdar.ic2c_extras.jei.jei;

import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.core.platform.registry.Ic2Formatters;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

import java.awt.*;
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
        font.drawString("Ticks: " + getEntryTicks(entry.getOutput()), 5, 60, Color.black.getRGB());
        font.drawString("Seconds: " + getEntryTicks(entry.getOutput()) / 20.0F, 5, 70, Color.black.getRGB());
        font.drawString("Cost: " + getEntryTicks(entry.getOutput()) * 20
                + " EU", 5, 80, Color.black.getRGB());
        if (Ic2cExtrasConfig.debugMode) {
            font.drawString("Recipe Id: " + entry.getRecipeID(), 5, 90, Color.black.getRGB());
        }
    }

    public static int getEntryTicks(MachineOutput output) {
        if (output == null || output.getMetadata() == null) {
            return 400;
        }
        return (400 + output.getMetadata().getInteger("RecipeTime"));
    }
}
