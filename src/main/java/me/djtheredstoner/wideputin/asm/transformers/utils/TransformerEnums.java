package me.djtheredstoner.wideputin.asm.transformers.utils;

import me.djtheredstoner.wideputin.asm.WidePutinTransformer;
import org.objectweb.asm.tree.MethodNode;

public class TransformerEnums {

    public enum TransformerClass {
        EntityRenderer("net/minecraft/client/renderer/EntityRenderer", "bfk"),
        AbstractClientPlayer("net/minecraft/client/entity/AbstractClientPlayer", "bet"),
        ResourceLocation("net/minecraft/util/ResourceLocation", "jy");

        public final String name;
        public final String srgClass;
        public final String notchClass;

        TransformerClass(String srgClass, String notchClass) {
            this.srgClass = srgClass;
            this.notchClass = notchClass;

            if(WidePutinTransformer.debobfuscated) {
                this.name = srgClass;
            } else {
                this.name = notchClass;
            }
        }

        public String getTransformerName() {
            return srgClass.replace("/", ".");
        }
    }

    public enum TransformerMethod {
        //EntityRenderer
        renderWorldPass("renderWorldPass", "func_175068_a", "a", "(IFJ)V"),
        getFOVModifier("getFOVModifier", "func_78481_a", "a", "(FZ)F"),

        //AbstractClientPlayer
        getLocationSkin("getLocationSkin", "func_110306_p", "i", "()Lnet/minecraft/util/ResourceLocation;", "()L"+TransformerClass.ResourceLocation.name+";");

        public final String name;
        public final String description;

        TransformerMethod(String deobfName, String srgName, String notchName, String srgDescription) {
           this(deobfName, srgName, notchName, srgDescription, srgDescription);
        }

        @SuppressWarnings("unused")
        TransformerMethod(String deobfName, String srgName, String notchName, String srgDescription, String notchDescription) {
            if(WidePutinTransformer.debobfuscated) {
                this.name = deobfName;
                this.description = srgDescription;
            } else {
                this.name = notchName;
                this.description = notchDescription;
            }
        }

        public boolean matches(MethodNode methodNode) {
            return this.name.equals(methodNode.name) && (this.description.equals(methodNode.desc));
        }
    }

    public enum TransformerField {
        //EntityRenderer
        farPlaneDistance("farPlaneDistance", "field_78530_s", "k", "F");

        public final String name;
        public final String type;

        TransformerField(String deobfName, String srgName, String notchName, String type) {
            this.type = type;

            if(WidePutinTransformer.debobfuscated) {
                this.name = deobfName;
            } else {
                this.name = notchName;
            }
        }
    }

}
