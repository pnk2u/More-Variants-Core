package de.pnku.more_variants_pale_oak_backport.mixin.more_crafting_tables;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakCraftingTableHolder;
import io.github.lieonlion.lolmct.init.MctItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MctItemInit.class)
public abstract class MctItemInitMixin {
    @Unique
    private static final BlockItem PALE_OAK_CRAFTING_TABLE_ITEM = registerPaleOakCraftingTableItem();

    @Invoker("registerItem")
    @SuppressWarnings("SameParameterValue")
    private static void invokeRegisterItem(String name, Item craftingTableItem, Item craftingTableAfter) {
        throw new AssertionError();
    }

    @Inject(method = "registerItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterItemsAtHead(CallbackInfo ci) {
        if (PALE_OAK_CRAFTING_TABLE_ITEM.getDefaultInstance().isEmpty()) {
            throw new IllegalStateException("Failed to register Pale Oak Crafting Table Item");
        }
    }

    @Unique
    private static BlockItem registerPaleOakCraftingTableItem() {
        String woodType = "pale_oak";
        BlockItem craftingTableItem = new BlockItem(PaleOakCraftingTableHolder.PALE_OAK_CRAFTING_TABLE, new Item.Properties());
        invokeRegisterItem(woodType + "_crafting_table", craftingTableItem, Items.CRAFTING_TABLE);
        PaleOakCraftingTableHolder.PALE_OAK_CRAFTING_TABLE_ITEM = craftingTableItem;
        return craftingTableItem;
    }
}
