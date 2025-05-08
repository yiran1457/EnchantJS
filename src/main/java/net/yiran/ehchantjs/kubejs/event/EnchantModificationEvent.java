package net.yiran.ehchantjs.kubejs.event;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.yiran.ehchantjs.EnchantJS;
import net.yiran.ehchantjs.IEnchantment;
import net.yiran.ehchantjs.kubejs.IEnchantJSEvents;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,modid = EnchantJS.MODID)
public class EnchantModificationEvent extends StartupEventJS {

    public EnchantModificationEvent() {}

    public void modify(Enchantment enchantment, Consumer<IEnchantment> consumer) {
        consumer.accept((IEnchantment) enchantment);
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        IEnchantJSEvents.MODIFY.post(new EnchantModificationEvent());
    }
}
