package me.djtheredstoner.wideputin.asm.transformers;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

import static org.objectweb.asm.Opcodes.*;

public class AbstractClientPlayerTransformer implements ITransformer{
    @Override
    public String[] getClassName() {
        return new String[]{"net.minecraft.client.entity.AbstractClientPlayer"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for(MethodNode method : classNode.methods) {
            String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, method.name, method.desc);

            if(methodName.equals("getLocationSkin") || methodName.equals("func_110306_p")) {

                Iterator<AbstractInsnNode> iterator = method.instructions.iterator();

                while(iterator.hasNext()) {
                    AbstractInsnNode next = iterator.next();

                    if(next.getOpcode() == ASTORE && ((VarInsnNode) next).var == 1) {
                        method.instructions.insert(next, insertLocationSkinHook());
                    }
                }
            }
        }
    }

    public InsnList insertLocationSkinHook() {
        InsnList list = new InsnList();

        list.add(new VarInsnNode(ALOAD, 0));
        list.add(new MethodInsnNode(INVOKESTATIC, "me/djtheredstoner/wideputin/asm/hooks/AbstractClientPlayerHook", "getLocationSkin", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;", false));
        list.add(new InsnNode(DUP));
        LabelNode label = new LabelNode();
        LabelNode label2 = new LabelNode();
        list.add(new JumpInsnNode(IFNONNULL, label));
        list.add(new JumpInsnNode(GOTO, label2));
        list.add(label);
        list.add(new InsnNode(ARETURN));
        list.add(label2);

        return list;
    }
}
