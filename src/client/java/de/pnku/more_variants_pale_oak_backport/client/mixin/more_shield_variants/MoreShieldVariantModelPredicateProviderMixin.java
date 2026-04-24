package de.pnku.more_variants_pale_oak_backport.client.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantModelPredicateProvider;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MoreShieldVariantModelPredicateProvider.class)
public abstract class MoreShieldVariantModelPredicateProviderMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Invoker("registerShield")
    public static void invokeRegisterShield(Item shield) {
        throw new AssertionError();
    }

    @Inject(method = "registerMoreShieldVariantItemModelPredicates", at = @At("HEAD"), remap = false)
    private static void injectedRegisterMoreShieldVariantItemModelPredicatesAtHead(CallbackInfo ci) {
        invokeRegisterShield(PaleOakVariantHolder.getItem(PaleOakVariantHolder.SHIELD_FAMILY, WOOD_TYPE));
    }
}
