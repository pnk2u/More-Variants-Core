package de.pnku.more_variants_core.mixin.mstv.more_fishing_rod_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.RodType;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_base.MoreStickVariants;
import de.pnku.mstv_mfrv.item.MoreFishingRodVariantItems;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
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
    private static void registerFishingRodItemVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (RodType rodType : RodType.values()) {
                Item rodItem = createRodItem(rodType.entityType(), woodType.getName());
                Item vanillaRodItem = rodType.getVanillaItem();
                String stickPath = woodType.getName() + "_" + MoreVariantType.STICK.registrationType();
                boolean stickExisted = BuiltInRegistries.ITEM.containsKey(MoreStickVariants.withModId(stickPath));
                if (stickExisted) {
                    Item stickItem = MoreVariantHolder.getItem(MoreVariantHolder.MoreVariantType.STICK, woodType);
                    registerRodItemVariantForType(rodType, rodItem, vanillaRodItem, stickItem);
                } else {
                    RegistryEntryAddedCallback.event(BuiltInRegistries.ITEM).register((rawId, id, item) -> {
                        if (id.getPath().equals(stickPath)) {
                            Item stickItem = MoreVariantHolder.getItem(MoreVariantHolder.MoreVariantType.STICK, woodType);
                            registerRodItemVariantForType(rodType, rodItem, vanillaRodItem, stickItem);
                        }
                    });
                }
                MoreVariantHolder.setItem(rodType, woodType, rodItem);
            }
        }
    }

    @Unique
    private static void registerRodItemVariantForType(RodType rodType, Item rodItem, Item vanillaRodItem, Item stickItem) {
        if (RodType.WARPED_FUNGUS_ON_A_STICK.equals(rodType)) {
            registerWarpedFungusOnAStickItem(rodItem, vanillaRodItem, stickItem);
        } else if (RodType.CARROT_ON_A_STICK.equals(rodType)) {
            registerCarrotOnAStickItem(rodItem, vanillaRodItem, stickItem);
        } else if (RodType.FISHING_ROD.equals(rodType)) {
            registerFishingRodItem(rodItem, vanillaRodItem, stickItem);
        } else {
            throw new IllegalStateException("Unexpected RodType: " + rodType);
        }
    }

    @Inject(method = "registerRodItems", at = @At(value = "TAIL", remap = false))
    private static void injectedRegisterRodItemsAtHead(CallbackInfo ci) {
        registerFishingRodItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    }

}
