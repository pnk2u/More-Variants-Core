package de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.Item;

import java.util.EnumMap;
import java.util.Map;

public final class PaleOakStickHolder {
    private static final Map<WoodType, Item> ITEMS = new EnumMap<>(WoodType.class);

    public static void setItem(WoodType woodType, Item item) {
        ITEMS.put(woodType, item);
    }

    public static Item getItem(WoodType woodType) {
        Item item = ITEMS.get(woodType);
        if (item == null) {
            throw new IllegalStateException("Stick item is not registered for wood type: " + woodType);
        }
        return item;
    }

    private PaleOakStickHolder() {
    }
}

