package de.pnku.more_variants_core.client.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantModelPredicateProvider;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
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
    private static void registerShieldVariantModelPredicateProviders(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            registerShield(MoreVariantHolder.getItem(VariantType.SHIELD, woodType));
        }
    }

    @Inject(method = "registerMoreShieldVariantItemModelPredicates", at = @At("HEAD"), remap = false)
    private static void injectedRegisterMoreShieldVariantItemModelPredicatesAtHead(CallbackInfo ci) {
        registerShieldVariantModelPredicateProviders(WoodTypeHolder.getWoodTypes());
    }
}
