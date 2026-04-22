package de.pnku.more_variants_pale_oak_backport.mixin.more_bookshelf_variants;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBookshelfHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
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
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_BOOKSHELF_ITEM = registerPaleOakBookshelfItem();

    @Shadow
    public static void registerItem(String name, Item item, Item after) {}

    @Unique
    private static Item registerPaleOakBookshelfItem() {
        Item bookshelfItem = new BlockItem(PaleOakBookshelfHolder.getBlock(WOOD_TYPE), new Item.Properties());
        registerItem(WOOD_TYPE.getName() + "_bookshelf", bookshelfItem, Items.BOOKSHELF);
        PaleOakBookshelfHolder.setItem(WOOD_TYPE, bookshelfItem);
        return bookshelfItem;
    }
}
