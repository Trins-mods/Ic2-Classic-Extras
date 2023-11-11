package trinsdar.ic2c_extras.recipes;

import ic2.api.recipes.misc.RecipeMods;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.IC2;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.registries.IC2Tags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.init.Ic2cExtrasTags;
import trinsdar.ic2c_extras.init.ModItems;
import trinsdar.ic2c_extras.recipes.recipelists.ExtendedRecipeList;

import static trinsdar.ic2c_extras.recipes.CraftingRecipes.id;

public class MachineRecipes {
    public static final ExtendedRecipeList ORE_WASHER = new ExtendedRecipeList("ore_washer", MachineRecipes::initOreWasherRecipes);
    public static final ExtendedRecipeList THERMAL_CENTRIFUGE = new ExtendedRecipeList("thermal_centrifuge", MachineRecipes::initThermalCentrifugeRecipes);

    public static void init(){
        IC2.RECIPES.get(true).getLists().add(ORE_WASHER);
        IC2.RECIPES.get(true).getLists().add(THERMAL_CENTRIFUGE);
        if (IC2.PLATFORM.isRendering()){
            IC2.RECIPES.get(false).getLists().add(ORE_WASHER);
            IC2.RECIPES.get(false).getLists().add(THERMAL_CENTRIFUGE);
        }
        IC2.RECIPES.get(true).macerator.registerListener(MachineRecipes::initMaceratorRecipes);
        IC2.RECIPES.get(true).extractor.registerListener(r -> {
            if (Ic2cExtrasConfig.EXTRA_NUCLEAR.get()){
                r.removeRecipe(new ResourceLocation("ic2", "uranium_extraction"));
            }
        });
        IC2.RECIPES.get(true).compressor.registerListener(r -> {
            if (Ic2cExtrasConfig.EXTRA_NUCLEAR.get()){
                r.removeRecipe(new ResourceLocation("ic2", "uranium_ingot"));
            }
            ORE_WASHER.reload();
            THERMAL_CENTRIFUGE.reload();
        });
    }

