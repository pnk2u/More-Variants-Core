package de.pnku.more_variants_pale_oak_backport.mixin.more_beehive_variants;

import de.pnku.mbhv.init.MbhvItemInit;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakBeehiveHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MbhvItemInit.class)
public abstract class MbhvItemInitMixin {
    @Unique
    private static final BlockItem PALE_OAK_BEEHIVE_ITEM = registerPaleOakBeehiveItem();

    @Invoker("registerBeehiveItem")
    public static void invokeRegisterBeehiveItem(BlockItem beehiveItem, Item beehiveAfter) {
        throw new AssertionError();
    }

    @Unique
    private static BlockItem registerPaleOakBeehiveItem() {
        BlockItem beehiveItem = new BlockItem(PaleOakBeehiveHolder.getBlock(), new Item.Properties());
        invokeRegisterBeehiveItem(beehiveItem, Items.BEEHIVE);
        PaleOakBeehiveHolder.setItem(beehiveItem);
        return beehiveItem;
    }
}
