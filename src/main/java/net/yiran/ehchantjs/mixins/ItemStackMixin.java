package net.yiran.ehchantjs.mixins;

import net.minecraft.world.item.ItemStack;
import net.yiran.ehchantjs.IItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true)
    private void isEnchantable(CallbackInfoReturnable<Boolean> cir) {
        if (!((IItem) ((ItemStack) (Object) this).getItem()).realIsEnchantable(((ItemStack) (Object) this))) {
            cir.setReturnValue(false);
        } else {
            cir.setReturnValue(!((ItemStack) (Object) this).isEnchanted());
        }
    }
}
