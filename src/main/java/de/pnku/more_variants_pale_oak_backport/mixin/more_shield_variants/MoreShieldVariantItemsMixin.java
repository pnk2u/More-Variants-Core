package de.pnku.more_variants_pale_oak_backport.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantItem;
import de.pnku.lolmsv.item.MoreShieldVariantItems;
import de.pnku.more_variants_pale_oak_backport.PaleOakConstants;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakShieldHolder;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MoreShieldVariantItems.class)
public abstract class MoreShieldVariantItemsMixin {
    @Unique
    private static final Item PALE_OAK_SHIELD = registerPaleOakShieldItem();

    @Invoker("registerShieldItem")
    @SuppressWarnings("SameParameterValue")
    private static void invokeRegisterShieldItem(Item shieldItem) {
        throw new AssertionError();
    }

    @Invoker("setProperties")
    @SuppressWarnings("SameParameterValue")
    private static Item.Properties invokeSetProperties() {
        throw new AssertionError();
    }

    @Unique
    private static Item registerPaleOakShieldItem() {
        Item shieldItem = new MoreShieldVariantItem(PaleOakConstants.WOOD_TYPE, invokeSetProperties());
        invokeRegisterShieldItem(shieldItem);
        PaleOakShieldHolder.setItem(shieldItem);
        return shieldItem;
    }
}
