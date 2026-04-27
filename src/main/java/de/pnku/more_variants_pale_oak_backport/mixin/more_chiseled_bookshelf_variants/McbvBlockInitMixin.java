package de.pnku.more_variants_pale_oak_backport.mixin.more_chiseled_bookshelf_variants;

import de.pnku.mcbv.block.MoreChiseledBookShelfBlock;
import de.pnku.mcbv.init.McbvBlockInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McbvBlockInit.class)
public abstract class McbvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_CHISELED_BOOKSHELF = registerPaleOakChiseledBookshelf();

    @Shadow
    private static void registerBlock(MoreChiseledBookShelfBlock chiseledBookshelf) {}

    @Unique
    private static Block registerPaleOakChiseledBookshelf() {
        MoreChiseledBookShelfBlock block = new MoreChiseledBookShelfBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        registerBlock(block);
        PaleOakVariantHolder.setBlock(VariantType.CHISELED_BOOKSHELF, WOOD_TYPE, block);
        return block;
    }
}
