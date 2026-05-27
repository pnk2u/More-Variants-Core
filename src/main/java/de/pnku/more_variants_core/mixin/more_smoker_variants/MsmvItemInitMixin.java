package de.pnku.more_variants_core.mixin.more_smoker_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.SmokerType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.more_variants_core.util.MoreVariantVanillaWoodTypes;
import de.pnku.msmv.init.MsmvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static de.pnku.more_variants_core.util.MoreVariantHolder.waitForBlockRegistration;
import static de.pnku.more_variants_core.util.MoreVariantHolder.waitForItemRegistration;
import static de.pnku.more_variants_core.MoreVariantsCore.LOGGER;

@Mixin(MsmvItemInit.class)
public abstract class MsmvItemInitMixin {
    @Shadow
    private static void registerSmokerItem(BlockItem smoker, Item smokerAfter) {}

    @Unique
    private static void registerSmokerItemVariants(List<MoreVariantWoodType> woodTypes) {
        if (woodTypes.isEmpty()) {
            LOGGER.warn("No More Variant Wood Types found for Smoker Item registration. Skipping Smoker Item Variant registration.");
            return;
        }
        SmokerType smokerType = SmokerType.COBBLESTONE;
        waitForBlockRegistration(smokerType, woodTypes.get(woodTypes.size() - 1), waitedFor ->
            waitForItemRegistration(smokerType, MoreVariantVanillaWoodTypes.getLast(), vanillaSmokerItem -> {
                for (MoreVariantWoodType woodType : woodTypes) {
                    BlockItem smokerItem = new BlockItem(MoreVariantHolder.getBlock(smokerType, woodType), new Item.Properties());
                    registerSmokerItem(smokerItem, vanillaSmokerItem);
                    MoreVariantHolder.setItem(smokerType, woodType, smokerItem);
                }
            }
            )
        );
    }

    @Inject(method = "registerSmokerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterSmokerItemsAtTail(CallbackInfo ci) {
        registerSmokerItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
