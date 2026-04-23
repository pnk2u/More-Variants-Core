package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakSmokerHolder {
    public enum SmokerType {
        COBBLESTONE,
        DEEPSLATE,
        BLACKSTONE;

        private final String registrationType;

        SmokerType() {
            this.registrationType = this.name().toLowerCase();
        }

        public String registrationType() {
            return registrationType;
        }
    }

    private static final Map<WoodType, EnumMap<SmokerType, Block>> BLOCKS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);
    private static final Map<WoodType, EnumMap<SmokerType, Item>> ITEMS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);

    public static void setBlock(WoodType woodType, SmokerType type, Block block) {
        woodTypeBlocks(woodType).put(type, block);
    }

    public static Block getBlock(WoodType woodType, SmokerType type) {
        Block block = woodTypeBlocks(woodType).get(type);
        if (block == null) {
            throw new IllegalStateException("Smoker block is not registered for wood type '" + woodType + "' and type: " + type);
        }
        return block;
    }

    public static void setItem(WoodType woodType, SmokerType type, Item item) {
        woodTypeItems(woodType).put(type, item);
    }

    public static Item getItem(WoodType woodType, SmokerType type) {
        Item item = woodTypeItems(woodType).get(type);
        if (item == null) {
            throw new IllegalStateException("Smoker item is not registered for wood type '" + woodType + "' and type: " + type);
        }
        return item;
    }

    private static EnumMap<SmokerType, Block> woodTypeBlocks(WoodType woodType) {
        return BLOCKS_BY_WOOD_TYPE.computeIfAbsent(woodType, key -> new EnumMap<>(SmokerType.class));
    }

    private static EnumMap<SmokerType, Item> woodTypeItems(WoodType woodType) {
        return ITEMS_BY_WOOD_TYPE.computeIfAbsent(woodType, key -> new EnumMap<>(SmokerType.class));
    }

    private PaleOakSmokerHolder() {
    }
}

