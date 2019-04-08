package trinsdar.ic2c_extras.gtintegration;

import gtclassic.GTBlocks;
import gtclassic.block.GTBlockOreStone;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.LinkedHashMap;

public class MaterialGen {
    public static LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();
    public static void init(){
        for (Block ore : Block.REGISTRY){
            System.out.println("getting blocks");
            if (ore instanceof GTBlockOreStone){
                System.out.println("found a gt ore");
                createCrushedOre(((GTBlockOreStone) ore).getOreEntry().getMaterial());
                createPurifiedCrushedOre(((GTBlockOreStone) ore).getOreEntry().getMaterial());
            }
        }
    }
    public static void createCrushedOre(GTMaterial mat) {
        itemMap.put(mat.getName() + "_crushedore", new MaterialItem(mat, "CrushedOre", 7, true));
    }

    public static void createPurifiedCrushedOre(GTMaterial mat) {
        itemMap.put(mat.getName() + "_purified", new MaterialItem(mat, "PurifiedCrushedOre", 9, false));
    }
}
