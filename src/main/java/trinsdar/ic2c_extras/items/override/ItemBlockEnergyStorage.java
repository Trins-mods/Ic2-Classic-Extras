package trinsdar.ic2c_extras.items.override;

import ic2.core.item.block.ItemBlockElectric;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemBlockEnergyStorage extends ItemBlockElectric {
    public ItemBlockEnergyStorage(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.getMetadata() < 3 || stack.getMetadata() == 5){
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
            if (nbt.hasKey("energy")){
                String string = nbt.getInteger("energy") + "/" + this.getMaxCharge(stack.getMetadata()) + " EU";
                tooltip.add(TextFormatting.AQUA + string);
            }
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i < 14; i++){
                ItemStack empty = new ItemStack(this, 1, i);
                if (i < 3 || i == 5){
                    ItemStack full = new ItemStack(this, 1, i);
                    this.charge(empty, 0);
                    this.charge(full, this.getMaxCharge(i));
                    items.add(empty);
                    items.add(full);
                } else if (i != 7 && i != 6){
                    items.add(empty);
                }
            }
        }
    }

    public int getMaxCharge(int meta){
        switch(meta) {
            case 0:
                return 40000;
            case 1:
                return  600000;
            case 2:
                return  10000000;
            case 5:
                return 1000000000;
            default:  return 0;
        }
    }

    public void charge(ItemStack stack, int amount){
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
        nbt.setInteger("energy", amount);
    }
}
