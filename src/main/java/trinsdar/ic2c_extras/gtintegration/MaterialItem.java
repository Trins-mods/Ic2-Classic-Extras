package trinsdar.ic2c_extras.gtintegration;

import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ILayeredItemModel;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.recipes.RecipeCrushed;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MaterialItem extends Item implements IStaticTexturedItem, GTColorItemInterface, ILayeredItemModel {

    private GTMaterial material;
    private int id;
    private boolean layered;

    public MaterialItem(GTMaterial material, String suffix, int id, boolean layered) {
        this.material = material;
        this.id = id;
        this.layered = layered;
        setRegistryName(this.material.getName() + suffix);
        setUnlocalizedName(IC2CExtras.MODID + "." + this.material.getName() + suffix);
        setCreativeTab(IC2CExtras.creativeTab);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures(IC2CExtras.MODID + "_items")[id];
    }

    @Override
    public Color getColor(ItemStack stack, int index) {
        if (index == 0) {
            return this.material.getColor();
        } else {
            return Color.white;
        }
    }

    @Override
    public boolean isLayered(ItemStack var1) {
        return layered;
    }

    @Override
    public int getLayers(ItemStack var1) {
        return 2;
    }

    @Override
    public TextureAtlasSprite getTexture(int index, ItemStack var2) {
        return Ic2Icons.getTextures(IC2CExtras.MODID + "_items")[id + index];
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
    public EnumActionResult onItemUse(EntityPlayer e, World w, BlockPos p, EnumHand h, EnumFacing facing, float hitX,
                                      float hitY, float hitZ) {

        for (RecipeCrushed.RecipeCauldronEnum recipes : RecipeCrushed.RecipeCauldronEnum.values()) {
            washDust(e, w, p, h, recipes.getInput(), recipes.getOutputs());
        }
        return EnumActionResult.PASS;
    }

    /**
     * Creates the behavior of washing dust if the input material matches
     */
    public EnumActionResult washDust(EntityPlayer player, World world, BlockPos pos, EnumHand hand, GTMaterial input,
                                     ItemStack... outputs) {
        IBlockState state = world.getBlockState(pos);
        PropertyInteger level = PropertyInteger.create("level", 0, 3);
        if (this == MaterialGen.getStack(input, "crushedore", 1).getItem() && layered) {
            if (state.getBlock() == Blocks.CAULDRON && state.getValue(level).intValue() > 0) {
                player.getHeldItem(hand).shrink(1);
                Blocks.CAULDRON.setWaterLevel(world, pos, state, state.getValue(level).intValue() - 1);
                for (ItemStack stack : outputs) {
                    player.dropItem(stack, false);
                }
                world.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F,
                        1.0F);
            }
        }
        return EnumActionResult.SUCCESS;
    }
}
