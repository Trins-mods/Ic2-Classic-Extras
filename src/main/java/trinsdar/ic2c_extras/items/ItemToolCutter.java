package trinsdar.ic2c_extras.items;

import ic2.api.classic.audio.AudioPosition;
import ic2.api.classic.audio.PositionSpec;
import ic2.api.classic.energy.tile.IInsulationModifieableConductor;
import ic2.api.classic.item.ICutterItem;
import ic2.core.IC2;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemToolCutter extends ItemToolCrafting implements ICutterItem {
    public ItemToolCutter(int maxDamage, String name, int index, boolean enchant) {
        super(maxDamage, name, index, enchant);
    }

    @Override
    public void cutInsulationFrom(ItemStack stack, World world, BlockPos pos, EntityPlayer player) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof IInsulationModifieableConductor) {
            IInsulationModifieableConductor cable = (IInsulationModifieableConductor)tile;
            if (cable.tryRemoveInsulation()) {
                if (IC2.platform.isSimulating()) {
                    StackUtil.dropAsEntity(world, pos, Ic2Items.rubber.copy());
                    stack.damageItem(3, player);
                } else {
                    IC2.audioManager.playOnce(new AudioPosition(world, pos), PositionSpec.Center, Ic2Sounds.cutterUse, true, IC2.audioManager.getDefaultVolume());
                }
            }
        }

    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return Ic2Items.cutter.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
