package trinsdar.ic2c_extras.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.util.Registry;

public class JeiMetalBenderCategory implements IRecipeCategory<JeiMetalBenderWrapper> {
    ItemStack displayName;
    IDrawable draw;
    IDrawable slot;
    IDrawable arrow;
    IDrawable progress;
    IDrawable charge;
    String id;

    public JeiMetalBenderCategory(IGuiHelper helper) {
        this.displayName = new ItemStack(Registry.metalBender);
        ResourceLocation texture = new ResourceLocation(IC2CExtras.MODID, "textures/gui/jei/jeimetalbender.png");
        this.id = id;
        this.draw = helper.createDrawable(texture, 41, 15, 99, getHeight());
        IDrawableStatic progressPic = helper.createDrawable(texture, 176, 14, 23, 16);
        this.progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
        IDrawableStatic chargePic = helper.createDrawable(texture, 176, 0, 13, 14);
        this.charge = helper.createAnimatedDrawable(chargePic, 500, IDrawableAnimated.StartDirection.TOP, true);
    }

    public int getHeight() {
        return Ic2cExtrasConfig.debugMode ? 70 : 60;
    }

    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft arg0) {
        this.progress.draw(arg0, 38, 19);
        this.charge.draw(arg0, 15, 21);
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
        return "metalBender";
    }

    @Override
    public void setRecipe(IRecipeLayout layout, JeiMetalBenderWrapper arg1, IIngredients ingridient) {
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        guiItemStacks.init(0, true, 5, 1);
        guiItemStacks.init(1, true, 23, 1);
        guiItemStacks.init(2, false, 74, 19);
        guiItemStacks.set(ingridient);

    }
}
