package trinsdar.ic2c_extras.items.override;

import ic2.core.item.misc.ItemMisc;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;
import trinsdar.ic2c_extras.util.references.Ic2cExtrasLang;

public class ItemMisc2 extends ItemMisc {
    @Override
    public void onLoad() {
        super.onLoad();
        this.unlocalizedNames.put(180, Ic2cExtrasLang.uraniumFuel);
    }
}
