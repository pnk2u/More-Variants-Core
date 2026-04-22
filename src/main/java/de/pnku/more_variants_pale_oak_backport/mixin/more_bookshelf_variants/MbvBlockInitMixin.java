package de.pnku.more_variants_pale_oak_backport.mixin.more_bookshelf_variants;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBookshelfHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import io.github.lieonlion.lolmbv.block.MoreBookshelfBlock;
import io.github.lieonlion.lolmbv.init.MbvBlockInit;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MbvBlockInit.class)
public abstract class MbvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_BOOKSHELF = registerPaleOakBookshelf();

    @Shadow
    private static void registerBlock(String name, Block block) {}

    @Unique
    private static Block registerPaleOakBookshelf() {
        Block bookshelfBlock = new MoreBookshelfBlock(WOOD_TYPE.getMapColor());
        registerBlock(WOOD_TYPE.getName() + "_bookshelf", bookshelfBlock);
        PaleOakBookshelfHolder.setBlock(WOOD_TYPE, bookshelfBlock);
        return bookshelfBlock;
    }
}
