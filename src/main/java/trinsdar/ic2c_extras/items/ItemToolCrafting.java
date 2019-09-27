package trinsdar.ic2c_extras.items;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.Arrays;
import java.util.List;

public class ItemToolCrafting extends Item implements IStaticTexturedItem {
    int itemIndex;
    boolean enchant;

    public ItemToolCrafting(int maxDamage, String name, int index, boolean enchant) {
        this.maxStackSize = 1;
        this.itemIndex = index;
        this.enchant = enchant;
        this.setMaxDamage(maxDamage);
        this.setNoRepair();
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase());
        setUnlocalizedName(name);
        setCreativeTab(IC2CExtras.creativeTab);
    }

    @Override
    public boolean hasContainerItem(ItemStack itemStack)
    {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack){
        ItemStack copy = itemStack.copy();
        return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return this.enchant;
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("ic2c_extras_tools")[this.itemIndex];
    }
}
