package trinsdar.ic2c_extras.items.override;

import ic2.core.item.reactor.ItemReactorUraniumRod;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;

public class ItemReactorUraniumRod2 extends ItemReactorUraniumRod {
    @Override
    public LocaleComp getLangComponent(ItemStack stack) {
        if (Ic2cExtrasRecipes.enableEmptyRods){
            return new LangComponentHolder.LocaleItemComp(super.getLangComponent(stack).getUnlocalizedFully().replace("reactor", "reactor2"));
        }
        return super.getLangComponent(stack);
    }
}
