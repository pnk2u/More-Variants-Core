package de.pnku.more_variants_pale_oak_backport.mixin.more_bookshelf_variants;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBookshelfHolder;
import io.github.lieonlion.lolmbv.block.MoreBookshelfBlock;
import io.github.lieonlion.lolmbv.init.MbvBlockInit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MbvBlockInit.class)
public abstract class MbvBlockInitMixin {
    @Unique
    private static final Block PALE_OAK_BOOKSHELF = registerPaleOakBookshelf();

    @Invoker("registerBlock")
    public static void invokeRegisterBookshelf(String name, Block bookshelfBlock) {
        throw new AssertionError();
    }

    @Inject(method = "registerBlocks", at = @At("HEAD"), remap = false)
    private static void injectedRegisterBookshelfBlocksAtHead(CallbackInfo ci) {
        if (PALE_OAK_BOOKSHELF.defaultBlockState().isAir()) {
            throw new IllegalStateException("Failed to register Pale Oak Bookshelf Block");
        }
    }

    @Unique
    private static Block registerPaleOakBookshelf() {
        String woodType = "pale_oak";
        Block bookshelfBlock = new MoreBookshelfBlock(MapColor.QUARTZ);
        invokeRegisterBookshelf(woodType + "_bookshelf", bookshelfBlock);
        PaleOakBookshelfHolder.PALE_OAK_BOOKSHELF = bookshelfBlock;
        return bookshelfBlock;
    }
}
