package trinsdar.ic2c_extras.event;

import ic2.core.IC2;
import ic2.core.item.wearable.armor.electric.QuantumSuit;
import ic2.core.platform.registries.IC2Potions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import trinsdar.ic2c_extras.IC2CExtrasConfig;
import trinsdar.ic2c_extras.init.IC2CExtrasTags;

import static ic2.core.item.wearable.armor.HazmatArmor.isFullHazmatSuit;

public class PlayerEvents {

    public boolean hasFullQuantumSuit(Player player) {
        for (int i = 0; i < 4; i++) {
            if (!(player.getInventory().getArmor(i).getItem() instanceof QuantumSuit)) {
                return false;
            }
        }
        return true;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        var player = event.player;
        if (!IC2CExtrasConfig.ITEM_RADIATION.get()) return;

        if (IC2.PLATFORM.isSimulating() && event.phase == TickEvent.Phase.END) {
            if (!player.isCreative()) {
                if (!isFullHazmatSuit(player) && !hasFullQuantumSuit(player)) {
                    if (player.getInventory().hasAnyMatching(i -> i.is(IC2CExtrasTags.RADIOACTIVE))) {
                        player.addEffect(new MobEffectInstance(IC2Potions.RADIATION, 1800, 0, false, false));
                    }
                }
            }
        }
    }
}
