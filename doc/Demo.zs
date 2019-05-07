import mods.ic2.MetalBender;
import mods.ic2.FluidCanningMachine;

/*
 * Arguments: output, input
 *   - IItemStack output
 *   - IIngredient input
 *   - IItemStack press      - must be an item.
 */
MetalBender.addRecipe(<minecraft:diamond_block>, <minecraft:dirt> * 64, <minecraft:anvil>);

/*
 * Arguments: output, input, inputFluid
 *   - IItemStack output
 *   - IIngredient input
 *   - ILiquidStack inputFluid
 */
FluidCanningMachine.addFillingRecipe(<minecraft:lava_bucket>, <minecraft:bucket>, <liquid:lava>);

/*
 * Arguments: output, outputFluid, input
 *   - IItemStack output
 *   - ILiquidStack outputFluid
 *   - IIngredient input
 */
FluidCanningMachine.addEmptyingRecipe(<minecraft:bucket>, <liquid:lava>, <minecraft:lava_bucket>);

/*
 * Arguments: outputFluid, input, inputFluid
 *   - ILiquidStack outputFluid
 *   - IIngredient input
 *   - ILiquidStack inputFluid
 */
FluidCanningMachine.addEnrichingRecipe(<liquid:lava>, <minecraft:magma>, <liquid:water>);

/*
 * Arguments: output, outputFluid, input, inputFluid
 *   - IItemStack output
 *   - ILiquidStack outputFluid
 *   - IIngredient input
 *   - ILiquidStack inputFluid
 */
FluidCanningMachine.addEnrichingRecipe(<minecraft:ice>, <liquid:lava>, <minecraft:magma>, <liquid:water>);