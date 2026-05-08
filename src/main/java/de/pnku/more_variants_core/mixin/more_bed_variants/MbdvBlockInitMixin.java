package de.pnku.more_variants_core.mixin.more_bed_variants;

import de.pnku.mbdv.block.MoreBedVariantBlock;
import de.pnku.mbdv.init.MbdvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.BedColorType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MbdvBlockInit.class)
public abstract class MbdvBlockInitMixin {
    @Shadow
    private static void registerBedBlock(MoreBedVariantBlock bed) {}

    @Unique
    private static void registerBedBlockVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (BedColorType colorType : BedColorType.values()) {
                MoreBedVariantBlock bedBlock = new MoreBedVariantBlock(colorType.color(), woodType.getName(), colorType.color().getName());
                registerBedBlock(bedBlock);
                MoreVariantHolder.setBlock(colorType, woodType, bedBlock);
            }
        }
    }

    @Inject(method = "registerBedBlocks", at = @At(value = "INVOKE", target = "Ljava/util/List;removeAll(Ljava/util/Collection;)Z"), remap = false)
    private static void injectedRegisterBedBlocks(CallbackInfo ci) {
        registerBedBlockVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
