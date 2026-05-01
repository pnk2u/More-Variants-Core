package de.pnku.more_variants_core.util;

import java.util.ArrayList;
import java.util.List;
import static de.pnku.more_variants_core.MoreVariantsCore.LOGGER;

public class MoreVariantWoodTypeHolder {
        private static final List<MoreVariantWoodType> woodTypes = new ArrayList<>();

        private static boolean initialized = false;
        public static void init() {
            initialized = true;
        }

        public static void addWoodTypes(MoreVariantWoodType... newWoodTypes) {
            for (MoreVariantWoodType woodType : newWoodTypes) {
                if (!woodTypes.contains(woodType)) {
                    woodTypes.add(woodType);
                }
            }
            LOGGER.info("Added " + newWoodTypes.length + " wood types. Total wood types: " + woodTypes.size());
        }

        public static MoreVariantWoodType getWoodTypeByName(String name) {
            for (MoreVariantWoodType woodType : woodTypes) {
                if (woodType.getName().equalsIgnoreCase(name)) {
                    return woodType;
                }
            }
            return null;
        }

        public static List<MoreVariantWoodType> getWoodTypes() {
            if (!initialized) init();
            LOGGER.info("Retrieving wood types. Total wood types: " + woodTypes.size());
            return woodTypes;
        }
}
