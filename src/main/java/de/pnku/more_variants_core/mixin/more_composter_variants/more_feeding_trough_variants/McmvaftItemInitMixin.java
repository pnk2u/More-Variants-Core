package de.pnku.more_variants_core.mixin.more_composter_variants.more_feeding_trough_variants;

import de.pnku.mcmvaft.init.McmvaftItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantVanillaWoodTypes;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static de.pnku.more_variants_core.util.MoreVariantHolder.waitForItemRegistration;

@Mixin(McmvaftItemInit.class)
public abstract class McmvaftItemInitMixin {
    @Shadow
    private static void registerItem(Item feedingTroughitem) {}

    @Unique
    private static void registerFeedingTroughItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantType feedingTroughType = MoreVariantType.FEEDING_TROUGH;
        waitForItemRegistration(feedingTroughType, MoreVariantVanillaWoodTypes.OAK, oakFeedingTrough -> {
            for (MoreVariantWoodType woodType : woodTypes) {
                BlockItem feedingTroughItem = new BlockItem(MoreVariantHolder.getBlock(feedingTroughType, woodType), new Item.Properties());
                registerItem(feedingTroughItem);
                MoreVariantHolder.setItem(feedingTroughType, woodType, feedingTroughItem);
            }
        });
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterCrafterItemsAtHead(CallbackInfo ci) {
        registerFeedingTroughItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
