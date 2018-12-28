package trinsdar.ic2c_extras.util;

import com.mcmoddev.basemetals.data.MaterialNames;

import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.init.Materials;
import com.mcmoddev.lib.material.MMDMaterial;
import com.mcmoddev.lib.util.Config.Options;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.item.recipe.entry.RecipeInputCombined;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2States;
import ic2.core.util.misc.StackUtil;
import mods.railcraft.api.crafting.Crafters;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.registry.RegistryBlock;
import trinsdar.ic2c_extras.util.registry.RegistryItem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static ic2.api.classic.recipe.ClassicRecipes.macerator;
import static ic2.api.classic.recipe.ClassicRecipes.massfabAmplifier;

public class Ic2cExtrasRecipes {
    public static boolean enableHarderUranium;
    public static boolean enableCasingsRequirePlates;
    public static boolean enableCuttingToolWires;
    public static boolean enableHVCablesRequireSteel;
    public static int
    itemQuality = 0,
    dungeonWeight = 10,
    netherFortressWeight = 15,
    jungleTempleWeight = 15,
    desertTempleWeight = 15,
    strongholdWeight = 20;
    public static IMachineRecipeList rolling = new BasicMachineRecipeList("rolling");
    public static IMachineRecipeList extruding = new BasicMachineRecipeList("extruding");
    public static IMachineRecipeList cutting = new BasicMachineRecipeList("cutting");
    public static IMachineRecipeList oreWashingPlant = new BasicMachineRecipeList("oreWashingPlant");
    public static IMachineRecipeList thermalCentrifuge = new BasicMachineRecipeList("thermalCentrifuge");

    public static MaterialNames materialNamesBme;
    public static com.mcmoddev.modernmetals.data.MaterialNames materialNamesMme;

    static IRecipeInput casing = new RecipeInputCombined(1,
            new IRecipeInput[] { new RecipeInputOreDict("casingRefinedIron"),
                    new RecipeInputOreDict("casingSilver"), new RecipeInputOreDict("casingSteel") });

    static IRecipeInput crushedCopper = new RecipeInputCombined(1,
            new IRecipeInput[] { new RecipeInputOreDict("crushedCopper"),
                    new RecipeInputOreDict("crushedPurifiedCopper") });

    static IRecipeInput crushedTin = new RecipeInputCombined(1,
            new IRecipeInput[] { new RecipeInputOreDict("crushedTin"),
                    new RecipeInputOreDict("crushedPurifiedTin") });


