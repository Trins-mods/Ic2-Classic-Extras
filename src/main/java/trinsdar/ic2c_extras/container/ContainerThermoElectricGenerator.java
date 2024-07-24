package trinsdar.ic2c_extras.container;


import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.filter.SimpleFilter;
import ic2.core.inventory.gui.components.simple.ChargebarComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.blockentity.BlockEntityThermoElectricGenerator;
import trinsdar.ic2c_extras.init.ModItems;

public class ContainerThermoElectricGenerator extends ContainerComponent<BlockEntityThermoElectricGenerator> {
    public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IC2CExtras.MODID, "textures/gui/thermo_electric_generator.png");
    public static final Box2i CHARGE_BOX = new Box2i(94, 39, 24, 9);
    public static final Vec2i CHARGE_POS = new Vec2i(176, 0);
    public ContainerThermoElectricGenerator(BlockEntityThermoElectricGenerator tile, Player player, int windowId) {
        super(tile, player, windowId);
        for (int y = 0; y < 2; ++y) {
            for (int x = 0; x < 3; ++x) {
                this.addSlot(new FilterSlot(tile, x + y * 3, 29 + x * 18, 27 + y * 18, new SimpleFilter(ModItems.PLUTONIUM_RTG)));
            }
        }
        this.addPlayerInventory(player.getInventory());
        this.addComponent(new ChargebarComponent(CHARGE_BOX, tile, CHARGE_POS, false));
    }

    @Override
    public ResourceLocation getTexture() {
        return GUI_TEXTURE;
    }
}
