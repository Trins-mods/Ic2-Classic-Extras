package trinsdar.ic2c_extras.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import trinsdar.ic2c_extras.gtintegration.MaterialGen;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.recipes.RecipeCrushed;

public class Ic2cExtrasOredict {
    public static void init(){
        OreDictionary.registerOre("crushedIron", Registry.ironCrushedOre);
        OreDictionary.registerOre("crushedGold", Registry.goldCrushedOre);
        OreDictionary.registerOre("crushedCopper", Registry.copperCrushedOre);
        OreDictionary.registerOre("crushedTin", Registry.tinCrushedOre);
        OreDictionary.registerOre("crushedSilver", Registry.silverCrushedOre);
        OreDictionary.registerOre("crushedLead", Registry.leadCrushedOre);
        OreDictionary.registerOre("crushedUranium", Registry.uraniumCrushedOre);

        OreDictionary.registerOre("crushedPurifiedIron", Registry.ironPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedGold", Registry.goldPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedCopper", Registry.copperPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedTin", Registry.tinPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedSilver", Registry.silverPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedLead", Registry.leadPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedUranium", Registry.uraniumPurifiedCrushedOre);

        OreDictionary.registerOre("dustTinyIron", Registry.ironTinyDust);
        OreDictionary.registerOre("dustTinyGold", Registry.goldTinyDust);
        OreDictionary.registerOre("dustTinyCopper", Registry.copperTinyDust);
        OreDictionary.registerOre("dustTinyTin", Registry.tinTinyDust);
        OreDictionary.registerOre("dustTinySilver", Registry.silverTinyDust);
        OreDictionary.registerOre("dustTinyLead", Registry.leadTinyDust);
        OreDictionary.registerOre("dustTinyObsidian", Registry.obsidianTinyDust);
        OreDictionary.registerOre("dustTinyBronze", Registry.bronzeTinyDust);

        OreDictionary.registerOre("dustSmallIron", Registry.ironSmallDust);
        OreDictionary.registerOre("dustSmallGold", Registry.goldSmallDust);
        OreDictionary.registerOre("dustSmallCopper", Registry.copperSmallDust);
        OreDictionary.registerOre("dustSmallTin", Registry.tinSmallDust);
        OreDictionary.registerOre("dustSmallSilver", Registry.silverSmallDust);
        OreDictionary.registerOre("dustSmallLead", Registry.leadSmallDust);
        OreDictionary.registerOre("dustSmallObsidian", Registry.obsidianSmallDust);
        OreDictionary.registerOre("dustSmallBronze", Registry.bronzeSmallDust);

        if (Ic2cExtrasRecipes.enableHarderUranium){
            OreDictionary.registerOre("dustTinyUranium235", Registry.uranium235TinyDust);
            OreDictionary.registerOre("dustTinyUranium238", Registry.uranium238TinyDust);
            OreDictionary.registerOre("dustTinyPlutonium", Registry.plutoniumTinyDust);
            OreDictionary.registerOre("dustSmallUranium235", Registry.uranium235SmallDust);
            OreDictionary.registerOre("dustSmallUranium238", Registry.uranium238SmallDust);
            OreDictionary.registerOre("dustSmallPlutonium", Registry.plutoniumSmallDust);
            OreDictionary.registerOre("dustUranium235", Registry.uranium235);
            OreDictionary.registerOre("dustUranium238", Registry.uranium238);
            OreDictionary.registerOre("dustPlutonium", Registry.plutonium);
        }

        OreDictionary.registerOre("dustLead", Registry.leadDust);
        OreDictionary.registerOre("ingotLead", Registry.leadIngot);
        OreDictionary.registerOre("ingotSteel", Registry.steelIngot);
        if (!Loader.isModLoaded("gtclassic")){
            OreDictionary.registerOre("plateRefinedIron", Registry.refinedIronPlate);
        }
        if (!Loader.isModLoaded("railcraft") && !Loader.isModLoaded("immersiveengineering") && !Loader.isModLoaded("gtclassic")){
            OreDictionary.registerOre("plateBronze", Registry.bronzePlate);
            OreDictionary.registerOre("plateSteel", Registry.steelPlate);
        }
        OreDictionary.registerOre("dustStone", Registry.stoneDust);
        OreDictionary.registerOre("dustDiamond", Registry.diamondDust);
        OreDictionary.registerOre("itemSlag", Registry.slag);

        OreDictionary.registerOre("casingCopper", Registry.copperCasing);
        OreDictionary.registerOre("casingTin", Registry.tinCasing);
        OreDictionary.registerOre("casingSilver", Registry.silverCasing);
        OreDictionary.registerOre("casingLead", Registry.leadCasing);
        OreDictionary.registerOre("casingIron", Registry.ironCasing);
        OreDictionary.registerOre("casingGold", Registry.goldCasing);
        OreDictionary.registerOre("casingRefinedIron", Registry.refinedIronCasing);
        OreDictionary.registerOre("casingSteel", Registry.steelCasing);
        OreDictionary.registerOre("casingBronze", Registry.bronzeCasing);

        OreDictionary.registerOre("craftingToolForgeHammer", new ItemStack(Registry.craftingHammer, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("craftingToolWireCutter", new ItemStack(Registry.wireCutters, 1, OreDictionary.WILDCARD_VALUE));

        OreDictionary.registerOre("blockSteel", Registry.steelBlock);
        OreDictionary.registerOre("blockRefinedIron", Registry.refinedIronBlock);
        OreDictionary.registerOre("blockLead", Registry.leadBlock);

        if (Loader.isModLoaded("gtclassic")){
            for (RecipeCrushed.RecipeCauldronEnum recipes : RecipeCrushed.RecipeCauldronEnum.values()) {
                OreDictionary.registerOre("crushed" + recipes.getInput().getDisplayName(), MaterialGen.getStack(recipes.getInput(), "crushedore", 1));
            }
            for (RecipeCrushed.RecipeThermalCentrifugeEnum recipes : RecipeCrushed.RecipeThermalCentrifugeEnum.values()) {
                OreDictionary.registerOre("crushedPurified" + recipes.getInput().getDisplayName(), MaterialGen.getStack(recipes.getInput(), "purifiedcrushedore", 1));
            }
        }
    }
}
