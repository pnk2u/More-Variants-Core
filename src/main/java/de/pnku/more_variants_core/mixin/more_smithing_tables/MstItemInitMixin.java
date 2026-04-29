package de.pnku.more_variants_core.mixin.more_smithing_tables;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import de.pnku.mst.init.MstItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MstItemInit.class)
public abstract class MstItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_SMITHING_TABLE_ITEM = registerPaleOakSmithingTableItem();

    @Shadow
    private static void registerItem(Item smithingTableItem) {}

    @Unique
    private static Item registerPaleOakSmithingTableItem() {
        Item smithingTableItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.SMITHING_TABLE, WOOD_TYPE), new Item.Properties());
        registerItem(smithingTableItem);
        MoreVariantHolder.setItem(VariantType.SMITHING_TABLE, WOOD_TYPE, smithingTableItem);
        return smithingTableItem;
    }
}
