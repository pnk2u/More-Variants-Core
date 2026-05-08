package de.pnku.more_variants_core.mixin.more_chiseled_bookshelf_variants;

import de.pnku.mcbv.block.MoreChiseledBookShelfBlock;
import de.pnku.mcbv.init.McbvBlockInit;
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

@Mixin(McbvBlockInit.class)
public abstract class McbvBlockInitMixin {
    @Shadow
    private static void registerBlock(MoreChiseledBookShelfBlock chiseledBookshelf) {}

    @Unique
    private static void registerChiseledBookshelfBlockVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType chiseledBookshelfType = MoreVariantHolder.MoreVariantType.CHISELED_BOOKSHELF;
        for (MoreVariantWoodType woodType : woodTypes) {
            MoreChiseledBookShelfBlock chiseledBookShelfBlock = new MoreChiseledBookShelfBlock(woodType.getMapColor(), woodType.getName());
            registerBlock(chiseledBookShelfBlock);
            MoreVariantHolder.setBlock(chiseledBookshelfType, woodType, chiseledBookShelfBlock);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtTail(CallbackInfo ci) {
        registerChiseledBookshelfBlockVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
