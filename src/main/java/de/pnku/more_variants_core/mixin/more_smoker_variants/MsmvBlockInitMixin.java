package de.pnku.more_variants_core.mixin.more_smoker_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.SmokerType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import de.pnku.msmv.block.MoreSmokerVariantBlock;
import de.pnku.msmv.init.MsmvBlockInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MsmvBlockInit.class)
public abstract class MsmvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final MoreSmokerVariantBlock PALE_OAK_COBBLESTONE_SMOKER = registerPaleOakSmoker(SmokerType.COBBLESTONE);
    @Unique
    private static final MoreSmokerVariantBlock PALE_OAK_BLACKSTONE_SMOKER = registerPaleOakSmoker(SmokerType.BLACKSTONE);
    @Unique
    private static final MoreSmokerVariantBlock PALE_OAK_DEEPSLATE_SMOKER = registerPaleOakSmoker(SmokerType.DEEPSLATE);

    @Shadow
    private static void registerSmokerBlock(MoreSmokerVariantBlock smoker) {}

    @Unique
    private static MoreSmokerVariantBlock registerPaleOakSmoker(SmokerType smokerType) {
        MoreSmokerVariantBlock smokerBlock = new MoreSmokerVariantBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName(), smokerType.registrationType());
        registerSmokerBlock(smokerBlock);
        MoreVariantHolder.setBlock(smokerType, WOOD_TYPE, smokerBlock);
        return smokerBlock;
    }
}
