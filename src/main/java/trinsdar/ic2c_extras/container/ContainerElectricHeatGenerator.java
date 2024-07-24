package trinsdar.ic2c_extras.container;

import ic2.core.block.machines.containers.lv.BasicMachineContainer;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.filter.IFilter;
import ic2.core.inventory.gui.IC2Screen;
import ic2.core.inventory.gui.components.simple.ChargebarComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.utils.math.geometry.Box2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.blockentity.BlockEntityElectricHeatGenerator;
import trinsdar.ic2c_extras.container.component.HeatGeneratorComponent;
import trinsdar.ic2c_extras.init.ModItems;

public class ContainerElectricHeatGenerator extends ContainerComponent<BlockEntityElectricHeatGenerator> {
    private static final Box2i CHARGE_BOX = new Box2i(9, 43, 14, 15);
    public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IC2CExtras.MODID, "textures/gui/electric_heat_generator.png");
    public ContainerElectricHeatGenerator(BlockEntityElectricHeatGenerator key, Player player, int id) {
        super(key, player, id);
        this.addSlot(FilterSlot.createDischargeSlot(key, key.tier, 0, 8, 62));
        IFilter filter = t -> t.getItem() == ModItems.COIL;
        for (int i = 0; i < 5; i++) {
            this.addSlot(new FilterSlot(key, i + 1, 44 + (18 * i), 27, filter));
            this.addSlot(new FilterSlot(key, i + 6, 44 + (18 * i), 45, filter));
        }
        this.addPlayerInventory(player.getInventory());
        this.addComponent(new HeatGeneratorComponent(new Box2i(34, 66, 108, 13), key.getHeatHandler()));
        this.addComponent(new ChargebarComponent(CHARGE_BOX, key, BasicMachineContainer.CHARGE_POS, true));
    }

    @Override
    public ResourceLocation getTexture() {
        return GUI_TEXTURE;
    }

    @Override
    public void onGuiLoaded(IC2Screen screen) {
        super.onGuiLoaded(screen);
        screen.clearFlag(IC2Screen.SHOW_PLAYER_INVENTORY_NAME);
    }
}
