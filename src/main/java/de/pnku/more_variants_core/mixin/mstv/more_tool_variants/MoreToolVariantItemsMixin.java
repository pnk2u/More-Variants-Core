package de.pnku.more_variants_core.mixin.mstv.more_tool_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantHolder.ToolType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_base.MoreStickVariants;
import de.pnku.mstv_mtoolv.item.MoreToolVariantItems;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Mixin(MoreToolVariantItems.class)
public abstract class MoreToolVariantItemsMixin {
    @Shadow
    private static void registerAxeItem(Item axeItem, Item stickIngredient, Item toolIngredient, String materialPrefix) {}

    @Shadow
    private static void registerPickaxeItem(Item pickaxeItem, Item stickIngredient, Item toolIngredient, String materialPrefix) {}

    @Shadow
    private static void registerHoeItem(Item hoeItem, Item stickIngredient, Item toolIngredient, String materialPrefix) {}

    @Shadow
    private static void registerShovelItem(Item shovelItem, Item stickIngredient, Item toolIngredient, String materialPrefix) {}

    @Shadow
    private static void registerBrushItem(Item brushItem, Item stickIngredient) {}

    @Unique
    private static Item createToolItemVariant(ToolType toolType) {
        if(toolType.isBrush()) {
            return new BrushItem(new Item.Properties().durability(64));
        } else if (toolType.isAxe()) {
            return new AxeItem(toolType.materialType().tier(), (new Item.Properties())
                    .attributes(AxeItem.createAttributes(toolType.materialType().tier(), toolType.aD(), toolType.aS())));
        } else if (toolType.isPickaxe()) {
            return new PickaxeItem(toolType.materialType().tier(), (new Item.Properties())
                    .attributes(PickaxeItem.createAttributes(toolType.materialType().tier(), toolType.aD(), toolType.aS())));
        } else if (toolType.isHoe()) {
            return new HoeItem(toolType.materialType().tier(), (new Item.Properties())
                    .attributes(HoeItem.createAttributes(toolType.materialType().tier(), toolType.aD(), toolType.aS())));
        } else if (toolType.isShovel()) {
            return new ShovelItem(toolType.materialType().tier(), (new Item.Properties())
                    .attributes(ShovelItem.createAttributes(toolType.materialType().tier(), toolType.aD(), toolType.aS())));
        } else {
            throw new IllegalArgumentException("Invalid tool type: " + toolType);
        }
    }

    @Unique
    private static void registerToolItemVariantForType(ToolType[] toolTypes, MoreVariantWoodType woodType, Item stickItem, Item secondaryIngredient) {
        for (ToolType toolType : toolTypes) {
            Item toolItem = createToolItemVariant(toolType);
            if (toolType.isBrush()) {
                registerBrushItem(toolItem, stickItem);
            } else {
                MoreVariantHolder.MaterialType materialType = toolType.materialType();
                if (toolType.materialType().isNetherite()) {
                    ToolType diamondVariant = toolType.getToolTypeByToolTypeTypeAndMaterialType(toolType.toolTypeType(), MoreVariantHolder.MaterialType.DIAMOND);
                    secondaryIngredient = MoreVariantHolder.getItem(diamondVariant, woodType);
                }
                Item toolIngredient = Objects.requireNonNullElse(materialType.ingredientItem(), secondaryIngredient);
                if (toolType.isAxe()) {
                    registerAxeItem(toolItem, stickItem, toolIngredient, materialType.namePrefix());
                } else if (toolType.isPickaxe()) {
                    registerPickaxeItem(toolItem, stickItem, toolIngredient, materialType.namePrefix());
                } else if (toolType.isHoe()) {
                    registerHoeItem(toolItem, stickItem, toolIngredient, materialType.namePrefix());
                } else if (toolType.isShovel()) {
                    registerShovelItem(toolItem, stickItem, toolIngredient, materialType.namePrefix());
                }
            }
            MoreVariantHolder.setItem(toolType, woodType, toolItem);
        }
    }

    @Unique
    private static void whenItemRegistered(ResourceLocation itemId, Consumer<Item> action) {
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

    @Unique
    private static void whenBlockRegistered(ResourceLocation blockId, Consumer<Block> action) {
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

    @Unique
    private static void registerToolItemVariants(List<MoreVariantWoodType> woodTypes) {
        ToolType[] toolTypes = ToolType.values();
        for (MoreVariantWoodType woodType : woodTypes) {
            ResourceLocation stickId = MoreVariantHolder.getRegistrationId(MoreVariantHolder.MoreVariantType.STICK, woodType);
            ResourceLocation planksId = woodType.getPlanksBlockId();
            if (BuiltInRegistries.ITEM.containsKey(stickId)) {
                Item stickItem = MoreVariantHolder.getItem(MoreVariantHolder.MoreVariantType.STICK, woodType);
                whenBlockRegistered(planksId, block -> registerToolItemVariantForType(toolTypes, woodType, stickItem, block.asItem()));
            } else {
                whenItemRegistered(stickId, item ->
                        whenBlockRegistered(planksId, block -> registerToolItemVariantForType(toolTypes, woodType, item, block.asItem())));
            }
        }
    }

    @Inject(method = "registerToolItems", at=@At("TAIL"), remap = false)
    private static void injectedRegisterToolItemsAtTail(CallbackInfo ci) {
        registerToolItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    }
}
