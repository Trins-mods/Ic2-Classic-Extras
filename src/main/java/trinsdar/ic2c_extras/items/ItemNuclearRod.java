package trinsdar.ic2c_extras.items;

import ic2.api.classic.reactor.IReactorPlannerComponent;
import ic2.api.classic.reactor.ISteamReactor;
import ic2.api.classic.reactor.ISteamReactorComponent;
import ic2.api.reactor.IReactor;
import ic2.core.item.base.ItemGrandualInt;
import ic2.core.util.obj.IBootable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTPrimitive;

import java.util.List;

public class ItemNuclearRod extends ItemGrandualInt
        implements IBootable, ISteamReactorComponent, IReactorPlannerComponent {
    public ItemNuclearRod(String name){
        setUnlocalizedName(name);
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
    public short getID(ItemStack itemStack) {
        return 0;
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

    }
}
