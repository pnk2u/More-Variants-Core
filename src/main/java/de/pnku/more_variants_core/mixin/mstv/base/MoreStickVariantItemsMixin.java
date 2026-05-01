package de.pnku.more_variants_core.mixin.mstv.base;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_base.item.MoreStickVariantItems;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(MoreStickVariantItems.class)
public abstract class MoreStickVariantItemsMixin {
    @Shadow
    private static void registerStickItem(Item stickItem, Item stickItemAfter) {}

    @Unique
    private static void registerStickItemVariants(List<WoodType> woodTypes) {
        VariantType stickType = VariantType.STICK;
        for (WoodType woodType : woodTypes) {
            Item stickItem = new MoreStickVariantItem(woodType.getName(), new Item.Properties());
            MoreVariantHolder.setItem(stickType, woodType, stickItem);
            registerStickItem(stickItem, VariantType.STICK.getVanillaItem());
        }
    }

    @Inject(method = "registerStickItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterStickItemsAtTail(CallbackInfo ci) {
        registerStickItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
