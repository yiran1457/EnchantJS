package net.yiran.ehchantjs;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.function.Predicate;

public interface IEnchantment {
    int getRealMaxLevel();
    void setRealMaxLevel(int realMaxLevel);

    int getRealMinLevel();
    void setRealMinLevel(int realMinLevel);

    void setCategory(Predicate<Item> delegate);
    void setRarity(Enchantment.Rarity rarity);
    void setCanEnchantFn(Predicate<ItemStack> canEnchantFn);
    void setCanApplyAtEnchantingTableFn(Predicate<ItemStack> canApplyAtEnchantingTableFn);
}
