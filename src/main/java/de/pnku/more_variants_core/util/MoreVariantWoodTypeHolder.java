package de.pnku.more_variants_core.util;

import java.util.ArrayList;
import java.util.List;
import static de.pnku.more_variants_core.MoreVariantsCore.LOGGER;
import static de.pnku.more_variants_core.util.VanillaWoodTypes.VALUES;

public class MoreVariantWoodTypeHolder {
    private static final List<MoreVariantWoodType> wood_types = new ArrayList<>();
    private static final List<MoreVariantWoodType> more_variant_wood_types = new ArrayList<>();
    private static final List<MoreVariantWoodType> vanilla_wood_types = new ArrayList<>();

    private static boolean initialized = false;
    public static void init() {
        more_variant_wood_types.forEach(woodType -> {
            if (!wood_types.contains(woodType)) wood_types.add(woodType);
        });
        addVanillaWoodTypes(VALUES);
        vanilla_wood_types.forEach(woodType -> {
            if (!wood_types.contains(woodType)) wood_types.add(woodType);
        });
        initialized = true;
    }

    public static void addMoreVariantWoodTypes(MoreVariantWoodType... newWoodTypes) {
        for (MoreVariantWoodType woodType : newWoodTypes) {
            if (!more_variant_wood_types.contains(woodType)) {
                int minimumId = VALUES.length + 2;
                if (woodType.getIntId() < minimumId) {
                    throw new IllegalArgumentException("Wood type '" + woodType.getName() + "' has an integer ID less than " + VALUES.length + ", which is reserved for Vanilla Wood Types.");
                }
                more_variant_wood_types.forEach(existingWoodType -> {
                    if (existingWoodType.getIntId() == woodType.getIntId()) {
                        throw new IllegalArgumentException("Wood type '" + woodType.getName() + "' has an integer ID that conflicts with existing wood type '" + existingWoodType.getName() + "'.");
                    }
                    if (existingWoodType.idString().equalsIgnoreCase(woodType.idString())) {
                        throw new IllegalArgumentException("Wood type '" + woodType.getName() + "' has an ID string that conflicts with existing wood type '" + existingWoodType.getName() + "'.");
                    }
                });
                more_variant_wood_types.add(woodType);
            }
        }
        LOGGER.info("Added " + newWoodTypes.length + " wood types. Total wood types: " + more_variant_wood_types.size());
    }

    public static void addVanillaWoodTypes(MoreVariantWoodType... newVanillaWoodTypes) {
        for (MoreVariantWoodType woodType : newVanillaWoodTypes) {
            if (!vanilla_wood_types.contains(woodType)) {
                vanilla_wood_types.add(woodType);
            }
        }
        LOGGER.info("Added " + newVanillaWoodTypes.length + " vanilla wood types. Total vanilla wood types: " + vanilla_wood_types.size());
    }

    public static MoreVariantWoodType getMoreVariantWoodTypeByName(String name) {
        for (MoreVariantWoodType woodType : more_variant_wood_types) {
            if (woodType.getName().equalsIgnoreCase(name)) {
                return woodType;
            }
        }
        LOGGER.warn("Wood type with name '" + name + "' not found in More Variant Wood Types.");
        return null;
    }

    public static MoreVariantWoodType getVanillaWoodTypeByName(String name) {
        for (MoreVariantWoodType woodType : vanilla_wood_types) {
            if (woodType.getName().equalsIgnoreCase(name)) {
                return woodType;
            }
        }
        LOGGER.warn("Wood type with name '" + name + "' not found in Vanilla Wood Types.");
        return null;
    }

    public static MoreVariantWoodType getWoodTypeByName(String name) {
        for (MoreVariantWoodType woodType : wood_types) {
            if (woodType.getName().equalsIgnoreCase(name)) {
                return woodType;
            }
        }
        LOGGER.warn("Wood type with name '" + name + "' not found in Wood Types.");
        return null;
    }

    public static List<MoreVariantWoodType> getMoreVariantWoodTypes() {
        if (!initialized) init();
        LOGGER.info("Retrieving wood types. Total wood types: " + more_variant_wood_types.size());
        return more_variant_wood_types;
    }

    public static List<MoreVariantWoodType> getVanillaWoodTypes() {
        if (!initialized) init();
        LOGGER.info("Retrieving vanilla wood types. Total vanilla wood types: " + vanilla_wood_types.size());
        return vanilla_wood_types;
    }

    public static List<MoreVariantWoodType> getAllWoodTypes() {
        if (!initialized) init();
        LOGGER.info("Retrieving all wood types. Total wood types: " + wood_types.size());
        return wood_types;
    }
}
