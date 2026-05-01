package de.pnku.more_variants_core.mixin.more_crafting_tables;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import io.github.lieonlion.lolmct.init.MctItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MctItemInit.class)
public abstract class MctItemInitMixin {
    @Shadow
    public static void registerItem(String name, Item item, Item after) {}

    @Unique
    private static void registerCraftingTableItemVariants(List<WoodType> woodTypes) {
        VariantType craftingTableType = VariantType.CRAFTING_TABLE;
        for (WoodType woodType : woodTypes) {
            BlockItem craftingTableItem = new BlockItem(MoreVariantHolder.getBlock(craftingTableType, woodType), new Item.Properties());
            registerItem(woodType.getName() + "_" + craftingTableType.registrationType(), craftingTableItem, craftingTableType.getVanillaItem());
            MoreVariantHolder.setItem(craftingTableType, woodType, craftingTableItem);
        }
    }

    @Inject(method = "registerItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterItemsAtHead(CallbackInfo ci) {
        registerCraftingTableItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
