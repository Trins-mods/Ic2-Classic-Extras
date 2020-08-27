package trinsdar.ic2c_extras.items;

import ic2.api.classic.reactor.IReactorPlanner;
import ic2.api.classic.reactor.ISteamReactor;
import ic2.api.reactor.IReactor;
import ic2.core.item.reactor.base.ItemUraniumRodBase;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.items.urantypes.MOX;
import trinsdar.ic2c_extras.items.urantypes.Plutonium;
import trinsdar.ic2c_extras.items.urantypes.Thorium230;
import trinsdar.ic2c_extras.items.urantypes.Thorium232;
import trinsdar.ic2c_extras.items.urantypes.Uranium233;
import trinsdar.ic2c_extras.items.urantypes.Uranium235;
import trinsdar.ic2c_extras.items.urantypes.Uranium238;

import java.util.Arrays;
import java.util.List;

public class ItemNuclearRod extends ItemUraniumRodBase {
    NuclearRodTypes type;
    NuclearRodVariants variant;

    private int index;
    public static IUranium[] types = new IUranium[0];

    public ItemNuclearRod(NuclearRodTypes type, NuclearRodVariants variant) {
        this.type = type;
        this.variant = variant;
        String name = type.getPrefix() + variant.getPrefix();
        this.setRegistryName(IC2CExtras.MODID, name.toLowerCase() + "cell");
        this.setCreativeTab(IC2CExtras.creativeTab);
        setUnlocalizedName(new LangComponentHolder.LocaleItemComp("item." + name + "Cell"));
    }

    public static void init() {
        types = new IUranium[7];
        types[0] = new Uranium238();
        types[1] = new Plutonium();
        types[2] = new MOX();
        types[3] = new Thorium232();
        types[4] = new Uranium233();
        types[5] = new Uranium235();
        types[6] = new Thorium230();
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta) {
        return getUran(variant).getTexture(getRodType(type));
    }

    @Override
    public LocaleComp getLangComponent(ItemStack stack) {
        return this.name;
    }

    @Override
    public IUranium getUranium(ItemStack stack) {
        return getUran(variant);
    }

    @Override
    public IUranium.RodType getRodType(ItemStack itemStack) {
        return this.getRodType(type);
    }

    @Override
    public ItemStack[] getSubParts() {
        return new ItemStack[0];
    }

    @Override
    public boolean hasSubParts() {
        return false;
    }

    @Override
    public ItemStack getReactorPart() {
        return getUran(variant).getRodType(getRodType(type));
    }

    @Override
    public short getID(ItemStack stack) {
        return this.getUran(variant).getRodID(getRodType(type));
    }

    @Override
    public IUranium.RodType getRodType(int metadata) {
        return this.getRodType(type);
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (variant == NuclearRodVariants.MOX && !(reactor instanceof ISteamReactor)){
            if (reactor instanceof IReactorPlanner) {
                IReactorPlanner planner = (IReactorPlanner)reactor;
                if (planner.isCollecting()) {
                    planner.addFuelPulse();
                }
            }
            if (!heatrun) {
                reactor.addOutput(this.getUranium(stack).getEUPerPulse() * getEuModifier(reactor));
            }
            return true;
        }
        return super.acceptUraniumPulse(stack, reactor, pulsingStack, youX, youY, pulseX, pulseY, heatrun);
    }

    public float getEuModifier(IReactor reactor){
        float percent = Math.min(1f, (float)reactor.getHeat() / 100000f);
        return 4.0F * percent + 1.0F;
    }

    public IUranium.RodType getRodType(NuclearRodTypes type) {
        if (type == NuclearRodTypes.SINGLE) {
            return IUranium.RodType.SingleRod;
        } else if (type == NuclearRodTypes.DOUBLE) {
            return IUranium.RodType.DualRod;
        } else if (type == NuclearRodTypes.QUAD) {
            return IUranium.RodType.QuadRod;
        }
        return IUranium.RodType.SingleRod;
    }

    public static IUranium getUran(NuclearRodVariants variant) {
        if (variant == NuclearRodVariants.URANIUM238) {
            return types[0];
        } else if (variant == NuclearRodVariants.PLUTONIUM) {
            return types[1];
        } else if (variant == NuclearRodVariants.MOX) {
            return types[2];
        } else if (variant == NuclearRodVariants.THORIUM232) {
            return types[3];
        } else if (variant == NuclearRodVariants.THORIUM230) {
            return types[4];
        } else if (variant == NuclearRodVariants.URANIUM235){
            return types[5];
        } else if (variant == NuclearRodVariants.URANIUM233){
            return types[6];
        }
        return types[0];
    }

    @Override
    public int getRodAmount(ItemStack stack) {
        return (int) this.getRodAmount(type);
    }

    @Override
    public IUranium getUranium(int i) {
        return getUran(variant);
    }

    public float getRodAmount(NuclearRodTypes type) {
        if (type == NuclearRodTypes.SINGLE) {
            return 1;
        } else if (type == NuclearRodTypes.DOUBLE) {
            return 2;
        } else if (type == NuclearRodTypes.QUAD) {
            return 4;
        }
        return 1;
    }


    @Override
    public int getTextureEntry(int i) {
        return 0;
    }


    public static enum NuclearRodTypes {
        SINGLE("single"),
        DOUBLE("double"),
        QUAD("quad");

        private String prefix;

        private NuclearRodTypes(String name) {
            prefix = name;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    public static enum NuclearRodVariants {
        URANIUM238("Uranium238"),
        PLUTONIUM("Plutonium"),
        MOX("MOX"),
        THORIUM232("Thorium232"),
        THORIUM230("Thorium230"),
        URANIUM235("Uranium235"),
        URANIUM233("Uranium233");

        private String prefix;

        private NuclearRodVariants(String name) {
            prefix = name;
        }

        public String getPrefix() {
            return prefix;
        }
    }
}
