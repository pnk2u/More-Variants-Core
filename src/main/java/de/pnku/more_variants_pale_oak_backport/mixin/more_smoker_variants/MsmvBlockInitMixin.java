package de.pnku.more_variants_pale_oak_backport.mixin.more_smoker_variants;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.SmokerType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.msmv.block.MoreSmokerVariantBlock;
import de.pnku.msmv.init.MsmvBlockInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MsmvBlockInit.class)
public abstract class MsmvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

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
        PaleOakVariantHolder.setBlock(PaleOakVariantHolder.SMOKER_FAMILY, WOOD_TYPE, smokerType, smokerBlock);
        return smokerBlock;
    }
}
