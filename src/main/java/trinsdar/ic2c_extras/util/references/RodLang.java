package trinsdar.ic2c_extras.util.references;

import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2ItemLang;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;

public class RodLang {
    public static LocaleComp nearDepletedUOXCell = new LangComponentHolder.LocaleItemComp("item.nearDepletedUOXCell");
    public static LocaleComp nearDepletedPlutoniumCell = new LangComponentHolder.LocaleItemComp("item.nearDepletedPlutoniumCell");
    public static LocaleComp nearDepletedMOXCell = new LangComponentHolder.LocaleItemComp("item.nearDepletedMOXCell");
    public static LocaleComp nearDepletedThorium232Cell = new LangComponentHolder.LocaleItemComp("item.nearDepletedThorium232Cell");
    public static LocaleComp nearDepletedThorium230Cell = new LangComponentHolder.LocaleItemComp("item.nearDepletedThorium230Cell");
    public static LocaleComp reEnrichedUOXCell = new LangComponentHolder.LocaleItemComp("item.reEnrichedUOXCell");
    public static LocaleComp reEnrichedPlutoniumCell = new LangComponentHolder.LocaleItemComp("item.reEnrichedPlutoniumCell");
    public static LocaleComp reEnrichedMOXCell = new LangComponentHolder.LocaleItemComp("item.reEnrichedMOXCell");
    public static LocaleComp reEnrichedThorium232Cell = new LangComponentHolder.LocaleItemComp("item.reEnrichedThorium232Cell");
    public static LocaleComp reEnrichedThorium230Cell = new LangComponentHolder.LocaleItemComp("item.reEnrichedThorium230Cell");

    public static LocaleComp nearDepletedUOXRod = new LangComponentHolder.LocaleItemComp("item.nearDepletedUOXRod");
    public static LocaleComp nearDepletedPlutoniumRod = new LangComponentHolder.LocaleItemComp("item.nearDepletedPlutoniumRod");
    public static LocaleComp nearDepletedMOXRod = new LangComponentHolder.LocaleItemComp("item.nearDepletedMOXRod");
    public static LocaleComp nearDepletedThorium232Rod = new LangComponentHolder.LocaleItemComp("item.nearDepletedThorium232Rod");
    public static LocaleComp nearDepletedThorium230Rod = new LangComponentHolder.LocaleItemComp("item.nearDepletedThorium230Rod");
    public static LocaleComp reEnrichedUOXRod = new LangComponentHolder.LocaleItemComp("item.reEnrichedUOXRod");
    public static LocaleComp reEnrichedPlutoniumRod = new LangComponentHolder.LocaleItemComp("item.reEnrichedPlutoniumRod");
    public static LocaleComp reEnrichedMOXRod = new LangComponentHolder.LocaleItemComp("item.reEnrichedMOXRod");
    public static LocaleComp reEnrichedThorium232Rod = new LangComponentHolder.LocaleItemComp("item.reEnrichedThorium232Rod");
    public static LocaleComp reEnrichedThorium230Rod = new LangComponentHolder.LocaleItemComp("item.reEnrichedThorium230Rod");
    public static void overrideLang(){
        if (Ic2cExtrasRecipes.enableEmptyRods){
            Ic2ItemLang.uranRodNearDeplete = new LangComponentHolder.LocaleItemComp("item.itemRodUranEmpty");
            Ic2ItemLang.uranRodRedstoneNearDeplete = new LangComponentHolder.LocaleItemComp("item.itemRodRedstoneEnrichedUranEmpty");
        }
    }
}
