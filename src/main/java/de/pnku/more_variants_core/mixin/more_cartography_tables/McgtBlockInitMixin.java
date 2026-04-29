package de.pnku.more_variants_core.mixin.more_cartography_tables;

import de.pnku.mcgt.block.MoreCartographyTablesBlock;
import de.pnku.mcgt.init.McgtBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McgtBlockInit.class)
public abstract class McgtBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final MoreCartographyTablesBlock PALE_OAK_CARTOGRAPHY_TABLE = registerPaleOakCartographyTableBlock();

    @Shadow
    private static void registerBlock(MoreCartographyTablesBlock cartographyTable) {}

    @Unique
    private static MoreCartographyTablesBlock registerPaleOakCartographyTableBlock() {
        MoreCartographyTablesBlock cartographyTable = new MoreCartographyTablesBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(cartographyTable);
        MoreVariantHolder.setBlock(VariantType.CARTOGRAPHY_TABLE, WOOD_TYPE, cartographyTable);
        return cartographyTable;
    }
}
