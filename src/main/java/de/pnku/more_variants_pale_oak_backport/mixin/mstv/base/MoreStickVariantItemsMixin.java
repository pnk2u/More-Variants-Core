package de.pnku.more_variants_pale_oak_backport.mixin.mstv.base;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakStickHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import de.pnku.mstv_base.item.MoreStickVariantItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;


@Mixin(MoreStickVariantItems.class)
public abstract class MoreStickVariantItemsMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_STICK = registerPaleOakStickItem();

    @Shadow
    @SuppressWarnings("SameParameterValue")
    private static void registerStickItem(Item stickItem, Item stickItemAfter) {}

    @Unique
    private static Item registerPaleOakStickItem() {
        Item stickItem = new MoreStickVariantItem(WOOD_TYPE.getName(), new Item.Properties());
        registerStickItem(stickItem, Items.STICK);
        PaleOakStickHolder.setItem(WOOD_TYPE, stickItem);
        return stickItem;
    }
}
