package de.pnku.more_variants_core.mixin.mstv.nemos_more_ladder_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import de.pnku.nemosmoreladdervariants.init.NmlvItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NmlvItems.class)
public abstract class NmlvItemsMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final Item PALE_OAK_LADDER_ITEM = registerPaleOakLadderItem();

    @Shadow
    public static Item registerLadderBlockItem(String name, Block ladderBlock) {
        throw new AssertionError();
    }

    @Unique
    private static Item registerPaleOakLadderItem() {
        Item ladderItem = registerLadderBlockItem(WOOD_TYPE.getName() + "_ladder", MoreVariantHolder.getBlock(VariantType.LADDER, WOOD_TYPE));
        MoreVariantHolder.setItem(VariantType.LADDER, WOOD_TYPE, ladderItem);
        return ladderItem;
    }
}
