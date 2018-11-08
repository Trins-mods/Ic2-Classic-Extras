package trinsdar.ic2c_extras.common.container;

import ic2.api.classic.tile.IMachine;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.GuiIC2;
import ic2.core.inventory.gui.buttons.IC2Button;
import ic2.core.inventory.gui.buttons.IconButton;
import ic2.core.inventory.gui.buttons.ToggleButton;
import ic2.core.inventory.gui.components.base.MachineChargeComp;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotDischarge;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.inventory.slots.SlotUpgrade;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.storage.Ic2GuiLang;
import ic2.core.platform.registry.Ic2GuiComp;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.common.tileentity.TileEntityMetalPress;

public class ContainerMetalPress  extends ContainerTileComponent<TileEntityMetalPress>{
    public static Box2D machineProgressBox = new Box2D(78, 36, 25, 14);
    public static Vec2i machineProgressPos = new Vec2i(176, 14);
    //public static Box2D rollingButton = new Box2D(20, -28, 28, 31);
    //public static Box2D extrudingButton = new Box2D(20, -28, 28, 31);
    //public static Box2D cuttingButton = new Box2D(20, -28, 28, 31);
    public static IconButton rollingButton = new IconButton(1, 78, 52, 20, 20, new LangComponentHolder.LocaleGuiComp("rollingMode"));
    public static IconButton extrudingButton = new IconButton(2, 78, 52, 20, 20, new LangComponentHolder.LocaleGuiComp("extrudingMode"));
    public static IconButton cuttingButton = new IconButton(3, 78, 52, 20, 20, new LangComponentHolder.LocaleGuiComp("cuttingMode"));

    public ContainerMetalPress(InventoryPlayer player, TileEntityMetalPress tile) {
        super(tile);
        this.addSlotToContainer(new SlotCustom(tile, 0, 56, 17, null));
        this.addSlotToContainer(new SlotDischarge(tile, 2147483647, 1, 56, 53));
        this.addSlotToContainer(new SlotOutput(player.player, tile, 2, 116, 35));

        for(int i = 0; i < 4; ++i)
        {
            this.addSlotToContainer(new SlotUpgrade((IMachine) tile, 3 + i, 152, 8 + i * 18));
        }

        this.addPlayerInventory(player);
        this.addComponent(new MachineChargeComp(tile, Ic2GuiComp.machineChargeBox, Ic2GuiComp.machineChargePos));
        this.addComponent(new MachineProgressComp(tile, Ic2GuiComp.machineProgressBox, Ic2GuiComp.machineProgressPos));

    }

    @SideOnly(Side.CLIENT)
    public void onGuiInit(GuiIC2 gui) {
        gui.registerButton(ContainerMetalPress.rollingButton);
        gui.registerButton(ContainerMetalPress.extrudingButton);
        gui.registerButton(ContainerMetalPress.cuttingButton);

    }

    @SideOnly(Side.CLIENT)
    public void onGuiTick(GuiIC2 gui) {
        gui.getButton(1).enabled = true;
        gui.getButton(2).enabled = true;
        gui.getButton(3).enabled = true;
    }

    @SideOnly(Side.CLIENT)
    public void onButtonClick(GuiIC2 gui, GuiButton button) {
        int id = button.id;
        if (id == 1){
            rollingButton.setTextVisiblity(false);
            extrudingButton.setTextVisiblity(true);

        }else if (id == 2){
            extrudingButton.setTextVisiblity(false);
            cuttingButton.setTextVisiblity(true);

        }else if (id == 3){
            cuttingButton.setTextVisiblity(false);
            rollingButton.setTextVisiblity(true);

        }

    }


    @Override
    public ResourceLocation getTexture() {
        return this.getGuiHolder().getGuiTexture();
    }

    @Override
    public int guiInventorySize() {
        return this.getGuiHolder().slotCount;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.getGuiHolder().canInteractWith(playerIn);
    }
}
