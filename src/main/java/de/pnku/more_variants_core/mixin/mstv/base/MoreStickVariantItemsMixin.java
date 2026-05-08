package de.pnku.more_variants_core.mixin.mstv.base;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_base.item.MoreStickVariantItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static de.pnku.more_variants_core.util.MoreVariantRegistryHelper.whenItemRegistered;


@Mixin(MoreStickVariantItems.class)
public abstract class MoreStickVariantItemsMixin {
    @Shadow
    private static void registerStickItem(Item stickItem, Item stickItemAfter) {}

    @Unique
    private static void registerStickItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType stickType = MoreVariantType.STICK;
        for (MoreVariantWoodType woodType : woodTypes) {
            ResourceLocation planksId = woodType.getPlanksId();
            whenItemRegistered(planksId, item -> { // MoreStickVariantItem#getPlanksItem references the Planks item
                Item stickItem = new MoreStickVariantItem(woodType.getName(), new Item.Properties());
                MoreVariantHolder.setItem(stickType, woodType, stickItem);
                registerStickItem(stickItem, MoreVariantHolder.MoreVariantType.STICK.getVanillaItem());
            });
        }
    }

    @Inject(method = "registerStickItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterStickItemsAtHead(CallbackInfo ci) {
        registerStickItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
