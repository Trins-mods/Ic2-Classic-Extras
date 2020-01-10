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

public class JeiThermalCentrifugeCategory implements IRecipeCategory<JeiThermalCentrifugeWrapper> {
    ItemStack displayName;
    IDrawable draw;
    IDrawable slot;
    IDrawable arrow;
    IDrawable progress;
    IDrawable charge;
    IDrawable heat;

    public JeiThermalCentrifugeCategory(IGuiHelper helper) {
        displayName = new ItemStack(Registry.thermalCentrifuge);
        ResourceLocation texture = new ResourceLocation(IC2CExtras.MODID, "textures/guisprites/jei/jeithermalcentrifuge.png");
        this.draw = helper.createDrawable(texture, 5, 14, 124, getHeight());
        IDrawableStatic progressPic = helper.createDrawable(texture, 176, 14, 45, 17);
        this.progress = helper.createAnimatedDrawable(progressPic, 150, IDrawableAnimated.StartDirection.LEFT, false);
        IDrawableStatic heatPic = helper.createDrawable(texture, 176, 31, 24, 17);
        this.heat = helper.createAnimatedDrawable(heatPic, 300, IDrawableAnimated.StartDirection.LEFT, false);
        IDrawableStatic chargePic = helper.createDrawable(texture, 176, 0, 13, 14);
        this.charge = helper.createAnimatedDrawable(chargePic, 500, IDrawableAnimated.StartDirection.TOP, true);
    }

    public int getHeight(){
        return Ic2cExtrasConfig.debugMode ? 100 : 90;
    }

    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft arg0) {
        this.progress.draw(arg0, 42, 20);
        this.heat.draw(arg0, 52, 38);
        this.charge.draw(arg0, 6, 22);
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
        return "thermalCentrifuge";
    }

    @Override
    public void setRecipe(IRecipeLayout layout, JeiThermalCentrifugeWrapper arg1, IIngredients ingridient) {
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        guiItemStacks.init(0, true, 5, 2); //input
        guiItemStacks.init(1, false, 105, 2); //outputs
        guiItemStacks.init(2, false, 105, 20);
        guiItemStacks.init(3, false, 105, 38);
        guiItemStacks.set(ingridient);

    }
}
