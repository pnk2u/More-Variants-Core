package de.pnku.more_variants_pale_oak_backport.mixin.more_bed_variants;

import de.pnku.mbdv.init.MbdvItemInit;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBedHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MbdvItemInit.class)
public abstract class MbdvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Final
    @Shadow
    public static Item.Properties bedProperties;

    @Unique
    private static final Item PALE_OAK_RED_BED_ITEM = registerPaleOakBedItem(DyeColor.RED);
    @Unique
    private static final Item PALE_OAK_BLACK_BED_ITEM = registerPaleOakBedItem(DyeColor.BLACK);
    @Unique
    private static final Item PALE_OAK_WHITE_BED_ITEM = registerPaleOakBedItem(DyeColor.WHITE);
    @Unique
    private static final Item PALE_OAK_ORANGE_BED_ITEM = registerPaleOakBedItem(DyeColor.ORANGE);
    @Unique
    private static final Item PALE_OAK_MAGENTA_BED_ITEM = registerPaleOakBedItem(DyeColor.MAGENTA);
    @Unique
    private static final Item PALE_OAK_LIGHT_BLUE_BED_ITEM = registerPaleOakBedItem(DyeColor.LIGHT_BLUE);
    @Unique
    private static final Item PALE_OAK_YELLOW_BED_ITEM = registerPaleOakBedItem(DyeColor.YELLOW);
    @Unique
    private static final Item PALE_OAK_LIME_BED_ITEM = registerPaleOakBedItem(DyeColor.LIME);
    @Unique
    private static final Item PALE_OAK_PINK_BED_ITEM = registerPaleOakBedItem(DyeColor.PINK);
    @Unique
    private static final Item PALE_OAK_GRAY_BED_ITEM = registerPaleOakBedItem(DyeColor.GRAY);
    @Unique
    private static final Item PALE_OAK_LIGHT_GRAY_BED_ITEM = registerPaleOakBedItem(DyeColor.LIGHT_GRAY);
    @Unique
    private static final Item PALE_OAK_CYAN_BED_ITEM = registerPaleOakBedItem(DyeColor.CYAN);
    @Unique
    private static final Item PALE_OAK_PURPLE_BED_ITEM = registerPaleOakBedItem(DyeColor.PURPLE);
    @Unique
    private static final Item PALE_OAK_BLUE_BED_ITEM = registerPaleOakBedItem(DyeColor.BLUE);
    @Unique
    private static final Item PALE_OAK_BROWN_BED_ITEM = registerPaleOakBedItem(DyeColor.BROWN);
    @Unique
    private static final Item PALE_OAK_GREEN_BED_ITEM = registerPaleOakBedItem(DyeColor.GREEN);

    @Shadow
    private static void registerWhiteBedItem(Item whiteBed, Item bedAfter) {}

    @Shadow
    private static void registerOtherBedItem(Item otherBed) {}

    @Unique
    private static Item registerPaleOakBedItem(DyeColor color) {
        Item bedItem = new BedItem(PaleOakBedHolder.getBlock(WOOD_TYPE, color), bedProperties);
        if (color == DyeColor.WHITE) {
            registerWhiteBedItem(bedItem, Items.WHITE_BED);
        } else {
            registerOtherBedItem(bedItem);
        }
        PaleOakBedHolder.setItem(WOOD_TYPE, color, bedItem);
        return bedItem;
    }
}
