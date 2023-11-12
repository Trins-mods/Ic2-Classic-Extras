package trinsdar.ic2c_extras.container;


import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.gui.components.simple.ChargebarComponent;
import ic2.core.inventory.gui.components.simple.ProgressComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.inventory.slot.UpgradeSlot;
import ic2.core.inventory.slot.XPSlot;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.blockentity.TileEntityThermalCentrifuge;
import trinsdar.ic2c_extras.container.component.HeatComponent;

public class ContainerThermalCentrifuge extends ContainerComponent<TileEntityThermalCentrifuge> {
    public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IC2CExtras.MODID, "textures/gui/thermal_centrifuge.png");

    public static final Box2i MACHINE_CHARGE_BOX = new Box2i(17, 36, 14, 14);
    public static final Vec2i MACHINE_CHARGE_POS = new Vec2i(176, 0);
    public static final Box2i MACHINE_PROGRESS_BOX = new Box2i(48, 35, 45, 17);
    public static final Vec2i MACHINE_PROGRESS_POS = new Vec2i(176, 14);
    public static final Box2i MACHINE_HEAT_BOX = new Box2i(56, 52, 24, 17);
    public static final Vec2i MACHINE_HEAT_POS = new Vec2i(176, 31);

    public ContainerThermalCentrifuge(TileEntityThermalCentrifuge tile, Player player, int id) {
        super(tile, player, id);
        this.addSlot(FilterSlot.createDischargeSlot(tile, tile.tier, 0, 17, 53));
        this.addSlot(new FilterSlot(tile, 1, 17, 17, (T) -> {
            return tile.getRecipeList().getRecipe(T, false) != null;
        }));
        this.addSlot(new XPSlot(tile, 2, 111, 17));
        this.addSlot(new XPSlot(tile, 3, 111, 35));
        this.addSlot(new XPSlot(tile, 4, 111, 53));

        for (int i = 0; i < tile.upgradeSlots; ++i) {
            this.addSlot(new UpgradeSlot(tile, 5 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player.getInventory());
        this.addComponent(new ChargebarComponent(ContainerThermalCentrifuge.MACHINE_CHARGE_BOX, tile, ContainerThermalCentrifuge.MACHINE_CHARGE_POS, true));
        this.addComponent(new ProgressComponent(ContainerThermalCentrifuge.MACHINE_PROGRESS_BOX, tile, ContainerThermalCentrifuge.MACHINE_PROGRESS_POS, false));
        this.addComponent(new HeatComponent(tile, ContainerThermalCentrifuge.MACHINE_HEAT_BOX, ContainerThermalCentrifuge.MACHINE_HEAT_POS));
    }

    @Override
    public ResourceLocation getTexture() {
        return GUI_TEXTURE;
    }
}
