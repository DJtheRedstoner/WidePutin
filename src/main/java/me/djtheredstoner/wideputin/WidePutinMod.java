package me.djtheredstoner.wideputin;

import me.djtheredstoner.wideputin.commands.WidePutinCommand;
import me.djtheredstoner.wideputin.config.WidePutinConfig;
import me.djtheredstoner.wideputin.listeners.WidePutinListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "wideputin", name = "Wide Putin", version = "1.0", clientSideOnly = true)
public class WidePutinMod {

    public static WidePutinConfig widePutinConfig;

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {

        WidePutinMod.widePutinConfig = new WidePutinConfig();
        widePutinConfig.preload();

        MinecraftForge.EVENT_BUS.register(new WidePutinListener());

        ClientCommandHandler.instance.registerCommand(new WidePutinCommand());
    }

    public static boolean isEnabled() {
        return widePutinConfig.enabled;
    }

    public static boolean shouldWiden() {
        return isEnabled() && widePutinConfig.widen;
    }

    public static int wideness() {
        return widePutinConfig.wideness;
    }

    public static boolean changeSkin() {
        return isEnabled() && widePutinConfig.skin;
    }

    public static boolean changeSkinOthers() {
        return isEnabled() && widePutinConfig.skinOthers;
    }

    public static boolean playMusic() {
        return isEnabled() && widePutinConfig.music;
    }
}
