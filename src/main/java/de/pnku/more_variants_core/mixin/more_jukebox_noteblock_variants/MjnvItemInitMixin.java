package de.pnku.more_variants_core.mixin.more_jukebox_noteblock_variants;

import de.pnku.mjnv.init.MjnvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.JukeboxNoteblockType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MjnvItemInit.class)
public abstract class MjnvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

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
        BlockItem item = new BlockItem(MoreVariantHolder.getBlock(type, WOOD_TYPE), new Item.Properties());
        MoreVariantHolder.setItem(type.variantType(), WOOD_TYPE, item);
        if (type == JukeboxNoteblockType.JUKEBOX) {
            registerJukeboxItem(item, Items.JUKEBOX);
        } else {
            registerNoteblockItem(item, Items.NOTE_BLOCK);
        }
        return item;
    }
}
