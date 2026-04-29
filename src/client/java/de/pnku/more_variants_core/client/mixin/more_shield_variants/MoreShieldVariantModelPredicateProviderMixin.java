package de.pnku.more_variants_core.client.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantModelPredicateProvider;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
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
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Invoker("registerShield")
    public static void invokeRegisterShield(Item shield) {
        throw new AssertionError();
    }

    @Inject(method = "registerMoreShieldVariantItemModelPredicates", at = @At("HEAD"), remap = false)
    private static void injectedRegisterMoreShieldVariantItemModelPredicatesAtHead(CallbackInfo ci) {
        invokeRegisterShield(MoreVariantHolder.getItem(VariantType.SHIELD, WOOD_TYPE));
    }
}
