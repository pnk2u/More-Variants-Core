package de.pnku.more_variants_core.client.mixin.more_shield_variants;

import de.pnku.lolmsv.config.MoreShieldVariantsConfig;
import de.pnku.more_variants_core.client.util.PaleOakShieldConfigAccessor;
import de.pnku.more_variants_core.util.WoodTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MoreShieldVariantsConfig.class)
public abstract class MoreShieldVariantsConfigMixin implements PaleOakShieldConfigAccessor {
    @Unique
    private boolean paleOakUseCustom = true;

    @Inject(method = "updateConfigs", at = @At("TAIL"), remap = false)
    private void injectedUpdateConfigsAtTail(MoreShieldVariantsConfig config, CallbackInfo ci) {
        PaleOakShieldConfigAccessor configAccess = (PaleOakShieldConfigAccessor) config;
        mvpob$setPaleOakUseCustom(configAccess.mvpob$isPaleOakUseCustom());
    }

    @Inject(method = "initialReadConfig", at = @At("TAIL"), remap = false)
    private static void injectedInitialReadConfigAtTail(CallbackInfo ci) {
        PaleOakShieldConfigAccessor configAccess = (PaleOakShieldConfigAccessor) MoreShieldVariantsConfig.getInstance();
        syncPaleOakTextureConfigEntry(configAccess.mvpob$isPaleOakUseCustom());
    }

    @Override
    public boolean mvpob$isPaleOakUseCustom() {
        return paleOakUseCustom;
    }

    @Override
    public void mvpob$setPaleOakUseCustom(boolean useCustom) {
        paleOakUseCustom = useCustom;
        syncPaleOakTextureConfigEntry(useCustom);
    }

    @Unique
    private static void syncPaleOakTextureConfigEntry(boolean useCustom) {
        if (useCustom) {
            boolean containsPaleOak = MoreShieldVariantsConfig.textureConfigList.stream()
                    .anyMatch(WoodTypes.PALE_OAK.getName()::equalsIgnoreCase);
            if (!containsPaleOak) {
                MoreShieldVariantsConfig.textureConfigList.add(WoodTypes.PALE_OAK.getName());
            }
            return;
        }

        MoreShieldVariantsConfig.textureConfigList.removeIf(WoodTypes.PALE_OAK.getName()::equalsIgnoreCase);
    }
}
