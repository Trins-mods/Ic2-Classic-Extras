package trinsdar.ic2c_extras.common.tileentity;

import ic2.api.classic.audio.PositionSpec;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.INullableRecipeInput;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IRecipeMachine;
import ic2.api.classic.tile.MachineType;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.energy.EnergyNet;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.RotationList;
import ic2.core.audio.AudioSource;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.comparator.ComparatorManager;
import ic2.core.block.base.util.comparator.comparators.ComparatorProgress;
import ic2.core.block.base.util.info.EnergyUsageInfo;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.block.base.util.info.misc.IEnergyUser;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.*;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.helpers.FilteredList;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.container.ContainerThermalCentrifuge;
import trinsdar.ic2c_extras.common.items.RegistryItem;

import java.util.*;

public class TileEntityThermalCentrifuge extends TileEntityElecMachine implements ITickable, IProgressMachine, IRecipeMachine, IOutputMachine, IHasGui, INetworkTileEntityEventListener, IEnergyUser
{

    @NetworkField(index = 7)
    public float progress = 0.0F;

    public int defaultEnergyConsume;
    public int defaultOperationLength;
    public int defaultMaxInput;
    public int defaultEnergyStorage;
    public int energyConsume;
    public int operationLength;
    public float progressPerTick;

    @NetworkField(index = 8)
    public float soundLevel = 1.0F;

    public IMachineRecipeList.RecipeEntry lastRecipe;

    @NetworkField(index = 9)
    public int recipeOperation;

    @NetworkField(index = 10)
    public int recipeEnergy;

    @NetworkField(index = 11)
    public boolean redstoneInverted;

    @NetworkField(index = 12)
    public boolean redstoneSensitive;

    public boolean defaultSensitive;
    public List<ItemStack> results = new ArrayList();
    public AudioSource audioSource;
    public IFilter filter;

    public static IMachineRecipeList thermalCentrifuge = new BasicMachineRecipeList("thermalCentrifuge");

    public static final int slotInput = 0;
    public static final int slotFuel = 1;
    public static final int slotOutput = 2;
    public static final int slotOutput2 = 3;
    public static final int slotOutput3 = 4;


    public TileEntityThermalCentrifuge() {
        this( 9, 48, 400, 128);
    }

    public TileEntityThermalCentrifuge(int slots, int energyPerTick, int maxProgress, int maxInput)
    {
        super(slots, maxInput);
        this.setFuelSlot(slotFuel);
        this.setCustomName("tileThermalCentrifuge");
        this.energyConsume = energyPerTick;
        this.defaultEnergyConsume = energyPerTick;
        this.operationLength = maxProgress;
        this.defaultOperationLength = maxProgress;
        this.defaultMaxInput = this.maxInput;
        this.defaultEnergyStorage = energyPerTick * maxProgress;
        this.defaultSensitive = false;
        this.addNetworkFields(new String[]{"soundLevel", "redstoneInverted", "redstoneSensitive"});
        this.addGuiFields(new String[]{"recipeOperation", "recipeEnergy", "progress"});
        this.addInfos(new InfoComponent[]{new EnergyUsageInfo(this), new ProgressInfo(this)});
    }

    @Override
    protected void addComparators(ComparatorManager manager)
    {
        super.addComparators(manager);
        manager.addComparatorMode(new ComparatorProgress(this));
    }

