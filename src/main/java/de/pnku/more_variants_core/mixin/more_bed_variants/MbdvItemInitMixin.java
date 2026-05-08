package de.pnku.more_variants_core.mixin.more_bed_variants;

import de.pnku.mbdv.init.MbdvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.BedColorType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MbdvItemInit.class)
public abstract class MbdvItemInitMixin {
    @Final
    @Shadow
    public static Item.Properties bedProperties;

    @Shadow
    private static void registerWhiteBedItem(Item whiteBed, Item bedAfter) {}

    @Shadow
    private static void registerOtherBedItem(Item otherBed) {}

    @Unique
    private static void registerBedItemVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (BedColorType colorType : BedColorType.values()) {
                Item bedItem = new BedItem(MoreVariantHolder.getBlock(colorType, woodType), bedProperties);
                if (colorType == BedColorType.WHITE) {
                    registerWhiteBedItem(bedItem, colorType.getVanillaItem());
                } else {
                    registerOtherBedItem(bedItem);
                }
                MoreVariantHolder.setItem(colorType, woodType, bedItem);
            }
        }
    }

    @Inject(method = "registerBedItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterBedItemsAtHead(CallbackInfo ci) {
        registerBedItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
