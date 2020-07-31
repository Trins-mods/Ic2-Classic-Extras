package trinsdar.ic2c_extras.util;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.Sprites;
import ic2.core.platform.textures.Sprites.SpriteData;
import ic2.core.platform.textures.Sprites.SpriteInfo;
import ic2.core.platform.textures.Sprites.TextureEntry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.IC2CExtras;
import trinsdar.ic2c_extras.Ic2cExtrasConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Icons {
    private static final Map<Block, IconInfo[]> TEXTURE_MAP = new HashMap<>();
    private static final List<String> blockTextureList = new ArrayList<>();
    private static final IconInfo[] SET_NULL = { s("null2"), s("null2") };
    private static final IconInfo[] SET_INVALID_SIZE = { s("null"), s("null") };

    @SideOnly(Side.CLIENT)
    public static void loadSprites() {
        makeSprites("blocks", 2, 2);
        IconInfo side = s(35);
        IconInfo top = s(22);
        IconInfo bottom = s(3);
        IconInfo common = s(36);
        IconInfo common2 = s(44);
        setTexture(Registry.thermalCentrifuge, bottom, s(19), side, s("ore_washer_front"), side, side, bottom, s(115), side, s("thermal_centrifuge_front_active"), side, side);
        setTexture(Registry.thermalWasher, bottom, s(19), side, s("ore_washer_front"), s("bmach_mv", 140), s("bmach_mv", 140), bottom, s(115), side, s("ore_washer_front_active"), s("bmach_mv",44), s("bmach_mv",44));
        setTexture(Registry.oreWashingPlant, bottom, s(19), side, s("ore_washer_front"), common, common, bottom, s(115), side, s("ore_washer_front_active"), s(43), s(43));
        setTexture(Registry.roller, bottom, s(20), side, s("roller_front"), common, common, bottom, s("roller_top_active"), side, s("roller_front_active"), common2, common2);
        setTexture(Registry.extruder, bottom, s(20), side, s(155), common, common, bottom, s("roller_top_active"), side, s("extruder_front_active_a"), common2, common2);
        setTexture(Registry.cutter, bottom, s(20), side, s("cutter_front"), common, common, bottom, s("roller_top_active"), side, s("cutter_front_active"), common2, common2);
        setTexture(Registry.metalBender, bottom, s(20), side, s("metal_bender_front"), common, common, bottom, s("roller_top_active"), side, s("metal_bender_front_active"), common2, common2);
        common = s("bmach_lv_2", 53);
        setTexture(Registry.fluidCanningMachine, bottom, top, side, s(54), common, common, bottom, top, side, s(150), common, common);
        common = s("tree_tapper_side");
        common2 = s("tree_tapper_side_active");
        setTexture(Registry.treeTapper, bottom, s("bmach_mv", 27), common, common, common, common, bottom, s("bmach_mv", 123), common2, common2, common2, common2);
        setTexture(Registry.impellerizedRoller, bottom, s(20), side, s("roller_front"), s("bmach_mv", 140), s("bmach_mv", 140), bottom, s("roller_top_active"), side, s("roller_front_active"), s("impellerized_roller_side_active"), s("impellerized_roller_side_active"));
        setTexture(Registry.liquescentExtruder, bottom, s(20), side, s(155), s("bmach_mv", 140), s("bmach_mv", 140), bottom, s("roller_top_active"), side, s("extruder_front_active_a"), s("impellerized_roller_side_active"), s("impellerized_roller_side_active"));
        setTexture(Registry.plasmaCutter, bottom, s(20), side, s("cutter_front"), s("bmach_mv", 140), s("bmach_mv", 140), bottom, s("roller_top_active"), side, s("cutter_front_active"), s("impellerized_roller_side_active"), s("impellerized_roller_side_active"));
        common = s("advanced_steam_turbine_side");
        setTexture(Registry.advancedSteamTurbine, s(1), top, common, s("bgen", 58), common, common, s(1), top, common, s("bgen", 160), common, common);
        common = s("bgen", 22);
        setTexture(Registry.thermoElectricGenerator, s(1), top, common, s("bgen", 39), common, common, s(1), top, common, s("bgen", 141), common, common);
        common = s("thermo_electric_generator_mkII_side");
        setTexture(Registry.thermoElectricGeneratorMKII, s(1), top, common, s("bgen", 39), common, common, s(1), top, common, s("bgen", 141), common, common);
        common = s("bmach_hv", 36);
        common2 = s("bmach_hv", 132);
        setTexture(Registry.electricDisenchanter, s("bmach_mv", 0), s("electric_disenchanter_top"), common, common, common, common, s("bmach_mv", 0), s("electric_disenchanter_top"), common2, common2, common2, common2);
        setTexture(Registry.autocraftingTable, s("autocrafting_table_side"), s("autocrafting_table_side"), s("autocrafting_table_back"), s("autocrafting_table_front"), s("autocrafting_table_side"), s("autocrafting_table_side"));
        blockTextureList.add("electric_disenchanter_side_overlay");
        blockTextureList.add("electric_disenchanter_side_active_overlay");
        makeSprites("misc_items", 16, 2);
        makeSprites("rtg", 2, 1);
        makeSprites("crushed_ore", 7, 1);
        makeSprites("purified_crushed_ore", 7, 1);
        makeSprites("item_casings", 16, 1);
        makeSprites("plates", 16, 1);
        makeSprites("materials", 5, 1);
        makeSprites("presses", 16, 2);
        makeSprites("small_dust", 16, 1);
        makeSprites("tiny_dust", 16, 1);
        makeSprites("tools", 6, 1);
        makeSprites("nuclear_cells", 5, 6);
        makeSprites("crops", 1, 1);
        makeSprites("aluminum", 3, 1);
        makeSprites("nickel", 3, 1);
        makeSprites("platinum", 3, 1);
        collectBasicTileSprites();
        Ic2Icons.addCustomTexture("metal_bender_front_active", 0, 0, new ResourceLocation(IC2CExtras.MODID, "animations/metal_bender_front"));
    }

    public static void makeSprites(String name, int maxX, int maxY) {
        String id = IC2CExtras.MODID + "_" + name;
        Ic2Icons.addSprite(new SpriteData(id, "ic2c_extras:textures/sprites/" + name + ".png", new SpriteInfo(maxX, maxY)));
        Ic2Icons.addTextureEntry(new TextureEntry(id, 0, 0, maxX, maxY));
    }

    public static void collectBasicTileSprites() {
        for(String string : blockTextureList) {
            if (Ic2cExtrasConfig.debugMode){
                IC2CExtras.logger.info("Attempting to get sprite data for: " + string);
            }
            Ic2Icons.addSprite(new Sprites.SpriteData(string, "ic2c_extras:textures/sprites/tiles/" + string + ".png", new Sprites.SpriteInfo(1, 1)));
            Ic2Icons.addTextureEntry(new Sprites.TextureEntry(string, 0, 0, 1, 1));
        }

    }

    /**
     * Getting a dynamically generated sprite array for a block
     *
     * @param block - the block to get sprite data for
     * @return - will return the sprite sheet if present or missing gtc texture (set
     *         null)
     */
    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite[] getTextureData(Block block) {
        return TEXTURE_MAP.containsKey(block) ? textureHelper(TEXTURE_MAP.get(block)) : textureHelper(SET_NULL);
    }

    /**
     *
     * @param block  to make textures for
     * @param values the spirte locations for the block texture
     */
    @SideOnly(Side.CLIENT)
    private static void setTexture(Block block, IconInfo... values) {
        TEXTURE_MAP.put(block, values);
    }

    /**
     *
     * @param arr size determines style of texture, 2 = off/on all sides, 6 = all
     *            side but single state, 12 = all sides full state
     * @return the constructed sprite
     */
    @SideOnly(Side.CLIENT)
    private static TextureAtlasSprite[] textureHelper(IconInfo[] arr) {
        if (arr.length == 2) {
            return buildTexture(arr[0], arr[0], arr[0], arr[0], arr[0], arr[0], arr[1], arr[1], arr[1], arr[1], arr[1], arr[1]);
        }
        if (arr.length == 6) {
            return buildTexture(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
        }
        if (arr.length == 12) {
            return buildTexture(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11]);
        }
        return textureHelper(SET_INVALID_SIZE);
    }

    /** How to make a custom texture **/
    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite[] buildTexture(IconInfo... arr) {
        TextureAtlasSprite[] texture = new TextureAtlasSprite[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            texture[i] = arr[i].getSprite();
        }
        return texture;
    }

    public static IconInfo s(String spriteName){
        if (!blockTextureList.contains(spriteName)){
            blockTextureList.add(spriteName);
        }
        return new IconInfo(spriteName);
    }

    public static IconInfo s(int spriteId){
        return new IconInfo(spriteId);
    }

    public static IconInfo s(String spriteName, int spriteId){
        return new IconInfo(spriteName, spriteId);
    }

    public static class IconInfo{
        int spriteId;
        String spriteName;
        public IconInfo(String spriteName, int spriteId){
            this.spriteName = spriteName;
            this.spriteId = spriteId;
        }

        public IconInfo(String spriteName){
            this.spriteName = spriteName;
            this.spriteId = 0;
        }

        public IconInfo(int spriteId){
            this.spriteName = "bmach_lv";
            this.spriteId = spriteId;
        }

        @SideOnly(Side.CLIENT)
        public TextureAtlasSprite getSprite(){
            return Ic2Icons.getTextures(spriteName)[spriteId];
        }
    }
}
