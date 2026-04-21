package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakBedHolder {
    private static final Map<WoodType, EnumMap<DyeColor, Block>> BLOCKS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);
    private static final Map<WoodType, EnumMap<DyeColor, Item>> ITEMS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);

    public static void setBlock(WoodType woodType, DyeColor color, Block block) {
        woodTypeBlocks(woodType).put(color, block);
    }

    public static Block getBlock(WoodType woodType, DyeColor color) {
        Block block = woodTypeBlocks(woodType).get(color);
        if (block == null) {
            throw new IllegalStateException("Bed block is not registered for wood type '" + woodType + "' and color: " + color);
        }
        return block;
    }

    public static void setItem(WoodType woodType, DyeColor color, Item item) {
        woodTypeItems(woodType).put(color, item);
    }

    public static Item getItem(WoodType woodType, DyeColor color) {
        Item item = woodTypeItems(woodType).get(color);
        if (item == null) {
            throw new IllegalStateException("Bed item is not registered for wood type '" + woodType + "' and color: " + color);
        }
        return item;
    }

    private static EnumMap<DyeColor, Block> woodTypeBlocks(WoodType woodType) {
        return BLOCKS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(DyeColor.class));
    }

    private static EnumMap<DyeColor, Item> woodTypeItems(WoodType woodType) {
        return ITEMS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(DyeColor.class));
    }

    private static WoodType requireWoodType(WoodType woodType) {
        if (woodType == null) {
            throw new IllegalArgumentException("woodType must not be null");
        }
        return woodType;
    }

    private PaleOakBedHolder() {
    }
}

