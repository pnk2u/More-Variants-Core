package de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakChestHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakRailHolder {
    public enum RailType {
        RAIL,
        ACTIVATOR_RAIL,
        DETECTOR_RAIL,
        POWERED_RAIL
    }

    private static final Map<RailType, Block> BLOCKS = new EnumMap<>(RailType.class);
    private static final Map<RailType, Item> ITEMS = new EnumMap<>(RailType.class);

    public static void setBlock(RailType type, Block block) {
        BLOCKS.put(type, block);
    }

    public static Block getBlock(RailType type) {
        return BLOCKS.get(type);
    }

    public static void setItem(RailType type, Item item) {
        ITEMS.put(type, item);
    }

    public static Item getItem(RailType type) {
        return ITEMS.get(type);
    }

    public static RailType getRailType(Block block) {
        for (Map.Entry<RailType, Block> entry : BLOCKS.entrySet()) {
            if (entry.getValue() == block) {
                return entry.getKey();
            }
        }
        return RailType.RAIL;
    }

    private PaleOakRailHolder() {
    }
}

