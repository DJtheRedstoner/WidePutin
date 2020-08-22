package me.djtheredstoner.wideputin.asm.hooks;

import me.djtheredstoner.wideputin.WidePutinMod;
import me.djtheredstoner.wideputin.config.WidePutinConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;

public class AbstractClientPlayerHook {

    public static final ResourceLocation putinSkin = new ResourceLocation("wideputin", "putin.png");

    public static ResourceLocation getLocationSkin(AbstractClientPlayer player) {
        if(Minecraft.getMinecraft().thePlayer == player) {
            return WidePutinMod.changeSkin() ? putinSkin : null;
        } else {
            return WidePutinMod.changeSkinOthers() ? putinSkin : null;
        }
    }
}
