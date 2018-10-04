package trinsdar.ic2c_extras.common.tileentity;

import ic2.api.classic.item.IMachineUpgradeItem;
import ic2.api.classic.recipe.INullableRecipeInput;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.machine.recipes.managers.BasicMachineRecipeList;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LangComponentHolder;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.items.RegistryItem;

import java.util.Iterator;

public class TileEntityMetalPress  extends TileEntityBasicElectricMachine {

    public TileEntityMetalPress(){super(3, 10, 400, 32);}

    public MachineType getType() {
        return MachineType.macerator;
    }

    public LocaleComp getBlockName()
    {
        return new LangComponentHolder.LocaleBlockComp("tile.metalPress");
    }

    public static IMachineRecipeList metalPress = new BasicMachineRecipeList("metalPress");

    @Override
    public IMachineRecipeList.RecipeEntry getOutputFor(ItemStack input) {
        return recipeList[0].getRecipeInAndOutput(input, false);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/guimetalpress.png");
    }

    public Class<? extends GuiScreen> getGuiClass(EntityPlayer player)
    {
        return GuiComponentContainer.class;
    }

    public ResourceLocation getStartSoundFile() {
        return Ic2Sounds.maceratorOp;
    }

    public ResourceLocation getInterruptSoundFile() {
        return Ic2Sounds.interruptingSound;
    }

    public double getWrenchDropRate() {
        return 0.8500000238418579D;
    }

    public boolean isValidInput(ItemStack par1) {
        if (par1 == null) {
            return false;
        } else {
            return recipeList[0].getRecipeInAndOutput(par1, true) != null ? super.isValidInput(par1) : false;
        }
    }

    @Override
    public boolean supportsNotify() {
        return true;
    }

