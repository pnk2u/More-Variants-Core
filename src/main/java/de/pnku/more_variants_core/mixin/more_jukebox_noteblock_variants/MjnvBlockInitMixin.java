package de.pnku.more_variants_core.mixin.more_jukebox_noteblock_variants;

import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import de.pnku.mjnv.block.MoreNoteblockVariantBlock;
import de.pnku.mjnv.init.MjnvBlockInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.JukeboxNoteblockType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MjnvBlockInit.class)
public abstract class MjnvBlockInitMixin {
    @Shadow
    private static void registerJukeboxBlock(Block jukebox) {}

    @Shadow
    private static void registerNoteblockBlock(Block noteblock) {}

    @Unique
    private static void registerJukeboxNoteblockBlockVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            for (JukeboxNoteblockType type : JukeboxNoteblockType.values()) {
                Block block = JukeboxNoteblockType.JUKEBOX.equals(type)
                        ? new MoreJukeboxVariantBlock(woodType.getMapColor(), woodType.getName())
                        : new MoreNoteblockVariantBlock(woodType.getMapColor(), woodType.getName());
                MoreVariantHolder.setBlock(type, woodType, block);
                if (JukeboxNoteblockType.JUKEBOX.equals(type)) {
                    registerJukeboxBlock(block);
                } else {
                    registerNoteblockBlock(block);
                }
            }
        }
    }

    @Inject(method = "registerJukeboxNoteblockBlocks", at = @At("TAIL"), remap = false)
    private static void injectedRegisterJukeboxNoteblockBlocksAtTail(CallbackInfo ci) {
        registerJukeboxNoteblockBlockVariants(WoodTypeHolder.getWoodTypes());
    }
}
