package de.pnku.more_variants_core.util;

import java.util.ArrayList;
import java.util.List;

public class WoodTypeHolder {
        private static final List<WoodType> woodTypes = new ArrayList<>();

        private static boolean initialized = false;
        public static void init() {
            initialized = true;
        }

        public static void addWoodTypes(WoodType... newWoodTypes) {
            for (WoodType woodType : newWoodTypes) {
                if (!woodTypes.contains(woodType)) {
                    woodTypes.add(woodType);
                }
            }
        }

        public static WoodType getWoodTypeByName(String name) {
            for (WoodType woodType : woodTypes) {
                if (woodType.getName().equalsIgnoreCase(name)) {
                    return woodType;
                }
            }
            return null;
        }

        public static List<WoodType> getWoodTypes() {
            if (!initialized) init();
            return woodTypes;
        }
}
