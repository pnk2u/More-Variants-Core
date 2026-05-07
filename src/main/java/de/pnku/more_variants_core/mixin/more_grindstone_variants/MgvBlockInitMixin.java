package de.pnku.more_variants_core.mixin.more_grindstone_variants;

import de.pnku.mgv.block.MoreGrindstoneBlock;
import de.pnku.mgv.init.MgvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MgvBlockInit.class)
public abstract class MgvBlockInitMixin {
    @Shadow
    private static void registerBlock(MoreGrindstoneBlock grindstone) {}

    @Unique
    private static void registerPaleOakGrindstoneBlockVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (MoreVariantHolder.GrindstoneType grindstoneType : MoreVariantHolder.GrindstoneType.values()) {
                MoreGrindstoneBlock grindstoneBlock = new MoreGrindstoneBlock(woodType.getMapColor(), woodType.getName(), woodType.getPlanksBlock(), grindstoneType.registrationType(), grindstoneType.getStoneSlabBlock(), "");
                registerBlock(grindstoneBlock);
                MoreVariantHolder.setBlock(grindstoneType, woodType, grindstoneBlock);
            }
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtTail(CallbackInfo ci) {
        registerPaleOakGrindstoneBlockVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    }
}
