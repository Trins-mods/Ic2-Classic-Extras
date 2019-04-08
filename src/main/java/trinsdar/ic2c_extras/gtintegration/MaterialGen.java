package trinsdar.ic2c_extras.gtintegration;

import gtclassic.GTBlocks;
import gtclassic.GTOreRegistry;
import gtclassic.block.GTBlockOreStone;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.util.Registry;

import java.util.LinkedHashMap;

public class MaterialGen {
    public static LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();
    public static void init(){
        for (Block ore : Block.REGISTRY){
            System.out.println("getting blocks");
            if (ore instanceof GTBlockOreStone){
                GTBlockOreStone ore2 = (GTBlockOreStone)ore;
                GTOreRegistry registry = ore2.getOreEntry();
                System.out.println("found a gt ore");
                if (registry != GTOreRegistry.CALCITE && registry != GTOreRegistry.CINNABAR && registry != GTOreRegistry.GRAPHITE && registry != GTOreRegistry.IRIDIUM && registry != GTOreRegistry.ANTHRACITE && registry != GTOreRegistry.OLIVINE && registry != GTOreRegistry.RUBY && registry != GTOreRegistry.SALTPETER && registry != GTOreRegistry.SAPPHIRE && registry != GTOreRegistry.SODALITE && registry != GTOreRegistry.SULFUR && registry != GTOreRegistry.SALT && registry != GTOreRegistry.VIBRANIUM){
                    createCrushedOre(ore2.getOreEntry().getMaterial());
                    createPurifiedCrushedOre(ore2.getOreEntry().getMaterial());
                }
            }
        }
    }
    public static void createCrushedOre(GTMaterial mat) {
        itemMap.put(mat.getName() + "_crushedore", new MaterialItem(mat, "CrushedOre", 7, true));
    }

    public static void createPurifiedCrushedOre(GTMaterial mat) {
        itemMap.put(mat.getName() + "_purifiedcrushedore", new MaterialItem(mat, "PurifiedCrushedOre", 9, false));
    }

    public static ItemStack getStack(GTMaterial mat, String suffix, int count) {
        return new ItemStack(itemMap.get(mat.getName() + "_" + suffix), count, 0);
    }
}
