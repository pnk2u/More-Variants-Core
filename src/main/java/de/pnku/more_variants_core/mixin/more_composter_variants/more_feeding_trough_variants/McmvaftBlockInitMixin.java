package de.pnku.more_variants_core.mixin.more_composter_variants.more_feeding_trough_variants;

import de.pnku.mcmvaft.block.MoreFeedingTroughBlock;
import de.pnku.mcmvaft.init.McmvaftBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(McmvaftBlockInit.class)
public abstract class McmvaftBlockInitMixin {
    @Shadow
    private static void registerBlock(Block feedingTrough) {}

    @Unique
    private static void registerFeedingTroughBlockVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType feedingTroughType = MoreVariantHolder.MoreVariantType.ANIMAL_FEEDING_TROUGH;
        for (MoreVariantWoodType woodType : woodTypes) {
            Block feedingTrough = new MoreFeedingTroughBlock(woodType.getMapColor(), woodType.getName());
            registerBlock(feedingTrough);
            MoreVariantHolder.setBlock(feedingTroughType, woodType, feedingTrough);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterCrafterItemsAtTail(CallbackInfo ci) {
        registerFeedingTroughBlockVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}

