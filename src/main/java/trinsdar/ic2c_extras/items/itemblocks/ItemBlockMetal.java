package trinsdar.ic2c_extras.items.itemblocks;

import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.blocks.BlockMetal;
import trinsdar.ic2c_extras.util.Registry;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

public class ItemBlockMetal extends ItemBlockRare {
    public ItemBlockMetal(BlockMetal block) {
        super(block);
        this.name = block.getName();
    }
}
