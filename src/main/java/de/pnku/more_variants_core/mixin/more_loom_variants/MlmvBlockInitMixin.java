package de.pnku.more_variants_core.mixin.more_loom_variants;

import de.pnku.mlmv.block.MoreLoomVariantBlock;
import de.pnku.mlmv.init.MlmvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MlmvBlockInit.class)
public abstract class MlmvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_LOOM = registerPaleOakLoom();

    @Shadow
    private static void registerBlock(MoreLoomVariantBlock loom) {}

    @Unique
    private static Block registerPaleOakLoom() {
        MoreLoomVariantBlock loom = new MoreLoomVariantBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(loom);
        MoreVariantHolder.setBlock(VariantType.LOOM, WOOD_TYPE, loom);
        return loom;
    }
}
