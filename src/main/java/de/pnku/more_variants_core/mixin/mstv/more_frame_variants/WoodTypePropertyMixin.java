package de.pnku.more_variants_core.mixin.mstv.more_frame_variants;

import com.google.common.collect.ImmutableSet;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_mframev.compat.fastitemframes.MoreFrameVariantsCompatibilityFIF;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(MoreFrameVariantsCompatibilityFIF.WoodTypeProperty.class)
public abstract class WoodTypePropertyMixin {
    @Mutable
    @Shadow
    @Final
    private ImmutableSet<MoreFrameVariantsCompatibilityFIF.WoodTypeValue> values;

    @Mutable
    @Shadow
    @Final
    private Map<String, MoreFrameVariantsCompatibilityFIF.WoodTypeValue> names;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectedConstructor(CallbackInfo ci) {
        List<MoreVariantWoodType> mVWoodTypes = MoreVariantWoodTypeHolder.getWoodTypes();
        ImmutableSet.Builder<MoreFrameVariantsCompatibilityFIF.WoodTypeValue> builder = ImmutableSet.builder();
        builder.addAll(this.values);

        for (MoreVariantWoodType mVWoodType : mVWoodTypes) {
            String normalizedName = mVWoodType.getName().replace("minecraft_", "");
            WoodType woodType = new WoodType(normalizedName, new BlockSetType(normalizedName));
            MoreFrameVariantsCompatibilityFIF.WoodTypeValue woodTypeValue = new MoreFrameVariantsCompatibilityFIF.WoodTypeValue(woodType);
            builder.add(woodTypeValue);
            this.names.putIfAbsent(normalizedName, woodTypeValue);
        }

        this.values = builder.build();

        Map<String, MoreFrameVariantsCompatibilityFIF.WoodTypeValue> renamed = new HashMap<>();
        this.names.forEach((name, value) -> {
            if (name.startsWith("minecraft_")) {
                renamed.put(name.replace("minecraft_", ""), value);
            }
        });
        renamed.forEach((name, value) -> {
            this.names.remove("minecraft_" + name);
            this.names.putIfAbsent(name, value);
        });
    }
}
