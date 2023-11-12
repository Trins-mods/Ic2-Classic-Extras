package trinsdar.ic2c_extras.container;


import ic2.core.block.machines.containers.lv.BasicMachineContainer;
import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.filter.IFilter;
import ic2.core.inventory.gui.components.simple.ChargebarComponent;
import ic2.core.inventory.gui.components.simple.ProgressComponent;
import ic2.core.inventory.gui.components.simple.TankComponent;
import ic2.core.inventory.slot.FilterSlot;
import ic2.core.inventory.slot.UpgradeSlot;
import ic2.core.inventory.slot.XPSlot;
import ic2.core.utils.math.geometry.Box2i;
import ic2.core.utils.math.geometry.Vec2i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.blockentity.TileEntityOreWashingPlant;

public class ContainerOreWashingPlant extends ContainerComponent<TileEntityOreWashingPlant> {
    public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(IC2CExtras.MODID, "textures/gui/ore_washing_plant.png");
    public ContainerOreWashingPlant(TileEntityOreWashingPlant tile, Player player, int windowId) {
        super(tile, player, windowId);
        this.addSlot(FilterSlot.createDischargeSlot(tile, tile.tier, 0, 56, 53));
        this.addSlot(new FilterSlot(tile, 1, 56, 17, (T) -> {
            return tile.getRecipeList().getRecipe(T, false) != null;
        }));
        this.addSlot(new XPSlot(tile, 2, 111, 17));
        this.addSlot(new XPSlot(tile, 3, 111, 35));
        this.addSlot(new XPSlot(tile, 4, 111, 53));

        for (int i = 0; i < tile.upgradeSlots; ++i) {
            this.addSlot(new UpgradeSlot(tile, 5 + i, 152, 8 + i * 18));
        }
        this.addPlayerInventory(player.getInventory());
        this.addComponent(new ChargebarComponent(BasicMachineContainer.CHARGE_BOX, tile, BasicMachineContainer.CHARGE_POS, true));
        this.addComponent(new ProgressComponent(BasicMachineContainer.PROGRESS_BOX, tile, BasicMachineContainer.PROGRESS_POS, false));
        this.addComponent(new TankComponent(new Box2i(32, 13, 16, 58), new Vec2i(176, 133), tile.getWaterTank()).setTankName("Water Tank"));
    }

    @Override
    public ResourceLocation getTexture() {
        return GUI_TEXTURE;
    }
}
