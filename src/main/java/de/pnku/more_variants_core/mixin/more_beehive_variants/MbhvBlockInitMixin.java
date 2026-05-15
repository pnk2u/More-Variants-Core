package de.pnku.more_variants_core.mixin.more_beehive_variants;

import de.pnku.mbhv.block.MoreBeehiveVariantBlock;
import de.pnku.mbhv.init.MbhvBlockInit;
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

@Mixin(MbhvBlockInit.class)
public abstract class MbhvBlockInitMixin {
    @Shadow
    private static void registerBeehiveBlock(MoreBeehiveVariantBlock beehiveBlock) {}

    @Unique
    private static void registerBeehiveBlockVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType beehiveType = MoreVariantHolder.MoreVariantType.BEEHIVE;
        for (MoreVariantWoodType woodType : woodTypes) {
            MoreBeehiveVariantBlock beehiveBlock = new MoreBeehiveVariantBlock(woodType.mapColor(), woodType.getName());
            registerBeehiveBlock(beehiveBlock);
            MoreVariantHolder.setBlock(beehiveType, woodType, beehiveBlock);
        }
    }

    @Inject(method = "registerBeehiveBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBeehiveBlocksAtTail(CallbackInfo ci) {
        registerBeehiveBlockVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
