package de.pnku.more_variants_pale_oak_backport.mixin.more_bookshelf_variants;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBookshelfHolder;
import de.pnku.more_variants_pale_oak_backport.PaleOakConstants;
import io.github.lieonlion.lolmbv.block.MoreBookshelfBlock;
import io.github.lieonlion.lolmbv.init.MbvBlockInit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MbvBlockInit.class)
public abstract class MbvBlockInitMixin {
    @Unique
    private static final Block PALE_OAK_BOOKSHELF = registerPaleOakBookshelf();

    @Invoker("registerBlock")
    public static void invokeRegisterBlock(String name, Block bookshelfBlock) {
        throw new AssertionError();
    }

    @Unique
    private static Block registerPaleOakBookshelf() {
        Block bookshelfBlock = new MoreBookshelfBlock(MapColor.QUARTZ);
        invokeRegisterBlock(PaleOakConstants.WOOD_TYPE + "_bookshelf", bookshelfBlock);
        PaleOakBookshelfHolder.setBlock(bookshelfBlock);
        return bookshelfBlock;
    }
}
