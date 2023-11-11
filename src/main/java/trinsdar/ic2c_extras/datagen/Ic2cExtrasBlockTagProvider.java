package trinsdar.ic2c_extras.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import trinsdar.ic2c_extras.IC2CExtras;

public class Ic2cExtrasBlockTagProvider extends BlockTagsProvider {
    public Ic2cExtrasBlockTagProvider(DataGenerator arg, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, IC2CExtras.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
    }
}
