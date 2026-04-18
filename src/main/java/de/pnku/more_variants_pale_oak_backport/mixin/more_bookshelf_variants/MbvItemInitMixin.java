package de.pnku.more_variants_pale_oak_backport.mixin.more_bookshelf_variants;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBookshelfHolder;
import io.github.lieonlion.lolmbv.init.MbvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MbvItemInit.class)
public abstract class MbvItemInitMixin {
    @Unique
    private static final Item PALE_OAK_BOOKSHELF_ITEM = registerPaleOakBookshelfItem();

    @Invoker("registerItem")
    public static void invokeRegisterBookshelfItem(String name, Item bookshelfItem, Item itemAfter) {
        throw new AssertionError();
    }

    @Inject(method = "registerItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterBookshelfItemsAtHead(CallbackInfo ci) {
        if (PALE_OAK_BOOKSHELF_ITEM.getDefaultInstance().isEmpty()) {
            throw new IllegalStateException("Failed to register Pale Oak Bookshelf Item");
        }
    }

    @Unique
    private static Item registerPaleOakBookshelfItem() {
        String woodType = "pale_oak";
        Item bookshelfItem = new BlockItem(PaleOakBookshelfHolder.PALE_OAK_BOOKSHELF, new Item.Properties());
        invokeRegisterBookshelfItem(woodType + "_bookshelf", bookshelfItem, Items.BOOKSHELF);
        PaleOakBookshelfHolder.PALE_OAK_BOOKSHELF_ITEM = bookshelfItem;
        return bookshelfItem;
    }
}
