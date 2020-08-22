package me.djtheredstoner.wideputin.asm;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.djtheredstoner.wideputin.asm.transformers.AbstractClientPlayerTransformer;
import me.djtheredstoner.wideputin.asm.transformers.EntityRendererTransformer;
import me.djtheredstoner.wideputin.asm.transformers.ITransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

public class WidePutinTransformer implements IClassTransformer {

    private Logger logger = LogManager.getLogger("Wide Putin Transformer");
    private final Multimap<String, ITransformer> transformerMap = ArrayListMultimap.create();

    public static final boolean outputBytecode;
    public static final boolean debobfuscated;

    static {
        outputBytecode = Boolean.parseBoolean(System.getProperty("debugBytecode", "false"));

        debobfuscated = (boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    public WidePutinTransformer() {
        registerTransformer(new EntityRendererTransformer());
        registerTransformer(new AbstractClientPlayerTransformer());
    }

    private void registerTransformer(ITransformer transformer) {
        for (String cls : transformer.getClassName()) {
            transformerMap.put(cls, transformer);
        }
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) return null;

        Collection<ITransformer> transformers = transformerMap.get(transformedName);
        if (transformers.isEmpty()) return bytes;

        logger.info("Found {} transformers for {}", transformers.size(), transformedName);

        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, ClassReader.EXPAND_FRAMES);

        MutableInt classWriterFlags = new MutableInt(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        transformers.forEach(transformer -> {
            logger.info("Applying transformer {} on {}...", transformer.getClass().getName(), transformedName);
            transformer.transform(node, transformedName);
        });

        ClassWriter writer = new ClassWriter(classWriterFlags.getValue());

        try {
            node.accept(writer);
        } catch (Throwable t) {
            logger.error("Exception when transforming " + transformedName + " : " + t.getClass().getSimpleName());
            t.printStackTrace();
        }

        if (WidePutinTransformer.outputBytecode) {
            final File bytecodeDirectory = new File("bytecode");
            String transformedClassName;

            if (transformedName.contains("$")) {
                transformedClassName = transformedName.replace('$', '.') + ".class";
            }
            else {
                transformedClassName = transformedName + ".class";
            }

            final File bytecodeOutput = new File(bytecodeDirectory, transformedClassName);

            try (final FileOutputStream os = new FileOutputStream(bytecodeOutput)) {
                os.write(writer.toByteArray());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return writer.toByteArray();
    }
}
