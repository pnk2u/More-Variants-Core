package de.pnku.more_variants_pale_oak_backport.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantItem;
import de.pnku.lolmsv.item.MoreShieldVariantItems;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakShieldHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MoreShieldVariantItems.class)
public abstract class MoreShieldVariantItemsMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_SHIELD = registerPaleOakShieldItem();

    @Shadow
    private static void registerShieldItem(Item shieldItem) {}

    @Shadow
    private static Item.Properties setProperties() {
        throw new AssertionError();
    }

    @Unique
    private static Item registerPaleOakShieldItem() {
        Item shieldItem = new MoreShieldVariantItem(WOOD_TYPE.getName(), setProperties());
        registerShieldItem(shieldItem);
        PaleOakShieldHolder.setItem(WOOD_TYPE, shieldItem);
        return shieldItem;
    }
}
