package trinsdar.ic2c_extras.gtintegration;

import gtclassic.GTOreRegistry;
import gtclassic.block.GTBlockOreStone;
import gtclassic.material.GTMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class MaterialGen {
    public static LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();
    public static void init(){
        Set<GTOreRegistry> ores = new HashSet<>();
        ores.addAll(Arrays.asList(GTOreRegistry.CALCITE, GTOreRegistry.CINNABAR, GTOreRegistry.GRAPHITE, GTOreRegistry.ANTHRACITE, GTOreRegistry.OLIVINE, GTOreRegistry.RUBY, GTOreRegistry.SALTPETER, GTOreRegistry.SAPPHIRE, GTOreRegistry.SODALITE, GTOreRegistry.SULFUR, GTOreRegistry.SALT, GTOreRegistry.VIBRANIUM));
        for (Block ore : Block.REGISTRY){
            if (ore instanceof GTBlockOreStone){
                GTBlockOreStone ore2 = (GTBlockOreStone)ore;
                GTOreRegistry registry = ore2.getOreEntry();
                if (!ores.contains(registry)){
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
