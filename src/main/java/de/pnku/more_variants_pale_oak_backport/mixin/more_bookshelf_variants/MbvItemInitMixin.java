package de.pnku.more_variants_pale_oak_backport.mixin.more_bookshelf_variants;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBookshelfHolder;
import de.pnku.more_variants_pale_oak_backport.PaleOakConstants;
import io.github.lieonlion.lolmbv.init.MbvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MbvItemInit.class)
public abstract class MbvItemInitMixin {
    @Unique
    private static final Item PALE_OAK_BOOKSHELF_ITEM = registerPaleOakBookshelfItem();

    @Invoker("registerItem")
    public static void invokeRegisterItem(String name, Item bookshelfItem, Item itemAfter) {
        throw new AssertionError();
    }

    @Unique
    private static Item registerPaleOakBookshelfItem() {
        Item bookshelfItem = new BlockItem(PaleOakBookshelfHolder.getBlock(), new Item.Properties());
        invokeRegisterItem(PaleOakConstants.WOOD_TYPE + "_bookshelf", bookshelfItem, Items.BOOKSHELF);
        PaleOakBookshelfHolder.setItem(bookshelfItem);
        return bookshelfItem;
    }
}
