package de.pnku.more_variants_core.mixin.mstv.more_armor_stand_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import de.pnku.mstv_masv.item.MoreArmorStandVariantItem;
import de.pnku.mstv_masv.item.MoreArmorStandVariantItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MoreArmorStandVariantItems.class)
public abstract class MoreArmorStandVariantItemsMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_ARMOR_STAND = registerPaleOakArmorStandItem();

    @Shadow
    private static void registerArmorStandItem(Item armorStandItem, Item armorStandAfter) {}

    @Unique
    private static Item registerPaleOakArmorStandItem() {
        Item armorStandItem = new MoreArmorStandVariantItem(WOOD_TYPE.getName(), new Item.Properties().stacksTo(16)); 
        registerArmorStandItem(armorStandItem, Items.ARMOR_STAND);
        MoreVariantHolder.setItem(VariantType.ARMOR_STAND, WOOD_TYPE, armorStandItem);
        return armorStandItem;
    }
}
