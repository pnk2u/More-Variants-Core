package de.pnku.more_variants_core.mixin.more_bed_variants;

import de.pnku.mbdv.init.MbdvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.BedColorType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

    @Inject(method = "registerBedItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBedItems(CallbackInfo ci) {
        registerBedItemVariants(WoodTypeHolder.getWoodTypes());
    }

    @Unique
    private static void registerBedItemVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            for (BedColorType colorType : BedColorType.values()) {
                Item bedItem = new BedItem(MoreVariantHolder.getBlock(colorType, woodType), bedProperties);
                if (colorType == BedColorType.WHITE) {
                    registerWhiteBedItem(bedItem, Items.WHITE_BED);
                } else {
                    registerOtherBedItem(bedItem);
                }
                MoreVariantHolder.setItem(colorType, woodType, bedItem);
            }
        }
    }
}
