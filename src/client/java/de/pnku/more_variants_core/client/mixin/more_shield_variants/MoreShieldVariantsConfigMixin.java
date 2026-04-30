package de.pnku.more_variants_core.client.mixin.more_shield_variants;

import de.pnku.lolmsv.config.MoreShieldVariantsConfig;
import de.pnku.more_variants_core.client.util.MoreVariantShieldConfigAccessor;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MoreShieldVariantsConfig.class)
public abstract class MoreShieldVariantsConfigMixin implements MoreVariantShieldConfigAccessor {
    @Unique
    private boolean paleOakUseCustom = true;

    @Inject(method = "updateConfigs", at = @At("TAIL"), remap = false)
    private void injectedUpdateConfigsAtTail(MoreShieldVariantsConfig config, CallbackInfo ci) {
        MoreVariantShieldConfigAccessor configAccess = (MoreVariantShieldConfigAccessor) config;
        updateShieldVariantConfigs(WoodTypeHolder.getWoodTypes(), configAccess);
    }

    @Inject(method = "initialReadConfig", at = @At("TAIL"), remap = false)
    private static void injectedInitialReadConfigAtTail(CallbackInfo ci) {
        MoreVariantShieldConfigAccessor configAccess = (MoreVariantShieldConfigAccessor) MoreShieldVariantsConfig.getInstance();
        initialReadShieldVariantConfigs(WoodTypeHolder.getWoodTypes(), configAccess);
    }

    @Override
    public boolean mvpob$isWoodTypeUseCustom(WoodType woodType) {
        return paleOakUseCustom;
    }

    @Override
    public void mvpob$setWoodTypeUseCustom(WoodType woodType, boolean useCustom) {
        paleOakUseCustom = useCustom;
        syncPaleOakTextureConfigEntry(woodType, useCustom);
    }

    @Unique
    private static void syncPaleOakTextureConfigEntry(WoodType woodType, boolean useCustom) {
        if (useCustom) {
            boolean containsPaleOak = MoreShieldVariantsConfig.textureConfigList.stream()
                    .anyMatch(woodType.getName()::equalsIgnoreCase);
            if (!containsPaleOak) {
                MoreShieldVariantsConfig.textureConfigList.add(woodType.getName());
            }
            return;
        }
        MoreShieldVariantsConfig.textureConfigList.removeIf(woodType.getName()::equalsIgnoreCase);
    }

    @Unique
    private static void updateShieldVariantConfigs(List<WoodType> woodTypes, MoreVariantShieldConfigAccessor configAccess) {
        for (WoodType woodType : woodTypes) {
            boolean useCustom = configAccess.mvpob$isWoodTypeUseCustom(woodType);
            configAccess.mvpob$setWoodTypeUseCustom(woodType, useCustom);
        }
    }

    @Unique
    private static void initialReadShieldVariantConfigs(List<WoodType> woodTypes, MoreVariantShieldConfigAccessor configAccess) {
        for (WoodType woodType : woodTypes) {
            boolean useCustom = configAccess.mvpob$isWoodTypeUseCustom(woodType);
            syncPaleOakTextureConfigEntry(woodType, useCustom);
        }
    }
}
