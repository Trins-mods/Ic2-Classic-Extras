package trinsdar.ic2c_extras.common.util;

import ic2.core.inventory.container.ContainerComponent;
import ic2.core.inventory.gui.GuiComponentContainer;

public class DeviceGui extends GuiComponentContainer {
    public DeviceGui(ContainerComponent container) {
        super(container);
    }

    public static class OreWashingPlantGui extends DeviceGui {
        public OreWashingPlantGui(ContainerComponent container) {
            super(container);
        }
    }
}