package trinsdar.ic2c_extras.util.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.util.RegistryBlock;

public class JeiOreWashingCategory implements IRecipeCategory<JeiOreWashingWrapper> {
    ItemStack displayName;
    IDrawable draw;
    IDrawable slot;
    IDrawable arrow;
    IDrawable progress;
    IDrawable charge;

    public JeiOreWashingCategory(IGuiHelper helper) {

        displayName = new ItemStack(RegistryBlock.oreWashingPlant);
        ResourceLocation texture = new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guiorewashingplant.png");
        this.draw = helper.createDrawable(texture, 5, 11, 124, 63);
        IDrawableStatic progressPic = helper.createDrawable(texture, 176, 14, 20, 19);
        this.progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
        IDrawableStatic chargePic = helper.createDrawable(texture, 176, 0, 13, 14);
        this.charge = helper.createAnimatedDrawable(chargePic, 500, IDrawableAnimated.StartDirection.TOP, true);
    }

    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft arg0) {
        this.progress.draw(arg0, 76, 22);
        this.charge.draw(arg0, 51, 25);
    }

    @Override
    public IDrawable getBackground() {
        return this.draw;
    }

    @Override
    public String getModName() {
        return "ic2c_extras";
    }

    @Override
    public String getTitle() {
        return displayName.getDisplayName();
    }

    @Override
    public String getUid() {
        return "oreWashing";
    }

    @Override
    public void setRecipe(IRecipeLayout layout, JeiOreWashingWrapper arg1, IIngredients ingridient) {
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        guiItemStacks.init(0, true, 50, 5); //input
        guiItemStacks.init(1, false, 105, 5); //outputs
        guiItemStacks.init(2, false, 105, 23);
        guiItemStacks.init(3, false, 105, 41);
        guiItemStacks.set(ingridient);

    }
}
