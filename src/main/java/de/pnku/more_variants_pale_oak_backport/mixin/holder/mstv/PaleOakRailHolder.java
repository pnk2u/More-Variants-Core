package de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakRailHolder {
    public enum RailType {
        RAIL(""),
        ACTIVATOR_RAIL("activator"),
        DETECTOR_RAIL("detector"),
        POWERED_RAIL("powered");

        private final String registrationType;

        RailType(String registrationType) {
            this.registrationType = registrationType;
        }

        public String registrationType() {
            return registrationType;
        }
    }

    private static final Map<WoodType, EnumMap<RailType, Block>> BLOCKS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);
    private static final Map<WoodType, EnumMap<RailType, Item>> ITEMS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);

    public static void setBlock(WoodType woodType, RailType type, Block block) {
        woodTypeBlocks(woodType).put(type, block);
    }

    public static Block getBlock(WoodType woodType, RailType type) {
        Block block = woodTypeBlocks(woodType).get(type);
        if (block == null) {
            throw new IllegalStateException("Rail block is not registered for wood type '" + woodType + "' and type: " + type);
        }
        return block;
    }

    public static void setItem(WoodType woodType, RailType type, Item item) {
        woodTypeItems(woodType).put(type, item);
    }

    public static Item getItem(WoodType woodType, RailType type) {
        Item item = woodTypeItems(woodType).get(type);
        if (item == null) {
            throw new IllegalStateException("Rail item is not registered for wood type '" + woodType + "' and type: " + type);
        }
        return item;
    }

    private static EnumMap<RailType, Block> woodTypeBlocks(WoodType woodType) {
        return BLOCKS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(RailType.class));
    }

    private static EnumMap<RailType, Item> woodTypeItems(WoodType woodType) {
        return ITEMS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(RailType.class));
    }

    private static WoodType requireWoodType(WoodType woodType) {
        if (woodType == null) {
            throw new IllegalArgumentException("woodType must not be null");
        }
        return woodType;
    }

    private PaleOakRailHolder() {
    }
}

