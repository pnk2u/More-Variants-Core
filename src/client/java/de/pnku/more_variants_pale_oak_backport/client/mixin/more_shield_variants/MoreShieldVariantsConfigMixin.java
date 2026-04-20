package de.pnku.more_variants_pale_oak_backport.client.mixin.more_shield_variants;

import de.pnku.lolmsv.config.MoreShieldVariantsConfig;
import de.pnku.more_variants_pale_oak_backport.PaleOakConstants;
import de.pnku.more_variants_pale_oak_backport.client.mixin.util.PaleOakShieldConfigAccessor;
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
                    .anyMatch(PaleOakConstants.WOOD_TYPE::equalsIgnoreCase);
            if (!containsPaleOak) {
                MoreShieldVariantsConfig.textureConfigList.add(PaleOakConstants.WOOD_TYPE);
            }
            return;
        }

        MoreShieldVariantsConfig.textureConfigList.removeIf(PaleOakConstants.WOOD_TYPE::equalsIgnoreCase);
    }
}
