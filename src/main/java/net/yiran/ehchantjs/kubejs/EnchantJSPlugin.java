package net.yiran.ehchantjs.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;

public class EnchantJSPlugin extends KubeJSPlugin {
    @Override
    public void registerEvents() {
        IEnchantJSEvents.GROUP.register();
    }
}
