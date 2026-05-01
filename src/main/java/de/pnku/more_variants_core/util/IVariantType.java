package de.pnku.more_variants_core.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface IVariantType {
    String registrationType();
    Block getVanillaBlock();
    Item getVanillaItem();
}
