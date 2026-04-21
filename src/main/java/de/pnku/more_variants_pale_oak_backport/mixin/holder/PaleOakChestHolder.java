package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakChestHolder {
    public enum ChestType {
        CHEST,
        TRAPPED_CHEST
    }

    private static final Map<WoodType, EnumMap<ChestType, Block>> BLOCKS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);
    private static final Map<WoodType, EnumMap<ChestType, Item>> ITEMS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);

    public static void setBlock(WoodType woodType, ChestType type, Block block) {
        woodTypeBlocks(woodType).put(type, block);
    }

    public static Block getBlock(WoodType woodType, ChestType type) {
        Block block = woodTypeBlocks(woodType).get(type);
        if (block == null) {
            throw new IllegalStateException("Chest block is not registered for wood type '" + woodType + "' and chest type: " + type);
        }
        return block;
    }

    public static void setItem(WoodType woodType, ChestType type, Item item) {
        woodTypeItems(woodType).put(type, item);
    }

    public static Item getItem(WoodType woodType, ChestType type) {
        Item item = woodTypeItems(woodType).get(type);
        if (item == null) {
            throw new IllegalStateException("Chest item is not registered for wood type '" + woodType + "' and chest type: " + type);
        }
        return item;
    }

    private static EnumMap<ChestType, Block> woodTypeBlocks(WoodType woodType) {
        return BLOCKS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(ChestType.class));
    }

    private static EnumMap<ChestType, Item> woodTypeItems(WoodType woodType) {
        return ITEMS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(ChestType.class));
    }

    private static WoodType requireWoodType(WoodType woodType) {
        if (woodType == null) {
            throw new IllegalArgumentException("woodType must not be null");
        }
        return woodType;
    }

    private PaleOakChestHolder() {
    }
}

