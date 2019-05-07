## Fluid Canning Machine

### Class

```java
import mods.ic2.FluidCanningMachine;
```

### Method

```java
/*
 * Arguments: output, input, inputFluid
 *   - IItemStack output
 *   - IIngredient input
 *   - ILiquidStack inputFluid
 */
FluidCanningMachine.addFillingRecipe(<minecraft:lava_bucket>, <minecraft:bucket>, <liquid:lava>);
```

```java
/*
 * Arguments: output, outputFluid, input
 *   - IItemStack output
 *   - ILiquidStack outputFluid
 *   - IIngredient input
 */
FluidCanningMachine.addEmptyingRecipe(<minecraft:bucket>, <liquid:lava>, <minecraft:lava_bucket>);
```

```java
/*
 * Arguments: outputFluid, input, inputFluid
 *   - ILiquidStack outputFluid
 *   - IIngredient input
 *   - ILiquidStack inputFluid
 */
FluidCanningMachine.addEnrichingRecipe(<liquid:lava>, <minecraft:magma>, <liquid:water>);
```

```java
/*
 * Arguments: output, outputFluid, input, inputFluid
 *   - IItemStack output
 *   - ILiquidStack outputFluid
 *   - IIngredient input
 *   - ILiquidStack inputFluid
 */
FluidCanningMachine.addEnrichingRecipe(<minecraft:ice>, <liquid:lava>, <minecraft:magma>, <liquid:water>);
```

To remove an existed recipe, simply disable the corresponding recipe in `config/ic2/ic2machineRecipes.json`.