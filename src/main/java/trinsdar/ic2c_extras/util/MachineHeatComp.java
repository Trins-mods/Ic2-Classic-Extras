package trinsdar.ic2c_extras.util;

import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.buttons.IconButton;
import ic2.core.inventory.gui.components.GuiComponent;
import ic2.core.platform.lang.storage.Ic2GuiLang;
import ic2.core.platform.registry.Ic2GuiComp;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.tileentity.TileEntityThermalCentrifuge;

import java.util.Arrays;
import java.util.List;

public class MachineHeatComp extends GuiComponent {

	byte lastMode;
	TileEntityThermalCentrifuge block;
	int white = 16777215;

	public MachineHeatComp(TileEntityThermalCentrifuge tile) {
		super(Ic2GuiComp.nullBox);
		this.block = tile;
	}

	@Override
	public List<ActionRequest> getNeededRequests() {
		return Arrays.asList(ActionRequest.GuiInit, ActionRequest.ButtonNotify, ActionRequest.GuiTick,
				ActionRequest.FrontgroundDraw, ActionRequest.BackgroundDraw);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void drawFrontground(GuiIC2 gui, int mouseX, int mouseY) {
		gui.drawString(block.getBlockName(), 12, 8, white);
		gui.drawString(Ic2GuiLang.energyStorageCharge, 12, 18, white);
		float heat = this.block.getHeat();
		float maxHeat = this.block.getMaxHeat();
		if (heat > maxHeat) {
			heat = maxHeat;
		}

		gui.drawString("" + heat, 12, 28, white);
		gui.drawString("/" + maxHeat, 12, 38, white);
		gui.drawString(Ic2GuiLang.energyStorageOutput.getLocalizedFormatted(new Object[] { this.block.heat }), 12, 48,
				white);
	}


}
