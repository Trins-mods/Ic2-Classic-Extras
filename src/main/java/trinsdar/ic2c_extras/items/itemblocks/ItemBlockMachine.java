package trinsdar.ic2c_extras.items.itemblocks;

import ic2.core.item.block.ItemBlockRare;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.player.PlayerHandler;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.Registry;

import java.util.List;

public class ItemBlockMachine extends ItemBlockRare {
    public ItemBlockMachine(Block block) {
        super(block);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        PlayerHandler handler = PlayerHandler.getClientPlayerHandler();
        if (this.getBlock() == Registry.autocraftingTable){
            tooltip.add(TextFormatting.RED + "Work in Progress");
        }
        if (handler.hasEUReader()) {
            if (this.getBlock() != Registry.reinforcedEncasedCable){
                tooltip.add(Ic2InfoLang.euReaderSinkInfo.getLocalizedFormatted(getMaxInput()));
                if (this.getBlock() == Registry.autocraftingTable){
                    tooltip.add("Energy Usage: 50 EU Per Craft");
                }
            } else {
                tooltip.add((Ic2InfoLang.euReaderCableLimit.getLocalizedFormatted(2048)));
                tooltip.add(Ic2InfoLang.euReaderCableLoss.getLocalizedFormatted(0.8D));
            }
        }
    }

    public int getMaxInput() {
        if (this.getBlock() == Registry.thermalCentrifuge || this.getBlock() == Registry.impellerizedRoller || this.getBlock() == Registry.liquescentExtruder || this.getBlock() == Registry.plasmaCutter || this.getBlock() == Registry.thermalWasher || this.getBlock() == Registry.metalBender || this.getBlock() == Registry.treeTapper || this.getBlock() == Registry.electricHeatGenerator) {
            return 128;
        } else if (this.getBlock() == Registry.electricDisenchanter){
            return 512;
        } else {
            return 32;
        }
    }
}
