package de.pnku.more_variants_core.mixin.more_nemos_woodcutter_variants;

import de.pnku.more_nemos_woodcutter_variants.item.MoreWoodcutterItems;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;


@Mixin(MoreWoodcutterItems.class)
public abstract class MoreWoodcutterItemsMixin {
    @Shadow
    @Mutable
    @Final
    public static List<Item> more_woodcutter_items;

    @Unique
    private static List<Item> registerWoodcutterItemVariants(List<MoreVariantWoodType> woodTypes) {
        List<Item> woodcutterItems = new ArrayList<>();
        MoreVariantType woodcutterType = MoreVariantType.WOODCUTTER;
        for (MoreVariantWoodType woodType : woodTypes) {
            Item woodcutterItem = new BlockItem(MoreVariantHolder.getBlock(woodcutterType, woodType), new Item.Properties());
            MoreVariantHolder.setItem(woodcutterType, woodType, woodcutterItem);
            woodcutterItems.add(woodcutterItem);
        }
        return woodcutterItems;
        
    }

    @Inject(method = "registerItems", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterSticksAtHead(CallbackInfo ci) {
        more_woodcutter_items = new ArrayList<>(more_woodcutter_items);
        more_woodcutter_items.addAll(registerWoodcutterItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes()));
    }
}
