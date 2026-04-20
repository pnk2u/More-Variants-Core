package de.pnku.more_variants_pale_oak_backport.mixin.holder;

import net.minecraft.world.item.Item;

public final class PaleOakShieldHolder {
    private static Item paleOakShield;

    public static void setItem(Item item) {
        paleOakShield = item;
    }

    public static Item getItem() {
        return paleOakShield;
    }

    private PaleOakShieldHolder() {
    }
}

