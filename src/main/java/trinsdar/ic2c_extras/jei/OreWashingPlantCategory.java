package trinsdar.ic2c_extras.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import ic2.api.recipes.ingridients.recipes.IRecipeOutput;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.jeiplugin.core.recipes.categories.BasicMachineCategory;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class OreWashingPlantCategory extends BasicMachineCategory {
    IDrawable overlay;
    public OreWashingPlantCategory(IGuiHelper helper, RecipeType<IMachineRecipeList.RecipeEntry> location, ResourceLocation texture, ItemLike provider) {
        super(helper, location, texture, provider);
        background = helper.createDrawable(texture, 28, 10, 102, 62);
        this.overlay = helper.createDrawable(texture, 176, 33, 16, 58);
        progress = helper.drawableBuilder(texture, 176, 14, 20, 19).buildAnimated(150, IDrawableAnimated.StartDirection.LEFT, false);
        charge = helper.drawableBuilder(texture, 176, 0, 13, 14).buildAnimated(500, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public void draw(IMachineRecipeList.RecipeEntry recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        this.progress.draw(stack, 52, 23);
        this.charge.draw(stack, 28, 26);
    }

    public void setRecipe(IRecipeLayoutBuilder layout, IMachineRecipeList.RecipeEntry recipe, IFocusGroup focus) {
        layout.addSlot(RecipeIngredientRole.INPUT, 28, 7).addItemStacks(recipe.getInputs()[0].getComponents());
        List<ItemStack> outputs = recipe.getOutput().getAllOutputs();
        layout.addSlot(RecipeIngredientRole.OUTPUT, 83, 7).addItemStack(outputs.get(0));
        if (outputs.size() > 1){
            layout.addSlot(RecipeIngredientRole.OUTPUT, 83, 25).addItemStack(outputs.get(1));
        }
        if (outputs.size() > 2){
            layout.addSlot(RecipeIngredientRole.OUTPUT, 83, 43).addItemStack(outputs.get(2));
        }
        int water = getRequiredWater(recipe.getOutput());
        if (water > 0){
            layout.addSlot(RecipeIngredientRole.INPUT, 4, 3).addIngredient(ForgeTypes.FLUID_STACK, new FluidStack(Fluids.WATER, water)).setFluidRenderer(1L, false, 16, 58).setOverlay(overlay, 0, 0);
        }
    }

    public static int getRequiredWater(IRecipeOutput output) {
        return output.getMetadata().getInt("amount");
    }
}
