package de.pnku.more_variants_pale_oak_backport.mixin.more_bed_variants;

import de.pnku.mbdv.block.MoreBedVariantBlock;
import de.pnku.mbdv.init.MbdvBlockInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.BedColorType;
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
    private static final MoreBedVariantBlock PALE_OAK_RED_BED = registerPaleOakBedBlock(BedColorType.RED);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_BLACK_BED = registerPaleOakBedBlock(BedColorType.BLACK);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_WHITE_BED = registerPaleOakBedBlock(BedColorType.WHITE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_ORANGE_BED = registerPaleOakBedBlock(BedColorType.ORANGE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_MAGENTA_BED = registerPaleOakBedBlock(BedColorType.MAGENTA);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_LIGHT_BLUE_BED = registerPaleOakBedBlock(BedColorType.LIGHT_BLUE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_YELLOW_BED = registerPaleOakBedBlock(BedColorType.YELLOW);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_LIME_BED = registerPaleOakBedBlock(BedColorType.LIME);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_PINK_BED = registerPaleOakBedBlock(BedColorType.PINK);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_GRAY_BED = registerPaleOakBedBlock(BedColorType.GRAY);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_LIGHT_GRAY_BED = registerPaleOakBedBlock(BedColorType.LIGHT_GRAY);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_CYAN_BED = registerPaleOakBedBlock(BedColorType.CYAN);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_PURPLE_BED = registerPaleOakBedBlock(BedColorType.PURPLE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_BLUE_BED = registerPaleOakBedBlock(BedColorType.BLUE);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_BROWN_BED = registerPaleOakBedBlock(BedColorType.BROWN);
    @Unique
    private static final MoreBedVariantBlock PALE_OAK_GREEN_BED = registerPaleOakBedBlock(BedColorType.GREEN);

    @Shadow
    private static void registerBedBlock(MoreBedVariantBlock bed) {}

    @Unique
    private static MoreBedVariantBlock registerPaleOakBedBlock(BedColorType colorType) {
        MoreBedVariantBlock bedBlock = new MoreBedVariantBlock(colorType.color(), WOOD_TYPE.getName(), colorType.color().getName());
        registerBedBlock(bedBlock);
        PaleOakVariantHolder.setBlock(colorType, WOOD_TYPE, bedBlock);
        return bedBlock;
    }
}
