package de.pnku.more_variants_pale_oak_backport.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakLadderHolder;
import de.pnku.nemosmoreladdervariants.init.NmlvBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

import static de.pnku.more_variants_pale_oak_backport.PaleOakConstants.WOOD_TYPE;

@Mixin(NmlvBlocks.class)
public abstract class NmlvBlocksMixin {
    @Unique
    private static final Block PALE_OAK_LADDER = registerPaleOakLadderBlock();

    @Invoker("registerLadderBlock")
    public static Block invokeRegisterLadderBlock(String name, Block ladderBlock) {
        throw new AssertionError();
    }

    @Unique
    private static Block registerPaleOakLadderBlock() {
        Block ladderBlock = invokeRegisterLadderBlock(WOOD_TYPE + "_ladder", new LadderBlock(Block.Properties.ofFullCopy(Blocks.LADDER)));
        PaleOakLadderHolder.setBlock(ladderBlock);
        return ladderBlock;
    }
}
