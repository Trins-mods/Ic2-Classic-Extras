package trinsdar.ic2c_extras.items.itemblocks;

import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.util.Registry;

public class ItemBlockMetal extends ItemBlockRare {
    public ItemBlockMetal(Block block) {
        super(block);
    }

    public LocaleComp getLangComponent(ItemStack stack) {
        if (this.getBlock() == Registry.steelBlock){
            return new LangComponentHolder.LocaleBlockComp("tile.steelBlock");
        }else if (this.getBlock() == Registry.refinedIronBlock){
            return new LangComponentHolder.LocaleBlockComp("tile.refinedIronBlock");
        }else {
            return new LangComponentHolder.LocaleBlockComp("tile.leadBlock");
        }
    }
}
