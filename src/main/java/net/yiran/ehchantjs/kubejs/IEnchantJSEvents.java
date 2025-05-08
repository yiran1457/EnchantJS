package net.yiran.ehchantjs.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.yiran.ehchantjs.kubejs.event.EnchantModificationEvent;

public interface IEnchantJSEvents {
    EventGroup GROUP = EventGroup.of("EnchantJSEvent");

    EventHandler MODIFY = GROUP.startup("modification",()-> EnchantModificationEvent.class);


}
