package trinsdar.ic2c_extras.items.itemblocks;

import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.player.PlayerHandler;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.Registry;

import java.util.List;

public class ItemBlockGenerator extends ItemBlockRare {
    public ItemBlockGenerator(Block block) {
        super(block);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        PlayerHandler handler = PlayerHandler.getClientPlayerHandler();
        if (handler.hasEUReader()) {
            if (this.getBlock() == Registry.thermoElectricGenerator){
                tooltip.add(Ic2InfoLang.electricProduction.getLocalizedFormatted("1 - 32"));
            }else {
                tooltip.add(Ic2InfoLang.electricProduction.getLocalizedFormatted(160));
            }
        }

    }
}
