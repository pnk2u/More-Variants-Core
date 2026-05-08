package de.pnku.more_variants_core.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;

public class VanillaWoodTypes {
        public static final MoreVariantWoodType ACACIA = new MoreVariantWoodType("acacia", MapColor.COLOR_ORANGE, ResourceLocation.withDefaultNamespace("acacia_planks"), 1);
        public static final MoreVariantWoodType BAMBOO = new MoreVariantWoodType("bamboo", MapColor.COLOR_YELLOW, ResourceLocation.withDefaultNamespace("bamboo_planks"), 2);
        public static final MoreVariantWoodType BIRCH = new MoreVariantWoodType("birch", MapColor.SAND, ResourceLocation.withDefaultNamespace("birch_planks"), 3);
        public static final MoreVariantWoodType CHERRY = new MoreVariantWoodType("cherry", MapColor.TERRACOTTA_WHITE, ResourceLocation.withDefaultNamespace("cherry_planks"), 4);
        public static final MoreVariantWoodType CRIMSON = new MoreVariantWoodType("crimson", MapColor.CRIMSON_STEM, ResourceLocation.withDefaultNamespace("crimson_planks"), 5);
        public static final MoreVariantWoodType DARK_OAK = new MoreVariantWoodType("dark_oak", MapColor.COLOR_BROWN, ResourceLocation.withDefaultNamespace("dark_oak_planks"), 6);
        public static final MoreVariantWoodType JUNGLE = new MoreVariantWoodType("jungle", MapColor.DIRT, ResourceLocation.withDefaultNamespace("jungle_planks"), 7);
        public static final MoreVariantWoodType MANGROVE = new MoreVariantWoodType("mangrove", MapColor.COLOR_RED, ResourceLocation.withDefaultNamespace("mangrove_planks"), 8);
        public static final MoreVariantWoodType OAK = new MoreVariantWoodType("oak", MapColor.WOOD, ResourceLocation.withDefaultNamespace("oak_planks"), 9);
        public static final MoreVariantWoodType SPRUCE = new MoreVariantWoodType("spruce", MapColor.PODZOL, ResourceLocation.withDefaultNamespace("spruce_planks"), 10);
        public static final MoreVariantWoodType WARPED = new MoreVariantWoodType("warped", MapColor.WARPED_STEM, ResourceLocation.withDefaultNamespace("warped_planks"), 11);
        public static final MoreVariantWoodType[] VALUES = new MoreVariantWoodType[]{ACACIA, BAMBOO, BIRCH, CHERRY, CRIMSON, DARK_OAK, JUNGLE, MANGROVE, OAK, SPRUCE, WARPED};
}
