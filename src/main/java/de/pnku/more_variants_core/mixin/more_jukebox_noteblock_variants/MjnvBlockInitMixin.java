package de.pnku.more_variants_core.mixin.more_jukebox_noteblock_variants;

import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import de.pnku.mjnv.block.MoreNoteblockVariantBlock;
import de.pnku.mjnv.init.MjnvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.JukeboxNoteblockType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MjnvBlockInit.class)
public abstract class MjnvBlockInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

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
        MoreVariantHolder.setBlock(type, WOOD_TYPE, block);
        if (type == JukeboxNoteblockType.JUKEBOX) {
            registerJukeboxBlock(block);
        } else {
            registerNoteblockBlock(block);
        }
        return block;
    }

}
