package de.pnku.more_variants_core.util;

import java.util.ArrayList;
import java.util.List;

public class WoodTypeHolder {
        private static final List<WoodType> woodTypes = new ArrayList<>();

        public static void addWoodTypes(WoodType... newWoodTypes) {
            for (WoodType woodType : newWoodTypes) {
                if (!woodTypes.contains(woodType)) {
                    woodTypes.add(woodType);
                }
            }
        }

        public static List<WoodType> getWoodTypes() {
            return woodTypes;
        }
}
