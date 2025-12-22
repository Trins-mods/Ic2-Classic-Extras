package trinsdar.ic2c_extras.recipes.recipelists;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ic2.api.recipes.ingridients.inputs.FluidInput;
import ic2.api.recipes.ingridients.inputs.IInput;
import ic2.api.recipes.ingridients.recipes.IFluidRecipeOutput;
import ic2.api.recipes.ingridients.recipes.IRecipeOutput;
import ic2.api.recipes.ingridients.recipes.SimpleFluidOutput;
import ic2.api.recipes.registries.IListenableRegistry;
import ic2.api.recipes.registries.IMachineRecipeList;
import ic2.api.recipes.registries.IMachineRecipeList.RecipeEntry;
import ic2.api.recipes.registries.IRefiningRecipeList;
import ic2.core.block.machines.recipes.IRecipeList;
import ic2.core.platform.recipes.misc.IngredientRegistry;
import ic2.core.utils.collection.CollectionUtils;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FermenterRecipeList implements IListenableRegistry<FermenterRecipeList>, IRecipeList {
    Map<ResourceLocation, RecipeEntry> recipes = CollectionUtils.createLinkedMap();
    Map<Fluid, IMachineRecipeList.RecipeEntry> recipeMap;
    boolean mapped;
    List<Consumer<FermenterRecipeList>> listeners = ObjectLists.synchronize(CollectionUtils.createList());
    public FermenterRecipeList(Consumer<FermenterRecipeList> listener) {
        listeners.add(listener);
        this.recipeMap = CollectionUtils.createLinkedMap();
        this.mapped = false;
    }

    @Override
    public void registerListener(Consumer<FermenterRecipeList> consumer) {
        if (consumer != null) listeners.add(consumer);
    }

    public IMachineRecipeList.RecipeEntry removeRecipe(ResourceLocation location) {
        return this.recipes.remove(location);
    }

    private void addRecipe(RecipeEntry recipeEntry) {
        IInput[] inputs = recipeEntry.getInputs();
        if (recipeEntry.getOutput() instanceof IFluidRecipeOutput && inputs != null && inputs.length == 1 && inputs[0] instanceof FluidInput){
            this.recipes.put(recipeEntry.getLocation(), recipeEntry);
        } else {
            throw new RuntimeException("Recipe Is Invalid: " + recipeEntry.getLocation());
        }
    }

    public void addRecipe(ResourceLocation id, FluidStack fluidInput, FluidStack fluidOutput, int totalProgress){
        CompoundTag data = new CompoundTag();
        data.putInt("totalProgress", totalProgress);
        this.addRecipe(new RecipeEntry(id, new SimpleFluidOutput(Collections.emptyList(), List.of(fluidOutput), data), new FluidInput(fluidInput)));
    }



    @Override
    public void writeRecipes(FriendlyByteBuf buffer) {
        buffer.writeVarInt(recipes.size());
        for(IMachineRecipeList.RecipeEntry entry : this.recipes.values()) {
            buffer.writeResourceLocation(entry.getLocation());
            IngredientRegistry.INSTANCE.writeRecipeOutput(entry.getOutput(), buffer);
            buffer.writeByte(entry.getInputs().length);

            for(IInput input : entry.getInputs()) {
                IngredientRegistry.INSTANCE.writeInput(input, buffer);
            }
        }

    }

    @Override
    public void readRecipes(FriendlyByteBuf buffer) {
        Map<ResourceLocation, IMachineRecipeList.RecipeEntry> recipes = CollectionUtils.createLinkedMap();
        int size = buffer.readVarInt();

        for(int i = 0; i < size; ++i) {
            ResourceLocation location = buffer.readResourceLocation();
            IRecipeOutput output = IngredientRegistry.INSTANCE.createOutput(buffer);
            IInput[] inputs = new IInput[buffer.readByte()];

            for(int j = 0; j < inputs.length; ++j) {
                inputs[j] = IngredientRegistry.INSTANCE.readInput(buffer);
            }

            recipes.put(location, new IMachineRecipeList.RecipeEntry(location, output, inputs));
        }

        this.mapped = false;
        this.recipes = recipes;
        this.recipeMap = CollectionUtils.createLinkedMap();
    }

    public Map<ResourceLocation, JsonObject> writeRecipes() {
        Map<ResourceLocation, JsonObject> result = CollectionUtils.createLinkedMap();

        for(IMachineRecipeList.RecipeEntry entry : this.recipes.values()) {
            JsonObject obj = new JsonObject();
            if (entry.getInputs().length <= 1) {
                obj.add("input", IngredientRegistry.INSTANCE.serializeInput(entry.getInputs()[0]));
            } else {
                JsonArray inputs = new JsonArray();

                for(IInput input : entry.getInputs()) {
                    inputs.add(IngredientRegistry.INSTANCE.serializeInput(input));
                }

                obj.add("input", inputs);
            }

            obj.add("output", IngredientRegistry.INSTANCE.serializeOutput(entry.getOutput()));
            result.put(entry.getLocation(), obj);
        }

        return result;
    }

    public void readRecipe(ResourceLocation id, JsonObject data) {
        if (IRecipeList.isRemover(data)) {
            IRecipeList.getEntriesToRemove(data, this::removeRecipe);
        } else {
            List<IInput> inputs = CollectionUtils.createList();
            IRecipeList.iterateObject(data.get("input"), (T) -> inputs.add(IngredientRegistry.INSTANCE.readInput(T)));
            if (inputs.isEmpty()) {
                throw new RuntimeException("Inputs are empty");
            } else {
                IRecipeOutput out = IngredientRegistry.INSTANCE.readOutput(GsonHelper.getAsJsonObject(data, "output"));
                if (out == null) {
                    throw new RuntimeException("Output is Empty");
                } else {
                    this.addRecipe(new IMachineRecipeList.RecipeEntry(id, out, inputs.toArray(IInput[]::new)));
                }
            }
        }
    }
}