    public static void init(){
        initShapedRecipes();
        initShapelessRecipes();
        initCompressRecipes();
        initFurnaceRecipes();
        initReplaceMaceratorRecipes();
        initMachineRecipes();
        initHarderUraniumProcessing();
        postInit();
        //initRailcraftRecipes();
    }
    static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
    public static void initShapedRecipes(){
        recipes.addRecipe(new ItemStack(RegistryBlock.advancedSteamTurbine, 1),
                " S ", "STS", " S ", 'S', Ic2Items.basicTurbine,'T', Ic2Items.transformerMV);
        recipes.addRecipe(new ItemStack(RegistryBlock.oreWashingPlant, 1),
                "III", "BCB", "McM", 'I', "ingotRefinedIron",'B', Items.BUCKET, 'C', Ic2Items.machine, 'M', Ic2Items.carbonMesh, 'c', "circuitBasic");
        recipes.addRecipe(new ItemStack(RegistryBlock.thermalCentrifuge, 1),
                "CMC", "IAI", "IHI", 'C', RegistryItem.coil,'M', Ic2Items.miningLaser, 'I', "ingotRefinedIron", 'A', Ic2Items.advMachine, 'H', RegistryItem.heatConductor);

        recipes.addRecipe(new ItemStack(RegistryBlock.roller, 1),
                " C ", "TBT", "ctc", 'C', "circuitBasic",'T', Ic2Items.toolBox, 'B', Ic2Items.machine, 'c', RegistryItem.coil, 't', RegistryItem.craftingHammer);

        recipes.addRecipe(new ItemStack(RegistryBlock.extruder, 1),
                " C ", "TBT", "cwc", 'C', "circuitBasic",'T', Ic2Items.toolBox, 'B', Ic2Items.machine, 'c', RegistryItem.coil, 'w', Ic2Items.copperCable);

        recipes.addRecipe(new ItemStack(RegistryBlock.cutter, 1),
                " C ", "TBT", "ctc", 'C', "circuitBasic",'T', Ic2Items.toolBox, 'B', Ic2Items.machine, 'c', RegistryItem.coil, 't', RegistryItem.wireCutters);

        recipes.addRecipe(new ItemStack(RegistryBlock.impellerizedRoller, 1),
                "CCC", "CRC", "CBC", 'R', RegistryBlock.roller,'B', Ic2Items.advMachine, 'C', RegistryItem.craftingHammer);

        recipes.addRecipe(new ItemStack(RegistryBlock.liquescentExtruder, 1),
                "CCC", "CEC", "CBC", 'E', RegistryBlock.extruder,'B', Ic2Items.advMachine, 'C', Ic2Items.goldCable);

        recipes.addRecipe(new ItemStack(RegistryBlock.plasmaCutter, 1),
                "CCC", "CcC", "CBC", 'c', RegistryBlock.cutter,'B', Ic2Items.advMachine, 'C', RegistryItem.wireCutters);

        recipes.addRecipe(new ItemStack(RegistryItem.coil, 1),
                "CCC", "CIC", "CCC", 'I', "ingotRefinedIron",'C', Ic2Items.copperCable);

        recipes.addRecipe(new ItemStack(RegistryItem.heatConductor, 1),
                "RCR", "RCR", "RCR", 'R', "itemRubber",'C', Ic2Items.copperIngot);

        recipes.addRecipe(new ItemStack(RegistryItem.craftingHammer, 1),
                "III", "III", " S ", 'I', "ingotRefinedIron",'S', "stickWood");

        recipes.addRecipe(new ItemStack(RegistryItem.wireCutters, 1),
                "I I", " I ", "S S", 'I', "ingotRefinedIron",'S', "stickWood");

        recipes.addRecipe(Ic2Items.battery,
                " C ", "TRT", "TRT", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'T', "casingTin");

        recipes.addRecipe(Ic2Items.fuelCan,
                " TT", "T T", "TTT", 'T', "casingTin");

        recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.emptyCell, 16),
                " T ", "T T", " T ", 'T', "casingTin");

        if (Loader.isModLoaded("gtclassic")){
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CCC", "RcR", "CCC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', casing);
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CRC", "CcC", "CRC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', casing);
        }else{
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CCC", "RcR", "CCC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', "casingRefinedIron");
            recipes.addRecipe(Ic2Items.electricCircuit,
                    "CRC", "CcC", "CRC", 'C', Ic2Items.insulatedCopperCable,'R', Items.REDSTONE, 'c', "casingRefinedIron");
        }


    }

    public static void initShapelessRecipes(){
        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.bronzeDust, 4), new Object[]{crushedCopper, crushedCopper, crushedCopper, crushedTin});
    }

    public static void dustUtil(String dust, ItemStack dusts, String tinyDust, ItemStack tinyDusts) {
        recipes.addRecipe(StackUtil.copyWithSize(dusts, 1),
                new Object[]{"TTT", "TTT", "TTT", 'T', tinyDust});
        recipes.addShapelessRecipe(StackUtil.copyWithSize(tinyDusts, 9),
                new Object[]{dust});

    }

    public static void ingotUtil(String block, Block blocks, String ingot, ItemStack ingots){
        recipes.addRecipe(new ItemStack(blocks, 1),
                new Object[]{"III", "III", "III", 'I', ingot});
        recipes.addShapelessRecipe(StackUtil.copyWithSize(ingots, 9),
                new Object[]{block});
    }

    public static void initCompressRecipes(){
        Ic2cExtrasRecipes.dustUtil("dustIron", Ic2Items.ironDust, "dustTinyIron", new ItemStack(RegistryItem.ironTinyDust));
        Ic2cExtrasRecipes.dustUtil("dustGold", Ic2Items.goldDust, "dustTinyGold", new ItemStack(RegistryItem.goldTinyDust));
        Ic2cExtrasRecipes.dustUtil("dustCopper", Ic2Items.copperDust, "dustTinyCopper", new ItemStack(RegistryItem.copperTinyDust));
        Ic2cExtrasRecipes.dustUtil("dustTin", Ic2Items.tinDust, "dustTinyTin", new ItemStack(RegistryItem.tinTinyDust));
        Ic2cExtrasRecipes.dustUtil("dustSilver", Ic2Items.silverDust, "dustTinySilver", new ItemStack(RegistryItem.silverTinyDust));
        Ic2cExtrasRecipes.dustUtil("dustLead", new ItemStack(RegistryItem.leadDust), "dustTinyLead", new ItemStack(RegistryItem.leadTinyDust));
        Ic2cExtrasRecipes.dustUtil("dustObsidian", Ic2Items.obsidianDust, "dustTinyObsidian", new ItemStack(RegistryItem.obsidianTinyDust));
        Ic2cExtrasRecipes.dustUtil("dustBronze", Ic2Items.bronzeDust, "dustTinyBronze", new ItemStack(RegistryItem.bronzeTinyDust));
        recipes.addRecipe(Ic2Items.iridiumOre,
                new Object[]{"III", "III", "III", 'I', RegistryItem.iridiumShard});
        recipes.addShapelessRecipe(new ItemStack(RegistryItem.iridiumShard, 9),
                new Object[]{Ic2Items.iridiumOre});

        if (Loader.isModLoaded("gtclassic")){
            TileEntityCompressor.addRecipe("ingotLead", 9, new ItemStack(RegistryBlock.leadBlock));
            TileEntityCompressor.addRecipe("ingotRefinedIron", 9, new ItemStack(RegistryBlock.refinedIronBlock));
            TileEntityCompressor.addRecipe("ingotSteel", 9, new ItemStack(RegistryBlock.steelBlock));
            GameRegistry.addSmelting(RegistryBlock.steelBlock, new ItemStack(RegistryItem.steelIngot, 9), 0.1F);
            GameRegistry.addSmelting(RegistryBlock.refinedIronBlock, StackUtil.copyWithSize(Ic2Items.refinedIronIngot, 9), 0.1F);
            GameRegistry.addSmelting(RegistryBlock.leadBlock, new ItemStack(RegistryItem.leadIngot, 9), 0.1F);
            TileEntityMacerator.addRecipe("blockLead", 1, new ItemStack(RegistryItem.leadDust, 9), 0.1F);
            TileEntityMacerator.addRecipe("blockRefinedIron", 1, StackUtil.copyWithSize(Ic2Items.ironDust, 9), 0.1F);
        }else if (!Loader.isModLoaded("gtclassic")){
            Ic2cExtrasRecipes.ingotUtil("blockSteel", RegistryBlock.steelBlock, "ingotSteel", new ItemStack(RegistryItem.steelIngot));
            Ic2cExtrasRecipes.ingotUtil("blockLead", RegistryBlock.leadBlock, "ingotLead", new ItemStack(RegistryItem.leadIngot));
            Ic2cExtrasRecipes.ingotUtil("blockRefinedIron", RegistryBlock.refinedIronBlock, "ingotRefinedIron", Ic2Items.refinedIronIngot);
        }

    }

    public static void initFurnaceRecipes(){
        GameRegistry.addSmelting(RegistryItem.ironCrushedOre, new ItemStack(Items.IRON_INGOT), 0.7F);
        GameRegistry.addSmelting(RegistryItem.goldCrushedOre, new ItemStack(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(RegistryItem.copperCrushedOre, StackUtil.copyWithSize(Ic2Items.copperIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.tinCrushedOre, StackUtil.copyWithSize(Ic2Items.tinIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.silverCrushedOre, StackUtil.copyWithSize(Ic2Items.silverIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.leadCrushedOre, new ItemStack(RegistryItem.leadIngot), 0.5F);
        GameRegistry.addSmelting(RegistryItem.ironPurifiedCrushedOre, new ItemStack(Items.IRON_INGOT), 0.7F);
        GameRegistry.addSmelting(RegistryItem.goldPurifiedCrushedOre, new ItemStack(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(RegistryItem.copperPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.copperIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.tinPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.tinIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.silverPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.silverIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.leadPurifiedCrushedOre, new ItemStack(RegistryItem.leadIngot), 0.5F);
        GameRegistry.addSmelting(RegistryItem.leadDust, new ItemStack(RegistryItem.leadIngot), 0.5F);
    }

    public static void initReplaceMaceratorRecipes(){
        macerator.removeRecipe(new RecipeInputOreDict("oreIron"));
        macerator.addRecipe(new RecipeInputOreDict("oreIron"), new ItemStack(RegistryItem.ironCrushedOre,2), 0.7F, "ironOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreGold"));
        macerator.addRecipe(new RecipeInputOreDict("oreGold"), new ItemStack(RegistryItem.goldCrushedOre,2), 1.0F, "goldOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreCopper"));
        macerator.addRecipe(new RecipeInputOreDict("oreCopper"), new ItemStack(RegistryItem.copperCrushedOre,2), 0.3F, "copperOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreTin"));
        macerator.addRecipe(new RecipeInputOreDict("oreTin"), new ItemStack(RegistryItem.tinCrushedOre,2), 0.4F, "tinOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreSilver"));
        macerator.addRecipe(new RecipeInputOreDict("oreSilver"), new ItemStack(RegistryItem.silverCrushedOre,2), 0.8F, "silverOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreLead"));
        macerator.addRecipe(new RecipeInputOreDict("oreLead"), new ItemStack(RegistryItem.leadCrushedOre,2), 0.8F, "leadOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorIron"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorIron", 3), new ItemStack(RegistryItem.ironCrushedOre,2), 0.7F, "ironPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorGold"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorGold", 3), new ItemStack(RegistryItem.goldCrushedOre,2), 1.0F, "goldPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorCopper"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorCopper", 3), new ItemStack(RegistryItem.copperCrushedOre,2), 0.3F, "copperPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorTin"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorTin", 3), new ItemStack(RegistryItem.tinCrushedOre,2), 0.4F, "tinPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorSilver"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorSilver", 3), new ItemStack(RegistryItem.silverCrushedOre,2), 0.8F, "silverPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorLead"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorLead", 3), new ItemStack(RegistryItem.leadCrushedOre,2), 0.8F, "leadPoorOre");
    }

    public static void postInit() {
        int lowHeat = 250;
        int mediumHeat = 300;
        ItemStack stoneDust = new ItemStack(RegistryItem.stoneDust);
        Set<String> crushedBlacklist = new HashSet();
        crushedBlacklist.addAll(Arrays.asList("crushedIron", "crushedGold", "crushedSilver", "crushedLead", "crushedCopper", "crushedTin", "crushedUranium"));
        Set<String> crushedPurifiedBlackList = new HashSet();
        crushedPurifiedBlackList.addAll(Arrays.asList("crushedPurifiedIron", "crushedPurifiedGold", "crushedPurifiedSilver", "crushedPurifiedLead", "crushedPurifiedCopper", "crushedPurifiedTin", "crushedPurifiedUranium"));
        String[] var2 = OreDictionary.getOreNames();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String id = var2[var4];
            String dust;
            String tinyDust;
            String purifiedCrushedOre;
            NonNullList listDust;
            NonNullList listTinyDust;
            NonNullList listPurifiedCrushedOre;
            if (id.startsWith("crushed")) {
                if (!crushedBlacklist.contains(id)) {
                    tinyDust = "dustTiny" + id.substring(7);
                    dust = "dust" + id.substring(7);
                    purifiedCrushedOre = "crushedPurified" + id.substring(7);
                    if (OreDictionary.doesOreNameExist(dust) && OreDictionary.doesOreNameExist(tinyDust) && OreDictionary.doesOreNameExist(purifiedCrushedOre)) {
                        listDust = OreDictionary.getOres(dust,  false);
                        listTinyDust = OreDictionary.getOres(tinyDust, false);
                        listPurifiedCrushedOre = OreDictionary.getOres(purifiedCrushedOre,false);
                        if (!listDust.isEmpty() && !listTinyDust.isEmpty() && !listPurifiedCrushedOre.isEmpty()) {
                            TileEntityThermalCentrifuge.addRecipe(new RecipeInputOreDict(id, 1), mediumHeat, (ItemStack)listDust.get(0), (ItemStack)listTinyDust.get(0), stoneDust);
                            TileEntityOreWashingPlant.addRecipe(new RecipeInputOreDict(id, 1), new MachineOutput(null, Arrays.asList((ItemStack)listPurifiedCrushedOre.get(0), StackUtil.copyWithSize((ItemStack)listTinyDust.get(0), 2), stoneDust)));
                        }
                    }
                }
            }
            if (id.startsWith("crushedPurified")) {
                if (!crushedPurifiedBlackList.contains(id)){
                    dust = "dust" + id.substring(15);
                    tinyDust = "dustTiny" + id.substring(15);
                    if (OreDictionary.doesOreNameExist(dust) && OreDictionary.doesOreNameExist(tinyDust)) {
                        listDust = OreDictionary.getOres(dust, false);
                        listTinyDust = OreDictionary.getOres(tinyDust, false);
                        if (!listDust.isEmpty() && !listTinyDust.isEmpty()) {
                            TileEntityThermalCentrifuge.addRecipe(new RecipeInputOreDict(id, 1), lowHeat, (ItemStack)listDust.get(0), StackUtil.copyWithSize((ItemStack)listTinyDust.get(0), 2));
                        }
                    }
                }

            }
        }

    }

    static final String[] myMaterialNamesBme = {
            materialNamesBme.ADAMANTINE,
            materialNamesBme.ANTIMONY,
            materialNamesBme.BISMUTH,
            materialNamesBme.COLDIRON,
            materialNamesBme.NICKEL,
            materialNamesBme.PLATINUM,
            materialNamesBme.STARSTEEL,
            materialNamesBme.ZINC
    };

    static final String[] myMaterialNamesMme = {
            materialNamesMme.ALUMINUM,
            materialNamesMme.BERYLLIUM,
            materialNamesMme.BORON,
            materialNamesMme.CADMIUM,
            materialNamesMme.CHROMIUM,
            materialNamesMme.IRIDIUM,
            materialNamesMme.MAGNESIUM,
            materialNamesMme.MANGANESE,
            materialNamesMme.OSMIUM,
            materialNamesMme.PLUTONIUM,
            materialNamesMme.RUTILE,
            materialNamesMme.TANTALUM,
            materialNamesMme.THORIUM,
            materialNamesMme.TITANIUM,
            materialNamesMme.TUNGSTEN,
            materialNamesMme.ZIRCONIUM
    };

    static final String[] myMaterialNamesMmeExtra = {
            materialNamesMme.ALUMINUM_BRASS,
            materialNamesMme.GALVANIZED_STEEL,
            materialNamesMme.NICHROME,
            materialNamesMme.STAINLESS_STEEL,
            materialNamesMme.URANIUM
    };

    public static void initMachineRecipes(){
        int lowHeat = 250;
        int mediumHeat = 300;
        ItemStack stoneDust = new ItemStack(RegistryItem.stoneDust);
        //ore washing plant
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedIron", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.ironPurifiedCrushedOre, 1), new ItemStack(RegistryItem.ironTinyDust, 2), stoneDust)));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedGold", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.goldPurifiedCrushedOre, 1), new ItemStack(RegistryItem.goldTinyDust, 2), stoneDust)));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.copperPurifiedCrushedOre, 1), new ItemStack(RegistryItem.copperTinyDust, 2), stoneDust)));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedTin", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.tinPurifiedCrushedOre, 1), new ItemStack(RegistryItem.tinTinyDust, 2, 3), stoneDust)));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.silverPurifiedCrushedOre, 1), new ItemStack(RegistryItem.silverTinyDust, 2), stoneDust)));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedLead", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.leadPurifiedCrushedOre, 1), new ItemStack(RegistryItem.leadTinyDust, 3), stoneDust)));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputItemStack(new ItemStack(Blocks.GRAVEL, 1))), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.stoneDust))));

        //thermal centrifuge recipes
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedIron", 1)), lowHeat, Ic2Items.ironDust, new ItemStack(RegistryItem.goldTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedGold", 1)), lowHeat, Ic2Items.goldDust, new ItemStack(RegistryItem.silverTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedCopper", 1)), lowHeat, Ic2Items.copperDust, new ItemStack(RegistryItem.tinTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedTin", 1)), lowHeat, Ic2Items.tinDust, new ItemStack(RegistryItem.ironTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedSilver", 1)), lowHeat, Ic2Items.silverDust, new ItemStack(RegistryItem.leadTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedLead", 1)), lowHeat, new ItemStack(RegistryItem.leadDust, 1), new ItemStack(RegistryItem.copperTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedIron", 1)), mediumHeat, Ic2Items.ironDust, new ItemStack(RegistryItem.goldTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedGold", 1)), mediumHeat, Ic2Items.goldDust, new ItemStack(RegistryItem.silverTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), mediumHeat, Ic2Items.copperDust, new ItemStack(RegistryItem.tinTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedTin", 1)), mediumHeat, Ic2Items.tinDust, new ItemStack(RegistryItem.ironTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), mediumHeat, Ic2Items.silverDust, stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedLead", 1)), mediumHeat, new ItemStack(RegistryItem.leadDust, 1), stoneDust);

        rolling.addRecipe((new RecipeInputOreDict("ingotCopper", 1)),  new ItemStack(RegistryItem.copperCasing, 2), 0.7f, "copperItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotTin", 1)),  new ItemStack(RegistryItem.tinCasing, 2), 0.7f, "tinItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotSilver", 1)),  new ItemStack(RegistryItem.silverCasing, 2), 0.7f, "silverItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotLead", 1)),  new ItemStack(RegistryItem.leadCasing, 2), 0.7f, "leadItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotIron", 1)),  new ItemStack(RegistryItem.ironCasing, 2), 0.7f, "ironItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotGold", 1)),  new ItemStack(RegistryItem.goldCasing, 2), 0.7f, "goldItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotRefinedIron", 1)),  new ItemStack(RegistryItem.refinedIronCasing, 2), 0.7f, "refinedIronItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotSteel", 1)),  new ItemStack(RegistryItem.steelCasing, 2), 0.7f, "steelItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotBronze", 1)),  new ItemStack(RegistryItem.bronzeCasing, 2), 0.7f, "bronzeItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("casingRefinedIron", 1)),  StackUtil.copyWithSize(Ic2Items.miningPipe, 1), 0.7f, "ironFenceExtruding");

        extruding.addRecipe(new RecipeInputOreDict("ingotCopper", 1),  StackUtil.copyWithSize(Ic2Items.copperCable, 3), 0.7f, "copperCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotTin", 1),  StackUtil.copyWithSize(Ic2Items.tinCable, 4), 0.7f, "tinCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotBronze", 1),  StackUtil.copyWithSize(Ic2Items.bronzeCable, 3), 0.7f, "bronzeCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotRefinedIron", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f, "HVCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotGold", 1),  StackUtil.copyWithSize(Ic2Items.goldCable, 6), 0.7f, "goldCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("casingTin", 1),  StackUtil.copyWithSize(Ic2Items.tinCan, 1), 0.7f, "tinCanExtruding");
        extruding.addRecipe(new RecipeInputOreDict("casingRefinedIron", 2),  StackUtil.copyWithSize(Ic2Items.ironFence, 3), 0.7f, "ironFenceExtruding");

        cutting.addRecipe(new RecipeInputOreDict("ingotRefinedIron", 3), Ic2Items.turbineBlade, "turbineBladeCutting");

        if (Loader.isModLoaded("basemetals")){

            if (Options.isModEnabled("ic2")){
                for (String matName : myMaterialNamesBme) {
                    MMDMaterial mat = Materials.getMaterialByName(matName);
                    String oreName = matName.substring(0, 1).toUpperCase() + matName.substring(1).toLowerCase();
                    String crushedName = "crushed" + oreName;
                    String crushedPurifiedName = "crushedPurified" + oreName;
                    String plateName = "plate" + oreName;
                    ItemStack crushedPurified = new ItemStack(mat.getItem(Names.CRUSHED_PURIFIED));
                    ItemStack powder1 = new ItemStack(mat.getItem(Names.POWDER));
                    ItemStack smallPowder1 = new ItemStack(mat.getItem(Names.SMALLPOWDER), 1);
                    ItemStack smallPowder2 = new ItemStack(mat.getItem(Names.SMALLPOWDER), 2);
                    ItemStack casings = new ItemStack(mat.getItem(Names.CASING), 2);

                    rolling.addRecipe(new RecipeInputOreDict(plateName, 1), casings, plateName + "Rolling");

                    TileEntityOreWashingPlant.addRecipe(new RecipeInputOreDict(crushedName, 1), new MachineOutput(null, Arrays.asList(crushedPurified, smallPowder2, stoneDust)));

                    TileEntityThermalCentrifuge.addRecipe(new RecipeInputOreDict(crushedName, 1), mediumHeat, powder1, smallPowder1, stoneDust);

                    TileEntityThermalCentrifuge.addRecipe(new RecipeInputOreDict(crushedPurifiedName, 1), lowHeat, powder1, smallPowder2);
                }
            }
        }

        if (Loader.isModLoaded("modernmetals")){
            if (Options.isModEnabled("ic2")){
                for (String matName : myMaterialNamesMme) {
                    MMDMaterial mat = Materials.getMaterialByName(matName);
                    String oreName = matName.substring(0, 1).toUpperCase() + matName.substring(1).toLowerCase();
                    String crushedName = "crushed" + oreName;
                    String crushedPurifiedName = "crushedPurified" + oreName;
                    String plateName = "plate" + oreName;
                    ItemStack crushedPurified = new ItemStack(mat.getItem(Names.CRUSHED_PURIFIED));
                    ItemStack powder1 = new ItemStack(mat.getItem(Names.POWDER));
                    ItemStack smallPowder1 = new ItemStack(mat.getItem(Names.SMALLPOWDER), 1);
                    ItemStack smallPowder2 = new ItemStack(mat.getItem(Names.SMALLPOWDER), 2);
                    ItemStack casings = new ItemStack(mat.getItem(Names.CASING), 2);

                    rolling.addRecipe(new RecipeInputOreDict(plateName, 1), casings, plateName + "Rolling");

                    TileEntityOreWashingPlant.addRecipe(new RecipeInputOreDict(crushedName, 1), new MachineOutput(null, Arrays.asList(crushedPurified, smallPowder2, stoneDust)));

                    TileEntityThermalCentrifuge.addRecipe(new RecipeInputOreDict(crushedName, 1), mediumHeat, powder1, smallPowder1, stoneDust);

                    TileEntityThermalCentrifuge.addRecipe(new RecipeInputOreDict(crushedPurifiedName, 1), lowHeat, powder1, smallPowder2);
                }

                for (String matName : myMaterialNamesMmeExtra) {
                    MMDMaterial mat = Materials.getMaterialByName(matName);
                    String oreName = matName.substring(0, 1).toUpperCase() + matName.substring(1).toLowerCase();
                    String plateName = "plate" + oreName;
                    ItemStack casings = new ItemStack(mat.getItem(Names.CASING), 2);

                    rolling.addRecipe(new RecipeInputOreDict(plateName, 1), casings, plateName + "Rolling");
                }
            }

        }
    }

    public static void initRailcraftRecipes(){
        if (Loader.isModLoaded("railcraft")){
            //Crafters.rockCrusher().getRecipes().removeIf(-> true);
        }
    }
    public static void initHarderUraniumProcessing(){
        ItemStack stoneDust = new ItemStack(RegistryItem.stoneDust);
        if (enableHarderUranium){
            macerator.removeRecipe(new RecipeInputOreDict("oreUranium"));
            macerator.addRecipe(new RecipeInputOreDict("oreUranium"), new ItemStack(RegistryItem.uraniumCrushedOre,2), 1.0F, "uraniumOre");
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(Ic2Items.reactorReEnrichedUraniumRod)), 500, new ItemStack(RegistryItem.plutoniumTinyDust, 2), new ItemStack(RegistryItem.uranium238, 4));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedUranium", 1)), 400, new ItemStack(RegistryItem.uranium238, 6), new ItemStack(RegistryItem.uranium235TinyDust, 1));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), 450, new ItemStack(RegistryItem.uranium238, 4), new ItemStack(RegistryItem.uranium235TinyDust, 1), stoneDust);
            TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.uraniumPurifiedCrushedOre, 1), new ItemStack(RegistryItem.uranium235TinyDust, 1), stoneDust)));
            TileEntityCompressor.addRecipe(new ItemStack(RegistryItem.plutoniumEnrichedUranium), new ItemStack(RegistryItem.plutoniumEnrichedUraniumIngot));
            recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.uraniumDrop, 1),
                    "UUU", "TTT", "UUU", 'U', "dustUranium238",'T', "dustTinyUranium235");
            recipes.addShapelessRecipe((new ItemStack(RegistryItem.plutoniumEnrichedUranium, 2)),
                    "dropUranium", RegistryItem.plutonium);
            Ic2cExtrasRecipes.dustUtil("dustUranium235", new ItemStack(RegistryItem.uranium235), "dustTinyUranium235", new ItemStack(RegistryItem.uranium235TinyDust));
            Ic2cExtrasRecipes.dustUtil("dustUranium238", new ItemStack(RegistryItem.uranium238), "dustTinyUranium238", new ItemStack(RegistryItem.uranium238TinyDust));
            Ic2cExtrasRecipes.dustUtil("dustPlutonium", new ItemStack(RegistryItem.plutonium), "dustTinyPlutonium", new ItemStack(RegistryItem.plutoniumTinyDust));
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onHarvestDropsEvent(BlockEvent.HarvestDropsEvent event) {
        IBlockState block = event.getState();
        if (block == Ic2States.uraniumOre){
            if (enableHarderUranium){
                event.getDrops().clear();
                event.getDrops().add(Ic2Items.uraniumOre);
            }
        }
    }

    @SubscribeEvent
    public void onLootTableLoad(LootTableLoadEvent event) {
        LootFunction[] funcs = new LootFunction[] { new SetMetadata(new LootCondition[0], new RandomValueRange(0, 3)) };
        String entryName = "ic2c_extras:iridiumshard";
        Item shard = RegistryItem.iridiumShard;
        if(event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)){
            event.getTable().getPool("main").addEntry(new LootEntryItem(shard, dungeonWeight, itemQuality, funcs, new LootCondition[0], entryName));
        }

        else if(event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE)){
            event.getTable().getPool("main").addEntry(new LootEntryItem(shard, netherFortressWeight, itemQuality, funcs, new LootCondition[0], entryName));
        }

        else if(event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)){
            event.getTable().getPool("main").addEntry(new LootEntryItem(shard, strongholdWeight, itemQuality, funcs, new LootCondition[0], entryName));
        }

        else if(event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CROSSING)){
            event.getTable().getPool("main").addEntry(new LootEntryItem(shard, strongholdWeight, itemQuality, funcs, new LootCondition[0], entryName));
        }

        else if(event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE)){
            event.getTable().getPool("main").addEntry(new LootEntryItem(shard, jungleTempleWeight, itemQuality, funcs, new LootCondition[0], entryName));
        }

        else if(event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID)){
            event.getTable().getPool("main").addEntry(new LootEntryItem(shard, desertTempleWeight, itemQuality, funcs, new LootCondition[0], entryName));
        }

    }

    public static void setConfig(boolean uranium, boolean casings, boolean wires, boolean hvCable){
        enableHarderUranium = uranium;
        enableCasingsRequirePlates = casings;
        enableCuttingToolWires = wires;
        enableHVCablesRequireSteel = hvCable;
    }
}
