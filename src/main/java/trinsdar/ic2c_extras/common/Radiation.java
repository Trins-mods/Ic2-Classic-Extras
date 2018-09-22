package trinsdar.ic2c_extras.common;

import ic2.api.info.Info;
import ic2.core.entity.IC2DamageSource;
import ic2.core.entity.IC2Potion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import trinsdar.ic2c_extras.Ic2cExtras;
import trinsdar.ic2c_extras.common.util.Icons;

import java.util.List;

public class Radiation
{
//    public static Radiation radiation;
//
//    private static final ResourceLocation ICON = new ResourceLocation(Ic2cExtras.MODID, "textures/guisprites/radiation_icon");
//    private final java.util.List<ItemStack> effectedItems;
//
//    public Radiation(boolean isBadEffect, int liquidColour, ItemStack... effectedItems){
//        super(isBadEffect, liquidColour);
//        this.setPotionName("potion." + Ic2cExtras.MODID + ":radiation");
//        this.effectedItems = java.util.Arrays.asList(effectedItems);
//
//    }
//
//    public void performEffect(EntityLivingBase entity, int amplifier) {
//        if (this == radiation) {
//            entity.attackEntityFrom(IC2DamageSource.radiation, (float)(amplifier + 1));
//        }
//
//    }
//
//    @Override
//    public boolean isReady(int p_76397_1_, int p_76397_2_){
//        // Copied from the vanilla wither effect. It does the timing stuff. 25 is the number of ticks between hits at
//        // amplifier 0
//        int k = 40 >> p_76397_2_;
//        return k > 0 ? p_76397_1_ % k == 0 : true;
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc){
//        mc.renderEngine.bindTexture(ICON);
//        Icons.drawTexturedRect(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha){
//        mc.renderEngine.bindTexture(ICON);
//        Icons.drawTexturedRect(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
//    }
//
//    public static void init()
//    {
//        Info.POTION_RADIATION = radiation = new Radiation(true, 5149489);
//        Info.POTION_RADIATION.setRegistryName(new ResourceLocation("ic2c_extras", "radiation"));
//        ForgeRegistries.POTIONS.register(radiation);
//        radiation.setPotionName("potion.radiation.name");
//        radiation.setIconIndex(6, 0);
//        radiation.setEffectiveness(0.25D);
//    }

    EntityPlayer player;
    public EntityPlayer getPlayer() {
    return this.player;
}


    if !player.isCreative(){}

}
