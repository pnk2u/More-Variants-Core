package de.pnku.more_variants_core.client.util;

import de.pnku.more_variants_core.util.WoodType;

public interface MoreVariantShieldConfigAccessor {
    boolean mvpob$isWoodTypeUseCustom(WoodType woodType);

    void mvpob$setWoodTypeUseCustom(WoodType woodType, boolean useCustom);
}

