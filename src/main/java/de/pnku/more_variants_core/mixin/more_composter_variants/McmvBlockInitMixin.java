package de.pnku.more_variants_core.mixin.more_composter_variants;

import de.pnku.mcmv.block.MoreComposterBlock;
import de.pnku.mcmv.init.McmvBlockInit;
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

@Mixin(McmvBlockInit.class)
public abstract class McmvBlockInitMixin {
    @Shadow
    private static void registerBlock(MoreComposterBlock composter) {}

    @Unique
    private static void registerComposterBlockVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType composterType = MoreVariantHolder.MoreVariantType.COMPOSTER;
        for (MoreVariantWoodType woodType : woodTypes) {
            MoreComposterBlock composter = new MoreComposterBlock(woodType.getMapColor(), woodType.getName());
            registerBlock(composter);
            MoreVariantHolder.setBlock(composterType, woodType, composter);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterCrafterItemsAtTail(CallbackInfo ci) {
        registerComposterBlockVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
