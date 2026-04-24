package de.pnku.more_variants_pale_oak_backport.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.nemosmoreladdervariants.init.NmlvBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NmlvBlocks.class)
public abstract class NmlvBlocksMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_LADDER = registerPaleOakLadderBlock();

    @Shadow
    public static Block registerLadderBlock(String name, Block ladderBlock) {
        throw new AssertionError();
    }

    @Unique
    private static Block registerPaleOakLadderBlock() {
        Block ladderBlock = registerLadderBlock(WOOD_TYPE.getName() + "_ladder", new LadderBlock(Block.Properties.ofFullCopy(Blocks.LADDER)));
        PaleOakVariantHolder.setBlock(PaleOakVariantHolder.LADDER_FAMILY, WOOD_TYPE, ladderBlock);
        return ladderBlock;
    }
}
