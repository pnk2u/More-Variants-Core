package de.pnku.more_variants_core.mixin.mstv.more_armor_stand_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_masv.item.MoreArmorStandVariantItem;
import de.pnku.mstv_masv.item.MoreArmorStandVariantItems;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MoreArmorStandVariantItems.class)
public abstract class MoreArmorStandVariantItemsMixin {
    @Shadow
    private static void registerArmorStandItem(Item armorStandItem, Item armorStandAfter) {}

    @Unique
    private static void registerArmorStandItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType armorStandType = MoreVariantType.ARMOR_STAND;
        for (MoreVariantWoodType woodType : woodTypes) {
            Item armorStandItem = new MoreArmorStandVariantItem(woodType.getName(), new Item.Properties().stacksTo(16));
            registerArmorStandItem(armorStandItem, armorStandType.getVanillaItem());
            MoreVariantHolder.setItem(armorStandType, woodType, armorStandItem);
        }
    }

        registerArmorStandItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    @Inject(method = "registerArmorStandItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterArmorStandItemsAtHead(CallbackInfo ci) {
    }
}
