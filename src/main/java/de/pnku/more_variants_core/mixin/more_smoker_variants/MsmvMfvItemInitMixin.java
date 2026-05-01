package de.pnku.more_variants_core.mixin.more_smoker_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.SmokerType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import de.pnku.msmv.init.MsmvMfvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MsmvMfvItemInit.class)
public abstract class MsmvMfvItemInitMixin {
    @Shadow
    private static void registerMfvSmokerItem(BlockItem smoker, Item smokerAfter) {}

    @Unique
    private static void registerNfvSmokerItemVariants(List<WoodType> woodTypes, SmokerType[] smokerTypes) {
        for (WoodType woodType : woodTypes) {
            for (SmokerType smokerType : smokerTypes) {
                BlockItem smokerItem = new BlockItem(MoreVariantHolder.getBlock(smokerType, woodType), new Item.Properties());
                registerMfvSmokerItem(smokerItem, smokerType.getVanillaItem());
                MoreVariantHolder.setItem(smokerType, woodType, smokerItem);
            }
        }
    }

    @Inject(method = "registerMfvSmokerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterSmokerItemsAtTail(CallbackInfo ci) {
        registerNfvSmokerItemVariants(WoodTypeHolder.getWoodTypes(), new SmokerType[]{SmokerType.BLACKSTONE, SmokerType.DEEPSLATE});
    }
}
