package trinsdar.ic2c_extras.util.jei;

import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.core.IC2;
import ic2.jeiIntigration.SubModul;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.tileentity.TileEntityFluidCanningMachine;
import trinsdar.ic2c_extras.tileentity.TileEntityMetalBender;
import trinsdar.ic2c_extras.util.GuiMachine.CutterGui;
import trinsdar.ic2c_extras.util.GuiMachine.ExtruderGui;
import trinsdar.ic2c_extras.util.GuiMachine.FluidCanningGui;
import trinsdar.ic2c_extras.util.GuiMachine.MetalBenderGui;
import trinsdar.ic2c_extras.util.GuiMachine.OreWashingPlantGui;
import trinsdar.ic2c_extras.util.GuiMachine.RollerGui;
import trinsdar.ic2c_extras.util.GuiMachine.ThermalCentrifugeGui;
import trinsdar.ic2c_extras.util.Registry;
import trinsdar.ic2c_extras.util.recipelists.ContainerInputRecipeList;
import trinsdar.ic2c_extras.util.recipelists.FluidCanningRecipeList;

import javax.annotation.Nonnull;

@JEIPlugin
public class JeiPlugin implements IModPlugin {
    public String oreWashingId = "oreWashing";
    public String thermalCentrifugeId = "thermalCentrifuge";
    public String rollerId = "roller";
    public String extruderId = "extruder";
    public String cutterId = "cutter";
    public String metalBenderId = "metalBender";
    public String fluidCanningId = "fluidCanning";

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime arg0) {
        // empty method for construction
    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        if (SubModul.load){
            //thermal centrifuge
            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiThermalCentrifugeWrapper(var1);
                }
            }, thermalCentrifugeId);
            registry.addRecipeCatalyst(new ItemStack(Registry.thermalCentrifuge), thermalCentrifugeId);
            registry.addRecipeClickArea(ThermalCentrifugeGui.class, 48, 32, 44, 20, thermalCentrifugeId);
            registry.addRecipes(Ic2cExtrasRecipes.thermalCentrifuge.getRecipeMap(), thermalCentrifugeId);

            //ore washing plant
            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiOreWashingWrapper(var1);
                }
            }, oreWashingId);
            registry.addRecipeCatalyst(new ItemStack(Registry.oreWashingPlant), oreWashingId);
            registry.addRecipeCatalyst(new ItemStack(Registry.thermalWasher), oreWashingId);
            registry.addRecipeClickArea(OreWashingPlantGui.class, 78, 32, 20, 23, oreWashingId);
            registry.addRecipes(Ic2cExtrasRecipes.oreWashingPlant.getRecipeMap(), oreWashingId);

            //roller
            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiSimpleMachineWrapper(var1);
                }
            }, rollerId);
            registry.addRecipeCatalyst(new ItemStack(Registry.roller), rollerId);
            registry.addRecipeCatalyst(new ItemStack(Registry.impellerizedRoller), rollerId);
            registry.addRecipeCatalyst(new ItemStack(Registry.metalBender), rollerId);
            registry.addRecipeClickArea(RollerGui.class, 78, 32, 20, 23, rollerId);
            registry.addRecipes(Ic2cExtrasRecipes.rolling.getRecipeMap(), rollerId);

            //extruder
            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiSimpleMachineWrapper(var1);
                }
            }, extruderId);
            registry.addRecipeCatalyst(new ItemStack(Registry.extruder), extruderId);
            registry.addRecipeCatalyst(new ItemStack(Registry.liquescentExtruder), extruderId);
            registry.addRecipeCatalyst(new ItemStack(Registry.metalBender), extruderId);
            registry.addRecipeClickArea(ExtruderGui.class, 78, 32, 20, 23, extruderId);
            registry.addRecipes(Ic2cExtrasRecipes.extruding.getRecipeMap(), extruderId);

            //cutter
            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiSimpleMachineWrapper(var1);
                }
            }, cutterId);
            registry.addRecipeCatalyst(new ItemStack(Registry.cutter), cutterId);
            registry.addRecipeCatalyst(new ItemStack(Registry.plasmaCutter), cutterId);
            registry.addRecipeCatalyst(new ItemStack(Registry.metalBender), cutterId);
            registry.addRecipeClickArea(CutterGui.class, 78, 32, 20, 23, cutterId);
            registry.addRecipes(Ic2cExtrasRecipes.cutting.getRecipeMap(), cutterId);

            //metal bender
            registry.handleRecipes(ContainerInputRecipeList.ContainerInputRecipe.class, new IRecipeWrapperFactory<ContainerInputRecipeList.ContainerInputRecipe>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(ContainerInputRecipeList.ContainerInputRecipe var1)
                {
                    return new JeiMetalBenderWrapper(var1);
                }
            }, metalBenderId);
            registry.addRecipeCatalyst(new ItemStack(Registry.metalBender), metalBenderId);
            registry.addRecipeClickArea(MetalBenderGui.class, 78, 32, 20, 23, metalBenderId);
            registry.addRecipes(TileEntityMetalBender.metalBender.getRecipeList(), metalBenderId);

            //fluid canning machine
            registry.handleRecipes(FluidCanningRecipeList.FluidCanningRecipe.class, new IRecipeWrapperFactory<FluidCanningRecipeList.FluidCanningRecipe>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(FluidCanningRecipeList.FluidCanningRecipe var1)
                {
                    return new JeiFluidCanningWrapper(var1);
                }
            }, fluidCanningId);
            registry.addRecipeCatalyst(new ItemStack(Registry.fluidCanningMachine), fluidCanningId);
            registry.addRecipeClickArea(FluidCanningGui.class, 64, 35, 24, 17, fluidCanningId);
            registry.addRecipes(TileEntityFluidCanningMachine.fluidCanning.getRecipeList(), fluidCanningId);

            IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
            if (IC2.config.getFlag("NonRadiation")){
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.oreWashingPlant));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thermalCentrifuge));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.roller));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.extruder));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.cutter));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.impellerizedRoller));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.liquescentExtruder));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plasmaCutter));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.metalBender));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.fluidCanningMachine));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.treeTapper));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.advancedSteamTurbine));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thermoElectricGenerator));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thermoElectricGeneratorMKII));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.steelBlock));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.refinedIronBlock));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadBlock));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.stoneDustBlock));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.copperCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.tinCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.silverCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.ironCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.goldCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.refinedIronCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.steelCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.bronzeCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.copperPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.tinPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.silverPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.ironPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.goldPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.refinedIronPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.steelPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.bronzePlate));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.ironCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.goldCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.copperCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.tinCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.silverCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uraniumCrushedOre));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.ironPurifiedCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.goldPurifiedCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.copperPurifiedCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.tinPurifiedCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.silverPurifiedCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadPurifiedCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uraniumPurifiedCrushedOre));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.ironTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.goldTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.copperTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.tinTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.silverTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium235TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.obsidianTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.bronzeTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium238TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium232TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium230TinyDust));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.ironSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.goldSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.copperSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.tinSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.silverSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium235SmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.obsidianSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.bronzeSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium238SmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumSmallDust));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.stoneDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.slag));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium235));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium238));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.coil));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.heatConductor));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.steelIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.oxidizedUraniumIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleEnrichedUraniumIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.iridiumShard));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.diamondDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.energiumDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.emptyFuelRod));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium232Dust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium232Ingot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium230Dust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium230Ingot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.moxFuel));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.denseIronPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.blankPress));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.rollingPress));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.extrudingPress));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.cuttingPress));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.lathingPress));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.gearingPress));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedPlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedPlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumRTG));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thoriumRTG));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singleUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singlePlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doublePlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadPlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singleMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singleThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singleThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicPlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.craftingHammer));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.wireCutters));

            }else if (!Ic2cExtrasConfig.harderUranium){
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium235));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium238));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium235TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium238TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium235SmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium238SmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uraniumCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uraniumPurifiedCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleEnrichedUraniumIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.oxidizedUraniumIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium232TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium232Dust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium232Ingot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium230TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium230Dust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thorium230Ingot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.moxFuel));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.emptyFuelRod));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singleUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicUOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedUOXCell));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singlePlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doublePlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadPlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedPlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicPlutoniumCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedPlutoniumCell));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singleMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicMOXCell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedMOXCell));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singleThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicThorium232Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedThorium232Cell));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.singleThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.quadThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.nearDepletedThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.isotopicThorium230Cell));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.reEnrichedThorium230Cell));
            }
        }
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new JeiThermalCentrifugeCategory(helper));
        registry.addRecipeCategories(new JeiOreWashingCategory(helper));
        registry.addRecipeCategories(new JeiMetalBenderCategory(helper));
        registry.addRecipeCategories(new JeiFluidCanningCategory(helper));
        registry.addRecipeCategories(new JeiSimpleMachineCategory(helper, new ItemStack(Registry.roller), rollerId));
        registry.addRecipeCategories(new JeiSimpleMachineCategory(helper, new ItemStack(Registry.extruder), extruderId));
        registry.addRecipeCategories(new JeiSimpleMachineCategory(helper, new ItemStack(Registry.cutter), cutterId));
    }
}
