package de.pnku.more_variants_core.mixin.more_fletching_tables;

import de.pnku.mft.init.MftItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MftItemInit.class)
public abstract class MftItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_FLETCHING_TABLE_ITEM = registerPaleOakFletchingTableItem();

    @Shadow
    private static void registerItem(BlockItem fletchingTable, Item fletchingTableAfter) {}

    @Unique
    private static BlockItem registerPaleOakFletchingTableItem() {
        BlockItem fletchingTableItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.FLETCHING_TABLE, WOOD_TYPE), new Item.Properties());
        registerItem(fletchingTableItem, Items.FLETCHING_TABLE);
        MoreVariantHolder.setItem(VariantType.FLETCHING_TABLE, WOOD_TYPE, fletchingTableItem);
        return fletchingTableItem;
    }
}
