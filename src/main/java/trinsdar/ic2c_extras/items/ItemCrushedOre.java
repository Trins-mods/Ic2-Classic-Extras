package trinsdar.ic2c_extras.items;

import ic2.core.platform.textures.Ic2Icons;
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
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;
import trinsdar.ic2c_extras.util.Registry;

import java.util.Arrays;
import java.util.List;

public class ItemCrushedOre extends Item implements IStaticTexturedItem {
    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 3);

    int index;

    public ItemCrushedOre(String variant, int index) {
        this.index = index;
        String name = variant + "CrushedOre";
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase());
        setUnlocalizedName(name);
        setCreativeTab(IC2CExtras.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("ic2c_extras_crushed_ore")[index];
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stoneDust = new ItemStack(Registry.stoneDust);
        if (Loader.isModLoaded("gtclassic") && Ic2cExtrasConfig.cauldronWashing) {
            washCrushedOre(player, worldIn, pos, hand, Registry.copperCrushedOre, new ItemStack(Registry.copperPurifiedCrushedOre, 1), new ItemStack(Registry.copperTinyDust, 2), stoneDust);
            washCrushedOre(player, worldIn, pos, hand, Registry.tinCrushedOre, new ItemStack(Registry.tinPurifiedCrushedOre, 1), new ItemStack(Registry.tinTinyDust, 2), stoneDust);
            washCrushedOre(player, worldIn, pos, hand, Registry.ironCrushedOre, new ItemStack(Registry.ironPurifiedCrushedOre, 1), new ItemStack(Registry.ironTinyDust, 2), stoneDust);
            washCrushedOre(player, worldIn, pos, hand, Registry.goldCrushedOre, new ItemStack(Registry.goldPurifiedCrushedOre, 1), new ItemStack(Registry.goldTinyDust, 2), stoneDust);
            washCrushedOre(player, worldIn, pos, hand, Registry.silverCrushedOre, new ItemStack(Registry.silverPurifiedCrushedOre, 1), new ItemStack(Registry.silverTinyDust, 2), stoneDust);
            washCrushedOre(player, worldIn, pos, hand, Registry.leadCrushedOre, new ItemStack(Registry.leadPurifiedCrushedOre, 1), new ItemStack(Registry.leadTinyDust, 2), stoneDust);
        }
        return EnumActionResult.PASS;
    }

    public EnumActionResult washCrushedOre(EntityPlayer player, World world, BlockPos pos, EnumHand hand, Item input, ItemStack... outputs) {
        IBlockState state = world.getBlockState(pos);
        if (this == input) {
            if (state.getBlock() == Blocks.CAULDRON && state.getValue(LEVEL).intValue() > 0) {
                if (!player.capabilities.isCreativeMode) {
                    player.getHeldItem(hand).shrink(1);
                }
                Blocks.CAULDRON.setWaterLevel(world, pos, state, state.getValue(LEVEL).intValue() - 1);
                for (ItemStack stack : outputs) {
                    ItemHandlerHelper.giveItemToPlayer(player, stack);
                }
                world.playSound((EntityPlayer) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F,
                        1.0F);
            }
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }
}
