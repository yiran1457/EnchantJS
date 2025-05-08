package net.yiran.ehchantjs.mixins;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.yiran.ehchantjs.IEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.function.Predicate;


@Mixin(Enchantment.class)
public abstract class EnchantmentMixin implements IEnchantment {

    @Shadow
    public abstract int getMaxLevel();

    @Shadow
    public abstract int getMinLevel();

    @Unique
    private int realMaxLevel;

    @Unique
    private boolean maxLevelIsModify = false;

    @Unique
    private int realMinLevel;

    @Unique
    private boolean minLevelIsModify = false;

    @Redirect(method = "getFullname", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"))
    private int getMaxLevel1(Enchantment instance) {
        return ((IEnchantment) instance).getRealMaxLevel();
    }

    @Unique
    @Nullable
    private Predicate<ItemStack> canApplyAtEnchantingTableFn;

    @Inject(method = "canApplyAtEnchantingTable",remap = false,at=@At(value = "HEAD"),cancellable = true)
    private void canApplyAtEnchantingTableHead(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        if (canApplyAtEnchantingTableFn != null) {
            cir.setReturnValue(canApplyAtEnchantingTableFn.test(stack));
        }
    }

    public void setCanApplyAtEnchantingTableFn(Predicate<ItemStack> canApplyAtEnchantingTableFn) {
        this.canApplyAtEnchantingTableFn = canApplyAtEnchantingTableFn;
    }


    @Unique
    @Nullable
    private Predicate<ItemStack> canEnchantFn;

    @Inject(method = "canEnchant",at=@At(value = "HEAD"),cancellable = true)
    private void canEnchantHead(ItemStack pStack, CallbackInfoReturnable<Boolean> cir){
        if (canEnchantFn != null) {
            cir.setReturnValue(canEnchantFn.test(pStack));
        }
    }

    public void setCanEnchantFn(Predicate<ItemStack> canEnchantFn) {
        this.canEnchantFn = canEnchantFn;
    }


    @Shadow
    public EnchantmentCategory category;

    public void setCategory(Predicate<Item> delegate) {
        this.category = EnchantmentCategory.create(this.getOrCreateDescriptionId(), delegate);
    }


    @Shadow
    public Enchantment.Rarity rarity;

    @Shadow protected abstract String getOrCreateDescriptionId();

    public void setRarity(Enchantment.Rarity rarity) {
        this.rarity = rarity;
    }


    public int getRealMaxLevel() {
        return maxLevelIsModify ? realMaxLevel : getMaxLevel();
    }

    public void setRealMaxLevel(int realMaxLevel) {
        this.maxLevelIsModify = true;
        this.realMaxLevel = realMaxLevel;
    }

    public int getRealMinLevel() {
        return minLevelIsModify ? realMinLevel : getMinLevel();
    }

    public void setRealMinLevel(int realMinLevel) {
        this.minLevelIsModify = true;
        this.realMinLevel = realMinLevel;
    }
}
