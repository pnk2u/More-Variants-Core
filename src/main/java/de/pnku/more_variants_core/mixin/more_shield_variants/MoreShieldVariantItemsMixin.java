package de.pnku.more_variants_core.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantItem;
import de.pnku.lolmsv.item.MoreShieldVariantItems;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MoreShieldVariantItems.class)
public abstract class MoreShieldVariantItemsMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

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
        MoreVariantHolder.setItem(VariantType.SHIELD, WOOD_TYPE, shieldItem);
        return shieldItem;
    }
}
