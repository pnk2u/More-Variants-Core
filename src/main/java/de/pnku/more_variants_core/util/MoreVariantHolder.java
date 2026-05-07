package de.pnku.more_variants_core.util;

import de.pnku.more_variants_core.util.MoreVariantMod;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class MoreVariantHolder {
    public enum MoreVariantType implements IMoreVariantType {
        ANIMAL_FEEDING_TROUGH(null,                     null,                       MoreVariantMod.ANIMAL_FEEDING_TROUGHS),
        BARREL(             Blocks.BARREL,              Items.BARREL,               MoreVariantMod.BARRELS),
        BED(                Blocks.WHITE_BED,           Items.WHITE_BED,            MoreVariantMod.BEDS),
        BEEHIVE(            Blocks.BEEHIVE,             Items.BEEHIVE,              MoreVariantMod.BEEHIVES),
        BOOKSHELF(          Blocks.BOOKSHELF,           Items.BOOKSHELF,            MoreVariantMod.BOOKSHELVES),
        CAMPFIRE(           Blocks.CAMPFIRE,            Items.CAMPFIRE,             MoreVariantMod.CAMPFIRES),
        CARTOGRAPHY_TABLE(  Blocks.CARTOGRAPHY_TABLE,   Items.CARTOGRAPHY_TABLE,    MoreVariantMod.CARTOGRAPHY_TABLES),
        CHEST(              Blocks.CHEST,               Items.CHEST,                MoreVariantMod.CHESTS),
        CHISELED_BOOKSHELF( Blocks.CHISELED_BOOKSHELF,  Items.CHISELED_BOOKSHELF,   MoreVariantMod.CHISELED_BOOKSHELVES),
        COMPOSTER(          Blocks.COMPOSTER,           Items.COMPOSTER,            MoreVariantMod.COMPOSTERS),
        CRAFTER(            Blocks.CRAFTER,             Items.CRAFTER,              MoreVariantMod.CRAFTERS),
        CRAFTING_TABLE(     Blocks.CRAFTING_TABLE,      Items.CRAFTING_TABLE,       MoreVariantMod.CRAFTING_TABLES),
        FLETCHING_TABLE(    Blocks.FLETCHING_TABLE,     Items.FLETCHING_TABLE,      MoreVariantMod.FLETCHING_TABLES),
        GRINDSTONE(         Blocks.GRINDSTONE,          Items.GRINDSTONE,           MoreVariantMod.GRINDSTONES),
        JUKEBOX_NOTEBLOCK(  Blocks.JUKEBOX,             Items.JUKEBOX,              MoreVariantMod.JUKEBOX_NOTEBLOCKS),
        LECTERN(            Blocks.LECTERN,             Items.LECTERN,              MoreVariantMod.LECTERNS),
        LOOM(               Blocks.LOOM,                Items.LOOM,                 MoreVariantMod.LOOMS),
        SHIELD(             null,                       Items.SHIELD,               MoreVariantMod.SHIELDS),
        SMITHING_TABLE(     Blocks.SMITHING_TABLE,      Items.SMITHING_TABLE,       MoreVariantMod.SMITHING_TABLES),
        SMOKER(             Blocks.SMOKER,              Items.SMOKER,               MoreVariantMod.SMOKERS),
        WOODCUTTER(         null,                       null,                       MoreVariantMod.WOODCUTTERS),
        STICK(              null,                       Items.STICK,                MoreVariantMod.STICKS),
        ARMOR_STAND(        null,                       Items.ARMOR_STAND,          MoreVariantMod.ARMOR_STANDS),
        FRAME(              null,                       Items.ITEM_FRAME,           MoreVariantMod.FRAMES),
        LADDER(             Blocks.LADDER,              Items.LADDER,               MoreVariantMod.LADDERS),
        RAIL(               Blocks.RAIL,                Items.RAIL,                 MoreVariantMod.RAILS),
        ROD(                null,                       Items.FISHING_ROD,          MoreVariantMod.FISHING_RODS),
        TOOL(               null,                       Items.WOODEN_PICKAXE,       MoreVariantMod.TOOLS),
        TORCH(              Blocks.TORCH,               Items.TORCH,                MoreVariantMod.TORCHES),
        WEAPON(             null,                       Items.WOODEN_SWORD,         MoreVariantMod.WEAPONS);

        private final Block vanillaBlock;
        private final Item vanillaItem;
        private final String modId;

        MoreVariantType(Block vanillaBlock, Item vanillaItem, MoreVariantMod mod) {
            this.vanillaBlock = vanillaBlock;
            this.vanillaItem = vanillaItem;
            this.modId = mod.modId();
        }

        public String modId() {
            return modId;
        }
        public ResourceLocation getRegistrationId(MoreVariantWoodType woodType) {
            return ResourceLocation.tryBuild(modId, String.join("_", woodType.getName(), this.registrationType()));
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
        default ResourceLocation getRegistrationId(MoreVariantWoodType woodType) {
            return ResourceLocation.tryBuild(variantType().modId(), String.join("_", woodType.getName(), this.registrationType()));
        }
        default Block getVanillaBlock() {
            return variantType().getVanillaBlock();
        }
        default Item getVanillaItem() {
            return variantType().getVanillaItem();
        }
    }
    public enum DefaultSubtype implements IMoreVariantSubType {
        DEFAULT;
        public String registrationType() {
            throw new UnsupportedOperationException("DefaultSubtype does not have a registration type");
        }
        public ResourceLocation getRegistrationId(MoreVariantWoodType woodType) {
            throw new UnsupportedOperationException("DefaultSubtype does not have an ID");
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
            return String.join("_", color.getName(), this.variantType().registrationType());
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


        public ResourceLocation getRegistrationId(MoreVariantWoodType woodType) {
            return ResourceLocation.tryBuild(this.variantType().modId, String.join("_", woodType.getName(), this.registrationType(), this.variantType().registrationType()));
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
        public ResourceLocation getRegistrationId(MoreVariantWoodType woodType) {
            return ResourceLocation.tryBuild(this.variantType().modId, String.join("_", woodType.getName(), this.registrationType(), this.variantType().registrationType()));
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
        private final boolean isWallTorch;
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

        private final Block stoneSlabBlock;

        GrindstoneType(Block stoneSlabBlock) {
            this.stoneSlabBlock = stoneSlabBlock;
        }

        public Block getStoneSlabBlock() {
            return stoneSlabBlock;
        }
        public ResourceLocation getRegistrationId(MoreVariantWoodType woodType) {
            return ResourceLocation.tryBuild(this.variantType().modId, String.join("_", woodType.getName(), this.registrationType(), this.variantType().registrationType()));
        }
        public MoreVariantType variantType() {
            return MoreVariantType.GRINDSTONE;
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
        public ResourceLocation getRegistrationId(MoreVariantWoodType woodType) {
            return ResourceLocation.tryBuild(this.variantType().modId, String.join("_", woodType.getName(), this.registrationType()));
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
        public MoreVariantType variantType() {
            return MoreVariantType.ROD;
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
        public MoreVariantType variantType() {
            return MoreVariantType.FRAME;
        }
        public Item getVanillaItem() {
            return vanillaItem;
        }
    }

    public interface ToolType extends IMoreVariantSubType {
        MaterialType materialType();
        ToolTypeType toolTypeType();
        float aD();
        float aS();
        Item getVanillaItem();

        @Override
        default MoreVariantType variantType() { return MoreVariantType.TOOL; }

        default boolean isAxe() { return toolTypeType() == ToolTypeType.AXE; }
        default boolean isPickaxe() { return toolTypeType() == ToolTypeType.PICKAXE; }
        default boolean isShovel() { return toolTypeType() == ToolTypeType.SHOVEL; }
        default boolean isHoe() { return toolTypeType() == ToolTypeType.HOE; }
        default boolean isBrush() { return toolTypeType() == ToolTypeType.BRUSH; }

        enum ToolTypeType { AXE, PICKAXE, SHOVEL, HOE, BRUSH }

        enum ShovelType implements ToolType {
            WOODEN_SHOVEL(MaterialType.WOOD, Items.WOODEN_SHOVEL),
            STONE_SHOVEL(MaterialType.STONE, Items.STONE_SHOVEL),
            BLACKSTONE_SHOVEL(MaterialType.BLACKSTONE, Items.STONE_SHOVEL),
            DEEPSLATE_SHOVEL(MaterialType.DEEPSLATE, Items.STONE_SHOVEL),
            GOLDEN_SHOVEL(MaterialType.GOLD, Items.GOLDEN_SHOVEL),
            IRON_SHOVEL(MaterialType.IRON, Items.IRON_SHOVEL),
            DIAMOND_SHOVEL(MaterialType.DIAMOND, Items.DIAMOND_SHOVEL),
            NETHERITE_SHOVEL(MaterialType.NETHERITE, Items.NETHERITE_SHOVEL);

            private final MaterialType materialType;
            private final float aD; private final float aS;
            private final Item vanillaItem;

            ShovelType(MaterialType materialType, Item vanillaItem) {
                this.materialType = materialType; this.aD = 1.5F; this.aS = -3.0F; this.vanillaItem = vanillaItem;
            }

            @Override public MaterialType materialType() { return materialType; }
            @Override public ToolTypeType toolTypeType() { return ToolTypeType.SHOVEL; }
            @Override public float aD() { return aD; }
            @Override public float aS() { return aS; }
            @Override public Item getVanillaItem() { return vanillaItem; }
        }

        enum PickaxeType implements ToolType {
            WOODEN_PICKAXE(MaterialType.WOOD, Items.WOODEN_PICKAXE),
            STONE_PICKAXE(MaterialType.STONE, Items.STONE_PICKAXE),
            BLACKSTONE_PICKAXE(MaterialType.BLACKSTONE, Items.STONE_PICKAXE),
            DEEPSLATE_PICKAXE(MaterialType.DEEPSLATE, Items.STONE_PICKAXE),
            GOLDEN_PICKAXE(MaterialType.GOLD, Items.GOLDEN_PICKAXE),
            IRON_PICKAXE(MaterialType.IRON, Items.IRON_PICKAXE),
            DIAMOND_PICKAXE(MaterialType.DIAMOND, Items.DIAMOND_PICKAXE),
            NETHERITE_PICKAXE(MaterialType.NETHERITE, Items.NETHERITE_PICKAXE);

            private final MaterialType materialType;
            private final float aD; private final float aS;
            private final Item vanillaItem;

            PickaxeType(MaterialType materialType, Item vanillaItem) {
                this.materialType = materialType; this.aD = 1.0F; this.aS = -2.8F; this.vanillaItem = vanillaItem;
            }

            @Override public MaterialType materialType() { return materialType; }
            @Override public ToolTypeType toolTypeType() { return ToolTypeType.PICKAXE; }
            @Override public float aD() { return aD; }
            @Override public float aS() { return aS; }
            @Override public Item getVanillaItem() { return vanillaItem; }
        }

        enum AxeType implements ToolType {
            WOODEN_AXE(MaterialType.WOOD, 6.0F, -3.2F, Items.WOODEN_AXE),
            STONE_AXE(MaterialType.STONE, 7.0F, -3.2F, Items.STONE_AXE),
            BLACKSTONE_AXE(MaterialType.BLACKSTONE, 7.0F, -3.2F, Items.STONE_AXE),
            DEEPSLATE_AXE(MaterialType.DEEPSLATE, 7.0F, -3.2F, Items.STONE_AXE),
            GOLDEN_AXE(MaterialType.GOLD, 6.0F, -3.0F, Items.GOLDEN_AXE),
            IRON_AXE(MaterialType.IRON, 6.0F, -3.1F, Items.IRON_AXE),
            DIAMOND_AXE(MaterialType.DIAMOND, 5.0F, -3.0F, Items.DIAMOND_AXE),
            NETHERITE_AXE(MaterialType.NETHERITE, 5.0F, -3.0F, Items.NETHERITE_AXE);

            private final MaterialType materialType;
            private final float aD; private final float aS;
            private final Item vanillaItem;

            AxeType(MaterialType materialType, float aD, float aS, Item vanillaItem) {
                this.materialType = materialType; this.aD = aD; this.aS = aS; this.vanillaItem = vanillaItem;
            }

            @Override public MaterialType materialType() { return materialType; }
            @Override public ToolTypeType toolTypeType() { return ToolTypeType.AXE; }
            @Override public float aD() { return aD; }
            @Override public float aS() { return aS; }
            @Override public Item getVanillaItem() { return vanillaItem; }
        }

        enum HoeType implements ToolType {
            WOODEN_HOE(MaterialType.WOOD, 0.0F, -3.0F, Items.WOODEN_HOE),
            STONE_HOE(MaterialType.STONE, -1.0F, -2.0F, Items.STONE_HOE),
            BLACKSTONE_HOE(MaterialType.BLACKSTONE, -1.0F, -2.0F, Items.STONE_HOE),
            DEEPSLATE_HOE(MaterialType.DEEPSLATE, -1.0F, -2.0F, Items.STONE_HOE),
            GOLDEN_HOE(MaterialType.GOLD, 0.0F, -3.0F, Items.GOLDEN_HOE),
            IRON_HOE(MaterialType.IRON, -2.0F, -1.0F, Items.IRON_HOE),
            DIAMOND_HOE(MaterialType.DIAMOND, -3.0F, 0.0F, Items.DIAMOND_HOE),
            NETHERITE_HOE(MaterialType.NETHERITE, -4.0F, 0.0F, Items.NETHERITE_HOE);

            private final MaterialType materialType;
            private final float aD; private final float aS;
            private final Item vanillaItem;

            HoeType(MaterialType materialType, float aD, float aS, Item vanillaItem) {
                this.materialType = materialType; this.aD = aD; this.aS = aS; this.vanillaItem = vanillaItem;
            }

            @Override public MaterialType materialType() { return materialType; }
            @Override public ToolTypeType toolTypeType() { return ToolTypeType.HOE; }
            @Override public float aD() { return aD; }
            @Override public float aS() { return aS; }
            @Override public Item getVanillaItem() { return vanillaItem; }
        }

        enum BrushType implements ToolType {
            BRUSH;
            BrushType() {}

            @Override public MaterialType materialType() {return MaterialType.WOOD;}
            @Override public ToolTypeType toolTypeType() {return ToolTypeType.BRUSH;}
            @Override public float aD() {return 0;}
            @Override public float aS() {return 0;}
            @Override public Item getVanillaItem() {return Items.BRUSH;}
        }

        static ToolType[] values() {
            List<ToolType> toolTypes = new ArrayList<>();
            for (int i = 0; i < ShovelType.values().length; i++) {
                toolTypes.add(ShovelType.values()[i]);
                toolTypes.add(PickaxeType.values()[i]);
                toolTypes.add(AxeType.values()[i]);
                toolTypes.add(HoeType.values()[i]);
            }
            toolTypes.add(BrushType.BRUSH);
            return toolTypes.toArray(new ToolType[0]);
        }

        default ToolType getToolTypeByToolTypeTypeAndMaterialType(ToolTypeType toolType, MaterialType materialType) {
            for (ToolType type : values()) {
                if (type.toolTypeType() == toolType && type.materialType() == materialType) return type;
            }
            throw new IllegalArgumentException("No tool type found for " + toolType + " " + materialType);
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

    public static ResourceLocation getRegistrationId(IMoreVariantType type, MoreVariantWoodType woodType) {
        return type.getRegistrationId(woodType);
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

