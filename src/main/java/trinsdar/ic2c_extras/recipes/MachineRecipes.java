package trinsdar.ic2c_extras.recipes;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.core.IC2;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import trinsdar.ic2c_extras.tileentity.TileEntityExtruder;
import trinsdar.ic2c_extras.tileentity.TileEntityFluidCanningMachine;
import trinsdar.ic2c_extras.tileentity.TileEntityMetalBender;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityRoller;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.util.Registry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ic2.api.classic.recipe.ClassicRecipes.macerator;
import static trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes.cutting;
import static trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes.extruding;
import static trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes.rolling;

public class MachineRecipes {

    public static void init(){
        initMachineRecipes();
        initFurnaceRecipes();
        initReplaceMaceratorRecipes();
    }

    static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
    public static Set<String> ingotBlacklist = new HashSet<>();
    public static void postInit() {
        Set<String> gemBlacklist = new HashSet();
        Set<String> ingotBmeMmeBlacklist = new HashSet();
        ingotBlacklist.addAll(Arrays.asList("ingotIron", "ingotGold", "ingotSilver", "ingotLead", "ingotCopper", "ingotTin", "ingotRefinedIron", "ingotSteel", "ingotBronze"));
        gemBlacklist.addAll(Arrays.asList("ingotDiamond", "ingotEmerald", "ingotQuartz", "ingotIridium", "ingotCoal", "ingotRedstone"));
        if (Loader.isModLoaded("gtc_expansion") || Loader.isModLoaded("techreborn")){
            ingotBlacklist.add("ingotIridiumAlloy");
        }
        if (Loader.isModLoaded("basemetals")){
            ingotBmeMmeBlacklist.addAll(Arrays.asList("ingotAdamantine", "ingotAntimony", "ingotBismuth", "ingotColdiron", "ingotNickel", "ingotPlatinum", "ingotStarsteel", "ingotZinc"));
        }
        if (Loader.isModLoaded("modernmetals")){
            ingotBmeMmeBlacklist.addAll(Arrays.asList("ingotAluminum", "ingotAluminium", "ingotAluminumbrass", "ingotAluminiumbrass", "ingotBeryllium", "ingotBoron", "ingotCadmium", "ingotChrome", "ingotChromium", "ingotGalvanizedsteel", "ingotIridium", "ingotMagnesium", "ingotManganese", "ingotNichrome", "ingotOsmium", "ingotPlutonium", "ingotRutile", "ingotStainlesssteel", "ingotTantalum", "ingotTitanium", "ingotThorium", "ingotTungsten", "ingotUranium", "ingotZirconium"));
        }
        String[] var2 = OreDictionary.getOreNames();
        int var3 = var2.length;

        if (Ic2cExtrasRecipes.enableAutoOredictRecipes){
            for(int var4 = 0; var4 < var3; ++var4) {
                String id = var2[var4];
                String plate;
                String gear;
                String rod;
                NonNullList listPlates;
                NonNullList listGears;
                NonNullList listRods;
                if (id.startsWith("ingot")){
                    if (!ingotBlacklist.contains(id) && !gemBlacklist.contains(id) && !ingotBmeMmeBlacklist.contains(id)){
                        plate = "plate" + id.substring(5);
                        if (OreDictionary.doesOreNameExist(plate)) {
                            listPlates = OreDictionary.getOres(plate, false);
                            if (!listPlates.isEmpty()) {
                                TileEntityRoller.addRecipe(new RecipeInputOreDict(id, 1), (ItemStack)listPlates.get(0));
                                if (Ic2cExtrasRecipes.enableHammerRecipes){
                                    if (Ic2cExtrasRecipes.enableTwoPlatesPerIngot){
                                        recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", "I", 'H', "craftingToolForgeHammer", 'I', id );
                                    }else {
                                        recipes.addRecipe((ItemStack)listPlates.get(0), "H", "I", 'H', "craftingToolForgeHammer", 'I', id );
                                    }
                                }
                            }
                        }
                    }
                    if (!gemBlacklist.contains(id)){
                        gear = "gear" + id.substring(5);
                        rod = "rod" + id.substring(5);
                        if (OreDictionary.doesOreNameExist(gear)) {
                            listGears = OreDictionary.getOres(gear, false);
                            if (!listGears.isEmpty()) {
                                TileEntityMetalBender.addRecipe(new RecipeInputOreDict(id, 4), new ItemStack(Registry.gearingPress), (ItemStack)listGears.get(0));
                            }
                        }
                        if (OreDictionary.doesOreNameExist(rod)) {
                            listRods = OreDictionary.getOres(rod, false);
                            if (!listRods.isEmpty()) {
                                TileEntityMetalBender.addRecipe(new RecipeInputOreDict(id, 1), new ItemStack(Registry.lathingPress), StackUtil.copyWithSize((ItemStack)listRods.get(0), 2));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void initFluidFillingndEmptyingRecipes(){
        for(Item item : Item.REGISTRY)
        {
            NonNullList<ItemStack> items = NonNullList.create();
            item.getSubItems(CreativeTabs.SEARCH, items);
            for(ItemStack stack : items)
            {

                IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack.copy());
                if(handler != null)
                {
                    if (hasDrainProperty(handler)){
                        FluidStack fluid = handler.drain(Integer.MAX_VALUE, true);
                        if (fluid != null){
                            ItemStack empty = handler.getContainer();
                            if (!empty.isEmpty()){
                                TileEntityFluidCanningMachine.addEmptyingRecipe(new RecipeInputItemStack(stack), empty, fluid);
                            }
                        }
                    }
                    if (hasFillProperty(handler)) {
                        IFluidHandlerItem fillingCapability = stack.copy().getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
                        if (fillingCapability != null) {
                            for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
                                int testFill = fillingCapability.fill(new FluidStack(fluid, Integer.MAX_VALUE), false);    //try to reduce itemstack copies
                                if (testFill > 0) {
                                    IFluidHandlerItem copiedCap = stack.copy().getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
                                    int fill = copiedCap.fill(new FluidStack(fluid, Integer.MAX_VALUE), true);
                                    FluidStack filledFluid = new FluidStack(fluid, fill);
                                    ItemStack filled = copiedCap.getContainer();
                                    TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(stack), filledFluid, filled);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean hasDrainProperty(IFluidHandler fluidHandler) {
        for (IFluidTankProperties properties : fluidHandler.getTankProperties()) {
            if (properties.canDrain()) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasFillProperty(IFluidHandler fluidHandler) {
        for (IFluidTankProperties properties : fluidHandler.getTankProperties()) {
            if (properties.canFill()) {
                return true;
            }
        }
        return false;
    }

    public static void initMachineRecipes(){
        int lowHeat = 400;
        int mediumHeat = 600;
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
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyPlutonium", 9), new ItemStack(Registry.plutoniumDust));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustSmallUranium235", 4), new ItemStack(Registry.uranium235));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustSmallUranium238", 4), new ItemStack(Registry.uranium238));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustSmallPlutonium", 4), new ItemStack(Registry.plutoniumDust));
        }

        if (Ic2cExtrasRecipes.enableEmptyRods){
            TileEntityExtruder.addRecipe(new RecipeInputOreDict("plateIron", 1), new ItemStack(Registry.emptyFuelRod));
        }

        if (Ic2cExtrasRecipes.enableCasingsRequirePlates){
            if (!Loader.isModLoaded("gtc_expansion")){
                TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotCopper", 1)),  new ItemStack(Registry.copperPlate, 1), 0.7f );
                TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotTin", 1)),  new ItemStack(Registry.tinPlate, 1), 0.7f);
                TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotSilver", 1)),  new ItemStack(Registry.silverPlate, 1), 0.7f);
                TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotLead", 1)),  new ItemStack(Registry.leadPlate, 1), 0.7f);
                TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotIron", 1)),  new ItemStack(Registry.ironPlate, 1), 0.7f);
                TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotGold", 1)),  new ItemStack(Registry.goldPlate, 1), 0.7f);
                TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotRefinedIron", 1)),  new ItemStack(Registry.refinedIronPlate, 1), 0.7f);
                TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotSteel", 1)),  new ItemStack(Registry.steelPlate, 1), 0.7f);
                TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotBronze", 1)),  new ItemStack(Registry.bronzePlate, 1), 0.7f);
            }
            TileEntityRoller.addRecipe((new RecipeInputOreDict("plateCopper", 1)),  new ItemStack(Registry.copperCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("plateTin", 1)),  new ItemStack(Registry.tinCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("plateSilver", 1)),  new ItemStack(Registry.silverCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("plateLead", 1)),  new ItemStack(Registry.leadCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("plateIron", 1)),  new ItemStack(Registry.ironCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("plateGold", 1)),  new ItemStack(Registry.goldCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("plateRefinedIron", 1)),  new ItemStack(Registry.refinedIronCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("plateSteel", 1)),  new ItemStack(Registry.steelCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("plateBronze", 1)),  new ItemStack(Registry.bronzeCasing, 2), 0.7f);
        }else {
            TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotCopper", 1)),  new ItemStack(Registry.copperCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotTin", 1)),  new ItemStack(Registry.tinCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotSilver", 1)),  new ItemStack(Registry.silverCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotLead", 1)),  new ItemStack(Registry.leadCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotIron", 1)),  new ItemStack(Registry.ironCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotGold", 1)),  new ItemStack(Registry.goldCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotRefinedIron", 1)),  new ItemStack(Registry.refinedIronCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotSteel", 1)),  new ItemStack(Registry.steelCasing, 2), 0.7f);
            TileEntityRoller.addRecipe((new RecipeInputOreDict("ingotBronze", 1)),  new ItemStack(Registry.bronzeCasing, 2), 0.7f);
        }

        TileEntityRoller.addRecipe((new RecipeInputOreDict(Ic2cExtrasRecipes.getRefinedIronCasing(), 1)),  StackUtil.copyWithSize(Ic2Items.miningPipe, 1), 0.7f);

        TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotCopper", 1),  StackUtil.copyWithSize(Ic2Items.copperCable, 3), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotTin", 1),  StackUtil.copyWithSize(Ic2Items.tinCable, 4), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotBronze", 1),  StackUtil.copyWithSize(Ic2Items.bronzeCable, 3), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotGold", 1),  StackUtil.copyWithSize(Ic2Items.goldCable, 6), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict("casingTin", 1),  StackUtil.copyWithSize(Ic2Items.tinCan, 1), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict(Ic2cExtrasRecipes.getRefinedIronCasing(), 2),  StackUtil.copyWithSize(Ic2Items.ironFence, 3), 0.7f);

        macerator.addRecipe(new RecipeInputOreDict("gemDiamond"), new ItemStack(Registry.diamondDust), 0.5F, "Diamond Dust");
        macerator.addRecipe(new RecipeInputItemStack(Ic2Items.energyCrystal), new ItemStack(Registry.energiumDust, 6), "Energium Dust");

        TileEntityCompressor.addRecipe(new ItemStack(Registry.energiumDust), 6, Ic2Items.energyCrystal);
        ClassicRecipes.earthExtractor.registerValue(5.85f, stoneDust);

        if (!IC2.config.getFlag("SteelRecipes")){
            if(Ic2cExtrasRecipes.enableCertainRecipesRequireSteel){
                TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotSteel", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f);
            }else {
                TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotRefinedIron", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f);
            }
        }else {
            TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotSteel", 1),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f);
        }

        if (Ic2cExtrasRecipes.enableDensePlatesTakePlates){
            ClassicRecipes.compressor.removeRecipe(new RecipeInputOreDict("ingotCopper", 8));
            TileEntityCompressor.addRecipe("plateCopper", 9, Ic2Items.denseCopperPlate);
            TileEntityCompressor.addRecipe("plateIron", 9, new ItemStack(Registry.denseIronPlate));
        }else {
            TileEntityCompressor.addRecipe("ingotIron", 8, new ItemStack(Registry.denseIronPlate));
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
        GameRegistry.addSmelting(Registry.ironTinyDust, new ItemStack(Items.IRON_NUGGET), 0.5f);
        GameRegistry.addSmelting(Registry.goldTinyDust, new ItemStack(Items.GOLD_NUGGET), 0.5f);
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

    public static void initMetalBenderRecipes(){
        for (IMachineRecipeList.RecipeEntry recipe : rolling.getRecipeMap()){
            TileEntityMetalBender.addRecipe(recipe.getInput(), new ItemStack(Registry.rollingPress), recipe.getOutput());
        }

        for (IMachineRecipeList.RecipeEntry recipe : extruding.getRecipeMap()){
            TileEntityMetalBender.addRecipe(recipe.getInput(), new ItemStack(Registry.extrudingPress), recipe.getOutput());
        }

        for (IMachineRecipeList.RecipeEntry recipe : cutting.getRecipeMap()){
            TileEntityMetalBender.addRecipe(recipe.getInput(), new ItemStack(Registry.cuttingPress), recipe.getOutput());
        }
    }
}
