package de.pnku.more_variants_core.mixin.mstv.base;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.blackgear.vanillabackport.common.registries.ModBlocks.PALE_OAK_PLANKS;

@Mixin(MoreStickVariantItem.class)
public abstract class MoreStickVariantItemMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Inject(method = "getStickItem", at = @At("HEAD"), cancellable = true)
    private static void injectedGetStickItemAtHead(String woodType, CallbackInfoReturnable<Item> cir) {
        if (WOOD_TYPE.getName().equals(woodType)) {
            Item paleOakStick = MoreVariantHolder.getItem(VariantType.STICK, WOOD_TYPE);
            cir.setReturnValue(paleOakStick);
        }
    }

    @Inject(method = "getPlanksItem", at = @At("HEAD"), cancellable = true)
    private static void injectedGetPlanksItemAtHead(String woodType, CallbackInfoReturnable<Item> cir) {
        if (WOOD_TYPE.getName().equals(woodType)) {
            Item paleOakPlanks = PALE_OAK_PLANKS.get().asItem();
            cir.setReturnValue(paleOakPlanks);
        }
    }
}
