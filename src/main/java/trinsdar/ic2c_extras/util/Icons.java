package trinsdar.ic2c_extras.util;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.Sprites.SpriteData;
import ic2.core.platform.textures.Sprites.SpriteInfo;
import ic2.core.platform.textures.Sprites.TextureEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;


public class Icons
{

    @SideOnly(Side.CLIENT)
    public static void loadSprites()
    {
        makeSprites("blocks", 2, 2);
        makeTileSprites("thermalcentrifuge", 1, 12);
        makeTileSprites( "thermalwasher", 1, 12);
        makeTileSprites( "orewashingplant", 1, 12);
        makeTileSprites( "roller", 1, 12);
        makeTileSprites( "extruder", 1, 12);
        makeTileSprites( "cutter", 1, 12);
        makeTileSprites( "metalbender", 1, 12);
        makeTileSprites( "fluidcanningmachine", 1, 12);
        makeTileSprites("treetapper", 1, 12);
        makeTileSprites( "impellerizedroller", 1, 12);
        makeTileSprites( "liquescentextruder", 1, 12);
        makeTileSprites( "plasmacutter", 1, 12);
        makeTileSprites( "advancedsteamturbine", 1, 12);
        makeTileSprites( "blastfurnace", 1, 12);
        makeTileSprites( "solidfuelfirebox", 1, 12);
        makeTileSprites( "liquidfuelfirebox", 1, 12);
        makeTileSprites("electricheater", 1, 12);
        makeSprites( "misc_items", 16, 2);
        makeSprites( "crushed_ore", 7, 1);
        makeSprites( "purified_crushed_ore", 7, 1);
        makeSprites( "item_casings", 16, 1);
        makeSprites( "plates", 16, 1);
        makeSprites( "materials", 5, 1);
        makeSprites( "presses", 16, 2);
        makeSprites( "small_dust", 16, 1);
        makeSprites("tiny_dust", 16, 1);
        makeSprites("tools", 6, 1);
        Ic2Icons.addCustomTexture("metalbender", 0, 9, new ResourceLocation(IC2CExtras.MODID,"animations/metalbender_front"));
    }

    public static void makeSprites(String name, int maxX, int maxY){
        String id = IC2CExtras.MODID + "_" + name;
        Ic2Icons.addSprite(new SpriteData(id, "ic2c_extras:textures/sprites/" + name + ".png", new SpriteInfo(maxX, maxY)));
        Ic2Icons.addTextureEntry(new TextureEntry(id, 0, 0, maxX, maxY));
    }

    public static void makeTileSprites(String id, int maxX, int maxY){
        Ic2Icons.addSprite(new SpriteData(id, "ic2c_extras:textures/sprites/" + id + ".png", new SpriteInfo(maxX, maxY)));
        Ic2Icons.addTextureEntry(new TextureEntry(id, 0, 0, maxX, maxY));
    }
}
