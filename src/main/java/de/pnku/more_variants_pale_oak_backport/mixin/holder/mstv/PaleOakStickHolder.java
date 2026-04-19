package de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv;

import net.minecraft.world.item.Item;

public final class PaleOakStickHolder {
    private static Item paleOakStick;

    public static void setItem(Item item) {
        paleOakStick = item;
    }

    public static Item getItem() {
        return paleOakStick;
    }

    private PaleOakStickHolder() {
    }
}

