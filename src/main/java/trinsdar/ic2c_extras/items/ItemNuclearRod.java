package trinsdar.ic2c_extras.items;

import ic2.api.classic.reactor.IReactorPlannerComponent;
import ic2.api.classic.reactor.ISteamReactor;
import ic2.api.classic.reactor.ISteamReactorComponent;
import ic2.api.reactor.IReactor;
import ic2.core.item.base.ItemGrandualInt;
import ic2.core.item.reactor.uranTypes.CharcoalUranium;
import ic2.core.item.reactor.uranTypes.IUranium;
import ic2.core.util.obj.IBootable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTPrimitive;
import trinsdar.ic2c_extras.items.urantypes.Californium;
import trinsdar.ic2c_extras.items.urantypes.MOX;
import trinsdar.ic2c_extras.items.urantypes.Plutonium;
import trinsdar.ic2c_extras.items.urantypes.Thorium;
import trinsdar.ic2c_extras.items.urantypes.UOX;

import java.util.List;

public class ItemNuclearRod extends ItemGrandualInt implements IBootable, ISteamReactorComponent, IReactorPlannerComponent {
    private int index;
    public static IUranium[] types = new IUranium[0];
    public ItemNuclearRod(String name, int index){
        setUnlocalizedName(name);
        this.index = index;
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
        return this.getUran(0).getRodID();
    }

    public IUranium getUran(int type){
        return types[type];
    }

    @Override
    public ReactorType getReactorInfo(ItemStack itemStack) {
        return null;
    }

    @Override
    public ReactorComponentType getType(ItemStack itemStack) {
        return null;
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
    public boolean acceptUraniumPulse(ItemStack itemStack, IReactor iReactor, ItemStack itemStack1, int i, int i1, int i2, int i3, boolean b) {
        return false;
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
    public int alterHeat(ItemStack itemStack, IReactor iReactor, int i, int i1, int i2) {
        return 0;
    }

    @Override
    public float influenceExplosion(ItemStack itemStack, IReactor iReactor) {
        return 0;
    }

    @Override
    public boolean canBePlacedIn(ItemStack itemStack, IReactor iReactor) {
        return false;
    }

    @Override
    public int getTextureEntry(int i) {
        return 0;
    }

    @Override
    public List<Integer> getValidVariants() {
        return null;
    }

    @Override
    public void onLoad() {
        types = new IUranium[5];
        types[0] = new UOX();
        types[1] = new Plutonium();
        types[2] = new MOX();
        types[3] = new Thorium();
        types[4] = new Californium();
    }
}
