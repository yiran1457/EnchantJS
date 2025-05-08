package net.yiran.ehchantjs.mixins;

import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.enchantment.Enchantment;
import net.yiran.ehchantjs.IEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerTrades.EnchantBookForEmeralds.class)
public class EnchantBookForEmeraldsMixin {
    @Redirect(method = "getOffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"))
    private int getMaxLevel(Enchantment instance) {
        return ((IEnchantment) instance).getRealMaxLevel();
    }

    @Redirect(method = "getOffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMinLevel()I"))
    private int getMinLevel(Enchantment instance) {
        return ((IEnchantment) instance).getRealMinLevel();
    }
}
