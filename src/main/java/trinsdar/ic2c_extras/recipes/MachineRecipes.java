package trinsdar.ic2c_extras.recipes;

import ic2.api.recipes.ingridients.inputs.IInput;
import ic2.api.recipes.ingridients.inputs.ItemInput;
import ic2.api.recipes.ingridients.inputs.ItemTagInput;
import ic2.api.recipes.misc.RecipeMods;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.IC2;
import ic2.core.block.machines.recipes.misc.EnrichRecipe;
import ic2.core.item.reactor.base.IUraniumRod;
import ic2.core.item.reactor.urantypes.BlazeUranium;
import ic2.core.item.reactor.urantypes.CharcoalUranium;
import ic2.core.item.reactor.urantypes.EnderUranium;
import ic2.core.item.reactor.urantypes.NetherStarUranium;
import ic2.core.item.reactor.urantypes.RedstoneUranium;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.registries.IC2Tags;
import ic2.core.utils.math.ColorUtils;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.loading.FMLEnvironment;
import trinsdar.ic2c_extras.IC2CExtrasConfig;
import trinsdar.ic2c_extras.init.IC2CExtrasTags;
import trinsdar.ic2c_extras.init.ModItems;
import trinsdar.ic2c_extras.nuclear.MOX;
import trinsdar.ic2c_extras.nuclear.Plutonium;
import trinsdar.ic2c_extras.nuclear.Thorium232;
import trinsdar.ic2c_extras.nuclear.Uranium233;
import trinsdar.ic2c_extras.nuclear.Uranium235;
import trinsdar.ic2c_extras.nuclear.Uranium238;
import trinsdar.ic2c_extras.recipes.recipelists.ExtendedRecipeList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static trinsdar.ic2c_extras.recipes.CraftingRecipes.id;

public class MachineRecipes {
    public static final ExtendedRecipeList ORE_WASHING_PLANT = new ExtendedRecipeList("ore_washing_plant", MachineRecipes::initOreWasherRecipes);
    public static final ExtendedRecipeList THERMAL_CENTRIFUGE = new ExtendedRecipeList("thermal_centrifuge", MachineRecipes::initThermalCentrifugeRecipes);

