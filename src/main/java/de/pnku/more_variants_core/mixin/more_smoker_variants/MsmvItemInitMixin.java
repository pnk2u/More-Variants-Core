package de.pnku.more_variants_core.mixin.more_smoker_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.SmokerType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import de.pnku.msmv.init.MsmvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MsmvItemInit.class)
public abstract class MsmvItemInitMixin {
    @Shadow
    private static void registerSmokerItem(BlockItem smoker, Item smokerAfter) {}

    @Unique
    private static void registerSmokerItemVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            for (SmokerType smokerType : SmokerType.values()) {
                BlockItem smokerItem = new BlockItem(MoreVariantHolder.getBlock(smokerType, woodType), new Item.Properties());
                registerSmokerItem(smokerItem, Items.SMOKER);
                MoreVariantHolder.setItem(smokerType, woodType, smokerItem);
            }
        }
    }

    @Inject(method = "registerSmokerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterSmokerItemsAtTail(CallbackInfo ci) {
        registerSmokerItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
