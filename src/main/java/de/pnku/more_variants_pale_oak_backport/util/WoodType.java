package de.pnku.more_variants_pale_oak_backport.util;

import net.minecraft.world.level.material.MapColor;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum WoodType {
    PALE_OAK("pale_oak", MapColor.QUARTZ);

    private final String namespace;
    private final String name;
    private final MapColor mapColor;

    WoodType(String name, MapColor mapColor) {
        this("minecraft", name, mapColor);
    }

    WoodType(String namespace, String name, MapColor mapColor) {
        this.namespace = namespace;
        this.name = name;
        this.mapColor = mapColor;
    }

    public String idString() {
        return namespace + ":" + name;
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

