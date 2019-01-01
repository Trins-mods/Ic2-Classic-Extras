package trinsdar.ic2c_extras.util;

import net.minecraftforge.oredict.OreDictionary;
import trinsdar.ic2c_extras.util.registry.RegistryBlock;
import trinsdar.ic2c_extras.util.registry.RegistryItem;

public class Ic2cExtrasOredict {
    public static void init(){
        OreDictionary.registerOre("crushedIron", RegistryItem.ironCrushedOre);
        OreDictionary.registerOre("crushedGold", RegistryItem.goldCrushedOre);
        OreDictionary.registerOre("crushedCopper", RegistryItem.copperCrushedOre);
        OreDictionary.registerOre("crushedTin", RegistryItem.tinCrushedOre);
        OreDictionary.registerOre("crushedSilver", RegistryItem.silverCrushedOre);
        OreDictionary.registerOre("crushedLead", RegistryItem.leadCrushedOre);
        OreDictionary.registerOre("crushedUranium", RegistryItem.uraniumCrushedOre);

        OreDictionary.registerOre("crushedPurifiedIron", RegistryItem.ironPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedGold", RegistryItem.goldPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedCopper", RegistryItem.copperPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedTin", RegistryItem.tinPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedSilver", RegistryItem.silverPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedLead", RegistryItem.leadPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedUranium", RegistryItem.uraniumPurifiedCrushedOre);

        OreDictionary.registerOre("dustTinyIron", RegistryItem.ironTinyDust);
        OreDictionary.registerOre("dustTinyGold", RegistryItem.goldTinyDust);
        OreDictionary.registerOre("dustTinyCopper", RegistryItem.copperTinyDust);
        OreDictionary.registerOre("dustTinyTin", RegistryItem.tinTinyDust);
        OreDictionary.registerOre("dustTinySilver", RegistryItem.silverTinyDust);
        OreDictionary.registerOre("dustTinyLead", RegistryItem.leadTinyDust);
        OreDictionary.registerOre("dustTinyObsidian", RegistryItem.obsidianTinyDust);
        OreDictionary.registerOre("dustTinyBronze", RegistryItem.bronzeTinyDust);

        if (Ic2cExtrasRecipes.enableHarderUranium){
            OreDictionary.registerOre("dustTinyUranium235", RegistryItem.uranium235TinyDust);
            OreDictionary.registerOre("dustTinyUranium238", RegistryItem.uranium238TinyDust);
            OreDictionary.registerOre("dustTinyPlutonium", RegistryItem.plutoniumTinyDust);
            OreDictionary.registerOre("dustUranium235", RegistryItem.uranium235);
            OreDictionary.registerOre("dustUranium238", RegistryItem.uranium238);
            OreDictionary.registerOre("dustPlutonium", RegistryItem.plutonium);
        }

        OreDictionary.registerOre("dustLead", RegistryItem.leadDust);
        OreDictionary.registerOre("ingotLead", RegistryItem.leadIngot);
        OreDictionary.registerOre("ingotSteel", RegistryItem.steelIngot);
        OreDictionary.registerOre("plateRefinedIron", RegistryItem.refinedIronPlate);
        OreDictionary.registerOre("dustStone", RegistryItem.stoneDust);

        OreDictionary.registerOre("casingCopper", RegistryItem.copperCasing);
        OreDictionary.registerOre("casingTin", RegistryItem.tinCasing);
        OreDictionary.registerOre("casingSilver", RegistryItem.silverCasing);
        OreDictionary.registerOre("casingLead", RegistryItem.leadCasing);
        OreDictionary.registerOre("casingIron", RegistryItem.ironCasing);
        OreDictionary.registerOre("casingGold", RegistryItem.goldCasing);
        OreDictionary.registerOre("casingRefinedIron", RegistryItem.refinedIronCasing);
        OreDictionary.registerOre("casingSteel", RegistryItem.steelCasing);
        OreDictionary.registerOre("casingBronze", RegistryItem.bronzeCasing);

        OreDictionary.registerOre("craftingToolForgeHammer", RegistryItem.craftingHammer);
        OreDictionary.registerOre("craftingToolWireCutter", RegistryItem.wireCutters);

        OreDictionary.registerOre("blockSteel", RegistryBlock.steelBlock);
        OreDictionary.registerOre("blockRefinedIron", RegistryBlock.refinedIronBlock);
        OreDictionary.registerOre("blockLead", RegistryBlock.leadBlock);
    }
}
