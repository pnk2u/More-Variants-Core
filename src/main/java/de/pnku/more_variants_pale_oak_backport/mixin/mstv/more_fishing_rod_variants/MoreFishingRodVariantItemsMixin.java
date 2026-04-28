package de.pnku.more_variants_pale_oak_backport.mixin.mstv.more_fishing_rod_variants;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.*;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.mstv_mfrv.item.MoreFishingRodVariantItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MoreFishingRodVariantItems.class)
public abstract class MoreFishingRodVariantItemsMixin {
    @Shadow
    public static Item createRodItem(String rodType, String woodType) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_FISHING_ROD = createRodItem("fish", WOOD_TYPE.getName());

    @Unique
    private static final Item CARROT_ON_A_PALE_OAK_STICK = createRodItem("pig", WOOD_TYPE.getName());

    @Unique
    private static final Item WARPED_FUNGUS_ON_A_PALE_OAK_STICK = createRodItem("strider", WOOD_TYPE.getName());

    @Shadow
    private static void registerFishingRodItem(Item fishingRodItem, Item fishingRodAfter, Item stickItem) {}

    @Shadow
    private static void registerCarrotOnAStickItem(Item carrotOnAStickItem, Item carrotOnAStickAfter, Item stickItem) {}

    @Shadow
    private static void registerWarpedFungusOnAStickItem(Item warpedFungusOnAStickItem, Item warpedFungusOnAStickAfter, Item stickItem) {}

    @Inject(method = "registerRodItems", at = @At(value = "HEAD", remap = false))
    private static void injectedRegisterRodItemsAtHead(CallbackInfo ci) {
        Item paleOakStickItem = PaleOakVariantHolder.getItem(VariantType.STICK, WOOD_TYPE);
        registerFishingRodItem(PALE_OAK_FISHING_ROD, Items.FISHING_ROD, paleOakStickItem);
        PaleOakVariantHolder.setItem(RodType.FISHING_ROD, WOOD_TYPE, PALE_OAK_FISHING_ROD);
        registerCarrotOnAStickItem(CARROT_ON_A_PALE_OAK_STICK, Items.CARROT_ON_A_STICK, paleOakStickItem);
        PaleOakVariantHolder.setItem(RodType.CARROT_ON_A_STICK, WOOD_TYPE, CARROT_ON_A_PALE_OAK_STICK);
        registerWarpedFungusOnAStickItem(WARPED_FUNGUS_ON_A_PALE_OAK_STICK, Items.WARPED_FUNGUS_ON_A_STICK, paleOakStickItem);
        PaleOakVariantHolder.setItem(RodType.WARPED_FUNGUS_ON_A_STICK, WOOD_TYPE, WARPED_FUNGUS_ON_A_PALE_OAK_STICK);
    }
}