    @Override
    protected void addSlots(InventoryHandler handler)
    {
        this.filter = new MachineFilter(this);
        handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
        handler.registerDefaultSlotAccess(AccessRule.Both, new int[]{slotFuel});
        handler.registerDefaultSlotAccess(AccessRule.Import, new int[]{slotInput});
        handler.registerDefaultSlotAccess(AccessRule.Export, new int[]{slotOutput, slotOutput2, slotOutput3});
        handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), new int[]{0, 2, 4});
        handler.registerDefaultSlotsForSide(RotationList.DOWN.getOppositeList(), new int[]{1, 3});
        handler.registerInputFilter(new ArrayFilter(new IFilter[]{CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)}), new int[]{slotFuel});
        handler.registerOutputFilter(CommonFilters.NotDischargeEU, new int[]{slotFuel});
        handler.registerSlotType(SlotType.Fuel, new int[]{slotFuel});
        handler.registerSlotType(SlotType.Input, new int[]{slotInput});
        handler.registerSlotType(SlotType.Output, new int[]{slotOutput, slotOutput2, slotOutput3});
    }

    @Override
    public int getEnergyUsage()
    {
        return this.recipeEnergy;
    }

    @Override
    public float getProgress()
    {
        return this.progress;
    }

    @Override
    public float getMaxProgress()
    {
        return (float) this.recipeOperation;
    }

    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input)
    {
        return thermalCentrifuge.getRecipeInAndOutput(input, false);
    }

    public boolean canWorkWithoutItems()
    {
        return false;
    }

    public ResourceLocation getStartSoundFile()
    {
        return Ic2Sounds.maceratorOp;
    }

    public ResourceLocation getInterruptSoundFile()
    {
        return Ic2Sounds.interruptingSound;
    }

    public boolean supportsNotify()
    {
        return true;
    }

    public boolean needsInitialRedstoneUpdate()
    {
        return true;
    }


    public void update()
    {
        this.handleChargeSlot(500);
        this.updateNeighbors();

        boolean noRoom = this.addToInventory();
        IMachineRecipeList.RecipeEntry entry = this.getRecipe();
        boolean canWork = this.canWork() && !noRoom;
        boolean operate = canWork && entry != null;
        if (operate)
        {
            this.handleChargeSlot(this.maxEnergy);
        }

        if (operate && this.energy >= this.energyConsume)
        {
            if (!this.getActive())
            {
                this.getNetwork().initiateTileEntityEvent(this, 0, false);
            }

            this.setActive(true);
            this.progress += this.progressPerTick;
            this.useEnergy(this.recipeEnergy);
            if (this.progress >= (float) this.recipeOperation)
            {
                this.operate(entry);
                this.progress = 0.0F;
                this.notifyNeighbors();
            }

            this.getNetwork().updateTileGuiField(this, "progress");
        }
        else
        {
            if (this.getActive())
            {
                if (this.progress != 0.0F)
                {
                    this.getNetwork().initiateTileEntityEvent(this, 1, false);
                }
                else
                {
                    this.getNetwork().initiateTileEntityEvent(this, 2, false);
                }
            }

            if (entry == null && this.progress != 0.0F)
            {
                this.progress = 0.0F;
                this.getNetwork().updateTileGuiField(this, "progress");
            }

            this.setActive(false);
        }

        for (int i = 0; i < 4; ++i)
        {
            ItemStack item = (ItemStack) this.inventory.get(i + this.inventory.size() - 4);
            if (item.getItem() instanceof IMachineUpgradeItem)
            {
                ((IMachineUpgradeItem) item.getItem()).onTick(item, this);
            }
        }

        this.updateComparators();
    }

    public void handleModifiers(IMachineRecipeList.RecipeEntry entry)
    {
        if (entry != null && entry.getOutput().getMetadata() != null)
        {
            NBTTagCompound nbt = entry.getOutput().getMetadata();
            double energyMod = nbt.hasKey("RecipeEnergyModifier") ? nbt.getDouble("RecipeEnergyModifier") : 1.0D;
            int newEnergy = applyModifier(this.energyConsume, nbt.getInteger("RecipeEnergy"), energyMod);
            if (newEnergy != this.recipeEnergy)
            {
                this.recipeEnergy = newEnergy;
                if (this.recipeEnergy < 1)
                {
                    this.recipeEnergy = 1;
                }

                this.getNetwork().updateTileGuiField(this, "recipeEnergy");
            }

            double progMod = nbt.hasKey("RecipeTimeModifier") ? nbt.getDouble("RecipeTimeModifier") : 1.0D;
            int newProgress = applyModifier(this.operationLength, nbt.getInteger("RecipeTime"), progMod);
            if (newProgress != this.recipeOperation)
            {
                this.recipeOperation = newProgress;
                if (this.recipeOperation < 1)
                {
                    this.recipeOperation = 1;
                }

                this.getNetwork().updateTileGuiField(this, "recipeOperation");
            }

        }
        else
        {
            if (this.recipeEnergy != this.energyConsume)
            {
                this.recipeEnergy = this.energyConsume;
                if (this.recipeEnergy < 1)
                {
                    this.recipeEnergy = 1;
                }

                this.getNetwork().updateTileGuiField(this, "recipeEnergy");
            }

            if (this.recipeOperation != this.operationLength)
            {
                this.recipeOperation = this.operationLength;
                if (this.recipeOperation < 1)
                {
                    this.recipeOperation = 1;
                }

                this.getNetwork().updateTileGuiField(this, "recipeOperation");
            }

        }
    }

    public void operate(IMachineRecipeList.RecipeEntry entry)
    {
        IRecipeInput input = entry.getInput();
        MachineOutput output = entry.getOutput().copy();

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemStack = (ItemStack) this.inventory.get(i + this.inventory.size() - 4);
            if (itemStack.getItem() instanceof IMachineUpgradeItem)
            {
                IMachineUpgradeItem item = (IMachineUpgradeItem) itemStack.getItem();
                item.onProcessEndPre(itemStack, this, output);
            }
        }

        List<ItemStack> list = new FilteredList();
        this.operateOnce(input, output, list);

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemStack = (ItemStack) this.inventory.get(i + this.inventory.size() - 4);
            if (itemStack.getItem() instanceof IMachineUpgradeItem)
            {
                IMachineUpgradeItem item = (IMachineUpgradeItem) itemStack.getItem();
                item.onProcessEndPost(itemStack, this, input, output, list);
            }
        }

        if (list.size() > 0)
        {
            this.results.addAll(list);
            this.addToInventory();
        }

    }

    public void operateOnce(IRecipeInput input, MachineOutput output, List<ItemStack> list)
    {
        list.addAll(output.getRecipeOutput(this.getMachineWorld().rand));
        if (!(input instanceof INullableRecipeInput) || !((ItemStack) this.inventory.get(slotInput)).isEmpty())
        {
            if (((ItemStack) this.inventory.get(slotInput)).getItem().hasContainerItem((ItemStack) this.inventory.get(slotInput)))
            {
                this.inventory.set(slotInput, ((ItemStack) this.inventory.get(slotInput)).getItem().getContainerItem((ItemStack) this.inventory.get(slotInput)));
            }
            else
            {
                ((ItemStack) this.inventory.get(slotInput)).shrink(input.getAmount());
            }

        }
    }

    public boolean addToInventory()
    {
        if (this.results.isEmpty())
        {
            return false;
        }
        else
        {
            for (int i = 0; i < this.results.size(); ++i)
            {
                ItemStack item = (ItemStack) this.results.get(i);
                if (item.isEmpty())
                {
                    this.results.remove(i--);
                }
                else if (((ItemStack) this.inventory.get(slotOutput)).isEmpty())
                {
                    this.inventory.set(slotOutput, item.copy());
                    this.results.remove(i--);
                }
                else if (StackUtil.isStackEqual((ItemStack) this.inventory.get(slotOutput), item, false, false))
                {
                    int left = ((ItemStack) this.inventory.get(slotOutput)).getMaxStackSize() - ((ItemStack) this.inventory.get(slotOutput)).getCount();
                    if (left <= 0)
                    {
                        break;
                    }

                    if (left < item.getCount())
                    {
                        int itemLeft = item.getCount() - left;
                        item.setCount(itemLeft);
                        ((ItemStack) this.inventory.get(slotOutput)).setCount(((ItemStack) this.inventory.get(slotOutput)).getMaxStackSize());
                        break;
                    }

                    ((ItemStack) this.inventory.get(slotOutput)).grow(item.getCount());
                    this.results.remove(i--);
                }
                else if (((ItemStack) this.inventory.get(slotOutput2)).isEmpty())
                {
                    this.inventory.set(slotOutput2, item.copy());
                    this.results.remove(i--);
                }
                else if (StackUtil.isStackEqual((ItemStack) this.inventory.get(slotOutput2), item, false, false))
                {
                    int left = ((ItemStack) this.inventory.get(slotOutput2)).getMaxStackSize() - ((ItemStack) this.inventory.get(slotOutput2)).getCount();
                    if (left <= 0)
                    {
                        break;
                    }

                    if (left < item.getCount())
                    {
                        int itemLeft = item.getCount() - left;
                        item.setCount(itemLeft);
                        ((ItemStack) this.inventory.get(slotOutput2)).setCount(((ItemStack) this.inventory.get(slotOutput2)).getMaxStackSize());
                        break;
                    }

                    ((ItemStack) this.inventory.get(slotOutput2)).grow(item.getCount());
                    this.results.remove(i--);
                }
                else if (((ItemStack) this.inventory.get(slotOutput3)).isEmpty())
                {
                    this.inventory.set(slotOutput3, item.copy());
                    this.results.remove(i--);
                }
                else if (StackUtil.isStackEqual((ItemStack) this.inventory.get(slotOutput3), item, false, false))
                {
                    int left = ((ItemStack) this.inventory.get(slotOutput3)).getMaxStackSize() - ((ItemStack) this.inventory.get(slotOutput3)).getCount();
                    if (left <= 0)
                    {
                        break;
                    }

                    if (left < item.getCount())
                    {
                        int itemLeft = item.getCount() - left;
                        item.setCount(itemLeft);
                        ((ItemStack) this.inventory.get(slotOutput3)).setCount(((ItemStack) this.inventory.get(slotOutput3)).getMaxStackSize());
                        break;
                    }

                    ((ItemStack) this.inventory.get(slotOutput3)).grow(item.getCount());
                    this.results.remove(i--);
                }}
            }

            return this.results.size() > 0;
        }
    }

    private IMachineRecipeList.RecipeEntry getRecipe()
    {
        if (((ItemStack) this.inventory.get(slotInput)).isEmpty() && !this.canWorkWithoutItems())
        {
            return null;
        }
        else
        {
            if (this.lastRecipe != null)
            {
                IRecipeInput recipe = this.lastRecipe.getInput();
                if (recipe instanceof INullableRecipeInput)
                {
                    if (!recipe.matches((ItemStack) this.inventory.get(slotInput)))
                    {
                        this.lastRecipe = null;
                    }
                }
                else if (!((ItemStack) this.inventory.get(slotInput)).isEmpty() && recipe.matches((ItemStack) this.inventory.get(0)))
                {
                    if (recipe.getAmount() > ((ItemStack) this.inventory.get(slotInput)).getCount())
                    {
                        return null;
                    }
                }
                else
                {
                    this.lastRecipe = null;
                }
            }

            if (this.lastRecipe == null)
            {
                IMachineRecipeList.RecipeEntry out = this.getOutputFor(((ItemStack) this.inventory.get(slotInput)).copy());
                if (out == null)
                {
                    return null;
                }

                this.lastRecipe = out;
                this.handleModifiers(out);
            }

            if (this.lastRecipe == null)
            {
                return null;
            }
            else if (((ItemStack) this.inventory.get(slotOutput)).getCount() >= ((ItemStack) this.inventory.get(slotOutput)).getMaxStackSize())
            {
                return null;
            }
            else if (((ItemStack) this.inventory.get(slotOutput2)).getCount() >= ((ItemStack) this.inventory.get(slotOutput2)).getMaxStackSize())
            {
                return null;
            }
            else if (((ItemStack) this.inventory.get(slotOutput3)).getCount() >= ((ItemStack) this.inventory.get(slotOutput3)).getMaxStackSize())
            {
                return null;
            }
            else if (((ItemStack) this.inventory.get(slotOutput)).isEmpty())
            {
                return this.lastRecipe;
            }
            else
            {
                Iterator var4 = this.lastRecipe.getOutput().getAllOutputs().iterator();

                ItemStack output;
                do
                {
                    if (!var4.hasNext())
                    {
                        return null;
                    }

                    output = (ItemStack) var4.next();
                }
                while (!StackUtil.isStackEqual((ItemStack) this.inventory.get(slotOutput), output, false, true));

                return this.lastRecipe;
            }
        }
    }

    public boolean canWork()
    {
        return !this.redstoneSensitive ? true : this.isRedstonePowered();
    }

    public boolean isRedstonePowered()
    {
        if (this.redstoneInverted)
        {
            return !super.isRedstonePowered();
        }
        else
        {
            return super.isRedstonePowered();
        }
    }

    public void handleRedstone()
    {
        if (this.redstoneSensitive)
        {
            super.handleRedstone();
        }

    }

    public double getEnergy()
    {
        return (double) this.energy;
    }

    public boolean useEnergy(double amount, boolean simulate)
    {
        if ((double) this.energy < amount)
        {
            return false;
        }
        else
        {
            if (!simulate)
            {
                this.useEnergy((int) amount);
            }

            return true;
        }
    }

    public void setRedstoneSensitive(boolean active)
    {
        if (this.redstoneSensitive != active)
        {
            this.redstoneSensitive = active;
        }

    }

    public boolean isRedstoneSensitive()
    {
        return this.redstoneSensitive;
    }

    public boolean isProcessing()
    {
        return this.getActive();
    }

    @Override
    public boolean isValidInput(ItemStack itemStack)
    {
        return false;
    }

    public float getRecipeProgress()
    {
        float ret = this.progress / (float) this.recipeOperation;
        if (ret > 1.0F)
        {
            ret = 1.0F;
        }

        return ret;
    }

    public void setOverclockRates()
    {
        this.lastRecipe = null;
        int extraProcessSpeed = 0;
        double processingSpeedMultiplier = 1.0D;
        int extraProcessTime = 0;
        double processTimeMultiplier = 1.0D;
        int extraEnergyDemand = 0;
        double energyDemandMultiplier = 1.0D;
        int extraEnergyStorage = 0;
        double energyStorageMultiplier = 1.0D;
        int extraTier = 0;
        float soundModfier = 1.0F;
        boolean redstonePowered = false;
        this.redstoneSensitive = this.defaultSensitive;

        for (int i = 0; i < 4; ++i)
        {
            ItemStack item = (ItemStack) this.inventory.get(i + this.inventory.size() - 4);
            if (item.getItem() instanceof IMachineUpgradeItem)
            {
                IMachineUpgradeItem upgrade = (IMachineUpgradeItem) item.getItem();
                upgrade.onInstalling(item, this);
                extraProcessSpeed += upgrade.getExtraProcessSpeed(item, this) * item.getCount();
                processingSpeedMultiplier *= Math.pow(upgrade.getProcessSpeedMultiplier(item, this), (double) item.getCount());
                extraProcessTime += upgrade.getExtraProcessTime(item, this) * item.getCount();
                processTimeMultiplier *= Math.pow(upgrade.getProcessTimeMultiplier(item, this), (double) item.getCount());
                extraEnergyDemand += upgrade.getExtraEnergyDemand(item, this) * item.getCount();
                energyDemandMultiplier *= Math.pow(upgrade.getEnergyDemandMultiplier(item, this), (double) item.getCount());
                extraEnergyStorage += upgrade.getExtraEnergyStorage(item, this) * item.getCount();
                energyStorageMultiplier *= Math.pow(upgrade.getEnergyStorageMultiplier(item, this), (double) item.getCount());
                soundModfier = (float) ((double) soundModfier * Math.pow((double) upgrade.getSoundVolumeMultiplier(item, this), (double) item.getCount()));
                extraTier += upgrade.getExtraTier(item, this) * item.getCount();
                if (upgrade.useRedstoneInverter(item, this))
                {
                    redstonePowered = true;
                }
            }
        }

        this.redstoneInverted = redstonePowered;
        this.progressPerTick = applyFloatModifier(1, extraProcessSpeed, processingSpeedMultiplier);
        this.energyConsume = applyModifier(this.defaultEnergyConsume, extraEnergyDemand, energyDemandMultiplier);
        this.operationLength = applyModifier(this.defaultOperationLength, extraProcessTime, processTimeMultiplier);
        this.setMaxEnergy(applyModifier(this.defaultEnergyStorage, extraEnergyStorage, energyStorageMultiplier));
        this.tier = this.baseTier + extraTier;
        if (this.tier > 13)
        {
            this.tier = 13;
        }

        this.maxInput = (int) EnergyNet.instance.getPowerFromTier(this.tier);
        if (this.energy > this.maxEnergy)
        {
            this.energy = this.maxEnergy;
        }

        this.soundLevel = soundModfier;
        if (this.progressPerTick < 0.01F)
        {
            this.progressPerTick = 0.01F;
        }

        if (this.operationLength < 1)
        {
            this.operationLength = 1;
        }

        if (this.energyConsume < 1)
        {
            this.energyConsume = 1;
        }

        this.handleModifiers(this.lastRecipe);
        this.getNetwork().updateTileEntityField(this, "redstoneInverted");
        this.getNetwork().updateTileEntityField(this, "redstoneSensitive");
        this.getNetwork().updateTileEntityField(this, "soundLevel");
        this.getNetwork().updateTileGuiField(this, "maxInput");
        this.getNetwork().updateTileGuiField(this, "energy");
    }

    static int applyModifier(int base, int extra, double multiplier)
    {
        long ret = Math.round((double) (base + extra) * multiplier);
        return ret > 2147483647L ? 2147483647 : (int) ret;
    }

    static float applyFloatModifier(int base, int extra, double multiplier)
    {
        double ret = (double) Math.round((double) (base + extra) * multiplier);
        return ret > 2.147483648E9D ? 2.14748365E9F : (float) ret;
    }

    public void onLoaded()
    {
        super.onLoaded();
        if (this.isSimulating())
        {
            this.setOverclockRates();
        }

    }

    public void onUnloaded()
    {
        if (this.isRendering() && this.audioSource != null)
        {
            IC2.audioManager.removeSources(this);
            this.audioSource.remove();
            this.audioSource = null;
        }

        super.onUnloaded();
    }


    public void onNetworkEvent(int event)
    {
        if (this.audioSource != null && this.audioSource.isRemoved())
        {
            this.audioSource = null;
        }

        if (this.audioSource == null && this.getStartSoundFile() != null)
        {
            this.audioSource = IC2.audioManager.createSource(this, PositionSpec.Center, this.getStartSoundFile(), true, false, IC2.audioManager.defaultVolume * this.soundLevel);
        }

        if (event == 0)
        {
            if (this.audioSource != null)
            {
                this.audioSource.play();
            }
        }
        else if (event == 1)
        {
            if (this.audioSource != null)
            {
                this.audioSource.stop();
                if (this.getInterruptSoundFile() != null)
                {
                    IC2.audioManager.playOnce(this, PositionSpec.Center, this.getInterruptSoundFile(), false, IC2.audioManager.defaultVolume * this.soundLevel);
                }
            }
        }
        else if (event == 2 && this.audioSource != null)
        {
            this.audioSource.stop();
        }

    }

    public void onNetworkUpdate(String field)
    {
        if (field.equals("isActive") && this.getActive())
        {
            this.onNetworkEvent(0);
        }

        super.onNetworkUpdate(field);
        if (field.equals("soundLevel") && this.audioSource != null)
        {
            this.audioSource.setVolume(IC2.audioManager.defaultVolume * this.soundLevel);
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.progress = nbt.getFloat("progress");
        this.results.clear();
        NBTTagList list = nbt.getTagList("Results", 10);

        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound data = list.getCompoundTagAt(i);
            this.results.add(new ItemStack(data));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setFloat("progress", this.progress);
        NBTTagList list = new NBTTagList();
        Iterator var3 = this.results.iterator();

        while (var3.hasNext())
        {
            ItemStack item = (ItemStack) var3.next();
            NBTTagCompound data = new NBTTagCompound();
            item.writeToNBT(data);
            list.appendTag(data);
        }

        nbt.setTag("Results", list);
        return nbt;
    }

    @Override
    public Set<IMachineUpgradeItem.UpgradeType> getSupportedTypes()
    {
        return new LinkedHashSet(Arrays.asList(IMachineUpgradeItem.UpgradeType.values()));
    }

    @Override
    public World getMachineWorld()
    {
        return this.getWorld();
    }

    @Override
    public BlockPos getMachinePos()
    {
        return this.getPos();
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return !this.isInvalid();
    }

    @Override
    public ContainerIC2 getGuiContainer(EntityPlayer player)
    {
        return new ContainerThermalCentrifuge(player.inventory, this);
    }

    @Override
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player)
    {
        return GuiComponentContainer.class;
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer)
    {

    }

    @Override
    public boolean hasGui(EntityPlayer player)
    {
        return true;
    }

    @Override
    public LocaleComp getBlockName()
    {
        return new LangComponentHolder.LocaleBlockComp("tile.thermalCentrifuge");
    }

    public ResourceLocation getGuiTexture()
    {
        return new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guithermalcentrifuge.png");
    }

    @Override
    public MachineType getType()
    {
        return MachineType.macerator;
    }

    @Override
    public IMachineRecipeList getRecipeList()
    {
        return thermalCentrifuge;
    }

    @Override
    public IHasInventory getOutputInventory()
    {
        return new RangedInventoryWrapper(this, new int[]{slotOutput, slotOutput2, slotOutput3});
    }

    @Override
    public IHasInventory getInputInventory()
    {
        return new RangedInventoryWrapper(this, new int[]{slotInput});
    }

    @Override
    public String getName()
    {
        return "tileThermalCentrifuge";
    }

    @Override
    public boolean hasCustomName()
    {
        return true;
    }

    public static void init()
    {
        addRecipe((IRecipeInput) (new RecipeInputItemStack(new ItemStack(RegistryItem.purifiedCrushedOres, 1, 0))), new MachineOutput(null, Arrays.asList(new ItemStack[]{ (Ic2Items.ironDust), new ItemStack(RegistryItem.tinyDustTypes, 2, 0), new ItemStack(RegistryItem.itemMiscs, 1, 2)})));
        addRecipe((IRecipeInput) (new RecipeInputItemStack(new ItemStack(RegistryItem.purifiedCrushedOres, 1, 1))), new MachineOutput(null, Arrays.asList(new ItemStack[]{ (Ic2Items.goldDust), new ItemStack(RegistryItem.tinyDustTypes, 2, 1), new ItemStack(RegistryItem.itemMiscs, 1, 2)})));
        addRecipe((IRecipeInput) (new RecipeInputItemStack(new ItemStack(RegistryItem.purifiedCrushedOres, 1, 2))), new MachineOutput(null, Arrays.asList(new ItemStack[]{ (Ic2Items.copperDust), new ItemStack(RegistryItem.tinyDustTypes, 2, 2), new ItemStack(RegistryItem.itemMiscs, 1, 2)})));
        addRecipe((IRecipeInput) (new RecipeInputItemStack(new ItemStack(RegistryItem.purifiedCrushedOres, 1, 3))), new MachineOutput(null, Arrays.asList(new ItemStack[]{ (Ic2Items.tinDust), new ItemStack(RegistryItem.tinyDustTypes, 2, 3), new ItemStack(RegistryItem.itemMiscs, 1, 2)})));
        addRecipe((IRecipeInput) (new RecipeInputItemStack(new ItemStack(RegistryItem.purifiedCrushedOres, 1, 4))), new MachineOutput(null, Arrays.asList(new ItemStack[]{ (Ic2Items.silverDust), new ItemStack(RegistryItem.tinyDustTypes, 2, 4), new ItemStack(RegistryItem.itemMiscs, 1, 2)})));
        addRecipe((IRecipeInput) (new RecipeInputItemStack(new ItemStack(RegistryItem.purifiedCrushedOres, 1, 5))), new MachineOutput(null, Arrays.asList(new ItemStack[]{ new ItemStack(RegistryItem.itemMiscs, 1, 4), new ItemStack(RegistryItem.tinyDustTypes, 2, 5), new ItemStack(RegistryItem.itemMiscs, 1, 2)})));
        addRecipe((IRecipeInput) (new RecipeInputItemStack(new ItemStack(RegistryItem.purifiedCrushedOres, 1, 6))), new MachineOutput(null, Arrays.asList(new ItemStack[]{ new ItemStack(RegistryItem.itemMiscs, 1, 1), new ItemStack(RegistryItem.tinyDustTypes, 3, 6), new ItemStack(RegistryItem.itemMiscs, 1, 2)})));
    }

    public static void addRecipe(IRecipeInput input, MachineOutput output)
    {
        thermalCentrifuge.addRecipe(input, output, output.toString());
    }

    private static void addRecipe(ItemStack itemStack, MachineOutput machineOutput) {
    }

    private static String makeString(ItemStack stack)
    {
        return stack.getDisplayName();
    }

    @Override
    public void tick() {

    }
}
