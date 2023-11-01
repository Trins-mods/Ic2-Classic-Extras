package trinsdar.ic2c_extras.util.fluidcell;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.HashMap;

public class CustomModelLoader implements ICustomModelLoader {

    private static HashMap<String, IModel> modelLookup = new HashMap<>();
    @SuppressWarnings("unused")
    private IResourceManager resourceManager;

    public static void register(String registryPath, IModel model) {
        if (!modelLookup.containsKey(registryPath))
            modelLookup.put(registryPath, model);
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    public boolean accepts(ResourceLocation loc) {
        return loc.getNamespace().equals(IC2CExtras.MODID) && modelLookup.containsKey(loc.getPath());
    }

    @Override
    public IModel loadModel(ResourceLocation modelLoc) {
        IModel model = modelLookup.get(modelLoc.getPath());
        return model != null ? model : ModelLoaderRegistry.getMissingModel();
    }
}