    @Override
    public String getName() {
        return "MetalPress";
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    int index = 0;
    public static IMachineRecipeList[] recipeList;
//    public IMachineRecipeList.RecipeEntry getRecipeForStack(ItemStack input)
//    {
//        return recipeList[index].getRecipe(input);
//    }

    @Override
    public IMachineRecipeList getRecipeList() {
        return recipeList[0];
    }

    private IMachineRecipeList.RecipeEntry getRecipe() {
        if (((ItemStack)this.inventory.get(0)).isEmpty() && !this.canWorkWithoutItems()) {
            return null;
        } else {
            if (this.lastRecipe != null) {
                IRecipeInput recipe = this.lastRecipe.getInput();
                if (recipe instanceof INullableRecipeInput) {
                    if (!recipe.matches((ItemStack)this.inventory.get(0))) {
                        this.lastRecipe = null;
                    }
                } else if (!((ItemStack)this.inventory.get(0)).isEmpty() && recipe.matches((ItemStack)this.inventory.get(0))) {
                    if (recipe.getAmount() > ((ItemStack)this.inventory.get(0)).getCount()) {
                        return null;
                    }
                } else {
                    this.lastRecipe = null;
                }
            }

            if (this.lastRecipe == null) {
                IMachineRecipeList.RecipeEntry out = this.getOutputFor(((ItemStack)this.inventory.get(0)).copy());
                if (out == null) {
                    return null;
                }

                this.lastRecipe = out;
                this.handleModifiers(out);
            }

            if (this.lastRecipe == null) {
                return null;
            } else if (((ItemStack)this.inventory.get(2)).isEmpty()) {
                return this.lastRecipe;
            } else if (((ItemStack)this.inventory.get(2)).getCount() >= ((ItemStack)this.inventory.get(2)).getMaxStackSize()) {
                return null;
            } else {
                Iterator var4 = this.lastRecipe.getOutput().getAllOutputs().iterator();

                ItemStack output;
                do {
                    if (!var4.hasNext()) {
                        return null;
                    }

                    output = (ItemStack)var4.next();
                } while(!StackUtil.isStackEqual((ItemStack)this.inventory.get(2), output, false, true));

                return this.lastRecipe;
            }
        }
    }

    @Override
    public void update() {
        this.handleRedstone();
        this.updateNeighbors();
        boolean noRoom = this.addToInventory();
        IMachineRecipeList.RecipeEntry entry = this.getRecipe();
        boolean canWork = this.canWork() && !noRoom;
        boolean operate = canWork && entry != null;
        if (operate) {
            this.handleChargeSlot(this.maxEnergy);
        }

        if (operate && this.energy >= this.energyConsume) {
            if (!this.getActive()) {
                this.getNetwork().initiateTileEntityEvent(this, 0, false);
            }

            this.setActive(true);
            this.progress += this.progressPerTick;
            this.useEnergy(this.recipeEnergy);
            if (this.progress >= (float)this.recipeOperation) {
                this.operate(entry);
                this.progress = 0.0F;
                this.notifyNeighbors();
            }

            this.getNetwork().updateTileGuiField(this, "progress");
        } else {
            if (this.getActive()) {
                if (this.progress != 0.0F) {
                    this.getNetwork().initiateTileEntityEvent(this, 1, false);
                } else {
                    this.getNetwork().initiateTileEntityEvent(this, 2, false);
                }
            }

            if (entry == null && this.progress != 0.0F) {
                this.progress = 0.0F;
                this.getNetwork().updateTileGuiField(this, "progress");
            }

            this.setActive(false);
        }

        for(int i = 0; i < 4; ++i) {
            ItemStack item = (ItemStack)this.inventory.get(i + this.inventory.size() - 4);
            if (item.getItem() instanceof IMachineUpgradeItem) {
                ((IMachineUpgradeItem)item.getItem()).onTick(item, this);
            }
        }

        this.updateComparators();
    }

    public static void init(){
        recipeList = new IMachineRecipeList[3];
        recipeList[0] = new BasicMachineRecipeList("Rolling");
        //recipes for future rolling mode
        addRecipe((String)"ingotCopper", 1, new ItemStack(RegistryItem.itemCasings, 2, 0), 0.7f);
        addRecipe((String)"ingotTin", 1, new ItemStack(RegistryItem.itemCasings, 2, 1), 0.7f);
        addRecipe((String)"ingotSilver", 1, new ItemStack(RegistryItem.itemCasings, 2, 2), 0.7f);
        addRecipe((String)"ingotLead", 1, new ItemStack(RegistryItem.itemCasings, 2, 3), 0.7f);
        addRecipe((String)"ingotIron", 1, new ItemStack(RegistryItem.itemCasings, 2, 4), 0.7f);
        addRecipe((String)"ingotGold", 1, new ItemStack(RegistryItem.itemCasings, 2, 5), 0.7f);
        addRecipe((String)"ingotRefinedIron", 1, new ItemStack(RegistryItem.itemCasings, 2, 6), 0.7f);
        addRecipe((String)"ingotSteel", 1, new ItemStack(RegistryItem.itemCasings, 2, 7), 0.7f);
        addRecipe((String)"ingotBronze", 1, new ItemStack(RegistryItem.itemCasings, 2, 8), 0.7f);
        recipeList[1] = new BasicMachineRecipeList("Extruding");
        //recipes for future extruding mode
        addRecipe((String)"ingotCopper", 1, StackUtil.copyWithSize(Ic2Items.copperCable, 3), 0.7f);
        addRecipe((String)"ingotTin", 1, StackUtil.copyWithSize(Ic2Items.tinCable, 4), 0.7f);
        addRecipe((String)"ingotBronze", 1, StackUtil.copyWithSize(Ic2Items.bronzeCable, 3), 0.7f);
        addRecipe((String)"ingotSteel", 1, StackUtil.copyWithSize(Ic2Items.ironCable, 5), 0.7f);
        addRecipe((String)"ingotGold", 1, StackUtil.copyWithSize(Ic2Items.goldCable, 5), 0.7f);
        addRecipe(new ItemStack(RegistryItem.itemCasings, 1, 1), StackUtil.copyWithSize(Ic2Items.tinCan, 1), 0.7f);
        recipeList[2] = new BasicMachineRecipeList("Cutting");
        //currently no recipes for cutting as that mode is mainly aimed at modpack makers
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input)), output);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input, stacksize)), output);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output) {
        addRecipe((IRecipeInput)(new RecipeInputOreDict(input, stacksize)), output);
    }

    public static void addRecipe(ItemStack input, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input)), output, exp);
    }

    public static void addRecipe(ItemStack input, int stacksize, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputItemStack(input, stacksize)), output, exp);
    }

    public static void addRecipe(String input, int stacksize, ItemStack output, float exp) {
        addRecipe((IRecipeInput)(new RecipeInputOreDict(input, stacksize)), output, exp);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output) {
        addRecipe(input, output, 0.0F);
    }

    public static void addRecipe(IRecipeInput input, ItemStack output, float exp) {
        //metalPress.addRecipe(input, output, exp, makeString(output));
        recipeList[0].addRecipe(input, output, exp, makeString(output));
    }

    private static String makeString(ItemStack stack) {
        return stack.getDisplayName();
    }


}
