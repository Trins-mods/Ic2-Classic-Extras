package trinsdar.ic2c_extras.recipes;

public class CraftingRecipes {



    static final String MACHINE_BASIC = "machineBlockBasic";
    static final String MACHINE_ADV = "machineBlockAdvanced";

    public static void init() {
        initShapedRecipes();
        initShapelessRecipes();
        initReplaceRecipes();
        initCompressRecipes();
        removeIc2Recipes();
    }

    public static void initShapedRecipes() {
        /*FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
        recipes.addRecipe(new ItemStack(Registry.advancedSteamTurbine, 1),
                " S ", "STS", " S ", 'S', Ic2Items.basicTurbine, 'T', Ic2Items.transformerMV);
        String basicCircuit = "circuitBasic";
        recipes.addRecipe(new ItemStack(Registry.oreWashingPlant, 1),
                "III", "BCB", "McM", 'I', IC2.getRefinedIron(), 'B', Items.BUCKET, 'C', MACHINE_BASIC, 'M', Ic2Items.carbonMesh, 'c', basicCircuit);
        recipes.addRecipe(new ItemStack(Registry.thermalWasher, 1),
                "BBB", "BOB", "BAB", 'B', Items.BUCKET, 'O', Registry.oreWashingPlant, 'A', MACHINE_ADV);
        recipes.addRecipe(new ItemStack(Registry.thermalCentrifuge, 1),
                "CMC", "IAI", "IHI", 'C', Registry.coil, 'M', Ic2Items.miningLaser, 'I', IC2.getRefinedIron(), 'A', MACHINE_ADV, 'H', Registry.heatConductor);

        recipes.addRecipe(new ItemStack(Registry.fluidCanningMachine), " C ", "EcE", "ITI", 'C', Ic2Items.electricCircuit, 'E', Ic2Items.emptyCell, 'c', Ic2Items.canner, 'T', Ic2Items.machineTank, 'I', "ingotTin");

        recipes.addRecipe(new ItemStack(Registry.electricHeatGenerator), "CBC", "CcC", "CHC", 'C', "casing" + IC2.getRefinedIron().substring(5), 'B', Ic2Items.battery, 'c', "circuitAdvanced", 'H', Registry.heatConductor);

        recipes.addRecipe(new ItemStack(Registry.fermenter), "CCC", "ccc", "CHC", 'C', "casing" + IC2.getRefinedIron().substring(5), 'c', Ic2Items.emptyCell, 'H', Registry.heatConductor);
        //recipes.addRecipe(new ItemStack(Registry.cutter, 1),
        //        " C ", "TBT", "ctc", 'C', basicCircuit,'T', Ic2Items.toolBox, 'B', MACHINE_BASIC, 'c', Registry.coil, 't', Ic2Items.cutter);

        //recipes.addRecipe(new ItemStack(Registry.plasmaCutter, 1),
        //        "CCC", "CcC", "CBC", 'c', Registry.cutter,'B', MACHINE_ADV, 'C', Ic2Items.cutter);



        recipes.addRecipe(new ItemStack(Registry.treeTapper), "CTC", "HcH", "MAM", 'C', "circuitBasic", 'T', Ic2Items.electricTreeTap, 'H', new ItemStack(Blocks.HOPPER, 2), 'c', "chestWood", 'M', StackUtil.copyWithSize(Ic2Items.miningPipe, 8), 'A', MACHINE_ADV);

        recipes.addRecipe(new ItemStack(Registry.reinforcedEncasedCable), "RRR", "RCR", "RRR", 'R', Ic2Items.reinforcedStone, 'C', Ic2Items.tribbleInsulatedIronCable);

        recipes.addRecipe(new ItemStack(Registry.coil, 1),
                "CCC", "CIC", "CCC", 'I', IC2.getRefinedIron(), 'C', Ic2Items.copperCable);

        recipes.addRecipe(new ItemStack(Registry.heatConductor, 1),
                "RRB", "RBR", "BRR", 'R', "itemRubber", 'B', "ingotCopper");

        recipes.addShapelessRecipe(new ItemStack(Registry.universalFluidCell), Ic2Items.emptyCell.copy());
        recipes.addShapelessRecipe(Ic2Items.emptyCell.copy(), cell(), new ItemStack(Registry.universalFluidCell));

        recipes.addRecipe(new ItemStack(Registry.craftingHammer, 1),
                "III", "III", " S ", 'I', "ingotRefinedIron", 'S', "stickWood");

        recipes.addRecipe(new ItemStack(Registry.wireCutters, 1),
                "I I", " I ", "S S", 'I', "ingotRefinedIron", 'S', "stickWood");

        recipes.addRecipe(Ic2Items.fuelCan,
                " TT", "T T", "TTT", 'T', "casingTin");

        recipes.addRecipe(new ItemStack(Registry.stoneDustBlock), "SS", "SS", 'S', "dustStone");

        recipes.addRecipe(Ic2Items.reactorCoolantCellSimple, " T ", "TWT", " T ", 'T', "casingTin", 'W', water);
        recipes.addRecipe(Ic2Items.reactorCoolantCellTriple, "TTT", "CCC", "TTT", 'T', "casingTin", 'C', Ic2Items.reactorCoolantCellSimple);
        recipes.addRecipe(Ic2Items.reactorCoolantCellTriple, "TTT", "WWW", "TTT", 'T', new RecipeInputOreDict("casingTin", 2), 'W', StackUtil.copyWithSize(Ic2Items.waterCell, 2));
        recipes.addRecipe(Ic2Items.reactorCoolantCellSix, "TCT", "TDT", "TCT", 'T', "casingTin", 'C', Ic2Items.reactorCoolantCellTriple, 'D', Ic2Items.denseCopperPlate);

        recipes.addRecipe(StackUtil.copyWithSize(Ic2Items.emptyCell, 16),
                " T ", "T T", " T ", 'T', "casingTin");

        recipes.addRecipe(new ItemStack(Registry.energiumDust, 9), "RRR", "RDR", "RRR", 'R', new RecipeInputOreDict("dustRedstone", 2), 'D', "dustDiamond");

        recipes.addShapelessRecipe(Ic2Items.nanoHelmet.copy(), (new FlagModifier(Ic2Items.nanoHelmet.copy(), "ReactorPlating", true)).setUsesInput(), Ic2Items.nanoHelmet.copy(), Ic2Items.reactorPlating.copy());
        recipes.addShapelessRecipe(Ic2Items.nanoChest.copy(), (new FlagModifier(Ic2Items.nanoChest.copy(), "ReactorPlating", true)).setUsesInput(), Ic2Items.nanoChest.copy(), Ic2Items.reactorPlating.copy());
        recipes.addShapelessRecipe(Ic2Items.nanoLeggings.copy(), (new FlagModifier(Ic2Items.nanoLeggings.copy(), "ReactorPlating", true)).setUsesInput(), Ic2Items.nanoLeggings.copy(), Ic2Items.reactorPlating.copy());
        recipes.addShapelessRecipe(Ic2Items.nanoBoots.copy(), (new FlagModifier(Ic2Items.nanoBoots.copy(), "ReactorPlating", true)).setUsesInput(), Ic2Items.nanoBoots.copy(), Ic2Items.reactorPlating.copy());

        if (Ic2cExtrasConfig.requiredLeadUses){
            recipes.overrideRecipe("shaped_tile.blockreactorchamber_1490756150", Ic2Items.reactorChamber.copy(), " L ", "LML", " L ", 'L', "plateDenseLead", 'M', "machineBlockBasic");
            recipes.overrideRecipe("shaped_tile.blocknuclearreactor_1318009097", Ic2Items.nuclearReactor.copy(), "LCL", "RRR", "LGL", 'L', "plateDenseLead", 'C', "circuitAdvanced", 'R', Ic2Items.reactorChamber, 'G', Ic2Items.generator);
            if (!Loader.isModLoaded("gtc_expansion") || !Ic2cExtrasConfig.compatGTCX) {
                recipes.overrideShapelessRecipe("shapeless_item.itemreactorplating_1093967048", Ic2Items.reactorPlating, "ingotLead", Ic2Items.advancedAlloy);
            }
        } else {
            recipes.addRecipe(Ic2Items.reactorChamber.copy(), " L ", "LML", " L ", 'L', "plateDenseLead", 'M', "machineBlockBasic");
            if (!Loader.isModLoaded("gtc_expansion") || !Ic2cExtrasConfig.compatGTCX) {
                recipes.addShapelessRecipe(Ic2Items.reactorPlating, "ingotLead", Ic2Items.advancedAlloy);
            }
        }

        recipes.addRecipe(new ItemStack(Registry.containmentBox), "LLL", "LCL", "LLL", 'L', "casingLead", 'C', "chest");*/


    }

    public static void removeIc2Recipes(){
    }

    public static void initReplaceRecipes() {

    }

    public static void initShapelessRecipes() {
        /*FluidStack water = new FluidStack(FluidRegistry.WATER, 1000);
        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.bronzeDust, 4), crushedCopper, crushedCopper, crushedCopper, crushedTin);

        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.constructionFoam, 4), water, "dustRedstone", "dustCoal", "dustStone", "dustStone", "dustStone");
        recipes.addShapelessRecipe(StackUtil.copyWithSize(Ic2Items.constructionFoam, 4), water, "dustRedstone", "dustCharcoal", "dustStone", "dustStone", "dustStone");

        recipes.addShapelessRecipe(new ItemStack(Registry.stoneDust, 4), Registry.stoneDustBlock);*/
    }

    public static void initCompressRecipes() {
    }
}
