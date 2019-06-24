package trinsdar.ic2c_extras.items;

import ic2.api.classic.reactor.IReactorPlanner;
import ic2.api.classic.reactor.ISteamReactor;
import ic2.api.reactor.IReactor;
import ic2.core.block.machine.high.TileEntityUraniumEnricher;
import ic2.core.item.reactor.ItemReactorUraniumRod;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.items.urantypes.Thorium230;
import trinsdar.ic2c_extras.items.urantypes.MOX;
import trinsdar.ic2c_extras.items.urantypes.Plutonium;
import trinsdar.ic2c_extras.items.urantypes.Thorium232;
import trinsdar.ic2c_extras.items.urantypes.UOX;
import trinsdar.ic2c_extras.recipes.Ic2cExtrasRecipes;

import java.util.Arrays;
import java.util.List;

public class ItemNuclearRod extends ItemReactorUraniumRod {
    NuclearRodTypes type;
    NuclearRodVariants variant;

    private int index;
    public static IUranium[] types = new IUranium[0];
    public ItemNuclearRod(NuclearRodTypes type, NuclearRodVariants variant){
        this.type = type;
        this.variant = variant;
        setUnlocalizedName(type.getPrefix() + variant.getPrefix() + "Cell");
        this.setHasSubtypes(false);
        this.setCreativeTab(IC2CExtras.creativeTab);
    }

    @Override
    public void onLoad() {
        IC2CExtras.logger.info(this.getLangComponent().getUnlocalized());
        types = new IUranium[5];
        types[0] = new UOX();
        types[1] = new Plutonium();
        types[2] = new MOX();
        types[3] = new Thorium232();
        types[4] = new Thorium230();
        if (variant == NuclearRodVariants.UOX &&  type == NuclearRodTypes.SINGLE){
            TileEntityUraniumEnricher.RECIPE_LIST.add(types[0]);
        }
    }

