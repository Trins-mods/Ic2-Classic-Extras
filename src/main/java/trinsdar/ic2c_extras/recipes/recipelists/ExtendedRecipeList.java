package trinsdar.ic2c_extras.recipes.recipelists;

import ic2.api.recipes.ingridients.generators.ItemWithNBTGenerator;
import ic2.api.recipes.ingridients.recipes.SimpleRecipeOutput;
import ic2.api.recipes.misc.RecipeMods;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.core.block.machines.recipes.MachineRecipeList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ExtendedRecipeList extends MachineRecipeList {
    public ExtendedRecipeList(String folder, Consumer<ExtendedRecipeList> reloader) {
        super(folder, r -> reloader.accept((ExtendedRecipeList) r));
    }

    public void addOreWashingRecipe(ResourceLocation id, ItemStack[] output, int water, Object... inputs) {
        this.addRecipe(id, new SimpleRecipeOutput(Arrays.stream(output).map(s -> new ItemWithNBTGenerator(s, s.getCount())).collect(Collectors.toList()), createNeededWater(water)), inputs);
    }

    public void addThermalCentrifugingRecipe(ResourceLocation id, ItemStack[] output, int heat, int timeAdd, Object... inputs) {
        this.addRecipe(id, new SimpleRecipeOutput(Arrays.stream(output).map(s -> new ItemWithNBTGenerator(s, s.getCount())).collect(Collectors.toList()), createNeededHeat(heat, timeAdd)), inputs);
    }

    public void registerExtendedListener(Consumer<ExtendedRecipeList> listener) {
        this.registerListener(r -> listener.accept((ExtendedRecipeList) r));
    }

    protected CompoundTag createNeededWater(int amount) {
        if (amount <= 0) {
            return null;
        }
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("amount", amount);
        return nbt;
    }

    protected CompoundTag createNeededHeat(int amount, int timeAdd) {
        if (amount <= 0) {
            return null;
        }
        CompoundTag nbt = RecipeMods.RECIPE_TIME.create(timeAdd);
        nbt.putInt("heat", amount);
        return nbt;
    }
}
