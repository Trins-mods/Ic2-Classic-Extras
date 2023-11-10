package trinsdar.ic2c_extras.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
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

public class JeiOreWashingCategory implements IRecipeCategory<JeiOreWashingWrapper> {
    ItemStack displayName;
    IDrawable draw;
    IDrawable overlay;
    IDrawable slot;
    IDrawable arrow;
    IDrawable progress;
    IDrawable charge;

    public JeiOreWashingCategory(IGuiHelper helper) {

        displayName = new ItemStack(Registry.oreWashingPlant);
        ResourceLocation texture = new ResourceLocation(IC2CExtras.MODID, "textures/gui/jei/jeiorewashingplant.png");
        this.draw = helper.createDrawable(texture, 28, 10, 102, getHeight());
        this.overlay = helper.createDrawable(texture, 176, 33, 16, 58);
        IDrawableStatic progressPic = helper.createDrawable(texture, 176, 14, 20, 19);
        this.progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
        IDrawableStatic chargePic = helper.createDrawable(texture, 176, 0, 13, 14);
        this.charge = helper.createAnimatedDrawable(chargePic, 500, IDrawableAnimated.StartDirection.TOP, true);
    }

    public int getHeight() {
        return Ic2cExtrasConfig.debugMode ? 73 : 63;
    }

    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft arg0) {
        this.progress.draw(arg0, 53, 23);
        this.charge.draw(arg0, 28, 26);
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
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();
        guiItemStacks.init(0, true, 27, 6); //input
        guiFluidStacks.init(1, true, 4, 3, 16, 58, 10000, true, this.overlay);
        guiItemStacks.init(2, false, 82, 6); //outputs
        guiItemStacks.init(3, false, 82, 24);
        guiItemStacks.init(4, false, 82, 42);
        guiFluidStacks.set(ingridient);
        guiItemStacks.set(ingridient);

    }
}
