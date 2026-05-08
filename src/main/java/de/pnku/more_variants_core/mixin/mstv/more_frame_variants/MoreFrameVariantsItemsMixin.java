package de.pnku.more_variants_core.mixin.mstv.more_frame_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.FrameType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_mframev.item.MoreFrameVariantItem;
import de.pnku.mstv_mframev.item.MoreFrameVariantItems;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MoreFrameVariantItems.class)
public abstract class MoreFrameVariantsItemsMixin {

    @Shadow
    private static void registerPaintingItem(Item paintingItem, Item paintingAfter) {}

    @Shadow
    private static void registerItemFramesItem(Item itemFrameItem, Item itemFrameAfter, Item glowItemFrameItem, Item glowItemFrameAfter) {}


    @Unique
    private static Item createFrameItemVariant(FrameType frameType, MoreVariantWoodType woodType) {
        return new MoreFrameVariantItem(woodType.getName(), frameType.entityType(), new Item.Properties());
    }

    @Unique
    private static void registerFrameItemVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            FrameType itemFrameType = FrameType.ITEM_FRAME;
            Item itemFrameItem = createFrameItemVariant(itemFrameType, woodType);
            FrameType glowItemFrameType = FrameType.GLOW_ITEM_FRAME;
            Item glowItemFrameItem = createFrameItemVariant(glowItemFrameType, woodType);
            FrameType paintingFrameType = FrameType.PAINTING;
            Item paintingFrameItem = createFrameItemVariant(paintingFrameType, woodType);
            registerItemFramesItem(itemFrameItem, itemFrameType.getVanillaItem(),
                                   glowItemFrameItem, glowItemFrameType.getVanillaItem());
            MoreVariantHolder.setItem(itemFrameType, woodType, itemFrameItem);
            MoreVariantHolder.setItem(glowItemFrameType, woodType, glowItemFrameItem);
            registerPaintingItem(  paintingFrameItem, paintingFrameType.getVanillaItem());
            MoreVariantHolder.setItem(paintingFrameType, woodType, paintingFrameItem);
        }
    }

    @Inject(method = "registerFrameItems", at=@At("HEAD"), remap = false)
    private static void injectedRegisterFrameItemsAtHead(CallbackInfo ci) {
        registerFrameItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