    public static void init(){
        IC2.RECIPES.get(true).getLists().add(ORE_WASHING_PLANT);
        IC2.RECIPES.get(true).getLists().add(THERMAL_CENTRIFUGE);
        if (FMLEnvironment.dist.isClient()){
            IC2.RECIPES.get(false).getLists().add(ORE_WASHING_PLANT);
            IC2.RECIPES.get(false).getLists().add(THERMAL_CENTRIFUGE);
        }
        IC2.RECIPES.get(true).macerator.registerListener(MachineRecipes::initMaceratorRecipes);
        IC2.RECIPES.get(true).extractor.registerListener(r -> {
            if (IC2CExtrasConfig.EXTRA_NUCLEAR.get() && !IC2CExtrasConfig.DISABLE_NON_RADIATION.get()){
                r.removeRecipe(new ResourceLocation("ic2", "uranium_extraction"));
            }
        });
        IC2.RECIPES.get(true).compressor.registerListener(r -> {
            if (IC2CExtrasConfig.DISABLE_NON_RADIATION.get()) return;
            r.addSimpleRecipe(id("dense_lead_plate"), new ItemStack(ModItems.DENSE_LEAD_PLATE), new ItemTagInput(IC2CExtrasTags.getForgeItemTag("ingots/lead"), 8));
            r.addSimpleRecipe(id("dense_iron_plate"), new ItemStack(ModItems.DENSE_IRON_PLATE), new ItemTagInput(IC2CExtrasTags.getForgeItemTag("ingots/iron"), 8));
            if (IC2CExtrasConfig.EXTRA_NUCLEAR.get()){
                r.addSimpleRecipe(id("enriched_uranium_ingot"), new ItemStack(ModItems.ENRICHED_URANIUM_INGOT), ModItems.ENRICHED_URANIUM_FUEL);
                r.addSimpleRecipe(id("mox_ingot"), new ItemStack(ModItems.MOX_INGOT), ModItems.MOX_FUEL);
                r.addSimpleRecipe(id("plutonium_ingot"), new ItemStack(ModItems.PLUTONIUM_INGOT), IC2CExtrasTags.getForgeItemTag("dusts/plutonium"));
                r.addSimpleRecipe(id("thorium_ingot"), new ItemStack(ModItems.THORIUM_INGOT), IC2CExtrasTags.getForgeItemTag("dusts/thorium"));
                r.addIC2SimpleRecipe("uranium_ingot", new ItemStack(IC2Items.INGOT_URANIUM), IC2CExtrasTags.getForgeItemTag("dusts/uranium"));
                r.addSimpleRecipe(id("uranium_233_ingot"), new ItemStack(ModItems.URANIUM_233_INGOT), IC2CExtrasTags.getForgeItemTag("dusts/uranium233"));
                r.addSimpleRecipe(id("uranium_235_ingot"), new ItemStack(ModItems.URANIUM_235_INGOT), IC2CExtrasTags.getForgeItemTag("dusts/uranium235"));
            }
            ORE_WASHING_PLANT.reload();
            THERMAL_CENTRIFUGE.reload();
        });
        IC2.RECIPES.get(true).enricher.registerListener(r -> {
            if (!IC2CExtrasConfig.DISABLE_NON_RADIATION.get() && IC2CExtrasConfig.EXTRA_NUCLEAR.get()){
                r.removeRecipe(new ResourceLocation("ic2", "blaze"));
                r.removeRecipe(new ResourceLocation("ic2", "charcoal"));
                r.removeRecipe(new ResourceLocation("ic2", "ender"));
                r.removeRecipe(new ResourceLocation("ic2", "nether_star"));
                r.removeRecipe(new ResourceLocation("ic2", "redstone"));
                r.registerRecipe(EnrichRecipe.createIC2Recipe(ModItems.ENRICHED_URANIUM_INGOT, Items.BLAZE_ROD, 200, IC2Items.INGOT_URANIUM_ENRICHED_BLAZE, 100, ColorUtils.rgb(232, 155, 7, 255), 20, "blaze"));
                Object2IntMap<IInput> inputs = new Object2IntLinkedOpenHashMap<>();
                inputs.put(new ItemInput(Items.CHARCOAL), 25);
                inputs.put(new ItemInput(IC2Blocks.CHARCOAL_BLOCK), 200);
                r.registerRecipe(EnrichRecipe.createIC2Recipe(ModItems.ENRICHED_URANIUM_INGOT, inputs, IC2Items.INGOT_URANIUM_ENRICHED_CHARCOAL, 100, ColorUtils.rgb(54, 54, 54, 255), 5, "charcoal"));
                r.registerRecipe(EnrichRecipe.createIC2Recipe(ModItems.ENRICHED_URANIUM_INGOT, Items.ENDER_PEARL, 100, IC2Items.INGOT_URANIUM_ENRICHED_ENDERPEARL, 100, ColorUtils.rgb(35, 174, 113, 255), 75, "ender"));
                r.registerRecipe(EnrichRecipe.createIC2Recipe(ModItems.ENRICHED_URANIUM_INGOT, Items.NETHER_STAR, 200, IC2Items.INGOT_URANIUM_ENRICHED_NETHERSTAR, 150, ColorUtils.rgb(255, 239, 106, 255), 150, "nether_star"));
                inputs = new Object2IntLinkedOpenHashMap<>();
                inputs.put(new ItemInput(Items.REDSTONE), 25);
                inputs.put(new ItemInput(Items.REDSTONE_BLOCK), 200);
                r.registerRecipe(EnrichRecipe.createIC2Recipe(ModItems.ENRICHED_URANIUM_INGOT, inputs, IC2Items.INGOT_URANIUM_ENRICHED_REDSTONE, 100, ColorUtils.RED, 25, "redstone"));
            }
        });
        IC2.RECIPES.get(true).canner.registerListener(r -> {
            if (IC2CExtrasConfig.DISABLE_NON_RADIATION.get()) return;
            List<Tuple<IInput, ItemStack>> addList = new ArrayList<>();
            if (r.getFillables().containsKey(new ItemStack(IC2Items.CELL_EMPTY))){
                r.getFillables().get(new ItemStack(IC2Items.CELL_EMPTY)).stream().forEach(t -> {
                    if (t.getB().getItem() != IC2Items.URANIUM_ROD_SINGLE){
                        addList.add(t);
                    }
                });
            }
            r.removeFillable(new ItemStack(IC2Items.CELL_EMPTY));
            addList.forEach(t -> {
                r.registerFillable(new ItemStack(IC2Items.CELL_EMPTY), t.getA(), t.getB());
            });
            IInput uranium = IC2CExtrasConfig.EXTRA_NUCLEAR.get() ? new ItemInput(ModItems.ENRICHED_URANIUM_INGOT) : new ItemTagInput(IC2Tags.INGOT_URANIUM);
            r.registerFillable(new ItemStack(CraftingRecipes.getEmptyNuclearCell()), uranium, new ItemStack(IC2Items.URANIUM_ROD_SINGLE));
            if (!IC2.CONFIG.enableHardEnrichedUranium.get() && IC2CExtrasConfig.EMPTY_NUCLEAR_ROD.get()){
                r.removeFillable(new ItemStack(IC2Items.CELL_EMPTY), new ItemStack(IC2Items.INGOT_URANIUM_ENRICHED_REDSTONE));
                r.registerFillable(new ItemStack(CraftingRecipes.getEmptyNuclearCell()), new ItemInput(IC2Items.INGOT_URANIUM_ENRICHED_REDSTONE), new ItemStack(IC2Items.URANIUM_ROD_REDSTONE_SINGLE));
                r.removeFillable(new ItemStack(IC2Items.CELL_EMPTY), new ItemStack(IC2Items.INGOT_URANIUM_ENRICHED_BLAZE));
                r.registerFillable(new ItemStack(CraftingRecipes.getEmptyNuclearCell()), new ItemInput(IC2Items.INGOT_URANIUM_ENRICHED_BLAZE), new ItemStack(IC2Items.URANIUM_ROD_BLAZE_SINGLE));
                r.removeFillable(new ItemStack(IC2Items.CELL_EMPTY), new ItemStack(IC2Items.INGOT_URANIUM_ENRICHED_CHARCOAL));
                r.registerFillable(new ItemStack(CraftingRecipes.getEmptyNuclearCell()), new ItemInput(IC2Items.INGOT_URANIUM_ENRICHED_CHARCOAL), new ItemStack(IC2Items.URANIUM_ROD_CHARCOAL_SINGLE));
                r.removeFillable(new ItemStack(IC2Items.CELL_EMPTY), new ItemStack(IC2Items.INGOT_URANIUM_ENRICHED_NETHERSTAR));
                r.registerFillable(new ItemStack(CraftingRecipes.getEmptyNuclearCell()), new ItemInput(IC2Items.INGOT_URANIUM_ENRICHED_NETHERSTAR), new ItemStack(IC2Items.URANIUM_ROD_NETHER_STAR_SINGLE));
                r.removeFillable(new ItemStack(IC2Items.CELL_EMPTY), new ItemStack(IC2Items.INGOT_URANIUM_ENRICHED_ENDERPEARL));
                r.registerFillable(new ItemStack(CraftingRecipes.getEmptyNuclearCell()), new ItemInput(IC2Items.INGOT_URANIUM_ENRICHED_ENDERPEARL), new ItemStack(IC2Items.URANIUM_ROD_ENDER_PEARL_SINGLE));
            }
            if (IC2CExtrasConfig.EXTRA_NUCLEAR.get()){
                IUraniumRod[] rods = new IUraniumRod[]{Uranium238.INSTANCE, Uranium235.INSTANCE, Uranium233.INSTANCE, MOX.INSTANCE, Plutonium.INSTANCE, Thorium232.INSTANCE};
                for (IUraniumRod rod : rods) {
                    if (!rod.isEnrichedUranium() || !IC2.CONFIG.enableHardEnrichedUranium.get()) {
                        String tag = rod == Uranium238.INSTANCE ? "uranium" : rod.getName().replace("_", "");
                        r.registerFillable(new ItemStack(CraftingRecipes.getEmptyNuclearCell()), new ItemTagInput(IC2CExtrasTags.getForgeItemTag("ingots/" + tag)), rod.createSingleRod());
                    }
                }
            }
        });
    }

