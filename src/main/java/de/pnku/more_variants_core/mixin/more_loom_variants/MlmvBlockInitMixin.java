package de.pnku.more_variants_core.mixin.more_loom_variants;

import de.pnku.mlmv.block.MoreLoomVariantBlock;
import de.pnku.mlmv.init.MlmvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MlmvBlockInit.class)
public abstract class MlmvBlockInitMixin {
    @Shadow
    private static void registerBlock(MoreLoomVariantBlock loom) {}

    @Unique
    private static void registerLoomBlockVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            MoreLoomVariantBlock loom = new MoreLoomVariantBlock(woodType.getMapColor(), woodType.getName());
            registerBlock(loom);
            MoreVariantHolder.setBlock(VariantType.LOOM, woodType, loom);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtTail(CallbackInfo ci) {
        registerLoomBlockVariants(WoodTypeHolder.getWoodTypes());
    }
}
