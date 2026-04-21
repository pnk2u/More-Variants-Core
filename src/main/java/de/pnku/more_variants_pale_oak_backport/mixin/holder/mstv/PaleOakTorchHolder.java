package de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakTorchHolder {
    public enum TorchType {
        TORCH("torch", "wall_torch"),
        SOUL_TORCH("soul_torch", "soul_wall_torch"),
        REDSTONE_TORCH("redstone_torch", "redstone_wall_torch");

        private final String torchName;
        private final String wallTorchName;

        TorchType(String torchName, String wallTorchName) {
            this.torchName = torchName;
            this.wallTorchName = wallTorchName;
        }

        public String torchName() {
            return torchName;
        }

        public String wallTorchName() {
            return wallTorchName;
        }
    }

    private static final Map<WoodType, EnumMap<TorchType, Block>> BLOCKS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);
    private static final Map<WoodType, EnumMap<TorchType, Block>> WALL_BLOCKS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);
    private static final Map<WoodType, EnumMap<TorchType, Item>> ITEMS_BY_WOOD_TYPE = new EnumMap<>(WoodType.class);

    public static void setBlock(WoodType woodType, TorchType type, Block block) {
        woodTypeBlocks(woodType).put(type, block);
    }

    public static Block getBlock(WoodType woodType, TorchType type) {
        Block block = woodTypeBlocks(woodType).get(type);
        if (block == null) {
            throw new IllegalStateException("Torch block is not registered for wood type '" + woodType + "' and type: " + type);
        }
        return block;
    }

    public static void setWallBlock(WoodType woodType, TorchType type, Block block) {
        woodTypeWallBlocks(woodType).put(type, block);
    }

    public static Block getWallBlock(WoodType woodType, TorchType type) {
        Block block = woodTypeWallBlocks(woodType).get(type);
        if (block == null) {
            throw new IllegalStateException("Wall torch block is not registered for wood type '" + woodType + "' and type: " + type);
        }
        return block;
    }

    public static void setItem(WoodType woodType, TorchType type, Item item) {
        woodTypeItems(woodType).put(type, item);
    }

    public static Item getItem(WoodType woodType, TorchType type) {
        Item item = woodTypeItems(woodType).get(type);
        if (item == null) {
            throw new IllegalStateException("Torch item is not registered for wood type '" + woodType + "' and type: " + type);
        }
        return item;
    }

    private static EnumMap<TorchType, Block> woodTypeBlocks(WoodType woodType) {
        return BLOCKS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(TorchType.class));
    }

    private static EnumMap<TorchType, Block> woodTypeWallBlocks(WoodType woodType) {
        return WALL_BLOCKS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(TorchType.class));
    }

    private static EnumMap<TorchType, Item> woodTypeItems(WoodType woodType) {
        return ITEMS_BY_WOOD_TYPE.computeIfAbsent(requireWoodType(woodType), key -> new EnumMap<>(TorchType.class));
    }

    private static WoodType requireWoodType(WoodType woodType) {
        if (woodType == null) {
            throw new IllegalArgumentException("woodType must not be null");
        }
        return woodType;
    }

    public static SimpleParticleType getParticleType(TorchType type) {
        return type == TorchType.SOUL_TORCH ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME;
    }

    public static Block getVanillaTorchBlock(TorchType type) {
        if (type == TorchType.REDSTONE_TORCH) {
            return Blocks.REDSTONE_TORCH;
        }
        if (type == TorchType.SOUL_TORCH) {
            return Blocks.SOUL_TORCH;
        }
        return Blocks.TORCH;
    }

    public static Block getVanillaWallTorchBlock(TorchType type) {
        if (type == TorchType.REDSTONE_TORCH) {
            return Blocks.REDSTONE_WALL_TORCH;
        }
        if (type == TorchType.SOUL_TORCH) {
            return Blocks.SOUL_WALL_TORCH;
        }
        return Blocks.WALL_TORCH;
    }

    private PaleOakTorchHolder() {
    }
}

