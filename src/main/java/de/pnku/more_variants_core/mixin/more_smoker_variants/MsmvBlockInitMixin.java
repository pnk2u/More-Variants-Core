package de.pnku.more_variants_core.mixin.more_smoker_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.SmokerType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.msmv.block.MoreSmokerVariantBlock;
import de.pnku.msmv.init.MsmvBlockInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MsmvBlockInit.class)
public abstract class MsmvBlockInitMixin {
    @Shadow
    private static void registerSmokerBlock(MoreSmokerVariantBlock smoker) {}

    @Unique
    private static void registerSmokerBlockVariants(List<MoreVariantWoodType> woodTypes, SmokerType[] smokerTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (SmokerType smokerType : smokerTypes) {
                MoreSmokerVariantBlock smokerBlock = new MoreSmokerVariantBlock(woodType.getMapColor(), woodType.getName(), smokerType.registrationType());
                registerSmokerBlock(smokerBlock);
                MoreVariantHolder.setBlock(smokerType, woodType, smokerBlock);
            }
        }
    }

    @Inject(method = "registerSmokerBlocks", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterSmokerBlocksAtTail(CallbackInfo ci) {
        registerSmokerBlockVariants(MoreVariantWoodTypeHolder.getWoodTypes(), SmokerType.values());
    }

    @Inject(method = "registerCobblestoneSmokerBlocks", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterCobblestoneSmokerBlocksAtTail(CallbackInfo ci) {
        registerSmokerBlockVariants(MoreVariantWoodTypeHolder.getWoodTypes(), new SmokerType[]{SmokerType.COBBLESTONE});
    }
}
