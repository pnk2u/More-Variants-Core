package de.pnku.more_variants_core.mixin.mstv.more_tool_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.ToolType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mstv_mtoolv.item.MoreToolVariantItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

import static de.pnku.more_variants_core.util.MoreVariantHolder.getRegistrationIds;
import static de.pnku.more_variants_core.util.MoreVariantRegistryHelper.whenItemRegistered;

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
        Item.Properties properties = new Item.Properties();
        if (toolType.materialType() == MoreVariantHolder.MaterialType.NETHERITE) {
            properties = properties.fireResistant();
        }
        if(toolType.isBrush()) {
            return new BrushItem(new Item.Properties().durability(64));
        } else if (toolType.isAxe()) {
            return new AxeItem(toolType.materialType().tier(), toolType.aD(), toolType.aS(), (properties));
        } else if (toolType.isPickaxe()) {
            return new PickaxeItem(toolType.materialType().tier(), (int) toolType.aD(), toolType.aS(), (properties));
        } else if (toolType.isHoe()) {
            return new HoeItem(toolType.materialType().tier(), (int) toolType.aD(), toolType.aS(), (properties));
        } else if (toolType.isShovel()) {
            return new ShovelItem(toolType.materialType().tier(), toolType.aD(), toolType.aS(), (properties));
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
    private static void registerToolItemVariants(List<MoreVariantWoodType> woodTypes) {
        ToolType[] toolTypes = ToolType.values();
        for (MoreVariantWoodType woodType : woodTypes) {
            ResourceLocation stickId = MoreVariantHolder.getRegistrationId(MoreVariantHolder.MoreVariantType.STICK, woodType);
            ResourceLocation planksId = woodType.planksId();
            whenItemRegistered(stickId, stickItem
                    -> whenItemRegistered(planksId, planksItem
                                    -> registerToolItemVariantForType(toolTypes, woodType, stickItem, planksItem),
                            getRegistrationIds(toolTypes, woodType)),
                    getRegistrationIds(toolTypes, woodType));
        }
    }

    @Inject(method = "registerToolItems", at=@At("TAIL"), remap = false)
    private static void injectedRegisterToolItemsAtTail(CallbackInfo ci) {
        registerToolItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
