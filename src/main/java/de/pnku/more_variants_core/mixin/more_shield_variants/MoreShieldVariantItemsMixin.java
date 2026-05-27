package de.pnku.more_variants_core.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantItem;
import de.pnku.lolmsv.item.MoreShieldVariantItems;
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

@Mixin(MoreShieldVariantItems.class)
public abstract class MoreShieldVariantItemsMixin {
    @Shadow
    private static void registerShieldItem(Item shieldItem) {}

    @Unique
    private static void registerShieldItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType shieldType = MoreVariantType.SHIELD;
        for (MoreVariantWoodType woodType : woodTypes) {
            Item shieldItem = new MoreShieldVariantItem(woodType.getName(), new Item.Properties().durability(336));
            registerShieldItem(shieldItem);
            MoreVariantHolder.setItem(shieldType, woodType, shieldItem);
        }
    }

    @Inject(method = "registerShieldItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterShieldItemsAtTail(CallbackInfo ci) {
        registerShieldItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
