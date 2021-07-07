package trinsdar.ic2c_extras.asm.mixins;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.util.helpers.CompareableStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.Map;

@Mixin(BasicMachineRecipeList.class)
public class MixinBasicMachineRecipeList {
    @Shadow
    protected Map<CompareableStack, IMachineRecipeList.RecipeEntry> recipeMap;
    @Shadow
    String base;

    public IMachineRecipeList.RecipeEntry getRecipeInAndOutput(ItemStack input, boolean ignore) {
        if (!input.isEmpty()) {
            CompareableStack item = new CompareableStack(input);
            IMachineRecipeList.RecipeEntry entry = this.recipeMap.get(item);
            if (entry == null) {
                entry = this.recipeMap.get(item.asWildCard());
                IC2CExtras.logger.info("Entry null, trying wildcard");
            }

            if (entry != null) {
                IC2CExtras.logger.info("Entry not null");
                IRecipeInput rInput = entry.getInput();
                if (rInput.matches(input)) {
                    IC2CExtras.logger.info("Input matches in recipe");
                    if (ignore || rInput.getAmount() <= input.getCount()) {
                        IC2CExtras.logger.info("Stack has enough");
                        return entry;
                    }
                }
            }
            IC2CExtras.logger.info("Entry null, unable to find item stack " + input.getDisplayName() + "in Recipe List " + base);
        }
        return null;
    }
}
