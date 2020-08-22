package me.djtheredstoner.wideputin.asm.hooks;

import me.djtheredstoner.wideputin.WidePutinMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.glu.Project;

@SuppressWarnings("unused")
public class EntityRendererHook {

    public static void setPerspective(float fovModifier, float farPlaneDistance) {

        if(WidePutinMod.shouldWiden()) {
            Minecraft mc = Minecraft.getMinecraft();

            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            Project.gluPerspective(fovModifier, (float) mc.displayWidth / (float)(WidePutinMod.wideness() / 100) / (float) mc.displayHeight, 0.05F, farPlaneDistance * MathHelper.SQRT_2);
            GlStateManager.matrixMode(5888);
        }
    }
}
