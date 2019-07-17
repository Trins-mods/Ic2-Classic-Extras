package trinsdar.ic2c_extras.util.references;

import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.lang.storage.Ic2ItemLang;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.Registry;

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

	public static LocaleComp singleUOXRod = new LangComponentHolder.LocaleItemComp("item.singleUOXRod");
    public static LocaleComp singlePlutoniumRod = new LangComponentHolder.LocaleItemComp("item.singlePlutoniumRod");
    public static LocaleComp singleMOXRod = new LangComponentHolder.LocaleItemComp("item.singleMOXRod");
    public static LocaleComp singleThorium232Rod = new LangComponentHolder.LocaleItemComp("item.singleThorium232Rod");
    public static LocaleComp singleThorium230Rod = new LangComponentHolder.LocaleItemComp("item.singleThorium230Rod");
    public static LocaleComp doubleUOXRod = new LangComponentHolder.LocaleItemComp("item.doubleUOXRod");
    public static LocaleComp doublePlutoniumRod = new LangComponentHolder.LocaleItemComp("item.doublePlutoniumRod");
    public static LocaleComp doubleMOXRod = new LangComponentHolder.LocaleItemComp("item.doubleMOXRod");
    public static LocaleComp doubleThorium232Rod = new LangComponentHolder.LocaleItemComp("item.doubleThorium232Rod");
    public static LocaleComp doubleThorium230Rod = new LangComponentHolder.LocaleItemComp("item.doubleThorium230Rod");
    public static LocaleComp quadUOXRod = new LangComponentHolder.LocaleItemComp("item.quadUOXRod");
    public static LocaleComp quadPlutoniumRod = new LangComponentHolder.LocaleItemComp("item.quadPlutoniumRod");
    public static LocaleComp quadMOXRod = new LangComponentHolder.LocaleItemComp("item.quadMOXRod");
    public static LocaleComp quadThorium232Rod = new LangComponentHolder.LocaleItemComp("item.quadThorium232Rod");
    public static LocaleComp quadThorium230Rod = new LangComponentHolder.LocaleItemComp("item.quadThorium230Rod");
    public static LocaleComp nearDepletedUOXRod = new LangComponentHolder.LocaleItemComp("item.nearDepletedUOXRod");
    public static LocaleComp nearDepletedPlutoniumRod = new LangComponentHolder.LocaleItemComp("item.nearDepletedPlutoniumRod");
    public static LocaleComp nearDepletedMOXRod = new LangComponentHolder.LocaleItemComp("item.nearDepletedMOXRod");
    public static LocaleComp nearDepletedThorium232Rod = new LangComponentHolder.LocaleItemComp("item.nearDepletedThorium232Rod");
    public static LocaleComp nearDepletedThorium230Rod = new LangComponentHolder.LocaleItemComp("item.nearDepletedThorium230Rod");
    public static LocaleComp isotopicUOXRod = new LangComponentHolder.LocaleItemComp("item.isotopicUOXRod");
    public static LocaleComp isotopicPlutoniumRod = new LangComponentHolder.LocaleItemComp("item.isotopicPlutoniumRod");
    public static LocaleComp isotopicMOXRod = new LangComponentHolder.LocaleItemComp("item.isotopicMOXRod");
    public static LocaleComp isotopicThorium232Rod = new LangComponentHolder.LocaleItemComp("item.isotopicThorium232Rod");
    public static LocaleComp isotopicThorium230Rod = new LangComponentHolder.LocaleItemComp("item.isotopicThorium230Rod");
    public static LocaleComp reEnrichedUOXRod = new LangComponentHolder.LocaleItemComp("item.reEnrichedUOXRod");
    public static LocaleComp reEnrichedPlutoniumRod = new LangComponentHolder.LocaleItemComp("item.reEnrichedPlutoniumRod");
    public static LocaleComp reEnrichedMOXRod = new LangComponentHolder.LocaleItemComp("item.reEnrichedMOXRod");
    public static LocaleComp reEnrichedThorium232Rod = new LangComponentHolder.LocaleItemComp("item.reEnrichedThorium232Rod");
    public static LocaleComp reEnrichedThorium230Rod = new LangComponentHolder.LocaleItemComp("item.reEnrichedThorium230Rod");
    public static void overrideLang(){
        if (Ic2cExtrasRecipes.enableEmptyRods){
        	Registry.singleUOXCell.setUnlocalizedName(singleUOXRod);
        	Registry.doubleUOXCell.setUnlocalizedName(doubleUOXRod);
        	Registry.quadUOXCell.setUnlocalizedName(quadUOXRod);
            Registry.nearDepletedUOXCell.setUnlocalizedName(nearDepletedUOXRod);
            Registry.isotopicUOXCell.setUnlocalizedName(isotopicUOXRod);
            Registry.reEnrichedUOXCell.setUnlocalizedName(reEnrichedUOXRod);

            Registry.singlePlutoniumCell.setUnlocalizedName(singlePlutoniumRod);
        	Registry.doublePlutoniumCell.setUnlocalizedName(doublePlutoniumRod);
        	Registry.quadPlutoniumCell.setUnlocalizedName(quadPlutoniumRod);
            Registry.nearDepletedPlutoniumCell.setUnlocalizedName(nearDepletedPlutoniumRod);
            Registry.isotopicPlutoniumCell.setUnlocalizedName(isotopicPlutoniumRod);
            Registry.reEnrichedPlutoniumCell.setUnlocalizedName(reEnrichedPlutoniumRod);

            Registry.singleMOXCell.setUnlocalizedName(singleMOXRod);
        	Registry.doubleMOXCell.setUnlocalizedName(doubleMOXRod);
        	Registry.quadMOXCell.setUnlocalizedName(quadMOXRod);
            Registry.nearDepletedMOXCell.setUnlocalizedName(nearDepletedMOXRod);
            Registry.isotopicMOXCell.setUnlocalizedName(isotopicMOXRod);
            Registry.reEnrichedMOXCell.setUnlocalizedName(reEnrichedMOXRod);

            Registry.singleThorium232Cell.setUnlocalizedName(singleThorium232Rod);
        	Registry.doubleThorium232Cell.setUnlocalizedName(doubleThorium232Rod);
        	Registry.quadThorium232Cell.setUnlocalizedName(quadThorium232Rod);
            Registry.nearDepletedThorium232Cell.setUnlocalizedName(nearDepletedThorium232Rod);
            Registry.isotopicThorium232Cell.setUnlocalizedName(isotopicThorium232Rod);
            Registry.reEnrichedThorium232Cell.setUnlocalizedName(reEnrichedThorium232Rod);

            Registry.singleThorium230Cell.setUnlocalizedName(singleThorium230Rod);
        	Registry.doubleThorium230Cell.setUnlocalizedName(doubleThorium230Rod);
        	Registry.quadThorium230Cell.setUnlocalizedName(quadThorium230Rod);
            Registry.nearDepletedThorium230Cell.setUnlocalizedName(nearDepletedThorium230Rod);
            Registry.isotopicThorium230Cell.setUnlocalizedName(isotopicThorium230Rod);
            Registry.reEnrichedThorium230Cell.setUnlocalizedName(reEnrichedThorium230Rod);
        }
    }
}
