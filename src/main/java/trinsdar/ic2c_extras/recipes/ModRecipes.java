package trinsdar.ic2c_extras.recipes;

import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import trinsdar.ic2c_extras.tileentity.TileEntityFluidCanningMachine;

public class ModRecipes {
    public static void init(){
        String dynamics = "thermaldynamics";
        String foundation = "thermalfoundation";
        if (Loader.isModLoaded(foundation)){
            if (Loader.isModLoaded(dynamics)){
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_0", 6, 1)), getModFluid("redstone", 200), getModMetaItem(dynamics, "duct_0", 2, 1), 75);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_0", 7, 1)), getModFluid("redstone", 200), getModMetaItem(dynamics, "duct_0", 3, 1), 75);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_0", 8, 1)), getModFluid("redstone", 200), getModMetaItem(dynamics, "duct_0", 4, 1), 75);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_0", 9, 1)), getModFluid("cryotheum", 500), getModMetaItem(dynamics, "duct_0", 5, 1), 250);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_32", 0, 1)), getModFluid("glowstone", 200), getModMetaItem(dynamics, "duct_32", 2, 1), 75);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_32", 1, 1)), getModFluid("glowstone", 200), getModMetaItem(dynamics, "duct_32", 3, 1), 75);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_32", 4, 1)), getModFluid("glowstone", 200), getModMetaItem(dynamics, "duct_32", 6, 1), 75);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_32", 5, 1)), getModFluid("glowstone", 200), getModMetaItem(dynamics, "duct_32", 7, 1), 75);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_64", 3, 1)), getModFluid("aerotheum", 125), getModMetaItem(dynamics, "duct_64", 0, 1), 75);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(dynamics, "duct_64", 0, 1)), getModFluid("ender"), getModMetaItem(dynamics, "duct_64", 2, 1), 500);
            }
            if (Loader.isModLoaded("openblocks")){
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(new ItemStack(Items.SNOWBALL, 2)), getModFluid("xpjuice", 200), getModMetaItem(foundation, "material", 2049, 1), 1000);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputOreDict("dustSulfur", 2), getModFluid("xpjuice", 200), new ItemStack(Items.BLAZE_POWDER, 1), 1000);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputOreDict("dustObsidian", 2), getModFluid("xpjuice", 200), getModMetaItem(foundation, "material", 2053, 1), 1000);
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputOreDict("dustSaltpeter", 2), getModFluid("xpjuice", 200), getModMetaItem(foundation, "material", 2051, 1), 1000);
            }
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(new ItemStack(Items.SNOWBALL, 2)), getModFluid("experience", 200), getModMetaItem(foundation, "material", 2049, 1), 1000);
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputOreDict("dustSulfur", 2), getModFluid("experience", 200), new ItemStack(Items.BLAZE_POWDER, 1), 1000);
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputOreDict("dustObsidian", 2), getModFluid("experience", 200), getModMetaItem(foundation, "material", 2053, 1), 1000);
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputOreDict("dustSaltpeter", 2), getModFluid("experience", 200), getModMetaItem(foundation, "material", 2051, 1), 1000);
            if (Loader.isModLoaded("immersiveengineering")){
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 816, 1)), getModFluid("plantoil", 160), getModMetaItem(foundation, "material", 817, 1));
                TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 818, 1)), getModFluid("plantoil", 160), getModMetaItem(foundation, "material", 819, 1));
            }
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 816, 1)), getModFluid("seed_oil", 100), getModMetaItem(foundation, "material", 817, 1));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 818, 1)), getModFluid("seed_oil", 100), getModMetaItem(foundation, "material", 819, 1));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "fertilizer", 0, 1)), getModFluid("sap", 200), getModMetaItem(foundation, "fertilizer", 1, 1));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 895, 1)), getModFluid("cryotheum", 200), new ItemStack(Items.ENDER_PEARL, 1));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 894, 1)), getModFluid("cryotheum", 200), new ItemStack(Items.GLOWSTONE_DUST, 1));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 893, 1)), getModFluid("cryotheum", 200), new ItemStack(Items.REDSTONE, 2));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 1025, 1)), getModFluid("ender"), new ItemStack(Items.ENDER_PEARL, 4));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 1025, 1)), getModFluid("redstone"), new ItemStack(Items.REDSTONE, 10));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 1025, 1)), getModFluid("glowstone"), new ItemStack(Items.GLOWSTONE_DUST, 4));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(getModMetaItem(foundation, "material", 1025, 1)), new FluidStack(FluidRegistry.WATER, 1000), new ItemStack(Blocks.ICE));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputOreDict("blockIce", 1), getModFluid("cryotheum", 200), new ItemStack(Blocks.PACKED_ICE));
            TileEntityFluidCanningMachine.addFillingRecipe(new RecipeInputItemStack(new ItemStack(Items.BOWL)), getModFluid("mushroom_stew", 200), new ItemStack(Items.MUSHROOM_STEW, 1));
        }
    }

    public static FluidStack getModFluid(String name){
        return getModFluid(name, 1000);
    }

    public static FluidStack getModFluid(String name, int amount){
        return FluidRegistry.getFluidStack(name, amount);
    }

    /**
     * A getter for retrieving modded items.
     *
     * @param modname - String, the mod name
     * @param itemid  - String, the item by name
     * @return ItemStack - the ItemStack requested
     */
    public static ItemStack getModItem(String modname, String itemid) {
        String pair = modname + ":" + itemid;
        return new ItemStack(Item.getByNameOrId(pair));
    }

    /**
     * A getter for retrieving modded items.
     *
     * @param modname - String, the mod name
     * @param itemid  - String, the item by name
     * @param amount  - int, the count
     * @return ItemStack - the ItemStack requested
     */
    public static ItemStack getModItem(String modname, String itemid, int amount) {
        String pair = modname + ":" + itemid;
        return new ItemStack(Item.getByNameOrId(pair), amount);
    }

    /**
     * A getter for retrieving modded items.
     *
     * @param modname - String, the mod name
     * @param itemid  - String, the item by name
     * @param meta    - int, the meta value of the item
     * @param amount  - int, the count
     * @return ItemStack - the ItemStack requested
     */
    public static ItemStack getModMetaItem(String modname, String itemid, int meta, int size) {
        String pair = modname + ":" + itemid;
        return GameRegistry.makeItemStack(pair, meta, size, null);
    }
}
