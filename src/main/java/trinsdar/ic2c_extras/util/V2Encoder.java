package trinsdar.ic2c_extras.util;

import ic2.core.block.machine.med.logic.encoder.impl.V1Encoder;

public class V2Encoder extends V1Encoder {
    @Override
    public int getBitLimit() {
        return 12;
    }
}
