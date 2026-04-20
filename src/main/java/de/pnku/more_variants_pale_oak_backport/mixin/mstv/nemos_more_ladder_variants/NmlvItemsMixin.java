package de.pnku.more_variants_pale_oak_backport.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_pale_oak_backport.mixin.holder.mstv.PaleOakLadderHolder;
import de.pnku.nemosmoreladdervariants.init.NmlvItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

import static de.pnku.more_variants_pale_oak_backport.PaleOakConstants.WOOD_TYPE;

@Mixin(NmlvItems.class)
public abstract class NmlvItemsMixin {
    @Unique
    private static final Item PALE_OAK_LADDER_ITEM = registerPaleOakLadderItem();

    @Invoker("registerLadderBlockItem")
    public static Item invokeRegisterLadderBlockItem(String name, Block ladderBlock) {
        throw new AssertionError();
    }

    @Unique
    private static Item registerPaleOakLadderItem() {
        Item ladderItem = invokeRegisterLadderBlockItem(WOOD_TYPE + "_ladder", PaleOakLadderHolder.getBlock());
        PaleOakLadderHolder.setItem(ladderItem);
        return ladderItem;
    }
}
