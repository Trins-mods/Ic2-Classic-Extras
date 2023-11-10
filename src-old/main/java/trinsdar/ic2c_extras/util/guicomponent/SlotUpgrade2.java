package trinsdar.ic2c_extras.util.guicomponent;

import ic2.api.classic.tile.IMachine;
import ic2.core.IC2;
import ic2.core.inventory.slots.SlotUpgrade;
import trinsdar.ic2c_extras.tileentity.TileEntityTreeTapper;
import trinsdar.ic2c_extras.tileentity.base.TileEntityContainerInputBase;
import trinsdar.ic2c_extras.tileentity.base.TileEntityFluidCannerBase;

public class SlotUpgrade2 extends SlotUpgrade {
    public SlotUpgrade2(IMachine par1, int par2, int par3, int par4) {
        super(par1, par2, par3, par4);
    }

    @Override
    public void onSlotChanged() {
        if (IC2.platform.isSimulating()) {
            if (getMachine() instanceof TileEntityContainerInputBase) {
                ((TileEntityContainerInputBase) getMachine()).setOverclockRates();
            }
            if (getMachine() instanceof TileEntityFluidCannerBase) {
                ((TileEntityFluidCannerBase) getMachine()).setOverclockRates();
            }
            if (getMachine() instanceof TileEntityTreeTapper) {
                ((TileEntityTreeTapper) getMachine()).setOverclockerUpgrade();
            }
        }
    }
}
