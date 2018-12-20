package trinsdar.ic2c_extras.util;

import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.core.IC2;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2States;
import ic2.core.util.helpers.BlockStateContainerIC2;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import trinsdar.ic2c_extras.tileentity.TileEntityOreWashingPlant;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;

import java.util.Arrays;

import static ic2.api.classic.recipe.ClassicRecipes.macerator;

public class Ic2cExtrasRecipes {
    public static boolean enableHarderUranium;
    public static boolean enableCasingsRequirePlates;
    public static boolean enableCuttingToolWires;
    public static boolean enableHVCablesRequireSteel;
    public static IMachineRecipeList rolling = new BasicMachineRecipeList("rolling");
    public static IMachineRecipeList extruding = new BasicMachineRecipeList("extruding");
    public static IMachineRecipeList cutting = new BasicMachineRecipeList("cutting");

    public static void init(){
        initShapedRecipes();
        initReplaceRecipes();
        initFurnaceRecipes();
        initReplaceMaceratorRecipes();
        initMachineRecipes();
        initHarderUraniumProcessing();
    }

    static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
    public static void initShapedRecipes(){
        recipes.addRecipe(new ItemStack(RegistryBlock.advancedSteamTurbine, 1),
                " S ", "STS", " S ", 'S', Ic2Items.basicTurbine,'T', Ic2Items.transformerMV);

    }

    public static void initReplaceRecipes(){
    }

    public static void initFurnaceRecipes(){
        GameRegistry.addSmelting(RegistryItem.ironCrushedOre, new ItemStack(Items.IRON_INGOT), 0.7F);
        GameRegistry.addSmelting(RegistryItem.goldCrushedOre, new ItemStack(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(RegistryItem.copperCrushedOre, StackUtil.copyWithSize(Ic2Items.copperIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.tinCrushedOre, StackUtil.copyWithSize(Ic2Items.tinIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.silverCrushedOre, StackUtil.copyWithSize(Ic2Items.silverIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.leadCrushedOre, new ItemStack(RegistryItem.leadIngot), 0.5F);
        GameRegistry.addSmelting(RegistryItem.ironPurifiedCrushedOre, new ItemStack(Items.IRON_INGOT), 0.7F);
        GameRegistry.addSmelting(RegistryItem.goldPurifiedCrushedOre, new ItemStack(Items.GOLD_INGOT), 1.0F);
        GameRegistry.addSmelting(RegistryItem.copperPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.copperIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.tinPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.tinIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.silverPurifiedCrushedOre, StackUtil.copyWithSize(Ic2Items.silverIngot, 1), 0.5F);
        GameRegistry.addSmelting(RegistryItem.leadPurifiedCrushedOre, new ItemStack(RegistryItem.leadIngot), 0.5F);
        GameRegistry.addSmelting(RegistryItem.leadDust, new ItemStack(RegistryItem.leadIngot), 0.5F);
    }

    public static void initReplaceMaceratorRecipes(){
        macerator.removeRecipe(new RecipeInputOreDict("oreIron"));
        macerator.addRecipe(new RecipeInputOreDict("oreIron"), new ItemStack(RegistryItem.ironCrushedOre,2), 0.7F, "ironOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreGold"));
        macerator.addRecipe(new RecipeInputOreDict("oreGold"), new ItemStack(RegistryItem.goldCrushedOre,2), 1.0F, "goldOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreCopper"));
        macerator.addRecipe(new RecipeInputOreDict("oreCopper"), new ItemStack(RegistryItem.copperCrushedOre,2), 0.3F, "copperOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreTin"));
        macerator.addRecipe(new RecipeInputOreDict("oreTin"), new ItemStack(RegistryItem.tinCrushedOre,2), 0.4F, "tinOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreSilver"));
        macerator.addRecipe(new RecipeInputOreDict("oreSilver"), new ItemStack(RegistryItem.silverCrushedOre,2), 0.8F, "silverOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreLead"));
        macerator.addRecipe(new RecipeInputOreDict("oreLead"), new ItemStack(RegistryItem.leadCrushedOre,2), 0.8F, "leadOre");
        macerator.removeRecipe(new RecipeInputOreDict("oreUranium"));
        macerator.addRecipe(new RecipeInputOreDict("oreUranium"), new ItemStack(RegistryItem.uraniumCrushedOre,2), 1.0F, "uraniumOre");
    }

