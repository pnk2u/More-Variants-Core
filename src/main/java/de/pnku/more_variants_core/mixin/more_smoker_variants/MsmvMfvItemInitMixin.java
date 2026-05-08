package de.pnku.more_variants_core.mixin.more_smoker_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.SmokerType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.more_variants_core.util.VanillaWoodTypes;
import de.pnku.msmv.init.MsmvMfvItemInit;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static de.pnku.more_variants_core.util.MoreVariantHolder.waitForItemRegistration;

@Mixin(MsmvMfvItemInit.class)
public abstract class MsmvMfvItemInitMixin {
    @Shadow
    private static void registerMfvSmokerItem(BlockItem smoker, Item smokerAfter) {}

    @Unique
    private static void registerNfvSmokerItemVariants(List<MoreVariantWoodType> woodTypes, SmokerType[] smokerTypes) {
        for (SmokerType smokerType : smokerTypes) {
            waitForItemRegistration(smokerType, VanillaWoodTypes.getLast(), vanillaSmokerItem -> {
                for (MoreVariantWoodType woodType : woodTypes) {
                    BlockItem smokerItem = new BlockItem(MoreVariantHolder.getBlock(smokerType, woodType), new Item.Properties());
                    registerMfvSmokerItem(smokerItem, vanillaSmokerItem);
                    MoreVariantHolder.setItem(smokerType, woodType, smokerItem);
                }
            });
        }
    }

    @Inject(method = "registerMfvSmokerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterSmokerItemsAtTail(CallbackInfo ci) {
        registerNfvSmokerItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes(), new SmokerType[]{SmokerType.DEEPSLATE, SmokerType.BLACKSTONE});
    }
}
