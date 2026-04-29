package de.pnku.more_variants_core.mixin.more_grindstone_variants;

import de.pnku.mgv.block.MoreGrindstoneBlock;
import de.pnku.mgv.init.MgvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.GrindstoneType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MgvBlockInit.class)
public abstract class MgvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_STONE_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.STONE);
    @Unique
    private static final Block PALE_OAK_SANDSTONE_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.SANDSTONE);
    @Unique
    private static final Block PALE_OAK_GRANITE_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.GRANITE);
    @Unique
    private static final Block PALE_OAK_DEEPSLATE_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.DEEPSLATE);
    @Unique
    private static final Block PALE_OAK_BASALT_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.BASALT);

    @Shadow
    private static void registerBlock(MoreGrindstoneBlock grindstone) {}

    @Unique
    private static Block registerPaleOakGrindstone(GrindstoneType grindstoneType) {
        MoreGrindstoneBlock grindstoneBlock = new MoreGrindstoneBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName(), WOOD_TYPE.getPlanksBlock(), grindstoneType.registrationType(), grindstoneType.getStoneSlabBlock(), grindstoneType.blockIdSuffix());
        registerBlock(grindstoneBlock);
        MoreVariantHolder.setBlock(grindstoneType, WOOD_TYPE, grindstoneBlock);
        return grindstoneBlock;
    }
}
