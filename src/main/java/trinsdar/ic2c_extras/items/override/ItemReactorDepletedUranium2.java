package trinsdar.ic2c_extras.items.override;

import ic2.core.item.reactor.ItemReactorDepletedUranium;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;

public class ItemReactorDepletedUranium2 extends ItemReactorDepletedUranium {
    @Override
    public LocaleComp getLangComponent(ItemStack stack) {
        if (Ic2cExtrasRecipes.enableEmptyRods){
            int meta = stack.getMetadata();
            if (meta == 0){
                return new LangComponentHolder.LocaleItemComp("item.reactorIsotopeRod");
            }
            if (meta == 1){
                return new LangComponentHolder.LocaleItemComp("item.itemRodRedstoneEnrichedUranEmpty");
            }
            if (meta == 2){
                return new LangComponentHolder.LocaleItemComp("item.itemRodBlazeEnrichedUranEmpty");
            }
            if (meta == 3){
                return new LangComponentHolder.LocaleItemComp("item.itemRodEnderPearlEnrichedUranEmpty");
            }
            if (meta == 4){
                return new LangComponentHolder.LocaleItemComp("item.itemRodNetherStarEnrichedUranEmpty");
            }
            if (meta == 5){
                return new LangComponentHolder.LocaleItemComp("item.itemRodCharcoalEnrichedUranEmpty");
            }
        }
        return super.getLangComponent(stack);
    }
}
