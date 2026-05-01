package de.pnku.more_variants_core.mixin.mstv.more_fishing_rod_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.RodType;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.StickVariantRegistrationHelper;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import de.pnku.mstv_mfrv.item.MoreFishingRodVariantItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MoreFishingRodVariantItems.class)
public abstract class MoreFishingRodVariantItemsMixin {
    @Shadow
    public static Item createRodItem(String rodType, String woodType) {throw new AssertionError();}

    @Shadow
    private static void registerFishingRodItem(Item fishingRodItem, Item fishingRodAfter, Item stickItem) {}

    @Shadow
    private static void registerCarrotOnAStickItem(Item carrotOnAStickItem, Item carrotOnAStickAfter, Item stickItem) {}

    @Shadow
    private static void registerWarpedFungusOnAStickItem(Item warpedFungusOnAStickItem, Item warpedFungusOnAStickAfter, Item stickItem) {}

    @Unique
    private static void registerFishingRodItemVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            Item stickItem = MoreVariantHolder.getItem(VariantType.STICK, woodType);
            if (stickItem == null) {
                StickVariantRegistrationHelper.registerStickItemVariants(WoodTypeHolder.getWoodTypes());
            }
            for (RodType rodType : RodType.values()) {
                Item rodItem = createRodItem(rodType.entityType(), woodType.getName());
                if (RodType.WARPED_FUNGUS_ON_A_STICK.equals(rodType)) {
                    registerWarpedFungusOnAStickItem(rodItem, Items.WARPED_FUNGUS_ON_A_STICK, stickItem);
                } else if (RodType.CARROT_ON_A_STICK.equals(rodType)) {
                    registerCarrotOnAStickItem(rodItem, Items.CARROT_ON_A_STICK, stickItem);
                } else if (RodType.FISHING_ROD.equals(rodType)) {
                    registerFishingRodItem(rodItem, Items.FISHING_ROD, stickItem);
                } else {
                    throw new IllegalStateException("Unexpected RodType: " + rodType);
                }
                MoreVariantHolder.setItem(rodType, woodType, rodItem);
            }
        }
    }

    @Inject(method = "registerRodItems", at = @At(value = "TAIL", remap = false))
    private static void injectedRegisterRodItemsAtHead(CallbackInfo ci) {
        registerFishingRodItemVariants(WoodTypeHolder.getWoodTypes());
    }

}
