package trinsdar.ic2c_extras.recipes;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
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
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.tileentity.TileEntityCutter;
import trinsdar.ic2c_extras.tileentity.TileEntityExtruder;
import trinsdar.ic2c_extras.tileentity.TileEntityFermenter;
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

    public static void init() {
        initMachineRecipes();
        initFurnaceRecipes();
        initReplaceMaceratorRecipes();
        TileEntityFermenter.init();
    }

    public static void postInit() {

    }

    public static void initFluidFillingndEmptyingRecipes() {
    }

    public static void initMachineRecipes() {
        /*int lowHeat = 400;
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
        if (!Loader.isModLoaded("gtc_expansion")){
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedIron", 1)), Ic2Items.ironDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedGold", 1)), Ic2Items.goldDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedCopper", 1)), Ic2Items.copperDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedTin", 1)), Ic2Items.tinDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedSilver", 1)), Ic2Items.silverDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedPurifiedLead", 1)), new ItemStack(Registry.leadDust, 1));
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedIron", 1)), Ic2Items.ironDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedGold", 1)), Ic2Items.goldDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), Ic2Items.copperDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedTin", 1)), Ic2Items.tinDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), Ic2Items.silverDust);
            TileEntityMacerator.addRecipe((new RecipeInputOreDict("crushedLead", 1)), new ItemStack(Registry.leadDust, 1));
        }

        macerator.removeRecipe(Ic2Items.plantBall.copy());
        TileEntityMacerator.addRecipe(Ic2Items.plantBall.copy(), new ItemStack(Registry.bioChaff));
        TileEntityMacerator.addRecipe(new ItemStack(Registry.bioChaff), new ItemStack(Blocks.DIRT, 8));


        TileEntityFluidCanningMachine.addEnrichingRecipe(new RecipeInputItemStack(new ItemStack(Registry.bioChaff)), FluidRegistry.getFluidStack("water", 1000), FluidRegistry.getFluidStack("biomass", 1000), TileEntityFluidCanningMachine.totalEu(400));

        if (Loader.isModLoaded("forestry")) {
        	TileEntityFluidCanningMachine.addEnrichingRecipe(new RecipeInputItemStack(new ItemStack(Registry.bioChaff)), FluidRegistry.getFluidStack("for.honey", 1000), FluidRegistry.getFluidStack("biomass", 1500), TileEntityFluidCanningMachine.totalEu(400));
        	TileEntityFluidCanningMachine.addEnrichingRecipe(new RecipeInputItemStack(new ItemStack(Registry.bioChaff)), FluidRegistry.getFluidStack("juice", 1000), FluidRegistry.getFluidStack("biomass", 1500), TileEntityFluidCanningMachine.totalEu(400));
        }

        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyIron", 9), Ic2Items.ironDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyGold", 9), Ic2Items.goldDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyCopper", 9), Ic2Items.copperDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyTin", 9), Ic2Items.tinDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinySilver", 9), Ic2Items.silverDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyLead", 9), new ItemStack(Registry.leadDust));
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyObsidian", 9), Ic2Items.obsidianDust);
        TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyBronze", 9), Ic2Items.bronzeDust);
        if (Ic2cExtrasConfig.harderUranium) {
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyUranium235", 9), new ItemStack(Registry.uranium235));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyUranium238", 9), new ItemStack(Registry.uranium238));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustTinyPlutonium", 9), new ItemStack(Registry.plutoniumDust));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustSmallUranium235", 4), new ItemStack(Registry.uranium235));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustSmallUranium238", 4), new ItemStack(Registry.uranium238));
            TileEntityCompressor.addRecipe(new RecipeInputOreDict("dustSmallPlutonium", 4), new ItemStack(Registry.plutoniumDust));
            TileEntityMacerator.addRecipe("crushedUranium", 1, new ItemStack(Registry.uraniumDust));
            TileEntityMacerator.addRecipe("crushedPurifiedUranium", 1, new ItemStack(Registry.uraniumDust));
            TileEntityMacerator.addRecipe("crushedCentrifugedUranium", 1, new ItemStack(Registry.uraniumDust));
            TileEntityCompressor.addRecipe("dustUranium", 1, Ic2Items.uraniumIngot.copy());
        }

        if (Ic2cExtrasConfig.emptyNuclearRod) {
            TileEntityExtruder.addRecipe(new RecipeInputOreDict("plateIron", 1), new ItemStack(Registry.emptyFuelRod));
        }*/

        /*TileEntityRoller.addRecipe((new RecipeInputOreDict(Ic2cExtrasRecipes.getRefinedIronCasing(), 1)), StackUtil.copyWithSize(Ic2Items.miningPipe, 1), 0.7f);

        TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotCopper", 1), StackUtil.copyWithSize(Ic2Items.copperCable, 3), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotTin", 1), StackUtil.copyWithSize(Ic2Items.tinCable, 4), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotBronze", 1), StackUtil.copyWithSize(Ic2Items.bronzeCable, 3), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict("ingotGold", 1), StackUtil.copyWithSize(Ic2Items.goldCable, 6), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict("casingTin", 1), StackUtil.copyWithSize(Ic2Items.tinCan, 1), 0.7f);
        TileEntityExtruder.addRecipe(new RecipeInputOreDict(Ic2cExtrasRecipes.getRefinedIronCasing(), 2), StackUtil.copyWithSize(Ic2Items.ironFence, 3), 0.7f);*/

        ClassicRecipes.earthExtractor.registerValue(5.85f, stoneDust);

        /*if (!Loader.isModLoaded("gtc_expansion")) {
            ClassicRecipes.fluidGenerator.addEntry(FluidRegistry.getFluid("biogas"), 2000, 16);
        }*/

        /*if (Ic2cExtrasConfig.densePlatesTakePlates) {
            ClassicRecipes.compressor.removeRecipe(new RecipeInputOreDict("ingotCopper", 8));
            TileEntityCompressor.addRecipe("plateCopper", 9, Ic2Items.denseCopperPlate);
            TileEntityCompressor.addRecipe("plateIron", 9, new ItemStack(Registry.denseIronPlate));
            TileEntityCompressor.addRecipe("plateLead", 9, new ItemStack(Registry.denseLeadPlate));
        } else {
            TileEntityCompressor.addRecipe("ingotIron", 8, new ItemStack(Registry.denseIronPlate));
            TileEntityCompressor.addRecipe("ingotLead", 8, new ItemStack(Registry.denseLeadPlate));
        }*/
    }

    public static void initFurnaceRecipes() {
    }

    public static void initReplaceMaceratorRecipes() {
    }

    public static void initMetalBenderRecipes() {

    }
}
