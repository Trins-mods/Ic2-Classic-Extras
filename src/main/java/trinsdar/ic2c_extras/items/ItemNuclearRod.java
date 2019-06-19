package trinsdar.ic2c_extras.items;

import ic2.api.classic.reactor.IReactorPlanner;
import ic2.api.classic.reactor.IReactorPlannerComponent;
import ic2.api.classic.reactor.ISteamReactor;
import ic2.api.classic.reactor.ISteamReactorComponent;
import ic2.api.reactor.IReactor;
import ic2.core.block.machine.high.TileEntityUraniumEnricher;
import ic2.core.item.base.ItemGrandualInt;
import ic2.core.item.reactor.ItemReactorUraniumRod;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.obj.IBootable;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.items.urantypes.ThoriumOxide;
import trinsdar.ic2c_extras.items.urantypes.MOX;
import trinsdar.ic2c_extras.items.urantypes.Plutonium;
import trinsdar.ic2c_extras.items.urantypes.Thorium;
import trinsdar.ic2c_extras.items.urantypes.UOX;

import java.util.Arrays;
import java.util.List;

public class ItemNuclearRod extends ItemGrandualInt implements IBootable, ISteamReactorComponent, IReactorPlannerComponent {
    NuclearRodTypes type;
    NuclearRodVariants variant;

    private int index;
    public static IUranium[] types = new IUranium[0];
    public ItemNuclearRod(NuclearRodTypes type, NuclearRodVariants variant, int index){
        this.type = type;
        this.variant = variant;
        setUnlocalizedName(type.getPrefix() + variant.getPrefix() + "Cell");
        this.setCreativeTab(IC2CExtras.creativeTab);
        this.index = index;
    }

    @Override
    public void onLoad() {
        types = new IUranium[5];
        types[0] = new UOX();
        types[1] = new Plutonium();
        types[2] = new MOX();
        types[3] = new Thorium();
        types[4] = new ThoriumOxide();
        TileEntityUraniumEnricher.RECIPE_LIST.add(types[0]);
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
    public ItemStack[] getSubParts() {
        return new ItemStack[0];
    }

    @Override
    public boolean hasSubParts() {
        return false;
    }

    @Override
    public ItemStack getReactorPart() {
        return null;
    }

    @Override
    public short getID(ItemStack stack) {
        return this.getUran().getRodID(getRodType());
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
        }else if (variant == NuclearRodVariants.THORIUM){
            return types[3];
        }else if (variant == NuclearRodVariants.THORIUMOXIDE){
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
    public NBTPrimitive getReactorStat(ReactorComponentStat reactorComponentStat, ItemStack itemStack) {
        return null;
    }

    @Override
    public boolean isAdvancedStat(ReactorComponentStat reactorComponentStat, ItemStack itemStack) {
        return false;
    }

    @Override
    public NBTPrimitive getReactorStat(IReactor iReactor, int i, int i1, ItemStack itemStack, ReactorComponentStat reactorComponentStat) {
        return null;
    }

    @Override
    public void processTick(ISteamReactor iSteamReactor, ItemStack itemStack, int i, int i1, boolean b, boolean b1) {

    }

    @Override
    public void processChamber(ItemStack itemStack, IReactor iReactor, int i, int i1, boolean b) {

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
    public boolean canStoreHeat(ItemStack itemStack, IReactor iReactor, int i, int i1) {
        return false;
    }

    @Override
    public int getMaxHeat(ItemStack itemStack, IReactor iReactor, int i, int i1) {
        return 0;
    }

    @Override
    public int getCurrentHeat(ItemStack itemStack, IReactor iReactor, int i, int i1) {
        return 0;
    }

    @Override
    public int alterHeat(ItemStack itemStack, IReactor iReactor, int x, int y, int heat) {
        return heat;
    }

    @Override
    public float influenceExplosion(ItemStack stack, IReactor reactor) {
        if (type != NuclearRodTypes.ISOTOPE){
            return this.getUran().getExplosionEffectModifier() * this.getRodAmount(type);
        }
        return 0.0f;
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
    public boolean canBePlacedIn(ItemStack itemStack, IReactor iReactor) {
        return true;
    }

    @Override
    public int getTextureEntry(int i) {
        return 0;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (type == NuclearRodTypes.ISOTOPE) {
            if (this.isInCreativeTab(tab)){
                items.add(this.getUran().getNewIsotopicRod());
            }

        }else {
            super.getSubItems(tab, items);
        }
    }

    @Override
    public boolean applyCustomDamage(ItemStack stack, int damage, EntityLivingBase src) {
        super.applyCustomDamage(stack, damage, src);
        return this.getCustomDamage(stack) > this.getMaxCustomDamage(stack);
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
        THORIUM("Thorium"),
        THORIUMOXIDE("ThoriumOxide");

        private String prefix;

        private NuclearRodVariants(String name) {
            prefix = name;
        }

        public String getPrefix() {
            return prefix;
        }
    }
}
