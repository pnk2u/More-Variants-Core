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
    public enum VariantType {
        BARREL,
        BED,
        BEEHIVE,
        BOOKSHELF,
        CAMPFIRE,
        CARTOGRAPHY_TABLE,
        CHEST,
        CHISELED_BOOKSHELF,
        COMPOSTER,
        CRAFTER,
        CRAFTING_TABLE,
        FLETCHING_TABLE,
        GRINDSTONE,
        JUKEBOX_NOTEBLOCK,
        LECTERN,
        LOOM,
        SHIELD,
        SMITHING_TABLE,
        SMOKER,
        WOODCUTTER,
        STICK,
        ARMOR_STAND,
        FRAME,
        LADDER,
        RAIL,
        TOOL,
        TORCH,
        WALL_TORCH,
        WEAPON;

        private final String registrationType;

        VariantType() {
            this.registrationType = this.name().toLowerCase();
        }

        public String registrationType() {
            return registrationType;
        }
    }

    public enum DefaultSubtype {
        DEFAULT
    }
    public enum ChestType {
        CHEST,
        TRAPPED_CHEST;
        public VariantType variantType() {
            return VariantType.CHEST;
        }
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
        public VariantType variantType() {
            return VariantType.SMOKER;
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
        public VariantType variantType() {
            return VariantType.RAIL;
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
        public VariantType variantType() {
            return VariantType.TORCH;
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
        public VariantType variantType() {
            return VariantType.GRINDSTONE;
        }
    }

    private static final Map<Enum<?>, EnumMap<WoodType, Map<Object, Block>>> BLOCKS_BY_TYPE = new HashMap<>();
    private static final Map<Enum<?>, EnumMap<WoodType, Map<Object, Item>>> ITEMS_BY_TYPE = new HashMap<>();

    public static void setBlock(Enum<?> variantType, WoodType woodType, Block block) {
        setBlock(variantType, woodType, DefaultSubtype.DEFAULT, block);
    }

    public static Block getBlock(Enum<?> variantType, WoodType woodType) {
        return getBlock(variantType, woodType, DefaultSubtype.DEFAULT);
    }

    public static void setBlock(Enum<?> variantType, WoodType woodType, Enum<?> subtype, Block block) {
        blocksFor(variantType, woodType).put(requireSubtype(subtype), block);
    }

    public static Block getBlock(Enum<?> variantType, WoodType woodType, Enum<?> subtype) {
        Block block = blocksFor(variantType, woodType).get(requireSubtype(subtype));
        if (block == null) {
            throw new IllegalStateException("Tried to get block for variantType '" + variantType + "', wood type '" + woodType + "' and subtype: " + subtype
                    + " but it has not been registered");
        }
        return block;
    }

    public static void setItem(Enum<?> variantType, WoodType woodType, Item item) {
        setItem(variantType, woodType, DefaultSubtype.DEFAULT, item);
    }

    public static Item getItem(Enum<?> variantType, WoodType woodType) {
        return getItem(variantType, woodType, DefaultSubtype.DEFAULT);
    }

    public static void setItem(Enum<?> variantType, WoodType woodType, Enum<?> subtype, Item item) {
        itemsFor(variantType, woodType).put(requireSubtype(subtype), item);
    }

    public static Item getItem(Enum<?> variantType, WoodType woodType, Enum<?> subtype) {
        Item item = itemsFor(variantType, woodType).get(requireSubtype(subtype));
        if (item == null) {
            throw new IllegalStateException("Tried to get item for variantType '" + variantType + "', wood type '" + woodType + "' and subtype: " + subtype
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

    private static Map<Object, Block> blocksFor(Enum<?> variantType, WoodType woodType) {
        return BLOCKS_BY_TYPE
                .computeIfAbsent(requireVariantType(variantType), key -> new EnumMap<>(WoodType.class))
                .computeIfAbsent(requireWoodType(woodType), key -> new HashMap<>());
    }

    private static Map<Object, Item> itemsFor(Enum<?> variantType, WoodType woodType) {
        return ITEMS_BY_TYPE
                .computeIfAbsent(requireVariantType(variantType), key -> new EnumMap<>(WoodType.class))
                .computeIfAbsent(requireWoodType(woodType), key -> new HashMap<>());
    }

    private static Enum<?> requireVariantType(Enum<?> variantType) {
        if (variantType == null) {
            throw new IllegalArgumentException("variantType must not be null");
        }
        return variantType;
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

