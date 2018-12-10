package trinsdar.ic2c_extras.util.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.util.RegistryBlock;

public class JeiThermalCentrifugeCategory implements IRecipeCategory<JeiThermalCentrifugeWrapper> {
    ItemStack displayName;
    IDrawable draw;
    IDrawable slot;
    IDrawable arrow;

    public JeiThermalCentrifugeCategory(IGuiHelper helper) {
        displayName = new ItemStack(RegistryBlock.thermalCentrifuge);
        ResourceLocation texture = new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guithermalcentrifuge.png");
        this.draw = helper.createDrawable(texture, 5, 14, 124, 60);
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
