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
    public ItemBlockMetal(Block block) {
        super(block);
    }

    @Override
    public LocaleComp getLangComponent(ItemStack stack) {
        Block block = this.getBlock();
        if (block == Registry.steelBlock) {
            return Ic2cExtrasLang.steelBlock;
        }
        if (block == Registry.refinedIronBlock) {
            return Ic2cExtrasLang.refinedIronBlock;
        }
        if (block == Registry.leadBlock) {
            return Ic2cExtrasLang.leadBlock;
        }
        if (block == Registry.stoneDustBlock) {
            return Ic2cExtrasLang.stoneDustBlock;
        }
        return super.getLangComponent(stack);
    }
}
