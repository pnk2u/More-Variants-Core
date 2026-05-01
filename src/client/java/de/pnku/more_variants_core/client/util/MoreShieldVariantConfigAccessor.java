package de.pnku.more_variants_core.client.util;

import de.pnku.more_variants_core.util.MoreVariantWoodType;

public interface MoreShieldVariantConfigAccessor {
    boolean mvpob$isWoodTypeUseCustom(MoreVariantWoodType woodType);

    void mvpob$setWoodTypeUseCustom(MoreVariantWoodType woodType, boolean useCustom);
}

