package net.yiran.ehchantjs.mixins;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;
import net.yiran.ehchantjs.IItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Predicate;

@Mixin(Item.class)
public abstract class ItemMixin implements IForgeItem, IItem {
    @Shadow public abstract boolean isEnchantable(ItemStack pStack);

    @Nullable
    private Function<ItemStack,Double> realGetEnchantmentValueFn;

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        if (realGetEnchantmentValueFn != null) {
            return realGetEnchantmentValueFn.apply(stack).intValue();
        }else {
            return IForgeItem.super.getEnchantmentValue(stack);
        }
    }

    public void setRealGetEnchantmentValueFn(Function<ItemStack,Double> realGetEnchantmentValueFn) {
        this.realGetEnchantmentValueFn = realGetEnchantmentValueFn;
    }

    @Nullable
    private Predicate<ItemStack> realIsEnchantableFn;

    public boolean realIsEnchantable(ItemStack stack) {
        if (realIsEnchantableFn != null) {
            return realIsEnchantableFn.test(stack);
        }else {
            return isEnchantable(stack);
        }
    }

    public void setRealIsEnchantable(Predicate<ItemStack> realIsEnchantable) {
        this.realIsEnchantableFn = realIsEnchantable;
    }
}
