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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ic2.core.item.armor.standart.ItemHazmatArmor.isFullHazmatSuit;

public class Radiation
{
    public boolean hasFullQuantumSuit(EntityPlayer player)
    {
        for(int i = 0;i<4;i++)
        {
            if(!(player.inventory.armorInventory.get(i).getItem() instanceof ItemArmorQuantumSuit))
            {
                return false;
            }
        }
        return true;
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event){
        EntityPlayer player = event.player;

//        Set<ItemStack> radiation = new HashSet<>();
//        radiation.add(new ItemStack(RegistryItem.itemMiscs, 1, 4));
//        radiation.add(new ItemStack(RegistryItem.itemMiscs, 1, 5));
//        radiation.add(new ItemStack(RegistryItem.itemMiscs, 1, 5));
//        radiation.add(new ItemStack(RegistryItem.tinyDustTypes, 1, 5));
//        radiation.add(new ItemStack(RegistryItem.tinyDustTypes, 1, 9));
//        radiation.add(new ItemStack(RegistryItem.tinyDustTypes, 1, 10));
//        radiation.add(Ic2Items.uraniumOre);
//        Ingredient ingredient = Ingredient.fromStacks(new ItemStack(RegistryItem.itemMiscs, 1, 4), new ItemStack(RegistryItem.itemMiscs, 1, 5));
//        ItemStack stack = (Ic2Items.uraniumDrop);
//        ItemStack stack2 = (Ic2Items.uraniumIngot);
//        ItemStack stack3 = (new ItemStack(RegistryItem.itemMiscs, 1, 4));
//        ItemStack stack4 = (new ItemStack(RegistryItem.itemMiscs, 1, 5));
//        ItemStack stack5 = (new ItemStack(RegistryItem.itemMiscs, 1, 6));
//        ItemStack stack6 = (new ItemStack(RegistryItem.tinyDustTypes, 1, 5));
//        ItemStack stack7 = (new ItemStack(RegistryItem.tinyDustTypes, 1, 9));
//        ItemStack stack8 = (new ItemStack(RegistryItem.tinyDustTypes, 1, 10));
//        ItemStack stack9 = (Ic2Items.redstoneUraniumIngot);
//        ItemStack stack10 = (Ic2Items.blazeUraniumIngot);
//        ItemStack stack11 = (Ic2Items.enderPearlUraniumIngot);
//        ItemStack stack12 = (Ic2Items.netherStarUraniumIngot);
//        ItemStack stack13 = (Ic2Items.reactorUraniumRodSingle);
//        ItemStack stack14 = (Ic2Items.reactorUraniumRodDual);
//        ItemStack stack15 = (Ic2Items.reactorUraniumRodQuad);
//        ItemStack stack16 = (Ic2Items.reactorRedstoneUraniumRodSingle);
//        ItemStack stack17 = (Ic2Items.reactorRedstoneUraniumRodDual);
//        ItemStack stack18 = (Ic2Items.reactorRedstoneUraniumRodQuad);
//        ItemStack stack19 = (Ic2Items.reactorBlazeUraniumRodSingle);
//        ItemStack stack20 = (Ic2Items.reactorBlazeUraniumRodDual);
//        ItemStack stack21 = (Ic2Items.reactorBlazeUraniumRodQuad);
//        ItemStack stack22 = (Ic2Items.reactorEnderPearlUraniumRodSingle);
//        ItemStack stack23 = (Ic2Items.reactorEnderPearlUraniumRodDual);
//        ItemStack stack24 = (Ic2Items.reactorEnderPearlUraniumRodQuad);
//        ItemStack stack25 = (Ic2Items.reactorNetherStarUraniumRodSingle);
//        ItemStack stack26 = (Ic2Items.reactorNetherStarUraniumRodDual);
//        ItemStack stack27 = (Ic2Items.reactorNetherStarUraniumRodQuad);
//        ItemStack stack28 = (Ic2Items.reactorCharcoalUraniumRodSingle);
//        ItemStack stack29 = (Ic2Items.reactorCharcoalUraniumRodDual);
//        ItemStack stack30 = (Ic2Items.reactorCharcoalUraniumRodQuad);
//        ItemStack stack31 = (Ic2Items.reactorUraniumIsotopicRod);
//        ItemStack stack32 = (Ic2Items.reactorRedstoneUraniumIsotopicRod);
//        ItemStack stack33 = (Ic2Items.reactorBlazeUraniumIsotopicRod);
//        ItemStack stack34 = (Ic2Items.reactorEnderPearlUraniumIsotopicRod);
//        ItemStack stack35 = (Ic2Items.reactorNetherStarUraniumIsotopicRod);
//        ItemStack stack36 = (Ic2Items.reactorCharcoalUraniumIsotopicRod);
//        ItemStack stack37 = (Ic2Items.reactorNearDepletedUraniumRod);
//        ItemStack stack38 = (Ic2Items.reactorNearDepletedRedstoneUraniumRod);
//        ItemStack stack39 = (Ic2Items.reactorNearDepletedBlazeUraniumRod);
//        ItemStack stack40 = (Ic2Items.reactorNearDepletedEnderPearlUraniumRod);
//        ItemStack stack41 = (Ic2Items.reactorNearDepletedNetherStarUraniumRod);
//        ItemStack stack42 = (Ic2Items.reactorNearDepletedCharcoalUraniumRod);
//        ItemStack stack43 = (Ic2Items.reactorReEnrichedUraniumRod);
//        ItemStack stack44 = (Ic2Items.reactorReEnrichedRedstoneUraniumRod);
//        ItemStack stack45 = (Ic2Items.reactorReEnrichedBlazeUraniumRod);
//        ItemStack stack46 = (Ic2Items.reactorReEnrichedEnderPearlUraniumRod);
//        ItemStack stack47 = (Ic2Items.reactorReEnrichedNetherStarUraniumRod);
//        ItemStack stack48 = (Ic2Items.reactorReEnrichedCharcoalUraniumRod);
//        boolean match = ingredient.apply(stack);
        if (event.phase == TickEvent.Phase.END) {
            if (!player.isCreative()) {
                if (!isFullHazmatSuit(player) && !hasFullQuantumSuit(player)) {
                    if (player.inventory.hasItemStack(Ic2Items.uraniumDrop)){
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.uraniumIngot)){
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(new ItemStack(RegistryItem.itemMiscs, 1, 4))){
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(new ItemStack(RegistryItem.itemMiscs, 1, 5))){
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(new ItemStack(RegistryItem.itemMiscs, 1, 6))){
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(new ItemStack(RegistryItem.tinyDustTypes, 1, 5))){
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(new ItemStack(RegistryItem.tinyDustTypes, 1, 9))){
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(new ItemStack(RegistryItem.tinyDustTypes, 1, 10))){
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.redstoneUraniumIngot)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.blazeUraniumIngot)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.enderPearlUraniumIngot)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.netherStarUraniumIngot)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.charcoalUraniumIngot)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorUraniumRodSingle)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorUraniumRodDual)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorUraniumRodQuad)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorRedstoneUraniumRodSingle)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorRedstoneUraniumRodDual)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorRedstoneUraniumRodQuad)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorBlazeUraniumRodSingle)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorBlazeUraniumRodDual)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorBlazeUraniumRodQuad)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorEnderPearlUraniumRodSingle)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorEnderPearlUraniumRodDual)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorEnderPearlUraniumRodQuad)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNetherStarUraniumRodSingle)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNetherStarUraniumRodDual)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNetherStarUraniumRodQuad)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorCharcoalUraniumRodSingle)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorCharcoalUraniumRodDual)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorCharcoalUraniumRodQuad)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorUraniumIsotopicRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorRedstoneUraniumIsotopicRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorBlazeUraniumIsotopicRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorEnderPearlUraniumIsotopicRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNetherStarUraniumIsotopicRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorCharcoalUraniumIsotopicRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNearDepletedUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNearDepletedRedstoneUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNearDepletedBlazeUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNearDepletedEnderPearlUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNearDepletedNetherStarUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorNearDepletedCharcoalUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorReEnrichedUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorReEnrichedRedstoneUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorReEnrichedBlazeUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorReEnrichedEnderPearlUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorReEnrichedNetherStarUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }else if (player.inventory.hasItemStack(Ic2Items.reactorReEnrichedCharcoalUraniumRod)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0));
                    }
                }
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
