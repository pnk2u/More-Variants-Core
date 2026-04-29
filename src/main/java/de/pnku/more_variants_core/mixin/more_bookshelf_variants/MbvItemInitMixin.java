package de.pnku.more_variants_core.mixin.more_bookshelf_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import io.github.lieonlion.lolmbv.init.MbvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MbvItemInit.class)
public abstract class MbvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_BOOKSHELF_ITEM = registerPaleOakBookshelfItem();

    @Shadow
    public static void registerItem(String name, Item item, Item after) {}

    @Unique
    private static Item registerPaleOakBookshelfItem() {
        Item bookshelfItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.BOOKSHELF, WOOD_TYPE), new Item.Properties());
        registerItem(WOOD_TYPE.getName() + "_bookshelf", bookshelfItem, Items.BOOKSHELF);
        MoreVariantHolder.setItem(VariantType.BOOKSHELF, WOOD_TYPE, bookshelfItem);
        return bookshelfItem;
    }
}
