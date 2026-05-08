package de.pnku.more_variants_core.mixin.more_barrel_variants;

import de.pnku.more_barrel_variants.init.MoreBarrelItems;
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


@Mixin(MoreBarrelItems.class)
public abstract class MoreBarrelItemsMixin {
    @Shadow
    @Mutable
    @Final
    public static List<Item> more_barrels;

    @Unique
    private static List<Item> registerBarrelItemVariants(List<MoreVariantWoodType> woodTypes) {
        List<Item> barrelItems = new ArrayList<>();
        MoreVariantType barrelType = MoreVariantHolder.MoreVariantType.BARREL;
        for (MoreVariantWoodType woodType : woodTypes) {
            Item barrelItem = new BlockItem(MoreVariantHolder.getBlock(barrelType, woodType), new Item.Properties());
            MoreVariantHolder.setItem(barrelType, woodType, barrelItem);
            barrelItems.add(barrelItem);
        }
        return barrelItems;
        
    }

    @Inject(method = "registerItems", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterSticksAtHead(CallbackInfo ci) {
        more_barrels = new ArrayList<>(more_barrels);
        more_barrels.addAll(registerBarrelItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes()));
    }
}
