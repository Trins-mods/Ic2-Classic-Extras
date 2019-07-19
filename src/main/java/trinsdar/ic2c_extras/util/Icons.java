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
        makeTileSprites("thermalcentrifuge");
        makeTileSprites( "thermalwasher");
        makeTileSprites( "orewashingplant");
        makeTileSprites( "roller");
        makeTileSprites( "extruder");
        makeTileSprites( "cutter");
        makeTileSprites( "metalbender");
        makeTileSprites( "fluidcanningmachine");
        makeTileSprites("treetapper");
        makeTileSprites( "impellerizedroller");
        makeTileSprites( "liquescentextruder");
        makeTileSprites( "plasmacutter");
        makeTileSprites( "advancedsteamturbine");
        makeTileSprites("thermoelectricgenerator");
        makeTileSprites("thermoelectricgeneratormkii");
        makeSprites( "misc_items", 16, 2);
        makeSprites("rtg", 2, 1);
        makeSprites( "crushed_ore", 7, 1);
        makeSprites( "purified_crushed_ore", 7, 1);
        makeSprites( "item_casings", 16, 1);
        makeSprites( "plates", 16, 1);
        makeSprites( "materials", 5, 1);
        makeSprites( "presses", 16, 2);
        makeSprites( "small_dust", 16, 1);
        makeSprites("tiny_dust", 16, 1);
        makeSprites("tools", 6, 1);
        makeSprites("nuclear_cells", 5, 6);
        makeSprites("crops", 1, 1);
        Ic2Icons.addCustomTexture("metalbender", 0, 9, new ResourceLocation(IC2CExtras.MODID,"animations/metalbender_front"));
    }

    public static void makeSprites(String name, int maxX, int maxY){
        String id = IC2CExtras.MODID + "_" + name;
        Ic2Icons.addSprite(new SpriteData(id, "ic2c_extras:textures/sprites/" + name + ".png", new SpriteInfo(maxX, maxY)));
        Ic2Icons.addTextureEntry(new TextureEntry(id, 0, 0, maxX, maxY));
    }

    public static void makeTileSprites(String id){
        Ic2Icons.addSprite(new SpriteData(id, "ic2c_extras:textures/sprites/" + id + ".png", new SpriteInfo(1, 12)));
        Ic2Icons.addTextureEntry(new TextureEntry(id, 0, 0, 1, 12));
    }
}
