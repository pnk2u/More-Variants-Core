package de.pnku.more_variants_core.mixin.more_smithing_tables;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.mst.init.MstItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MstItemInit.class)
public abstract class MstItemInitMixin {
    @Shadow
    private static void registerItem(Item smithingTableItem) {}

    @Unique
    private static void registerSmithingTableItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantType smithingTableType = MoreVariantType.SMITHING_TABLE;
        for (MoreVariantWoodType woodType : woodTypes) {
            Item smithingTableItem = new BlockItem(MoreVariantHolder.getBlock(smithingTableType, woodType), new Item.Properties());
            registerItem(smithingTableItem);
            MoreVariantHolder.setItem(smithingTableType, woodType, smithingTableItem);
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterItemsAtTail(CallbackInfo ci) {
        registerSmithingTableItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
