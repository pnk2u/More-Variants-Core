package de.pnku.more_variants_core.util;

import de.pnku.mstv_base.MoreStickVariants;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.mstv_base.item.MoreStickVariantItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public final class StickVariantRegistrationHelper {
    public static List<Item> registerStickItemVariants(List<WoodType> woodTypes) {
        List<Item> registeredStickItems = new ArrayList<>();
        VariantType stickType = VariantType.STICK;
        for (WoodType woodType : woodTypes) {
            if (BuiltInRegistries.ITEM.containsKey(MoreStickVariants.withModId(woodType.getName() + "_" + stickType.registrationType()))) {
                continue;
            }
            Item stickItem = new MoreStickVariantItem(woodType.getName(), new Item.Properties());
            MoreVariantHolder.setItem(stickType, woodType, stickItem);
            registeredStickItems.add(stickItem);
        }
        return registeredStickItems;
    }

    private StickVariantRegistrationHelper() {}
}