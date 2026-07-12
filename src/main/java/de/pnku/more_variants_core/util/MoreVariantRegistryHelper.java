package de.pnku.more_variants_core.util;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class MoreVariantRegistryHelper {

    public static void whenItemRegistered(ResourceLocation itemId, Consumer<Item> action) {
        whenItemRegistered(itemId, action, List.of());
    }

    public static void whenItemRegistered(ResourceLocation itemId, Consumer<Item> action, ResourceLocation waitingItemId) {
        whenItemRegistered(itemId, action, List.of(waitingItemId));
    }

    public static void whenItemRegistered(ResourceLocation waitedForItemId, Consumer<Item> action, List<ResourceLocation> waitingItemIds) {
        if (BuiltInRegistries.ITEM.containsKey(waitedForItemId)) {
            action.accept(BuiltInRegistries.ITEM.get(waitedForItemId));
        } else {
            AtomicBoolean foundWaitingItem = new AtomicBoolean(false);
            waitingItemIds.forEach(waitingItemId -> {
                if (BuiltInRegistries.ITEM.containsKey(waitingItemId)) {
                    foundWaitingItem.set(true);
                }
            });
            if (!foundWaitingItem.get()) {
                RegistryEntryAddedCallback.event(BuiltInRegistries.ITEM).register((itemIntId, id, item) -> {
                    if (id.equals(waitedForItemId)) {
                        action.accept(item);
                    }
                });
            }
        }
    }

    public static void whenBlockRegistered(ResourceLocation blockId, Consumer<Block> action) {
        if (BuiltInRegistries.BLOCK.containsKey(blockId)) {
            action.accept(BuiltInRegistries.BLOCK.get(blockId));
        } else {
            RegistryEntryAddedCallback.event(BuiltInRegistries.BLOCK).register((blockIntId, id, block) -> {
                if (id.equals(blockId)) {
                    action.accept(block);
                }
            });
        }
    }
}