    public static void initOreWasherRecipes(ExtendedRecipeList list){
        ItemStack stoneDust = new ItemStack(ModItems.STONE_DUST);
        list.addOreWashingRecipe(id("crushed_iron"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_IRON_ORE), new ItemStack(ModItems.TINY_IRON_DUST, 2), stoneDust}, 1000, Ic2cExtrasTags.getForgeItemTag("crushed_ores/iron"));
        list.addOreWashingRecipe(id("crushed_gold"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_GOLD_ORE), new ItemStack(ModItems.TINY_GOLD_DUST, 2), stoneDust}, 1000, Ic2cExtrasTags.getForgeItemTag("crushed_ores/gold"));
        list.addOreWashingRecipe(id("crushed_copper"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_COPPER_ORE), new ItemStack(ModItems.TINY_COPPER_DUST, 2), stoneDust}, 1000, Ic2cExtrasTags.getForgeItemTag("crushed_ores/copper"));
        list.addOreWashingRecipe(id("crushed_tin"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_TIN_ORE), new ItemStack(ModItems.TINY_IRON_DUST, 2), stoneDust}, 1000, Ic2cExtrasTags.getForgeItemTag("crushed_ores/tin"));
        list.addOreWashingRecipe(id("crushed_silver"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_SILVER_ORE), new ItemStack(ModItems.TINY_SILVER_DUST, 2), stoneDust}, 1000, Ic2cExtrasTags.getForgeItemTag("crushed_ores/silver"));
        list.addOreWashingRecipe(id("crushed_lead"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_LEAD_ORE), new ItemStack(ModItems.TINY_LEAD_DUST, 2), stoneDust}, 1000, Ic2cExtrasTags.getForgeItemTag("crushed_ores/lead"));
        list.addOreWashingRecipe(id("crushed_aluminium"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_ALUMINIUM_ORE), new ItemStack(ModItems.TINY_ALUMINIUM_DUST, 2), stoneDust}, 1000, Ic2cExtrasTags.getForgeItemTag("crushed_ores/aluminium"));
        if (Ic2cExtrasConfig.EXTRA_NUCLEAR.get()) {
            list.addOreWashingRecipe(id("crushed_uranium"), new ItemStack[]{new ItemStack(ModItems.PURIFIED_URANIUM_ORE), new ItemStack(ModItems.TINY_LEAD_DUST, 2), stoneDust}, 1000, Ic2cExtrasTags.getForgeItemTag("crushed_ores/uranium"));
        }
    }

    public static void initThermalCentrifugeRecipes(ExtendedRecipeList list){
        list.addThermalCentrifugingRecipe(id("purified_iron"), new ItemStack[]{new ItemStack(IC2Items.DUST_IRON), new ItemStack(ModItems.TINY_TIN_DUST, 1)}, 400, 250, Ic2cExtrasTags.getForgeItemTag("purified_ores/iron"));
        list.addThermalCentrifugingRecipe(id("purified_gold"), new ItemStack[]{new ItemStack(IC2Items.DUST_GOLD), new ItemStack(ModItems.TINY_IRON_DUST, 1)}, 400, 250, Ic2cExtrasTags.getForgeItemTag("purified_ores/gold"));
        list.addThermalCentrifugingRecipe(id("purified_copper"), new ItemStack[]{new ItemStack(IC2Items.DUST_COPPER), new ItemStack(ModItems.TINY_GOLD_DUST, 1)}, 400, 250, Ic2cExtrasTags.getForgeItemTag("purified_ores/copper"));
        list.addThermalCentrifugingRecipe(id("purified_tin"), new ItemStack[]{new ItemStack(IC2Items.DUST_TIN), new ItemStack(ModItems.TINY_TIN_DUST, 1)}, 400, 250, Ic2cExtrasTags.getForgeItemTag("purified_ores/tin"));
        list.addThermalCentrifugingRecipe(id("purified_silver"), new ItemStack[]{new ItemStack(IC2Items.DUST_SILVER), new ItemStack(ModItems.TINY_LEAD_DUST, 1)}, 400, 250, Ic2cExtrasTags.getForgeItemTag("purified_ores/silver"));
        list.addThermalCentrifugingRecipe(id("purified_lead"), new ItemStack[]{new ItemStack(ModItems.LEAD_DUST), new ItemStack(ModItems.TINY_SILVER_DUST, 1)}, 400, 250, Ic2cExtrasTags.getForgeItemTag("purified_ores/lead"));
        list.addThermalCentrifugingRecipe(id("purified_aluminium"), new ItemStack[]{new ItemStack(IC2Items.DUST_ALUMINIUM), new ItemStack(ModItems.TINY_IRON_DUST, 1)}, 400, 250, Ic2cExtrasTags.getForgeItemTag("purified_ores/aluminium"));
        if (Ic2cExtrasConfig.EXTRA_NUCLEAR.get()) {
            list.addThermalCentrifugingRecipe(id("purified_uranium"), new ItemStack[]{new ItemStack(ModItems.REFINED_URANIUM_ORE), new ItemStack(ModItems.TINY_THORIUM_DUST, 1)}, 900, 375, Ic2cExtrasTags.getForgeItemTag("purified_ores/uranium"));
            list.addThermalCentrifugingRecipe(id("refined_uranium"), new ItemStack[]{new ItemStack(ModItems.URANIUM_238_DUST, 2), new ItemStack(ModItems.TINY_URANIUM_235_DUST, 1)}, 900, 375, Ic2cExtrasTags.getForgeItemTag("refined_ores/uranium"));
            list.addThermalCentrifugingRecipe(id("re_enriched_uranium_cell"), new ItemStack[]{new ItemStack(ModItems.TINY_PLUTONIUM_DUST), new ItemStack(ModItems.TINY_THORIUM_DUST, 2), new ItemStack(CraftingRecipes.getEmptyNuclearCell())}, 1500, 750, IC2Items.URANIUM_ROD_RE_ENRICHED);
            list.addThermalCentrifugingRecipe(id("re_enriched_uranium_cell"), new ItemStack[]{new ItemStack(ModItems.TINY_PLUTONIUM_DUST, 2), new ItemStack(CraftingRecipes.getEmptyNuclearCell())}, 1500, 750, ModItems.RE_ENRICHED_URANIUM_238_ROD);
            list.addThermalCentrifugingRecipe(id("re_enriched_uranium_cell"), new ItemStack[]{new ItemStack(ModItems.TINY_URANIUM_233_DUST, 2), new ItemStack(CraftingRecipes.getEmptyNuclearCell())}, 1500, 750, ModItems.RE_ENRICHED_THORIUM_ROD);
        }
    }

    public static void initMaceratorRecipes(IMachineRecipeList list){
        if (Ic2cExtrasConfig.DISABLE_NON_RADIATION.get()) return;
        list.addIC2XPRecipe("iron_ore_to_dust", new ItemStack(ModItems.CRUSHED_IRON_ORE, 2), 0.7F, net.minecraftforge.common.Tags.Items.ORES_IRON);
        list.addIC2XPRecipe("gold_ore_to_dust", new ItemStack(ModItems.CRUSHED_GOLD_ORE, 2), 1.0F, net.minecraftforge.common.Tags.Items.ORES_GOLD);
        list.addIC2XPRecipe("copper_ore_to_dust", new ItemStack(ModItems.CRUSHED_COPPER_ORE, 2), 0.3F, IC2Tags.ORE_COPPER);
        list.addIC2XPRecipe("tin_ore_to_dust", new ItemStack(ModItems.CRUSHED_TIN_ORE, 2), 0.4F, IC2Tags.ORE_TIN);
        list.addIC2XPRecipe("silver_ore_to_dust", new ItemStack(ModItems.CRUSHED_SILVER_ORE, 2), 0.8F, IC2Tags.ORE_SILVER);
        list.addXPRecipe(id("lead_ore_to_crushed"), new ItemStack(ModItems.CRUSHED_LEAD_ORE, 2), 0.8f, Ic2cExtrasTags.LEAD_ORE);
        list.addIC2XPRecipe("aluminum_ore_nether_to_dust", new ItemStack(ModItems.CRUSHED_ALUMINIUM_ORE, 2), 1.0F, IC2Tags.ORE_ALUMINIUM);
        list.addIC2XPRecipe("raw_iron_to_dust", new ItemStack(ModItems.CRUSHED_IRON_ORE, 2), 0.7F, net.minecraftforge.common.Tags.Items.RAW_MATERIALS_IRON);
        list.addIC2XPRecipe("raw_gold_to_dust", new ItemStack(ModItems.CRUSHED_GOLD_ORE, 2), 1.0F, net.minecraftforge.common.Tags.Items.RAW_MATERIALS_GOLD);
        list.addIC2XPRecipe("raw_copper_to_dust", new ItemStack(ModItems.CRUSHED_COPPER_ORE, 2), 0.3F, net.minecraftforge.common.Tags.Items.RAW_MATERIALS_COPPER);
        list.addIC2XPRecipe("raw_tin_to_dust", new ItemStack(ModItems.CRUSHED_TIN_ORE, 2), 0.4F, IC2Tags.RAW_TIN);
        list.addIC2XPRecipe("raw_silver_to_dust", new ItemStack(ModItems.CRUSHED_SILVER_ORE, 2), 0.8F, IC2Tags.RAW_SILVER);
        list.addXPRecipe(id("raw_lead_to_crushed"), new ItemStack(ModItems.CRUSHED_LEAD_ORE, 2), 0.8F, Ic2cExtrasTags.RAW_LEAD);
        list.addIC2XPRecipe("raw_aluminum_to_dust", new ItemStack(ModItems.CRUSHED_ALUMINIUM_ORE, 2), 1.0F, IC2Tags.RAW_ALUMINIUM);
        list.addIC2XPRecipe("raw_iron_block_to_dust", new ItemStack(ModItems.CRUSHED_IRON_ORE, 18), 6.3F, RecipeMods.RECIPE_TIME.create(4.0), net.minecraftforge.common.Tags.Items.STORAGE_BLOCKS_RAW_IRON);
        list.addIC2XPRecipe("raw_gold_block_to_dust", new ItemStack(ModItems.CRUSHED_GOLD_ORE, 18), 9.0F, RecipeMods.RECIPE_TIME.create(4.0), net.minecraftforge.common.Tags.Items.STORAGE_BLOCKS_RAW_GOLD);
        list.addIC2XPRecipe("raw_copper_block_to_dust", new ItemStack(ModItems.CRUSHED_COPPER_ORE, 18), 2.7F, RecipeMods.RECIPE_TIME.create(4.0), net.minecraftforge.common.Tags.Items.STORAGE_BLOCKS_RAW_COPPER);
        list.addIC2XPRecipe("raw_tin_block_to_dust", new ItemStack(ModItems.CRUSHED_TIN_ORE, 18), 3.6F, RecipeMods.RECIPE_TIME.create(4.0), IC2Tags.STORAGE_RAW_TIN);
        list.addIC2XPRecipe("raw_silver_block_to_dust", new ItemStack(ModItems.CRUSHED_SILVER_ORE, 18), 7.2F, RecipeMods.RECIPE_TIME.create(4.0), IC2Tags.STORAGE_RAW_SILVER);
        list.addXPRecipe(id("raw_lead_block_to_crushed"), new ItemStack(ModItems.CRUSHED_LEAD_ORE, 18), 7.2F, RecipeMods.RECIPE_TIME.create(4.0), Ic2cExtrasTags.RAW_LEAD_BLOCK);
        list.addIC2XPRecipe("raw_aluminum_block_to_dust", new ItemStack(IC2Items.DUST_ALUMINIUM, 18), 9.0F, RecipeMods.RECIPE_TIME.create(4.0), IC2Tags.STORAGE_RAW_ALUMINIUM);
        if (Ic2cExtrasConfig.EXTRA_NUCLEAR.get()){
            list.addXPRecipe(id("uranium_ore_to_crushed"), new ItemStack(ModItems.CRUSHED_URANIUM_ORE, 2), 0.9f, IC2Tags.ORE_URANIUM);
            list.addXPRecipe(id("raw_uranium_to_crushed"), new ItemStack(ModItems.CRUSHED_URANIUM_ORE, 2), 0.9f, IC2Tags.RAW_URANIUM);
            list.addXPRecipe(id("raw_uranium_block_to_crushed"), new ItemStack(ModItems.CRUSHED_URANIUM_ORE, 18), 8.1f, IC2Tags.STORAGE_RAW_URANIUM);
        }
    }
}
