package de.pnku.more_variants_core.mixin.mstv.base;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MoreStickVariantItem.class)
public abstract class MoreStickVariantItemMixin {
    @Inject(method = "getStickItem", at = @At("HEAD"), cancellable = true)
    private static void injectedGetStickItemAtHead(String woodTypeName, CallbackInfoReturnable<Item> cir) {
        MoreVariantWoodType woodType = MoreVariantWoodTypeHolder.getWoodTypeByName(woodTypeName);
        if (woodType != null) {
            Item stickItem = MoreVariantHolder.getItem(MoreVariantHolder.MoreVariantType.STICK, woodType);
            cir.setReturnValue(stickItem);
        }
    }

    @Inject(method = "getPlanksItem", at = @At("HEAD"), cancellable = true)
    private static void injectedGetPlanksItemAtHead(String woodTypeName, CallbackInfoReturnable<Item> cir) {
        MoreVariantWoodType woodType = MoreVariantWoodTypeHolder.getWoodTypeByName(woodTypeName);
        if (woodType != null) {
            Item planksItem = woodType.getPlanksItem();
            cir.setReturnValue(planksItem);
        }
    }
}
