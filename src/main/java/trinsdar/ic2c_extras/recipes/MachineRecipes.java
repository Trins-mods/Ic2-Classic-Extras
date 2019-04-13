package trinsdar.ic2c_extras.recipes;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.IC2;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.blocks.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.Registry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ic2.api.classic.recipe.ClassicRecipes.macerator;
import static trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes.extruding;
import static trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes.rolling;

public class MachineRecipes {

    public static void init(){
        initMachineRecipes();
        initFurnaceRecipes();
        initReplaceMaceratorRecipes();
        postInit();
    }

    static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
    public static void postInit() {
        Set<String> crushedBlacklist = new HashSet();
        Set<String> crushedPurifiedBlackList = new HashSet();
        Set<String> plateBlacklist = new HashSet();
        Set<String> ingotWhitelist = new HashSet();
        Set<String> ingotGTBlacklist = new HashSet();
        Set<String> gemBlacklist = new HashSet();
        Set<String> ingotBmeMmeBlacklist = new HashSet();
        crushedBlacklist.addAll(Arrays.asList("crushedIron", "crushedGold", "crushedSilver", "crushedLead", "crushedCopper", "crushedTin", "crushedUranium"));
        crushedPurifiedBlackList.addAll(Arrays.asList("crushedPurifiedIron", "crushedPurifiedGold", "crushedPurifiedSilver", "crushedPurifiedLead", "crushedPurifiedCopper", "crushedPurifiedTin", "crushedPurifiedUranium"));
        plateBlacklist.addAll(Arrays.asList("plateIron", "plateGold", "plateSilver", "plateLead", "plateCopper", "plateTin", "plateRefinedIron", "plateSteel", "plateBronze"));
        ingotWhitelist.addAll(Arrays.asList("ingotIron", "ingotGold", "ingotSilver", "ingotLead", "ingotCopper", "ingotTin", "ingotRefinedIron", "ingotSteel", "ingotBronze"));
        gemBlacklist.addAll(Arrays.asList("ingotDiamond", "ingotEmerald", "ingotQuartz", "ingotIridium", "ingotCoal"));
        if (Loader.isModLoaded("gtclassic")){
            ingotGTBlacklist.addAll(Arrays.asList("ingotGermanium", "ingotTungsten", "ingotInvar", "ingotZinc", "ingotManganese", "ingotMagnalium", "ingotBismuthBronze", "ingotElectrum", "ingotConstantan", "ingotAluminum", "ingotAluminium", "ingotGraphite", "ingotPlatinum", "ingotChrome", "ingotBrass", "ingotPlutonium", "ingotStainlessSteel", "ingotTungstensteel", "ingotTitanium", "ingotNickel", "ingotOsmium", "ingotNichrome", "ingotTantalum", "ingotBismuthr"));
        }
        if (Loader.isModLoaded("basemetals")){
            plateBlacklist.addAll(Arrays.asList("plateAdamantine", "plateAntimony", "plateBismuth", "plateColdiron", "plateNickel", "platePlatinum", "plateStarsteel", "plateZinc"));
            ingotBmeMmeBlacklist.addAll(Arrays.asList("ingotAdamantine", "ingotAntimony", "ingotBismuth", "ingotColdiron", "ingotNickel", "ingotPlatinum", "ingotStarsteel", "ingotZinc"));
        }
        if (Loader.isModLoaded("modernmetals")){
            plateBlacklist.addAll(Arrays.asList("plateAluminum", "plateAluminium", "plateAluminumbrass", "plateAluminiumbrass", "plateBeryllium", "plateBoron", "plateCadmium", "plateChrome", "plateChromium", "plateGalvanizedsteel", "plateIridium", "plateMagnesium", "plateManganese", "plateNichrome", "plateOsmium", "platePlutonium", "plateRutile", "plateStainlesssteel", "plateTantalum", "plateTitanium", "plateThorium", "plateTungsten", "plateUranium", "plateZirconium"));
            ingotBmeMmeBlacklist.addAll(Arrays.asList("ingotAluminum", "ingotAluminium", "ingotAluminumbrass", "ingotAluminiumbrass", "ingotBeryllium", "ingotBoron", "ingotCadmium", "ingotChrome", "ingotChromium", "ingotGalvanizedsteel", "ingotIridium", "ingotMagnesium", "ingotManganese", "ingotNichrome", "ingotOsmium", "ingotPlutonium", "ingotRutile", "ingotStainlesssteel", "ingotTantalum", "ingotTitanium", "ingotThorium", "ingotTungsten", "ingotUranium", "ingotZirconium"));
        }
        String[] var2 = OreDictionary.getOreNames();
        int var3 = var2.length;

        if (Ic2cExtrasRecipes.enableAutoOredictRecipes){
            for(int var4 = 0; var4 < var3; ++var4) {
                String id = var2[var4];
                String plate;
                NonNullList listPlates;
                if (id.startsWith("ingot")){
                    if (ingotWhitelist.contains(id) && !gemBlacklist.contains(id)){
                        plate = "plate" + id.substring(5);
                        if (Ic2cExtrasRecipes.enableCasingsRequirePlates){
                            if (OreDictionary.doesOreNameExist(plate)) {
                                listPlates = OreDictionary.getOres(plate, false);
                                if (!listPlates.isEmpty()) {
                                    rolling.addRecipe(new RecipeInputOreDict(id, 1), (ItemStack)listPlates.get(0), plate + "Rolling");
                                    if (Ic2cExtrasRecipes.enableHammerRecipes && !Loader.isModLoaded("gtclassic")){
                                        if (Ic2cExtrasRecipes.enableTwoPlatesPerIngot){
                                            recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", "I", 'H', "craftingToolForgeHammer", 'I', id  );
                                        }else {
                                            recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", 'H', "craftingToolForgeHammer", 'I', id );
                                        }
                                    }
                                }
                            }
                        }
                    } else if (!ingotWhitelist.contains(id) && !gemBlacklist.contains(id) && !ingotBmeMmeBlacklist.contains(id)){
                        plate = "plate" + id.substring(5);
                        if (OreDictionary.doesOreNameExist(plate)) {
                            listPlates = OreDictionary.getOres(plate, false);
                            if (!listPlates.isEmpty()) {
                                rolling.addRecipe(new RecipeInputOreDict(id, 1), (ItemStack)listPlates.get(0), plate + "Rolling");
                                if (Ic2cExtrasRecipes.enableHammerRecipes && !ingotGTBlacklist.contains(id)){
                                    if (Ic2cExtrasRecipes.enableTwoPlatesPerIngot){
                                        recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", "I", 'H', "craftingToolForgeHammer", 'I', id );
                                    }else {
                                        recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", 'H', "craftingToolForgeHammer", 'I', id );
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void initMachineRecipes(){
        int lowHeat = 250;
        int mediumHeat = 300;
        ItemStack stoneDust = new ItemStack(Registry.stoneDust);
        //ore washing plant
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedIron", 1)), 1000, new ItemStack(Registry.ironPurifiedCrushedOre, 1), new ItemStack(Registry.ironTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedGold", 1)), 1000, new ItemStack(Registry.goldPurifiedCrushedOre, 1), new ItemStack(Registry.goldTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), 1000, new ItemStack(Registry.copperPurifiedCrushedOre, 1), new ItemStack(Registry.copperTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedTin", 1)), 1000, new ItemStack(Registry.tinPurifiedCrushedOre, 1), new ItemStack(Registry.tinTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), 1000, new ItemStack(Registry.silverPurifiedCrushedOre, 1), new ItemStack(Registry.silverTinyDust, 2), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedLead", 1)), 1000, new ItemStack(Registry.leadPurifiedCrushedOre, 1), new ItemStack(Registry.leadTinyDust, 3), stoneDust);
        TileEntityOreWashingPlant.addRecipe((new RecipeInputItemStack(new ItemStack(Blocks.GRAVEL, 1))), 100, stoneDust);

        //thermal centrifuge recipes
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedIron", 1)), lowHeat, Ic2Items.ironDust, new ItemStack(Registry.goldTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedGold", 1)), lowHeat, Ic2Items.goldDust, new ItemStack(Registry.silverTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedCopper", 1)), lowHeat, Ic2Items.copperDust, new ItemStack(Registry.tinTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedTin", 1)), lowHeat, Ic2Items.tinDust, new ItemStack(Registry.ironTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedSilver", 1)), lowHeat, Ic2Items.silverDust, new ItemStack(Registry.leadTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedLead", 1)), lowHeat, new ItemStack(Registry.leadDust, 1), new ItemStack(Registry.copperTinyDust, 1));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedIron", 1)), mediumHeat, Ic2Items.ironDust, new ItemStack(Registry.goldTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedGold", 1)), mediumHeat, Ic2Items.goldDust, new ItemStack(Registry.silverTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), mediumHeat, Ic2Items.copperDust, new ItemStack(Registry.tinTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedTin", 1)), mediumHeat, Ic2Items.tinDust, new ItemStack(Registry.ironTinyDust, 1), stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), mediumHeat, Ic2Items.silverDust, stoneDust);
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedLead", 1)), mediumHeat, new ItemStack(Registry.leadDust, 1), stoneDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedIron", 1)), Ic2Items.ironDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedGold", 1)), Ic2Items.goldDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedCopper", 1)), Ic2Items.copperDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedTin", 1)),  Ic2Items.tinDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedSilver", 1)), Ic2Items.silverDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedLead", 1)), new ItemStack(Registry.leadDust, 1));
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedIron", 1)), Ic2Items.ironDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedGold", 1)), Ic2Items.goldDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), Ic2Items.copperDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedTin", 1)),  Ic2Items.tinDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), Ic2Items.silverDust);
        TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedLead", 1)), new ItemStack(Registry.leadDust, 1));

        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyIron", 9), Ic2Items.ironDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyGold", 9), Ic2Items.goldDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyCopper", 9), Ic2Items.copperDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyTin", 9), Ic2Items.tinDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinySilver", 9), Ic2Items.silverDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyLead", 9), new ItemStack(Registry.leadDust));
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyObsidian", 9), Ic2Items.obsidianDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyBronze", 9), Ic2Items.bronzeDust);
        if (Ic2cExtrasRecipes.enableHarderUranium){
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyUranium235", 9), new ItemStack(Registry.uranium235));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyUranium238", 9), new ItemStack(Registry.uranium238));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyPlutonium", 9), new ItemStack(Registry.plutonium));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustSmallUranium235", 4), new ItemStack(Registry.uranium235));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustSmallUranium238", 4), new ItemStack(Registry.uranium238));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustSmallPlutonium", 4), new ItemStack(Registry.plutonium));
        }

        if (Ic2cExtrasRecipes.enableCasingsRequirePlates){
            rolling.addRecipe((new RecipeInputOreDict("plateCopper", 1)),  new ItemStack(Registry.copperCasing, 2), 0.7f, "copperPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateTin", 1)),  new ItemStack(Registry.tinCasing, 2), 0.7f, "tinPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateSilver", 1)),  new ItemStack(Registry.silverCasing, 2), 0.7f, "silverPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateLead", 1)),  new ItemStack(Registry.leadCasing, 2), 0.7f, "leadPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateIron", 1)),  new ItemStack(Registry.ironCasing, 2), 0.7f, "ironPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateGold", 1)),  new ItemStack(Registry.goldCasing, 2), 0.7f, "goldPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateRefinedIron", 1)),  new ItemStack(Registry.refinedIronCasing, 2), 0.7f, "refinedIronPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateSteel", 1)),  new ItemStack(Registry.steelCasing, 2), 0.7f, "steelPlateItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("plateBronze", 1)),  new ItemStack(Registry.bronzeCasing, 2), 0.7f, "bronzePlateItemCasingRolling");
        }else {
            rolling.addRecipe((new RecipeInputOreDict("ingotCopper", 1)),  new ItemStack(Registry.copperCasing, 2), 0.7f, "copperItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotTin", 1)),  new ItemStack(Registry.tinCasing, 2), 0.7f, "tinItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotSilver", 1)),  new ItemStack(Registry.silverCasing, 2), 0.7f, "silverItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotLead", 1)),  new ItemStack(Registry.leadCasing, 2), 0.7f, "leadItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotIron", 1)),  new ItemStack(Registry.ironCasing, 2), 0.7f, "ironItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotGold", 1)),  new ItemStack(Registry.goldCasing, 2), 0.7f, "goldItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotRefinedIron", 1)),  new ItemStack(Registry.refinedIronCasing, 2), 0.7f, "refinedIronItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotSteel", 1)),  new ItemStack(Registry.steelCasing, 2), 0.7f, "steelItemCasingRolling");
            rolling.addRecipe((new RecipeInputOreDict("ingotBronze", 1)),  new ItemStack(Registry.bronzeCasing, 2), 0.7f, "bronzeItemCasingRolling");
        }

        rolling.addRecipe((new RecipeInputOreDict(Ic2cExtrasRecipes.getRefinedIronCasing(), 1)),  StackUtil.copyWithSize(Ic2Items.miningPipe, 1), 0.7f, "miningPipeRolling");

        extruding.addRecipe(new RecipeInputOreDict("ingotCopper", 1),  StackUtil.copyWithSize(Ic2Items.copperCable, 3), 0.7f, "copperCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotTin", 1),  StackUtil.copyWithSize(Ic2Items.tinCable, 4), 0.7f, "tinCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotBronze", 1),  StackUtil.copyWithSize(Ic2Items.bronzeCable, 3), 0.7f, "bronzeCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("ingotGold", 1),  StackUtil.copyWithSize(Ic2Items.goldCable, 6), 0.7f, "goldCableExtruding");
        extruding.addRecipe(new RecipeInputOreDict("casingTin", 1),  StackUtil.copyWithSize(Ic2Items.tinCan, 1), 0.7f, "tinCanExtruding");
        extruding.addRecipe(new RecipeInputOreDict(Ic2cExtrasRecipes.getRefinedIronCasing(), 2),  StackUtil.copyWithSize(Ic2Items.ironFence, 3), 0.7f, "ironFenceExtruding");

        macerator.addRecipe(new RecipeInputOreDict("gemDiamond"), new ItemStack(Registry.diamondDust), 0.5F, "Diamond Dust");
        macerator.addRecipe(new RecipeInputItemStack(Ic2Items.energyCrystal), new ItemStack(Registry.energiumDust, 6), "Energium Dust");

        TileEntityCompressor.addRecipe(new ItemStack(Registry.energiumDust), 6, Ic2Items.energyCrystal);

        if (!IC2.config.getFlag("SteelRecipes")){
            if(Ic2cExtrasRecipes.enableCertainRecipesRequireSteel){
                extruding.addRecipe(new RecipeInputOreDict("ingotSteel", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f, "HVCableExtruding");
            }else {
                extruding.addRecipe(new RecipeInputOreDict("ingotRefinedIron", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f, "HVCableExtruding");
            }
        }else {
            extruding.addRecipe(new RecipeInputOreDict("ingotSteel", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f, "HVCableExtruding");
        }
    }

    public static void initFurnaceRecipes(){
        GameRegistry.addSmelting(Registry.ironCrushedOre, new ItemStack(Items.IRON_INGOT), 0.7F);
        GameRegistry.addSmelting(Registry.goldCrushedOre, new ItemStack(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(Registry.copperCrushedOre, StackUtil.copyWithSize(Ic2Items.copperIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.tinCrushedOre, StackUtil.copyWithSize(Ic2Items.tinIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.silverCrushedOre, StackUtil.copyWithSize(Ic2Items.silverIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.leadCrushedOre, new ItemStack(Registry.leadIngot), 0.5F);
        GameRegistry.addSmelting(Registry.ironPurifiedCrushedOre, new ItemStack(Items.IRON_INGOT), 0.7F);
        GameRegistry.addSmelting(Registry.goldPurifiedCrushedOre, new ItemStack(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(Registry.copperPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.copperIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.tinPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.tinIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.silverPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.silverIngot, 1), 0.5F);
        GameRegistry.addSmelting(Registry.leadPurifiedCrushedOre, new ItemStack(Registry.leadIngot), 0.5F);
        GameRegistry.addSmelting(Registry.leadDust, new ItemStack(Registry.leadIngot), 0.5F);
    }

    public static void initReplaceMaceratorRecipes(){
        macerator.removeRecipe(new RecipeInputOreDict("oreIron"));
        macerator.addRecipe(new RecipeInputOreDict("oreIron"), new ItemStack(Registry.ironCrushedOre,2), 0.7F, "ironOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreGold"));
        macerator.addRecipe(new RecipeInputOreDict("oreGold"), new ItemStack(Registry.goldCrushedOre,2), 1.0F, "goldOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreCopper"));
        macerator.addRecipe(new RecipeInputOreDict("oreCopper"), new ItemStack(Registry.copperCrushedOre,2), 0.3F, "copperOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreTin"));
        macerator.addRecipe(new RecipeInputOreDict("oreTin"), new ItemStack(Registry.tinCrushedOre,2), 0.4F, "tinOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreSilver"));
        macerator.addRecipe(new RecipeInputOreDict("oreSilver"), new ItemStack(Registry.silverCrushedOre,2), 0.8F, "silverOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreLead"));
        macerator.addRecipe(new RecipeInputOreDict("oreLead"), new ItemStack(Registry.leadCrushedOre,2), 0.8F, "leadOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorIron"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorIron", 3), new ItemStack(Registry.ironCrushedOre,2), 0.7F, "ironPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorGold"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorGold", 3), new ItemStack(Registry.goldCrushedOre,2), 1.0F, "goldPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorCopper"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorCopper", 3), new ItemStack(Registry.copperCrushedOre,2), 0.3F, "copperPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorTin"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorTin", 3), new ItemStack(Registry.tinCrushedOre,2), 0.4F, "tinPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorSilver"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorSilver", 3), new ItemStack(Registry.silverCrushedOre,2), 0.8F, "silverPoorOre");
        macerator.removeRecipe(new RecipeInputOreDict("orePoorLead"));
        macerator.addRecipe(new RecipeInputOreDict("orePoorLead", 3), new ItemStack(Registry.leadCrushedOre,2), 0.8F, "leadPoorOre");
    }
}
