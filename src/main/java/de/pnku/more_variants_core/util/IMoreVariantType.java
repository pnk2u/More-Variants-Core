package de.pnku.more_variants_core.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface IMoreVariantType {
    ResourceLocation getRegistrationId(MoreVariantWoodType woodType);
    default String registrationType() {
        return ((Enum<?>) this).name().toLowerCase();
    }
    Block getVanillaBlock();
    Item getVanillaItem();
}
