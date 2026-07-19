package de.pnku.more_variants_core.util;

import java.util.*;

import static de.pnku.more_variants_core.MoreVariantsCore.LOGGER;
import static de.pnku.more_variants_core.util.MoreVariantVanillaWoodTypes.VALUES;

public class MoreVariantWoodTypeHolder {
    private static final Set<MoreVariantWoodType> wood_types = new LinkedHashSet<>();
    private static final Set<MoreVariantWoodType> more_variant_wood_types = new LinkedHashSet<>();
    private static final Set<MoreVariantWoodType> vanilla_wood_types = new LinkedHashSet<>();

    private static final Map<String, MoreVariantWoodType> wood_types_by_name = new LinkedHashMap<>();
    private static final Map<String, MoreVariantWoodType> more_variant_wood_types_by_name = new LinkedHashMap<>();
    private static final Map<String, MoreVariantWoodType> vanilla_wood_types_by_name = new LinkedHashMap<>();

    private static final Map<Integer, MoreVariantWoodType> more_variant_wood_types_by_int_id = new HashMap<>();
    private static final Map<String, MoreVariantWoodType> more_variant_wood_types_by_id_string = new HashMap<>();

    private static final Set<BiomeSpawnItemVariant> biome_spawn_item_variants = new LinkedHashSet<>();

    private static boolean initialized = false;

    public static void init() {
        if (initialized) {
            return;
        }

        LOGGER.debug("Initializing Wood Types...");
        LOGGER.info("Initialized More Variant Wood Types: " + more_variant_wood_types.stream().map(MoreVariantWoodType::getName).toList());

        addVanillaWoodTypes(VALUES);
        LOGGER.debug("Initialized Vanilla Wood Types: " + vanilla_wood_types.stream().map(MoreVariantWoodType::getName).toList());

        rebuildAllWoodTypes();
        initialized = true;
    }

    public static void addMoreVariantWoodTypes(MoreVariantWoodType... newWoodTypes) {
        for (MoreVariantWoodType woodType : newWoodTypes) {
            if (more_variant_wood_types.contains(woodType)) {
                continue;
            }

            int minimumId = VALUES.length + 2;
            if (woodType.getIntId() < minimumId) {
                throw new IllegalArgumentException("Wood Type '" + woodType.getName() + "' has an integer ID less than " + minimumId + ", which is reserved for Vanilla Wood Types.");
            }

            MoreVariantWoodType existingByIntId = more_variant_wood_types_by_int_id.get(woodType.getIntId());
            if (existingByIntId != null) {
                throw new IllegalArgumentException("Wood Type '" + woodType.getName() + "' has an integer ID that conflicts with existing Wood Type '" + existingByIntId.getName() + "'.");
            }

            String normalizedIdString = normalize(woodType.getIdString());
            MoreVariantWoodType existingByIdString = more_variant_wood_types_by_id_string.get(normalizedIdString);
            if (existingByIdString != null) {
                throw new IllegalArgumentException("Wood Type '" + woodType.getName() + "' has an ID string that conflicts with existing Wood Type '" + existingByIdString.getName() + "'.");
            }

            more_variant_wood_types.add(woodType);
            more_variant_wood_types_by_name.putIfAbsent(normalize(woodType.getName()), woodType);
            more_variant_wood_types_by_int_id.put(woodType.getIntId(), woodType);
            more_variant_wood_types_by_id_string.put(normalizedIdString, woodType);

            if (initialized) {
                addToAllWoodTypes(woodType);
            }
        }

        LOGGER.debug("Added " + newWoodTypes.length + " More Variant Wood Types. Total: " + more_variant_wood_types.size());
    }

    public static void addVanillaWoodTypes(MoreVariantWoodType... newVanillaWoodTypes) {
        for (MoreVariantWoodType woodType : newVanillaWoodTypes) {
            if (vanilla_wood_types.add(woodType)) {
                vanilla_wood_types_by_name.putIfAbsent(normalize(woodType.getName()), woodType);
                if (initialized) {
                    addToAllWoodTypes(woodType);
                }
            }
        }

        LOGGER.debug("Added " + newVanillaWoodTypes.length + " Vanilla Wood Types. Total: " + vanilla_wood_types.size());
    }

    public static MoreVariantWoodType getMoreVariantWoodTypeByName(String name) {
        ensureInitialized();
        MoreVariantWoodType woodType = more_variant_wood_types_by_name.get(normalize(name));
        if (woodType == null) {
            LOGGER.warn("Wood Type with name '" + name + "' not found in More Variant Wood Types.");
        }
        return woodType;
    }

    public static MoreVariantWoodType getVanillaWoodTypeByName(String name) {
        ensureInitialized();
        MoreVariantWoodType woodType = vanilla_wood_types_by_name.get(normalize(name));
        if (woodType == null) {
            LOGGER.warn("Wood Type with name '" + name + "' not found in Vanilla Wood Types.");
        }
        return woodType;
    }

    public static MoreVariantWoodType getWoodTypeByName(String name) {
        ensureInitialized();
        MoreVariantWoodType woodType = wood_types_by_name.get(normalize(name));
        if (woodType == null) {
            LOGGER.warn("Wood Type with name '" + name + "' not found in Wood Types.");
        }
        return woodType;
    }

    public static List<MoreVariantWoodType> getMoreVariantWoodTypes() {
        ensureInitialized();
        return List.copyOf(more_variant_wood_types);
    }

    public static List<MoreVariantWoodType> getVanillaWoodTypes() {
        ensureInitialized();
        return List.copyOf(vanilla_wood_types);
    }

    public static List<MoreVariantWoodType> getAllWoodTypes() {
        ensureInitialized();
        return List.copyOf(wood_types);
    }

    private static void ensureInitialized() {
        if (!initialized) {
            init();
        }
    }

    private static String normalize(String value) {
        return value.toLowerCase(Locale.ROOT);
    }

    private static void rebuildAllWoodTypes() {
        wood_types.clear();
        wood_types_by_name.clear();

        for (MoreVariantWoodType woodType : more_variant_wood_types) {
            addToAllWoodTypes(woodType);
        }
        for (MoreVariantWoodType woodType : vanilla_wood_types) {
            addToAllWoodTypes(woodType);
        }
    }

    private static void addToAllWoodTypes(MoreVariantWoodType woodType) {
        if (wood_types.add(woodType)) {
            wood_types_by_name.putIfAbsent(normalize(woodType.getName()), woodType);
        }
    }

    public static List<BiomeSpawnItemVariant> getBiomeSpawnItemVariants() {
        return List.copyOf(biome_spawn_item_variants);
    }

    public static void addBiomeSpawnItemVariant(String[] biomeIds,  MoreVariantWoodType mainWoodType, float mainProb,
                                                                    MoreVariantWoodType altWoodType, float altProb) {
        BiomeSpawnItemVariant variant = new BiomeSpawnItemVariant(biomeIds, mainWoodType, mainProb, altWoodType, altProb);
        biome_spawn_item_variants.add(variant);
    }


    public record BiomeSpawnItemVariant(String[] biomeIds,  MoreVariantWoodType mainWoodType, float mainProb,
                                                            MoreVariantWoodType altWoodType, float altProb) {}
}
