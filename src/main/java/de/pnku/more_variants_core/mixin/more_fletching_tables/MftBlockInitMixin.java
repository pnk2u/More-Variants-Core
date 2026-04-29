package de.pnku.more_variants_core.mixin.more_fletching_tables;

import de.pnku.mft.block.MoreFletchingTablesBlock;
import de.pnku.mft.init.MftBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MftBlockInit.class)
public abstract class MftBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_FLETCHING_TABLE = registerPaleOakFletchingTableBlock();

    @Shadow
    private static void registerBlock(MoreFletchingTablesBlock fletchingTable) {}

    @Unique
    private static Block registerPaleOakFletchingTableBlock() {
        MoreFletchingTablesBlock fletchingTable = new MoreFletchingTablesBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(fletchingTable);
        MoreVariantHolder.setBlock(VariantType.FLETCHING_TABLE, WOOD_TYPE, fletchingTable);
        return fletchingTable;
    }
}
