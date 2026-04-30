package de.pnku.more_variants_core.util;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public final class MoreVariantHolder {
    public enum VariantType implements IVariantType {
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
        ROD,
        TOOL,
        TORCH,
        WALL_TORCH,
        WEAPON;

        public String registrationType() {
            return this.name().toLowerCase();
        }
    }

    public interface IVariantSubType extends IVariantType {
        VariantType variantType();
    }
    public enum DefaultSubtype implements IVariantSubType {
        DEFAULT;
        public String registrationType() {
            throw new UnsupportedOperationException("DefaultSubtype does not have a registration type");
        }
        public VariantType variantType() {
            throw new UnsupportedOperationException("DefaultSubtype does not have a variant type");
        }
    }
    public enum BedColorType implements IVariantSubType {
        RED(DyeColor.RED),
        BLACK(DyeColor.BLACK),
        WHITE(DyeColor.WHITE),
        ORANGE(DyeColor.ORANGE),
        MAGENTA(DyeColor.MAGENTA),
        LIGHT_BLUE(DyeColor.LIGHT_BLUE),
        YELLOW(DyeColor.YELLOW),
        LIME(DyeColor.LIME),
        PINK(DyeColor.PINK),
        GRAY(DyeColor.GRAY),
        LIGHT_GRAY(DyeColor.LIGHT_GRAY),
        CYAN(DyeColor.CYAN),
        PURPLE(DyeColor.PURPLE),
        BLUE(DyeColor.BLUE),
        BROWN(DyeColor.BROWN),
        GREEN(DyeColor.GREEN);

        private final String registrationType;
        private final DyeColor color;

        BedColorType(DyeColor color) {
            this.registrationType = this.name().toLowerCase();
            this.color = color;
        }

        public DyeColor color() {
            return color;
        }
        public String registrationType() {
            return registrationType;
        }
        public VariantType variantType() {
            return VariantType.BED;
        }
    }
    public enum ChestType implements IVariantSubType {
        CHEST,
        TRAPPED_CHEST;

        public String registrationType() {return this.name().toLowerCase();}
        public VariantType variantType() {
            return VariantType.CHEST;
        }
    }
    public enum SmokerType implements IVariantSubType {
        COBBLESTONE,
        DEEPSLATE,
        BLACKSTONE;

        public String registrationType() {
            return this.name().toLowerCase();
        }
        public VariantType variantType() {
            return VariantType.SMOKER;
        }
    }

    public enum RailType implements IVariantSubType {
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

    public enum TorchType implements IVariantSubType {
        TORCH("torch", "wall_torch"),
        WALL_TORCH(TORCH),
        SOUL_TORCH("soul_torch", "soul_wall_torch"),
        SOUL_WALL_TORCH(SOUL_TORCH),
        REDSTONE_TORCH("redstone_torch", "redstone_wall_torch"),
        REDSTONE_WALL_TORCH(REDSTONE_TORCH);

        private final String torchName;
        private final String wallTorchName;
        private final TorchType baseTorchType;
        private boolean isWallTorch;

        TorchType(String torchName, String wallTorchName) {
            this.torchName = torchName;
            this.wallTorchName = wallTorchName;
            this.baseTorchType = this;
            this.isWallTorch = false;
        }
        TorchType(TorchType baseTorchType) {
            this.torchName = baseTorchType.torchName;
            this.wallTorchName = baseTorchType.wallTorchName;
            this.baseTorchType = baseTorchType;
            this.isWallTorch = true;
        }

        public String torchName() {
            return torchName;
        }
        public String wallTorchName() {
            return wallTorchName;
        }
        public TorchType getBaseTorchType() {
            return baseTorchType;
        }
        public boolean isWallTorch() {
            return isWallTorch;
        }
        public SimpleParticleType getParticleType() {
            return this == SOUL_TORCH ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME;
        }
        public String registrationType() {
            return torchName;
        }
        public VariantType variantType() {
            return VariantType.TORCH;
        }
    }

    public enum GrindstoneType implements IVariantSubType {
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

        public Block getStoneSlabBlock() {
            return stoneSlabBlock;
        }
        public String blockIdSuffix() {
            return blockIdSuffix;
        }
        public String registrationType() {
            return registrationType;
        }
        public VariantType variantType() {
            return VariantType.GRINDSTONE;
        }
    }

    public enum JukeboxNoteblockType implements IVariantSubType {
        JUKEBOX,
        NOTEBLOCK;

        public String registrationType() {
            return this.name().toLowerCase();
        }
        public VariantType variantType() {
            return VariantType.JUKEBOX_NOTEBLOCK;
        }
    }

    public enum RodType implements IVariantSubType {
        FISHING_ROD("fish"),
        CARROT_ON_A_STICK("pig"),
        WARPED_FUNGUS_ON_A_STICK("strider");

        private final String entityType;

        RodType(String entityType) {
            this.entityType = entityType;
        }

        public String entityType() {
            return entityType;
        }
        public String registrationType() {
            return this.name().toLowerCase();
        }
        public VariantType variantType() {
            return VariantType.ROD;
        }
    }

    private static final Map<VariantType, Map<WoodType, Map<Object, Block>>> BLOCKS_BY_TYPE = new HashMap<>();
    private static final Map<VariantType, Map<WoodType, Map<Object, Item>>> ITEMS_BY_TYPE = new HashMap<>();


    public static void setBlock(IVariantType type, WoodType woodType, Block block) {
        VariantType variantType = typeToVariantType(type);
        IVariantSubType variantSubType = typeToVariantSubType(type);
        blocksFor(variantType, woodType).put(requireSubtype(variantSubType), block);
    }

    public static Block getBlock(IVariantType type, WoodType woodType) {
        VariantType variantType = typeToVariantType(type);
        IVariantSubType variantSubType = typeToVariantSubType(type);
        Block block = blocksFor(variantType, woodType).get(requireSubtype(variantSubType));
        if (block == null) {
            throw new IllegalStateException("Tried to get block for variantType '" + variantType + "', wood type '" + woodType + "' and subtype: " + variantSubType
                    + " but it has not been registered");
        }
        return block;
    }

    public static void setItem(IVariantType type, WoodType woodType, Item item) {
        VariantType variantType = typeToVariantType(type);
        IVariantSubType variantSubType = typeToVariantSubType(type);
        itemsFor(variantType, woodType).put(requireSubtype(variantSubType), item);
    }

    public static Item getItem(IVariantType type, WoodType woodType) {
        VariantType variantType = typeToVariantType(type);
        IVariantSubType variantSubType = typeToVariantSubType(type);
        Item item = itemsFor(variantType, woodType).get(requireSubtype(variantSubType));
        if (item == null) {
            throw new IllegalStateException("Tried to get item for variantType '" + variantType + "', wood type '" + woodType + "' and subtype: " + variantSubType
                    + " but it has not been registered");
        }
        return item;
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

    private static Map<Object, Block> blocksFor(VariantType variantType, WoodType woodType) {
        return BLOCKS_BY_TYPE
                .computeIfAbsent(requireVariantType(variantType), key -> new HashMap<>())
                .computeIfAbsent(requireWoodType(woodType), key -> new HashMap<>());
    }

    private static Map<Object, Item> itemsFor(VariantType variantType, WoodType woodType) {
        return ITEMS_BY_TYPE
                .computeIfAbsent(requireVariantType(variantType), key -> new HashMap<>())
                .computeIfAbsent(requireWoodType(woodType), key -> new HashMap<>());
    }

    private static VariantType requireVariantType(VariantType variantType) {
        if (variantType == null) {
            throw new IllegalArgumentException("VariantType must not be null");
        }
        return variantType;
    }

    private static WoodType requireWoodType(WoodType woodType) {
        if (woodType == null) {
            throw new IllegalArgumentException("WoodType must not be null");
        }
        return woodType;
    }

    private static IVariantSubType requireSubtype(IVariantSubType variantSubType) {
        if (variantSubType == null) {
            throw new IllegalArgumentException("IVariantSubType must not be null");
        }
        return variantSubType;
    }

    private static VariantType typeToVariantType(IVariantType type) {
        if (type instanceof VariantType) {
            return (VariantType) type;
        } else if (type instanceof IVariantSubType) {
            return ((IVariantSubType) type).variantType();
        } else {
            throw new IllegalArgumentException("Type must be either a VariantType or IVariantSubType");
        }
    }

    private static IVariantSubType typeToVariantSubType(IVariantType type) {
        if (type instanceof IVariantSubType) {
            return (IVariantSubType) type;
        } else if (type instanceof VariantType) {
            return DefaultSubtype.DEFAULT;
        } else {
            throw new IllegalArgumentException("Type must be either a VariantType or IVariantSubType");
        }
    }

    private MoreVariantHolder() {}
}