    public static void initOreWasherRecipes(ExtendedRecipeList list){
        ItemStack stoneDust = new ItemStack(ModItems.STONE_DUST);
        list.addOreWashingRecipe(id("crushed_iron"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_IRON_ORE), new ItemStack(ModItems.TINY_IRON_DUST, 2), stoneDust}, 1000, IC2CExtrasTags.getForgeItemTag("crushed_ores/iron"));
        list.addOreWashingRecipe(id("crushed_gold"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_GOLD_ORE), new ItemStack(ModItems.TINY_GOLD_DUST, 2), stoneDust}, 1000, IC2CExtrasTags.getForgeItemTag("crushed_ores/gold"));
        list.addOreWashingRecipe(id("crushed_copper"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_COPPER_ORE), new ItemStack(ModItems.TINY_COPPER_DUST, 2), stoneDust}, 1000, IC2CExtrasTags.getForgeItemTag("crushed_ores/copper"));
        list.addOreWashingRecipe(id("crushed_tin"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_TIN_ORE), new ItemStack(ModItems.TINY_IRON_DUST, 2), stoneDust}, 1000, IC2CExtrasTags.getForgeItemTag("crushed_ores/tin"));
        list.addOreWashingRecipe(id("crushed_silver"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_SILVER_ORE), new ItemStack(ModItems.TINY_SILVER_DUST, 2), stoneDust}, 1000, IC2CExtrasTags.getForgeItemTag("crushed_ores/silver"));
        list.addOreWashingRecipe(id("crushed_lead"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_LEAD_ORE), new ItemStack(ModItems.TINY_LEAD_DUST, 2), stoneDust}, 1000, IC2CExtrasTags.getForgeItemTag("crushed_ores/lead"));
        list.addOreWashingRecipe(id("crushed_aluminium"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_ALUMINIUM_ORE), new ItemStack(ModItems.TINY_ALUMINIUM_DUST, 2), stoneDust}, 1000, IC2CExtrasTags.getForgeItemTag("crushed_ores/aluminium"));
        if (IC2CExtrasConfig.EXTRA_NUCLEAR.get()) {
            list.addOreWashingRecipe(id("crushed_uranium"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_URANIUM_ORE), new ItemStack(ModItems.TINY_LEAD_DUST, 2), stoneDust}, 1000, IC2CExtrasTags.getForgeItemTag("crushed_ores/uranium"));
        }
    }

    public static void initThermalCentrifugeRecipes(ExtendedRecipeList list){
        list.addThermalCentrifugingRecipe(id("purified_iron"), new ItemStack[]{new ItemStack(IC2Items.DUST_IRON), new ItemStack(ModItems.TINY_TIN_DUST, 1)}, 400, 250, IC2CExtrasTags.getForgeItemTag("purified_ores/iron"));
        list.addThermalCentrifugingRecipe(id("purified_gold"), new ItemStack[]{new ItemStack(IC2Items.DUST_GOLD), new ItemStack(ModItems.TINY_IRON_DUST, 1)}, 400, 250, IC2CExtrasTags.getForgeItemTag("purified_ores/gold"));
        list.addThermalCentrifugingRecipe(id("purified_copper"), new ItemStack[]{new ItemStack(IC2Items.DUST_COPPER), new ItemStack(ModItems.TINY_GOLD_DUST, 1)}, 400, 250, IC2CExtrasTags.getForgeItemTag("purified_ores/copper"));
        list.addThermalCentrifugingRecipe(id("purified_tin"), new ItemStack[]{new ItemStack(IC2Items.DUST_TIN), new ItemStack(ModItems.TINY_TIN_DUST, 1)}, 400, 250, IC2CExtrasTags.getForgeItemTag("purified_ores/tin"));
        list.addThermalCentrifugingRecipe(id("purified_silver"), new ItemStack[]{new ItemStack(IC2Items.DUST_SILVER), new ItemStack(ModItems.TINY_LEAD_DUST, 1)}, 400, 250, IC2CExtrasTags.getForgeItemTag("purified_ores/silver"));
        list.addThermalCentrifugingRecipe(id("purified_lead"), new ItemStack[]{new ItemStack(ModItems.LEAD_DUST), new ItemStack(ModItems.TINY_SILVER_DUST, 1)}, 400, 250, IC2CExtrasTags.getForgeItemTag("purified_ores/lead"));
        list.addThermalCentrifugingRecipe(id("purified_aluminium"), new ItemStack[]{new ItemStack(IC2Items.DUST_ALUMINIUM), new ItemStack(ModItems.TINY_IRON_DUST, 1)}, 400, 250, IC2CExtrasTags.getForgeItemTag("purified_ores/aluminium"));
        if (IC2CExtrasConfig.EXTRA_NUCLEAR.get()) {
            list.addThermalCentrifugingRecipe(id("purified_uranium"), new ItemStack[]{new ItemStack(ModItems.REFINED_URANIUM_ORE), new ItemStack(ModItems.TINY_THORIUM_DUST, 1)}, 900, 375, IC2CExtrasTags.getForgeItemTag("purified_ores/uranium"));
            list.addThermalCentrifugingRecipe(id("refined_uranium"), new ItemStack[]{new ItemStack(ModItems.URANIUM_DUST, 2), new ItemStack(ModItems.TINY_URANIUM_235_DUST, 1)}, 900, 375, IC2CExtrasTags.getForgeItemTag("refined_ores/uranium"));
            list.addThermalCentrifugingRecipe(id("re_enriched_uranium_cell"), new ItemStack[]{new ItemStack(ModItems.TINY_PLUTONIUM_DUST), new ItemStack(ModItems.TINY_THORIUM_DUST, 2), new ItemStack(CraftingRecipes.getEmptyNuclearCell())}, 1500, 750, IC2Items.URANIUM_ROD_RE_ENRICHED);
            list.addThermalCentrifugingRecipe(id("re_enriched_uranium_238_cell"), new ItemStack[]{new ItemStack(ModItems.TINY_PLUTONIUM_DUST, 2), new ItemStack(CraftingRecipes.getEmptyNuclearCell())}, 1500, 750, ModItems.RE_ENRICHED_URANIUM_238_ROD);
            list.addThermalCentrifugingRecipe(id("re_enriched_thorium_cell"), new ItemStack[]{new ItemStack(ModItems.TINY_URANIUM_233_DUST, 2), new ItemStack(CraftingRecipes.getEmptyNuclearCell())}, 1500, 750, ModItems.RE_ENRICHED_THORIUM_ROD);
        }
    }

    public static void initMaceratorRecipes(IMachineRecipeList list){
        if (IC2CExtrasConfig.DISABLE_NON_RADIATION.get()) return;
        list.addIC2XPRecipe("iron_ore_to_dust", new ItemStack(ModItems.CRUSHED_IRON_ORE, 2), 0.7F, net.minecraftforge.common.Tags.Items.ORES_IRON);
        list.addIC2XPRecipe("gold_ore_to_dust", new ItemStack(ModItems.CRUSHED_GOLD_ORE, 2), 1.0F, net.minecraftforge.common.Tags.Items.ORES_GOLD);
        list.addIC2XPRecipe("copper_ore_to_dust", new ItemStack(ModItems.CRUSHED_COPPER_ORE, 2), 0.3F, IC2Tags.ORE_COPPER);
        list.addIC2XPRecipe("tin_ore_to_dust", new ItemStack(ModItems.CRUSHED_TIN_ORE, 2), 0.4F, IC2Tags.ORE_TIN);
        list.addIC2XPRecipe("silver_ore_to_dust", new ItemStack(ModItems.CRUSHED_SILVER_ORE, 2), 0.8F, IC2Tags.ORE_SILVER);
        list.addXPRecipe(id("lead_ore_to_crushed"), new ItemStack(ModItems.CRUSHED_LEAD_ORE, 2), 0.8f, IC2CExtrasTags.LEAD_ORE);
        list.addIC2XPRecipe("aluminum_ore_nether_to_dust", new ItemStack(ModItems.CRUSHED_ALUMINIUM_ORE, 2), 1.0F, IC2Tags.ORE_ALUMINIUM);
        list.addIC2XPRecipe("raw_iron_to_dust", new ItemStack(ModItems.CRUSHED_IRON_ORE, 2), 0.7F, net.minecraftforge.common.Tags.Items.RAW_MATERIALS_IRON);
        list.addIC2XPRecipe("raw_gold_to_dust", new ItemStack(ModItems.CRUSHED_GOLD_ORE, 2), 1.0F, net.minecraftforge.common.Tags.Items.RAW_MATERIALS_GOLD);
        list.addIC2XPRecipe("raw_copper_to_dust", new ItemStack(ModItems.CRUSHED_COPPER_ORE, 2), 0.3F, net.minecraftforge.common.Tags.Items.RAW_MATERIALS_COPPER);
        list.addIC2XPRecipe("raw_tin_to_dust", new ItemStack(ModItems.CRUSHED_TIN_ORE, 2), 0.4F, IC2Tags.RAW_TIN);
        list.addIC2XPRecipe("raw_silver_to_dust", new ItemStack(ModItems.CRUSHED_SILVER_ORE, 2), 0.8F, IC2Tags.RAW_SILVER);
        list.addXPRecipe(id("raw_lead_to_crushed"), new ItemStack(ModItems.CRUSHED_LEAD_ORE, 2), 0.8F, IC2CExtrasTags.RAW_LEAD);
        list.addIC2XPRecipe("raw_aluminum_to_dust", new ItemStack(ModItems.CRUSHED_ALUMINIUM_ORE, 2), 1.0F, IC2Tags.RAW_ALUMINIUM);
        list.addIC2XPRecipe("raw_iron_block_to_dust", new ItemStack(ModItems.CRUSHED_IRON_ORE, 18), 6.3F, RecipeMods.RECIPE_TIME.create(4.0), net.minecraftforge.common.Tags.Items.STORAGE_BLOCKS_RAW_IRON);
        list.addIC2XPRecipe("raw_gold_block_to_dust", new ItemStack(ModItems.CRUSHED_GOLD_ORE, 18), 9.0F, RecipeMods.RECIPE_TIME.create(4.0), net.minecraftforge.common.Tags.Items.STORAGE_BLOCKS_RAW_GOLD);
        list.addIC2XPRecipe("raw_copper_block_to_dust", new ItemStack(ModItems.CRUSHED_COPPER_ORE, 18), 2.7F, RecipeMods.RECIPE_TIME.create(4.0), net.minecraftforge.common.Tags.Items.STORAGE_BLOCKS_RAW_COPPER);
        list.addIC2XPRecipe("raw_tin_block_to_dust", new ItemStack(ModItems.CRUSHED_TIN_ORE, 18), 3.6F, RecipeMods.RECIPE_TIME.create(4.0), IC2Tags.STORAGE_RAW_TIN);
        list.addIC2XPRecipe("raw_silver_block_to_dust", new ItemStack(ModItems.CRUSHED_SILVER_ORE, 18), 7.2F, RecipeMods.RECIPE_TIME.create(4.0), IC2Tags.STORAGE_RAW_SILVER);
        list.addXPRecipe(id("raw_lead_block_to_crushed"), new ItemStack(ModItems.CRUSHED_LEAD_ORE, 18), 7.2F, RecipeMods.RECIPE_TIME.create(4.0), IC2CExtrasTags.RAW_LEAD_BLOCK);
        list.addIC2XPRecipe("raw_aluminum_block_to_dust", new ItemStack(IC2Items.DUST_ALUMINIUM, 18), 9.0F, RecipeMods.RECIPE_TIME.create(4.0), IC2Tags.STORAGE_RAW_ALUMINIUM);
        if (IC2CExtrasConfig.EXTRA_NUCLEAR.get()){
            list.addXPRecipe(id("uranium_ore_to_crushed"), new ItemStack(ModItems.CRUSHED_URANIUM_ORE, 2), 0.9f, IC2Tags.ORE_URANIUM);
            list.addXPRecipe(id("raw_uranium_to_crushed"), new ItemStack(ModItems.CRUSHED_URANIUM_ORE, 2), 0.9f, IC2Tags.RAW_URANIUM);
            list.addXPRecipe(id("raw_uranium_block_to_crushed"), new ItemStack(ModItems.CRUSHED_URANIUM_ORE, 18), 8.1f, IC2Tags.STORAGE_RAW_URANIUM);
        }
    }
}
