package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakBedHolder {
    private static final Map<DyeColor, Block> BLOCKS = new EnumMap<>(DyeColor.class);
    private static final Map<DyeColor, Item> ITEMS = new EnumMap<>(DyeColor.class);

    public static void setBlock(DyeColor color, Block block) {
        BLOCKS.put(color, block);
    }

    public static Block getBlock(DyeColor color) {
        return BLOCKS.get(color);
    }

    public static void setItem(DyeColor color, Item item) {
        ITEMS.put(color, item);
    }

    public static Item getItem(DyeColor color) {
        return ITEMS.get(color);
    }

    private PaleOakBedHolder() {
    }
}

