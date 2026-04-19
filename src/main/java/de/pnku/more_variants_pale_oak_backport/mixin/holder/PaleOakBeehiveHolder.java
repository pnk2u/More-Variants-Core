package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class PaleOakBeehiveHolder {
    private static Block paleOakBeehive;
    private static Item paleOakBeehiveItem;

    public static void setBlock(Block block) {
        paleOakBeehive = block;
    }

    public static Block getBlock() {
        return paleOakBeehive;
    }

    public static void setItem(Item item) {
        paleOakBeehiveItem = item;
    }

    public static Item getItem() {
        return paleOakBeehiveItem;
    }

    private PaleOakBeehiveHolder() {
    }
}

