package de.pnku.more_variants_core.mixin.more_smithing_tables;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import de.pnku.mst.block.MoreSmithingTableBlock;
import de.pnku.mst.init.MstBlockInit;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MstBlockInit.class)
public abstract class MstBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_SMITHING_TABLE = registerPaleOakSmithingTable();

    @Shadow
    private static void registerBlock(Block block) {}

    @Unique
    private static Block registerPaleOakSmithingTable() {
        Block smithingTableBlock = new MoreSmithingTableBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(smithingTableBlock);
        MoreVariantHolder.setBlock(VariantType.SMITHING_TABLE, WOOD_TYPE, smithingTableBlock);
        return smithingTableBlock;
    }
}
