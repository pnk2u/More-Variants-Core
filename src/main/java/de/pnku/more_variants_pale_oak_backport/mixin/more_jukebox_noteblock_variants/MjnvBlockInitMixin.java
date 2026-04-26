package de.pnku.more_variants_pale_oak_backport.mixin.more_jukebox_noteblock_variants;

import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import de.pnku.mjnv.block.MoreNoteblockVariantBlock;
import de.pnku.mjnv.init.MjnvBlockInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.JukeboxNoteblockType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MjnvBlockInit.class)
public abstract class MjnvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_JUKEBOX = registerPaleOakJukeboxNoteblockBlock(JukeboxNoteblockType.JUKEBOX);

    @Unique
    private static final Block PALE_OAK_NOTEBLOCK = registerPaleOakJukeboxNoteblockBlock(JukeboxNoteblockType.NOTEBLOCK);

    @Shadow
    private static void registerJukeboxBlock(Block jukebox) {}

    @Shadow
    private static void registerNoteblockBlock(Block noteblock) {}

    @Unique
    private static Block registerPaleOakJukeboxNoteblockBlock(JukeboxNoteblockType type) {
        Block block = JukeboxNoteblockType.JUKEBOX.equals(type)
                ? new MoreJukeboxVariantBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName())
                : new MoreNoteblockVariantBlock(WOOD_TYPE.getMapColor(), WOOD_TYPE.getName());
        PaleOakVariantHolder.setBlock(type.variantType(), WOOD_TYPE, type, block);
        if (type == JukeboxNoteblockType.JUKEBOX) {
            registerJukeboxBlock(block);
        } else {
            registerNoteblockBlock(block);
        }
        return block;
    }

}
