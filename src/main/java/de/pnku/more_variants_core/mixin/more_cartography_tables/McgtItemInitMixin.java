package de.pnku.more_variants_core.mixin.more_cartography_tables;

import de.pnku.mcgt.init.McgtItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(McgtItemInit.class)
public abstract class McgtItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem cartographyTable, Item cartographyTableAfter) {}

    @Unique
    private static void registerCartographyTableItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantType cartographyTableType = MoreVariantType.CARTOGRAPHY_TABLE;
        for (MoreVariantWoodType woodType : woodTypes) {
            BlockItem cartographyTableItem = new BlockItem(MoreVariantHolder.getBlock(cartographyTableType, woodType), new Item.Properties());
            registerItem(cartographyTableItem, cartographyTableType.getVanillaItem());
            MoreVariantHolder.setItem(cartographyTableType, woodType, cartographyTableItem);
        }
    }

    @Inject(method = "registerItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterCartographyTableItemsAtHead(CallbackInfo ci) {
        registerCartographyTableItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
