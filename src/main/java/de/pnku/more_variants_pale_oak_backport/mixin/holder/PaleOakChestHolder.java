package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakChestHolder {
    public enum ChestType {
        CHEST,
        TRAPPED_CHEST
    }

    private static final Map<ChestType, Block> BLOCKS = new EnumMap<>(ChestType.class);
    private static final Map<ChestType, Item> ITEMS = new EnumMap<>(ChestType.class);

    public static void setBlock(ChestType type, Block block) {
        BLOCKS.put(type, block);
    }

    public static Block getBlock(ChestType type) {
        return BLOCKS.get(type);
    }

    public static void setItem(ChestType type, Item item) {
        ITEMS.put(type, item);
    }

    public static Item getItem(ChestType type) {
        return ITEMS.get(type);
    }

    private PaleOakChestHolder() {
    }
}

