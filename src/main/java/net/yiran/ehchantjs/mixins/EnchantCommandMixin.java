package net.yiran.ehchantjs.mixins;

import net.minecraft.server.commands.EnchantCommand;
import net.minecraft.world.item.enchantment.Enchantment;
import net.yiran.ehchantjs.IEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantCommand.class)
public class EnchantCommandMixin {
    @Redirect(method = "enchant",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"))
    private static int getMaxLevel(Enchantment instance) {
        return ((IEnchantment) instance).getRealMaxLevel();
    }
}
