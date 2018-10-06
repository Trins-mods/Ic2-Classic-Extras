package trinsdar.ic2c_extras.common.util;

import ic2.core.platform.registry.ItemAPI;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import trinsdar.ic2c_extras.common.items.RegistryItem;

import java.util.HashMap;
import java.util.Map;

public class Ic2cExtrasItemApi extends ItemAPI {
    Map<ResourceLocation, ItemStack> stacks = new HashMap();
    @Override
    public void init() {
        super.init();
        this.putItem("misc_resource", "iridium_shard", new ItemStack(RegistryItem.itemMiscs, 1, 12));
        this.putItem("misc_resource", "slag", new ItemStack(RegistryItem.itemMiscs, 1, 3));
        this.putItem("crafting", "coil", new ItemStack(RegistryItem.itemMiscs, 1, 7));
        this.putItem("crafting", "heat_conductor", new ItemStack(RegistryItem.itemMiscs, 1, 8));
        this.putItem("crushed", "iron", new ItemStack(RegistryItem.crushedOres, 1, 0));
        this.putItem("crushed", "gold", new ItemStack(RegistryItem.crushedOres, 1, 1));
        this.putItem("crushed", "copper", new ItemStack(RegistryItem.crushedOres, 1, 2));
        this.putItem("crushed", "tin", new ItemStack(RegistryItem.crushedOres, 1, 3));
        this.putItem("crushed", "silver", new ItemStack(RegistryItem.crushedOres, 1, 4));
        this.putItem("crushed", "uranium", new ItemStack(RegistryItem.crushedOres, 1, 5));
        this.putItem("crushed", "lead", new ItemStack(RegistryItem.crushedOres, 1, 6));
        this.putItem("purified", "iron", new ItemStack(RegistryItem.purifiedCrushedOres, 1, 0));
        this.putItem("purified", "gold", new ItemStack(RegistryItem.purifiedCrushedOres, 1, 1));
        this.putItem("purified", "copper", new ItemStack(RegistryItem.purifiedCrushedOres, 1, 2));
        this.putItem("purified", "tin", new ItemStack(RegistryItem.purifiedCrushedOres, 1, 3));
        this.putItem("purified", "silver", new ItemStack(RegistryItem.purifiedCrushedOres, 1, 4));
        this.putItem("purified", "uranium", new ItemStack(RegistryItem.purifiedCrushedOres, 1, 5));
        this.putItem("purified", "lead", new ItemStack(RegistryItem.purifiedCrushedOres, 1, 6));
        this.putItem("dust", "stone", new ItemStack(RegistryItem.itemMiscs, 1, 1));
        this.putItem("dust", "lead", new ItemStack(RegistryItem.itemMiscs, 1, 2));
        this.putItem("dust", "small_iron", new ItemStack(RegistryItem.tinyDustTypes, 1,0));
        this.putItem("dust", "small_bronze", new ItemStack(RegistryItem.tinyDustTypes, 1,8));
        this.putItem("dust", "small_copper", new ItemStack(RegistryItem.tinyDustTypes, 1,2));
        this.putItem("dust", "small_gold", new ItemStack(RegistryItem.tinyDustTypes, 1,1));
        this.putItem("dust", "small_lead", new ItemStack(RegistryItem.tinyDustTypes, 1,6));
        this.putItem("dust", "small_obsidian", new ItemStack(RegistryItem.tinyDustTypes, 1,7));
        this.putItem("dust", "small_silver", new ItemStack(RegistryItem.tinyDustTypes, 1,4));
        this.putItem("dust", "small_tin", new ItemStack(RegistryItem.tinyDustTypes, 1,3));
        this.putItem("ingot", "steel", new ItemStack(RegistryItem.itemMiscs, 1, 9));
        this.putItem("ingot", "lead", new ItemStack(RegistryItem.itemMiscs, 1, 0));
        this.putItem("casing","bronze", new ItemStack(RegistryItem.itemCasings, 1, 8));
        this.putItem("casing","copper", new ItemStack(RegistryItem.itemCasings, 1, 0));
        this.putItem("casing","tin", new ItemStack(RegistryItem.itemCasings, 1, 1));
        this.putItem("casing","lead", new ItemStack(RegistryItem.itemCasings, 1, 3));
        this.putItem("casing","iron", new ItemStack(RegistryItem.itemCasings, 1, 4));
        this.putItem("casing","gold", new ItemStack(RegistryItem.itemCasings, 1, 5));
        this.putItem("casing","steel", new ItemStack(RegistryItem.itemCasings, 1, 7));
        this.putItem("nuclear", "uranium_235", new ItemStack(RegistryItem.itemMiscs, 1, 4));
        this.putItem("nuclear", "uranium_238", new ItemStack(RegistryItem.itemMiscs, 1, 5));
        this.putItem("nuclear", "plutonium", new ItemStack(RegistryItem.itemMiscs, 1, 6));
        this.putItem("nuclear", "small_uranium_235", new ItemStack(RegistryItem.tinyDustTypes, 1,5));
        this.putItem("nuclear", "small_uranium_238", new ItemStack(RegistryItem.tinyDustTypes, 1,9));
        this.putItem("nuclear", "small_plutonium", new ItemStack(RegistryItem.tinyDustTypes, 1,10));
    }

    private void putItem(String key, String value, ItemStack stack) {
        this.stacks.put(new ResourceLocation(key, value), stack.copy());
    }
}
