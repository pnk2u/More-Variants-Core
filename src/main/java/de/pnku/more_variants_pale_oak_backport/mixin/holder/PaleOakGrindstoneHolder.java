package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakGrindstoneHolder {
    public enum GrindstoneType {
        STONE(Blocks.STONE_SLAB),
        SANDSTONE(Blocks.SANDSTONE_SLAB),
        GRANITE(Blocks.GRANITE_SLAB),
        DEEPSLATE(Blocks.POLISHED_DEEPSLATE_SLAB),
        BASALT(Blocks.BASALT);

        private final String registrationType;
        private final String blockIdSuffix;
        private final Block stoneSlabBlock;

        GrindstoneType(Block stoneSlabBlock) {
            this.stoneSlabBlock = stoneSlabBlock;
            this.registrationType = this.name().toLowerCase();
            this.blockIdSuffix = "_" + (!registrationType.equals("stone") ? "" : ("_" + registrationType)) + "_grindstone";
        }

        public String registrationType() {
            return registrationType;
        }

        public Block getStoneSlabBlock() {
            return stoneSlabBlock;
        }

        public String blockIdSuffix() {
            return blockIdSuffix;
        }
    }

    private static final Map<WoodType, EnumMap<GrindstoneType, Block>> BLOCKS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);
    private static final Map<WoodType, EnumMap<GrindstoneType, Item>> ITEMS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);

    public static void setBlock(WoodType woodType, GrindstoneType type, Block block) {
        woodTypeBlocks(woodType).put(type, block);
    }

    public static Block getBlock(WoodType woodType, GrindstoneType type) {
        Block block = woodTypeBlocks(woodType).get(type);
        if (block == null) {
            throw new IllegalStateException("Grindstone block is not registered for wood type '" + woodType + "' and type: " + type);
        }
        return block;
    }

    public static void setItem(WoodType woodType, GrindstoneType type, Item item) {
        woodTypeItems(woodType).put(type, item);
    }

    public static Item getItem(WoodType woodType, GrindstoneType type) {
        Item item = woodTypeItems(woodType).get(type);
        if (item == null) {
            throw new IllegalStateException("Grindstone item is not registered for wood type '" + woodType + "' and type: " + type);
        }
        return item;
    }

    private static EnumMap<GrindstoneType, Block> woodTypeBlocks(WoodType woodType) {
        return BLOCKS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(GrindstoneType.class));
    }

    private static EnumMap<GrindstoneType, Item> woodTypeItems(WoodType woodType) {
        return ITEMS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(GrindstoneType.class));
    }

    private static WoodType requireWoodType(WoodType woodType) {
        if (woodType == null) {
            throw new IllegalArgumentException("woodType must not be null");
        }
        return woodType;
    }

    private PaleOakGrindstoneHolder() {
    }
}

