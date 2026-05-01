package de.pnku.more_variants_core.mixin.more_fletching_tables;

import de.pnku.mft.init.MftItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MftItemInit.class)
public abstract class MftItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem fletchingTable, Item fletchingTableAfter) {}

    @Unique
    private static void registerFletchingTableItemVariants(List<WoodType> woodTypes) {
        VariantType fletchingTableType = VariantType.FLETCHING_TABLE;
        for (WoodType woodType : woodTypes) {
            BlockItem fletchingTableItem = new BlockItem(MoreVariantHolder.getBlock(fletchingTableType, woodType), new Item.Properties());
            registerItem(fletchingTableItem, fletchingTableType.getVanillaItem());
            MoreVariantHolder.setItem(fletchingTableType, woodType, fletchingTableItem);
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterFletchingTableItemsAtTail(CallbackInfo ci) {
        registerFletchingTableItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
