package de.pnku.more_variants_pale_oak_backport.client.mixin.more_shield_variants;

import de.pnku.lolmsv.config.MoreShieldVariantsConfig;
import de.pnku.lolmsv.config.MoreShieldVariantsConfigScreen;
import de.pnku.more_variants_pale_oak_backport.client.mixin.util.PaleOakShieldConfigAccessor;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MoreShieldVariantsConfigScreen.class)
public abstract class MoreShieldVariantsConfigScreenMixin {
    @Inject(method = "builder", at = @At(value = "INVOKE", target = "Lde/pnku/lolmsv/config/MoreShieldVariantsConfig;isDarkOakUseCustom()Z"), remap = false)
    private static void injectedBuilderAtReturn(CallbackInfoReturnable<ConfigBuilder> cir) {
        ConfigBuilder configBuilder = cir.getReturnValue();
        ConfigCategory shieldTexture = configBuilder.getOrCreateCategory(
                Component.translatable("config.category.moreshieldvariants.shieldTexture")
        );

        PaleOakShieldConfigAccessor configAccess = (PaleOakShieldConfigAccessor) MoreShieldVariantsConfig.getInstance();
        shieldTexture.addEntry(configBuilder.entryBuilder()
                .startBooleanToggle(
                        Component.translatable("config.shieldTexture_option.moreshieldvariants.paleOakUseCustom"),
                        configAccess.mvpob$isPaleOakUseCustom()
                )
                .setDefaultValue(true)
                .setSaveConsumer(configAccess::mvpob$setPaleOakUseCustom)
                .setTooltip(new Component[]{
                        Component.translatable("config.shieldTexture_option.moreshieldvariants.paleOakUseCustom.tooltip")
                })
                .build());
    }
}

