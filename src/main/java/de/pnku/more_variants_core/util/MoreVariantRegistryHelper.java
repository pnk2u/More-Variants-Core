package de.pnku.more_variants_core.util;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class MoreVariantRegistryHelper {
    public static void whenItemRegistered(ResourceLocation itemId, Consumer<Item> action) {
        if (BuiltInRegistries.ITEM.containsKey(itemId)) {
            action.accept(BuiltInRegistries.ITEM.get(itemId));
            return;
        }
        RegistryEntryAddedCallback.event(BuiltInRegistries.ITEM).register((itemIntId, id, item) -> {
            if (id.equals(itemId)) {
                action.accept(item);
            }
        });
    }

    public static void whenBlockRegistered(ResourceLocation blockId, Consumer<Block> action) {
        if (BuiltInRegistries.BLOCK.containsKey(blockId)) {
            action.accept(BuiltInRegistries.BLOCK.get(blockId));
            return;
        }
        RegistryEntryAddedCallback.event(BuiltInRegistries.BLOCK).register((blockIntId, id, block) -> {
            if (id.equals(blockId)) {
                action.accept(block);
            }
        });
    }
}
