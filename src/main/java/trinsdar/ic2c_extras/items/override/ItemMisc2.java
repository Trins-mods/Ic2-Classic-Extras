package trinsdar.ic2c_extras.items.override;

import ic2.core.item.misc.ItemMisc;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;

public class ItemMisc2 extends ItemMisc {
    @Override
    public LocaleComp getLangComponent(ItemStack stack) {
        int meta = stack.getMetadata();
        if(Ic2cExtrasRecipes.enableEmptyRods){
            switch (meta){
                case 180 : return new LangComponentHolder.LocaleItemComp("item.itemFuelUran");
                case 500 : return new LangComponentHolder.LocaleItemComp("item.itemRodUranEmpty");
                case 501 : return new LangComponentHolder.LocaleItemComp("item.itemRodRedstoneEnrichedUranEmpty");
                case 502 : return new LangComponentHolder.LocaleItemComp("item.itemRodBlazeEnrichedUranEmpty");
                case 503 : return new LangComponentHolder.LocaleItemComp("item.itemRodEnderPearlEnrichedUranEmpty");
                case 504 : return new LangComponentHolder.LocaleItemComp("item.itemRodNetherStarEnrichedUranEmpty");
                case 505 : return new LangComponentHolder.LocaleItemComp("item.itemRodCharcoalEnrichedUranEmpty");
                case 550 : return new LangComponentHolder.LocaleItemComp("item.itemRodUranEnriched");
                case 551 : return new LangComponentHolder.LocaleItemComp("item.itemRodRedstoneUranEnriched");
                case 552 : return new LangComponentHolder.LocaleItemComp("item.itemRodBlazeUranEnriched");
                case 553 : return new LangComponentHolder.LocaleItemComp("item.itemRodEnderPearlUranEnriched");
                case 554 : return new LangComponentHolder.LocaleItemComp("item.itemRodNetherStarUranEnriched");
                case 555 : return new LangComponentHolder.LocaleItemComp("item.itemRodCharcoalUranEnriched");
                default: return super.getLangComponent(stack);
            }
        }
        return super.getLangComponent(stack);
    }
}
