package de.pnku.more_variants_core.mixin.more_barrel_variants;

import de.pnku.more_barrel_variants.init.MoreBarrelItems;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
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
    private static List<Item> registerBarrelItemVariants(List<WoodType> woodTypes) {
        List<Item> barrelItems = new ArrayList<>();
        for (WoodType woodType : woodTypes) {
            Item barrelItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.BARREL, woodType), new Item.Properties());
            MoreVariantHolder.setItem(VariantType.BARREL, woodType, barrelItem);
            barrelItems.add(barrelItem);
        }
        return barrelItems;
        
    }

    @Inject(method = "registerItems", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterSticksAtHead(CallbackInfo ci) {
        more_barrels = new ArrayList<>(more_barrels);
        more_barrels.addAll(registerBarrelItemVariants(WoodTypeHolder.getWoodTypes()));
    }
}
