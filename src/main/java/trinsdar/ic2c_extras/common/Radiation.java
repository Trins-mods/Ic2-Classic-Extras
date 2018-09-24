package trinsdar.ic2c_extras.common;

import ic2.api.classic.item.IHazmatSuit;
import ic2.api.info.Info;
import ic2.core.IC2;
import ic2.core.entity.IC2DamageSource;
import ic2.core.entity.IC2Potion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.util.Icons;

import java.util.List;

import static ic2.core.item.armor.standart.ItemHazmatArmor.isFullHazmatSuit;

public class Radiation
{

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event){
        EntityPlayer player = event.player;
        ItemStack stack = new ItemStack(Items.IRON_INGOT);
        if (!player.isCreative())
        {
            if((!isFullHazmatSuit(player)) && player.inventory.hasItemStack(stack))
            {
                player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 90, 0));
            }
        }
    }


//    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
//        boolean server = IC2.platform.isSimulating();
//        if (server) {
//            PotionEffect radiation = player.getActivePotionEffect(IC2Potion.radiation);
//            if (radiation != null && isFullHazmatSuit(player)) {
//                IC2.platform.removePotion(player, IC2Potion.radiation);
//            }
//        }
//    }


}
