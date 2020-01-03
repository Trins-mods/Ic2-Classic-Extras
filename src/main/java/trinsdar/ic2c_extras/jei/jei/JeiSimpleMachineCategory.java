package trinsdar.ic2c_extras.jei.jei;

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

public class JeiSimpleMachineCategory implements IRecipeCategory<JeiSimpleMachineWrapper> {
    ItemStack displayName;
    IDrawable draw;
    IDrawable slot;
    IDrawable arrow;
    IDrawable progress;
    IDrawable charge;
    String id;

    public JeiSimpleMachineCategory(IGuiHelper helper, ItemStack displayName, String id) {
        this.displayName = displayName;
        ResourceLocation texture = new ResourceLocation(IC2CExtras.MODID, "textures/guisprites/jei/jei" + id + ".png");
        this.id = id;
        this.draw = helper.createDrawable(texture, 50, 15, 90, getHeight());
        IDrawableStatic progressPic = helper.createDrawable(texture, 176, 14, 23, 16);
        this.progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
        IDrawableStatic chargePic = helper.createDrawable(texture, 176, 0, 13, 14);
        this.charge = helper.createAnimatedDrawable(chargePic, 500, IDrawableAnimated.StartDirection.TOP, true);
    }

    public int getHeight(){
        return Ic2cExtrasConfig.debugMode ? 70 : 60;
    }

    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft arg0) {
        this.progress.draw(arg0, 29, 19);
        this.charge.draw(arg0, 6, 21);
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
        return id;
    }

    @Override
    public void setRecipe(IRecipeLayout layout, JeiSimpleMachineWrapper arg1, IIngredients ingridient) {
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        guiItemStacks.init(0, true, 5, 1);
        guiItemStacks.init(1, false, 65, 19);
        guiItemStacks.set(ingridient);

    }
}
