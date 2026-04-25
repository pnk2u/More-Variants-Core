package de.pnku.more_variants_pale_oak_backport.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import static com.blackgear.vanillabackport.common.registries.ModBlocks.PALE_OAK_PLANKS;

public enum WoodType {
    PALE_OAK("pale_oak", MapColor.QUARTZ, PALE_OAK_PLANKS.get());

    private final String namespace;
    private final String name;
    private final String idString;
    private final MapColor mapColor;
    private final Block planksBlock;

    WoodType(String name, MapColor mapColor, Block planksBlock) {
        this("minecraft", name, mapColor, planksBlock);
    }

    WoodType(String namespace, String name, MapColor mapColor, Block planksBlock) {
        this.namespace = namespace;
        this.name = name;
        this.idString = namespace + ":" + name;
        this.mapColor = mapColor;
        this.planksBlock = planksBlock;
    }

    public String idString() {
        return idString;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public MapColor getMapColor() {
        return mapColor;
    }

    public Block getPlanksBlock() {
        return planksBlock;
    }

    public static Optional<WoodType> fromId(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }

        String normalizedId = id.toLowerCase(Locale.ROOT);
        final String canonicalId;
        if (!normalizedId.contains(":")) {
            canonicalId = "minecraft:" + normalizedId;
        } else {
            canonicalId = normalizedId;
        }

        return Arrays.stream(values())
                .filter(woodType -> canonicalId.equals(woodType.idString()))
                .findFirst();
    }
}

