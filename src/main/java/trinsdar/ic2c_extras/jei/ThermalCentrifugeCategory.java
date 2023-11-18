package trinsdar.ic2c_extras.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import ic2.api.recipes.misc.RecipeMods;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.jeiplugin.core.recipes.categories.BasicMachineCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import trinsdar.ic2c_extras.blockentity.TileEntityThermalCentrifuge;

import java.util.List;

public class ThermalCentrifugeCategory extends BasicMachineCategory {
    IDrawable heat;
    public ThermalCentrifugeCategory(IGuiHelper helper, RecipeType<IMachineRecipeList.RecipeEntry> location, ResourceLocation texture, ItemLike provider) {
        super(helper, location, texture, provider);
        background = helper.drawableBuilder(texture, 13, 12, 117, 60).addPadding(0, 10, 0, 0).build();
        progress = helper.drawableBuilder(texture, 176, 14, 45, 17).buildAnimated(150, IDrawableAnimated.StartDirection.LEFT, false);
        charge = helper.drawableBuilder(texture, 176, 0, 13, 14).buildAnimated(500, IDrawableAnimated.StartDirection.TOP, true);
        heat = helper.drawableBuilder(texture, 176, 31, 24, 17).buildAnimated(300, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(IMachineRecipeList.RecipeEntry recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        this.progress.draw(stack, 35, 23);
        this.heat.draw(stack, 43, 40);
        this.charge.draw(stack, 4, 24);
        int duration = getDuration(recipe);
        Minecraft.getInstance().font.drawShadow(stack, Component.translatable("jei_info.ic2c_extras.ticks", duration), 3, 60, 0xffffff);
        int heat = TileEntityThermalCentrifuge.getRequiredHeat(recipe.getOutput());
        Minecraft.getInstance().font.drawShadow(stack, Component.translatable("jei_info.ic2c_extras.heat", heat), 35,12, 0xFFFFFF);
    }

    protected int getDuration(IMachineRecipeList.RecipeEntry entry) {
        CompoundTag nbt = entry.getOutput().getMetadata();
        return RecipeMods.RECIPE_TIME.apply(nbt, 200);
    }

    public void setRecipe(IRecipeLayoutBuilder layout, IMachineRecipeList.RecipeEntry recipe, IFocusGroup focus) {
        layout.addSlot(RecipeIngredientRole.INPUT, 4, 5).addItemStacks(recipe.getInputs()[0].getComponents());
        List<ItemStack> outputs = recipe.getOutput().getAllOutputs();
        layout.addSlot(RecipeIngredientRole.OUTPUT, 98, 5).addItemStack(outputs.get(0));
        if (outputs.size() > 1){
            layout.addSlot(RecipeIngredientRole.OUTPUT, 98, 23).addItemStack(outputs.get(1));
        }
        if (outputs.size() > 2){
            layout.addSlot(RecipeIngredientRole.OUTPUT, 98, 41).addItemStack(outputs.get(2));
        }
    }
}
