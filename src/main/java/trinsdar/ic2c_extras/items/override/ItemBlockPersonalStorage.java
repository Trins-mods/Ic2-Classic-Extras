package trinsdar.ic2c_extras.items.override;

import ic2.core.item.block.ItemBlockPersonal;
import ic2.core.platform.registry.Ic2Items;
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

public class ItemBlockPersonalStorage extends ItemBlockPersonal {
    public ItemBlockPersonalStorage(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.getMetadata() == 5){
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
            if (nbt.hasKey("energy") && nbt.hasKey("MaxEnergy")){
                String string = nbt.getInteger("energy") + "/" + nbt.getInteger("MaxEnergy") + " EU";
                tooltip.add(TextFormatting.AQUA + string);
            }
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            int[] validMetas = {0, 1, 2, 3, 4, 5, 8};
            for (int meta : validMetas){
                if (meta == 5) {
                    ItemStack[] stacks = {Ic2Items.personalEnergyStorageBatBox.copy(), Ic2Items.personalEnergyStorageMFE.copy(), Ic2Items.personalEnergyStorageMFSU.copy()};
                    for (ItemStack empty : stacks){
                        ItemStack full = empty.copy();
                        charge(empty, false);
                        charge(full, true);
                        items.add(empty);
                        items.add(full);
                    }
                } else {
                    items.add(new ItemStack(this, 1, meta));
                }
            }
        }
    }

    public void charge(ItemStack stack, boolean max){
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
        int amount = max ? nbt.getInteger("MaxEnergy") : 0;
        nbt.setInteger("energy", amount);
    }
}
