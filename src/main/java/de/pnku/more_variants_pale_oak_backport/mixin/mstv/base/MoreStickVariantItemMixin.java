package de.pnku.more_variants_pale_oak_backport.mixin.mstv.base;

import de.pnku.more_variants_pale_oak_backport.PaleOakConstants;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakStickHolder;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.blackgear.vanillabackport.common.registries.ModBlocks.PALE_OAK_PLANKS;

@Mixin(MoreStickVariantItem.class)
public abstract class MoreStickVariantItemMixin {
    @Inject(method = "getStickItem", at = @At("HEAD"), cancellable = true)
    private static void injectedGetStickItemAtHead(String woodType, CallbackInfoReturnable<Item> cir) {
        if (PaleOakConstants.WOOD_TYPE.equals(woodType)) {
            Item paleOakStick = PaleOakStickHolder.getItem();
            cir.setReturnValue(paleOakStick);
        }
    }

    @Inject(method = "getPlanksItem", at = @At("HEAD"), cancellable = true)
    private static void injectedGetPlanksItemAtHead(String woodType, CallbackInfoReturnable<Item> cir) {
        if (PaleOakConstants.WOOD_TYPE.equals(woodType)) {
            Item paleOakPlanks = PALE_OAK_PLANKS.get().asItem();
            cir.setReturnValue(paleOakPlanks);
        }
    }
}
