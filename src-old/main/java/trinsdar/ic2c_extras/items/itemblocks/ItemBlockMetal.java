package trinsdar.ic2c_extras.items.itemblocks;

import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
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
            return Ic2cExtrasLang.STEEL_BLOCK;
        }
        if (block == Registry.refinedIronBlock) {
            return Ic2cExtrasLang.REFINED_IRON_BLOCK;
        }
        if (block == Registry.leadBlock) {
            return Ic2cExtrasLang.LEAD_BLOCK;
        }
        if (block == Registry.stoneDustBlock) {
            return Ic2cExtrasLang.STONE_DUST_BLOCK;
        }
        return super.getLangComponent(stack);
    }
}
