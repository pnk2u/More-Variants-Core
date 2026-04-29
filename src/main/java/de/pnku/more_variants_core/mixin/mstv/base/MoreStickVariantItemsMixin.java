package de.pnku.more_variants_core.mixin.mstv.base;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
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
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_STICK = registerPaleOakStickItem();

    @Shadow
    @SuppressWarnings("SameParameterValue")
    private static void registerStickItem(Item stickItem, Item stickItemAfter) {}

    @Unique
    private static Item registerPaleOakStickItem() {
        Item stickItem = new MoreStickVariantItem(WOOD_TYPE.getName(), new Item.Properties());
        registerStickItem(stickItem, Items.STICK);
        MoreVariantHolder.setItem(VariantType.STICK, WOOD_TYPE, stickItem);
        return stickItem;
    }
}
