package de.pnku.more_variants_core.mixin.more_bookshelf_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import io.github.lieonlion.lolmbv.block.MoreBookshelfBlock;
import io.github.lieonlion.lolmbv.init.MbvBlockInit;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MbvBlockInit.class)
public abstract class MbvBlockInitMixin {
    @Shadow
    private static void registerBlock(String name, Block block) {}

    @Unique
    private static void registerBookshelfBlockVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            Block bookshelfBlock = new MoreBookshelfBlock(woodType.getMapColor());
            registerBlock(woodType.getName() + "_bookshelf", bookshelfBlock);
            MoreVariantHolder.setBlock(VariantType.BOOKSHELF, woodType, bookshelfBlock);
        }
    }

    @Inject(method = "registerBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBlocksAtTail(CallbackInfo ci) {
        registerBookshelfBlockVariants(WoodTypeHolder.getWoodTypes());
    }
}
