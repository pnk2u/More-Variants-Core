package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class PaleOakCraftingTableHolder {
    private static Block paleOakCraftingTable;
    private static Item paleOakCraftingTableItem;

    public static void setBlock(Block block) {
        paleOakCraftingTable = block;
    }

    public static Block getBlock() {
        return paleOakCraftingTable;
    }

    public static void setItem(Item item) {
        paleOakCraftingTableItem = item;
    }

    public static Item getItem() {
        return paleOakCraftingTableItem;
    }

    private PaleOakCraftingTableHolder() {
    }
}

