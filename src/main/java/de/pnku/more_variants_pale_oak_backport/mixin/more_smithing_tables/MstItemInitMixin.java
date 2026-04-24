package de.pnku.more_variants_pale_oak_backport.mixin.more_smithing_tables;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.mst.init.MstItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MstItemInit.class)
public abstract class MstItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_SMITHING_TABLE_ITEM = registerPaleOakSmithingTableItem();

    @Shadow
    private static void registerItem(Item smithingTableItem) {}

    @Unique
    private static Item registerPaleOakSmithingTableItem() {
        Item smithingTableItem = new BlockItem(PaleOakVariantHolder.getBlock(VariantType.SMITHING_TABLE, WOOD_TYPE), new Item.Properties());
        registerItem(smithingTableItem);
        PaleOakVariantHolder.setItem(VariantType.SMITHING_TABLE, WOOD_TYPE, smithingTableItem);
        return smithingTableItem;
    }
}
