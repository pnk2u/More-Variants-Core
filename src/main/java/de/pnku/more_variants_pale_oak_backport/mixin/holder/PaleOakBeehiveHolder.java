package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakBeehiveHolder {
    private static final Map<WoodType, Block> BLOCKS = new EnumMap<>(WoodType.class);
    private static final Map<WoodType, Item> ITEMS = new EnumMap<>(WoodType.class);

    public static void setBlock(WoodType woodType, Block block) {
        BLOCKS.put(woodType, block);
    }

    public static Block getBlock(WoodType woodType) {
        Block block = BLOCKS.get(woodType);
        if (block == null) {
            throw new IllegalStateException("Beehive block is not registered for wood type: " + woodType);
        }
        return block;
    }

    public static void setItem(WoodType woodType, Item item) {
        ITEMS.put(woodType, item);
    }

    public static Item getItem(WoodType woodType) {
        Item item = ITEMS.get(woodType);
        if (item == null) {
            throw new IllegalStateException("Beehive item is not registered for wood type: " + woodType);
        }
        return item;
    }

    private PaleOakBeehiveHolder() {
    }
}

