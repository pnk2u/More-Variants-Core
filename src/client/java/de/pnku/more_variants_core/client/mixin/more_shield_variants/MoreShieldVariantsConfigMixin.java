package de.pnku.more_variants_core.client.mixin.more_shield_variants;

import de.pnku.lolmsv.config.MoreShieldVariantsConfig;
import de.pnku.more_variants_core.client.util.MoreShieldVariantConfigAccessor;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Set;

@Mixin(MoreShieldVariantsConfig.class)
public abstract class MoreShieldVariantsConfigMixin implements MoreShieldVariantConfigAccessor {

    @Inject(method = "updateConfigs", at = @At("TAIL"), remap = false)
    private void injectedUpdateConfigsAtTail(MoreShieldVariantsConfig config, CallbackInfo ci) {
        MoreShieldVariantConfigAccessor configAccess = (MoreShieldVariantConfigAccessor) config;
        updateShieldVariantConfigs(MoreVariantWoodTypeHolder.getWoodTypes(), configAccess);
    }

    @Inject(method = "initialReadConfig", at = @At("TAIL"), remap = false)
    private static void injectedInitialReadConfigAtTail(CallbackInfo ci) {
        MoreShieldVariantConfigAccessor configAccess = (MoreShieldVariantConfigAccessor) MoreShieldVariantsConfig.getInstance();
        initialReadShieldVariantConfigs(MoreVariantWoodTypeHolder.getWoodTypes(), configAccess);
    }

    @Override
    public boolean mvpob$isWoodTypeUseCustom(MoreVariantWoodType woodType) {
        return MoreShieldVariantsConfig.textureConfigList.stream()
                .anyMatch(woodType.getName()::equalsIgnoreCase);
    }

    @Override
    public void mvpob$setWoodTypeUseCustom(MoreVariantWoodType woodType, boolean useCustom) {
        syncWoodTypeTextureConfigEntry(woodType, useCustom);
    }

    @Unique
    private static void syncWoodTypeTextureConfigEntry(MoreVariantWoodType woodType, boolean useCustom) {
        if (useCustom) {
            if (MoreShieldVariantsConfig.textureConfigList.stream()
                    .noneMatch(woodType.getName()::equalsIgnoreCase)) {
                MoreShieldVariantsConfig.textureConfigList.add(woodType.getName());
            }
            return;
        }
        MoreShieldVariantsConfig.textureConfigList.removeIf(woodType.getName()::equalsIgnoreCase);
    }

    @Unique
    private static void updateShieldVariantConfigs(List<MoreVariantWoodType> woodTypes, MoreShieldVariantConfigAccessor configAccess) {
        Set<String> configuredShieldVariants = new java.util.HashSet<>(MoreShieldVariantsConfig.textureConfigList);

        for (MoreVariantWoodType woodType : woodTypes) {
            String woodTypeName = woodType.getName();
            boolean useCustom = configuredShieldVariants.contains(woodTypeName);

            if (useCustom) {
                configuredShieldVariants.add(woodTypeName);
            } else {
                configuredShieldVariants.remove(woodTypeName);
            }
        }
        MoreShieldVariantsConfig.textureConfigList.clear();
        MoreShieldVariantsConfig.textureConfigList.addAll(configuredShieldVariants);
    }

    @Unique
    private static void initialReadShieldVariantConfigs(List<MoreVariantWoodType> woodTypes, MoreShieldVariantConfigAccessor configAccess) {
        for (MoreVariantWoodType woodType : woodTypes) {
            boolean useCustom = configAccess.mvpob$isWoodTypeUseCustom(woodType);
            syncWoodTypeTextureConfigEntry(woodType, useCustom);
        }
    }
}
