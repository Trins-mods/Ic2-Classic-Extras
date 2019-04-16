package trinsdar.ic2c_extras.gtintegration;

import gtclassic.GTMod;
import gtclassic.material.GTMaterial;
import gtclassic.ore.GTOreRegistry;
import gtclassic.ore.GTOreStone;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import trinsdar.ic2c_extras.IC2CExtras;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class MaterialGen {
    public static LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();
    public static void init(){
        Set<GTOreRegistry> ores = new HashSet<>();
        ores.addAll(Arrays.asList(GTOreRegistry.CALCITE, GTOreRegistry.CINNABAR, GTOreRegistry.GRAPHITE, GTOreRegistry.ANTHRACITE, GTOreRegistry.OLIVINE, GTOreRegistry.RUBY, GTOreRegistry.SALTPETER, GTOreRegistry.SAPPHIRE, GTOreRegistry.SODALITE, GTOreRegistry.SULFUR, GTOreRegistry.SALT, GTOreRegistry.VIBRANIUM, GTOreRegistry.BISMUTHTINE, GTOreRegistry.CASSITERITE, GTOreRegistry.MAGNETITE, GTOreRegistry.BASALT));
        for (Block ore : Block.REGISTRY){
            if (ore instanceof GTOreStone){
                GTOreStone ore2 = (GTOreStone)ore;
                GTOreRegistry registry = ore2.getOreEntry();
                if (!ores.contains(registry)){
                    createCrushedOre(registry.getMaterial());
                    createPurifiedCrushedOre(registry.getMaterial());
                    createTinyDust(registry.getMaterial());
                }
            }
        }
        createTinyDust(GTMaterial.Alumina);
        createTinyDust(GTMaterial.Platinum);
        createTinyDust(GTMaterial.Nickel);
        createTinyDust(GTMaterial.Zinc);
        createTinyDust(GTMaterial.Tantalum);
        createTinyDust(GTMaterial.Manganese);
        createTinyDust(GTMaterial.Molybdenum);
        createTinyDust(GTMaterial.Osmium);
    }

    public static void localizationUtil(GTMaterial mat) {
        GTMod.logger.info("item.ic2c_extras." + mat.getName() + "TinyDust" + ".name=" + "Tiny Piles of "
                + mat.getDisplayName() + " Dust");
    }
    public static void createCrushedOre(GTMaterial mat) {
        itemMap.put(mat.getName() + "_crushedore", new MaterialItem(mat, "CrushedOre", 7, true));
    }

    public static void createPurifiedCrushedOre(GTMaterial mat) {
        itemMap.put(mat.getName() + "_purifiedcrushedore", new MaterialItem(mat, "PurifiedCrushedOre", 9, false));
    }

    public static void createTinyDust(GTMaterial mat) {
        itemMap.put(mat.getName() + "_tinydust", new MaterialItem(mat, "TinyDust", 10, false));
    }

    public static ItemStack getStack(GTMaterial mat, String suffix, int count) {
        return new ItemStack(itemMap.get(mat.getName() + "_" + suffix), count, 0);
    }
}
