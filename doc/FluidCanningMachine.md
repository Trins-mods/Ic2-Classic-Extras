## Fluid Canning Machine

### Class

```java
import mods.ic2.FluidCanningMachine;
```

### Method

```java
/*
 * Arguments: output, input, inputFluid, totalEu(Optional)
 *   - IItemStack output
 *   - IIngredient input
 *   - ILiquidStack inputFluid
 *   - int totalEu, Optional
 */
FluidCanningMachine.addFillingRecipe(<minecraft:lava_bucket>, <minecraft:bucket>, <liquid:lava>, 50);
```

```java
/*
 * Arguments: output, outputFluid, input, totalEu(Optional)
 *   - IItemStack output
 *   - ILiquidStack outputFluid
 *   - IIngredient input
 *   - int totalEu, Optional
 */
FluidCanningMachine.addEmptyingRecipe(<minecraft:bucket>, <liquid:lava>, <minecraft:lava_bucket>, 50);
```

```java
/*
 * Arguments: outputFluid, input, inputFluid, totalEu(Optional)
 *   - ILiquidStack outputFluid
 *   - IIngredient input
 *   - ILiquidStack inputFluid
 *   - int totalEu, Optional
 */
FluidCanningMachine.addEnrichingRecipe(<liquid:lava>, <minecraft:magma>, <liquid:water>, 400);
```

```java
/*
 * Arguments: output, outputFluid, input, inputFluid, totalEu(Optional)
 *   - IItemStack output
 *   - ILiquidStack outputFluid
 *   - IIngredient input
 *   - ILiquidStack inputFluid
 *   - int totalEu, Optional
 */
FluidCanningMachine.addEnrichingRecipe(<minecraft:ice>, <liquid:lava>, <minecraft:magma>, <liquid:water>, 400);
```

To remove an existed recipe, simply disable the corresponding recipe in `config/ic2/ic2machineRecipes.json`.