    public static void initMachineRecipes(){
        //ore washing plant
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedIron", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.ironPurifiedCrushedOre, 1), new ItemStack(RegistryItem.ironTinyDust, 2), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedGold", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.goldPurifiedCrushedOre, 1), new ItemStack(RegistryItem.goldTinyDust, 2), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.copperPurifiedCrushedOre, 1), new ItemStack(RegistryItem.copperTinyDust, 2), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedTin", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.tinPurifiedCrushedOre, 1), new ItemStack(RegistryItem.tinTinyDust, 2, 3), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.silverPurifiedCrushedOre, 1), new ItemStack(RegistryItem.silverTinyDust, 2), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedLead", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.leadPurifiedCrushedOre, 1), new ItemStack(RegistryItem.leadTinyDust, 3), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityOreWashingPlant.addRecipe((new RecipeInputItemStack(new ItemStack(Blocks.GRAVEL, 1))), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.stoneDust, 1))));

        //thermal centrifuge recipes
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedIron", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.ironDust), new ItemStack(RegistryItem.goldTinyDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedGold", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.goldDust), new ItemStack(RegistryItem.silverTinyDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedCopper", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.copperDust), new ItemStack(RegistryItem.tinTinyDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedTin", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.tinDust), new ItemStack(RegistryItem.ironTinyDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedSilver", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.silverDust), new ItemStack(RegistryItem.leadTinyDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedLead", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.leadDust, 1), new ItemStack(RegistryItem.copperTinyDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedIron", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.ironDust), new ItemStack(RegistryItem.goldTinyDust, 1), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedGold", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.goldDust), new ItemStack(RegistryItem.silverTinyDust, 1), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedCopper", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.copperDust), new ItemStack(RegistryItem.tinTinyDust, 1), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedTin", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.tinDust), new ItemStack(RegistryItem.ironTinyDust, 1), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedSilver", 1)), new MachineOutput(null, Arrays.asList((Ic2Items.silverDust), new ItemStack(RegistryItem.stoneDust, 1))));
        TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedLead", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.leadDust, 1), new ItemStack(RegistryItem.stoneDust, 1))));

        rolling.addRecipe((new RecipeInputOreDict("ingotCopper", 1)),  new ItemStack(RegistryItem.copperCasing, 2), 0.7f, "copperItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotTin", 1)),  new ItemStack(RegistryItem.tinCasing, 2), 0.7f, "tinItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotSilver", 1)),  new ItemStack(RegistryItem.silverCasing, 2), 0.7f, "silverItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotLead", 1)),  new ItemStack(RegistryItem.leadCasing, 2), 0.7f, "leadItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotIron", 1)),  new ItemStack(RegistryItem.ironCasing, 2), 0.7f, "ironItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotGold", 1)),  new ItemStack(RegistryItem.goldCasing, 2), 0.7f, "goldItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotRefinedIron", 1)),  new ItemStack(RegistryItem.refinedIronCasing, 2), 0.7f, "refinedIronItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotSteel", 1)),  new ItemStack(RegistryItem.steelCasing, 2), 0.7f, "steelItemCasingRolling");
        rolling.addRecipe((new RecipeInputOreDict("ingotBronze", 1)),  new ItemStack(RegistryItem.bronzeCasing, 2), 0.7f, "bronzeItemCasingRolling");

        extruding.addRecipe((new RecipeInputOreDict("ingotCopper", 1)),  StackUtil.copyWithSize(Ic2Items.copperCable, 3), 0.7f, "copperCableExtruding");
        extruding.addRecipe((new RecipeInputOreDict("ingotTin", 1)),  StackUtil.copyWithSize(Ic2Items.tinCable, 4), 0.7f, "tinCableExtruding");
        extruding.addRecipe((new RecipeInputOreDict("ingotBronze", 1)),  StackUtil.copyWithSize(Ic2Items.bronzeCable, 3), 0.7f, "bronzeCableExtruding");
        extruding.addRecipe((new RecipeInputOreDict("ingotRefinedIron", 1)),  StackUtil.copyWithSize(Ic2Items.ironCable, 6), 0.7f, "HVCableExtruding");
        extruding.addRecipe((new RecipeInputOreDict("ingotGold", 1)),  StackUtil.copyWithSize(Ic2Items.goldCable, 6), 0.7f, "goldCableExtruding");
        extruding.addRecipe((new RecipeInputItemStack(new ItemStack(RegistryItem.tinCasing, 1))),  StackUtil.copyWithSize(Ic2Items.tinCan, 1), 0.7f, "tinCanExtruding");
    }
    public static void initHarderUraniumProcessing(){
        if (enableHarderUranium){
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputItemStack(Ic2Items.reactorReEnrichedUraniumRod)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.plutonium), StackUtil.copyWithSize(Ic2Items.uraniumDrop, 4))));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedPurifiedUranium", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.uranium238, 6), new ItemStack(RegistryItem.uranium235TinyDust, 1))));
            TileEntityThermalCentrifuge.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.uranium238, 4), new ItemStack(RegistryItem.uranium235TinyDust, 1), new ItemStack(RegistryItem.stoneDust, 1))));
            TileEntityOreWashingPlant.addRecipe((new RecipeInputOreDict("crushedUranium", 1)), new MachineOutput(null, Arrays.asList(new ItemStack(RegistryItem.uraniumPurifiedCrushedOre, 1), new ItemStack(RegistryItem.uranium235TinyDust, 1), new ItemStack(RegistryItem.stoneDust, 1))));
            recipes.addRecipe((Ic2Items.uraniumDrop),
                    "UUU", "TTT", "UUU", 'U', RegistryItem.uranium238,'T', RegistryItem.uranium235TinyDust);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onHarvestDropsEvent(BlockEvent.HarvestDropsEvent event) {
        Block block = event.getState().getBlock();
        if (block == Ic2States.uraniumOre.getBlock()){
            if (enableHarderUranium){
                event.getDrops().clear();
                event.getDrops().add(Ic2Items.uraniumOre);
            }
        }
    }

    public static void setConfig(boolean uranium, boolean casings, boolean wires, boolean hvCable){
        enableHarderUranium = uranium;
        enableCasingsRequirePlates = casings;
        enableCuttingToolWires = wires;
        enableHVCablesRequireSteel = hvCable;
    }
}
