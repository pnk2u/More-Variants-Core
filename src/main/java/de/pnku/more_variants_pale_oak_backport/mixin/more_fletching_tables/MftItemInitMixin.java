package de.pnku.more_variants_pale_oak_backport.mixin.more_fletching_tables;

import de.pnku.mft.init.MftItemInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MftItemInit.class)
public abstract class MftItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_FLETCHING_TABLE_ITEM = registerPaleOakFletchingTableItem();

    @Shadow
    private static void registerItem(BlockItem fletchingTable, Item fletchingTableAfter) {}

    @Unique
    private static BlockItem registerPaleOakFletchingTableItem() {
        BlockItem fletchingTableItem = new BlockItem(PaleOakVariantHolder.getBlock(PaleOakVariantHolder.FLETCHING_TABLE_FAMILY, WOOD_TYPE), new Item.Properties());
        registerItem(fletchingTableItem, Items.FLETCHING_TABLE);
        PaleOakVariantHolder.setItem(PaleOakVariantHolder.FLETCHING_TABLE_FAMILY, WOOD_TYPE, fletchingTableItem);
        return fletchingTableItem;
    }
}
