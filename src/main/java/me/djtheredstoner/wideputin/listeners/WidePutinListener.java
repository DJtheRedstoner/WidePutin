package me.djtheredstoner.wideputin.listeners;

import me.djtheredstoner.wideputin.WidePutinMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class WidePutinListener {

    public ResourceLocation widePutinMusicLocation = new ResourceLocation("wideputin", "putin_music");
    public ISound widePutinMusic = PositionedSoundRecord.create(widePutinMusicLocation);

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        SoundHandler soundHandler = mc.getSoundHandler();
        if(WidePutinMod.playMusic()) {
            if (mc.theWorld != null && !soundHandler.isSoundPlaying(widePutinMusic)) {
                soundHandler.playSound(widePutinMusic);
            }
        } else if(soundHandler.isSoundPlaying(widePutinMusic)) {
            soundHandler.stopSound(widePutinMusic);
        }
    }

}
