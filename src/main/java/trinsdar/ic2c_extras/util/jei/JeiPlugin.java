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
import trinsdar.ic2c_extras.util.GuiMachine.*;
import trinsdar.ic2c_extras.util.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.Registry;

import javax.annotation.Nonnull;

@JEIPlugin
public class JeiPlugin implements IModPlugin {
    public String oreWashingId = "oreWashing";
    public String thermalCentrifugeId = "thermalCentrifuge";
    public String rollerId = "roller";
    public String extruderId = "extruder";
    public String cutterId = "cutter";

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
                    return new JeiRollerWrapper(var1);
                }
            }, "roller");
            registry.addRecipeCatalyst(new ItemStack(Registry.roller), rollerId);
            registry.addRecipeCatalyst(new ItemStack(Registry.impellerizedRoller), rollerId);
            registry.addRecipeClickArea(RollerGui.class, 78, 32, 20, 23, rollerId);
            registry.addRecipes(Ic2cExtrasRecipes.rolling.getRecipeMap(), rollerId);

            //extruder
            registry.handleRecipes(IMachineRecipeList.RecipeEntry.class, new IRecipeWrapperFactory<IMachineRecipeList.RecipeEntry>(){
                @Override
                public IRecipeWrapper getRecipeWrapper(IMachineRecipeList.RecipeEntry var1)
                {
                    return new JeiExtruderWrapper(var1);
                }
            }, extruderId);
            registry.addRecipeCatalyst(new ItemStack(Registry.extruder), extruderId);
            registry.addRecipeCatalyst(new ItemStack(Registry.liquescentExtruder), extruderId);
            registry.addRecipeClickArea(ExtruderGui.class, 78, 32, 20, 23, extruderId);
            registry.addRecipes(Ic2cExtrasRecipes.extruding.getRecipeMap(), extruderId);

            IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
            blacklist.addIngredientToBlacklist(new ItemStack(Registry.blastFurnace));
            blacklist.addIngredientToBlacklist(new ItemStack(Registry.solidFuelFirebox));
            blacklist.addIngredientToBlacklist(new ItemStack(Registry.liquidFuelFirebox));
            blacklist.addIngredientToBlacklist(new ItemStack(Registry.electricHeater));
            if (IC2.config.getFlag("NonRadiation")){
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.oreWashingPlant));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.thermalCentrifuge));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.roller));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.extruder));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.cutter));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.impellerizedRoller));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.liquescentExtruder));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plasmaCutter));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.blastFurnace));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.advancedSteamTurbine));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.solidFuelFirebox));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.liquidFuelFirebox));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.electricHeater));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.steelBlock));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.refinedIronBlock));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadBlock));

                blacklist.addIngredientToBlacklist(new ItemStack(Registry.copperCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.tinCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.silverCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.leadCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.ironCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.goldCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.refinedIronCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.steelCasing));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.bronzeCasing));
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
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutonium));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.coil));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.heatConductor));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.steelIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumEnrichedUraniumIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleEnrichedUraniumIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.iridiumShard));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.refinedIronPlate));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.diamondDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.energiumDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.craftingHammer));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.wireCutters));

            }else if (!Ic2cExtrasRecipes.enableHarderUranium){
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium235));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium238));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium235TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium238TinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium235SmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uranium238SmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uraniumCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.uraniumPurifiedCrushedOre));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutonium));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumTinyDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumSmallDust));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.doubleEnrichedUraniumIngot));
                blacklist.addIngredientToBlacklist(new ItemStack(Registry.plutoniumEnrichedUraniumIngot));
            }
        }
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new JeiThermalCentrifugeCategory(helper));
        registry.addRecipeCategories(new JeiOreWashingCategory(helper));
        registry.addRecipeCategories(new JeiRollerCategory(helper));
        registry.addRecipeCategories(new JeiExtruderCategory(helper));
    }
}
