package de.pnku.more_variants_core.mixin.more_lectern_variants;

import de.pnku.mlv.block.MoreLecternBlock;
import de.pnku.mlv.init.MlvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MlvBlockInit.class)
public abstract class MlvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_LECTERN = registerPaleOakLectern();

    @Shadow
    private static void registerBlock(MoreLecternBlock lectern) {}

    @Unique
    private static Block registerPaleOakLectern() {
        MoreLecternBlock lectern = new MoreLecternBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(lectern);
        MoreVariantHolder.setBlock(VariantType.LECTERN, WOOD_TYPE, lectern);
        return lectern;
    }
}
