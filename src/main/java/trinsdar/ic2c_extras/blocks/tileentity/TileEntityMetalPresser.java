package trinsdar.ic2c_extras.blocks.tileentity;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.MachineType;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TileEntityMetalPresser extends TileEntityBasicElectricMachine {

    public TileEntityMetalPresser() {
        super(4, 5, 400, 32);
    }

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack itemStack) {
        return null;
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return null;
    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return null;
    }

    @Override
    public MachineType getType() {
        return null;
    }
}
