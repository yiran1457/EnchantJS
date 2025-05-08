package net.yiran.ehchantjs;

import net.minecraft.world.item.ItemStack;

import java.util.function.Function;
import java.util.function.Predicate;

public interface IItem {
    void setRealIsEnchantable(Predicate<ItemStack> realIsEnchantable);
    boolean realIsEnchantable(ItemStack stack);
    void setRealGetEnchantmentValueFn(Function<ItemStack,Double> realGetEnchantmentValueFn);
}
