package me.djtheredstoner.wideputin.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class WidePutinLoadingPlugin implements IFMLLoadingPlugin
{
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{WidePutinTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
