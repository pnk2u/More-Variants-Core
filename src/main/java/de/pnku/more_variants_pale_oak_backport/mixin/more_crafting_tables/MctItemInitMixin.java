package de.pnku.more_variants_pale_oak_backport.mixin.more_crafting_tables;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import io.github.lieonlion.lolmct.init.MctItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MctItemInit.class)
public abstract class MctItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_CRAFTING_TABLE_ITEM = registerPaleOakCraftingTableItem();

    @Shadow
    public static void registerItem(String name, Item item, Item after) {}

    @Inject(method = "registerItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterItemsAtHead(CallbackInfo ci) {
        if (PALE_OAK_CRAFTING_TABLE_ITEM.getDefaultInstance().isEmpty()) {
            throw new IllegalStateException("Failed to register Pale Oak Crafting Table Item");
        }
    }

    @Unique
    private static BlockItem registerPaleOakCraftingTableItem() {
        BlockItem craftingTableItem = new BlockItem(PaleOakVariantHolder.getBlock(VariantType.CRAFTING_TABLE, WOOD_TYPE), new Item.Properties());
        registerItem(WOOD_TYPE.getName() + "_crafting_table", craftingTableItem, Items.CRAFTING_TABLE);
        PaleOakVariantHolder.setItem(VariantType.CRAFTING_TABLE, WOOD_TYPE, craftingTableItem);
        return craftingTableItem;
    }
}
