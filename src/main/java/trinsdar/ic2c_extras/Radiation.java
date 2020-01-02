package trinsdar.ic2c_extras;

import ic2.core.IC2;
import ic2.core.entity.IC2Potion;
import ic2.core.item.armor.electric.ItemArmorQuantumSuit;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import trinsdar.ic2c_extras.util.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ic2.core.item.armor.standart.ItemHazmatArmor.isFullHazmatSuit;

public class Radiation {
	public static List<ItemStack> radiation = new ArrayList<>();

	public static void initRadiation() {
		radiation = new ArrayList<>();
		radiation.add(Ic2Items.uraniumDrop);
		radiation.add(Ic2Items.uraniumIngot);
		radiation.add(new ItemStack(Registry.uranium235));
		radiation.add(new ItemStack(Registry.uranium238));
		radiation.add(new ItemStack(Registry.plutoniumDust));
		radiation.add(new ItemStack(Registry.uranium235TinyDust));
		radiation.add(new ItemStack(Registry.uranium238TinyDust));
		radiation.add(new ItemStack(Registry.plutoniumTinyDust));
		radiation.add(new ItemStack(Registry.doubleEnrichedUraniumIngot));
		radiation.add(new ItemStack(Registry.oxidizedUraniumIngot));
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

	public static Item getItem(String name) {
		ResourceLocation loc = new ResourceLocation(name);
		return Item.getByNameOrId(loc.toString());
	}

	public static Block getBlock(String name) {
		ResourceLocation loc = new ResourceLocation(name);
		return Block.getBlockFromName(loc.toString());
	}

	public boolean hasFullQuantumSuit(EntityPlayer player) {
		for (int i = 0; i < 4; i++) {
			if (!(player.inventory.armorInventory.get(i).getItem() instanceof ItemArmorQuantumSuit)) {
				return false;
			}
		}
		return true;
	}
	int ticker = 0;
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = event.player;

		if (IC2.platform.isSimulating() && event.phase == TickEvent.Phase.END){
			if (Ic2cExtrasConfig.itemRadiation){
				if (!player.isCreative()) {
					if (!isFullHazmatSuit(player) && !hasFullQuantumSuit(player)) {
						if (hasRadiationItem(player)) {
							player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0, false, false));
						}
					}
				}
			}
			if (player.getUniqueID().equals(new UUID(0x1964e3d1650040e7L, 0x9ff2e6161d41a8c2L))){
				int count = 0;
				for (int i = 0; i < player.inventory.mainInventory.size(); i++){
					if (player.inventory.getStackInSlot(i).getCount() > 0){
						count += 1;
					}
				}
				int emptySlots = player.inventory.mainInventory.size() - count;
				if (ticker == 0){
					switch (emptySlots){
						case 3: IC2.platform.messagePlayer(player, "Bear, your inventory starts to get full."); ticker = 2400; break;
						case 2: IC2.platform.messagePlayer(player, "You should clean up your Inventory, Bear!"); ticker = 2400; break;
						case 1: IC2.platform.messagePlayer(player, "Your Inventory is almost full, Bear!!"); ticker = 2400; break;
						case 0: IC2.platform.messagePlayer(player, "You are full of shit, Bear!!!"); ticker = 2400; break;
						default: break;
					}
				}
				if (ticker > 0){
					if (emptySlots < 6){
						ticker -= 1;
					}else {
						ticker = 0;
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
}
