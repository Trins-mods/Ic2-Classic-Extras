package trinsdar.ic2c_extras.events;

import ic2.core.IC2;
import ic2.core.entity.IC2Potion;
import ic2.core.item.armor.electric.ItemArmorQuantumSuit;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import trinsdar.ic2c_extras.items.ItemNuclearRod;
import trinsdar.ic2c_extras.util.IReactorPlated;
import trinsdar.ic2c_extras.util.Registry;

import java.util.ArrayList;
import java.util.List;

import static ic2.core.item.armor.standart.ItemHazmatArmor.isFullHazmatSuit;

public class RadiationEvent {
    public static List<ItemStack> radiation = new ArrayList<>();

    public static void initRadiation() {
        radiation.add(Ic2Items.uraniumDrop);
        radiation.add(Ic2Items.uraniumIngot);
        radiation.add(new ItemStack(Registry.uranium235));
        radiation.add(new ItemStack(Registry.uranium235Ingot));
        radiation.add(new ItemStack(Registry.plutoniumDust));
        radiation.add(new ItemStack(Registry.plutoniumIngot));
        radiation.add(new ItemStack(Registry.plutoniumRTG));
        radiation.add(new ItemStack(Registry.uranium235TinyDust));
        radiation.add(new ItemStack(Registry.uranium235SmallDust));
        radiation.add(new ItemStack(Registry.plutoniumSmallDust));
        radiation.add(new ItemStack(Registry.plutoniumTinyDust));
        radiation.add(new ItemStack(Registry.doubleEnrichedUraniumIngot));
        radiation.add(new ItemStack(Registry.uranium233Ingot));
        radiation.add(new ItemStack(Registry.uranium233Dust));
        radiation.add(new ItemStack(Registry.uranium233TinyDust));
        radiation.add(new ItemStack(Registry.thorium230Dust));
        radiation.add(new ItemStack(Registry.thorium230TinyDust));
        radiation.add(new ItemStack(Registry.thorium230Ingot));
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
        for (ItemNuclearRod.NuclearRodVariants variants : ItemNuclearRod.NuclearRodVariants.values()){
            if (variants != ItemNuclearRod.NuclearRodVariants.THORIUM232){
                for (IUranium.RodType type : IUranium.RodType.values()){
                    radiation.add(ItemNuclearRod.getUran(variants).getRodType(type));
                }
            }
        }
    }

    public static Item getItem(String modid, String name) {
        ResourceLocation loc = new ResourceLocation(modid, name);
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

    public boolean hasFullNuclearcraftSuit(EntityPlayer player){
        String modid = "nuclearcraft";
        if (!Loader.isModLoaded(modid)){
            return false;
        }
        for (int i = 0; i < 4; i++) {
            ItemStack stack = player.inventory.armorInventory.get(i);
            Item item = stack.getItem();
            if (stack.isEmpty() || (item != getItem(modid, "helm_hazmat") && item != getItem(modid, "chest_hazmat") && item != getItem(modid, "legs_hazmat") && item != getItem(modid, "boots_hazmat"))) {
                return false;
            }
        }
        return true;
    }

    public boolean hasFullPlatedSuit(EntityPlayer player) {
        for (int i = 0; i < 4; i++) {
            ItemStack stack = player.inventory.armorInventory.get(i);
            Item item = stack.getItem();
            if (!(item instanceof IReactorPlated) || !((IReactorPlated)item).hasReactorPlate(stack)) {
                return false;
            }
        }
        return true;
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event) {
        EntityPlayer player = event.player;

        if (IC2.platform.isSimulating() && event.phase == TickEvent.Phase.END) {
            if (!player.isCreative()) {
                if (!isFullHazmatSuit(player) && !hasFullQuantumSuit(player) && !hasFullPlatedSuit(player) && !hasFullNuclearcraftSuit(player)) {
                    if (hasRadiationItem(player)) {
                        player.addPotionEffect(new PotionEffect(IC2Potion.radiation, 1800, 0, false, false));
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
