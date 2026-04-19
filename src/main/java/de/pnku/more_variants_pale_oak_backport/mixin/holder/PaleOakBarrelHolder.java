package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class PaleOakBarrelHolder {
    private static Block paleOakBarrel;
    private static Item paleOakBarrelItem;

    public static void setBlock(Block block) {
        paleOakBarrel = block;
    }

    public static Block getBlock() {
        return paleOakBarrel;
    }

    public static void setItem(Item item) {
        paleOakBarrelItem = item;
    }

    public static Item getItem() {
        return paleOakBarrelItem;
    }

    private PaleOakBarrelHolder() {
    }
}

