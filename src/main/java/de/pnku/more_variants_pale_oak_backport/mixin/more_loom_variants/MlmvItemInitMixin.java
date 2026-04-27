package de.pnku.more_variants_pale_oak_backport.mixin.more_loom_variants;

import de.pnku.mlmv.init.MlmvItemInit;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MlmvItemInit.class)
public abstract class MlmvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_LOOM_ITEM = registerPaleOakLoomItem();

    @Shadow
    private static void registerItem(BlockItem loom, Item loomAfter) {}

    @Unique
    private static BlockItem registerPaleOakLoomItem() {
        BlockItem loomItem = new BlockItem(PaleOakVariantHolder.getBlock(VariantType.LOOM, WOOD_TYPE), new Item.Properties());
        registerItem(loomItem, Items.LOOM);
        PaleOakVariantHolder.setItem(VariantType.LOOM, WOOD_TYPE, loomItem);
        return loomItem;
    }
}
