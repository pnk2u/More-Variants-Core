package de.pnku.more_variants_core.util;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import static de.pnku.more_variants_core.util.MoreVariantVanillaWoodTypes.MoreVariantVanillaWoodType.buildMoreVariantVanillaWoodType;

public class MoreVariantVanillaWoodTypes {
        public static final MoreVariantWoodType ACACIA = buildMoreVariantVanillaWoodType(WoodType.ACACIA, MapColor.COLOR_ORANGE, 1);
        public static final MoreVariantWoodType BAMBOO = buildMoreVariantVanillaWoodType(WoodType.BAMBOO, MapColor.COLOR_YELLOW, SoundType.BAMBOO_WOOD, 2);
        public static final MoreVariantWoodType BIRCH = buildMoreVariantVanillaWoodType(WoodType.BIRCH, MapColor.SAND, 3);
        public static final MoreVariantWoodType CHERRY = buildMoreVariantVanillaWoodType(WoodType.CHERRY, MapColor.TERRACOTTA_WHITE, SoundType.CHERRY_WOOD, 4);
        public static final MoreVariantWoodType CRIMSON = buildMoreVariantVanillaWoodType(WoodType.CRIMSON, MapColor.CRIMSON_STEM, SoundType.NETHER_WOOD, 5);
        public static final MoreVariantWoodType DARK_OAK = buildMoreVariantVanillaWoodType(WoodType.DARK_OAK, MapColor.COLOR_BROWN, 6);
        public static final MoreVariantWoodType JUNGLE = buildMoreVariantVanillaWoodType(WoodType.JUNGLE, MapColor.DIRT, 7);
        public static final MoreVariantWoodType MANGROVE = buildMoreVariantVanillaWoodType(WoodType.MANGROVE, MapColor.COLOR_RED, 8);
        public static final MoreVariantWoodType OAK = buildMoreVariantVanillaWoodType(WoodType.OAK, MapColor.WOOD, 9);
        public static final MoreVariantWoodType SPRUCE = buildMoreVariantVanillaWoodType(WoodType.SPRUCE, MapColor.PODZOL, 10);
        public static final MoreVariantWoodType WARPED = buildMoreVariantVanillaWoodType(WoodType.WARPED, MapColor.WARPED_STEM, SoundType.NETHER_WOOD, 11);
        public static final MoreVariantWoodType[] VALUES = new MoreVariantWoodType[]{ACACIA, BAMBOO, BIRCH, CHERRY, CRIMSON, DARK_OAK, JUNGLE, MANGROVE, OAK, SPRUCE, WARPED};

        public static MoreVariantWoodType getLast() {
            return VALUES[VALUES.length - 1];
        }

        public static class MoreVariantVanillaWoodType {
                public static MoreVariantWoodType buildMoreVariantVanillaWoodType(WoodType woodType, MapColor mapColor, int id) {
                        String name = woodType.name().toLowerCase();
                        return new MoreVariantWoodType(name, mapColor, id);
                }

                public static MoreVariantWoodType buildMoreVariantVanillaWoodType(WoodType woodType, MapColor mapColor, SoundType soundType, int id) {
                        String name = woodType.name().toLowerCase();
                        return new MoreVariantWoodType(name, mapColor, soundType, id);
                }
        }
}
