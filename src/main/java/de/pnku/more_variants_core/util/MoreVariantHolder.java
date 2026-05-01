package de.pnku.more_variants_core.util;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class MoreVariantHolder {
    public enum MoreVariantType implements IMoreVariantType {
        BARREL(Blocks.BARREL, Items.BARREL),
        BED(Blocks.WHITE_BED, Items.WHITE_BED),
        BEEHIVE(Blocks.BEEHIVE, Items.BEEHIVE),
        BOOKSHELF(Blocks.BOOKSHELF, Items.BOOKSHELF),
        CAMPFIRE(Blocks.CAMPFIRE, Items.CAMPFIRE),
        CARTOGRAPHY_TABLE(Blocks.CARTOGRAPHY_TABLE, Items.CARTOGRAPHY_TABLE),
        CHEST(Blocks.CHEST, Items.CHEST),
        CHISELED_BOOKSHELF(Blocks.CHISELED_BOOKSHELF, Items.CHISELED_BOOKSHELF),
        COMPOSTER(Blocks.COMPOSTER, Items.COMPOSTER),
        CRAFTER(Blocks.CRAFTING_TABLE, Items.CRAFTING_TABLE),
        CRAFTING_TABLE(Blocks.CRAFTING_TABLE, Items.CRAFTING_TABLE),
        FLETCHING_TABLE(Blocks.FLETCHING_TABLE, Items.FLETCHING_TABLE),
        GRINDSTONE(Blocks.GRINDSTONE, Items.GRINDSTONE),
        JUKEBOX_NOTEBLOCK(Blocks.JUKEBOX, Items.JUKEBOX),
        LECTERN(Blocks.LECTERN, Items.LECTERN),
        LOOM(Blocks.LOOM, Items.LOOM),
        SHIELD(null, Items.SHIELD),
        SMITHING_TABLE(Blocks.SMITHING_TABLE, Items.SMITHING_TABLE),
        SMOKER(Blocks.SMOKER, Items.SMOKER),
        WOODCUTTER(Blocks.LECTERN, Items.LECTERN),
        STICK(null, Items.STICK),
        ARMOR_STAND(null, Items.ARMOR_STAND),
        FRAME(null, Items.ITEM_FRAME),
        LADDER(Blocks.LADDER, Items.LADDER),
        RAIL(Blocks.RAIL, Items.RAIL),
        ROD(null, Items.FISHING_ROD),
        TOOL(null, Items.WOODEN_PICKAXE),
        TORCH(Blocks.TORCH, Items.TORCH),
        WEAPON(null, Items.WOODEN_SWORD);

        private final Block vanillaBlock;
        private final Item vanillaItem;

        MoreVariantType(Block vanillaBlock, Item vanillaItem) {
            this.vanillaBlock = vanillaBlock;
            this.vanillaItem = vanillaItem;
        }

        public String registrationType() {
            return this.name().toLowerCase();
        }
        public @Nullable Block getVanillaBlock() {
            return vanillaBlock;
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
    }

    public interface IMoreVariantSubType extends IMoreVariantType {
        MoreVariantType variantType();
    }
    public enum DefaultSubtype implements IMoreVariantSubType {
        DEFAULT;
        public String registrationType() {
            throw new UnsupportedOperationException("DefaultSubtype does not have a registration type");
        }
        public MoreVariantType variantType() {
            throw new UnsupportedOperationException("DefaultSubtype does not have a variant type");
        }
        public Block getVanillaBlock() {
            throw new UnsupportedOperationException("DefaultSubtype does not have a vanilla block");
        }
        public Item getVanillaItem() {
            throw new UnsupportedOperationException("DefaultSubtype does not have a vanilla item");
        }
    }
    public enum BedColorType implements IMoreVariantSubType {
        RED(DyeColor.RED, Blocks.RED_BED, Items.RED_BED),
        BLACK(DyeColor.BLACK, Blocks.BLACK_BED, Items.BLACK_BED),
        WHITE(DyeColor.WHITE, Blocks.WHITE_BED, Items.WHITE_BED),
        ORANGE(DyeColor.ORANGE, Blocks.ORANGE_BED, Items.ORANGE_BED),
        MAGENTA(DyeColor.MAGENTA, Blocks.MAGENTA_BED, Items.MAGENTA_BED),
        LIGHT_BLUE(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_BED, Items.LIGHT_BLUE_BED),
        YELLOW(DyeColor.YELLOW, Blocks.YELLOW_BED, Items.YELLOW_BED),
        LIME(DyeColor.LIME, Blocks.LIME_BED, Items.LIME_BED),
        PINK(DyeColor.PINK, Blocks.PINK_BED, Items.PINK_BED),
        GRAY(DyeColor.GRAY, Blocks.GRAY_BED, Items.GRAY_BED),
        LIGHT_GRAY(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_BED, Items.LIGHT_GRAY_BED),
        CYAN(DyeColor.CYAN, Blocks.CYAN_BED, Items.CYAN_BED),
        PURPLE(DyeColor.PURPLE, Blocks.PURPLE_BED, Items.PURPLE_BED),
        BLUE(DyeColor.BLUE, Blocks.BLUE_BED, Items.BLUE_BED),
        BROWN(DyeColor.BROWN, Blocks.BROWN_BED, Items.BROWN_BED),
        GREEN(DyeColor.GREEN, Blocks.GREEN_BED, Items.GREEN_BED);

        private final DyeColor color;
        private final Block vanillaBlock;
        private final Item vanillaItem;

        BedColorType(DyeColor color, Block vanillaBlock, Item vanillaItem) {
            this.color = color;
            this.vanillaBlock = vanillaBlock;
            this.vanillaItem = vanillaItem;
        }

        public DyeColor color() {
            return color;
        }
        public String registrationType() {
            return this.name().toLowerCase();
        }
        public MoreVariantType variantType() {
            return MoreVariantType.BED;
        }
        public Block getVanillaBlock() {
            return vanillaBlock;
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
    }
    public enum ChestType implements IMoreVariantSubType {
        CHEST(Blocks.CHEST, Items.CHEST),
        TRAPPED_CHEST(Blocks.TRAPPED_CHEST, Items.TRAPPED_CHEST);

        private final Block vanillaBlock;
        private final Item vanillaItem;

        ChestType(Block vanillaBlock, Item vanillaItem) {
            this.vanillaBlock = vanillaBlock;
            this.vanillaItem = vanillaItem;
        }

        public String registrationType() {return this.name().toLowerCase();}
        public MoreVariantType variantType() {
            return MoreVariantType.CHEST;
        }
        public Block getVanillaBlock() {
            return vanillaBlock;
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
    }
    public enum SmokerType implements IMoreVariantSubType {
        COBBLESTONE,
        DEEPSLATE,
        BLACKSTONE;


        public String registrationType() {
            return this.name().toLowerCase();
        }
        public MoreVariantType variantType() {
            return MoreVariantType.SMOKER;
        }
        public Block getVanillaBlock() {
            return this.variantType().getVanillaBlock();
        }
        public Item getVanillaItem() {
            return this.variantType().getVanillaItem();
        }
    }

    public enum RailType implements IMoreVariantSubType {
        RAIL("", Blocks.RAIL, Items.RAIL),
        ACTIVATOR_RAIL("activator", Blocks.ACTIVATOR_RAIL, Items.ACTIVATOR_RAIL),
        DETECTOR_RAIL("detector", Blocks.DETECTOR_RAIL, Items.DETECTOR_RAIL),
        POWERED_RAIL("powered", Blocks.POWERED_RAIL, Items.POWERED_RAIL);

        private final String registrationType;
        private final Block vanillaBlock;
        private final Item vanillaItem;

        RailType(String registrationType, Block vanillaBlock, Item vanillaItem) {
            this.registrationType = registrationType;
            this.vanillaBlock = vanillaBlock;
            this.vanillaItem = vanillaItem;
        }

        public String registrationType() {
            return registrationType;
        }
        public MoreVariantType variantType() {
            return MoreVariantType.RAIL;
        }
        public Block getVanillaBlock() {
            return vanillaBlock;
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
    }

    public enum TorchType implements IMoreVariantSubType {
        TORCH("torch", "wall_torch", Blocks.TORCH, Items.TORCH),
        WALL_TORCH(TORCH, Blocks.WALL_TORCH),
        SOUL_TORCH("soul_torch", "soul_wall_torch", Blocks.SOUL_TORCH, Items.SOUL_TORCH),
        SOUL_WALL_TORCH(SOUL_TORCH, Blocks.SOUL_WALL_TORCH),
        REDSTONE_TORCH("redstone_torch", "redstone_wall_torch", Blocks.REDSTONE_TORCH, Items.REDSTONE_TORCH),
        REDSTONE_WALL_TORCH(REDSTONE_TORCH, Blocks.REDSTONE_WALL_TORCH);

        private final String torchName;
        private final String wallTorchName;
        private final TorchType baseTorchType;
        private boolean isWallTorch;
        private final Block vanillaBlock;
        private final Item vanillaItem;

        TorchType(String torchName, String wallTorchName, Block vanillaBlock, Item vanillaItem) {
            this.torchName = torchName;
            this.wallTorchName = wallTorchName;
            this.baseTorchType = this;
            this.isWallTorch = false;
            this.vanillaBlock = vanillaBlock;
            this.vanillaItem = vanillaItem;
        }
        TorchType(TorchType baseTorchType, Block vanillaBlock) {
            this.torchName = baseTorchType.torchName;
            this.wallTorchName = baseTorchType.wallTorchName;
            this.baseTorchType = baseTorchType;
            this.isWallTorch = true;
            this.vanillaBlock = vanillaBlock;
            this.vanillaItem = baseTorchType.vanillaItem;
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
        public MoreVariantType variantType() {
            return MoreVariantType.TORCH;
        }
        public Block getVanillaBlock() {
            return vanillaBlock;
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
    }

    public enum GrindstoneType implements IMoreVariantSubType {
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
        public MoreVariantType variantType() {
            return MoreVariantType.GRINDSTONE;
        }
        public Block getVanillaBlock() {
            return this.variantType().getVanillaBlock();
        }
        public Item getVanillaItem() {
            return this.variantType().getVanillaItem();
        }
    }

    public enum JukeboxNoteblockType implements IMoreVariantSubType {
        JUKEBOX(Blocks.JUKEBOX, Items.JUKEBOX),
        NOTEBLOCK(Blocks.NOTE_BLOCK, Items.NOTE_BLOCK);

        private final Block vanillaBlock;
        private final Item vanillaItem;

        JukeboxNoteblockType(Block vanillaBlock, Item vanillaItem) {
            this.vanillaBlock = vanillaBlock;
            this.vanillaItem = vanillaItem;
        }

        public String registrationType() {
            return this.name().toLowerCase();
        }
        public MoreVariantType variantType() {
            return MoreVariantType.JUKEBOX_NOTEBLOCK;
        }
        public Block getVanillaBlock() {
            return vanillaBlock;
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
    }

    public enum RodType implements IMoreVariantSubType {
        FISHING_ROD("fish", Items.FISHING_ROD),
        CARROT_ON_A_STICK("pig", Items.CARROT_ON_A_STICK),
        WARPED_FUNGUS_ON_A_STICK("strider", Items.WARPED_FUNGUS_ON_A_STICK);

        private final String entityType;
        private final Item vanillaItem;

        RodType(String entityType, Item vanillaItem) {
            this.entityType = entityType;
            this.vanillaItem = vanillaItem;
        }

        public String entityType() {
            return entityType;
        }
        public String registrationType() {
            return this.name().toLowerCase();
        }
        public MoreVariantType variantType() {
            return MoreVariantType.ROD;
        }
        public Block getVanillaBlock() {
            return this.variantType().getVanillaBlock();
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
    }

    private static final Map<MoreVariantType, Map<MoreVariantWoodType, Map<Object, Block>>> BLOCKS_BY_TYPE = new HashMap<>();
    private static final Map<MoreVariantType, Map<MoreVariantWoodType, Map<Object, Item>>> ITEMS_BY_TYPE = new HashMap<>();


    public static void setBlock(IMoreVariantType type, MoreVariantWoodType woodType, Block block) {
        MoreVariantType variantType = typeToVariantType(type);
        IMoreVariantSubType variantSubType = typeToVariantSubType(type);
        blocksFor(variantType, woodType).putIfAbsent(requireSubtype(variantSubType), block);
    }

    public static Block getBlock(IMoreVariantType type, MoreVariantWoodType woodType) {
        return getBlock(type, woodType, false);
    }
    public static Block getBlock(IMoreVariantType type, MoreVariantWoodType woodType, boolean allowNull) {
        MoreVariantType variantType = typeToVariantType(type);
        IMoreVariantSubType variantSubType = typeToVariantSubType(type);
        Block block = blocksFor(variantType, woodType).get(requireSubtype(variantSubType));
        if (block == null && !allowNull) {
            throw new IllegalStateException("Tried to get block for variantType '" + variantType + "', wood type '" + woodType.getName() + "' and subtype: " + variantSubType
                    + " but it has not been registered");
        }
        return block;
    }

    public static void setItem(IMoreVariantType type, MoreVariantWoodType woodType, Item item) {
        MoreVariantType variantType = typeToVariantType(type);
        IMoreVariantSubType variantSubType = typeToVariantSubType(type);
        itemsFor(variantType, woodType).putIfAbsent(requireSubtype(variantSubType), item);
    }

    public static Item getItem(IMoreVariantType type, MoreVariantWoodType woodType) {
        return getItem(type, woodType, false);
    }
    public static Item getItem(IMoreVariantType type, MoreVariantWoodType woodType, boolean allowNull) {
        MoreVariantType variantType = typeToVariantType(type);
        IMoreVariantSubType variantSubType = typeToVariantSubType(type);
        Item item = itemsFor(variantType, woodType).get(requireSubtype(variantSubType));
        if (item == null && !allowNull) {
            throw new IllegalStateException("Tried to get item for variantType '" + variantType + "', wood type '" + woodType.getName() + "' and subtype: " + variantSubType
                    + " but it has not been registered");
        }
        return item;
    }

    private static Map<Object, Block> blocksFor(MoreVariantType variantType, MoreVariantWoodType woodType) {
        return BLOCKS_BY_TYPE
                .computeIfAbsent(requireVariantType(variantType), key -> new HashMap<>())
                .computeIfAbsent(requireWoodType(woodType), key -> new HashMap<>());
    }

    private static Map<Object, Item> itemsFor(MoreVariantType variantType, MoreVariantWoodType woodType) {
        return ITEMS_BY_TYPE
                .computeIfAbsent(requireVariantType(variantType), key -> new HashMap<>())
                .computeIfAbsent(requireWoodType(woodType), key -> new HashMap<>());
    }

    private static MoreVariantType requireVariantType(MoreVariantType variantType) {
        if (variantType == null) {
            throw new IllegalArgumentException("MoreVariantType must not be null");
        }
        return variantType;
    }

    private static MoreVariantWoodType requireWoodType(MoreVariantWoodType woodType) {
        if (woodType == null) {
            throw new IllegalArgumentException("WoodType must not be null");
        }
        return woodType;
    }

    private static IMoreVariantSubType requireSubtype(IMoreVariantSubType variantSubType) {
        if (variantSubType == null) {
            throw new IllegalArgumentException("IMoreVariantSubType must not be null");
        }
        return variantSubType;
    }

    private static MoreVariantType typeToVariantType(IMoreVariantType type) {
        if (type instanceof MoreVariantType) {
            return (MoreVariantType) type;
        } else if (type instanceof IMoreVariantSubType) {
            return ((IMoreVariantSubType) type).variantType();
        } else {
            throw new IllegalArgumentException("Type must be either a MoreVariantType or IMoreVariantSubType");
        }
    }

    private static IMoreVariantSubType typeToVariantSubType(IMoreVariantType type) {
        if (type instanceof IMoreVariantSubType) {
            return (IMoreVariantSubType) type;
        } else if (type instanceof MoreVariantType) {
            return DefaultSubtype.DEFAULT;
        } else {
            throw new IllegalArgumentException("Type must be either a MoreVariantType or IMoreVariantSubType");
        }
    }

    private MoreVariantHolder() {}
}

