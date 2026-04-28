package de.pnku.more_variants_pale_oak_backport.mixin.more_jukebox_noteblock_variants;

import de.pnku.mjnv.init.MjnvItemInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.JukeboxNoteblockType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MjnvItemInit.class)
public abstract class MjnvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_JUKEBOX = registerPaleOakJukeboxNoteblockItem(JukeboxNoteblockType.JUKEBOX);

    @Unique
    private static final BlockItem PALE_OAK_NOTEBLOCK = registerPaleOakJukeboxNoteblockItem(JukeboxNoteblockType.NOTEBLOCK);

    @Shadow
    private static void registerJukeboxItem(BlockItem jukebox, Item jukeboxAfter) {}

    @Shadow
    private static void registerNoteblockItem(BlockItem noteblock, Item noteblockAfter) {}

    @Unique
    private static BlockItem registerPaleOakJukeboxNoteblockItem(JukeboxNoteblockType type) {
        BlockItem item = new BlockItem(PaleOakVariantHolder.getBlock(type, WOOD_TYPE), new Item.Properties());
        PaleOakVariantHolder.setItem(type.variantType(), WOOD_TYPE, item);
        if (type == JukeboxNoteblockType.JUKEBOX) {
            registerJukeboxItem(item, Items.JUKEBOX);
        } else {
            registerNoteblockItem(item, Items.NOTE_BLOCK);
        }
        return item;
    }
}
