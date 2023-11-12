package trinsdar.ic2c_extras.recipes;


import ic2.api.recipes.registries.IAdvancedCraftingManager;
import ic2.core.IC2;
import ic2.core.item.reactor.base.IUraniumRod;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.registries.IC2Tags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.IC2CExtrasConfig;
import trinsdar.ic2c_extras.init.IC2CExtrasTags;
import trinsdar.ic2c_extras.init.ModBlocks;
import trinsdar.ic2c_extras.init.ModItems;
import trinsdar.ic2c_extras.nuclear.MOX;
import trinsdar.ic2c_extras.nuclear.Plutonium;
import trinsdar.ic2c_extras.nuclear.Thorium232;
import trinsdar.ic2c_extras.nuclear.Uranium233;
import trinsdar.ic2c_extras.nuclear.Uranium235;
import trinsdar.ic2c_extras.nuclear.Uranium238;

public class CraftingRecipes {

    public static void loadRecipes(IAdvancedCraftingManager registry){
        if (IC2CExtrasConfig.DISABLE_NON_RADIATION.get()) return;
        addDustRecipe(registry, "iron", IC2Items.DUST_IRON, ModItems.TINY_IRON_DUST);
        addDustRecipe(registry, "gold", IC2Items.DUST_GOLD, ModItems.TINY_GOLD_DUST);
        addDustRecipe(registry, "copper", IC2Items.DUST_COPPER, ModItems.TINY_COPPER_DUST);
        addDustRecipe(registry, "tin", IC2Items.DUST_TIN, ModItems.TINY_TIN_DUST);
        addDustRecipe(registry, "aluminium", IC2Items.DUST_ALUMINIUM, ModItems.TINY_ALUMINIUM_DUST);
        addDustRecipe(registry, "silver", IC2Items.DUST_SILVER, ModItems.TINY_SILVER_DUST);
        addDustRecipe(registry, "lead", ModItems.LEAD_DUST, ModItems.TINY_LEAD_DUST);
        addDustRecipe(registry, "uranium233", ModItems.URANIUM_233_DUST, ModItems.TINY_URANIUM_233_DUST);
        addDustRecipe(registry, "uranium235", ModItems.URANIUM_235_DUST, ModItems.TINY_URANIUM_235_DUST);
        addDustRecipe(registry, "uranium", ModItems.URANIUM_DUST, ModItems.TINY_URANIUM_238_DUST);
        addDustRecipe(registry, "plutonium", ModItems.PLUTONIUM_DUST, ModItems.TINY_PLUTONIUM_DUST);
        addDustRecipe(registry, "thorium", ModItems.THORIUM_DUST, ModItems.TINY_THORIUM_DUST);

        registry.addShapedRecipe(id("thermal_centrifuge"), new ItemStack(ModBlocks.THERMAL_CENTRIFUGE), "CLC", "RMR", "RHR", 'C', ModItems.COIL, 'L', IC2Items.MINING_LASER, 'R', IC2Tags.INGOT_REFINED_IRON, 'M', IC2Blocks.ADVANCED_MACHINE_BLOCK, 'H', ModItems.HEAT_CONDUCTOR);
        registry.addShapedRecipe(id("coil"), new ItemStack(ModItems.COIL), "CCC", "CRC", "CCC", 'C', IC2Items.COPPER_CABLE, 'R', IC2Tags.INGOT_REFINED_IRON);
        registry.addShapedRecipe(id("heat_conductor"), new ItemStack(ModItems.HEAT_CONDUCTOR), "RRC", "RCR", "CRR", 'R', IC2Items.RUBBER, 'C', IC2Tags.INGOT_COPPER);
        registry.addShapedRecipe(new ResourceLocation(IC2CExtrasConfig.REACTOR_CHAMBER_REQUIRES_LEAD.get() ? "ic2" : IC2CExtras.MODID, "reactor_chamber"), new ItemStack(IC2Blocks.REACTOR_CHAMBER), " D ", "DMD", " D ", 'D', ModItems.DENSE_LEAD_PLATE, 'M', IC2Blocks.MACHINE_BLOCK);
        registry.addShapedRecipe(id("ore_washing_plant"), new ItemStack(ModBlocks.ORE_WASHING_PLANT), "RRR", "BMB", "cCc", 'R', IC2Tags.INGOT_REFINED_IRON, 'B', Items.BUCKET, 'M', IC2Blocks.MACHINE_BLOCK, 'c', IC2Items.CARBON_MESH, 'C', IC2Items.CIRCUIT);
        registry.addShapelessRecipe(new ResourceLocation(IC2CExtras.MODID, "plating"), new ItemStack(IC2Items.PLATING), IC2CExtrasTags.getForgeItemTag("ingots/lead"), IC2Items.PLATE_ADVANCED_ALLOY);
        registry.addShapedRecipe(new ResourceLocation(IC2CExtras.MODID, "containment_box"), new ItemStack(ModItems.CONTAINMENT_BOX), " L ", "LCL", " L ", 'L', IC2CExtrasTags.getForgeItemTag("ingots/lead"), 'C', IC2CExtrasTags.getForgeItemTag("chests/wooden"));

        registry.addShapelessIC2Recipe("uranium__single_2", new ItemStack(IC2Items.URANIUM_ROD_SINGLE), getEmptyNuclearCell(), getUraniumRodIngredientItem());
        registry.addShapelessIC2Recipe("uranium__single_2_tag", new ItemStack(IC2Items.URANIUM_ROD_SINGLE), getEmptyNuclearCell(), getUraniumRodIngredient());
        registry.addShapedIC2Recipe("uranium__near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', getUraniumRodIngredientItem());
        registry.addShapedIC2Recipe("uranium__near_depleted_tag", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', getUraniumRodIngredient());
        if (IC2CExtrasConfig.EMPTY_NUCLEAR_ROD.get()){
            registry.addShapedRecipe(id("empty_nuclear_rod"), new ItemStack(ModItems.EMPTY_NUCLEAR_ROD, 4), " I ", "I I", " I ", 'I', IC2Tags.INGOT_REFINED_IRON);
            registry.addShapedIC2Recipe("uranium_redstone_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_REDSTONE, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_REDSTONE);
            registry.addShapedIC2Recipe("uranium_blaze_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_BLAZE, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_BLAZE);
            registry.addShapedIC2Recipe("uranium_ender_pearl_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_ENDER_PEARL, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_ENDERPEARL);
            registry.addShapedIC2Recipe("uranium_nether_star_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_NETHER_STAR, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_NETHERSTAR);
            registry.addShapedIC2Recipe("uranium_charcoal_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_CHARCOAL, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_CHARCOAL);
        }
        if (IC2CExtrasConfig.EXTRA_NUCLEAR.get()){
            registry.addShapedRecipe(id("thermo_electric_generator"), new ItemStack(ModBlocks.THERMO_ELECTRIC_GENERATOR), "III", "IRI", "ITI", 'I', ModItems.DENSE_IRON_PLATE, 'R', IC2Blocks.NUCLEAR_REACTOR, 'T', IC2Blocks.THERMAL_GENERATOR);
            registry.addShapedRecipe(id("plutonium_rtg"), new ItemStack(ModItems.PLUTONIUM_RTG), "IPI", "IPI", "IPI", 'I', ModItems.DENSE_IRON_PLATE, 'P', IC2CExtrasTags.getForgeItemTag("ingots/plutonium"));
            registry.addShapedRecipe(id("mox_fuel"), new ItemStack(ModItems.MOX_FUEL, 3), "UUU", "PPP", "UUU", 'U', IC2CExtrasTags.getForgeItemTag("dusts/uranium"), 'P', IC2CExtrasTags.getForgeItemTag("tiny_dusts/plutonium"));
            registry.addShapedRecipe(id("enriched_uranium_fuel"), new ItemStack(ModItems.ENRICHED_URANIUM_FUEL, 3), "UUU", "PPP", "UUU", 'U', IC2CExtrasTags.getForgeItemTag("dusts/uranium"), 'P', IC2CExtrasTags.getForgeItemTag("tiny_dusts/uranium235"));
            addRodRecipes(registry, MOX.INSTANCE);
            addRodRecipes(registry, Plutonium.INSTANCE);
            addRodRecipes(registry, Thorium232.INSTANCE);
            addRodRecipes(registry, Uranium233.INSTANCE);
            addRodRecipes(registry, Uranium235.INSTANCE);
            addRodRecipes(registry, Uranium238.INSTANCE);
        }
        loadSmeltingRecipe(registry);
    }

    private static void loadSmeltingRecipe(IAdvancedCraftingManager registry){
        addCrushedSmeltingRecipe(registry, "iron", Items.IRON_INGOT, 0.35f);
        addCrushedSmeltingRecipe(registry, "gold", Items.GOLD_INGOT, 0.5f);
        addCrushedSmeltingRecipe(registry, "copper", Items.COPPER_INGOT, 0.15f);
        addCrushedSmeltingRecipe(registry, "tin", IC2Items.INGOT_TIN, 0.2f);
        addCrushedSmeltingRecipe(registry, "silver", IC2Items.INGOT_SILVER, 0.4f);
        addCrushedSmeltingRecipe(registry, "lead", ModItems.LEAD_INGOT, 0.4f);
        registry.addSmeltingRecipe(id("lead_dust"), new ItemStack(ModItems.LEAD_INGOT), IC2CExtrasTags.getForgeItemTag("dusts/lead"), 0.4f, IAdvancedCraftingManager.SmeltingType.BLASTFURNACE, IAdvancedCraftingManager.SmeltingType.FURNACE);
    }

    private static void addCrushedSmeltingRecipe(IAdvancedCraftingManager registry, String dust, Item output, float xp){
        registry.addSmeltingRecipe(id("crushed_" + dust + "_to_ingot"), new ItemStack(output), IC2CExtrasTags.getForgeItemTag("crushed_ores/" + dust), xp, IAdvancedCraftingManager.SmeltingType.BLASTFURNACE, IAdvancedCraftingManager.SmeltingType.FURNACE);
        registry.addSmeltingRecipe(id("purified_" + dust + "_to_ingot"), new ItemStack(output), IC2CExtrasTags.getForgeItemTag("purified_ores/" + dust), xp, IAdvancedCraftingManager.SmeltingType.BLASTFURNACE, IAdvancedCraftingManager.SmeltingType.FURNACE);
    }


    public static Item getUraniumRodIngredientItem(){
        return IC2CExtrasConfig.EXTRA_NUCLEAR.get() ? ModItems.ENRICHED_URANIUM_FUEL : IC2Items.INGOT_URANIUM;
    }

    public static Object getUraniumRodIngredient(){
        return IC2CExtrasConfig.EXTRA_NUCLEAR.get() ? ModItems.ENRICHED_URANIUM_FUEL : IC2Tags.INGOT_URANIUM;
    }

    public static Item getEmptyNuclearCell(){
        return IC2CExtrasConfig.EMPTY_NUCLEAR_ROD.get() ? ModItems.EMPTY_NUCLEAR_ROD : IC2Items.CELL_EMPTY;
    }

    private static void addDustRecipe(IAdvancedCraftingManager registry, String dust, Item output, Item tiny){
        registry.addShapelessRecipe(id(dust + "_dust"), new ItemStack(output), IC2CExtrasTags.getForgeItemTag("tiny_dusts/" + dust), IC2CExtrasTags.getForgeItemTag("tiny_dusts/" + dust)
                , IC2CExtrasTags.getForgeItemTag("tiny_dusts/" + dust), IC2CExtrasTags.getForgeItemTag("tiny_dusts/" + dust), IC2CExtrasTags.getForgeItemTag("tiny_dusts/" + dust)
                , IC2CExtrasTags.getForgeItemTag("tiny_dusts/" + dust), IC2CExtrasTags.getForgeItemTag("tiny_dusts/" + dust), IC2CExtrasTags.getForgeItemTag("tiny_dusts/" + dust)
                , IC2CExtrasTags.getForgeItemTag("tiny_dusts/" + dust));
        registry.addShapelessRecipe(id(dust + "_tiny_dust"), new ItemStack(tiny, 9), IC2CExtrasTags.getForgeItemTag("dusts/" + dust));
    }

    private static void addRodRecipes(IAdvancedCraftingManager recipes, IUraniumRod rod){
        recipes.addShapedRecipe(id("near_depleted_" + rod.getName()), rod.createNearDepletedRod(8), "XXX", "XYX", "XXX", 'Y', rod.getBaseIngot(), 'X', getEmptyNuclearCell());
        recipes.addShapelessRecipe(id("isotopic_" + rod.getName() + "_0"), rod.createIsotopicRod(), rod.createNearDepletedRod(1), IC2Tags.DUST_COAL);
        recipes.addShapelessRecipe(id("isotopic_" + rod.getName() + "_1"), rod.createIsotopicRod(), rod.createNearDepletedRod(), IC2Tags.DUST_CHARCOAL);
        recipes.addShapelessRecipe(id("single_" + rod.getName() + "_0"), rod.createSingleRod(), IC2Tags.DUST_COAL, rod.createReEnrichedRod());
        recipes.addShapelessRecipe(id("single_" + rod.getName() + "_1"), rod.createSingleRod(), IC2Tags.DUST_CHARCOAL, rod.createReEnrichedRod());
        recipes.addShapedRecipe(id("dual_" + rod.getName()), rod.createDualRod(), "UCU", 'U', rod.createSingleRod(), 'C', IC2Items.PLATE_DENSE_COPPER);
        recipes.addShapedRecipe(id("quad_" + rod.getName() + "_0"), rod.createQuadRod(), " U ", "CCC", " U ", 'U', rod.createDualRod(), 'C', IC2Items.PLATE_DENSE_COPPER);
        recipes.addShapedRecipe(id("quad_" + rod.getName() + "_1"), rod.createQuadRod(), "UCU", "CCC", "UCU", 'U', rod.createSingleRod(), 'C', IC2Items.PLATE_DENSE_COPPER);
        if (!rod.isEnrichedUranium() || !IC2.CONFIG.enableHardEnrichedUranium.get()) {
            recipes.addShapelessRecipe(id("single_" + rod.getName() + "_2"), rod.createSingleRod(), getEmptyNuclearCell(), rod.getBaseIngot());
        }
    }

    public static ResourceLocation id(String id){
        return new ResourceLocation(IC2CExtras.MODID, id);
    }
}
