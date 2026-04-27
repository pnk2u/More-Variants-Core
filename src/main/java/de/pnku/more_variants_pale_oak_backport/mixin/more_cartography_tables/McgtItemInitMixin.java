package de.pnku.more_variants_pale_oak_backport.mixin.more_cartography_tables;

import de.pnku.mcgt.init.McgtItemInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McgtItemInit.class)
public abstract class McgtItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_CARTOGRAPHY_TABLE_ITEM = registerPaleOakCartographyTableItem();

    @Shadow
    private static void registerItem(BlockItem cartographyTable, Item cartographyTableAfter) {}

    @Unique
    private static BlockItem registerPaleOakCartographyTableItem() {
        BlockItem cartographyTableItem = new BlockItem(PaleOakVariantHolder.getBlock(PaleOakVariantHolder.VariantType.CARTOGRAPHY_TABLE, WOOD_TYPE), new Item.Properties());
        registerItem(cartographyTableItem, Items.CARTOGRAPHY_TABLE);
        PaleOakVariantHolder.setItem(VariantType.CARTOGRAPHY_TABLE, WOOD_TYPE, cartographyTableItem);
        return cartographyTableItem;
    }
}
