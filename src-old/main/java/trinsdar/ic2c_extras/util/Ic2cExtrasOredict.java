package trinsdar.ic2c_extras.util;

import ic2.core.platform.registry.Ic2Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class Ic2cExtrasOredict {
    public static void init() {
        OreDictionary.registerOre("crushedIron", Registry.ironCrushedOre);
        OreDictionary.registerOre("crushedGold", Registry.goldCrushedOre);
        OreDictionary.registerOre("crushedCopper", Registry.copperCrushedOre);
        OreDictionary.registerOre("crushedTin", Registry.tinCrushedOre);
        OreDictionary.registerOre("crushedSilver", Registry.silverCrushedOre);
        OreDictionary.registerOre("crushedLead", Registry.leadCrushedOre);
        OreDictionary.registerOre("crushedUranium", Registry.uraniumCrushedOre);
        OreDictionary.registerOre("crushedAluminum", Registry.aluminumCrushedOre);
        OreDictionary.registerOre("crushedAluminium", Registry.aluminumCrushedOre);
        OreDictionary.registerOre("crushedNickel", Registry.nickelCrushedOre);
        OreDictionary.registerOre("crushedPlatinum", Registry.platinumCrushedOre);

        OreDictionary.registerOre("crushedPurifiedIron", Registry.ironPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedGold", Registry.goldPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedCopper", Registry.copperPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedTin", Registry.tinPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedSilver", Registry.silverPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedLead", Registry.leadPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedUranium", Registry.uraniumPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedAluminum", Registry.aluminumPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedAluminium", Registry.aluminumPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedNickel", Registry.nickelPurifiedCrushedOre);
        OreDictionary.registerOre("crushedPurifiedPlatinum", Registry.platinumPurifiedCrushedOre);
        OreDictionary.registerOre("crushedCentrifugedUranium", Registry.refinedUraniumOre);

        OreDictionary.registerOre("dustTinyIron", Registry.ironTinyDust);
        OreDictionary.registerOre("dustTinyGold", Registry.goldTinyDust);
        OreDictionary.registerOre("dustTinyCopper", Registry.copperTinyDust);
        OreDictionary.registerOre("dustTinyTin", Registry.tinTinyDust);
        OreDictionary.registerOre("dustTinySilver", Registry.silverTinyDust);
        OreDictionary.registerOre("dustTinyLead", Registry.leadTinyDust);
        OreDictionary.registerOre("dustTinyObsidian", Registry.obsidianTinyDust);
        OreDictionary.registerOre("dustTinyBronze", Registry.bronzeTinyDust);
        OreDictionary.registerOre("dustTinyAluminum", Registry.aluminumTinyDust);
        OreDictionary.registerOre("dustTinyAluminium", Registry.aluminumTinyDust);
        OreDictionary.registerOre("dustTinyNickel", Registry.nickelTinyDust);
        OreDictionary.registerOre("dustTinyPlatinum", Registry.platinumTinyDust);

        OreDictionary.registerOre("dustSmallIron", Registry.ironSmallDust);
        OreDictionary.registerOre("dustSmallGold", Registry.goldSmallDust);
        OreDictionary.registerOre("dustSmallCopper", Registry.copperSmallDust);
        OreDictionary.registerOre("dustSmallTin", Registry.tinSmallDust);
        OreDictionary.registerOre("dustSmallSilver", Registry.silverSmallDust);
        OreDictionary.registerOre("dustSmallLead", Registry.leadSmallDust);
        OreDictionary.registerOre("dustSmallObsidian", Registry.obsidianSmallDust);
        OreDictionary.registerOre("dustSmallBronze", Registry.bronzeSmallDust);

        OreDictionary.registerOre("dustTinyUranium233", Registry.uranium233TinyDust);
        OreDictionary.registerOre("dustTinyUranium235", Registry.uranium235TinyDust);
        OreDictionary.registerOre("dustTinyUranium238", Registry.uranium238TinyDust);
        OreDictionary.registerOre("dustTinyPlutonium", Registry.plutoniumTinyDust);
        OreDictionary.registerOre("dustTinyThorium", Registry.thoriumTinyDust);
        OreDictionary.registerOre("dustSmallUranium235", Registry.uranium235SmallDust);
        OreDictionary.registerOre("dustSmallUranium238", Registry.uranium238SmallDust);
        OreDictionary.registerOre("dustSmallPlutonium", Registry.plutoniumSmallDust);
        OreDictionary.registerOre("dustUranium235", Registry.uranium235);
        OreDictionary.registerOre("dustUranium233", Registry.uranium233Dust);
        OreDictionary.registerOre("dustUranium238", Registry.uranium238);
        OreDictionary.registerOre("dustPlutonium", Registry.plutoniumDust);
        OreDictionary.registerOre("dustThorium", Registry.thoriumDust);
        OreDictionary.registerOre("dustUranium", Registry.uraniumDust);
        OreDictionary.registerOre("ingotPlutonium", Registry.plutoniumIngot);
        OreDictionary.registerOre("ingotThorium", Registry.thoriumIngot);
        OreDictionary.registerOre("ingotUranium233", Registry.uranium233Ingot);
        OreDictionary.registerOre("ingotUranium235", Registry.uranium235Ingot);

        OreDictionary.registerOre("dustLead", Registry.leadDust);
        OreDictionary.registerOre("ingotLead", Registry.leadIngot);
        OreDictionary.registerOre("ingotSteel", Registry.steelIngot);
        OreDictionary.registerOre("plateCopper", Registry.copperPlate);
        OreDictionary.registerOre("plateTin", Registry.tinPlate);
        OreDictionary.registerOre("plateSilver", Registry.silverPlate);
        OreDictionary.registerOre("plateLead", Registry.leadPlate);
        OreDictionary.registerOre("plateIron", Registry.ironPlate);
        OreDictionary.registerOre("plateGold", Registry.goldPlate);
        OreDictionary.registerOre("plateRefinedIron", Registry.refinedIronPlate);
        OreDictionary.registerOre("plateSteel", Registry.steelPlate);
        OreDictionary.registerOre("plateBronze", Registry.bronzePlate);
        OreDictionary.registerOre("plateDenseIron", Registry.denseIronPlate);
        OreDictionary.registerOre("plateDenseLead", Registry.denseLeadPlate);
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
        if (!Loader.isModLoaded("gtc_expansion")){
            OreDictionary.registerOre("machineBlockBasic", Ic2Items.machine);
            OreDictionary.registerOre("machineBlockAdvanced", Ic2Items.advMachine);
        }
    }
}
