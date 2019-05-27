package trinsdar.ic2c_extras.gtintegration;

import gtclassic.color.GTColorItemInterface;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialGen;
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
import net.minecraftforge.items.ItemHandlerHelper;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.recipes.RecipeCrushed;
import trinsdar.ic2c_extras.util.Registry;

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
        return Ic2Icons.getTextures(IC2CExtras.MODID + "_materials")[id];
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
        return Ic2Icons.getTextures(IC2CExtras.MODID + "_materials")[id + index];
    }

    /**
     * Called when a Block is right-clicked with this Item
     */

    static GTMaterialGen GT;
    static GTMaterial M;

    static String purifiedCrushedOre = "purifiedcrushedore";
    static String tinyDust = "tinydust";
    @Override
    public EnumActionResult onItemUse(EntityPlayer e, World w, BlockPos p, EnumHand h, EnumFacing facing, float hitX,
                                      float hitY, float hitZ) {
        washDust(e, w, p, h, GTMaterial.Bismuthtine, MaterialGen.getStack(GTMaterial.Bismuthtine, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Bismuthtine, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Bauxite, MaterialGen.getStack(GTMaterial.Bauxite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Bauxite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Cassiterite, MaterialGen.getStack(GTMaterial.Cassiterite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Cassiterite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Chromite, MaterialGen.getStack(GTMaterial.Chromite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Chromite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Cryolite, MaterialGen.getStack(GTMaterial.Cryolite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Cryolite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Galena, MaterialGen.getStack(GTMaterial.Galena, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Galena, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Garnierite, MaterialGen.getStack(GTMaterial.Garnierite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Garnierite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Iridium, MaterialGen.getStack(GTMaterial.Iridium, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Platinum, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Limonite, MaterialGen.getStack(GTMaterial.Limonite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Limonite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Malachite, MaterialGen.getStack(GTMaterial.Malachite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Malachite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Magnetite, MaterialGen.getStack(GTMaterial.Magnetite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Magnetite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Pyrite, MaterialGen.getStack(GTMaterial.Pyrite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Pyrite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Sheldonite, MaterialGen.getStack(GTMaterial.Sheldonite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Sheldonite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Sphalerite, MaterialGen.getStack(GTMaterial.Sphalerite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Sphalerite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Tantalite, MaterialGen.getStack(GTMaterial.Tantalite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Tantalite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Tetrahedrite, MaterialGen.getStack(GTMaterial.Tetrahedrite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Tetrahedrite, tinyDust, 1) , new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Tungstate, MaterialGen.getStack(GTMaterial.Tungstate, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Tungstate, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Pyrolusite, MaterialGen.getStack(GTMaterial.Pyrolusite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Pyrolusite, tinyDust, 1), new ItemStack(Registry.stoneDust));
        washDust(e, w, p, h, GTMaterial.Molybdenite, MaterialGen.getStack(GTMaterial.Molybdenite, purifiedCrushedOre, 1), MaterialGen.getStack(GTMaterial.Molybdenite, tinyDust, 1), new ItemStack(Registry.stoneDust));
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
                if (!player.capabilities.isCreativeMode){
                    player.getHeldItem(hand).shrink(1);
                }
                Blocks.CAULDRON.setWaterLevel(world, pos, state, state.getValue(level).intValue() - 1);
                for (ItemStack stack : outputs) {
                    ItemHandlerHelper.giveItemToPlayer(player, stack);
                }
                world.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F,
                        1.0F);
            }
        }
        return EnumActionResult.SUCCESS;
    }
}
