package net.yiran.ehchantjs.mixins;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.yiran.ehchantjs.IEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantRandomlyFunction.class)
public class EnchantRandomlyFunctionMixin {
    @Redirect(method = "enchantItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"))
    private static int getMaxLevel(Enchantment instance) {
        return ((IEnchantment) instance).getRealMaxLevel();
    }

    @Redirect(method = "enchantItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMinLevel()I"))
    private static int getMinLevel(Enchantment instance) {
        return ((IEnchantment) instance).getRealMinLevel();
    }

}
