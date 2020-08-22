package me.djtheredstoner.wideputin.commands;

import me.djtheredstoner.wideputin.WidePutinMod;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class WidePutinCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "wideputin";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/wideputin - open the config gui";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if(e.phase == TickEvent.Phase.START) {
            Minecraft.getMinecraft().displayGuiScreen(WidePutinMod.widePutinConfig.gui());
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
