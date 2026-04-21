package de.pnku.more_variants_pale_oak_backport.util;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public enum WoodType {
    PALE_OAK("pale_oak");

    private final String namespace;
    private final String name;

    WoodType(String name) {
        this("minecraft", name);
    }

    WoodType(String namespace, String name) {
        this.namespace = namespace;
        this.name = name;
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

