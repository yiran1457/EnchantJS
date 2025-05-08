package net.yiran.ehchantjs.mixins;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.yiran.ehchantjs.IEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.stream.IntStream;

@Mixin(CreativeModeTabs.class)
public class CreativeModeTabsMixin {
    @Inject(
            method = "generateEnchantmentBookTypesOnlyMaxLevel",
            at=@At(value = "HEAD"),
            cancellable = true
    )
    private static void generateEnchantmentBookTypesOnlyMaxLevel(CreativeModeTab.Output pOutput, HolderLookup<Enchantment> pEnchantments, Set<EnchantmentCategory> pCategories, CreativeModeTab.TabVisibility pTabVisibility, CallbackInfo ci){
        pEnchantments
                .listElements()
                .map(Holder::value)
                .filter((enchantment) -> enchantment.allowedInCreativeTab(Items.ENCHANTED_BOOK, pCategories))
                .map((enchantment) -> EnchantedBookItem.createForEnchantment(
                        new EnchantmentInstance(enchantment, ((IEnchantment)enchantment).getRealMaxLevel())
                ))
                .forEach((p_269989_) -> pOutput.accept(p_269989_, pTabVisibility));
        ci.cancel();
    }
    /*
    @Redirect(method = "lambda$generateEnchantmentBookTypesOnlyMaxLevel$39", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"))
    private static int getMaxLevel1(Enchantment instance) {
        return ((IEnchantment) instance).getRealMaxLevel();
    }
    */

    @Inject(method = "generateEnchantmentBookTypesAllLevels",at=@At(value = "HEAD"),
            cancellable = true)
    private static void generateEnchantmentBookTypesAllLevels(CreativeModeTab.Output pOutput, HolderLookup<Enchantment> pEnchantments, Set<EnchantmentCategory> pCategories, CreativeModeTab.TabVisibility pTabVisibility, CallbackInfo ci){
        pEnchantments
                .listElements()
                .map(Holder::value)
                .filter((enchantment) -> enchantment.allowedInCreativeTab(Items.ENCHANTED_BOOK, pCategories))
                .flatMap((enchantment) -> IntStream.rangeClosed(((IEnchantment)enchantment).getRealMinLevel(), ((IEnchantment)enchantment).getRealMaxLevel())
                        .mapToObj((i) -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i)))
                )
                .forEach((itemStack) -> pOutput.accept(itemStack, pTabVisibility));
        ci.cancel();
    }

    /*
    @Redirect(method = "lambda$generateEnchantmentBookTypesAllLevels$43", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"))
    private static int getMaxLevel2(Enchantment instance) {
        return ((IEnchantment) instance).getRealMaxLevel();
    }

    @Redirect(method = "lambda$generateEnchantmentBookTypesAllLevels$43", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMinLevel()I"))
    private static int getMinLevel1(Enchantment instance) {
        return ((IEnchantment) instance).getRealMinLevel();
    }
     */
}
