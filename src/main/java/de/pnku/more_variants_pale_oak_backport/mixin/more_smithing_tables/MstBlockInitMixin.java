package de.pnku.more_variants_pale_oak_backport.mixin.more_smithing_tables;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.mst.block.MoreSmithingTableBlock;
import de.pnku.mst.init.MstBlockInit;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MstBlockInit.class)
public abstract class MstBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_SMITHING_TABLE = registerPaleOakSmithingTable();

    @Shadow
    private static void registerBlock(Block block) {}

    @Unique
    private static Block registerPaleOakSmithingTable() {
        Block smithingTableBlock = new MoreSmithingTableBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(smithingTableBlock);
        PaleOakVariantHolder.setBlock(VariantType.SMITHING_TABLE, WOOD_TYPE, smithingTableBlock);
        return smithingTableBlock;
    }
}
