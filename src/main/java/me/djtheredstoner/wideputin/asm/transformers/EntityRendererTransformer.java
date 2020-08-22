package me.djtheredstoner.wideputin.asm.transformers;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

import static org.objectweb.asm.Opcodes.*;

public class EntityRendererTransformer implements ITransformer{

    @Override
    public String[] getClassName() {
        return new String[]{"net.minecraft.client.renderer.EntityRenderer"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for(MethodNode method : classNode.methods) {
            String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, method.name, method.desc);

            if(methodName.equals("renderWorldPass") || methodName.equals("func_175068_a")) {

                Iterator<AbstractInsnNode> iterator = method.instructions.iterator();

                while (iterator.hasNext()) {
                    AbstractInsnNode next = iterator.next();

                    if (next instanceof LdcInsnNode && ((LdcInsnNode) next).cst.equals("prepareterrain")) {
                        method.instructions.insertBefore(next.getPrevious().getPrevious().getPrevious(), insertWidthHook());
                    }
                }
            }
        }
    }

    private InsnList insertWidthHook() {
        InsnList list = new InsnList();

        list.add(new VarInsnNode(ALOAD, 0));
        list.add(new VarInsnNode(FLOAD, 2));
        list.add(new InsnNode(ICONST_1));
        list.add(new MethodInsnNode(INVOKESPECIAL, "net/minecraft/client/renderer/EntityRenderer", "func_78481_a", "(FZ)F", false));
        list.add(new VarInsnNode(ALOAD, 0));
        list.add(new FieldInsnNode(GETFIELD, "net/minecraft/client/renderer/EntityRenderer", "field_78530_s", "F"));
        list.add(new MethodInsnNode(INVOKESTATIC, "me/djtheredstoner/wideputin/asm/hooks/EntityRendererHook", "setPerspective", "(FF)V", false));

        return list;
    }
}
