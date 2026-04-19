package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class PaleOakBookshelfHolder {
    private static Block paleOakBookshelf;
    private static Item paleOakBookshelfItem;

    public static void setBlock(Block block) {
        paleOakBookshelf = block;
    }

    public static Block getBlock() {
        return paleOakBookshelf;
    }

    public static void setItem(Item item) {
        paleOakBookshelfItem = item;
    }

    public static Item getItem() {
        return paleOakBookshelfItem;
    }

    private PaleOakBookshelfHolder() {
    }
}

