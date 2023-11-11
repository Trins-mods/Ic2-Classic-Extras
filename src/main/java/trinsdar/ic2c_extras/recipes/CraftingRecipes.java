package trinsdar.ic2c_extras.recipes;


import com.google.gson.JsonObject;
import ic2.api.recipes.registries.IAdvancedCraftingManager;
import ic2.core.IC2;
import ic2.core.item.reactor.base.IUraniumRod;
import ic2.core.platform.recipes.mods.IRecipeModifier;
import ic2.core.platform.registries.IC2Blocks;
import ic2.core.platform.registries.IC2Items;
import ic2.core.platform.registries.IC2Tags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.init.Ic2cExtrasTags;
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
        if (Ic2cExtrasConfig.DISABLE_NON_RADIATION.get()) return;
        addDustRecipe(registry, "iron", IC2Items.DUST_IRON, ModItems.TINY_IRON_DUST);
        addDustRecipe(registry, "gold", IC2Items.DUST_GOLD, ModItems.TINY_GOLD_DUST);
        addDustRecipe(registry, "copper", IC2Items.DUST_COPPER, ModItems.TINY_COPPER_DUST);
        addDustRecipe(registry, "tin", IC2Items.DUST_TIN, ModItems.TINY_TIN_DUST);
        addDustRecipe(registry, "aluminium", IC2Items.DUST_ALUMINIUM, ModItems.TINY_ALUMINIUM_DUST);
        addDustRecipe(registry, "silver", IC2Items.DUST_SILVER, ModItems.TINY_SILVER_DUST);
        addDustRecipe(registry, "lead", ModItems.LEAD_DUST, ModItems.TINY_LEAD_DUST);
        addDustRecipe(registry, "uranium233", ModItems.URANIUM_233_DUST, ModItems.TINY_URANIUM_233_DUST);
        addDustRecipe(registry, "uranium235", ModItems.URANIUM_235_DUST, ModItems.TINY_URANIUM_235_DUST);
        addDustRecipe(registry, "uranium", ModItems.URANIUM_238_DUST, ModItems.TINY_URANIUM_238_DUST);
        addDustRecipe(registry, "plutonium", ModItems.PLUTONIUM_DUST, ModItems.TINY_PLUTONIUM_DUST);
        addDustRecipe(registry, "thorium", ModItems.THORIUM_DUST, ModItems.TINY_THORIUM_DUST);

        registry.addShapelessIC2Recipe("uranium__single_2", new ItemStack(IC2Items.URANIUM_ROD_SINGLE), getEmptyNuclearCell(), getUraniumRodIngredientItem());
        registry.addShapelessIC2Recipe("uranium__single_2_tag", new ItemStack(IC2Items.URANIUM_ROD_SINGLE), getEmptyNuclearCell(), getUraniumRodIngredient());
        registry.addShapedIC2Recipe("uranium__near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', getUraniumRodIngredientItem());
        registry.addShapedIC2Recipe("uranium__near_depleted_tag", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', getUraniumRodIngredient());
        if (Ic2cExtrasConfig.EMPTY_NUCLEAR_ROD.get()){
            registry.addShapedRecipe(id("empty_nuclear_rod"), new ItemStack(ModItems.EMPTY_NUCLEAR_ROD, 4), " I ", "I I", " I ", 'I', IC2Tags.INGOT_REFINED_IRON);
            registry.addShapedIC2Recipe("uranium_redstone_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_REDSTONE, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_REDSTONE);
            registry.addShapedIC2Recipe("uranium_blaze_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_BLAZE, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_BLAZE);
            registry.addShapedIC2Recipe("uranium_ender_pearl_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_ENDER_PEARL, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_ENDERPEARL);
            registry.addShapedIC2Recipe("uranium_nether_star_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_NETHER_STAR, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_NETHERSTAR);
            registry.addShapedIC2Recipe("uranium_charcoal_near_depleted", new ItemStack(IC2Items.URANIUM_ROD_NEAR_DEPLETED_CHARCOAL, 8), "CCC", "CUC", "CCC", 'C', getEmptyNuclearCell(), 'U', IC2Items.INGOT_URANIUM_ENRICHED_CHARCOAL);
        }
        if (Ic2cExtrasConfig.EXTRA_NUCLEAR.get()){
            addRodRecipes(registry, MOX.INSTANCE);
            addRodRecipes(registry, Plutonium.INSTANCE);
            addRodRecipes(registry, Thorium232.INSTANCE);
            addRodRecipes(registry, Uranium233.INSTANCE);
            addRodRecipes(registry, Uranium235.INSTANCE);
            addRodRecipes(registry, Uranium238.INSTANCE);
        }
    }

    public static Item getUraniumRodIngredientItem(){
        return Ic2cExtrasConfig.EXTRA_NUCLEAR.get() ? ModItems.ENRICHED_URANIUM_FUEL : IC2Items.INGOT_URANIUM;
    }

    public static Object getUraniumRodIngredient(){
        return Ic2cExtrasConfig.EXTRA_NUCLEAR.get() ? ModItems.ENRICHED_URANIUM_FUEL : IC2Tags.INGOT_URANIUM;
    }

    public static Item getEmptyNuclearCell(){
        return Ic2cExtrasConfig.EMPTY_NUCLEAR_ROD.get() ? ModItems.EMPTY_NUCLEAR_ROD : IC2Items.CELL_EMPTY;
    }

    private static void addDustRecipe(IAdvancedCraftingManager registry, String dust, Item output, Item tiny){
        registry.addShapelessRecipe(id(dust + "_dust"), new ItemStack(output), Ic2cExtrasTags.getForgeItemTag("tiny_dusts/" + dust), Ic2cExtrasTags.getForgeItemTag("tiny_dusts/" + dust)
                , Ic2cExtrasTags.getForgeItemTag("tiny_dusts/" + dust), Ic2cExtrasTags.getForgeItemTag("tiny_dusts/" + dust), Ic2cExtrasTags.getForgeItemTag("tiny_dusts/" + dust)
                , Ic2cExtrasTags.getForgeItemTag("tiny_dusts/" + dust), Ic2cExtrasTags.getForgeItemTag("tiny_dusts/" + dust), Ic2cExtrasTags.getForgeItemTag("tiny_dusts/" + dust)
                , Ic2cExtrasTags.getForgeItemTag("tiny_dusts/" + dust));
        registry.addShapelessRecipe(id(dust + "_tiny_dust"), new ItemStack(tiny, 9), Ic2cExtrasTags.getForgeItemTag("dusts/" + dust));
    }

    private static void addRodRecipes(IAdvancedCraftingManager recipes, IUraniumRod rod){
        recipes.addShapedIC2Recipe("near_depleted_" + rod.getName(), rod.createNearDepletedRod(8), "XXX", "XYX", "XXX", 'Y', rod.getBaseIngot(), 'X', getEmptyNuclearCell());
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
