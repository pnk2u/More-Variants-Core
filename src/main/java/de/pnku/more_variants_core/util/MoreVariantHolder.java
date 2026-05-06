package de.pnku.more_variants_core.util;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.item.*;
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

    public enum FrameType implements IMoreVariantSubType {
        PAINTING(EntityType.PAINTING, Items.PAINTING),
        ITEM_FRAME(EntityType.ITEM_FRAME, Items.ITEM_FRAME),
        GLOW_ITEM_FRAME(EntityType.GLOW_ITEM_FRAME, Items.GLOW_ITEM_FRAME);

        private final EntityType<? extends HangingEntity> entityType;
        private final Item vanillaItem;

        FrameType(EntityType<? extends HangingEntity> entityType, Item vanillaItem) {
            this.entityType = entityType;
            this.vanillaItem = vanillaItem;
        }

        public EntityType<? extends HangingEntity> entityType() {
            return entityType;
        }
        public String registrationType() {
            return this.name().toLowerCase();
        }
        public MoreVariantType variantType() {
            return MoreVariantType.FRAME;
        }
        public Block getVanillaBlock() {
            return this.variantType().getVanillaBlock();
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
    }

    public enum ToolType implements IMoreVariantSubType {
        WOODEN_SHOVEL(MaterialType.WOOD, true, Items.WOODEN_SHOVEL),
        WOODEN_PICKAXE(MaterialType.WOOD, false, Items.WOODEN_PICKAXE),
        WOODEN_AXE(MaterialType.WOOD, ToolTypeType.AXE, 6.0F, -3.2F, Items.WOODEN_AXE),
        WOODEN_HOE(MaterialType.WOOD, ToolTypeType.HOE, 0.0F, -3.0F, Items.WOODEN_HOE),
        STONE_SHOVEL(MaterialType.STONE, true, Items.STONE_SHOVEL),
        STONE_PICKAXE(MaterialType.STONE, false, Items.STONE_PICKAXE),
        STONE_AXE(MaterialType.STONE, ToolTypeType.AXE, 7.0F, -3.2F, Items.STONE_AXE),
        STONE_HOE(MaterialType.STONE, ToolTypeType.HOE, -1.0F, -2.0F, Items.STONE_HOE),
        DEEPSLATE_SHOVEL(MaterialType.DEEPSLATE, true, Items.STONE_SHOVEL),
        DEEPSLATE_PICKAXE(MaterialType.DEEPSLATE, false, Items.STONE_PICKAXE),
        DEEPSLATE_AXE(MaterialType.DEEPSLATE, ToolTypeType.AXE, 7.0F, -3.2F, Items.STONE_AXE),
        DEEPSLATE_HOE(MaterialType.DEEPSLATE, ToolTypeType.HOE, -1.0F, -2.0F, Items.STONE_HOE),
        BLACKSTONE_SHOVEL(MaterialType.BLACKSTONE, true, Items.STONE_SHOVEL),
        BLACKSTONE_PICKAXE(MaterialType.BLACKSTONE, false, Items.STONE_PICKAXE),
        BLACKSTONE_AXE(MaterialType.BLACKSTONE, ToolTypeType.AXE, 7.0F, -3.2F, Items.STONE_AXE),
        BLACKSTONE_HOE(MaterialType.BLACKSTONE, ToolTypeType.HOE, -1.0F, -2.0F, Items.STONE_HOE),
        IRON_SHOVEL(MaterialType.IRON, true, Items.IRON_SHOVEL),
        IRON_PICKAXE(MaterialType.IRON, false, Items.IRON_PICKAXE),
        IRON_AXE(MaterialType.IRON, ToolTypeType.AXE, 6.0F, -3.1F, Items.IRON_AXE),
        IRON_HOE(MaterialType.IRON, ToolTypeType.HOE, -2.0F, -1.0F, Items.IRON_HOE),
        GOLDEN_SHOVEL(MaterialType.GOLD, true, Items.GOLDEN_SHOVEL),
        GOLDEN_PICKAXE(MaterialType.GOLD, false, Items.GOLDEN_PICKAXE),
        GOLDEN_AXE(MaterialType.GOLD, ToolTypeType.AXE, 6.0F, -3.0F, Items.GOLDEN_AXE),
        GOLDEN_HOE(MaterialType.GOLD, ToolTypeType.HOE, 0.0F, -3.0F, Items.GOLDEN_HOE),
        DIAMOND_SHOVEL(MaterialType.DIAMOND, true, Items.DIAMOND_SHOVEL),
        DIAMOND_PICKAXE(MaterialType.DIAMOND, false, Items.DIAMOND_PICKAXE),
        DIAMOND_AXE(MaterialType.DIAMOND, ToolTypeType.AXE, 5.0F, -3.0F, Items.DIAMOND_AXE),
        DIAMOND_HOE(MaterialType.DIAMOND, ToolTypeType.HOE, -3.0F, 0.0F, Items.DIAMOND_HOE),
        NETHERITE_SHOVEL(MaterialType.NETHERITE, true, Items.NETHERITE_SHOVEL),
        NETHERITE_PICKAXE(MaterialType.NETHERITE, false, Items.NETHERITE_PICKAXE),
        NETHERITE_AXE(MaterialType.NETHERITE, ToolTypeType.AXE, 5.0F, -3.0F, Items.NETHERITE_AXE),
        NETHERITE_HOE(MaterialType.NETHERITE, ToolTypeType.HOE, -4.0F, 0.0F, Items.NETHERITE_HOE),
        BRUSH();

        private final MaterialType materialType;
        private final ToolTypeType toolTypeType;
        private final float attackDamage;
        private final float attackSpeed;
        private final Item vanillaItem;

        ToolType() {
            this(null, ToolTypeType.BRUSH, 0.0F, 0.0F, Items.BRUSH);
        }

        ToolType(MaterialType materialType, boolean isShovel, Item vanillaItem) {
            this(materialType, isShovel ? ToolTypeType.SHOVEL : ToolTypeType.PICKAXE, isShovel ? 1.5F : 1.0F, isShovel ? -3.0F : -2.8F, vanillaItem);
        }

        ToolType(MaterialType materialType, ToolTypeType toolTypeType, float aD, float aS, Item vanillaItem) {
            this.materialType = materialType;
            this.toolTypeType = toolTypeType;
            this.attackDamage = aD;
            this.attackSpeed = aS;
            this.vanillaItem = vanillaItem;
        }

        public MaterialType materialType() {
            return materialType;
        }
        public ToolTypeType toolTypeType() {
            return toolTypeType;
        }
        public float aD() {
            return attackDamage;
        }
        public float aS() {
            return attackSpeed;
        }
        public String registrationType() {
            return materialType.namePrefix() + "_" + toolTypeType.name().toLowerCase();
        }
        public MoreVariantType variantType() {
            return MoreVariantType.TOOL;
        }
        public Block getVanillaBlock() {
            return this.variantType().getVanillaBlock();
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
        public ToolType getToolTypeByToolTypeTypeAndMaterialType(ToolTypeType toolType, MaterialType materialType) {
            for (ToolType type : ToolType.values()) {
                if (type.toolTypeType == toolType && type.materialType == materialType) {
                    return type;
                }
            }
            throw new IllegalArgumentException("No tool type found for tool type '" + toolType + "' and material type '" + materialType + "'");
        }
        public boolean isAxe() {
            return toolTypeType == ToolTypeType.AXE;
        }
        public boolean isPickaxe() {
            return toolTypeType == ToolTypeType.PICKAXE;
        }
        public boolean isShovel() {
            return toolTypeType == ToolTypeType.SHOVEL;
        }
        public boolean isHoe() {
            return toolTypeType == ToolTypeType.HOE;
        }
        public boolean isBrush() {
            return toolTypeType == ToolTypeType.BRUSH;
        }

        public enum ToolTypeType {
            AXE,
            PICKAXE,
            SHOVEL,
            HOE,
            BRUSH
        }
    }

    public enum MaterialType {
        WOOD(Tiers.WOOD, "wooden", null),
        STONE(Tiers.STONE, "stone", Items.COBBLESTONE),
        DEEPSLATE(Tiers.STONE, "deepslate", Items.DEEPSLATE),
        BLACKSTONE(Tiers.STONE, "blackstone", Items.BLACKSTONE),
        GOLD(Tiers.GOLD, "golden", Items.GOLD_INGOT),
        IRON(Tiers.IRON, "iron", Items.IRON_INGOT),
        DIAMOND(Tiers.DIAMOND, "diamond", Items.DIAMOND),
        NETHERITE(Tiers.NETHERITE, "netherite", null);

        private final Tier tier;
        private final String namePrefix;
        private final Item ingredientItem;

        MaterialType(Tier tier, String namePrefix, Item ingredientItem) {
            this.tier = tier;
            this.namePrefix = namePrefix;
            this.ingredientItem = ingredientItem;
        }

        public Tier tier() {
            return tier;
        }
        public String namePrefix() {
            return namePrefix;
        }
        public Item ingredientItem() {
            return ingredientItem;
        }
        public boolean isNetherite() {
            return this == NETHERITE;
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

