package trinsdar.ic2c_extras.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.init.IC2CExtrasTags;
import trinsdar.ic2c_extras.init.ModBlocks;

public class IC2CExtrasBlockTagProvider extends BlockTagsProvider {
    public IC2CExtrasBlockTagProvider(DataGenerator arg, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, IC2CExtras.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(IC2CExtrasTags.getForgeBlockTag("ores/lead")).add(ModBlocks.LEAD_ORE, ModBlocks.DEEPSLATE_LEAD_ORE);
        this.tag(IC2CExtrasTags.getForgeBlockTag("ores")).add(ModBlocks.LEAD_ORE, ModBlocks.DEEPSLATE_LEAD_ORE);
        this.tag(IC2CExtrasTags.getForgeBlockTag("storage_blocks/raw_lead")).add(ModBlocks.RAW_LEAD_BLOCK);
        this.tag(IC2CExtrasTags.getForgeBlockTag("storage_blocks/lead")).add(ModBlocks.LEAD_BLOCK);
        this.tag(IC2CExtrasTags.getForgeBlockTag("storage_blocks")).add(ModBlocks.RAW_LEAD_BLOCK, ModBlocks.LEAD_BLOCK);
    }
}
