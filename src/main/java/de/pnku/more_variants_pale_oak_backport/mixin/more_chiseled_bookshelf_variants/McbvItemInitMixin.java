package de.pnku.more_variants_pale_oak_backport.mixin.more_chiseled_bookshelf_variants;

import de.pnku.mcbv.init.McbvItemInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McbvItemInit.class)
public abstract class McbvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_CHISELED_BOOKSHELF_ITEM = registerPaleOakChiseledBookshelfItem();

    @Shadow
    private static void registerItem(BlockItem chiseledBookshelf, Item chiseledBookshelfAfter) {}

    @Unique
    private static BlockItem registerPaleOakChiseledBookshelfItem() {
        BlockItem chiseledBookshelfItem = new BlockItem(PaleOakVariantHolder.getBlock(VariantType.CHISELED_BOOKSHELF, WOOD_TYPE), new Item.Properties());
        registerItem(chiseledBookshelfItem, Items.CHISELED_BOOKSHELF);
        PaleOakVariantHolder.setItem(VariantType.CHISELED_BOOKSHELF, WOOD_TYPE, chiseledBookshelfItem);
        return chiseledBookshelfItem;
    }
}