    @Override
    public LocaleComp getLangComponent(ItemStack stack) {
        if (Ic2cExtrasRecipes.enableEmptyRods){
            return new LangComponentHolder.LocaleItemComp(this.getUnlocalizedName().replace("Cell", "Rod"));
        }
        return new LangComponentHolder.LocaleItemComp(this.getUnlocalizedName());
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getTexture(int meta) {
        return getUran().getTexture(getRodType());
    }

    @Override
    public int getMaxCustomDamage(ItemStack stack) {
        return this.getUran().getMaxDurability();
    }

    @Override
    public IUranium getUran(ItemStack stack) {
        return this.getUran();
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
    public short getID(ItemStack stack) {
        return this.getUran().getRodID(getRodType());
    }

    @Override
    public IUranium.RodType getRodType(int metadata) {
        return this.getRodType();
    }

    public IUranium.RodType getRodType(){
        if (type == NuclearRodTypes.SINGLE){
            return IUranium.RodType.SingleRod;
        }else if (type == NuclearRodTypes.DOUBLE){
            return IUranium.RodType.DualRod;
        }else if (type == NuclearRodTypes.QUAD){
            return IUranium.RodType.QuadRod;
        }else if (type == NuclearRodTypes.ISOTOPE){
            return IUranium.RodType.IsotopicRod;
        }
        return IUranium.RodType.SingleRod;
    }

    public IUranium getUran(){
        if (variant == NuclearRodVariants.UOX){
            return types[0];
        }else if (variant == NuclearRodVariants.PLUTONIUM){
            return types[1];
        }else if (variant == NuclearRodVariants.MOX){
            return types[2];
        }else if (variant == NuclearRodVariants.THORIUM232){
            return types[3];
        }else if (variant == NuclearRodVariants.THORIUM230){
            return types[4];
        }
        return types[0];
    }

    @Override
    public ReactorType getReactorInfo(ItemStack itemStack) {
        if (type == NuclearRodTypes.ISOTOPE){
            return ReactorType.Reactor;
        }
        return ReactorType.Both;
    }

    @Override
    public ReactorComponentType getType(ItemStack itemStack) {
        if (type == NuclearRodTypes.ISOTOPE){
            return ReactorComponentType.IsotopeCell;
        }
        return ReactorComponentType.FuelRod;
    }

    @Override
    public List<ReactorComponentStat> getExtraStats(ItemStack itemStack) {
        return null;
    }

    @Override
    public NBTPrimitive getReactorStat(ReactorComponentStat stat, ItemStack stack) {
        IUranium uran = this.getUran();
        if (type == NuclearRodTypes.ISOTOPE){
            return uran != null && stat == ReactorComponentStat.MaxDurability ? new NBTTagInt(uran.getMaxDurability()) : nulltag;
        }else {
            return super.getReactorStat(stat, stack);
        }
    }

    @Override
    public boolean isAdvancedStat(ReactorComponentStat stat, ItemStack itemStack) {
        return type != NuclearRodTypes.ISOTOPE && super.isAdvancedStat(stat, itemStack);
    }

    @Override
    public NBTPrimitive getReactorStat(IReactor reactor, int x, int y, ItemStack stack, ReactorComponentStat stat) {
        if (type == NuclearRodTypes.ISOTOPE){
            return nulltag;
        }else {
            return super.getReactorStat(reactor, x, y, stack, stat);
        }
    }

    @Override
    public void processTick(ISteamReactor iSteamReactor, ItemStack itemStack, int i, int i1, boolean b, boolean b1) {
        if(type != NuclearRodTypes.ISOTOPE){
            super.processTick(iSteamReactor, itemStack, i, i1, b, b1);
        }
    }

    @Override
    public void processChamber(ItemStack itemStack, IReactor iReactor, int i, int i1, boolean b) {
        if(type != NuclearRodTypes.ISOTOPE){
            super.processChamber(itemStack, iReactor, i, i1, b);
        }
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (reactor instanceof IReactorPlanner) {
            IReactorPlanner planner = (IReactorPlanner)reactor;
            if (planner.isCollecting()) {
                if (type == NuclearRodTypes.ISOTOPE){
                    planner.addReEnrichPulse();
                }else {
                    planner.addFuelPulse();
                }
            }
        }
        IUranium uran = this.getUran();
        int myLevel;
        if (reactor instanceof ISteamReactor) {
            if (heatrun && type == NuclearRodTypes.ISOTOPE) {
                myLevel = this.getCustomDamage(stack) - 1 - reactor.getHeat() / 3000;
                if (myLevel <= 0) {
                    reactor.setItemAt(youX, youY, uran.getRodType(IUranium.RodType.ReEnrichedRod));
                } else {
                    this.setCustomDamage(stack, myLevel);
                }
            }
            return true;
        } else {
            if (type == NuclearRodTypes.ISOTOPE){
                myLevel = this.getCustomDamage(stack) - 1 - reactor.getHeat() / 3000;
                if (myLevel <= 0) {
                    reactor.setItemAt(youX, youY, uran.getRodType(IUranium.RodType.ReEnrichedRod));
                } else {
                    this.setCustomDamage(stack, myLevel);
                }
            }else {
                if (!heatrun) {
                    reactor.addOutput(uran.getEUPerPulse());
                }
            }


            return true;
        }
    }

    @Override
    public float influenceExplosion(ItemStack stack, IReactor reactor) {
        if (type != NuclearRodTypes.ISOTOPE){
            return this.getUran().getExplosionEffectModifier() * this.getRodAmount(type);
        }
        return 0.0f;
    }

    @Override
    public int getRodAmount(ItemStack stack) {
        return (int)this.getRodAmount(type);
    }

    public float getRodAmount(NuclearRodTypes type){
        if (type == NuclearRodTypes.SINGLE){
            return 1;
        } else if (type == NuclearRodTypes.DOUBLE){
            return 2;
        }else if (type == NuclearRodTypes.QUAD){
            return 4;
        }
        return 1;
    }


    @Override
    public int getTextureEntry(int i) {
        return 0;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)){
            if (type == NuclearRodTypes.ISOTOPE) {
                items.add(this.getUran().getNewIsotopicRod());
            }else {
                items.add(new ItemStack(this));
            }
        }

    }

    public static enum NuclearRodTypes {
        SINGLE("single"),
        DOUBLE("double"),
        QUAD("quad"),
        ISOTOPE("isotopic");

        private String prefix;

        private NuclearRodTypes(String name) {
            prefix = name;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    public static enum NuclearRodVariants {
        UOX("UOX"),
        PLUTONIUM("Plutonium"),
        MOX("MOX"),
        THORIUM232("Thorium232"),
        THORIUM230("Thorium230");

        private String prefix;

        private NuclearRodVariants(String name) {
            prefix = name;
        }

        public String getPrefix() {
            return prefix;
        }
    }
}
