package de.pnku.more_variants_core.mixin.more_bed_variants;

import de.pnku.mbdv.init.MbdvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.BedColorType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MbdvItemInit.class)
public abstract class MbdvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Final
    @Shadow
    public static Item.Properties bedProperties;

    @Unique
    private static final Item PALE_OAK_RED_BED_ITEM = registerPaleOakBedItem(BedColorType.RED);
    @Unique
    private static final Item PALE_OAK_BLACK_BED_ITEM = registerPaleOakBedItem(BedColorType.BLACK);
    @Unique
    private static final Item PALE_OAK_WHITE_BED_ITEM = registerPaleOakBedItem(BedColorType.WHITE);
    @Unique
    private static final Item PALE_OAK_ORANGE_BED_ITEM = registerPaleOakBedItem(BedColorType.ORANGE);
    @Unique
    private static final Item PALE_OAK_MAGENTA_BED_ITEM = registerPaleOakBedItem(BedColorType.MAGENTA);
    @Unique
    private static final Item PALE_OAK_LIGHT_BLUE_BED_ITEM = registerPaleOakBedItem(BedColorType.LIGHT_BLUE);
    @Unique
    private static final Item PALE_OAK_YELLOW_BED_ITEM = registerPaleOakBedItem(BedColorType.YELLOW);
    @Unique
    private static final Item PALE_OAK_LIME_BED_ITEM = registerPaleOakBedItem(BedColorType.LIME);
    @Unique
    private static final Item PALE_OAK_PINK_BED_ITEM = registerPaleOakBedItem(BedColorType.PINK);
    @Unique
    private static final Item PALE_OAK_GRAY_BED_ITEM = registerPaleOakBedItem(BedColorType.GRAY);
    @Unique
    private static final Item PALE_OAK_LIGHT_GRAY_BED_ITEM = registerPaleOakBedItem(BedColorType.LIGHT_GRAY);
    @Unique
    private static final Item PALE_OAK_CYAN_BED_ITEM = registerPaleOakBedItem(BedColorType.CYAN);
    @Unique
    private static final Item PALE_OAK_PURPLE_BED_ITEM = registerPaleOakBedItem(BedColorType.PURPLE);
    @Unique
    private static final Item PALE_OAK_BLUE_BED_ITEM = registerPaleOakBedItem(BedColorType.BLUE);
    @Unique
    private static final Item PALE_OAK_BROWN_BED_ITEM = registerPaleOakBedItem(BedColorType.BROWN);
    @Unique
    private static final Item PALE_OAK_GREEN_BED_ITEM = registerPaleOakBedItem(BedColorType.GREEN);

    @Shadow
    private static void registerWhiteBedItem(Item whiteBed, Item bedAfter) {}

    @Shadow
    private static void registerOtherBedItem(Item otherBed) {}

    @Unique
    private static Item registerPaleOakBedItem(BedColorType colorType) {
        Item bedItem = new BedItem(MoreVariantHolder.getBlock(colorType, WOOD_TYPE), bedProperties);
        if (colorType == BedColorType.WHITE) {
            registerWhiteBedItem(bedItem, Items.WHITE_BED);
        } else {
            registerOtherBedItem(bedItem);
        }
        MoreVariantHolder.setItem(colorType, WOOD_TYPE, bedItem);
        return bedItem;
    }
}
