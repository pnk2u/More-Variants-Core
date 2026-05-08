package de.pnku.more_variants_core.mixin.more_chest_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.ChestType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.block.MoreTrappedChestBlock;
import io.github.lieonlion.mcv.init.McvBlockInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(McvBlockInit.class)
public abstract class McvBlockInitMixin {
    @Shadow
    private static void registerBlock(MoreChestBlock chest, MoreTrappedChestBlock trappedChest) {}

    @Unique
    private static void registerChestBlockVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            registerBlock(createChestVariant(woodType), createTrappedChestVariant(woodType));
        }
    }

    @Inject(method = "registerBlocks", at = @At("HEAD"), remap = false)
    private static void injectedRegisterBlocksAtHead(CallbackInfo ci) {
        registerChestBlockVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }

    @Unique
    private static MoreChestBlock createChestVariant(MoreVariantWoodType woodType) {
        MoreChestBlock chestBlock = new MoreChestBlock(woodType.getMapColor(), woodType.getName());
        MoreVariantHolder.setBlock(ChestType.CHEST, woodType, chestBlock);
        return chestBlock;
    }

    @Unique
    private static MoreTrappedChestBlock createTrappedChestVariant(MoreVariantWoodType woodType) {
        MoreTrappedChestBlock trappedChestBlock = new MoreTrappedChestBlock(woodType.getMapColor(), woodType.getName());
        MoreVariantHolder.setBlock(ChestType.TRAPPED_CHEST, woodType, trappedChestBlock);
        return trappedChestBlock;
    }
}
