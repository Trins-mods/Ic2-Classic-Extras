package trinsdar.ic2c_extras.util;

import net.minecraftforge.oredict.OreDictionary;

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
    }
}
