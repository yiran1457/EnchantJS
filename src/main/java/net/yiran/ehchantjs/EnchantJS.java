package net.yiran.ehchantjs;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(EnchantJS.MODID)
public class EnchantJS {
    public static final String MODID = "enchantjs";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EnchantJS() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

}
