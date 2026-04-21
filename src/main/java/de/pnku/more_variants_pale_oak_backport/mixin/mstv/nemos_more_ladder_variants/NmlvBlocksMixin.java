package de.pnku.more_variants_pale_oak_backport.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakLadderHolder;
import de.pnku.nemosmoreladdervariants.init.NmlvBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(NmlvBlocks.class)
public abstract class NmlvBlocksMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Block PALE_OAK_LADDER = registerPaleOakLadderBlock();

    @Invoker("registerLadderBlock")
    public static Block invokeRegisterLadderBlock(String name, Block ladderBlock) {
        throw new AssertionError();
    }

    @Unique
    private static Block registerPaleOakLadderBlock() {
        Block ladderBlock = invokeRegisterLadderBlock(WOOD_TYPE.getName() + "_ladder", new LadderBlock(Block.Properties.ofFullCopy(Blocks.LADDER)));
        PaleOakLadderHolder.setBlock(WOOD_TYPE, ladderBlock);
        return ladderBlock;
    }
}
