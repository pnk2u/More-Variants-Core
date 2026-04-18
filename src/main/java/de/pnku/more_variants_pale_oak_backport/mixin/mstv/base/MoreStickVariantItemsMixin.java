package de.pnku.more_variants_pale_oak_backport.mixin.mstv.base;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakStickHolder;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_base.item.MoreStickVariantItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(MoreStickVariantItems.class)
public abstract class MoreStickVariantItemsMixin {
    @Unique
    private static final Item PALE_OAK_STICK = registerPaleOakStickItem();

    @Invoker("registerStickItem")
    private static void invokeRegisterStickItem(Item stickItem, Item stickItemAfter) {
        throw new AssertionError();
    }

    @Inject(method = "registerStickItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterStickItemsAtHead(CallbackInfo ci) {
        if (PALE_OAK_STICK.getDefaultInstance().isEmpty()) {
            throw new IllegalStateException("Failed to register Pale Oak Stick Item");
        }
    }

    @Unique
    private static Item registerPaleOakStickItem() {
        String woodType = "pale_oak";
        Item stickItem = new MoreStickVariantItem(woodType, new Item.Properties());
        invokeRegisterStickItem(stickItem, Items.STICK);
        PaleOakStickHolder.PALE_OAK_STICK = stickItem;
        return stickItem;
    }
}
