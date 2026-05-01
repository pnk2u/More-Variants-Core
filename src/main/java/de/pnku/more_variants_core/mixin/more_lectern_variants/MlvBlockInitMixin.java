package de.pnku.more_variants_core.mixin.more_lectern_variants;

import de.pnku.mlv.block.MoreLecternBlock;
import de.pnku.mlv.init.MlvBlockInit;
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

@Mixin(MlvBlockInit.class)
public abstract class MlvBlockInitMixin {
    @Shadow
    private static void registerBlock(MoreLecternBlock lectern) {}

    @Unique
    private static void registerkLecternBlockVariants(List<WoodType> woodTypes) {
        VariantType lecternType = VariantType.LECTERN;
        for (WoodType woodType : woodTypes) {
            MoreLecternBlock lectern = new MoreLecternBlock(woodType.getMapColor(), woodType.getName());
            registerBlock(lectern);
            MoreVariantHolder.setBlock(lecternType, woodType, lectern);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtTail(CallbackInfo ci) {
        registerkLecternBlockVariants(WoodTypeHolder.getWoodTypes());
    }
}
