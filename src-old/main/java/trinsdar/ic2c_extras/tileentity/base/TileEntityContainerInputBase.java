package trinsdar.ic2c_extras.tileentity.base;

import ic2.api.classic.audio.PositionSpec;
import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IStackOutput;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.classic.tile.machine.ISpeedMachine;
import ic2.api.energy.EnergyNet;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.recipe.IRecipeInput;
import ic2.core.IC2;
import ic2.core.audio.AudioSource;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.util.info.EnergyUsageInfo;
import ic2.core.block.base.util.info.ProgressInfo;
import ic2.core.block.base.util.info.SpeedInfo;
import ic2.core.block.base.util.info.misc.IEnergyUser;
import ic2.core.block.base.util.output.IStackRegistry;
import ic2.core.block.base.util.output.MultiSlotOutput;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.base.IHasInventory;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.transport.wrapper.RangedInventoryWrapper;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IOutputMachine;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.util.recipelists.ContainerInputRecipeList;
import trinsdar.ic2c_extras.util.recipelists.ContainerInputRecipeList.ContainerInputRecipe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public abstract class TileEntityContainerInputBase extends TileEntityElecMachine implements IOutputMachine, IProgressMachine, ISpeedMachine, IEnergyUser, ITickable, IHasGui, INetworkTileEntityEventListener {
    public static final String MOVE_CONTAINER_TAG = "move_container";

    @NetworkField(index = 7)
    public int progress = 0;

    // Defaults
    public int defaultEnergyConsume;
    public int defaultOperationLength;
    public int defaultMaxInput;
    public int defaultEnergyStorage;

    // Currents WithUpgrades
    public int energyConsume;
    public int operationLength;
    public float progressPerTick;
    @NetworkField(index = 8)
    public float soundLevel = 1F;

    @NetworkField(
            index = 9,
            compression = NetworkField.BitLevel.Bit16
    )
    public int speed;
    public static final int maxSpeed = 10000;

    // Current Usage & Time
    @NetworkField(index = 10)
    public int recipeOperation;
    @NetworkField(index = 11)
    public int recipeEnergy;

    @NetworkField(index = 12)
    public boolean redstoneInverted;
    @NetworkField(index = 13)
    public boolean redstoneSensitive;
    public boolean defaultSensitive;
    public ContainerInputRecipe lastRecipe;
    public boolean shouldCheckRecipe;
    public final boolean supportsUpgrades;
    public final int upgradeSlots;
    public static int slotInput;
    public static int slotInputContainer;
    public static int slotOutput;
    public AudioSource audioSource;

    LinkedList<IStackOutput> outputs = new LinkedList<IStackOutput>();

    public TileEntityContainerInputBase(int slots, int upgrades, int energyPerTick, int maxProgress, int maxinput) {
        super(slots + upgrades, maxinput);
        supportsUpgrades = upgrades > 0;
        upgradeSlots = upgrades;
        energyConsume = energyPerTick;
        defaultEnergyConsume = energyPerTick;
        operationLength = maxProgress;
        defaultOperationLength = maxProgress;
        speed = 0;
        defaultMaxInput = maxInput;
        defaultEnergyStorage = energyPerTick * maxProgress;
        defaultSensitive = false;
        progressPerTick = 1F;
        shouldCheckRecipe = true;
        addNetworkFields("soundLevel", "redstoneInverted", "redstoneSensitive");
        addGuiFields("recipeOperation", "recipeEnergy", "progress", "speed");
        addInfos(new EnergyUsageInfo(this), new ProgressInfo(this), new SpeedInfo(this));
    }

    @Override
    public void update() {
        handleRedstone();
        updateNeighbors();
        boolean noRoom = addToInventory();
        if (shouldCheckRecipe) {
            lastRecipe = getRecipe();
            shouldCheckRecipe = false;
        }
        boolean canWork = canWork() && !noRoom;
        boolean operate = (canWork && lastRecipe != null);
        if (operate) {
            handleChargeSlot(maxEnergy);
        }
        if ((isRedstonePowered() || lastRecipe != null) && this.energy > 0) {
            if (this.speed < 10000) {
                ++this.speed;
                this.getNetwork().updateTileGuiField(this, "speed");
            }

            this.useEnergy(1);
        } else if (this.speed > 0) {
            this.speed -= Math.min(this.speed, 4);
            this.getNetwork().updateTileGuiField(this, "speed");
        }
        if (operate && energy >= energyConsume) {
            if (!getActive()) {
                getNetwork().initiateTileEntityEvent(this, 0, false);
            }
            setActive(true);
            progress += (float) speed / 30 * progressPerTick;
            useEnergy(recipeEnergy);
            if (progress >= recipeOperation) {
                process(lastRecipe);
                progress = 0;
                notifyNeighbors();
            }
            getNetwork().updateTileGuiField(this, "progress");
        } else {
            if (getActive()) {
                if (progress != 0) {
                    getNetwork().initiateTileEntityEvent(this, 1, false);
                } else {
                    getNetwork().initiateTileEntityEvent(this, 2, false);
                }
            }
            if (lastRecipe == null && progress != 0) {
                progress = 0;
                getNetwork().updateTileGuiField(this, "progress");
            }
            setActive(false);
        }
        if (supportsUpgrades) {
            for (int i = 0; i < upgradeSlots; i++) {
                ItemStack item = inventory.get(i + inventory.size() - upgradeSlots);
                if (item.getItem() instanceof IMachineUpgradeItem) {
                    ((IMachineUpgradeItem) item.getItem()).onTick(item, this);
                }
            }
        }
        updateComparators();
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void process(ContainerInputRecipe recipe) {
        for (ItemStack stack : recipe.getOutputs().copy().getRecipeOutput(getWorld().rand, getTileData())) {
            outputs.add(new MultiSlotOutput(stack, getOutputSlots()));
        }

        IRecipeInput input = recipe.getInput();
        ItemStack container = inventory.get(slotInputContainer);
        ItemStack stack = inventory.get(slotInput);
        if (container.getItem().hasContainerItem(container)) {
            inventory.set(slotInputContainer, container.getItem().getContainerItem(container));
        }
        if (stack.getItem().hasContainerItem(stack)) {
            inventory.set(slotInput, stack.getItem().getContainerItem(stack));
        } else {
            stack.shrink(input.getAmount());
        }
        addToInventory();
        for (int i = 0; i < upgradeSlots; i++) {
            ItemStack item = inventory.get(i + inventory.size() - upgradeSlots);
            if (item.getItem() instanceof IMachineUpgradeItem) {
                ((IMachineUpgradeItem) item.getItem()).onProcessFinished(item, this);
            }
        }
        shouldCheckRecipe = true;
    }

    public boolean addToInventory() {
        if (outputs.isEmpty()) {
            return false;
        }
        for (Iterator<IStackOutput> iter = outputs.iterator(); iter.hasNext(); ) {
            IStackOutput output = iter.next();
            if (output.addToInventory(this)) {
                iter.remove();
            }
        }
        return outputs.size() > 0;
    }

    public ContainerInputRecipe getRecipe() {
        if (inventory.get(slotInput).isEmpty() || inventory.get(slotInputContainer).isEmpty()) {
            return null;
        }
        if (lastRecipe == ContainerInputRecipeList.INVALID_RECIPE) {
            return null;
        }
        if (lastRecipe != null) {
            if (!checkRecipe(lastRecipe)) {
                lastRecipe = null;
                applyRecipeEffect(null);
            }
        }
        if (lastRecipe == null) {
            lastRecipe = getRecipeList().getRecipe(new Predicate<ContainerInputRecipe>() {
                @Override
                public boolean test(ContainerInputRecipe t) {
                    if (checkRecipe(t)) {
                        return true;
                    }
                    return false;
                }
            });
            if (lastRecipe == ContainerInputRecipeList.INVALID_RECIPE) {
                return null;
            }
            applyRecipeEffect(lastRecipe.getOutputs());
        }
        if (lastRecipe == null) {
            return null;
        }
        if (getStackInSlot(slotOutput).isEmpty()) {
            return lastRecipe;
        }
        for (ItemStack output : lastRecipe.getOutputs().getAllOutputs()) {
            if (StackUtil.isStackEqual(inventory.get(slotOutput), output, false, true)) {
                if (inventory.get(slotOutput).getCount() + output.getCount() <= inventory.get(slotOutput)
                        .getMaxStackSize()) {
                    return lastRecipe;
                }
            }
        }
        return null;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        super.setStackInSlot(slot, stack);
        shouldCheckRecipe = true;
        if (isSimulating() && lastRecipe == ContainerInputRecipeList.INVALID_RECIPE && isRecipeSlot(slot)) {
            lastRecipe = null;
        }
    }

    public void applyRecipeEffect(MachineOutput output) {
        if (output == null || output.getMetadata() == null) {
            if (recipeEnergy != energyConsume) {
                recipeEnergy = energyConsume;
                if (recipeEnergy < 1) {
                    recipeEnergy = 1;
                }
                getNetwork().updateTileGuiField(this, "recipeEnergy");
            }
            if (recipeOperation != operationLength) {
                recipeOperation = operationLength;
                if (recipeOperation < 1) {
                    recipeOperation = 1;
                }
                getNetwork().updateTileGuiField(this, "recipeOperation");
            }
            return;
        }
        NBTTagCompound nbt = output.getMetadata();
        double energyMod = nbt.hasKey("RecipeEnergyModifier") ? nbt.getDouble("RecipeEnergyModifier") : 1F;
        int newEnergy = TileEntityBasicElectricMachine.applyModifier(energyConsume, nbt.getInteger("RecipeEnergy"),
                energyMod);
        if (newEnergy != recipeEnergy) {
            recipeEnergy = newEnergy;
            if (recipeEnergy < 1) {
                recipeEnergy = 1;
            }
            getNetwork().updateTileGuiField(this, "recipeEnergy");
        }
        double progMod = nbt.hasKey("RecipeTimeModifier") ? nbt.getDouble("RecipeTimeModifier") : 1F;
        int newProgress = TileEntityBasicElectricMachine.applyModifier(operationLength, nbt.getInteger("RecipeTime"),
                progMod);
        if (newProgress != recipeOperation) {
            recipeOperation = newProgress;
            if (recipeOperation < 1) {
                recipeOperation = 1;
            }
            getNetwork().updateTileGuiField(this, "recipeOperation");
        }
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
        if (isSimulating()) {
            setOverclockRates();
        }
        shouldCheckRecipe = true;
    }

    public void setOverclockRates() {
        lastRecipe = null;
        shouldCheckRecipe = true;
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
        redstoneSensitive = defaultSensitive;
        for (int i = 0; i < 4; i++) {
            ItemStack item = inventory.get(i + inventory.size() - 4);
            if (item.getItem() instanceof IMachineUpgradeItem) {
                IMachineUpgradeItem upgrade = (IMachineUpgradeItem) item.getItem();
                upgrade.onInstalling(item, this);
                extraProcessSpeed += upgrade.getExtraProcessSpeed(item, this) * item.getCount();
                processingSpeedMultiplier *= Math.pow(upgrade.getProcessSpeedMultiplier(item, this), item.getCount());
                extraProcessTime += upgrade.getExtraProcessTime(item, this) * item.getCount();
                processTimeMultiplier *= Math.pow(upgrade.getProcessTimeMultiplier(item, this), item.getCount());
                extraEnergyDemand += upgrade.getExtraEnergyDemand(item, this) * item.getCount();
                energyDemandMultiplier *= Math.pow(upgrade.getEnergyDemandMultiplier(item, this), item.getCount());
                extraEnergyStorage += upgrade.getExtraEnergyStorage(item, this) * item.getCount();
                energyStorageMultiplier *= Math.pow(upgrade.getEnergyStorageMultiplier(item, this), item.getCount());
                soundModfier *= Math.pow(upgrade.getSoundVolumeMultiplier(item, this), item.getCount());
                extraTier += upgrade.getExtraTier(item, this) * item.getCount();
                if (upgrade.useRedstoneInverter(item, this)) {
                    redstonePowered = true;
                }
            }
        }
        redstoneInverted = redstonePowered;
        progressPerTick = TileEntityBasicElectricMachine.applyFloatModifier(1, extraProcessSpeed,
                processingSpeedMultiplier);
        energyConsume = TileEntityBasicElectricMachine.applyModifier(defaultEnergyConsume, extraEnergyDemand,
                energyDemandMultiplier);
        operationLength = TileEntityBasicElectricMachine.applyModifier(defaultOperationLength, extraProcessTime,
                processTimeMultiplier);
        setMaxEnergy(TileEntityBasicElectricMachine.applyModifier(defaultEnergyStorage, extraEnergyStorage,
                energyStorageMultiplier));
        tier = baseTier + extraTier;
        if (tier > 13) {
            tier = 13;
        }
        maxInput = (int) EnergyNet.instance.getPowerFromTier(tier);
        if (energy > maxEnergy) {
            energy = maxEnergy;
        }
        soundLevel = soundModfier;
        if (progressPerTick < 0.01F) {
            progressPerTick = 0.01F;
        }
        if (operationLength < 1) {
            operationLength = 1;
        }
        if (energyConsume < 1) {
            energyConsume = 1;
        }
        if (lastRecipe == null || lastRecipe == ContainerInputRecipeList.INVALID_RECIPE) {
            applyRecipeEffect(null);
        } else {
            applyRecipeEffect(lastRecipe.getOutputs());
        }
        getNetwork().updateTileEntityField(this, "redstoneInverted");
        getNetwork().updateTileEntityField(this, "redstoneSensitive");
        getNetwork().updateTileEntityField(this, "soundLevel");
        getNetwork().updateTileGuiField(this, "maxInput");
        getNetwork().updateTileGuiField(this, "energy");
    }

    public boolean checkRecipe(ContainerInputRecipe entry) {
        if (!entry.matches(inventory.get(slotInput), inventory.get(slotInputContainer))) {
            return false;
        }
        return true;
    }

    public abstract int[] getInputSlots();

    public abstract IFilter[] getInputFilters(int[] slots);

    public abstract boolean isRecipeSlot(int slot);

    public abstract int[] getOutputSlots();

    public abstract ContainerInputRecipeList getRecipeList();

    public boolean canWork() {
        if (!redstoneSensitive) {
            return true;
        }
        return isRedstonePowered();
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public boolean useEnergy(double amount, boolean simulate) {
        if (energy < amount) {
            return false;
        }
        if (!simulate) {
            useEnergy((int) amount);
        }
        return true;
    }

    @Override
    public void setRedstoneSensitive(boolean active) {
        if (redstoneSensitive != active) {
            redstoneSensitive = active;
        }
    }

    @Override
    public boolean isRedstoneSensitive() {
        return redstoneSensitive;
    }

    @Override
    public boolean isRedstonePowered() {
        return this.redstoneInverted != super.isRedstonePowered();
    }

    @Override
    public boolean isProcessing() {
        return getActive();
    }

    @Override
    public boolean isValidInput(ItemStack par1) {
        return getRecipeList().isValidRecipeInput(par1);
    }

    public boolean isValidContainer(ItemStack par1) {
        return getRecipeList().isValidRecipeContainer(par1);
    }

    @Override
    public World getMachineWorld() {
        return getWorld();
    }

    @Override
    public BlockPos getMachinePos() {
        return getPos();
    }

    @Override
    public int getEnergyUsage() {
        return recipeEnergy;
    }

    @Override
    public float getProgress() {
        return progress;
    }

    @Override
    public float getMaxProgress() {
        return recipeOperation;
    }

    @Override
    public IHasInventory getOutputInventory() {
        return new RangedInventoryWrapper(this, getOutputSlots());
    }

    @Override
    public IHasInventory getInputInventory() {
        int[] input = getInputSlots();
        return (new RangedInventoryWrapper(this, input).addFilters(getInputFilters(input)));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return !isInvalid();
    }

    public void onUnloaded() {
        if (this.isRendering() && this.audioSource != null) {
            IC2.audioManager.removeSources(this);
            this.audioSource.remove();
            this.audioSource = null;
        }

        super.onUnloaded();
    }

    public ResourceLocation getStartSoundFile() {
        return Ic2Sounds.electricFurnaceLoop;
    }

    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

    public void onNetworkEvent(int event) {
        if (this.audioSource != null && this.audioSource.isRemoved()) {
            this.audioSource = null;
        }

        if (this.audioSource == null && this.getStartSoundFile() != null) {
            this.audioSource = IC2.audioManager.createSource(this, PositionSpec.Center, this.getStartSoundFile(), true,
                    false, IC2.audioManager.defaultVolume * this.soundLevel);
        }

        if (event == 0) {
            if (this.audioSource != null) {
                this.audioSource.play();
            }
        } else if (event == 1) {
            if (this.audioSource != null) {
                this.audioSource.stop();
                if (this.getInterruptSoundFile() != null) {
                    IC2.audioManager.playOnce(this, PositionSpec.Center, this.getInterruptSoundFile(), false,
                            IC2.audioManager.defaultVolume * this.soundLevel);
                }
            }
        } else if (event == 2 && this.audioSource != null) {
            this.audioSource.stop();
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.progress = nbt.getInteger("progress");
        this.speed = nbt.getInteger("Speed");
        this.outputs.clear();
        NBTTagList list = nbt.getTagList("Results", 10);

        for (int i = 0; i < list.tagCount(); ++i) {
            IStackOutput output = IStackRegistry.INSTANCE.readNBT(list.getCompoundTagAt(i));
            if (output != null) {
                this.outputs.add(output);
            }
        }

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("progress", this.progress);
        nbt.setInteger("Speed", this.speed);
        NBTTagList list = new NBTTagList();
        Iterator var3 = this.outputs.iterator();

        while (var3.hasNext()) {
            IStackOutput item = (IStackOutput) var3.next();
            list.appendTag(IStackRegistry.INSTANCE.saveNBT(item));
        }

        nbt.setTag("Results", list);
        return nbt;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
        return GuiComponentContainer.class;
    }

    @Override
    public void onGuiClosed(EntityPlayer player) {

    }

    @Override
    public boolean hasGui(EntityPlayer player) {
        return true;
    }

}
