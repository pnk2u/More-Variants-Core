package de.pnku.more_variants_pale_oak_backport.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.nemosmoreladdervariants.init.NmlvItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NmlvItems.class)
public abstract class NmlvItemsMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_LADDER_ITEM = registerPaleOakLadderItem();

    @Shadow
    public static Item registerLadderBlockItem(String name, Block ladderBlock) {
        throw new AssertionError();
    }

    @Unique
    private static Item registerPaleOakLadderItem() {
        Item ladderItem = registerLadderBlockItem(WOOD_TYPE.getName() + "_ladder", PaleOakVariantHolder.getBlock(PaleOakVariantHolder.LADDER_FAMILY, WOOD_TYPE));
        PaleOakVariantHolder.setItem(PaleOakVariantHolder.LADDER_FAMILY, WOOD_TYPE, ladderItem);
        return ladderItem;
    }
}
