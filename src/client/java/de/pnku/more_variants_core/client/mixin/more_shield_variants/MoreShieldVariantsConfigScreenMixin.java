package de.pnku.more_variants_core.client.mixin.more_shield_variants;

import de.pnku.lolmsv.config.MoreShieldVariantsConfig;
import de.pnku.lolmsv.config.MoreShieldVariantsConfigScreen;
import de.pnku.more_variants_core.client.util.MoreShieldVariantConfigAccessor;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.network.chat.Component;
import org.apache.commons.text.CaseUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(MoreShieldVariantsConfigScreen.class)
public abstract class MoreShieldVariantsConfigScreenMixin {
    @Inject(method = "builder", at = @At("RETURN"), remap = false)
    private static void injectedBuilderAtReturn(CallbackInfoReturnable<ConfigBuilder> cir) {
        ConfigBuilder configBuilder = cir.getReturnValue();
        if (configBuilder == null) {
            return;
        }

        List<MoreVariantWoodType> woodTypes = MoreVariantWoodTypeHolder.getMoreVariantWoodTypes();
        for (MoreVariantWoodType woodType : woodTypes) {
            ConfigCategory shieldTexture = configBuilder.getOrCreateCategory(
                    Component.translatable("config.category.moreshieldvariants.shieldTexture")
            );
            String woodTypeAsCamel = CaseUtils.toCamelCase(woodType.getName(), false, '_');

            MoreShieldVariantConfigAccessor configAccess = (MoreShieldVariantConfigAccessor) MoreShieldVariantsConfig.getInstance();
            AbstractConfigListEntry<?> variantEntry = configBuilder.entryBuilder()
                    .startBooleanToggle(
                            Component.translatable("config.shieldTexture_option.moreshieldvariants." + woodTypeAsCamel + "UseCustom"),
                            configAccess.mvpob$isWoodTypeUseCustom(woodType)
                    )
                    .setDefaultValue(true)
                    .setSaveConsumer(newUseCustom -> configAccess.mvpob$setWoodTypeUseCustom(woodType, newUseCustom))
                    .setTooltip(new Component[]{
                            Component.translatable("config.shieldTexture_option.moreshieldvariants." + woodTypeAsCamel + "UseCustom.tooltip")
                    })
                    .build();

            shieldTexture.addEntry(variantEntry);
            }
    }
}
