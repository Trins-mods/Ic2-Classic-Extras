package trinsdar.ic2c_extras.common;

import ic2.api.classic.item.IHazmatSuit;
import ic2.api.info.Info;
import ic2.core.IC2;
import ic2.core.entity.IC2DamageSource;
import ic2.core.entity.IC2Potion;
import ic2.core.item.armor.electric.ItemArmorQuantumSuit;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.items.RegistryItem;
import trinsdar.ic2c_extras.common.util.Icons;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ic2.core.item.armor.standart.ItemHazmatArmor.isFullHazmatSuit;

public class Radiation {
	private static ArrayList<ItemStack> radiation = null;

	private void initRadiation() {
		if (radiation == null) {
			radiation = new ArrayList<>();
			radiation.add(Ic2Items.uraniumDrop);
			radiation.add(Ic2Items.uraniumIngot);
			radiation.add(new ItemStack(RegistryItem.itemMiscs, 1, 4));
			radiation.add(new ItemStack(RegistryItem.itemMiscs, 1, 5));
			radiation.add(new ItemStack(RegistryItem.itemMiscs, 1, 6));
			radiation.add(new ItemStack(RegistryItem.tinyDustTypes, 1, 5));
			radiation.add(new ItemStack(RegistryItem.tinyDustTypes, 1, 9));
			radiation.add(new ItemStack(RegistryItem.tinyDustTypes, 1, 10));
			radiation.add(Ic2Items.redstoneUraniumIngot);
			radiation.add(Ic2Items.blazeUraniumIngot);
			radiation.add(Ic2Items.enderPearlUraniumIngot);
			radiation.add(Ic2Items.netherStarUraniumIngot);
			radiation.add(Ic2Items.charcoalUraniumIngot);
			radiation.add(Ic2Items.reactorUraniumRodSingle);
			radiation.add(Ic2Items.reactorUraniumRodDual);
			radiation.add(Ic2Items.reactorUraniumRodQuad);
			radiation.add(Ic2Items.reactorRedstoneUraniumRodSingle);
			radiation.add(Ic2Items.reactorRedstoneUraniumRodDual);
			radiation.add(Ic2Items.reactorRedstoneUraniumRodQuad);
			radiation.add(Ic2Items.reactorBlazeUraniumRodSingle);
			radiation.add(Ic2Items.reactorBlazeUraniumRodDual);
			radiation.add(Ic2Items.reactorBlazeUraniumRodQuad);
			radiation.add(Ic2Items.reactorEnderPearlUraniumRodSingle);
			radiation.add(Ic2Items.reactorEnderPearlUraniumRodDual);
			radiation.add(Ic2Items.reactorEnderPearlUraniumRodQuad);
			radiation.add(Ic2Items.reactorNetherStarUraniumRodSingle);
			radiation.add(Ic2Items.reactorNetherStarUraniumRodDual);
			radiation.add(Ic2Items.reactorNetherStarUraniumRodQuad);
			radiation.add(Ic2Items.reactorCharcoalUraniumRodSingle);
			radiation.add(Ic2Items.reactorCharcoalUraniumRodDual);
			radiation.add(Ic2Items.reactorCharcoalUraniumRodQuad);
			radiation.add(Ic2Items.reactorUraniumIsotopicRod);
			radiation.add(Ic2Items.reactorRedstoneUraniumIsotopicRod);
			radiation.add(Ic2Items.reactorBlazeUraniumIsotopicRod);
			radiation.add(Ic2Items.reactorEnderPearlUraniumIsotopicRod);
			radiation.add(Ic2Items.reactorNetherStarUraniumIsotopicRod);
			radiation.add(Ic2Items.reactorCharcoalUraniumIsotopicRod);
			radiation.add(Ic2Items.reactorNearDepletedUraniumRod);
			radiation.add(Ic2Items.reactorNearDepletedRedstoneUraniumRod);
			radiation.add(Ic2Items.reactorNearDepletedBlazeUraniumRod);
			radiation.add(Ic2Items.reactorNearDepletedEnderPearlUraniumRod);
			radiation.add(Ic2Items.reactorNearDepletedNetherStarUraniumRod);
			radiation.add(Ic2Items.reactorNearDepletedCharcoalUraniumRod);
			radiation.add(Ic2Items.reactorReEnrichedUraniumRod);
			radiation.add(Ic2Items.reactorReEnrichedRedstoneUraniumRod);
			radiation.add(Ic2Items.reactorReEnrichedBlazeUraniumRod);
			radiation.add(Ic2Items.reactorReEnrichedEnderPearlUraniumRod);
			radiation.add(Ic2Items.reactorReEnrichedNetherStarUraniumRod);
			radiation.add(Ic2Items.reactorReEnrichedCharcoalUraniumRod);
		}
	}

	public boolean hasFullQuantumSuit(EntityPlayer player) {
		for (int i = 0; i < 4; i++) {
			if (!(player.inventory.armorInventory.get(i).getItem() instanceof ItemArmorQuantumSuit)) {
				return false;
			}
		}
		return true;
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		initRadiation();

		if (event.phase == TickEvent.Phase.END) {
			if (!player.isCreative()) {
				if (!isFullHazmatSuit(player) && !hasFullQuantumSuit(player)) {
					if (hasRadiationItem(player)) {
						player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
					}
				}
			}
		}
	}

	private boolean hasRadiationItem(EntityPlayer player) {
		for (ItemStack item : radiation) {
			if (player.inventory.hasItemStack(item))
				return true;
		}
		return false;
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
