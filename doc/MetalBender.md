## Metal Bender

### Class

```java
import mods.ic2.MetalBender;
```

### Method

```java
/*
 * Arguments: output, input
 *   - IItemStack output
 *   - IIngredient input
 *   - IItemStack press      - must be an item.
 */
MetalBender.addRecipe(<minecraft:diamond_block>, <minecraft:dirt> * 64, <minecraft:anvil>);
```

To remove an existed recipe, simply disable the corresponding recipe in `config/ic2/ic2machineRecipes.json`.