package de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class PaleOakLadderHolder {
    public static Block paleOakLadder;
    public static Item paleOakLadderItem;

    public static void setBlock(Block block) {
        paleOakLadder = block;
    }

    public static Block getBlock() {
        return paleOakLadder;
    }

    public static void setItem(Item item) {
        paleOakLadderItem = item;
    }

    public static Item getItem() {
        return paleOakLadderItem;
    }
}

