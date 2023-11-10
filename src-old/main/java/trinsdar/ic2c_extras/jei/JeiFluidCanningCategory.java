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

public class JeiFluidCanningCategory implements IRecipeCategory<JeiFluidCanningWrapper> {
    ItemStack displayName;
    IDrawable draw;
    IDrawable overlay;
    IDrawable slot;
    IDrawable arrow;
    IDrawable progress;
    IDrawable charge;
    String id;

    public JeiFluidCanningCategory(IGuiHelper helper) {
        this.displayName = new ItemStack(Registry.fluidCanningMachine);
        ResourceLocation texture = new ResourceLocation(IC2CExtras.MODID, "textures/gui/jei/jeifluidcanningmachine.png");
        this.draw = helper.createDrawable(texture, 15, 11, 124, getHeight());
        this.overlay = helper.createDrawable(texture, 176, 33, 16, 58);
        IDrawableStatic progressPic = helper.createDrawable(texture, 176, 14, 23, 17);
        this.progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
        IDrawableStatic chargePic = helper.createDrawable(texture, 176, 0, 13, 14);
        this.charge = helper.createAnimatedDrawable(chargePic, 500, IDrawableAnimated.StartDirection.TOP, true);
    }

    public int getHeight() {
        return Ic2cExtrasConfig.debugMode ? 72 : 62;
    }

    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft arg0) {
        this.progress.draw(arg0, 49, 24);
        this.charge.draw(arg0, 29, 26);
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
        return "fluidCanning";
    }

    @Override
    public void setRecipe(IRecipeLayout layout, JeiFluidCanningWrapper arg1, IIngredients ingridient) {
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        IGuiFluidStackGroup group = layout.getFluidStacks();
        guiItemStacks.init(0, true, 28, 6);
        group.init(1, true, 6, 2, 16, 58, 10000, true, this.overlay);
        guiItemStacks.init(2, false, 78, 24);
        group.init(3, false, 102, 2, 16, 58, 10000, true, this.overlay);
        group.set(ingridient);
        guiItemStacks.set(ingridient);

    }
}
