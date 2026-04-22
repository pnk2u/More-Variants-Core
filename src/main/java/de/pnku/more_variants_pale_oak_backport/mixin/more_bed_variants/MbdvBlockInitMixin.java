package de.pnku.more_variants_pale_oak_backport.mixin.more_bed_variants;

import de.pnku.mbdv.block.MoreBedVariantBlock;
import de.pnku.mbdv.init.MbdvBlockInit;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBedHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MbdvBlockInit.class)
public abstract class MbdvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final MoreBedVariantBlock PALE_OAK_RED_BED = registerPaleOakBedBlock(DyeColor.RED);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_BLACK_BED = registerPaleOakBedBlock(DyeColor.BLACK);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_WHITE_BED = registerPaleOakBedBlock(DyeColor.WHITE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_ORANGE_BED = registerPaleOakBedBlock(DyeColor.ORANGE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_MAGENTA_BED = registerPaleOakBedBlock(DyeColor.MAGENTA);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_LIGHT_BLUE_BED = registerPaleOakBedBlock(DyeColor.LIGHT_BLUE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_YELLOW_BED = registerPaleOakBedBlock(DyeColor.YELLOW);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_LIME_BED = registerPaleOakBedBlock(DyeColor.LIME);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_PINK_BED = registerPaleOakBedBlock(DyeColor.PINK);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_GRAY_BED = registerPaleOakBedBlock(DyeColor.GRAY);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_LIGHT_GRAY_BED = registerPaleOakBedBlock(DyeColor.LIGHT_GRAY);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_CYAN_BED = registerPaleOakBedBlock(DyeColor.CYAN);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_PURPLE_BED = registerPaleOakBedBlock(DyeColor.PURPLE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_BLUE_BED = registerPaleOakBedBlock(DyeColor.BLUE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_BROWN_BED = registerPaleOakBedBlock(DyeColor.BROWN);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_GREEN_BED = registerPaleOakBedBlock(DyeColor.GREEN);

    @Shadow
    private static void registerBedBlock(MoreBedVariantBlock bed) {}

    @Unique
    private static MoreBedVariantBlock registerPaleOakBedBlock(DyeColor color) {
        MoreBedVariantBlock bedBlock = new MoreBedVariantBlock(color, WOOD_TYPE.getName(), color.getName());
        registerBedBlock(bedBlock);
        PaleOakBedHolder.setBlock(WOOD_TYPE, color, bedBlock);
        return bedBlock;
    }
}
