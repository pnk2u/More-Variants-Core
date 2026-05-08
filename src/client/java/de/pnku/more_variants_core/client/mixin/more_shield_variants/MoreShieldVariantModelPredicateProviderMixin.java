package de.pnku.more_variants_core.client.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantModelPredicateProvider;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MoreShieldVariantModelPredicateProvider.class)
public abstract class MoreShieldVariantModelPredicateProviderMixin {
    @Shadow
    private static void registerShield(Item shield) {
        throw new AssertionError();
    }

    @Unique
    private static void registerShieldVariantModelPredicateProviders(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            registerShield(MoreVariantHolder.getItem(MoreVariantType.SHIELD, woodType));
        }
    }

    @Inject(method = "registerMoreShieldVariantItemModelPredicates", at = @At("HEAD"), remap = false)
    private static void injectedRegisterMoreShieldVariantItemModelPredicatesAtHead(CallbackInfo ci) {
        registerShieldVariantModelPredicateProviders(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
