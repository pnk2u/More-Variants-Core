package de.pnku.more_variants_core.mixin.more_chiseled_bookshelf_variants;

import de.pnku.mcbv.block.MoreChiseledBookShelfBlock;
import de.pnku.mcbv.init.McbvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McbvBlockInit.class)
public abstract class McbvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_CHISELED_BOOKSHELF = registerPaleOakChiseledBookshelf();

    @Shadow
    private static void registerBlock(MoreChiseledBookShelfBlock chiseledBookshelf) {}

    @Unique
    private static Block registerPaleOakChiseledBookshelf() {
        MoreChiseledBookShelfBlock block = new MoreChiseledBookShelfBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(block);
        MoreVariantHolder.setBlock(VariantType.CHISELED_BOOKSHELF, WOOD_TYPE, block);
        return block;
    }
}
