package de.pnku.more_variants_pale_oak_backport.util;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class PaleOakVariantHolder {
    public static final String BARREL_FAMILY = "barrel";
    public static final String BED_FAMILY = "bed";
    public static final String BEEHIVE_FAMILY = "beehive";
    public static final String BOOKSHELF_FAMILY = "bookshelf";
    public static final String CHEST_FAMILY = "chest";
    public static final String CRAFTING_TABLE_FAMILY = "crafting_table";
    public static final String FLETCHING_TABLE_FAMILY = "fletching_table";
    public static final String GRINDSTONE_FAMILY = "grindstone";
    public static final String SHIELD_FAMILY = "shield";
    public static final String SMOKER_FAMILY = "smoker";
    public static final String LADDER_FAMILY = "ladder";
    public static final String RAIL_FAMILY = "rail";
    public static final String STICK_FAMILY = "stick";
    public static final String TORCH_FAMILY = "torch";
    public static final String WALL_TORCH_FAMILY = "wall_torch";

    public enum DefaultSubtype {
        DEFAULT
    }
    public enum ChestType {
        CHEST,
        TRAPPED_CHEST
    }

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

    private static final Map<String, EnumMap<WoodType, Map<Object, Block>>> BLOCKS_BY_FAMILY = new HashMap<>();
    private static final Map<String, EnumMap<WoodType, Map<Object, Item>>> ITEMS_BY_FAMILY = new HashMap<>();

    public static void setBlock(String family, WoodType woodType, Block block) {
        setBlock(family, woodType, DefaultSubtype.DEFAULT, block);
    }

    public static Block getBlock(String family, WoodType woodType) {
        return getBlock(family, woodType, DefaultSubtype.DEFAULT);
    }

    public static void setBlock(String family, WoodType woodType, Enum<?> subtype, Block block) {
        blocksFor(family, woodType).put(requireSubtype(subtype), block);
    }

    public static Block getBlock(String family, WoodType woodType, Enum<?> subtype) {
        Block block = blocksFor(family, woodType).get(requireSubtype(subtype));
        if (block == null) {
            throw new IllegalStateException("Tried to get block for family '" + family + "', wood type '" + woodType + "' and subtype: " + subtype
                    + " but it has not been registered");
        }
        return block;
    }

    public static void setItem(String family, WoodType woodType, Item item) {
        setItem(family, woodType, DefaultSubtype.DEFAULT, item);
    }

    public static Item getItem(String family, WoodType woodType) {
        return getItem(family, woodType, DefaultSubtype.DEFAULT);
    }

    public static void setItem(String family, WoodType woodType, Enum<?> subtype, Item item) {
        itemsFor(family, woodType).put(requireSubtype(subtype), item);
    }

    public static Item getItem(String family, WoodType woodType, Enum<?> subtype) {
        Item item = itemsFor(family, woodType).get(requireSubtype(subtype));
        if (item == null) {
            throw new IllegalStateException("Tried to get item for family '" + family + "', wood type '" + woodType + "' and subtype: " + subtype
                    + " but it has not been registered");
        }
        return item;
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

    private static Map<Object, Block> blocksFor(String family, WoodType woodType) {
        return BLOCKS_BY_FAMILY
                .computeIfAbsent(requireFamily(family), key -> new EnumMap<>(WoodType.class))
                .computeIfAbsent(requireWoodType(woodType), key -> new HashMap<>());
    }

    private static Map<Object, Item> itemsFor(String family, WoodType woodType) {
        return ITEMS_BY_FAMILY
                .computeIfAbsent(requireFamily(family), key -> new EnumMap<>(WoodType.class))
                .computeIfAbsent(requireWoodType(woodType), key -> new HashMap<>());
    }

    private static String requireFamily(String family) {
        if (family == null || family.isBlank()) {
            throw new IllegalArgumentException("family must not be blank");
        }
        return family;
    }

    private static WoodType requireWoodType(WoodType woodType) {
        if (woodType == null) {
            throw new IllegalArgumentException("woodType must not be null");
        }
        return woodType;
    }

    private static Object requireSubtype(Enum<?> subtype) {
        if (subtype == null) {
            throw new IllegalArgumentException("subtype must not be null");
        }
        return subtype;
    }

    private PaleOakVariantHolder() {
    }
}

